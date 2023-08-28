package lp.fe.javafx.bf2components;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Getter;
import lp.Manager;
import lp.fe.enums.NodeTextEnum;
import org.jetbrains.annotations.NotNull;

@Getter
public abstract class BF2Component {

    protected final Manager manager = Manager.getInstance();
    private final HBox contentPane = new HBox();
    private final LeftSidePart leftSidePart = new LeftSidePart();
    private final RightSidePart rightSidePart = new RightSidePart();
    private final Tab tab = new Tab();

    protected BF2Component(@NotNull NodeTextEnum title) {
        manager.registerReloadableComponent(this);
        tab.setText(title.getText(tab.textProperty()));
        tab.setClosable(false);
        tab.setContent(contentPane);
        ScrollPane scrollPane = new ScrollPane(getLeftSidePart().getLeftPane());
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        contentPane.getChildren().add(scrollPane);
        contentPane.getChildren().add(getRightSidePart().getRightPane());
    }

    public void resize(@NotNull Stage stage) {
        contentPane.setPrefHeight(stage.getHeight());
        leftSidePart.resize(stage);
        rightSidePart.resize(stage);
    }

    public abstract void reloadData();

    public abstract void rewriteData();
}