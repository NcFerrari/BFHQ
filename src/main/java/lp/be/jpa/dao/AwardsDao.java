package lp.be.jpa.dao;

import lp.be.business.dto.Awards;

import java.util.List;

public interface AwardsDao {

    void saveOrUpdate(Awards awards);

    Awards getAwards(int id);

    List<Awards> getAllAwards();

    void deleteAwards(Awards awards);

    void deleteAwards(int id);

}