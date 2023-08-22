package lp.be.serviceimpl;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lp.be.enums.PictureSourceEnum;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;

import java.io.InputStream;

public class PictureServiceImpl implements PictureService {

    private static PictureService pictureService;

    public static PictureService getInstance() {
        if (pictureService == null) {
            pictureService = new PictureServiceImpl();
        }
        return pictureService;
    }

    private PictureServiceImpl() {

    }

    @Override
    public BF2Image getImage(PictureSourceEnum sourceEnum, String pictureName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                sourceEnum.getPath() + pictureName + sourceEnum.getFileFormat());
        if (inputStream == null) {
            return null;
        }
        return new BF2Image(pictureName, new Image(inputStream));
    }

    @Override
    public BF2Image getImage(PictureSourceEnum sourceEnum, int pictureNameAsNumber) {
        return getImage(sourceEnum, String.valueOf(pictureNameAsNumber));
    }

    @Override
    public void removeColor(BF2Image bf2Image) {
        if (bf2Image.getImageView().getEffect() == null) {
            bf2Image.getImageView().setEffect(new ColorAdjust());
        }
        useEffect(bf2Image.getImageView(), -1, -0.77, -0.63, -0.25);
    }

    @Override
    public void addColor(BF2Image bf2Image) {
        if (bf2Image.getImageView().getEffect() == null) {
            bf2Image.getImageView().setEffect(new ColorAdjust());
        }
        useEffect(bf2Image.getImageView(), 0, 0, 0, 0);
    }

    private void useEffect(ImageView imageView, double hue, double saturation, double brightness, double contrast) {
        ((ColorAdjust) imageView.getEffect()).setHue(hue);
        ((ColorAdjust) imageView.getEffect()).setSaturation(saturation);
        ((ColorAdjust) imageView.getEffect()).setBrightness(brightness);
        ((ColorAdjust) imageView.getEffect()).setContrast(contrast);
    }
}
