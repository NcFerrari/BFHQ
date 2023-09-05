package lp.be.serviceimpl;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lp.be.service.BF2Image;
import lp.be.service.PictureService;
import lp.fe.enums.NodeTextEnum;

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
    public BF2Image getRankBF2Image(int rank) {
        return getImage(PictureSourceEnum.RANKS, rank);
    }

    @Override
    public BF2Image getAwardBF2Image(int imageId, int level) {
        return getImage(PictureSourceEnum.getPictureSourceEnum(imageId, level), imageId);
    }

    @Override
    public BF2Image getSmallAwardBF2Image(int imageId) {
        return getImage(PictureSourceEnum.getPictureSourceEnum(imageId, 1, true), imageId);
    }

    @Override
    public BF2Image getFactionBF2Image(int factionId) {
        return getImage(PictureSourceEnum.FACTIONS, factionId);
    }

    @Override
    public BF2Image getKitBF2Image(int kitId) {
        return getImage(PictureSourceEnum.KITS, kitId);
    }

    @Override
    public BF2Image getMapBF2Image(int mapId) {
        return getImage(PictureSourceEnum.MAPS, mapId);
    }

    @Override
    public BF2Image getVehicleBF2Image(int vehicleId) {
        return getImage(PictureSourceEnum.VEHICLES, vehicleId);
    }

    @Override
    public BF2Image getWeaponBF2Image(int weaponId) {
        return getImage(PictureSourceEnum.WEAPONS, weaponId);
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

    private BF2Image getImage(PictureSourceEnum sourceEnum, int pictureId) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                sourceEnum.getPath() + pictureId + sourceEnum.getFileFormat());
        if (inputStream == null) {
            return null;
        }
        return new BF2Image(new Image(inputStream), pictureId, NodeTextEnum.getNodeTextEnum(sourceEnum, pictureId));
    }
}
