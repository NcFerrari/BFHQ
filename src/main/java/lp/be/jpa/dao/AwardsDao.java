package lp.be.jpa.dao;

import lp.be.business.dto.Awards;

public interface AwardsDao {

    void saveOrUpdate(Awards awards);

    Awards getAwards(int id);

    void deleteAwards(Awards awards);

    void deleteAwards(int id);

}