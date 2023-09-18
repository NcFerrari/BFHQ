package lp.fe.javafx.bf2components.leaderboard;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import lombok.Getter;
import lp.be.business.dto.Player;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.BF2Component;

@Getter
public class LeaderBoardPane extends BF2Component {

    private final Label categoryLabel;
    private final ComboBox<String> sortCategoriesComboBox = new ComboBox<>();
    private final ObservableList<StringProperty> items = FXCollections.observableArrayList();

    public LeaderBoardPane() {
        super(NodeTextEnum.TAB_MENU_LEADERBOARDS);
        categoryLabel = new Label();
        categoryLabel.setId(NamespaceEnum.TITLE_STYLE.getText());
        categoryLabel.setText(NodeTextEnum.CATEGORY_TITLE.getText(categoryLabel.textProperty()));
        sortCategoriesComboBox.setEditable(true);
        items.addListener((ListChangeListener<? super StringProperty>) c -> {
            c.next();
            sortCategoriesComboBox.getItems().add(items.get(c.getFrom()).get());
        });
        sortCategoriesComboBox.getEditor().setOnKeyPressed(evt -> {
            if (evt.getCode().equals(KeyCode.UP)) {
                sortCategoriesComboBox.getSelectionModel().selectPrevious();
            } else if (evt.getCode().equals(KeyCode.DOWN)) {
                sortCategoriesComboBox.getSelectionModel().selectNext();
            }
        });
        getLeftSidePart().getLeftTopPane().getChildren().addAll(categoryLabel, sortCategoriesComboBox);
        initCategoryComboBox();
    }

    private void initCategoryComboBox() {
        addComboBoxItem(NodeTextEnum.SORT_BY_RANK);
        addComboBoxItem(NodeTextEnum.SORT_BY_KILLS);
        addComboBoxItem(NodeTextEnum.SORT_BY_DEATHS);
        addComboBoxItem(NodeTextEnum.SORT_BY_COUNT_OF_AWARDS);
        addComboBoxItem(NodeTextEnum.SORT_BY_TIME);
        addComboBoxItem(NodeTextEnum.SORT_BY_SCORE);
        addComboBoxItem(NodeTextEnum.SORT_BY_WINS);
        addComboBoxItem(NodeTextEnum.SORT_BY_LOSSES);
    }

    private void addComboBoxItem(NodeTextEnum nodeTextEnum) {
        StringProperty stringProperty = new SimpleStringProperty();
        stringProperty.setValue(nodeTextEnum.getText(stringProperty));
        items.add(stringProperty);
    }

    public void refreshComboBox() {
        sortCategoriesComboBox.getSelectionModel().clearSelection();
        sortCategoriesComboBox.getItems().clear();
        items.forEach(stringProperty -> sortCategoriesComboBox.getItems().add(stringProperty.get()));
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
