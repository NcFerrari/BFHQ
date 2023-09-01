package lp.fe.javafx.bf2components.awards;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lp.Manager;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.RightSidePart;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class AwardTwoThird {

    private final VBox rightPane;
    private final ObservableList<Label> titles = FXCollections.observableArrayList();
    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private final ObservableList<GridPane> grids = FXCollections.observableArrayList();
    private final ObservableMap<NodeTextEnum, ObservableList<ObservableMap<Object, BF2Image>>> bf2imageMap =
            FXCollections.observableMap(new HashMap<>());
    private final Manager manager = Manager.getInstance();
    private ObservableList<BF2Image> awardsOfSelectedPlayer = FXCollections.observableArrayList();

    public AwardTwoThird(RightSidePart rightSidePart) {
        this.rightPane = rightSidePart.getRightPane();
        Integer[] medals = {2020419, 2020719, 2020903, 2020913, 2020919, 2021322, 2021403, 2021613, 2051902,
                2051907, 2051919, 2190303, 2190308, 2190309, 2190318, 2190703, 2191319, 2191608, 3270519};
        Integer[] badges = {1031105, 1031109, 1031113, 1031115, 1031119, 1031120, 1031121, 1031406, 1031619,
                1031923, 1032415, 1190304, 1190507, 1190601, 1191819, 1220104, 1220118, 1220122, 1220803, 1222016};
        Integer[] ribbons = {3040109, 3040718, 3150914, 3151920, 3190105, 3190118, 3190318, 3190409, 3190605,
                3190803, 3191305, 3211305, 3212201, 3240102, 3240301, 3240703, 3241213, 3242303};
        addPane(NodeTextEnum.LAST_AWARD, new Integer[6], 6);
        addPane(NodeTextEnum.MEDALS, medals, 10);
        addPane(NodeTextEnum.BADGES, badges, 10);
        addPane(NodeTextEnum.RIBBONS, ribbons, 8);
    }

    public void resize(Stage stage) {
        double twoThirdWidth = 2 * stage.getWidth() / 3 - 18;
        rightPane.setPrefWidth(twoThirdWidth);
        titles.forEach(title -> title.setMinWidth(twoThirdWidth));

        bf2imageMap.forEach(((nodeTextEnum, bf2ImageList) -> {
            if (nodeTextEnum == NodeTextEnum.LAST_AWARD) {
                bf2ImageList.forEach(bf2Images -> bf2Images.values().forEach(bf2Image ->
                        bf2Image.setImageViewSize(twoThirdWidth / (bf2Images.size() + 3),
                                twoThirdWidth / (bf2Images.size() + 3))));
            } else if (nodeTextEnum == NodeTextEnum.RIBBONS) {
                double twoThirdWidthPart = twoThirdWidth / (bf2ImageList.get(0).size());
                bf2ImageList.forEach(bf2Images -> bf2Images.values().forEach(bf2Image ->
                        bf2Image.setImageViewSize(twoThirdWidthPart, twoThirdWidthPart / 3)));
            } else {
                bf2ImageList.forEach(bf2Images -> bf2Images.values().forEach(bf2Image ->
                        bf2Image.setImageViewSize(twoThirdWidth / (bf2Images.size() + 5.5))));
            }
        }));

        grids.stream().limit(grids.size() - 1L).forEach(grid -> grid.setHgap(stage.getWidth() / 48));
    }


    public void rewriteData() {
        resetImages();
        awardsOfSelectedPlayer = FXCollections.observableArrayList(manager.getAwardsForSelectedPlayer(0));
        for (int i = 0; i < awardsOfSelectedPlayer.size(); i++) {
            if (i < bf2imageMap.get(NodeTextEnum.LAST_AWARD).get(0).size()) {
                bf2imageMap.get(NodeTextEnum.LAST_AWARD).get(0).get(i).updateData(awardsOfSelectedPlayer.get(i),
                        manager.isShowToolkit());
            }
        }
    }

    private void addPane(NodeTextEnum title, Integer[] imageNameIds, int columns) {
        Label label = new Label();
        label.setText(title.getText(label.textProperty()));
        label.setId(NamespaceEnum.TITLE_STYLE.getText());
        titles.add(label);
        rightPane.getChildren().add(label);

        GridPane imagesGridPane = new GridPane();
        rightPane.getChildren().add(imagesGridPane);
        grids.add(imagesGridPane);

        ObservableList<ObservableMap<Object, BF2Image>> images = FXCollections.observableArrayList();
        for (int i = 0; i < (imageNameIds.length / columns) + (imageNameIds.length % columns == 0 ? 0 : 1); i++) {
            images.add(FXCollections.observableMap(new LinkedHashMap<>()));
        }
        for (int i = 0; i < imageNameIds.length; i++) {
            BF2Image bf2Image = new BF2Image();
            if (imageNameIds[i] != null) {
                bf2Image = pictureService.getSmallAwardBF2Image(imageNameIds[i]);
                images.get(i / columns).put(bf2Image.getNodeTextEnum(), bf2Image);
            } else {
                images.get(i / columns).put(i, bf2Image);
            }
            imagesGridPane.add(bf2Image.getImageView(), i - (i / columns) * columns, i / columns);
        }
        bf2imageMap.put(title, images);
        resetImages();
    }

    private void resetImages() {
        awardsOfSelectedPlayer.clear();
        bf2imageMap.forEach(((nodeTextEnum, bf2ImageList) -> {
            if (nodeTextEnum == NodeTextEnum.LAST_AWARD) {
                bf2ImageList.forEach(bf2Images -> bf2Images.values().forEach(BF2Image::removeImage));
            } else if (nodeTextEnum == NodeTextEnum.RIBBONS) {
                bf2ImageList.forEach(bf2Images -> bf2Images.values().forEach(BF2Image::removeImage));
            } else {
                bf2ImageList.forEach(bf2Images -> bf2Images.values().forEach(pictureService::removeColor));
            }
        }));
    }
}
