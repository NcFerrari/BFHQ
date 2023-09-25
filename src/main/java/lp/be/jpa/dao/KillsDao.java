package lp.be.jpa.dao;

import lp.be.business.dto.Kills;
import lp.be.business.dto.KillsForPlayer;

import java.util.List;

public interface KillsDao {

    void saveOrUpdate(Kills kills);

    Kills getKills(int id);

    List<Kills> getAllKills();

    void deleteKills(Kills kills);

    void deleteKills(int id);

    List<KillsForPlayer> getDataForPlayer(Integer id);
}