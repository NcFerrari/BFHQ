package lp;

import lombok.Getter;
import lp.be.business.dto.Maps;
import lp.be.business.dto.Player;
import lp.be.jpa.dao.MapsDao;
import lp.be.jpa.dao.PlayerDao;
import lp.be.jpa.daoimpl.MapsDaoImpl;
import lp.be.jpa.daoimpl.PlayerDaoImpl;
import lp.be.service.LangService;
import lp.be.serviceimpl.LangServiceImpl;
import lp.fe.enums.LangEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.MainApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Manager {

    private static Manager manager;
    private final LangService langService = LangServiceImpl.getInstance();
    private final Map<String, Player> players = new HashMap<>();
    private final List<Maps> maps = new ArrayList<>();

    private final PlayerDao playerDao = new PlayerDaoImpl();
    private final MapsDao mapsDao = new MapsDaoImpl();

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
}
