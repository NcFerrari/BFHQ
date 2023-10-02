package lp.fe.enums;

import lombok.Getter;

@Getter
public enum MapInfoEnum {
    MAP_0(0, 1, 3191305),
    MAP_1(0, 1, 3191305),
    MAP_2(0, 1, 3191305),
    MAP_3(0, 1, 3191305),
    MAP_4(0, 1, 3191305),
    MAP_5(0, 1, 3191305),
    MAP_6(0, 1, 3191305),
    MAP_10(9, 1, null),
    MAP_11(9, 1, null),
    MAP_12(0, 1, null),
    MAP_100(0, 2, 3190605),
    MAP_101(0, 2, 3190605),
    MAP_102(0, 2, 3190605),
    MAP_103(0, 2, 3190605),
    MAP_105(0, 2, 3190605),
    MAP_110(9, 2, null),
    MAP_200(0, 2, null),
    MAP_201(0, 1, null),
    MAP_202(0, 1, null),
    MAP_601(0, 2, 3190605),
    MAP_700(0, 1, null);

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
    private final Integer idForRibbon;

    MapInfoEnum(int idFirstTeam, int idSecondTeam, Integer idForRibbon) {
        this.idFirstTeam = idFirstTeam;
        this.idSecondTeam = idSecondTeam;
        this.idForRibbon = idForRibbon;
        firstTeam = factions[idFirstTeam];
        secondTeam = factions[idSecondTeam];
    }
}
