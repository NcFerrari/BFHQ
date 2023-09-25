package lp.fe.javafx.bf2components.leaderboard;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.be.business.dto.Awards;
import lp.be.business.dto.KillsForPlayer;
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
    private final TableView<PlayerForSorting> statisticTable = new TableView<>();
    private final TableView<KillsForPlayer> killsTable = new TableView<>();
    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private final NamespaceEnum[] methods = {NamespaceEnum.GET_RANK, NamespaceEnum.GET_KILLS,
            NamespaceEnum.GET_DEATHS, NamespaceEnum.GET_COUNT_OF_AWARDS, NamespaceEnum.GET_TIME,
            NamespaceEnum.GET_SCORE, NamespaceEnum.GET_WINS, NamespaceEnum.GET_LOSSES,
            NamespaceEnum.GET_TEAM_KILLING, NamespaceEnum.GET_TEAM_PLAYER_SCORE,
            NamespaceEnum.GET_KILL_STREAK};
    private final Tab showKillsTab = new Tab();
    private final Button showKillsTableButton = new Button();
    private Label categoryLabel;
    private Stage stage;

    public LeaderBoardOneThird(LeftSidePart leftSidePart) {
        initCategoryLabel();
        initCategoryComboBox();
        showKillsTableButton.setText(NodeTextEnum.SHOW_KILLS_BUTTON_TITLE.getText(showKillsTableButton.textProperty()));
        showKillsTableButton.setOnAction(evt -> {
            if (manager.getSelectedPlayer() != null) {
                showKillsTab.setContent(killsTable);
                fillKillsTab(manager.getKillsForSelectedPlayer());
            }
        });
        ((BorderPane) leftSidePart.getMainPane().getChildren().get(0)).setCenter(tabPane);
        tabPane.setId(NamespaceEnum.INNER_TAB_PANE_STYLE.getText());
        addTabs();
        createAndAddColumnsToTable();
        createKillsTable();
        rewriteData();
    }

    private void addTabs() {
        Tab showLeaderBoardTab = new Tab();
        showLeaderBoardTab.setText(NodeTextEnum.SHOW_LEADERBOARD.getText(showLeaderBoardTab.textProperty()));
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new VBox(categoryLabel, sortCategoriesComboBox));
        borderPane.setCenter(statisticTable);
        showLeaderBoardTab.setContent(borderPane);
        showLeaderBoardTab.setClosable(false);

        showKillsTab.setText(NodeTextEnum.SHOW_KILLING.getText(showKillsTab.textProperty()));
        resetKillsTab();
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
        createAndAddColumn(statisticTable, NodeTextEnum.ORDER_TITLE, NamespaceEnum.ORDER);
        createAndAddColumn(statisticTable, NodeTextEnum.NAME_TITLE, NamespaceEnum.NAME);
        createAndAddColumn(statisticTable, NodeTextEnum.RANK_TITLE_2, NamespaceEnum.RANK);
        createAndAddColumn(statisticTable, NodeTextEnum.SORT_BY_SCORE, NamespaceEnum.SCORE);
        createAndAddColumn(statisticTable, null, NamespaceEnum.VALUE);
        statisticTable.getColumns().get(0).setSortable(true);
    }

    private void createKillsTable() {
        createAndAddColumn(killsTable, NodeTextEnum.PLAYER_KILLS_COUNT, NamespaceEnum.NAME_OF_KILLED_PLAYER);
        createAndAddColumn(killsTable, NodeTextEnum.COUNT, NamespaceEnum.COUNT_OF_KILLS_ANOTHER_PLAYER);
        createAndAddColumn(killsTable, NodeTextEnum.KILLS_BY_PLAYER_COUNT, NamespaceEnum.NAME_OF_PLAYER_WHO_KILLED);
        createAndAddColumn(killsTable, NodeTextEnum.COUNT, NamespaceEnum.COUNT_OF_KILLS_BY_ANOTHER_PLAYER);
    }

    private <T> void createAndAddColumn(TableView<T> table, NodeTextEnum nodeTextEnum, NamespaceEnum attribute) {
        TableColumn<T, Object> column = new TableColumn<>();
        if (nodeTextEnum != null) {
            column.setText(nodeTextEnum.getText(column.textProperty()));
        }
        column.setSortable(false);
        column.setReorderable(false);
        column.setResizable(false);
        column.setCellValueFactory(new PropertyValueFactory<>(attribute.getText()));
        table.getColumns().add(column);
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
        statisticTable.getItems().clear();
        playerForSortingList.forEach(PlayerForSorting::clearValue);
        statisticTable.getColumns().get(4).setText(sortCategoriesComboBox.getValue());
        String methodName = null;
        if (!sortCategoriesComboBox.getSelectionModel().isEmpty()) {
            methodName = methods[sortCategoriesComboBox.getSelectionModel().getSelectedIndex()].getText();
        }
        sortAndShowPlayers(methodName);
    }

    private void fillKillsTab(List<KillsForPlayer> killsForPlayerList) {
        killsTable.getItems().clear();
        killsForPlayerList.forEach(killsTable.getItems()::add);
    }

    public void resetKillsTab() {
        showKillsTab.setContent(showKillsTableButton);
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
                    statisticTable.getItems().add(playerForSorting);
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
        statisticTable.getColumns().get(0).setPrefWidth(oneThird / 6.5);
        statisticTable.getColumns().get(2).setPrefWidth(oneThird / 6.5);
        statisticTable.getColumns().get(3).setPrefWidth(oneThird / 6.5);
        statisticTable.getColumns().get(4).setPrefWidth(oneThird / 4);
        statisticTable.getColumns().get(1).setPrefWidth(oneThird
                - statisticTable.getColumns().get(0).getWidth()
                - statisticTable.getColumns().get(2).getWidth()
                - statisticTable.getColumns().get(3).getWidth()
                - statisticTable.getColumns().get(4).getWidth()
                - oneThird / 20);
        for (PlayerForSorting playerForSorting : statisticTable.getItems()) {
            playerForSorting.getRankImage().setFitWidth(oneThird / 10);
            playerForSorting.getRankImage().setFitHeight(oneThird / 10);
        }
        statisticTable.getItems().forEach(playerForSorting ->
                playerForSorting.getValue().wrappingWidthProperty()
                        .bind(statisticTable.getColumns().get(4).widthProperty()));
        killsTable.getColumns().get(0).setPrefWidth(3 * oneThird / 8);
        killsTable.getColumns().get(1).setPrefWidth(oneThird / 8);
        killsTable.getColumns().get(2).setPrefWidth(killsTable.getColumns().get(0).getPrefWidth());
        killsTable.getColumns().get(3).setPrefWidth(killsTable.getColumns().get(1).getPrefWidth());
    }
}
