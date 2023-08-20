package lp.fe.javafx.bf2components;

import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import lp.fe.enums.NodeTextEnum;

@Getter
public abstract class BF2Component {

    private final Tab tab;
    private final HBox contentPane = new HBox();
    private final LeftSidePart leftSidePart = new LeftSidePart();

    protected BF2Component(NodeTextEnum title) {
        tab = new Tab();
        tab.setText(title.getText(tab.textProperty()));
        tab.setClosable(false);
        tab.setContent(contentPane);
        contentPane.getChildren().add(getLeftSidePart().getLeftPane());
    }

    public void resize(Stage stage) {
        double oneThirdWidth = stage.getWidth() / 3;
        contentPane.setPrefHeight(stage.getHeight());
        leftSidePart.getLeftPane().setPrefWidth(oneThirdWidth);
        leftSidePart.getPlayerNameTitle().setMinWidth(oneThirdWidth);
        leftSidePart.getNameComboBox().setPrefWidth(oneThirdWidth);
    }
}