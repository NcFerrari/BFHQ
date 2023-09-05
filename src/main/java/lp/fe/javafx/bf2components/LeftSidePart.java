package lp.fe.javafx.bf2components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lp.Manager;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.awards.AwardsPane;
import org.jetbrains.annotations.NotNull;

@Getter
public class LeftSidePart {

    private final Manager manager = Manager.getInstance();
    private final VBox leftPane = new VBox();
    private final Label playerNameTitle = new Label();
    private final ComboBox<String> nameComboBox = new ComboBox<>();

    public LeftSidePart() {
        playerNameTitle.setText(NodeTextEnum.PLAYER_NAME_TITLE.getText(playerNameTitle.textProperty()));
        playerNameTitle.setId(NamespaceEnum.TITLE_STYLE.getText());
        leftPane.getChildren().add(playerNameTitle);
        leftPane.setId(NamespaceEnum.LEFT_SIDE_PART_STYLE.getText());
        leftPane.getChildren().add(nameComboBox);
        nameComboBox.setId(NamespaceEnum.NAME_COMBO_BOX_STYLE.getText());
        nameComboBox.setEditable(true);
        nameComboBox.getEditor().setOnKeyPressed(evt -> {
            if (evt.getCode().equals(KeyCode.UP)) {
                nameComboBox.getSelectionModel().selectPrevious();
            } else if (evt.getCode().equals(KeyCode.DOWN)) {
                nameComboBox.getSelectionModel().selectNext();
            }
        });
        nameComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            manager.setSelectedPlayer(newValue);
            manager.getReloadableList().forEach(BF2Component::rewriteData);
            manager.getReloadableList()
                    .stream()
                    .filter(AwardsPane.class::isInstance)
                    .forEach(bf2Component -> ((AwardsPane) bf2Component).clearBigImage());
        });
        fillNameComboBox();
    }

    public void fillNameComboBox() {
        String name = null;
        if (manager.getSelectedPlayer() != null) {
            name = manager.getSelectedPlayer().getName();
        }
        nameComboBox.getItems().clear();
        nameComboBox.getItems().addAll(manager.getPlayerNames());
        if (nameComboBox.getItems().contains(name)) {
            nameComboBox.getSelectionModel().select(name);
        }
    }

    public void resize(@NotNull Stage stage) {
        double oneThirdWidth = stage.getWidth() / 3;
        getLeftPane().setPrefWidth(oneThirdWidth);
        getPlayerNameTitle().setMinWidth(oneThirdWidth);
        getNameComboBox().setPrefWidth(oneThirdWidth);
    }
}
