package lp.be.jpa.daoimpl;

import lp.be.business.dto.Unlocks;
import lp.be.jpa.EntityManager;
import lp.be.jpa.dao.UnlocksDao;
import lp.be.jpa.entity.UnlocksEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UnlocksDaoImpl extends EntityManager implements UnlocksDao {

    @Override
    public void saveOrUpdate(Unlocks unlocks) {
        if (getSession() == null || unlocks == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(unlocks));
        getSession().getTransaction().commit();
    }

    @Override
    public Unlocks getUnlocks(int id) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        UnlocksEntity entity = getSession().get(UnlocksEntity.class, id);
        getSession().getTransaction().commit();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Unlocks> getAllUnlocks() {
        if (getSession() == null) {
            return new ArrayList<>();
        }
        getSession().beginTransaction();
        List<UnlocksEntity> entities = getSession().createQuery("FROM UnlocksEntity").getResultList();
        getSession().getTransaction().commit();
        List<Unlocks> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteUnlocks(Unlocks unlocks) {
        if (getSession() == null || unlocks == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(unlocks));
        getSession().getTransaction().commit();
    }

    @Override
    public void deleteUnlocks(int id) {
        if (getSession() == null) {
            return;
        }
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM UnlocksEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
    }

    private Unlocks mapEntityToDto(UnlocksEntity entity) {
        if (entity == null) {
            return null;
        }
        Unlocks dto = new Unlocks();
        dto.setId(entity.getIdEntity());
        dto.setKit(entity.getKitEntity());
        dto.setState(entity.getStateEntity());
        return dto;
    }

    private UnlocksEntity mapDtoToEntity(Unlocks dto) {
        if (dto == null) {
            return null;
        }
        UnlocksEntity entity = new UnlocksEntity();
        entity.setIdEntity(dto.getId());
        entity.setKitEntity(dto.getKit());
        entity.setStateEntity(dto.getState());
        return entity;
    }
}