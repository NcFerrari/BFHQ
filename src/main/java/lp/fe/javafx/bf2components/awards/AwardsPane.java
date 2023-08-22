package lp.fe.javafx.bf2components.awards;

import javafx.scene.control.Button;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;

public class AwardsPane extends BF2Component {

    public AwardsPane() {
        super(NodeTextEnum.TAB_MENU_AWARDS);
    }

    @Override
    public void reloadData() {
        getLeftSidePart().fillNameComboBox();
    }

    @Override
    public void rewriteData() {
        getLeftSidePart().getLeftPane().getChildren().clear();
        Button button = new Button();
        button.setText(NodeTextEnum.WINS.getText(button.textProperty()));
        getLeftSidePart().getLeftPane().getChildren().add(button);
    }
}
