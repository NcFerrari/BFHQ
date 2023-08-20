package lp.be.jpa.dao;

import lp.be.business.dto.Kits;

import java.util.List;

public interface KitsDao {

    void saveOrUpdate(Kits kits);

    Kits getKits(int id);

    List<Kits> getAllKits();

    void deleteKits(Kits kits);

    void deleteKits(int id);

}