package lp.fe.javafx.bf2components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;

@Getter
public class LeftSidePart {

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
    }
}
