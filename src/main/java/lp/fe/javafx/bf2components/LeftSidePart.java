package lp.fe.javafx.bf2components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lp.Manager;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;

@Getter
public class LeftSidePart {

    private static final long COMBO_BOX_COUNTER_TIME = 2000;
    private final VBox leftPane = new VBox();
    private final Label playerNameTitle = new Label();
    private final ComboBox<String> nameComboBox = new ComboBox<>();
    private final Manager manager = Manager.getInstance();

    private long timeCount;
    private String letters = NamespaceEnum.EMPTY_STRING.getText();

    public LeftSidePart() {
        playerNameTitle.setText(NodeTextEnum.PLAYER_NAME_TITLE.getText(playerNameTitle.textProperty()));
        playerNameTitle.setId(NamespaceEnum.TITLE_STYLE.getText());
        leftPane.getChildren().add(playerNameTitle);
        leftPane.setId(NamespaceEnum.LEFT_SIDE_PART_STYLE.getText());
        leftPane.getChildren().add(nameComboBox);
        nameComboBox.setId(NamespaceEnum.NAME_COMBO_BOX_STYLE.getText());
        fillNameComboBox();
    }

    private void fillNameComboBox() {
        nameComboBox.getItems().addAll(manager.getPlayerNames());
        nameComboBox.setOnKeyPressed(evt -> {
            if (timeCount < (System.currentTimeMillis() - COMBO_BOX_COUNTER_TIME)) {
                timeCount = System.currentTimeMillis();
                letters = NamespaceEnum.EMPTY_STRING.getText();
            }
            letters += evt.getText();
            for (String item : nameComboBox.getItems()) {
                if (item.toLowerCase().trim().startsWith(letters.toLowerCase().trim())) {
                    nameComboBox.getSelectionModel().select(item);
                    break;
                }
            }
        });
        nameComboBox.valueProperty().addListener((observable, oldValue, newValue) ->
                manager.setSelectedPlayer(newValue));
    }
}
