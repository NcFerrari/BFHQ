package lp.be.jpa.dao;

import lp.be.business.dto.Kills;
import lp.be.business.dto.KillsForPlayer;

import java.util.List;
import java.util.Map;

public interface KillsDao {

    void saveOrUpdate(Kills kills);

    Kills getKills(int id);

    Map<String, Map<String, Integer>> getAllKills();

    void deleteKills(Kills kills);

    void deleteKills(int id);

    List<KillsForPlayer> getDataForPlayer(Integer id);
}