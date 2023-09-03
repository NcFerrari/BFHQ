package lp.fe.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.fe.enums.NamespaceEnum;
import lp.fe.javafx.bf2components.BF2Component;
import lp.fe.javafx.bf2components.awards.AwardsPane;
import lp.fe.javafx.bf2components.stats.StatsPane;

public class TabbedPane {

    private final ObservableList<BF2Component> bf2Components = FXCollections.observableArrayList();
    private final TabPane tabPane;

    public TabbedPane(VBox mainPane) {
        tabPane = new TabPane();
        tabPane.setOnKeyPressed(this::keyList);
        tabPane.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) ->
                bf2Components.get(newValue.intValue()).reloadData());
        mainPane.getChildren().add(tabPane);

        bf2Components.addListener((ListChangeListener<? super BF2Component>) observable -> {
            observable.next();
            tabPane.getTabs().add(bf2Components.get(observable.getFrom()).getTab());
        });
        fillTabs();
        tabPane.getSelectionModel().selectLast();
    }

    public void resize(Stage stage) {
        tabPane.setTabMinWidth(stage.getWidth() / 5.824);
        tabPane.setTabMinHeight(stage.getHeight() / 16.98);
        tabPane.setStyle(String.format(NamespaceEnum.FONT_SIZE_STYLE.getText(), stage.getHeight() / 32.65));
        bf2Components.forEach(bf2Component -> bf2Component.resize(stage));
    }

    private void fillTabs() {
        bf2Components.add(new StatsPane());
        bf2Components.add(new AwardsPane());
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
