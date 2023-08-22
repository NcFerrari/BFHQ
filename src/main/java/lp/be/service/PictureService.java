package lp.be.service;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lp.be.enums.PictureSourceEnum;

public interface PictureService {

    BF2Image getImage(PictureSourceEnum sourceEnum, String pictureName);

    BF2Image getImage(PictureSourceEnum sourceEnum, int pictureNameAsNumber);


    void removeColor(BF2Image bf2Image);

    void addColor(BF2Image bf2Image);
}
