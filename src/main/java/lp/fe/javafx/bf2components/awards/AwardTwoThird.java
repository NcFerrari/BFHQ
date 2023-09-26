package lp.fe.javafx.bf2components.awards;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.RightSidePart;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class AwardTwoThird {

    private final VBox content = new VBox();
    private final ObservableList<Label> titles = FXCollections.observableArrayList();
    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private final ObservableMap<NodeTextEnum, GridPane> grids = FXCollections.observableMap(new HashMap<>());
    private final ObservableMap<String, BF2Image> bf2Images = FXCollections.observableMap(new HashMap<>());
    private final ObservableList<StackPane> stackPanes = FXCollections.observableArrayList();
    private final Manager manager = Manager.getInstance();

    public AwardTwoThird(RightSidePart rightSidePart, AwardsPane awardsPane) {
        rightSidePart.getRightPane().setCenter(content);
        Integer[] medals = {2020419, 2020719, 2020903, 2020913, 2020919, 2021322, 2021403, 2021613, 2051902,
                2051907, 2051919, 2190303, 2190308, 2190309, 2190318, 2190703, 2191319, 2191608, 3270519};
        Integer[] badges = {1031105, 1031109, 1031113, 1031115, 1031119, 1031120, 1031121, 1031406, 1031619,
                1031923, 1032415, 1190304, 1190507, 1190601, 1191819, 1220104, 1220118, 1220122, 1220803, 1222016};
        addPane(NodeTextEnum.LAST_AWARD, new Integer[6], 6);
        addPane(NodeTextEnum.MEDAL, medals, 10);
        addPane(NodeTextEnum.BADGE, badges, 10);
        addPane(NodeTextEnum.RIBBON, new Integer[18], 8);
        resetImages();
        bf2Images.forEach((key, bf2Image) -> bf2Image.getImageView().setOnMousePressed(event -> {
            if (bf2Image.isEnable()) {
                awardsPane.showBigImage(key, bf2Image);
            }
        }));
    }

    public void resize(Stage stage) {
        double twoThirdWidth = 2 * stage.getWidth() / 3 - 25;
        content.setPrefWidth(twoThirdWidth);
        titles.forEach(title -> title.setMinWidth(twoThirdWidth));

        bf2Images.forEach((key, bf2Image) -> {
            if (key.contains(NodeTextEnum.LAST_AWARD.name())) {
                bf2Image.setImageViewSize(twoThirdWidth / (grids.get(NodeTextEnum.LAST_AWARD).getColumnCount() + 2));
            } else if (key.contains(NodeTextEnum.RIBBON.name())) {
                double twoThirdWidthPart = twoThirdWidth / grids.get(NodeTextEnum.RIBBON).getColumnCount();
                bf2Image.setImageViewSize(twoThirdWidthPart, twoThirdWidthPart / 3);
            } else {
                bf2Image.setImageViewSize(twoThirdWidth / (grids.get(NodeTextEnum.MEDAL).getColumnCount() + 5.5));
            }
        });
        grids.forEach((nodeTextEnum, gridPane) -> {
            if (nodeTextEnum != NodeTextEnum.RIBBON) {
                gridPane.setHgap(stage.getWidth() / 48);
            }
        });
        stackPanes.forEach(stackPane -> stackPane.getChildren().get(1).setVisible(stage.getWidth() > 1200));
    }

    public void rewriteData() {
        resetImages();
        int lastAwardNumber = 0;
        int ribbonNumber = 0;
        for (BF2Image bf2Image : manager.getAwardsForSelectedPlayer(0)) {
            BF2Image localBF2Image;
            if (lastAwardNumber < stackPanes.size()) {
                localBF2Image = bf2Images.get(NodeTextEnum.LAST_AWARD.name() + lastAwardNumber);
                localBF2Image.updateData(bf2Image, manager.isShowTooltip());
                localBF2Image.setImageId(bf2Image.getImageId());
                localBF2Image.setNodeTextEnum(bf2Image.getNodeTextEnum());
                localBF2Image.setLevel(bf2Image.getLevel());
                localBF2Image.setFirstEarned(bf2Image.getFirstEarned());
                localBF2Image.setLastEarned(bf2Image.getLastEarned());
                ((Label) stackPanes.get(lastAwardNumber).getChildren().get(1)).setText(bf2Image.getLastEarned()
                        .format(DateTimeFormatter.ofPattern(NamespaceEnum.CZE_DATE_FORMAT.getText())));
                localBF2Image.setImageId(bf2Image.getImageId());
                lastAwardNumber++;
            }
            if (bf2Image.getNodeTextEnum().name().contains(NodeTextEnum.MEDAL.name())) {
                pictureService.addColor(bf2Images.get(bf2Image.getNodeTextEnum().name()));
                localBF2Image = bf2Images.get(bf2Image.getNodeTextEnum().name());
                localBF2Image.updateData(bf2Image, manager.isShowTooltip());
                localBF2Image.setLevel(bf2Image.getLevel());
                localBF2Image.setFirstEarned(bf2Image.getFirstEarned());
                localBF2Image.setLastEarned(bf2Image.getLastEarned());
            } else if (bf2Image.getNodeTextEnum().name().contains(NodeTextEnum.BADGE.name())) {
                String key = bf2Image.getNodeTextEnum().name().replaceFirst(NamespaceEnum.NUMBER_REGEX.getText(),
                        NamespaceEnum.ONE.getText());
                pictureService.addColor(bf2Images.get(key));
                localBF2Image = bf2Images.get(key);
                localBF2Image.setLevel(bf2Image.getLevel());
                localBF2Image.setFirstEarned(bf2Image.getFirstEarned());
                localBF2Image.setLastEarned(bf2Image.getLastEarned());
                localBF2Image.updateData(bf2Image, manager.isShowTooltip());
            } else {
                int ribbonId = Integer.parseInt(bf2Image.getNodeTextEnum().name().substring(7));
                localBF2Image = bf2Images.get(NodeTextEnum.RIBBON.name() + ribbonNumber++);
                localBF2Image.setNodeTextEnum(bf2Image.getNodeTextEnum());
                localBF2Image.setLevel(bf2Image.getLevel());
                localBF2Image.setFirstEarned(bf2Image.getFirstEarned());
                localBF2Image.setLastEarned(bf2Image.getLastEarned());
                localBF2Image.updateData(pictureService.getSmallAwardBF2Image(ribbonId), manager.isShowTooltip());
            }
            localBF2Image.setImageId(bf2Image.getImageId());
        }
    }

    private void addPane(NodeTextEnum title, Integer[] imageNameIds, int columns) {
        Label label = new Label();
        label.setText(title.getText(label.textProperty()));
        label.setId(NamespaceEnum.TITLE_STYLE.getText());
        titles.add(label);
        content.getChildren().add(label);

        GridPane imagesGridPane = new GridPane();
        content.getChildren().add(imagesGridPane);
        grids.put(title, imagesGridPane);

        for (int i = 0; i < imageNameIds.length; i++) {
            BF2Image bf2Image = new BF2Image();
            if (imageNameIds[i] != null) {
                bf2Image = pictureService.getSmallAwardBF2Image(imageNameIds[i]);
                bf2Images.put(bf2Image.getNodeTextEnum().name(), bf2Image);
                imagesGridPane.add(bf2Image.getImageView(), i - (i / columns) * columns, i / columns);
            } else if (title.equals(NodeTextEnum.LAST_AWARD)) {
                bf2Images.put(title.name() + i, bf2Image);
                StackPane stackPane = new StackPane();
                stackPane.setAlignment(Pos.BOTTOM_CENTER);
                stackPane.getChildren().add(bf2Image.getImageView());
                Label dateLabel = new Label();
                dateLabel.setId(NamespaceEnum.DATE_STYLE.getText());
                stackPane.getChildren().add(dateLabel);
                stackPanes.add(stackPane);
                imagesGridPane.add(stackPane, i - (i / columns) * columns, i / columns);
            } else {
                bf2Images.put(title.name() + i, bf2Image);
                imagesGridPane.add(bf2Image.getImageView(), i - (i / columns) * columns, i / columns);
            }
        }
    }

    private void resetImages() {
        bf2Images.forEach(((nodeTextEnum, bf2Image) -> {
            if (String.valueOf(nodeTextEnum).contains(NodeTextEnum.LAST_AWARD.name())
                    || String.valueOf(nodeTextEnum).contains(NodeTextEnum.RIBBON.name())) {
                bf2Image.removeImage();
            } else if (String.valueOf(nodeTextEnum).contains(NodeTextEnum.BADGE.name())
                    || String.valueOf(nodeTextEnum).contains(NodeTextEnum.MEDAL.name())) {
                pictureService.removeColor(bf2Image);
            }
            bf2Image.updateData(bf2Image, false);
            bf2Image.setEnable(false);
            bf2Image.setImageId(null);
        }));
        stackPanes.forEach(stackPane ->
                ((Label) stackPane.getChildren().get(1)).setText(NamespaceEnum.EMPTY_STRING.getText()));
    }
}
