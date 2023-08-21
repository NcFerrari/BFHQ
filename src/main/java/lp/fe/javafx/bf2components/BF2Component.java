package lp.fe.javafx.bf2components;

import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import lp.fe.enums.NodeTextEnum;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class BF2Component extends ReloadableComponent {

    private final HBox contentPane = new HBox();
    private final LeftSidePart leftSidePart = new LeftSidePart();
    private final Tab tab = new Tab();

    protected BF2Component(@NotNull NodeTextEnum title) {
        tab.setText(title.getText(tab.textProperty()));
        tab.setClosable(false);
        tab.setContent(contentPane);
        contentPane.getChildren().add(getLeftSidePart().getLeftPane());
    }

    public void resize(@NotNull Stage stage) {
        contentPane.setPrefHeight(stage.getHeight());
        leftSidePart.resize(stage);
    }
}