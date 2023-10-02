package lp.fe.enums;

import lombok.Getter;

@Getter
public enum MapInfoEnum {
    MAP_0(0, 1),
    MAP_1(1, 2),
    MAP_2(2, 9),
    MAP_3(9, 1),
    MAP_4(1, 0),
    MAP_5(9, 0),
    MAP_6(0, 1),
    MAP_10(0, 2),
    MAP_11(1, 0),
    MAP_12(2, 9),
    MAP_100(0, 1),
    MAP_101(2, 1),
    MAP_102(0, 2),
    MAP_103(9, 1),
    MAP_105(9, 0),
    MAP_110(1, 0),
    MAP_200(1, 2),
    MAP_201(0, 9),
    MAP_202(2, 0),
    MAP_601(9, 1),
    MAP_700(1, 2);

    private final NodeTextEnum[] factions = {
            NodeTextEnum.FACTION_USMC,
            NodeTextEnum.FACTION_MEC,
            NodeTextEnum.FACTION_PLA,
            null,
            null,
            null,
            null,
            null,
            null,
            NodeTextEnum.FACTION_EU};
    private final int idFirstTeam;
    private final int idSecondTeam;
    private final NodeTextEnum firstTeam;
    private final NodeTextEnum secondTeam;

    MapInfoEnum(int idFirstTeam, int idSecondTeam) {
        this.idFirstTeam = idFirstTeam;
        this.idSecondTeam = idSecondTeam;
        firstTeam = factions[idFirstTeam];
        secondTeam = factions[idSecondTeam];
    }
}
