package lp.fe.javafx.bf2components.mapinfo;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;

public class MapInfo extends BF2Component {

    private final ListView<Label> mapList = new ListView<>();

    public MapInfo() {
        super(NodeTextEnum.TAB_MENU_MAP_INFO);
        NodeTextEnum.getMaps().forEach(map -> {
            Label label = new Label();
            label.setText(map.getText(label.textProperty()));
            mapList.getItems().add(label);
        });
        getLeftSidePart().getLeftBottomPane().getChildren().add(mapList);
    }
}
