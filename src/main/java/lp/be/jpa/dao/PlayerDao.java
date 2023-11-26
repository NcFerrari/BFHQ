package lp.be.jpa.dao;

import lp.be.business.dto.Player;

import java.util.List;
import java.util.Map;

public interface PlayerDao {

    void saveOrUpdate(Player player);

    Player getPlayer(String name);

    Player getPlayer(int id);

    List<Player> getAllPlayer();

    void deletePlayer(Player player);

    void deletePlayer(int id);

    void timeRepair(Map<String, Player> players);
}
