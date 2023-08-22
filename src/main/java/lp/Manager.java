package lp;

import lombok.Getter;
import lp.be.business.dto.Awards;
import lp.be.business.dto.Maps;
import lp.be.business.dto.Player;
import lp.be.jpa.dao.AwardsDao;
import lp.be.jpa.dao.MapsDao;
import lp.be.jpa.dao.PlayerDao;
import lp.be.jpa.daoimpl.AwardsDaoImpl;
import lp.be.jpa.daoimpl.MapsDaoImpl;
import lp.be.jpa.daoimpl.PlayerDaoImpl;
import lp.be.service.LangService;
import lp.be.serviceimpl.LangServiceImpl;
import lp.fe.enums.LangEnum;
import lp.fe.enums.NamespaceEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.MainApp;
import lp.fe.javafx.bf2components.BF2Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Manager {

    private static Manager manager;
    private final LangService langService = LangServiceImpl.getInstance();
    private final Map<String, Player> players = new HashMap<>();
    private final List<Maps> maps = new ArrayList<>();
    private final List<BF2Component> reloadableList = new ArrayList<>();

    private final PlayerDao playerDao = new PlayerDaoImpl();
    private final MapsDao mapsDao = new MapsDaoImpl();
    private final AwardsDao awardsDao = new AwardsDaoImpl();

    private Player selectedPlayer;

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

    public void loadDataFromDB() {
        players.clear();
        playerDao.getAllPlayer().forEach(player -> players.put(player.getName(), player));
        if (getSelectedPlayer() != null) {
            setSelectedPlayer(getSelectedPlayer().getName());
        }
        maps.clear();
        maps.addAll(mapsDao.getAllMaps());
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
    public List<Awards> getLastAwardsForSelectedPlayer(int limitOfAwards) {
        return awardsDao.getAllAwardsById(getSelectedPlayer().getId(), limitOfAwards);
    }
}
