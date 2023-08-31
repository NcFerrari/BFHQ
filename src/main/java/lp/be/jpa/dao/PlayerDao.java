package lp.be.jpa.dao;

import lp.be.business.dto.Player;

public interface PlayerDao {

    void saveOrUpdate(Player player);

    Player getPlayer(String name);

    Player getPlayer(int id);

    void deletePlayer(Player player);

    void deletePlayer(int id);
}
