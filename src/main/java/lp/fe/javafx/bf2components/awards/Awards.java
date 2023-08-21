package lp.fe.javafx.bf2components.awards;

import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;

public class Awards extends BF2Component {

    public Awards() {
        super(NodeTextEnum.TAB_MENU_AWARDS);
    }

    @Override
    public void reloadData() {
        getLeftSidePart().fillNameComboBox();
    }
}
