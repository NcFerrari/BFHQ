package lp.be.service;

public interface PictureService {

    BF2Image getRankBF2Image(int rank);

    BF2Image getSmallRankBF2Image(int rank);

    BF2Image getAwardBF2Image(int imageId);

    BF2Image getAwardBF2Image(int imageId, int level);

    BF2Image getSmallAwardBF2Image(int imageId);

    BF2Image getFactionBF2Image(int faction);

    BF2Image getKitBF2Image(int kit);

    BF2Image getMapBF2Image(int map);

    BF2Image getVehicleBF2Image(int vehicle);

    BF2Image getWeaponBF2Image(int weapon);

    void removeColor(BF2Image bf2Image);

    void addColor(BF2Image bf2Image);
}
