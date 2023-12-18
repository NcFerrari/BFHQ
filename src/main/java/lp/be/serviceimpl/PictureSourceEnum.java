package lp.be.serviceimpl;

import lombok.Getter;

@Getter
public
enum PictureSourceEnum {

    BRONZE_BADGES("pictures/badges/bronze/", ".png"),
    SILVER_BADGES("pictures/badges/silver/", ".png"),
    GOLD_BADGES("pictures/badges/gold/", ".png"),
    SMALL_BRONZE_BADGES("pictures/smallBadges/bronze/", ".png"),
    SMALL_SILVER_BADGES("pictures/smallBadges/silver/", ".png"),
    SMALL_GOLD_BADGES("pictures/smallBadges/gold/", ".png"),
    FACTIONS("pictures/factions/", ".png"),
    KITS("pictures/kits/", ".png"),
    MAPS("pictures/maps/", ".png"),
    MEDALS("pictures/medals/", ".png"),
    RANKS("pictures/ranks/", ".png"),
    RIBBONS("pictures/ribbons/", ".png"),
    SMALL_RANKS("pictures/smallRanks/", ".png"),
    SMALL_RIBBONS("pictures/smallRibbons/", ".png"),
    SMALL_MEDALS("pictures/smallMedals/", ".png"),
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
            return getBadgePath(level, smallRanks);
        } else if (imageId <= 2191608 || imageId == 3270519) {
            return getMedalPath(smallRanks);
        } else {
            return getRibbonsPath(smallRanks);
        }
    }

    private static PictureSourceEnum getMedalPath(boolean smallRanks) {
        if (smallRanks) {
            return SMALL_MEDALS;
        }
        return MEDALS;
    }

    private static PictureSourceEnum getRibbonsPath(boolean smallRanks) {
        if (smallRanks) {
            return SMALL_RIBBONS;
        }
        return RIBBONS;
    }

    private static PictureSourceEnum getBadgePath(int level, boolean smallRanks) {
        if (smallRanks) {
            level += 3;
        }
        switch (level) {
            case 2:
                return SILVER_BADGES;
            case 3:
                return GOLD_BADGES;
            case 4:
                return SMALL_BRONZE_BADGES;
            case 5:
                return SMALL_SILVER_BADGES;
            case 6:
                return SMALL_GOLD_BADGES;
            default:
                return BRONZE_BADGES;
        }
    }
}
