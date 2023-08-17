package lp.fe.javafx.bf2components;

import javafx.scene.control.Tab;
import lombok.Getter;
import lp.fe.enums.NodeTextEnum;

@Getter
public abstract class BF2Component {

    private final Tab tab;

    protected BF2Component(NodeTextEnum title) {
        tab = new Tab();
        tab.setText(title.getText(tab.textProperty()));
        tab.setClosable(false);
    }
}