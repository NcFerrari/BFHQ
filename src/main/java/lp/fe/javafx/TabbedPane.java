package lp.fe.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import lp.fe.javafx.bf2components.BF2Component;
import lp.fe.javafx.bf2components.awards.Awards;
import lp.fe.javafx.bf2components.stats.Stats;

public class TabbedPane {

    private final ObservableList<BF2Component> bf2Components = FXCollections.observableArrayList();
    private final TabPane tabPane;

    public TabbedPane(VBox mainPane) {
        tabPane = new TabPane();
        tabPane.setOnKeyPressed(this::keyList);
        mainPane.getChildren().add(tabPane);

        bf2Components.addListener((ListChangeListener<? super BF2Component>) observable -> {
            observable.next();
            tabPane.getTabs().add(bf2Components.get(observable.getFrom()).getTab());
        });
        fillTabs();
    }

    public void resize(VBox mainPane) {
        tabPane.setTabMinWidth(mainPane.getWidth() / 5.824);
        tabPane.setTabMinHeight(mainPane.getHeight() / 16.98);
        tabPane.setStyle("-fx-font-size: " + mainPane.getHeight() / 32.65);
        bf2Components.forEach(bf2Component -> bf2Component.resize(mainPane.getHeight()));
    }

    private void fillTabs() {
        bf2Components.add(new Stats());
        bf2Components.add(new Awards());
    }

    private void keyList(KeyEvent evt) {
        if (evt.getCode() == KeyCode.UP) {
            if (tabPane.getSelectionModel().isSelected(tabPane.getTabs().size() - 1)) {
                tabPane.getSelectionModel().selectFirst();
            } else {
                tabPane.getSelectionModel().select(tabPane.getSelectionModel().getSelectedIndex() + 1);
            }
        } else if (evt.getCode() == KeyCode.DOWN) {
            if (tabPane.getSelectionModel().isSelected(0)) {
                tabPane.getSelectionModel().selectLast();
            } else {
                tabPane.getSelectionModel().select(tabPane.getSelectionModel().getSelectedIndex() - 1);
            }
        }
    }
}
