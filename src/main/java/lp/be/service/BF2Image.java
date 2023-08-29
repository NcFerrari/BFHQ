package lp.be.service;

import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lp.fe.enums.NodeTextEnum;

@Getter
@Setter
public class BF2Image {

    private final ImageView imageView;
    private final Tooltip tooltip;
    private NodeTextEnum nodeTextEnum;

    public BF2Image() {
        this(null, null);
    }

    public BF2Image(Image image, NodeTextEnum nodeTextEnum) {
        this.nodeTextEnum = nodeTextEnum;
        imageView = new ImageView();
        tooltip = new Tooltip();
        imageView.setImage(image);
    }

    public void installTooltip(String text) {
        tooltip.setText(text);
        Tooltip.install(imageView, tooltip);
    }

    public void uninstallTooltip() {
        Tooltip.uninstall(imageView, tooltip);
    }

    public void updateData(BF2Image bf2Image, boolean enableToolkit) {
        if (!NodeTextEnum.getComponentsForTranslate().containsKey(tooltip.textProperty())) {
            tooltip.setText(NodeTextEnum.EMPTY_STRING.getText(tooltip.textProperty()));
        }
        if (enableToolkit && bf2Image.getNodeTextEnum() != null) {
            NodeTextEnum.getComponentsForTranslate().replace(tooltip.textProperty(), bf2Image.getNodeTextEnum());
            Tooltip.install(imageView, tooltip);
        } else {
            uninstallTooltip();
        }
        imageView.setImage(bf2Image.getImage());
    }

    public Image getImage() {
        return imageView.getImage();
    }

    public void setImageViewSize(double width, double height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    public void removeImage() {
        getImageView().setImage(null);
    }
}
