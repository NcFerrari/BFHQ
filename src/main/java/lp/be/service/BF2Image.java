package lp.be.service;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lp.fe.enums.NodeTextEnum;

@Getter
@Setter
public class BF2Image {

    private String name;
    private ImageView imageView;
    private NodeTextEnum toolkitKeyWord;

    public BF2Image(String name, Image image) {
        this.name = name;
        imageView = new ImageView();
        imageView.setImage(image);
        setToolkitKeyWord(name);
    }

    public void setToolkitKeyWord(String name) {
        if (name == null) {
            return;
        }
        String possibleName = name.toUpperCase().trim();
        if (nodeTextEnumContains(possibleName)) {
            this.toolkitKeyWord = NodeTextEnum.valueOf(possibleName);
        }
    }

    public Image getImage() {
        return imageView.getImage();
    }

    private boolean nodeTextEnumContains(String name) {
        for (NodeTextEnum value : NodeTextEnum.values()) {
            if (value.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
