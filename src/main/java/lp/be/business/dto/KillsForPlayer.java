package lp.be.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KillsForPlayer {

    private String nameOfKilledPlayer;

    private int countOfKillsAnotherPlayer;

    private String nameOfPlayerWhoKilled;

    private int countOfKillsByAnotherPlayer;

}
