package lp.be.jpa.dao;

import lp.be.business.dto.Army;

import java.util.List;

public interface ArmyDao {

    void saveOrUpdate(Army army);

    Army getArmy(int id);

    List<Army> getAllArmy();

    void deleteArmy(Army army);

    void deleteArmy(int id);

}