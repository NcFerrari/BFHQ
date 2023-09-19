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
import lombok.Getter;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

@Getter
public class LeaderBoardPane extends BF2Component {

    private final Label categoryLabel;
    private final ComboBox<String> sortCategoriesComboBox = new ComboBox<>();
    private final ObservableList<StringProperty> items = FXCollections.observableArrayList();
    private final TableView<PlayerForSorting> playerTable = new TableView<>();
    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private Stage stage;

    public LeaderBoardPane() {
        super(NodeTextEnum.TAB_MENU_LEADERBOARDS);
        categoryLabel = new Label();
        categoryLabel.setId(NamespaceEnum.TITLE_STYLE.getText());
        categoryLabel.setText(NodeTextEnum.CATEGORY_TITLE.getText(categoryLabel.textProperty()));
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
        getLeftSidePart().getLeftTopPane().getChildren().addAll(categoryLabel, sortCategoriesComboBox);
        initCategoryComboBox();
        initPlayerTable();
    }

    private void initPlayerTable() {
        addColumn(NodeTextEnum.ORDER_TITLE, NamespaceEnum.ORDER);
        addColumn(NodeTextEnum.NAME_TITLE, NamespaceEnum.NAME);
        addColumn(NodeTextEnum.RANK_TITLE_2, NamespaceEnum.RANK);
        addColumn(NodeTextEnum.EMPTY_STRING, NamespaceEnum.VALUE);
        playerTable.getColumns().get(0).setSortable(true);
        ((BorderPane) getLeftSidePart().getMainPane().getChildren().get(0)).setCenter(playerTable);
        rewriteData();
    }

    private void addColumn(NodeTextEnum nodeTextEnum, NamespaceEnum attribute) {
        TableColumn<PlayerForSorting, Integer> column = new TableColumn<>();
        column.setText(nodeTextEnum.getText(column.textProperty()));
        column.setSortable(false);
        column.setReorderable(false);
        column.setResizable(false);
        column.setCellValueFactory(new PropertyValueFactory<>(attribute.getText()));
        playerTable.getColumns().add(column);
    }

    private void initCategoryComboBox() {
        addComboBoxItem(NodeTextEnum.SORT_BY_RANK);
        addComboBoxItem(NodeTextEnum.SORT_BY_KILLS);
        addComboBoxItem(NodeTextEnum.SORT_BY_DEATHS);
        addComboBoxItem(NodeTextEnum.SORT_BY_COUNT_OF_AWARDS);
        addComboBoxItem(NodeTextEnum.SORT_BY_TIME);
        addComboBoxItem(NodeTextEnum.SORT_BY_SCORE);
        addComboBoxItem(NodeTextEnum.SORT_BY_WINS);
        addComboBoxItem(NodeTextEnum.SORT_BY_LOSSES);
    }

    private void addComboBoxItem(NodeTextEnum nodeTextEnum) {
        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.setValue(nodeTextEnum.getText(stringProperty));
        items.add(stringProperty);
    }

    public void refreshComboBox() {
        sortCategoriesComboBox.getSelectionModel().clearSelection();
        sortCategoriesComboBox.getItems().clear();
        items.forEach(stringProperty -> sortCategoriesComboBox.getItems().add(stringProperty.get()));
    }

    @Override
    public void resize(@NotNull Stage stage) {
        super.resize(stage);
        this.stage = stage;
        double oneThird = stage.getWidth() / 3;
        categoryLabel.setPrefWidth(oneThird);
        sortCategoriesComboBox.setPrefWidth(oneThird);
        playerTable.getColumns().get(0).setPrefWidth(oneThird / 6.5);
        playerTable.getColumns().get(2).setPrefWidth(oneThird / 6.5);
        playerTable.getColumns().get(3).setPrefWidth(oneThird / 4);
        playerTable.getColumns().get(1).setPrefWidth(oneThird
                - playerTable.getColumns().get(0).getWidth()
                - playerTable.getColumns().get(2).getWidth()
                - playerTable.getColumns().get(3).getWidth()
                - oneThird / 20);
        for (PlayerForSorting playerForSorting : playerTable.getItems()) {
            playerForSorting.getRank().setFitWidth(oneThird / 10);
            playerForSorting.getRank().setFitHeight(oneThird / 10);
        }
    }

    @Override
    public void reloadData() {
        getLeftSidePart().fillNameComboBox();
    }

    @Override
    public void rewriteData() {
        playerTable.getItems().clear();
        PlayerForSorting.restartCounter();
        playerTable.getColumns().get(3).setText(sortCategoriesComboBox.getValue());
        ObservableList<PlayerForSorting> playerForSortingList = FXCollections.observableArrayList();
        manager.getPlayers().values().forEach(player -> {
            PlayerForSorting playerForSorting = new PlayerForSorting();
            playerForSorting.setName(player.getName());
            BF2Image bf2Image = pictureService.getSmallRankBF2Image(player.getRank());
            playerForSorting.setRank(bf2Image.getImageView());
            switch (sortCategoriesComboBox.getSelectionModel().getSelectedIndex()) {
                case 0: playerForSorting.setValue(player.getRank());break;
                case 1: playerForSorting.setValue(player.getKills().intValue());break;
                case 2: playerForSorting.setValue(player.getDeaths().intValue());break;
                case 3: break;
                case 4: playerForSorting.setValue(player.getTime().intValue());break;
                case 5: playerForSorting.setValue(player.getScore().intValue());break;
                case 6: playerForSorting.setValue(player.getWins().intValue());break;
                case 7: playerForSorting.setValue(player.getLosses().intValue());break;
                default:
            }
            playerForSortingList.add(playerForSorting);
        });
        playerForSortingList
                .stream()
                .sorted(Comparator.comparingInt(PlayerForSorting::getValue).reversed())
                .forEach(playerForSorting -> {
                    playerForSorting.setOrder();
                    playerTable.getItems().add(playerForSorting);
                });
        if (stage != null) {
            resize(stage);
        }
    }
}
