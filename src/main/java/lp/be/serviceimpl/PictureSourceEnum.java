package lp.be.serviceimpl;

import lombok.Getter;

@Getter
public
enum PictureSourceEnum {

    BRONZE_BADGES("pictures/badges/bronze/", ".jpg"),
    SILVER_BADGES("pictures/badges/silver/", ".jpg"),
    GOLD_BADGES("pictures/badges/gold/", ".jpg"),
    FACTIONS("pictures/factions/", ".png"),
    KITS("pictures/kits/", ".png"),
    MAPS("pictures/maps/", ".png"),
    MEDALS("pictures/medals/", ".jpg"),
    RANKS("pictures/ranks/", ".jpg"),
    RIBBONS("pictures/ribbons/", ".jpg"),
    SMALL_RANKS("pictures/smallRanks/", ".png"),
    SMALL_RIBBONS("pictures/smallRibbons/", ".png"),
    VEHICLES("pictures/vehicles/", ".png"),
    WEAPONS("pictures/weapons/", ".png");

    private final String path;
    private final String fileFormat;

    PictureSourceEnum(String path, String fileFormat) {
        this.path = path;
        this.fileFormat = fileFormat;
    }

    static PictureSourceEnum getPictureSourceEnum(int imageId, int level) {
        return getPictureSourceEnum(imageId, level, false);
    }

    static PictureSourceEnum getPictureSourceEnum(int imageId, int level, boolean smallRanks) {
        if (imageId <= 1222016) {
            return getBadgePath(level);
        } else if (imageId <= 2191608 || imageId == 3270519) {
            return MEDALS;
        } else {
            return getRibbonsPath(smallRanks);
        }
    }

    private static PictureSourceEnum getRibbonsPath(boolean smallRanks) {
        if (smallRanks) {
            return SMALL_RIBBONS;
        }
        return RIBBONS;
    }

    private static PictureSourceEnum getBadgePath(int level) {
        switch (level) {
            case 2:
                return SILVER_BADGES;
            case 3:
                return GOLD_BADGES;
            default:
                return BRONZE_BADGES;
        }
    }
}
