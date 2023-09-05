package lp.fe.javafx.bf2components.awards;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.bf2components.LeftSidePart;

public class AwardOneThird {

    private final PictureService pictureService = PictureServiceImpl.getInstance();
    private final StackPane paneForBigImage = new StackPane();
    private final BF2Image bigImage;

    public AwardOneThird(LeftSidePart leftSidePart) {
        bigImage = new BF2Image();
        paneForBigImage.setId(NamespaceEnum.BIG_IMAGE_STYLE.getText());
        paneForBigImage.getChildren().add(bigImage.getImageView());
        leftSidePart.getLeftPane().getChildren().add(new Pane(paneForBigImage));
    }

    public void resize(Stage stage) {
        double oneThird = stage.getWidth() / 3;
        double oneFifth = stage.getWidth() / 5;
        paneForBigImage.setPrefSize(oneFifth, oneFifth);
        paneForBigImage.setLayoutX(oneThird / 2 - oneFifth / 2);
        bigImage.setImageViewSize(oneFifth);
    }

    public void showBigImage(String key, BF2Image bf2Image) {
        if (key.contains(NodeTextEnum.RIBBON.name())) {
            bigImage.getImageView().setImage(pictureService.getAwardBF2Image(bf2Image.getImageId(), 1).getImage());
        } else {
            bigImage.getImageView().setImage(bf2Image.getImage());
        }
    }

    public void clearBigImage() {
        bigImage.getImageView().setImage(null);
    }
}
