package lp.be.jpa.daoimpl;

import lp.be.business.dto.Player;
import lp.be.enums.NamespaceEnum;
import lp.be.jpa.EntityManager;
import lp.be.jpa.dao.PlayerDao;
import lp.be.jpa.entity.PlayerEntity;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PlayerDaoImpl extends EntityManager implements PlayerDao {

    private final KitsDaoImpl kitsDaoImpl = new KitsDaoImpl();
    private final VehiclesDaoImpl vehiclesDaoImpl = new VehiclesDaoImpl();
    private final WeaponsDaoImpl weaponsDaoImpl = new WeaponsDaoImpl();
    private final ArmyDaoImpl armyDaoImpl = new ArmyDaoImpl();

    @Override
    public void saveOrUpdate(Player player) {
        if (getSession() == null || player == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(player));
        getSession().getTransaction().commit();
    }

    @Override
    public Player getPlayer(String name) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        NativeQuery<PlayerEntity> query = getSession().createNativeQuery("SELECT * " +
                        "FROM Player " +
                        "WHERE name=:name",
                PlayerEntity.class);
        query.setParameter(NamespaceEnum.NAME.getText(), name);
        PlayerEntity playerEntity = query.getSingleResult();
        getSession().getTransaction().commit();
        return mapEntityToDto(playerEntity);
    }

    @Override
    public Player getPlayer(int id) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        PlayerEntity entity = getSession().get(PlayerEntity.class, id);
        getSession().getTransaction().commit();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Player> getAllPlayer() {
        if (getSession() == null) {
            return new ArrayList<>();
        }
        getSession().beginTransaction();
        List<PlayerEntity> entities = getSession().createQuery("FROM PlayerEntity").getResultList();
        entities.forEach(player -> {
            long time = player.getCmdtimeEntity() + player.getSqltimeEntity() + player.getSqmtimeEntity();
            long lwTime = player.getTimeEntity() - time;
            if (lwTime < 0) {
                player.setSqmtimeEntity(player.getSqmtimeEntity() - 2 * Math.abs(lwTime));
            }
            player.setLwtimeEntity(Math.abs(lwTime));
            getSession().update(player);
        });
        getSession().getTransaction().commit();
        List<Player> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deletePlayer(Player player) {
        if (getSession() == null || player == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(player));
        getSession().getTransaction().commit();
    }

    @Override
    public void deletePlayer(int id) {
        if (getSession() == null) {
            return;
        }
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM PlayerEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
    }

    private Player mapEntityToDto(PlayerEntity entity) {
        if (entity == null) {
            return null;
        }
        Player dto = new Player();
        dto.setId(entity.getIdEntity());
        dto.setName(entity.getNameEntity());
        dto.setCountry(entity.getCountryEntity());
        dto.setTime(entity.getTimeEntity());
        dto.setRounds(entity.getRoundsEntity());
        dto.setIp(entity.getIpEntity());
        dto.setScore(entity.getScoreEntity());
        dto.setCmdscore(entity.getCmdscoreEntity());
        dto.setSkillscore(entity.getSkillscoreEntity());
        dto.setTeamscore(entity.getTeamscoreEntity());
        dto.setKills(entity.getKillsEntity());
        dto.setDeaths(entity.getDeathsEntity());
        dto.setCaptures(entity.getCapturesEntity());
        dto.setNeutralizes(entity.getNeutralizesEntity());
        dto.setCaptureassists(entity.getCaptureassistsEntity());
        dto.setNeutralizeassists(entity.getNeutralizeassistsEntity());
        dto.setDefends(entity.getDefendsEntity());
        dto.setDamageassists(entity.getDamageassistsEntity());
        dto.setHeals(entity.getHealsEntity());
        dto.setRevives(entity.getRevivesEntity());
        dto.setAmmos(entity.getAmmosEntity());
        dto.setRepairs(entity.getRepairsEntity());
        dto.setTargetassists(entity.getTargetassistsEntity());
        dto.setDriverspecials(entity.getDriverspecialsEntity());
        dto.setDriverassists(entity.getDriverassistsEntity());
        dto.setPassengerassists(entity.getPassengerassistsEntity());
        dto.setTeamkills(entity.getTeamkillsEntity());
        dto.setTeamdamage(entity.getTeamdamageEntity());
        dto.setTeamvehicledamage(entity.getTeamvehicledamageEntity());
        dto.setSuicides(entity.getSuicidesEntity());
        dto.setKillstreak(entity.getKillstreakEntity());
        dto.setDeathstreak(entity.getDeathstreakEntity());
        dto.setRank(entity.getRankEntity());
        dto.setBanned(entity.getBannedEntity());
        dto.setKicked(entity.getKickedEntity());
        dto.setCmdtime(entity.getCmdtimeEntity());
        dto.setSqltime(entity.getSqltimeEntity());
        dto.setSqmtime(entity.getSqmtimeEntity());
        dto.setLwtime(entity.getLwtimeEntity());
        dto.setWins(entity.getWinsEntity());
        dto.setLosses(entity.getLossesEntity());
        dto.setAvailunlocks(entity.getAvailunlocksEntity());
        dto.setUsedunlocks(entity.getUsedunlocksEntity());
        dto.setJoined(entity.getJoinedEntity());
        dto.setRndscore(entity.getRndscoreEntity());
        dto.setLastonline(entity.getLastonlineEntity());
        dto.setChng(entity.getChngEntity());
        dto.setDecr(entity.getDecrEntity());
        dto.setMode0(entity.getMode0Entity());
        dto.setMode1(entity.getMode1Entity());
        dto.setMode2(entity.getMode2Entity());
        dto.setPermban(entity.getPermbanEntity());
        dto.setClantag(entity.getClantagEntity());
        dto.setKits(kitsDaoImpl.mapEntityToDto(entity.getKitsEntity()));
        dto.setVehicles(vehiclesDaoImpl.mapEntityToDto(entity.getVehiclesEntity()));
        dto.setWeapons(weaponsDaoImpl.mapEntityToDto(entity.getWeaponsEntity()));
        dto.setArmy(armyDaoImpl.mapEntityToDto(entity.getArmyEntity()));
        return dto;
    }

    private PlayerEntity mapDtoToEntity(Player dto) {
        if (dto == null) {
            return null;
        }
        PlayerEntity entity = new PlayerEntity();
        entity.setIdEntity(dto.getId());
        entity.setNameEntity(dto.getName());
        entity.setCountryEntity(dto.getCountry());
        entity.setTimeEntity(dto.getTime());
        entity.setRoundsEntity(dto.getRounds());
        entity.setIpEntity(dto.getIp());
        entity.setScoreEntity(dto.getScore());
        entity.setCmdscoreEntity(dto.getCmdscore());
        entity.setSkillscoreEntity(dto.getSkillscore());
        entity.setTeamscoreEntity(dto.getTeamscore());
        entity.setKillsEntity(dto.getKills());
        entity.setDeathsEntity(dto.getDeaths());
        entity.setCapturesEntity(dto.getCaptures());
        entity.setNeutralizesEntity(dto.getNeutralizes());
        entity.setCaptureassistsEntity(dto.getCaptureassists());
        entity.setNeutralizeassistsEntity(dto.getNeutralizeassists());
        entity.setDefendsEntity(dto.getDefends());
        entity.setDamageassistsEntity(dto.getDamageassists());
        entity.setHealsEntity(dto.getHeals());
        entity.setRevivesEntity(dto.getRevives());
        entity.setAmmosEntity(dto.getAmmos());
        entity.setRepairsEntity(dto.getRepairs());
        entity.setTargetassistsEntity(dto.getTargetassists());
        entity.setDriverspecialsEntity(dto.getDriverspecials());
        entity.setDriverassistsEntity(dto.getDriverassists());
        entity.setPassengerassistsEntity(dto.getPassengerassists());
        entity.setTeamkillsEntity(dto.getTeamkills());
        entity.setTeamdamageEntity(dto.getTeamdamage());
        entity.setTeamvehicledamageEntity(dto.getTeamvehicledamage());
        entity.setSuicidesEntity(dto.getSuicides());
        entity.setKillstreakEntity(dto.getKillstreak());
        entity.setDeathstreakEntity(dto.getDeathstreak());
        entity.setRankEntity(dto.getRank());
        entity.setBannedEntity(dto.getBanned());
        entity.setKickedEntity(dto.getKicked());
        entity.setCmdtimeEntity(dto.getCmdtime());
        entity.setSqltimeEntity(dto.getSqltime());
        entity.setSqmtimeEntity(dto.getSqmtime());
        entity.setLwtimeEntity(dto.getLwtime());
        entity.setWinsEntity(dto.getWins());
        entity.setLossesEntity(dto.getLosses());
        entity.setAvailunlocksEntity(dto.getAvailunlocks());
        entity.setUsedunlocksEntity(dto.getUsedunlocks());
        entity.setJoinedEntity(dto.getJoined());
        entity.setRndscoreEntity(dto.getRndscore());
        entity.setLastonlineEntity(dto.getLastonline());
        entity.setChngEntity(dto.getChng());
        entity.setDecrEntity(dto.getDecr());
        entity.setMode0Entity(dto.getMode0());
        entity.setMode1Entity(dto.getMode1());
        entity.setMode2Entity(dto.getMode2());
        entity.setPermbanEntity(dto.getPermban());
        entity.setClantagEntity(dto.getClantag());
        entity.setKitsEntity(kitsDaoImpl.mapDtoToEntity(dto.getKits()));
        entity.setVehiclesEntity(vehiclesDaoImpl.mapDtoToEntity(dto.getVehicles()));
        entity.setWeaponsEntity(weaponsDaoImpl.mapDtoToEntity(dto.getWeapons()));
        entity.setArmyEntity(armyDaoImpl.mapDtoToEntity(dto.getArmy()));
        return entity;
    }
}