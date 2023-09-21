package lp.fe.javafx.bf2components.leaderboard;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lp.Manager;
import lp.be.business.dto.Awards;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.LeftSidePart;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeaderBoardOneThird {

    private final Manager manager = Manager.getInstance();
    private final ComboBox<String> sortCategoriesComboBox = new ComboBox<>();
    private final ObservableList<StringProperty> items = FXCollections.observableArrayList();
    private final TableView<PlayerForSorting> playerTable = new TableView<>();
    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private Label categoryLabel;
    private Stage stage;

    public LeaderBoardOneThird(LeftSidePart leftSidePart) {
        initCategoryLabel();
        initCategoryComboBox();
        leftSidePart.getLeftTopPane().getChildren().addAll(categoryLabel, sortCategoriesComboBox);
        ((BorderPane) leftSidePart.getMainPane().getChildren().get(0)).setCenter(playerTable);
        createAndAddColumnsToTable();
        rewriteData();
    }

    private void initCategoryLabel() {
        categoryLabel = new Label();
        categoryLabel.setId(NamespaceEnum.TITLE_STYLE.getText());
        categoryLabel.setText(NodeTextEnum.CATEGORY_TITLE.getText(categoryLabel.textProperty()));
    }

    private void initCategoryComboBox() {
        sortCategoriesComboBox.setEditable(true);
        sortCategoriesComboBox.getEditor().setOnKeyPressed(evt -> {
            if (evt.getCode().equals(KeyCode.UP)) {
                sortCategoriesComboBox.getSelectionModel().selectPrevious();
            } else if (evt.getCode().equals(KeyCode.DOWN)) {
                sortCategoriesComboBox.getSelectionModel().selectNext();
            }
            rewriteData();
        });
        sortCategoriesComboBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> rewriteData());
        items.addListener((ListChangeListener<? super StringProperty>) c -> {
            c.next();
            sortCategoriesComboBox.getItems().add(items.get(c.getFrom()).get());
        });
        NodeTextEnum[] comboBoxItems = {NodeTextEnum.SORT_BY_RANK, NodeTextEnum.SORT_BY_KILLS,
                NodeTextEnum.SORT_BY_DEATHS, NodeTextEnum.SORT_BY_COUNT_OF_AWARDS, NodeTextEnum.SORT_BY_TIME,
                NodeTextEnum.SORT_BY_SCORE, NodeTextEnum.SORT_BY_WINS, NodeTextEnum.SORT_BY_LOSSES,
                NodeTextEnum.SORT_BY_TEAM_KILLING, NodeTextEnum.SORT_BY_TEAM_PLAYER_SCORE,
                NodeTextEnum.SORT_BY_KILL_STREAK};
        Arrays.asList(comboBoxItems).forEach(this::addComboBoxItem);
    }

    private void addComboBoxItem(NodeTextEnum nodeTextEnum) {
        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.setValue(nodeTextEnum.getText(stringProperty));
        items.add(stringProperty);
    }

    private void createAndAddColumnsToTable() {
        createAndAddColumn(NodeTextEnum.ORDER_TITLE, NamespaceEnum.ORDER);
        createAndAddColumn(NodeTextEnum.NAME_TITLE, NamespaceEnum.NAME);
        createAndAddColumn(NodeTextEnum.RANK_TITLE_2, NamespaceEnum.RANK);
        createAndAddColumn(NodeTextEnum.SORT_BY_SCORE, NamespaceEnum.SCORE);
        createAndAddColumn(null, NamespaceEnum.VALUE);
        playerTable.getColumns().get(0).setSortable(true);
    }

    private void createAndAddColumn(NodeTextEnum nodeTextEnum, NamespaceEnum attribute) {
        TableColumn<PlayerForSorting, Integer> column = new TableColumn<>();
        if (nodeTextEnum != null) {
            column.setText(nodeTextEnum.getText(column.textProperty()));
        }
        column.setSortable(false);
        column.setReorderable(false);
        column.setResizable(false);
        column.setCellValueFactory(new PropertyValueFactory<>(attribute.getText()));
        playerTable.getColumns().add(column);
    }

    public void refreshComboBox() {
        sortCategoriesComboBox.getSelectionModel().clearSelection();
        sortCategoriesComboBox.getItems().clear();
        items.forEach(stringProperty -> sortCategoriesComboBox.getItems().add(stringProperty.get()));
    }

    public void rewriteData() {
        PlayerForSorting.restartCounter();
        playerTable.getItems().clear();
        playerTable.getColumns().get(4).setText(sortCategoriesComboBox.getValue());
        ObservableList<PlayerForSorting> playerForSortingList = FXCollections.observableArrayList();
        manager.getPlayers().values().forEach(player -> {
            PlayerForSorting playerForSorting = new PlayerForSorting();
            playerForSorting.setName(player.getName());
            playerForSorting.setScore(player.getScore().intValue());
            BF2Image bf2Image = pictureService.getSmallRankBF2Image(player.getRank());
            playerForSorting.setRank(bf2Image.getImageView());
            switch (sortCategoriesComboBox.getSelectionModel().getSelectedIndex()) {
                case 0:
                    playerForSorting.setSortingValue(player.getRank());
                    break;
                case 1:
                    playerForSorting.setSortingValue(player.getKills().intValue());
                    break;
                case 2:
                    playerForSorting.setSortingValue(player.getDeaths().intValue());
                    break;
                case 3:
                    List<Awards> list = manager.getAwards().get(player.getId());
                    if (list != null) {
                        playerForSorting.setSortingValue(list.size());
                    } else {
                        playerForSorting.setSortingValue(0);
                    }
                    break;
                case 4:
                    playerForSorting.setSortingValue(player.getTime().intValue());
                    break;
                case 5:
                    playerForSorting.setSortingValue(player.getScore().intValue());
                    break;
                case 6:
                    playerForSorting.setSortingValue(player.getWins().intValue());
                    break;
                case 7:
                    playerForSorting.setSortingValue(player.getLosses().intValue());
                    break;
                case 8:
                    playerForSorting.setSortingValue(player.getTeamkills().intValue());
                    break;
                case 9:
                    playerForSorting.setSortingValue(player.getTeamscore().intValue());
                    break;
                case 10:
                    playerForSorting.setSortingValue(player.getKillstreak().intValue());
                    break;
                default:
            }
            playerForSortingList.add(playerForSorting);
        });
        sortAndAddNewListToTable(playerForSortingList);
    }

    private void sortAndAddNewListToTable(ObservableList<PlayerForSorting> playerForSortingList) {
        playerForSortingList
                .stream()
                .sorted(Comparator.comparingInt(PlayerForSorting::getScore).reversed())
                .sorted(Comparator.comparingInt(PlayerForSorting::getSortingValue).reversed())
                .forEach(playerForSorting -> {
                    if (sortCategoriesComboBox.getSelectionModel().getSelectedIndex() == 0) {
                        playerForSorting.setValueText(NodeTextEnum
                                .getRankTitleFromInt(playerForSorting.getSortingValue()).getSelectedText());
                        playerForSorting.getValue().wrappingWidthProperty()
                                .bind(playerTable.getColumns().get(4).widthProperty());
                    } else if (sortCategoriesComboBox.getSelectionModel().getSelectedIndex() == 4) {
                        playerForSorting.setValueText(manager.longToTime((long) playerForSorting.getSortingValue()));
                    } else if (sortCategoriesComboBox.getSelectionModel().getSelectedIndex() > -1) {
                        playerForSorting.setValueText(String.valueOf(playerForSorting.getSortingValue()));
                    }
                    playerForSorting.setOrder();
                    playerTable.getItems().add(playerForSorting);
                });
        if (stage != null) {
            resize(stage);
        }
    }

    public void resize(Stage stage) {
        this.stage = stage;
        double oneThird = stage.getWidth() / 3;
        categoryLabel.setPrefWidth(oneThird);
        sortCategoriesComboBox.setPrefWidth(oneThird);
        playerTable.getColumns().get(0).setPrefWidth(oneThird / 6.5);
        playerTable.getColumns().get(2).setPrefWidth(oneThird / 6.5);
        playerTable.getColumns().get(3).setPrefWidth(oneThird / 6.5);
        playerTable.getColumns().get(4).setPrefWidth(oneThird / 4);
        playerTable.getColumns().get(1).setPrefWidth(oneThird
                - playerTable.getColumns().get(0).getWidth()
                - playerTable.getColumns().get(2).getWidth()
                - playerTable.getColumns().get(3).getWidth()
                - playerTable.getColumns().get(4).getWidth()
                - oneThird / 20);
        for (PlayerForSorting playerForSorting : playerTable.getItems()) {
            playerForSorting.getRank().setFitWidth(oneThird / 10);
            playerForSorting.getRank().setFitHeight(oneThird / 10);
        }
    }
}
