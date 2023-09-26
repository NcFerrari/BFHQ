package lp.fe.javafx.bf2components;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.Getter;
import lp.fe.enums.NamespaceEnum;

@Getter
public class RightSidePart {

    private final BorderPane rightPane = new BorderPane();

    public RightSidePart() {
        rightPane.setId(NamespaceEnum.RIGHT_SIDE_PART_STYLE.getText());
    }

    public void resize(Stage stage) {
        double oneThirdWidth = 2 * stage.getWidth() / 3 - 20;
        getRightPane().setPrefWidth(oneThirdWidth);
    }
}
