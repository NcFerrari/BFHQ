package lp.fe.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.fe.enums.FXTextEnum;
import lp.fe.enums.LangEnum;

import java.awt.Toolkit;

public class MainApp extends Application {

    private final double width = 19 * Toolkit.getDefaultToolkit().getScreenSize().width / 20.0;
    private final double height = 17 * Toolkit.getDefaultToolkit().getScreenSize().height / 20.0;
    private final Manager manager = Manager.getInstance();

    @Override
    public void start(Stage stage) {
        manager.setLanguage(LangEnum.CZE);
        stage.setTitle(FXTextEnum.APPLICATION_TITLE.getText(stage.titleProperty()));
        VBox mainPane = new VBox();
        Scene scene = new Scene(mainPane, width, height);
        stage.setScene(scene);
        stage.show();
    }
}
