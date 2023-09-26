package lp.be.business.dto;

import lombok.Data;

import java.util.Map;

@Data
public class KillsForTable {

    private String name;
    private Map<String, Integer> enemyKills;
}
