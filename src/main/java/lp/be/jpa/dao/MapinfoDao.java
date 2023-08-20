package lp.be.jpa.dao;

import lp.be.business.dto.Mapinfo;

import java.util.List;

public interface MapinfoDao {

    void saveOrUpdate(Mapinfo mapinfo);

    Mapinfo getMapinfo(int id);

    List<Mapinfo> getAllMapinfo();

    void deleteMapinfo(Mapinfo mapinfo);

    void deleteMapinfo(int id);

}