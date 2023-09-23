package lp.fe.javafx.bf2components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private final HBox mainPane = new HBox();
    private final VBox leftTopPane = new VBox();
    private final VBox leftBottomPane = new VBox();
    private final Label playerNameTitle = new Label();
    private final ComboBox<String> nameComboBox = new ComboBox<>();
    private final ScrollPane scrollPane;

    public LeftSidePart() {
        scrollPane = new ScrollPane(leftBottomPane);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainPane.getChildren().add(new BorderPane(scrollPane, leftTopPane, null, null, null));
        mainPane.setId(NamespaceEnum.LEFT_PANE_MAIN_PANE_STYLE.getText());
        playerNameTitle.setText(NodeTextEnum.PLAYER_NAME_TITLE.getText(playerNameTitle.textProperty()));
        playerNameTitle.setId(NamespaceEnum.TITLE_STYLE.getText());
        leftTopPane.setId(NamespaceEnum.LEFT_SIDE_PART_STYLE.getText());
        leftBottomPane.setId(NamespaceEnum.LEFT_SIDE_PART_STYLE.getText());
        leftTopPane.getChildren().add(playerNameTitle);
        leftTopPane.getChildren().add(nameComboBox);
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
            manager.getReloadableList()
                    .stream()
                    .filter(AwardsPane.class::isInstance)
                    .forEach(bf2Component -> ((AwardsPane) bf2Component).resetAwardLeftPart());
            manager.getReloadableList().forEach(BF2Component::rewriteData);
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
        getPlayerNameTitle().setMinWidth(oneThirdWidth);
        getNameComboBox().setPrefWidth(oneThirdWidth);
    }
}
