package lp.be.jpa.daoimpl;

import lp.be.business.dto.Kills;
import lp.be.business.dto.KillsForPlayer;
import lp.be.jpa.EntityManager;
import lp.be.jpa.dao.KillsDao;
import lp.be.jpa.entity.KillsEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KillsDaoImpl extends EntityManager implements KillsDao {

    @Override
    public void saveOrUpdate(Kills kills) {
        if (getSession() == null || kills == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(kills));
        getSession().getTransaction().commit();
    }

    @Override
    public Kills getKills(int id) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        KillsEntity entity = getSession().get(KillsEntity.class, id);
        getSession().getTransaction().commit();
        return mapEntityToDto(entity);
    }

    @Override
    public Map<String, Map<String, Integer>> getAllKills() {
        if (getSession() == null) {
            return Collections.emptyMap();
        }
        getSession().beginTransaction();
        List<Object[]> entities = getSession().createNativeQuery(
                "SELECT p.name AS 'attacker', p2.name as 'victim', k.count AS 'count'" +
                        "FROM kills k " +
                        "JOIN player p " +
                        "ON k.attacker=p.id " +
                        "JOIN player p2 " +
                        "ON k.victim =p2.id").list();
        getSession().getTransaction().commit();
        Map<String, Map<String, Integer>> resultMap = new HashMap<>();
        entities.forEach(objects -> {
            resultMap.putIfAbsent((String) objects[0], new HashMap<>());
            resultMap.get(String.valueOf(objects[0])).put(String.valueOf(objects[1]), (Integer) objects[2]);
        });
        return resultMap;
    }

    @Override
    public void deleteKills(Kills kills) {
        if (getSession() == null || kills == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(kills));
        getSession().getTransaction().commit();
    }

    @Override
    public void deleteKills(int id) {
        if (getSession() == null) {
            return;
        }
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM KillsEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
    }

    @Override
    public List<KillsForPlayer> getDataForPlayer(Integer id) {
        if (getSession() == null) {
            return Collections.emptyList();
        }
        getSession().beginTransaction();
        List<Object[]> entities = getSession().createNativeQuery(
                "SELECT p.name AS 'name'," +
                        "victims.count AS 'killed'," +
                        "attackers.count AS 'killed_by' " +
                        "FROM player p " +
                        "JOIN (SELECT victim , count FROM kills where attacker=:id) victims " +
                        "ON p.id=victims.victim " +
                        "JOIN (SELECT attacker, count from kills where victim=:id) attackers " +
                        "ON p.id=attackers.attacker " +
                        "ORDER BY victims.count DESC, p.name").setParameter("id", id).list();
        getSession().getTransaction().commit();

        List<Object[]> entities2 = entities
                .stream()
                .sorted(Comparator.comparing(o -> ((String) o[0])))
                .sorted((o1, o2) -> ((Integer) o2[2]).compareTo((Integer) o1[2]))
                .collect(Collectors.toList());
        List<KillsForPlayer> killsForPlayerList = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            KillsForPlayer killsForPlayer = new KillsForPlayer();
            killsForPlayer.setNameOfKilledPlayer((String) entities.get(i)[0]);
            killsForPlayer.setCountOfKillsAnotherPlayer((Integer) entities.get(i)[1]);
            killsForPlayer.setNameOfPlayerWhoKilled((String) entities2.get(i)[0]);
            killsForPlayer.setCountOfKillsByAnotherPlayer((Integer) entities2.get(i)[2]);
            killsForPlayerList.add(killsForPlayer);
        }
        return killsForPlayerList;
    }

    private Kills mapEntityToDto(KillsEntity entity) {
        if (entity == null) {
            return null;
        }
        Kills dto = new Kills();
        dto.setAttacker(entity.getAttackerEntity());
        dto.setVictim(entity.getVictimEntity());
        dto.setCount(entity.getCountEntity());
        return dto;
    }

    private KillsEntity mapDtoToEntity(Kills dto) {
        if (dto == null) {
            return null;
        }
        KillsEntity entity = new KillsEntity();
        entity.setAttackerEntity(dto.getAttacker());
        entity.setVictimEntity(dto.getVictim());
        entity.setCountEntity(dto.getCount());
        return entity;
    }
}