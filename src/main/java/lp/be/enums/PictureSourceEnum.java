package lp.be.enums;

import lombok.Getter;

@Getter
public enum PictureSourceEnum {

    RANKS("pictures/ranks/", ".jpg");

    private final String path;
    private final String fileFormat;

    PictureSourceEnum(String path, String fileFormat) {
        this.path = path;
        this.fileFormat = fileFormat;
    }
}
