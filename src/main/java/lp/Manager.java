package lp;

import lombok.Getter;
import lp.be.business.dto.Army;
import lp.be.business.dto.Awards;
import lp.be.business.dto.KillsForPlayer;
import lp.be.business.dto.KillsForTable;
import lp.be.business.dto.Kits;
import lp.be.business.dto.Maps;
import lp.be.business.dto.Player;
import lp.be.business.dto.Vehicles;
import lp.be.business.dto.Weapons;
import lp.be.jpa.daoimpl.AwardsDaoImpl;
import lp.be.jpa.daoimpl.KillsDaoImpl;
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
import lp.fe.javafx.bf2components.leaderboard.LeaderBoardPane;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private boolean showTooltip;

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

    public void refreshLeaderBoardComboBox() {
        Optional<LeaderBoardPane> possibleLeaderBuilder = reloadableList
                .stream()
                .filter(LeaderBoardPane.class::isInstance)
                .map(LeaderBoardPane.class::cast)
                .findFirst();
        possibleLeaderBuilder.ifPresent(LeaderBoardPane::refreshComboBox);
    }

    public void switchShowingTooltips() {
        showTooltip = !showTooltip;
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
        maps.clear();
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
            BF2Image bf2Image = pictureService.getAwardBF2Image(playerAwards.get(i).getAwd(),
                    playerAwards.get(i).getLevel().intValue());
            bf2Image.setLevel(playerAwards.get(i).getLevel().intValue());
            bf2Image.setFirstEarned(LocalDate.ofEpochDay(playerAwards.get(i).getFirst() / 86_400));
            bf2Image.setLastEarned(LocalDate.ofEpochDay(playerAwards.get(i).getEarned() / 86_400));
            bf2Images.add(bf2Image);

        }
        return bf2Images
                .stream()
                .sorted(Comparator.comparingInt(BF2Image::getLevel))
                .collect(Collectors.toList());
    }

    public Map<NamespaceEnum, String[]> getMostPlayedData() throws Exception {
        Map<NamespaceEnum, String[]> result = new EnumMap<>(NamespaceEnum.class);

        Long[] kitTimeArray = new Long[7];
        for (int i = 0; i < kitTimeArray.length; i++) {
            kitTimeArray[i] = (Long) getSelectedPlayer().getKits().getClass()
                    .getMethod(NamespaceEnum.GET_TIME.getText() + i).invoke(getSelectedPlayer().getKits());
        }

        Long[] vehicleTimeArray = new Long[8];
        for (int i = 0; i < vehicleTimeArray.length - 1; i++) {
            vehicleTimeArray[i] = (Long) getSelectedPlayer().getVehicles().getClass()
                    .getMethod(NamespaceEnum.GET_TIME.getText() + i).invoke(getSelectedPlayer().getVehicles());
        }
        vehicleTimeArray[7] = getSelectedPlayer().getVehicles().getTimepara();

        Long[] weaponTimeArray = new Long[14];
        for (int i = 0; i < weaponTimeArray.length - 5; i++) {
            weaponTimeArray[i] = (Long) getSelectedPlayer().getWeapons().getClass()
                    .getMethod(NamespaceEnum.GET_TIME.getText() + i).invoke(getSelectedPlayer().getWeapons());
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
                        longToTime((Long) army.getClass().getMethod(
                                NamespaceEnum.GET_TIME.getText() + (i != 3 ? i : 9)).invoke(army)),
                        String.valueOf(army.getClass().getMethod(
                                NamespaceEnum.GET_WIN.getText() + (i != 3 ? i : 9)).invoke(army)),
                        String.valueOf(army.getClass().getMethod(
                                NamespaceEnum.GET_LOSS.getText() + (i != 3 ? i : 9)).invoke(army))});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Map<NodeTextEnum, String[]> getMapsStatsData() {
        Map<NodeTextEnum, String[]> result = new EnumMap<>(NodeTextEnum.class);
        maps.get(getSelectedPlayer().getId()).forEach(map -> result.put(NodeTextEnum.valueOf(
                NamespaceEnum.MAP.getText() + map.getMapid()), new String[]{
                String.valueOf(map.getWin()), String.valueOf(map.getLoss()), longToTime(map.getTime())})
        );
        return result;
    }

    public List<String[]> getTeamWorkData() {
        List<String[]> result = new ArrayList<>();
        result.add(new String[]{formatNumber(getSelectedPlayer().getTeamscore())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getCaptures())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getCaptureassists())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getDefends())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getDamageassists())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getHeals())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getRevives())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getAmmos())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getRepairs())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getDriverspecials())});
        return result;
    }

    private String formatNumber(Long value) {
        StringBuilder result = new StringBuilder(value.toString());
        for (int i = result.length() - 3; i > 0; i -= 3) {
            result.insert(i, NamespaceEnum.TEXT_POINT.getText());
        }
        return result.toString();
    }

    public List<String[]> getPlayerStatsData() {
        List<String[]> result = new ArrayList<>();
        result.add(new String[]{formatNumber(getSelectedPlayer().getKillstreak())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getDeathstreak())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getDriverassists())});
        result.add(new String[]{formatNumber(getSelectedPlayer().getCmdscore())});
        result.add(new String[]{longToTime(getSelectedPlayer().getCmdtime())});
        result.add(new String[]{longToTime(getSelectedPlayer().getSqltime())});
        result.add(new String[]{longToTime(getSelectedPlayer().getSqmtime())});
        result.add(new String[]{longToTime(getSelectedPlayer().getLwtime())});
        return result;
    }

    public List<String[]> getKitEquipmentData() {
        List<String[]> result = new ArrayList<>();
        Weapons weapon = getSelectedPlayer().getWeapons();
        long explodesKills = weapon.getC4kills() + weapon.getClaymorekills() + weapon.getAtminekills();
        long explodesDeaths = weapon.getC4deaths() + weapon.getClaymoredeaths() + weapon.getAtminedeaths();
        long explodesHits = weapon.getC4hit() + weapon.getClaymorehit() + weapon.getAtminehit();
        long explodesFired = weapon.getC4fired() + weapon.getClaymorefired() + weapon.getAtminefired();
        long explodesTime = weapon.getC4time() + weapon.getClaymoretime() + weapon.getAtminetime();
        Long[] sums = {0L, 0L, 0L, 0L, 0L};
        Long[][] longs = new Long[13][];
        for (int i = 0; i < longs.length - 4; i++) {
            try {
                longs[i] = new Long[]{
                        (Long) weapon.getClass().getMethod(NamespaceEnum.GET_KILLS.getText() + i).invoke(weapon),
                        (Long) weapon.getClass().getMethod(NamespaceEnum.GET_DEATHS.getText() + i).invoke(weapon),
                        (Long) weapon.getClass().getMethod(NamespaceEnum.GET_HIT.getText() + i).invoke(weapon),
                        (Long) weapon.getClass().getMethod(NamespaceEnum.GET_FIRED.getText() + i).invoke(weapon),
                        (Long) weapon.getClass().getMethod(NamespaceEnum.GET_TIME.getText() + i).invoke(weapon)};
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        longs[9] = new Long[]{weapon.getKnifekills(), weapon.getKnifedeaths(), weapon.getKnifehit(),
                weapon.getKnifefired(), weapon.getKnifetime()};
        longs[10] = new Long[]{weapon.getShockpadkills(), weapon.getShockpaddeaths(), weapon.getShockpadhit(),
                weapon.getShockpadfired(), weapon.getShockpadtime()};
        longs[11] = new Long[]{explodesKills, explodesDeaths, explodesHits, explodesFired, explodesTime};
        longs[12] = new Long[]{weapon.getHandgrenadekills(), weapon.getHandgrenadedeaths(), weapon.getHandgrenadehit(),
                weapon.getHandgrenadefired(), weapon.getHandgrenadetime()};
        for (Long[] data : longs) {
            addToKitEquipmentResulList(result, data);
            for (int i = 0; i < data.length; i++) {
                sums[i] += data[i];
            }
        }
        addToKitEquipmentResulList(result, sums);
        return result;
    }

    private void addToKitEquipmentResulList(List<String[]> resultList, Long[] data) {
        double accuracy = ((double) data[2] / (data[3] > 0 ? data[3] : 1)) * 100;
        resultList.add(new String[]{String.format(NamespaceEnum.TWO_DIGITS_FORMAT.getText(), accuracy),
                formatNumber(data[0]), formatNumber(data[1]), getKDRatio(data[0], data[1]), longToTime(data[4])}
        );
    }

    private String getKDRatio(Long a, Long b) {
        long nsd = lcm(a, b);
        if (nsd == 0) {
            return a + NamespaceEnum.COLON.getText() + b;
        }
        return (a / nsd) + NamespaceEnum.COLON.getText() + (b / nsd);
    }

    /**
     * Least common multiple
     *
     * @return least common multiple (number)
     */
    private long lcm(long firstNumber, long secondNumber) {
        if (firstNumber == 0 || secondNumber == 0) {
            return 1;
        }
        firstNumber = Math.abs(firstNumber);
        secondNumber = Math.abs(secondNumber);
        while (secondNumber > 0) {
            long c = firstNumber % secondNumber;
            firstNumber = secondNumber;
            secondNumber = c;
        }
        return firstNumber;
    }

    public List<String[]> getVehicleCategoryData() {
        List<String[]> result = new ArrayList<>();
        Vehicles vehicles = getSelectedPlayer().getVehicles();
        for (int i = 0; i < 7; i++) {
            if (i == 5) {
                continue;
            }
            try {
                result.add(addToResult(new Long[]{
                        (Long) vehicles.getClass().getMethod(NamespaceEnum.GET_KILLS.getText() + i).invoke(vehicles),
                        (Long) vehicles.getClass().getMethod(NamespaceEnum.GET_RK.getText() + i).invoke(vehicles),
                        (Long) vehicles.getClass().getMethod(NamespaceEnum.GET_DEATHS.getText() + i).invoke(vehicles),
                        (Long) vehicles.getClass().getMethod(NamespaceEnum.GET_TIME.getText() + i).invoke(vehicles)}));
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        return result;
    }

    public List<String[]> getKitsData() {
        List<String[]> result = new ArrayList<>();
        Kits kits = getSelectedPlayer().getKits();
        for (int i = 0; i < 7; i++) {
            try {
                result.add(new String[]{
                        formatNumber((Long) kits.getClass().getMethod(NamespaceEnum.GET_KILLS.getText() + i)
                                .invoke(kits)),
                        formatNumber((Long) kits.getClass().getMethod(NamespaceEnum.GET_DEATHS.getText() + i)
                                .invoke(kits)),
                        getKDRatio((Long) kits.getClass().getMethod(NamespaceEnum.GET_KILLS.getText() + i).invoke(kits),
                                (Long) kits.getClass().getMethod(NamespaceEnum.GET_DEATHS.getText() + i).invoke(kits)),
                        longToTime((Long) kits.getClass().getMethod(NamespaceEnum.GET_TIME.getText() + i).invoke(kits))
                });
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }
        return result;
    }

    private String[] addToResult(Long[] data) {
        return new String[]{formatNumber(data[0]), formatNumber(data[1]), formatNumber(data[2]),
                getKDRatio(data[0], data[1]), longToTime(data[3])};
    }

    public List<KillsForPlayer> getKillsForSelectedPlayer() {
        return new KillsDaoImpl().getDataForPlayer(getSelectedPlayer().getId());
    }

    public List<KillsForTable> getKillsForAllPlayers() {
        List<KillsForTable> resultList = new ArrayList<>();
        new KillsDaoImpl().getAllKills().forEach((name, map) -> {
            KillsForTable killsForTable = new KillsForTable();
            killsForTable.setName(name);
            killsForTable.setEnemyKills(map);
            resultList.add(killsForTable);
        });
        return resultList
                .stream()
                .sorted(Comparator.comparing(KillsForTable::getName))
                .collect(Collectors.toList());
    }
}
