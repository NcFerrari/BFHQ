package lp.be.jpa;

import lombok.Getter;
import lp.be.jpa.dao.AwardsDao;
import lp.be.jpa.dao.KillsDao;
import lp.be.jpa.dao.MapsDao;
import lp.be.jpa.dao.PlayerDao;
import lp.be.jpa.daoimpl.AwardsDaoImpl;
import lp.be.jpa.daoimpl.KillsDaoImpl;
import lp.be.jpa.daoimpl.MapsDaoImpl;
import lp.be.jpa.daoimpl.PlayerDaoImpl;

@Getter
public class Daos {

    private final PlayerDao playerDao = new PlayerDaoImpl();
    private final AwardsDao awardsDao = new AwardsDaoImpl();
    private final MapsDao mapsDao = new MapsDaoImpl();
    private final KillsDao killsDao = new KillsDaoImpl();

}
