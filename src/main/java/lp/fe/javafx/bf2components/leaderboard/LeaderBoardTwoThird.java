package lp.fe.javafx.bf2components.leaderboard;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lp.Manager;
import lp.be.business.dto.KillsForTable;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.RightSidePart;

public class LeaderBoardTwoThird {

    private final TableView<KillsForTable> crossTable = new TableView<>();
    private final Manager manager = Manager.getInstance();
    private final Button showCrossTableButton = new Button();

    public LeaderBoardTwoThird(RightSidePart rightSidePart) {
        showCrossTableButton.setText(NodeTextEnum.SHOW_CROSS_TABLE.getText(showCrossTableButton.textProperty()));
        rightSidePart.getRightPane().setCenter(showCrossTableButton);
        showCrossTableButton.setOnAction(evt -> rightSidePart.getRightPane().setCenter(crossTable));
        crossTable.setId(NamespaceEnum.CROSS_TABLE_STYLE.getText());
        loadTableData(rightSidePart);
    }

    private void loadTableData(RightSidePart rightSidePart) {
        crossTable.getItems().clear();
        crossTable.getColumns().clear();
        ObservableList<KillsForTable> data = FXCollections.observableArrayList(manager.getKillsForAllPlayers());

        TableColumn<KillsForTable, String> nameColumn = new TableColumn<>();
        nameColumn.setText(NodeTextEnum.NAME_TITLE.getText(nameColumn.textProperty()));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(NamespaceEnum.NAME.getText()));
        nameColumn.setStyle(String.format(NamespaceEnum.FONT_SIZE_STYLE.getText(), 12f));
        nameColumn.setResizable(false);
        nameColumn.setSortable(false);
        nameColumn.setReorderable(false);
        nameColumn.setId(NamespaceEnum.FIRST_COLUMN_STYLE.getText());
        Button closeButton = new Button(NamespaceEnum.X_MARK.getText());
        closeButton.setOnAction(evt -> rightSidePart.getRightPane().setCenter(showCrossTableButton));
        nameColumn.setGraphic(closeButton);
        crossTable.getColumns().add(nameColumn);

        for (KillsForTable datum : data) {
            TableColumn<KillsForTable, Object> column = new TableColumn<>(datum.getName());
            column.setCellValueFactory(cellData -> {
                if (column.getText().equals(cellData.getValue().getName())) {
                    Label xLabel = new Label(NamespaceEnum.X_MARK.getText());
                    xLabel.setId(NamespaceEnum.X_MARK_STYLE.getText());
                    return new SimpleObjectProperty<>(xLabel);
                }
                if (cellData.getValue().getEnemyKills().containsKey(column.getText())) {
                    return new SimpleObjectProperty<>(cellData.getValue().getEnemyKills().get(column.getText()));
                }
                return new SimpleObjectProperty<>(0);
            });
            column.setStyle(String.format(NamespaceEnum.FONT_SIZE_STYLE.getText(), 12f));
            column.setResizable(false);
            column.setSortable(false);
            column.setReorderable(false);
            crossTable.getColumns().add(column);
        }
        crossTable.setItems(data);
    }
}
