package lp.be.jpa.dao;

import lp.be.business.dto.Unlocks;

import java.util.List;

public interface UnlocksDao {

    void saveOrUpdate(Unlocks unlocks);

    Unlocks getUnlocks(int id);

    List<Unlocks> getAllUnlocks();

    void deleteUnlocks(Unlocks unlocks);

    void deleteUnlocks(int id);

}