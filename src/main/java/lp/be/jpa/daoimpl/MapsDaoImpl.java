package lp.be.jpa.daoimpl;

import lp.be.business.dto.Maps;
import lp.be.jpa.EntityManager;
import lp.be.jpa.dao.MapsDao;
import lp.be.jpa.entity.MapsEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MapsDaoImpl extends EntityManager implements MapsDao {

    @Override
    public void saveOrUpdate(Maps maps) {
        if (getSession() == null || maps == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(maps));
        getSession().getTransaction().commit();
    }

    @Override
    public Maps getMaps(int id) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        MapsEntity entity = getSession().get(MapsEntity.class, id);
        getSession().getTransaction().commit();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Maps> getAllMaps() {
        if (getSession() == null) {
            return new ArrayList<>();
        }
        getSession().beginTransaction();
        List<Object[]> entities = getSession().createNativeQuery("SELECT * FROM maps").getResultList();
        getSession().getTransaction().commit();
        List<Maps> dtos = new ArrayList<>();
        entities.forEach(entityObject -> {
            Maps dtoFromObject = new Maps();
            dtoFromObject.setId(Integer.valueOf("" + entityObject[0]));
            dtoFromObject.setMapid(Short.valueOf("" + entityObject[1]));
            dtoFromObject.setTime(Long.valueOf("" + entityObject[2]));
            dtoFromObject.setWin(Long.valueOf("" + entityObject[3]));
            dtoFromObject.setLoss(Long.valueOf("" + entityObject[4]));
            dtoFromObject.setBest(Long.valueOf("" + entityObject[5]));
            dtoFromObject.setWorst(Long.valueOf("" + entityObject[6]));
            dtos.add(dtoFromObject);
        });
        return dtos;
    }

    @Override
    public void deleteMaps(Maps maps) {
        if (getSession() == null || maps == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(maps));
        getSession().getTransaction().commit();
    }

    @Override
    public void deleteMaps(int id) {
        if (getSession() == null) {
            return;
        }
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM MapsEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
    }

    @Override
    public List<Maps> getAllMapsById(Integer playerId) {
        if (getSession() == null) {
            return new ArrayList<>();
        }
        getSession().beginTransaction();
        List<Object[]> entities = getSession().createNativeQuery("" +
                "SELECT * " +
                "FROM maps " +
                "WHERE id=:id ").setParameter("id", playerId).list();
        getSession().getTransaction().commit();
        List<Maps> dtos = new ArrayList<>();
        entities.forEach(entityObject -> {
            Maps dtoFromObject = new Maps();
            dtoFromObject.setId(Integer.valueOf("" + entityObject[0]));
            dtoFromObject.setMapid(Short.valueOf("" + entityObject[1]));
            dtoFromObject.setTime(Long.valueOf("" + entityObject[2]));
            dtoFromObject.setWin(Long.valueOf("" + entityObject[3]));
            dtoFromObject.setLoss(Long.valueOf("" + entityObject[4]));
            dtoFromObject.setBest(Long.valueOf("" + entityObject[5]));
            dtoFromObject.setWorst(Long.valueOf("" + entityObject[6]));
            dtos.add(dtoFromObject);
        });
        return dtos;
    }

    private Maps mapEntityToDto(MapsEntity entity) {
        if (entity == null) {
            return null;
        }
        Maps dto = new Maps();
        dto.setId(entity.getIdEntity());
        dto.setMapid(entity.getMapidEntity());
        dto.setTime(entity.getTimeEntity());
        dto.setWin(entity.getWinEntity());
        dto.setLoss(entity.getLossEntity());
        dto.setBest(entity.getBestEntity());
        dto.setWorst(entity.getWorstEntity());
        return dto;
    }

    private MapsEntity mapDtoToEntity(Maps dto) {
        if (dto == null) {
            return null;
        }
        MapsEntity entity = new MapsEntity();
        entity.setIdEntity(dto.getId());
        entity.setMapidEntity(dto.getMapid());
        entity.setTimeEntity(dto.getTime());
        entity.setWinEntity(dto.getWin());
        entity.setLossEntity(dto.getLoss());
        entity.setBestEntity(dto.getBest());
        entity.setWorstEntity(dto.getWorst());
        return entity;
    }
}