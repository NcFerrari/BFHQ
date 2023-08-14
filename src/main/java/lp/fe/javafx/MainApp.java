package lp.fe.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.fe.enums.LangEnum;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextsEnum;

import java.awt.Toolkit;
import java.util.Objects;

public class MainApp extends Application {

    private final double width = 19 * Toolkit.getDefaultToolkit().getScreenSize().width / 20.0;
    private final double height = 17 * Toolkit.getDefaultToolkit().getScreenSize().height / 20.0;
    private final Manager manager = Manager.getInstance();

    @Override
    public void start(Stage stage) {
        manager.setLanguage(LangEnum.CZE);
        stage.setTitle(NodeTextsEnum.APPLICATION_TITLE.getText(stage.titleProperty()));
        VBox mainPane = new VBox();
        mainPane.setId("box");
        Scene scene = new Scene(mainPane, width, height);
        loadCssStyles(scene);
        temporaryMethodToRemove(scene);
        stage.setScene(scene);
        stage.show();
    }

    private void loadCssStyles(Scene scene) {
        addCSS(scene);
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

    private void addCSS(Scene scene) {
        scene.getStylesheets().add(Objects.requireNonNull(MainApp.class.getClassLoader()
                .getResource(NamespaceEnum.BASIC_STYLE.getText())).toExternalForm());
    }
}
