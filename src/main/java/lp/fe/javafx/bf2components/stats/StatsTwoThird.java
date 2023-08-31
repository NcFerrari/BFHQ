package lp.fe.javafx.bf2components.stats;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import lombok.Getter;
import lp.Manager;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.enums.TabPositionEnum;
import lp.fe.javafx.bf2components.RightSidePart;

import java.util.EnumMap;
import java.util.Map;

@Getter
public class StatsTwoThird {

    private final Manager manager = Manager.getInstance();
    private final TabPane upTabPane = new TabPane();
    private final TabPane downTabPane = new TabPane();
    private final VBox rightPane;
    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private final Map<NodeTextEnum, TableView<ObjectProperty<Object>[]>> tableMap = new EnumMap<>(NodeTextEnum.class);
    private final ObservableList<BF2Image> images = FXCollections.observableArrayList();

    public StatsTwoThird(RightSidePart rightSidePart) {
        this.rightPane = rightSidePart.getRightPane();
        rightPane.getChildren().addAll(upTabPane, downTabPane);

        tabMostPlayed();
        tabArmyStats();
        tabMapStats();
        tabTeamWork();
        tabPlayerStats();
        tabKitEquipment();
        tabVehicleCategory();
        tabKits();
    }

    public void rewriteData() {
        reloadTabMostPlayedData();
        reloadTabArmyStats();
        reloadTabMapStats();
        reloadTabTeamWork();
        reloadTabPlayerStats();
        reloadTabKitEquipment();
        reloadTabVehicleCategory();
        reloadKits();
    }

    public void resize(Stage stage) {
        double twoThird = 2 * stage.getWidth() / 3 - 18;
        rightPane.setMinWidth(twoThird);
        rightPane.setMaxSize(rightPane.getMinWidth(), rightPane.getMinHeight());
        upTabPane.setPrefHeight(stage.getHeight() / 2);
        downTabPane.setPrefHeight(stage.getHeight() / 2);
        double upTabWidth = twoThird / upTabPane.getTabs().size() - 30;
        String upStyle = String.format(NamespaceEnum.PREF_WIDTH_STYLE.getText(), upTabWidth, upTabWidth / 7.57);
        upTabPane.getTabs().forEach(tab -> tab.setStyle(upStyle));
        double downTabWidth = twoThird / downTabPane.getTabs().size() - 30;
        String downStyle = String.format(NamespaceEnum.PREF_WIDTH_STYLE.getText(), downTabWidth, downTabWidth / 7.57);
        downTabPane.getTabs().forEach(tab -> tab.setStyle(downStyle));
        tableMap.values().forEach(table -> table.getColumns().forEach(column ->
                column.setPrefWidth((twoThird / table.getColumns().size()) - 30)));
        for (ObjectProperty<Object> objectImage : tableMap.get(NodeTextEnum.MOST_PLAYED_TAB).getItems().get(1)) {
            ((ImageView) objectImage.get()).setFitWidth(twoThird / 8);
            ((ImageView) objectImage.get()).setFitHeight(twoThird / 8);
        }
        for (ObjectProperty<Object>[] rowObject : tableMap.get(NodeTextEnum.ARMY_STATS_TAB).getItems()) {
            ((ImageView) rowObject[0].get()).setFitWidth(twoThird / 6);
            ((ImageView) rowObject[0].get()).setFitHeight(twoThird / 24);
        }
    }

    private void createTable(TabPositionEnum tabContainer, NodeTextEnum tabTitle,
                             NodeTextEnum[] columns, int rowCounts) {
        TableView<ObjectProperty<Object>[]> table = new TableView<>();
        addTabToTabPane(tabContainer, tabTitle, table);
        tableMap.put(tabTitle, table);

        ObservableList<TableColumn<ObjectProperty<Object>[], Object>> localColumns =
                FXCollections.observableArrayList();

        for (int i = 0; i < columns.length; i++) {
            localColumns.add(generateNewColumn(columns[i], i));
        }

        table.getColumns().addAll(localColumns);

        ObservableList<ObjectProperty<Object>[]> rows = FXCollections.observableArrayList();
        for (int i = 0; i < rowCounts; i++) {
            rows.add(new ObjectProperty[columns.length]);
        }
        table.setItems(rows);
    }

    private void addTabToTabPane(TabPositionEnum tabPosition, NodeTextEnum fxText, TableView<?> table) {
        TabPane chosenTab = upTabPane;
        if (tabPosition == TabPositionEnum.DOWN) {
            chosenTab = downTabPane;
        }
        Tab tab = new Tab();
        chosenTab.getTabs().add(tab);
        tab.setClosable(false);
        tab.setText(fxText.getText(tab.textProperty()));
        tab.setContent(table);
    }

    private TableColumn<ObjectProperty<Object>[], Object> generateNewColumn(NodeTextEnum title, int columnOrder) {
        TableColumn<ObjectProperty<Object>[], Object> column = new TableColumn<>();
        column.setText(title.getText(column.textProperty()));
        column.setCellValueFactory(data -> data.getValue()[columnOrder]);
        column.setSortable(false);
        column.setResizable(false);
        column.setReorderable(false);
        return column;
    }

//    //============================= tab pane elements ==========================

    private void tabMostPlayed() {
        NodeTextEnum[] columnTitles = {NodeTextEnum.KITS, NodeTextEnum.VEHICLE_CATEGORY, NodeTextEnum.WEAPON,
                NodeTextEnum.MAP};
        createTable(TabPositionEnum.UP, NodeTextEnum.MOST_PLAYED_TAB, columnTitles, 3);
        ObservableList<ObjectProperty<Object>[]> rowObject = tableMap.get(NodeTextEnum.MOST_PLAYED_TAB).getItems();
        for (int i = 0; i < columnTitles.length; i++) {
            Label textLabel = new Label();
            tableMap.get(NodeTextEnum.MOST_PLAYED_TAB).getColumns().get(0).
                    setId(NamespaceEnum.FIRST_COLUMN_MOST_PLAYED_STYLE.getText());
            textLabel.setText(NodeTextEnum.EMPTY_STRING.getText(textLabel.textProperty()));
            images.add(new BF2Image());
            rowObject.get(0)[i] = new SimpleObjectProperty<>(textLabel);
            rowObject.get(1)[i] = new SimpleObjectProperty<>(images.get(i).getImageView());
            addLabelToRow(rowObject.get(2), i);
        }
    }

    private void tabArmyStats() {
        NodeTextEnum[] columnTitles = {NodeTextEnum.EMPTY_STRING, NodeTextEnum.EMPTY_STRING, NodeTextEnum.TIME,
                NodeTextEnum.WINS, NodeTextEnum.LOSSES};
        BF2Image[] factionImages = {
                pictureService.getFactionBF2Image(0),
                pictureService.getFactionBF2Image(1),
                pictureService.getFactionBF2Image(2),
                pictureService.getFactionBF2Image(9)};
        NodeTextEnum[] factionNames = {
                NodeTextEnum.FACTION_USMC,
                NodeTextEnum.FACTION_MEC,
                NodeTextEnum.FACTION_PLA,
                NodeTextEnum.FACTION_EU};
        createTable(TabPositionEnum.UP, NodeTextEnum.ARMY_STATS_TAB, columnTitles, factionImages.length);
        tableMap.get(NodeTextEnum.ARMY_STATS_TAB).getColumns().get(1).setId(NamespaceEnum.FIRST_COLUMN_STYLE.getText());
        tableMap.get(NodeTextEnum.ARMY_STATS_TAB).setId(NamespaceEnum.CENTER_RIGHT_TABLE_STYLE.getText());
        ObservableList<ObjectProperty<Object>[]> rowObject = tableMap.get(NodeTextEnum.ARMY_STATS_TAB).getItems();
        for (int i = 0; i < rowObject.size(); i++) {
            rowObject.get(i)[0] = new SimpleObjectProperty<>(factionImages[i].getImageView());
            Label armyLabel = generateNewLabel(factionNames[i]);
            armyLabel.setFont(new Font(11.5));
            rowObject.get(i)[1] = new SimpleObjectProperty<>(armyLabel);
            addLabelToRow(rowObject.get(i), 2);
            addLabelToRow(rowObject.get(i), 3);
            addLabelToRow(rowObject.get(i), 4);
        }
    }

    private void tabMapStats() {
        NodeTextEnum[] columnTitles = {NodeTextEnum.MAP_NAME, NodeTextEnum.WINS, NodeTextEnum.LOSSES,
                NodeTextEnum.TIME};
        NodeTextEnum[] maps = {NodeTextEnum.MAP_0, NodeTextEnum.MAP_1, NodeTextEnum.MAP_2, NodeTextEnum.MAP_3,
                NodeTextEnum.MAP_4, NodeTextEnum.MAP_5, NodeTextEnum.MAP_6, NodeTextEnum.MAP_10, NodeTextEnum.MAP_11,
                NodeTextEnum.MAP_12, NodeTextEnum.MAP_100, NodeTextEnum.MAP_101, NodeTextEnum.MAP_102,
                NodeTextEnum.MAP_103, NodeTextEnum.MAP_105, NodeTextEnum.MAP_110, NodeTextEnum.MAP_200,
                NodeTextEnum.MAP_201, NodeTextEnum.MAP_202, NodeTextEnum.MAP_601, NodeTextEnum.MAP_700};
        generateTab(TabPositionEnum.UP, NodeTextEnum.MAP_STATS_TAB, columnTitles, maps, 0);
    }

    private void tabTeamWork() {
        NodeTextEnum[] columnTitles = {NodeTextEnum.TEAM_ACTION, NodeTextEnum.SCORE};
        NodeTextEnum[] teamActions = {NodeTextEnum.TEAM_WORK_SCORE, NodeTextEnum.CAPTURED_CP,
                NodeTextEnum.CAPTURE_ASSIST, NodeTextEnum.DEFENDED_CP, NodeTextEnum.KILL_ASSISTS, NodeTextEnum.HEAL,
                NodeTextEnum.REVIVE, NodeTextEnum.SUPPORT, NodeTextEnum.REPAIR, NodeTextEnum.DRIVER_SPECIAL};
        generateTab(TabPositionEnum.UP, NodeTextEnum.TEAM_WORK_TITLE, columnTitles, teamActions, 0);
        Tooltip tooltip = new Tooltip();
        tooltip.setText(NodeTextEnum.DRIVER_SPECIAL_TOOLTIP.getText(tooltip.textProperty()));
        ((Label) tableMap.get(NodeTextEnum.TEAM_WORK_TITLE).getItems().get(teamActions.length - 1)[0].get()).
                setTooltip(tooltip);
    }

    private void tabPlayerStats() {
        NodeTextEnum[] columnTitles = {NodeTextEnum.PLAYER_ACTION, NodeTextEnum.SCORE};
        NodeTextEnum[] playerActions = {NodeTextEnum.KILL_STREAK, NodeTextEnum.DEATH_STREAK,
                NodeTextEnum.DRIVER_ASSISTS, NodeTextEnum.CMD_SCORE, NodeTextEnum.CMD_TIME, NodeTextEnum.SQL_TIME,
                NodeTextEnum.SQM_TIME, NodeTextEnum.LW_TIME};
        generateTab(TabPositionEnum.UP, NodeTextEnum.PLAYER_STATS, columnTitles, playerActions, 0);
    }

    private void tabKitEquipment() {
        NodeTextEnum[] columnTitles = {NodeTextEnum.OVERALL_ACCURACY, NodeTextEnum.ACCURACY, NodeTextEnum.KILLS_TEXT,
                NodeTextEnum.DEATHS_TEXT, NodeTextEnum.KILL_DEATH_RATIO, NodeTextEnum.TIME_USED};
        NodeTextEnum[] weapons = {NodeTextEnum.WEAPON_0, NodeTextEnum.WEAPON_1, NodeTextEnum.WEAPON_2,
                NodeTextEnum.WEAPON_3, NodeTextEnum.WEAPON_4, NodeTextEnum.WEAPON_5, NodeTextEnum.WEAPON_6,
                NodeTextEnum.WEAPON_7, NodeTextEnum.WEAPON_8, NodeTextEnum.WEAPON_9, NodeTextEnum.WEAPON_13,
                NodeTextEnum.WEAPON_EXPLOSIVES, NodeTextEnum.WEAPON_11, NodeTextEnum.RESULT};
        generateTab(TabPositionEnum.DOWN, NodeTextEnum.KIT_EQUIPMENT, columnTitles, weapons, 19);
    }

    private void tabVehicleCategory() {
        NodeTextEnum[] columnTitles = {NodeTextEnum.EMPTY_STRING, NodeTextEnum.KILLS_TEXT, NodeTextEnum.ROAD_KILLS,
                NodeTextEnum.DEATHS_TEXT, NodeTextEnum.KILL_DEATH_RATIO, NodeTextEnum.TIME_USED};
        NodeTextEnum[] vehicles = {NodeTextEnum.VEHICLE_0, NodeTextEnum.VEHICLE_1, NodeTextEnum.VEHICLE_2, NodeTextEnum.VEHICLE_3,
                NodeTextEnum.VEHICLE_4, NodeTextEnum.VEHICLE_6};
        generateTab(TabPositionEnum.DOWN, NodeTextEnum.VEHICLE_CATEGORY, columnTitles, vehicles, 18);
    }

    private void tabKits() {
        NodeTextEnum[] columnTitles = {NodeTextEnum.EMPTY_STRING, NodeTextEnum.KILLS_TEXT, NodeTextEnum.DEATHS_TEXT,
                NodeTextEnum.KILL_DEATH_RATIO, NodeTextEnum.TIME_USED};
        NodeTextEnum[] kits = {NodeTextEnum.KIT_0, NodeTextEnum.KIT_1, NodeTextEnum.KIT_2, NodeTextEnum.KIT_3, NodeTextEnum.KIT_4,
                NodeTextEnum.KIT_5, NodeTextEnum.KIT_6};
        generateTab(TabPositionEnum.DOWN, NodeTextEnum.KITS, columnTitles, kits, 19);
    }

    private void reloadTabMostPlayedData() {
        Map<NamespaceEnum, String[]> data;
        try {
            data = manager.getMostPlayedData();
            NamespaceEnum[] categories = {NamespaceEnum.KIT, NamespaceEnum.VEHICLE,
                    NamespaceEnum.WEAPON, NamespaceEnum.MAP};
            ObservableList<ObjectProperty<Object>[]> rowObject = tableMap.get(NodeTextEnum.MOST_PLAYED_TAB).getItems();
            for (int i = 0; i < rowObject.get(0).length; i++) {
                NodeTextEnum.getComponentsForTranslate().replace(((Label) rowObject.get(0)[i].get()).textProperty(),
                        NodeTextEnum.valueOf(categories[i].getText() + data.get(categories[i])[0]));
                BF2Image bf2image;
                switch (categories[i]) {
                    case KIT:
                        bf2image = pictureService.getKitBF2Image(Integer.parseInt(data.get(categories[i])[0]));
                        break;
                    case VEHICLE:
                        bf2image = pictureService.getVehicleBF2Image(Integer.parseInt(data.get(categories[i])[0]));
                        break;
                    case WEAPON:
                        bf2image = pictureService.getWeaponBF2Image(Integer.parseInt(data.get(categories[i])[0]));
                        break;
                    case MAP:
                        bf2image = pictureService.getMapBF2Image(Integer.parseInt(data.get(categories[i])[0]));
                        break;
                    default:
                        bf2image = null;
                }
                if (bf2image != null) {
                    images.get(i).updateData(bf2image, manager.isShowToolkit());
                }
                ((Label) rowObject.get(2)[i].get()).setText(data.get(categories[i])[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reloadTabArmyStats() {
        Map<NodeTextEnum, String[]> data = manager.getArmyStatsData();
        NodeTextEnum[] factions = {NodeTextEnum.FACTION_USMC, NodeTextEnum.FACTION_MEC, NodeTextEnum.FACTION_PLA,
                NodeTextEnum.FACTION_EU};
        for (int i = 0; i < tableMap.get(NodeTextEnum.ARMY_STATS_TAB).getItems().size(); i++) {
            ObjectProperty<Object>[] row = tableMap.get(NodeTextEnum.ARMY_STATS_TAB).getItems().get(i);
            ((Label) row[2].get()).setText(data.get(factions[i])[0]);
            ((Label) row[3].get()).setText(data.get(factions[i])[1]);
            ((Label) row[4].get()).setText(data.get(factions[i])[2]);
        }
    }

    private void reloadTabMapStats() {
        Map<NodeTextEnum, String[]> data = manager.getMapsStatsData();
        NodeTextEnum[] maps = {NodeTextEnum.MAP_0, NodeTextEnum.MAP_1, NodeTextEnum.MAP_2, NodeTextEnum.MAP_3,
                NodeTextEnum.MAP_4, NodeTextEnum.MAP_5, NodeTextEnum.MAP_6, NodeTextEnum.MAP_10, NodeTextEnum.MAP_11,
                NodeTextEnum.MAP_12, NodeTextEnum.MAP_100, NodeTextEnum.MAP_101, NodeTextEnum.MAP_102,
                NodeTextEnum.MAP_103, NodeTextEnum.MAP_105, NodeTextEnum.MAP_110, NodeTextEnum.MAP_200,
                NodeTextEnum.MAP_201, NodeTextEnum.MAP_202, NodeTextEnum.MAP_601, NodeTextEnum.MAP_700};
        ObservableList<ObjectProperty<Object>[]> rowObject = tableMap.get(NodeTextEnum.MAP_STATS_TAB).getItems();
        for (int i = 0; i < rowObject.size(); i++) {
            String wins = NamespaceEnum.X_MARK.getText();
            String losses = NamespaceEnum.X_MARK.getText();
            String time = NamespaceEnum.X_MARK.getText();
            String id = NamespaceEnum.X_MARK.getText();
            if (data.containsKey(maps[i])) {
                wins = data.get(maps[i])[0];
                losses = data.get(maps[i])[1];
                time = data.get(maps[i])[2];
                id = NamespaceEnum.EMPTY_STRING.getText();
            }
            Label winLabel = ((Label) rowObject.get(i)[1].get());
            winLabel.setText(wins);
            winLabel.setId(id);
            Label lossesLabel = ((Label) rowObject.get(i)[2].get());
            lossesLabel.setText(losses);
            lossesLabel.setId(id);
            Label timeLabel = ((Label) rowObject.get(i)[3].get());
            timeLabel.setText(time);
            timeLabel.setId(id);
        }
    }

    private void reloadTabTeamWork() {
        generateTab(FXCollections.observableArrayList(manager.getTeamWorkData()), NodeTextEnum.TEAM_WORK_TITLE);
    }

    private void reloadTabPlayerStats() {
        generateTab(FXCollections.observableArrayList(manager.getPlayerStatsData()), NodeTextEnum.PLAYER_STATS);
    }

    private void reloadTabKitEquipment() {
        generateTab(FXCollections.observableArrayList(manager.getKitEquipmentData()), NodeTextEnum.KIT_EQUIPMENT);
    }

    private void reloadTabVehicleCategory() {
        generateTab(FXCollections.observableArrayList(manager.getVehicleCategoryData()), NodeTextEnum.VEHICLE_CATEGORY);
    }

    private void reloadKits() {
        generateTab(FXCollections.observableArrayList(manager.getKitsData()), NodeTextEnum.KITS);
    }

    private void generateTab(ObservableList<String[]> data, NodeTextEnum title) {
        ObservableList<ObjectProperty<Object>[]> rowObject = tableMap.get(title).getItems();
        for (int i = 0; i < rowObject.size(); i++) {
            for (int j = 0; j < tableMap.get(title).getColumns().size() - 1; j++) {
                ((Label) rowObject.get(i)[j + 1].get()).setText(data.get(i)[j]);
            }
        }
    }

    private void generateTab(TabPositionEnum position, NodeTextEnum title, NodeTextEnum[] columns, NodeTextEnum[] rows,
                             int fontSize) {
        createTable(position, title, columns, rows.length);
        tableMap.get(title).getColumns().get(0).setId(NamespaceEnum.FIRST_COLUMN_STYLE.getText());
        tableMap.get(title).setId(NamespaceEnum.CENTER_RIGHT_TABLE.getText());
        ObservableList<ObjectProperty<Object>[]> rowObject = tableMap.get(title).getItems();
        for (int i = 0; i < rows.length; i++) {
            Label label = generateNewLabel(rows[i]);
            if (fontSize > 0) {
                label.setFont(new Font(fontSize));
            }
            rowObject.get(i)[0] = new SimpleObjectProperty<>(label);
            for (int j = 1; j < columns.length; j++) {
                addLabelToRow(rowObject.get(i), j, fontSize);
            }
        }
    }

    private Label generateNewLabel(NodeTextEnum nodeTextEnum) {
        Label label = new Label();
        label.setText(nodeTextEnum.getText(label.textProperty()));
        return label;
    }

    private void addLabelToRow(ObjectProperty<Object>[] rowArray, int index, int labelTextSize) {
        Label label = new Label();
        if (labelTextSize > 0) {
            label.setFont(new Font(labelTextSize));
        }
        rowArray[index] = new SimpleObjectProperty<>(label);
    }

    private void addLabelToRow(ObjectProperty<Object>[] rowArray, int index) {
        addLabelToRow(rowArray, index, 0);
    }
}
