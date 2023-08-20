package lp.be.jpa.dao;

import lp.be.business.dto.RoundHistory;

import java.util.List;

public interface RoundHistoryDao {

    void saveOrUpdate(RoundHistory roundHistory);

    RoundHistory getRoundHistory(int id);

    List<RoundHistory> getAllRoundHistory();

    void deleteRoundHistory(RoundHistory roundHistory);

    void deleteRoundHistory(int id);

}