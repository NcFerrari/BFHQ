package lp.fe.javafx.bf2components.leaderboard;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.be.business.dto.Awards;
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
    private final ObservableList<PlayerForSorting> playerForSortingList = FXCollections.observableArrayList();
    private final ComboBox<String> sortCategoriesComboBox = new ComboBox<>();
    private final ObservableList<StringProperty> items = FXCollections.observableArrayList();
    private final TabPane tabPane = new TabPane();
    private final TableView<PlayerForSorting> playerTable = new TableView<>();
    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private final NamespaceEnum[] methods = {NamespaceEnum.GET_RANK, NamespaceEnum.GET_KILLS,
            NamespaceEnum.GET_DEATHS, NamespaceEnum.GET_COUNT_OF_AWARDS, NamespaceEnum.GET_TIME,
            NamespaceEnum.GET_SCORE, NamespaceEnum.GET_WINS, NamespaceEnum.GET_LOSSES,
            NamespaceEnum.GET_TEAM_KILLING, NamespaceEnum.GET_TEAM_PLAYER_SCORE,
            NamespaceEnum.GET_KILL_STREAK};
    private Label categoryLabel;
    private Stage stage;

    public LeaderBoardOneThird(LeftSidePart leftSidePart) {
        initCategoryLabel();
        initCategoryComboBox();
        ((BorderPane) leftSidePart.getMainPane().getChildren().get(0)).setCenter(tabPane);
        tabPane.setId(NamespaceEnum.INNER_TAB_PANE_STYLE.getText());
        addTabs();
        createAndAddColumnsToTable();
        rewriteData();
    }

    private void addTabs() {
        Tab showLeaderBoardTab = new Tab();
        showLeaderBoardTab.setText(NodeTextEnum.SHOW_LEADERBOARD.getText(showLeaderBoardTab.textProperty()));
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new VBox(categoryLabel, sortCategoriesComboBox));
        borderPane.setCenter(playerTable);
        showLeaderBoardTab.setContent(borderPane);
        showLeaderBoardTab.setClosable(false);

        Tab showKillsTab = new Tab();
        showKillsTab.setText(NodeTextEnum.SHOW_KILLING.getText(showKillsTab.textProperty()));
        showKillsTab.setContent(null);
        showKillsTab.setClosable(false);
        tabPane.getTabs().addAll(showLeaderBoardTab, showKillsTab);
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
        NodeTextEnum[] comboBoxItems = new NodeTextEnum[methods.length];
        for (int i = 0; i < methods.length; i++) {
            comboBoxItems[i] = NodeTextEnum.valueOf(
                    NamespaceEnum.SORT_BY.getText() + methods[i].name().split(NamespaceEnum.GET_PREFIX.getText())[1]);
        }
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

    public void reloadData() {
        playerForSortingList.clear();
        manager.getPlayers().values().forEach(player -> {
            PlayerForSorting playerForSorting = new PlayerForSorting();
            playerForSorting.setName(player.getName());
            playerForSorting.setRankImage(pictureService.getSmallRankBF2Image(player.getRank()).getImageView());
            playerForSorting.setRank(player.getRank());
            playerForSorting.setKills(player.getKills().intValue());
            playerForSorting.setDeaths(player.getDeaths().intValue());
            List<Awards> awardList = manager.getAwards().get(player.getId());
            playerForSorting.setCountOfAwards(awardList != null ? awardList.size() : 0);
            playerForSorting.setTime(player.getTime().intValue());
            playerForSorting.setScore(player.getScore().intValue());
            playerForSorting.setWins(player.getWins().intValue());
            playerForSorting.setLosses(player.getLosses().intValue());
            playerForSorting.setTeamKilling(player.getTeamkills().intValue());
            playerForSorting.setTeamPlayerScore(player.getTeamscore().intValue());
            playerForSorting.setKillStreak(player.getKillstreak().intValue());
            playerForSortingList.add(playerForSorting);
        });
        sortCategoriesComboBox.getSelectionModel().clearSelection();
        rewriteData();
    }

    public void rewriteData() {
        PlayerForSorting.restartCounter();
        playerTable.getItems().clear();
        playerForSortingList.forEach(PlayerForSorting::clearValue);
        playerTable.getColumns().get(4).setText(sortCategoriesComboBox.getValue());
        String methodName = null;
        if (!sortCategoriesComboBox.getSelectionModel().isEmpty()) {
            methodName = methods[sortCategoriesComboBox.getSelectionModel().getSelectedIndex()].getText();
        }
        sortAndShowPlayers(methodName);
    }

    private void sortAndShowPlayers(String methodName) {
        playerForSortingList
                .stream()
                .sorted(Comparator.comparingInt(PlayerForSorting::getScore).reversed())
                .sorted((obj1, obj2) -> {
                    try {
                        return Integer.compare(
                                (Integer) obj2.getClass().getMethod(methodName).invoke(obj2),
                                (Integer) obj1.getClass().getMethod(methodName).invoke(obj1));
                    } catch (Exception e) {
                        return 0;
                    }
                })
                .forEach(playerForSorting -> {
                    playerForSorting.prepareValue(methodName);
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
        double tabWidth = oneThird / tabPane.getTabs().size() - 30;
        String tabStyle = String.format(NamespaceEnum.PREF_WIDTH_STYLE.getText(), tabWidth, tabWidth / 8);
        tabPane.getTabs().forEach(tab -> tab.setStyle(tabStyle));
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
            playerForSorting.getRankImage().setFitWidth(oneThird / 10);
            playerForSorting.getRankImage().setFitHeight(oneThird / 10);
        }
        playerTable.getItems().forEach(playerForSorting ->
                playerForSorting.getValue().wrappingWidthProperty()
                        .bind(playerTable.getColumns().get(4).widthProperty()));
    }
}
