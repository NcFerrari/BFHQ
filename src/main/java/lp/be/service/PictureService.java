package lp.be.service;

public interface PictureService {

    BF2Image getRankBF2Image(int rank);

    BF2Image getSmallRankBF2Image(int rank);

    BF2Image getAwardBF2Image(int imageId);

    BF2Image getAwardBF2Image(int imageId, int level);

    BF2Image getSmallAwardBF2Image(int imageId);

    BF2Image getFactionBF2Image(int factionId);

    BF2Image getKitBF2Image(int kitId);

    BF2Image getMapBF2Image(int mapId);

    BF2Image getVehicleBF2Image(int vehicleId);

    BF2Image getWeaponBF2Image(int weaponId);

    void removeColor(BF2Image bf2Image);

    void addColor(BF2Image bf2Image);
}
