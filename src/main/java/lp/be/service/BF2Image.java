package lp.be.service;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lp.fe.enums.NodeTextEnum;

import java.time.LocalDate;

@Getter
@Setter
public class BF2Image {

    private final ImageView imageView;
    private final Tooltip tooltip;
    private NodeTextEnum nodeTextEnum;
    private LocalDate firstEarned;
    private LocalDate lastEarned;
    private int level;
    private Integer imageId;
    private boolean enable;

    public BF2Image() {
        this(null, null, null);
    }

    public BF2Image(Image image, Integer imageId, NodeTextEnum nodeTextEnum) {
        this.nodeTextEnum = nodeTextEnum;
        this.imageId = imageId;
        imageView = new ImageView();
        tooltip = new Tooltip();
        imageView.setImage(image);
    }

    public void updateData(BF2Image bf2Image, boolean enableToolkit) {
        if (!NodeTextEnum.getComponentsForTranslate().containsKey(tooltip.textProperty())) {
            tooltip.setText(NodeTextEnum.EMPTY_STRING.getText(tooltip.textProperty()));
        }
        if (enableToolkit && bf2Image.getNodeTextEnum() != null) {
            NodeTextEnum.getComponentsForTranslate().replace(tooltip.textProperty(), bf2Image.getNodeTextEnum());
            Tooltip.install(imageView, tooltip);
        } else {
            Tooltip.uninstall(imageView, tooltip);
        }
        imageView.setImage(bf2Image.getImage());
        setEnable(true);
    }

    public Image getImage() {
        return imageView.getImage();
    }

    public void setImageViewSize(double width, double height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    public void setImageViewSize(double size) {
        setImageViewSize(size, size);
    }

    public void removeImage() {
        getImageView().setImage(null);
    }
}
