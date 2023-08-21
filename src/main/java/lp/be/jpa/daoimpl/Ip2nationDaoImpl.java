package lp.be.jpa.daoimpl;

import lp.be.business.dto.Ip2nation;
import lp.be.jpa.EntityManager;
import lp.be.jpa.dao.Ip2nationDao;
import lp.be.jpa.entity.Ip2nationEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Ip2nationDaoImpl extends EntityManager implements Ip2nationDao {

    @Override
    public void saveOrUpdate(Ip2nation ip2nation) {
        if (getSession() == null || ip2nation == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(ip2nation));
        getSession().getTransaction().commit();
    }

    @Override
    public Ip2nation getIp2nation(int id) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        Ip2nationEntity entity = getSession().get(Ip2nationEntity.class, id);
        getSession().getTransaction().commit();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Ip2nation> getAllIp2nation() {
        if (getSession() == null) {
            return new ArrayList<>();
        }
        getSession().beginTransaction();
        List<Ip2nationEntity> entities = getSession().createQuery("FROM Ip2nationEntity").getResultList();
        getSession().getTransaction().commit();
        List<Ip2nation> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteIp2nation(Ip2nation ip2nation) {
        if (getSession() == null || ip2nation == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(ip2nation));
        getSession().getTransaction().commit();
    }

    @Override
    public void deleteIp2nation(int id) {
        if (getSession() == null) {
            return;
        }
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM Ip2nationEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
    }

    private Ip2nation mapEntityToDto(Ip2nationEntity entity) {
        if (entity == null) {
            return null;
        }
        Ip2nation dto = new Ip2nation();
        dto.setIp(entity.getIpEntity());
        dto.setCountry(entity.getCountryEntity());
        return dto;
    }

    private Ip2nationEntity mapDtoToEntity(Ip2nation dto) {
        if (dto == null) {
            return null;
        }
        Ip2nationEntity entity = new Ip2nationEntity();
        entity.setIpEntity(dto.getIp());
        entity.setCountryEntity(dto.getCountry());
        return entity;
    }
}