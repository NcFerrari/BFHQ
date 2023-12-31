package lp.be.jpa.daoimpl;

import lp.be.business.dto.RoundHistory;
import lp.be.jpa.EntityManager;
import lp.be.jpa.dao.RoundHistoryDao;
import lp.be.jpa.entity.RoundHistoryEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RoundHistoryDaoImpl extends EntityManager implements RoundHistoryDao {

    @Override
    public void saveOrUpdate(RoundHistory roundHistory) {
        if (getSession() == null || roundHistory == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(roundHistory));
        getSession().getTransaction().commit();
    }

    @Override
    public RoundHistory getRoundHistory(int id) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        RoundHistoryEntity entity = getSession().get(RoundHistoryEntity.class, id);
        getSession().getTransaction().commit();
        return mapEntityToDto(entity);
    }

    @Override
    public List<RoundHistory> getAllRoundHistory() {
        if (getSession() == null) {
            return new ArrayList<>();
        }
        getSession().beginTransaction();
        List<RoundHistoryEntity> entities = getSession().createQuery("FROM RoundHistoryEntity").getResultList();
        getSession().getTransaction().commit();
        List<RoundHistory> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteRoundHistory(RoundHistory roundHistory) {
        if (getSession() == null || roundHistory == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(roundHistory));
        getSession().getTransaction().commit();
    }

    @Override
    public void deleteRoundHistory(int id) {
        if (getSession() == null) {
            return;
        }
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM RoundHistoryEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
    }

    private RoundHistory mapEntityToDto(RoundHistoryEntity entity) {
        if (entity == null) {
            return null;
        }
        RoundHistory dto = new RoundHistory();
        dto.setId(entity.getIdEntity());
        dto.setTimestamp(entity.getTimestampEntity());
        dto.setMapid(entity.getMapidEntity());
        dto.setTime(entity.getTimeEntity());
        dto.setTeam1(entity.getTeam1Entity());
        dto.setTeam2(entity.getTeam2Entity());
        dto.setTickets1(entity.getTickets1Entity());
        dto.setTickets2(entity.getTickets2Entity());
        dto.setPids1(entity.getPids1Entity());
        dto.setPids1End(entity.getPids1EndEntity());
        dto.setPids2(entity.getPids2Entity());
        dto.setPids2End(entity.getPids2EndEntity());
        return dto;
    }

    private RoundHistoryEntity mapDtoToEntity(RoundHistory dto) {
        if (dto == null) {
            return null;
        }
        RoundHistoryEntity entity = new RoundHistoryEntity();
        entity.setIdEntity(dto.getId());
        entity.setTimestampEntity(dto.getTimestamp());
        entity.setMapidEntity(dto.getMapid());
        entity.setTimeEntity(dto.getTime());
        entity.setTeam1Entity(dto.getTeam1());
        entity.setTeam2Entity(dto.getTeam2());
        entity.setTickets1Entity(dto.getTickets1());
        entity.setTickets2Entity(dto.getTickets2());
        entity.setPids1Entity(dto.getPids1());
        entity.setPids1EndEntity(dto.getPids1End());
        entity.setPids2Entity(dto.getPids2());
        entity.setPids2EndEntity(dto.getPids2End());
        return entity;
    }
}