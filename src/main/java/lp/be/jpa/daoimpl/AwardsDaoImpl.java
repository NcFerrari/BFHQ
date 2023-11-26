package lp.be.jpa.daoimpl;

import lp.be.business.dto.Awards;
import lp.be.jpa.EntityManager;
import lp.be.jpa.dao.AwardsDao;
import lp.be.jpa.entity.AwardsEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AwardsDaoImpl extends EntityManager implements AwardsDao {

    @Override
    public void saveOrUpdate(Awards awards) {
        if (getSession() == null || awards == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(awards));
        getSession().getTransaction().commit();
    }

    @Override
    public Awards getAwards(int id) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        AwardsEntity entity = getSession().get(AwardsEntity.class, id);
        getSession().getTransaction().commit();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Awards> getAllAwards() {
        if (getSession() == null) {
            return new ArrayList<>();
        }
        getSession().beginTransaction();
        List<Object[]> entities = getSession().createNativeQuery("SELECT * FROM awards ORDER BY earned DESC").list();
        getSession().getTransaction().commit();
        List<Awards> dtos = new ArrayList<>();
        entities.forEach(entityObject -> {
            Awards dtoFromObject = new Awards();
            dtoFromObject.setId(Integer.valueOf("" + entityObject[0]));
            dtoFromObject.setAwd(Integer.valueOf("" + entityObject[1]));
            dtoFromObject.setLevel(Long.valueOf("" + entityObject[2]));
            dtoFromObject.setEarned(Long.valueOf("" + entityObject[3]));
            dtoFromObject.setFirst(Long.valueOf("" + entityObject[4]));
            dtos.add(dtoFromObject);
        });
        return dtos;
    }

    @Override
    public void deleteAwards(Awards awards) {
        if (getSession() == null || awards == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(awards));
        getSession().getTransaction().commit();
    }

    @Override
    public void deleteAwards(int id) {
        if (getSession() == null) {
            return;
        }
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM AwardsEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
    }

    private Awards mapEntityToDto(AwardsEntity entity) {
        if (entity == null) {
            return null;
        }
        Awards dto = new Awards();
        dto.setId(entity.getIdEntity());
        dto.setAwd(entity.getAwdEntity());
        dto.setLevel(entity.getLevelEntity());
        dto.setEarned(entity.getEarnedEntity());
        dto.setFirst(entity.getFirstEntity());
        return dto;
    }

    private AwardsEntity mapDtoToEntity(Awards dto) {
        if (dto == null) {
            return null;
        }
        AwardsEntity entity = new AwardsEntity();
        entity.setIdEntity(dto.getId());
        entity.setAwdEntity(dto.getAwd());
        entity.setLevelEntity(dto.getLevel());
        entity.setEarnedEntity(dto.getEarned());
        entity.setFirstEntity(dto.getFirst());
        return entity;
    }
}