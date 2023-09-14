package lp.fe.javafx.bf2components.awards;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.LeftSidePart;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class AwardOneThird {

    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private final StackPane paneForBigImage = new StackPane();
    private final BF2Image bigImage = new BF2Image();
    private final ObservableMap<NamespaceEnum, Label> labelMap = FXCollections.observableMap(new HashMap<>());
    private final ObservableMap<NodeTextEnum, CheckBox> checkboxMap = FXCollections.observableMap(new HashMap<>());
    private final LeftSidePart leftSidePart;
    private boolean enableLabels = true;
    private String key;
    private BF2Image bf2Image;

    public AwardOneThird(@NotNull LeftSidePart leftSidePart) {
        this.leftSidePart = leftSidePart;
        paneForBigImage.setId(NamespaceEnum.BIG_IMAGE_STYLE.getText());
        paneForBigImage.getChildren().add(bigImage.getImageView());
        leftSidePart.getLeftTopPane().getChildren().add(new Pane(paneForBigImage));
        initBigImagePart();
        initCheckList();
    }

    private void initBigImagePart() {
        BorderPane northPane = new BorderPane();
        northPane.setLeft(addLabel(NamespaceEnum.TITLE_TEXT, NodeTextEnum.EMPTY_STRING));
        northPane.setRight(addLabel(NamespaceEnum.STARS_COUNTER, null));

        BorderPane southTopBorder = new BorderPane();
        southTopBorder.setLeft(addLabel(NamespaceEnum.LAST_EARNED, NodeTextEnum.LAST_AWARDED));
        southTopBorder.setRight(addLabel(NamespaceEnum.LAST_EARNED_DATE, null));

        BorderPane southBottomBorder = new BorderPane();
        southBottomBorder.setLeft(addLabel(NamespaceEnum.FIRST_EARNED, NodeTextEnum.FIRST_AWARDED));
        southBottomBorder.setRight(addLabel(NamespaceEnum.FIRST_EARNED_DATE, null));

        BorderPane imageBorderPane = new BorderPane();
        imageBorderPane.setTop(northPane);
        VBox vBox = new VBox();
        vBox.getChildren().add(southTopBorder);
        vBox.getChildren().add(southBottomBorder);
        imageBorderPane.setBottom(vBox);
        paneForBigImage.getChildren().add(imageBorderPane);
    }

    private void initCheckList() {
        leftSidePart.getScrollPane().setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        NodeTextEnum.getAwards().forEach(this::addCheckBox);
    }

    private void addCheckBox(NodeTextEnum nodeTextEnum) {
        CheckBox checkbox = new CheckBox();
        checkbox.setText(nodeTextEnum.getText(checkbox.textProperty()));
        if (checkboxMap.size() % 2 == 0) {
            checkbox.setId(NamespaceEnum.CHECK_BOX_STYLE.getText());
        } else {
            checkbox.setId(NamespaceEnum.CHECK_BOX_LIGHT_STYLE.getText());
        }
        leftSidePart.getLeftBottomPane().getChildren().add(checkbox);
        Tooltip tooltip = new Tooltip();
        tooltip.setText(NodeTextEnum.valueOf(
                NamespaceEnum.TOOLTIP.getText() + nodeTextEnum.name()).getText(tooltip.textProperty()));
        Tooltip.install(checkbox, tooltip);
        checkboxMap.put(nodeTextEnum, checkbox);
    }

    private @NotNull Label addLabel(NamespaceEnum key, NodeTextEnum textEnum) {
        Label label = new Label();
        label.setId(NamespaceEnum.BIG_IMAGE_LABEL_STYLE.getText());
        label.setVisible(false);
        if (textEnum != null) {
            label.setText(textEnum.getText(label.textProperty()));
        }
        labelMap.put(key, label);
        return label;
    }

    public void rewriteData() {
        Manager.getInstance().getAwardsForSelectedPlayer(0).forEach(bf2Image -> {
            checkboxMap.get(bf2Image.getNodeTextEnum()).setSelected(true);
        });
    }

    public void resize(@NotNull Stage stage) {
        double oneThird = stage.getWidth() / 3;
        double oneFifth = stage.getWidth() / 5;
        paneForBigImage.setPrefSize(oneFifth, oneFifth);
        paneForBigImage.setLayoutX(oneThird / 2 - oneFifth / 2);
        bigImage.setImageViewSize(oneFifth);
        enableLabels = stage.getWidth() > 1200;
        showBigImage(key, bf2Image);
        checkboxMap.values().forEach(checkBox -> checkBox.setPrefWidth(oneThird));
    }

    public void showBigImage(String key, BF2Image bf2Image) {
        clearBigImage();
        if (key == null) {
            return;
        }
        this.key = key;
        this.bf2Image = bf2Image;
        if (key.contains(NodeTextEnum.RIBBON.name())) {
            bigImage.getImageView().setImage(pictureService.getAwardBF2Image(bf2Image.getImageId(), 1).getImage());
        } else {
            bigImage.getImageView().setImage(bf2Image.getImage());
        }

        labelMap.get(NamespaceEnum.TITLE_TEXT).setVisible(enableLabels);
        NodeTextEnum.getComponentsForTranslate().replace(labelMap.get(NamespaceEnum.TITLE_TEXT).textProperty(),
                bf2Image.getNodeTextEnum());

        if (bf2Image.getNodeTextEnum().equals(NodeTextEnum.MEDAL_2051902) ||
                bf2Image.getNodeTextEnum().equals(NodeTextEnum.MEDAL_2051907) ||
                bf2Image.getNodeTextEnum().equals(NodeTextEnum.MEDAL_2051919)) {
            labelMap.get(NamespaceEnum.STARS_COUNTER).setVisible(enableLabels);
            labelMap.get(NamespaceEnum.STARS_COUNTER).setText(NamespaceEnum.X_MARK.getText() + bf2Image.getLevel());

            labelMap.get(NamespaceEnum.FIRST_EARNED).setVisible(enableLabels);
            labelMap.get(NamespaceEnum.FIRST_EARNED_DATE).setVisible(enableLabels);
            labelMap.get(NamespaceEnum.FIRST_EARNED_DATE).setText(bf2Image.getFirstEarned()
                    .format(DateTimeFormatter.ofPattern(NamespaceEnum.DATE_FORMAT.getText())));
        }

        labelMap.get(NamespaceEnum.LAST_EARNED).setVisible(enableLabels);
        labelMap.get(NamespaceEnum.LAST_EARNED_DATE).setVisible(enableLabels);
        labelMap.get(NamespaceEnum.LAST_EARNED_DATE).setText(bf2Image.getLastEarned()
                .format(DateTimeFormatter.ofPattern(NamespaceEnum.DATE_FORMAT.getText())));
    }

    public void clearBigImage() {
        bigImage.getImageView().setImage(null);
        labelMap.values().forEach(label -> label.setVisible(false));
        key = null;
        bf2Image = null;
        checkboxMap.values().forEach(checkBox -> checkBox.setSelected(false));
    }
}
