package lp.fe.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lp.Manager;
import lp.fe.enums.LangEnum;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;

import java.awt.Toolkit;
import java.util.Objects;

public class MainApp extends Application {

    private final double width = 19 * Toolkit.getDefaultToolkit().getScreenSize().width / 20.0;
    private final double height = 17 * Toolkit.getDefaultToolkit().getScreenSize().height / 20.0;
    private final Manager manager = Manager.getInstance();

    private TabbedPane tabbedPane;

    @Override
    public void start(Stage stage) {
        stage.setTitle(NodeTextEnum.APPLICATION_TITLE.getText(stage.titleProperty()));
        VBox mainPane = new VBox();
        Scene scene = new Scene(mainPane, width, height);
        loadCssStyles(scene);
        temporaryMethodToRemove(scene);
        stage.setScene(scene);
        stage.show();

        setListener(stage);

        new UpperPane(mainPane);
        tabbedPane = new TabbedPane(mainPane);

        resize(stage);
    }

    private void setListener(Stage stage) {
        stage.widthProperty().addListener((observable, oldValue, newValue) -> resize(stage));
        stage.heightProperty().addListener((observable, oldValue, newValue) -> resize(stage));
        stage.maximizedProperty().addListener((observable, oldValue, newValue) -> resize(stage));
        stage.setOnCloseRequest(this::showClosingDialog);
    }

    private void showClosingDialog(WindowEvent evt) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, NamespaceEnum.EMPTY_STRING.getText());
        alert.setTitle(NodeTextEnum.DIALOG_TEXT_WINDOW_CLOSE_TITLE.getText(alert.titleProperty()));
        alert.setHeaderText(NamespaceEnum.EMPTY_STRING.getText());
        alert.setContentText(NodeTextEnum.DIALOG_TEXT_WINDOW_CLOSE.getText(alert.contentTextProperty()));
        alert.getButtonTypes().clear();

        ButtonType yesButton;
        ButtonType noButton;
        if (manager.getLanguage().equals(LangEnum.ENG)) {
            yesButton = new ButtonType(NamespaceEnum.DIALOG_BUTTON_YES_EN.getText());
            noButton = new ButtonType(NamespaceEnum.DIALOG_BUTTON_NO_EN.getText());
        } else {
            yesButton = new ButtonType(NamespaceEnum.DIALOG_BUTTON_YES_CZ.getText());
            noButton = new ButtonType(NamespaceEnum.DIALOG_BUTTON_NO_CZ.getText());
        }
        alert.getButtonTypes().addAll(yesButton, noButton);
        alert.showAndWait();

        if (alert.getResult() == noButton) {
            evt.consume();
        }
    }

    private void resize(Stage stage) {
        tabbedPane.resize(stage);
    }

    private void loadCssStyles(Scene scene) {
        addCSS(scene, NamespaceEnum.PANES_STYLE);
        addCSS(scene, NamespaceEnum.BUTTONS_STYLE);
        addCSS(scene, NamespaceEnum.TAB_PANE_STYLE);
        addCSS(scene, NamespaceEnum.RIGHT_SIDE_STYLE);
    }

    private void temporaryMethodToRemove(Scene scene) {
        Thread t = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    scene.getStylesheets().remove(NamespaceEnum.TEMPORARY_CSS_FILE.getText());
                    scene.getStylesheets().add(NamespaceEnum.TEMPORARY_CSS_FILE.getText());
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private void addCSS(Scene scene, NamespaceEnum namespaceEnum) {
        scene.getStylesheets().add(Objects.requireNonNull(MainApp.class.getClassLoader()
                .getResource(namespaceEnum.getText())).toExternalForm());
    }
}
