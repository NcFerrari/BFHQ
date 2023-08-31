package lp;

import lombok.Getter;
import lp.be.business.dto.Army;
import lp.be.business.dto.Awards;
import lp.be.business.dto.Maps;
import lp.be.business.dto.Player;
import lp.be.jpa.daoimpl.AwardsDaoImpl;
import lp.be.jpa.daoimpl.MapsDaoImpl;
import lp.be.jpa.daoimpl.PlayerDaoImpl;
import lp.be.service.BF2Image;
import lp.be.service.LangService;
import lp.be.service.PictureService;
import lp.be.serviceimpl.LangServiceImpl;
import lp.be.serviceimpl.PictureServiceImpl;
import lp.fe.enums.LangEnum;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.MainApp;
import lp.fe.javafx.bf2components.BF2Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Manager {

    private static Manager manager;
    private final LangService langService = LangServiceImpl.getInstance();
    private final Map<String, Player> players = new HashMap<>();
    private final Map<Integer, List<Awards>> awards = new HashMap<>();
    private final Map<Integer, List<Maps>> maps = new HashMap<>();
    private final List<BF2Component> reloadableList = new ArrayList<>();
    private final PictureService pictureService = PictureServiceImpl.getInstance();

    private Player selectedPlayer;
    private boolean showToolkit;

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
            javafx.application.Application.launch(MainApp.class);
        }
        return manager;
    }

    private Manager() {
        loadDataFromDB();
    }

    public static void main(String[] args) {
        getInstance();
    }

    public LangEnum getLanguage() {
        return langService.getSelectedLanguage();
    }

    public void setLanguage(LangEnum language) {
        langService.setSelectedLanguage(language);
        NodeTextEnum.reloadTexts();
    }

    public void switchShowingTooltips() {
        showToolkit = !showToolkit;
        reloadableList.forEach(BF2Component::rewriteData);
    }

    public void loadDataFromDB() {
        players.clear();
        new PlayerDaoImpl().getAllPlayer().forEach(player -> players.put(player.getName(), player));
        if (getSelectedPlayer() != null) {
            setSelectedPlayer(getSelectedPlayer().getName());
        }
        awards.clear();
        new AwardsDaoImpl().getAllAwards().forEach(award -> {
            awards.putIfAbsent(award.getId(), new ArrayList<>());
            awards.get(award.getId()).add(award);
        });
        new MapsDaoImpl().getAllMaps().forEach(map -> {
            maps.putIfAbsent(map.getId(), new ArrayList<>());
            maps.get(map.getId()).add(map);
        });
    }

    public void setSelectedPlayer(String playerName) {
        selectedPlayer = players.get(playerName);
    }

    public List<String> getPlayerNames() {
        List<String> playerNames = new ArrayList<>(players.keySet());
        Collections.sort(playerNames);
        return playerNames;
    }

    public void registerReloadableComponent(BF2Component component) {
        reloadableList.add(component);
    }

    public String longToTime(Long time) {
        String result = NamespaceEnum.EMPTY_STRING.getText();
        long countedTime = time;
        if (countedTime / 86_400 > 0) {
            result += countedTime / 86_400 + NamespaceEnum.DAYS_LETTER.getText() + NamespaceEnum.SPACE_STRING.getText();
            countedTime %= 86_400;
        }
        if (countedTime / 3_600 > 0 || time > 3_600) {
            result += countedTime / 3_600 + NamespaceEnum.HOURS_LETTER.getText() + NamespaceEnum.SPACE_STRING.getText();
            countedTime %= 3_600;
        }
        if (countedTime / 60 > 0 || time > 60) {
            result += countedTime / 60 + NamespaceEnum.MINUTES_LETTER.getText() + NamespaceEnum.SPACE_STRING.getText();
            countedTime %= 60;
        }
        result += countedTime + NamespaceEnum.SECONDS_LETTER.getText();
        return result;
    }

    /**
     * @param limitOfAwards if limit is 0, it returns all awards for selected player
     * @return list of awards
     */
    public List<BF2Image> getAwardsForSelectedPlayer(int limitOfAwards) {
        List<BF2Image> bf2Images = new ArrayList<>();
        List<Awards> playerAwards = awards.get(getSelectedPlayer().getId());
        if (playerAwards == null) {
            return bf2Images;
        }
        for (int i = 0; i < playerAwards.size() && (limitOfAwards == 0 || i < limitOfAwards); i++) {
            bf2Images.add(pictureService.getAwardBF2Image(playerAwards.get(i).getAwd(),
                    playerAwards.get(i).getLevel().intValue()));
        }
        return bf2Images;
    }

    public Map<NamespaceEnum, String[]> getMostPlayedData() throws Exception {
        Map<NamespaceEnum, String[]> result = new EnumMap<>(NamespaceEnum.class);

        Long[] kitTimeArray = new Long[7];
        for (int i = 0; i < kitTimeArray.length; i++) {
            kitTimeArray[i] = (Long) getSelectedPlayer().getKits().getClass()
                    .getMethod("getTime" + i).invoke(getSelectedPlayer().getKits());
        }

        Long[] vehicleTimeArray = new Long[8];
        for (int i = 0; i < vehicleTimeArray.length - 1; i++) {
            vehicleTimeArray[i] = (Long) getSelectedPlayer().getVehicles().getClass()
                    .getMethod("getTime" + i).invoke(getSelectedPlayer().getVehicles());
        }
        vehicleTimeArray[7] = getSelectedPlayer().getVehicles().getTimepara();

        Long[] weaponTimeArray = new Long[14];
        for (int i = 0; i < weaponTimeArray.length - 5; i++) {
            weaponTimeArray[i] = (Long) getSelectedPlayer().getWeapons().getClass()
                    .getMethod("getTime" + i).invoke(getSelectedPlayer().getWeapons());
        }
        weaponTimeArray[9] = getSelectedPlayer().getWeapons().getKnifetime();
        weaponTimeArray[10] = getSelectedPlayer().getWeapons().getC4time();
        weaponTimeArray[11] = getSelectedPlayer().getWeapons().getHandgrenadetime();
        weaponTimeArray[12] = getSelectedPlayer().getWeapons().getClaymoretime();
        weaponTimeArray[13] = getSelectedPlayer().getWeapons().getAtminetime();

        result.put(NamespaceEnum.KIT, getMostPlayedValueFromArray(kitTimeArray));
        result.put(NamespaceEnum.VEHICLE, getMostPlayedValueFromArray(vehicleTimeArray));
        result.put(NamespaceEnum.WEAPON, getMostPlayedValueFromArray(weaponTimeArray));
        result.put(NamespaceEnum.MAP, getMostPlayedMap());
        return result;
    }

    private String[] getMostPlayedValueFromArray(Long[] array) {
        String mostPlayedIndex = NamespaceEnum.ZERO.getText();
        long highestTimePlayed = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > highestTimePlayed) {
                highestTimePlayed = array[i];
                mostPlayedIndex = String.valueOf(i);
            }
        }
        return new String[]{mostPlayedIndex, longToTime(highestTimePlayed)};
    }

    private String[] getMostPlayedMap() {
        long mostPlayedIndex = 0;
        long highestTimePlayed = 0;
        for (int i = 0; i < getMapsForSelectedPlayer().size(); i++) {
            if (getMapsForSelectedPlayer().get(i).getTime() > highestTimePlayed) {
                highestTimePlayed = getMapsForSelectedPlayer().get(i).getTime();
                mostPlayedIndex = getMapsForSelectedPlayer().get(i).getMapid();
            }
        }
        return new String[]{String.valueOf(mostPlayedIndex), longToTime(highestTimePlayed)};
    }

    public List<Maps> getMapsForSelectedPlayer() {
        return maps.get(getSelectedPlayer().getId());
    }

    public Map<NodeTextEnum, String[]> getArmyStatsData() {
        Map<NodeTextEnum, String[]> result = new EnumMap<>(NodeTextEnum.class);
        Army army = getSelectedPlayer().getArmy();
        NodeTextEnum[] factions = {NodeTextEnum.FACTION_USMC, NodeTextEnum.FACTION_MEC, NodeTextEnum.FACTION_PLA,
                NodeTextEnum.FACTION_EU};
        for (int i = 0; i < 4; i++) {
            try {
                result.put(factions[i], new String[]{
                        longToTime((Long) army.getClass().getMethod("getTime" + (i != 3 ? i : 9)).invoke(army)),
                        String.valueOf(army.getClass().getMethod("getWin" + (i != 3 ? i : 9)).invoke(army)),
                        String.valueOf(army.getClass().getMethod("getLoss" + (i != 3 ? i : 9)).invoke(army))});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
