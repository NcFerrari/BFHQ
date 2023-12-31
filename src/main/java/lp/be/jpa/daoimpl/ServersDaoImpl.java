package lp.be.jpa.daoimpl;

import lp.be.business.dto.Servers;
import lp.be.jpa.EntityManager;
import lp.be.jpa.dao.ServersDao;
import lp.be.jpa.entity.ServersEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ServersDaoImpl extends EntityManager implements ServersDao {

    @Override
    public void saveOrUpdate(Servers servers) {
        if (getSession() == null || servers == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().saveOrUpdate(mapDtoToEntity(servers));
        getSession().getTransaction().commit();
    }

    @Override
    public Servers getServers(int id) {
        if (getSession() == null) {
            return null;
        }
        getSession().beginTransaction();
        ServersEntity entity = getSession().get(ServersEntity.class, id);
        getSession().getTransaction().commit();
        return mapEntityToDto(entity);
    }

    @Override
    public List<Servers> getAllServers() {
        if (getSession() == null) {
            return new ArrayList<>();
        }
        getSession().beginTransaction();
        List<ServersEntity> entities = getSession().createQuery("FROM ServersEntity").getResultList();
        getSession().getTransaction().commit();
        List<Servers> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(mapEntityToDto(entity)));
        return dtos;
    }

    @Override
    public void deleteServers(Servers servers) {
        if (getSession() == null || servers == null) {
            return;
        }
        getSession().beginTransaction();
        getSession().delete(mapDtoToEntity(servers));
        getSession().getTransaction().commit();
    }

    @Override
    public void deleteServers(int id) {
        if (getSession() == null) {
            return;
        }
        getSession().beginTransaction();
        Query<?> query = getSession().createQuery("DELETE FROM ServersEntity WHERE id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        getSession().getTransaction().commit();
    }

    private Servers mapEntityToDto(ServersEntity entity) {
        if (entity == null) {
            return null;
        }
        Servers dto = new Servers();
        dto.setId(entity.getIdEntity());
        dto.setIp(entity.getIpEntity());
        dto.setPrefix(entity.getPrefixEntity());
        dto.setName(entity.getNameEntity());
        dto.setPort(entity.getPortEntity());
        dto.setQueryport(entity.getQueryportEntity());
        dto.setLastupdate(entity.getLastupdateEntity());
        return dto;
    }

    private ServersEntity mapDtoToEntity(Servers dto) {
        if (dto == null) {
            return null;
        }
        ServersEntity entity = new ServersEntity();
        entity.setIdEntity(dto.getId());
        entity.setIpEntity(dto.getIp());
        entity.setPrefixEntity(dto.getPrefix());
        entity.setNameEntity(dto.getName());
        entity.setPortEntity(dto.getPort());
        entity.setQueryportEntity(dto.getQueryport());
        entity.setLastupdateEntity(dto.getLastupdate());
        return entity;
    }
}