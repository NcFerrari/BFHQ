package lp.fe.javafx.bf2components.leaderboard;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import lp.be.business.dto.Player;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;

public class LeaderBoardPane extends BF2Component {

    private final Label categoryLabel;
    private final ComboBox<NodeTextEnum> sortCategoriesComboBox = new ComboBox<>();

    public LeaderBoardPane() {
        super(NodeTextEnum.TAB_MENU_LEADERBOARDS);
        categoryLabel = new Label();
        categoryLabel.setId(NamespaceEnum.TITLE_STYLE.getText());
        categoryLabel.setText(NodeTextEnum.CATEGORY_TITLE.getText(categoryLabel.textProperty()));
        sortCategoriesComboBox.setEditable(true);
        sortCategoriesComboBox.getEditor().setOnKeyPressed(evt -> {
            if (evt.getCode().equals(KeyCode.UP)) {
                sortCategoriesComboBox.getSelectionModel().selectPrevious();
            } else if (evt.getCode().equals(KeyCode.DOWN)) {
                sortCategoriesComboBox.getSelectionModel().selectNext();
            }
        });
        getLeftSidePart().getLeftTopPane().getChildren().addAll(categoryLabel, sortCategoriesComboBox);
    }

    @Override
    public void resize(Stage stage) {
        super.resize(stage);
        double oneThird = stage.getWidth() / 3;
        categoryLabel.setPrefWidth(oneThird);
        sortCategoriesComboBox.setPrefWidth(oneThird);
    }

    @Override
    public void reloadData() {
        getLeftSidePart().fillNameComboBox();
    }

    @Override
    public void rewriteData() {
        Player player = manager.getSelectedPlayer();
    }
}
