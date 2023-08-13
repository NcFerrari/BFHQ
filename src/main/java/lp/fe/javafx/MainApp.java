package lp.fe.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.Toolkit;

public class MainApp extends Application {

    private static final double WIDTH = 19 * Toolkit.getDefaultToolkit().getScreenSize().width / 20.0;
    private static final double HEIGHT = 17 * Toolkit.getDefaultToolkit().getScreenSize().height / 20.0;

    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }
}
