package lp.fe.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lp.Manager;
import lp.fe.enums.LangEnum;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;

@Getter
public class UpperPane {

    private final Manager manager = Manager.getInstance();
    private final ObservableList<BF2Component> reloadableList = FXCollections.observableArrayList();
    private final FlowPane topPane;

    public UpperPane(VBox mainPane) {
        topPane = new FlowPane();
        topPane.setAlignment(Pos.CENTER_RIGHT);
        mainPane.getChildren().add(topPane);
        addNodes();
    }

    private void addNodes() {
        ToggleButton toolkitToggleButton = new ToggleButton();
        toolkitToggleButton.setText(NodeTextEnum.TOOLKIT_TEXT.getText(toolkitToggleButton.textProperty()));
        toolkitToggleButton.setOnAction(evt -> manager.switchShowingTooltips());
        topPane.getChildren().add(toolkitToggleButton);

        Button updateButton = new Button();
        updateButton.setText(NodeTextEnum.UPDATE_DATA_BUTTON.getText(updateButton.textProperty()));
        updateButton.setOnAction(evt -> {
            manager.loadDataFromDB();
            manager.getReloadableList().forEach(BF2Component::reloadData);
        });
        topPane.getChildren().add(updateButton);

        ComboBox<LangEnum> langEnumComboBox = new ComboBox<>();
        langEnumComboBox.getItems().addAll(LangEnum.CZE, LangEnum.ENG);
        langEnumComboBox.getSelectionModel().select(manager.getLanguage());
        langEnumComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            manager.setLanguage(newValue);
            manager.refreshLeaderBoardComboBox();
        });
        langEnumComboBox.setId(NamespaceEnum.LANG_COMBO_BOX_STYLE.getText());
        topPane.getChildren().add(langEnumComboBox);
    }
}

