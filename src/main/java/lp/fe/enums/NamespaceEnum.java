package lp.fe.enums;

import lombok.Getter;

@Getter
public enum NamespaceEnum {

    HIBERNATE_CONFIG("hibernate.cfg.xml"),
    HIBERNATE_CONNECTION_URL("hibernate.connection.url"),
    JDBC("jdbc:mysql://localhost:3306/bf2stats"),
    CANNOT_LOG_DB("Cannot log to database"),
    CALL_COUNT("Count of call: %d"),
    PANES_STYLE("css/panes.css"),
    BUTTONS_STYLE("css/buttons.css"),
    TAB_PANE_STYLE("css/tab-pane.css"),
    RIGHT_SIDE_STYLE("css/right-side-part.css"),
    DATE_FORMAT("yyyy-MM-dd"),
    CZE_DATE_FORMAT("dd. MMMM. yyyy"),
    HOUR_FORMAT("HH"),
    LOG_PATTERN("[%d] %-8p (%-25c): %m%n"),
    LOG_FILE_FORMAT("logs/%s/%s.log"),
    TWO_DIGITS_FORMAT("%.2f%%"),
    NUMBER_REGEX("\\d"),
    LANG_COMBO_BOX_STYLE("lang-combo-box"),
    TITLE_STYLE("title"),
    LEFT_SIDE_PART_STYLE("left-side-part"),
    RIGHT_SIDE_PART_STYLE("right-side-part"),
    PROGRESS_BAR_STYLE("progress-bar"),
    BORDER_LIGHT_STYLE("border-light"),
    BORDER_DARK_STYLE("border-dark"),
    SUB_TITLE_STYLE("sub-title"),
    DB_VALUE_STYLE("db-value"),
    VALUE_STYLE("value"),
    LAST_THREE_AWARDS_PANE_STYLE("last-three-awards"),
    FIRST_COLUMN_MOST_PLAYED_STYLE("first-column-most-played "),
    FIRST_COLUMN_STYLE("first-column"),
    CENTER_RIGHT_TABLE_STYLE("center-right"),
    CENTER_RIGHT_TABLE("center-right"),
    ID_UP_TAB_PANE("up-tab-pane"),
    ID_DOWN_TAB_PANE("down-tab-pane"),
    PREF_WIDTH_STYLE("-fx-pref-width: %f;-fx-font-size: %f;"),
    FONT_SIZE_STYLE("-fx-font-size: %f"),
    DATE_STYLE("date-label"),
    BIG_IMAGE_STYLE("big-image"),
    BIG_IMAGE_LABEL_STYLE("big-image-label"),
    CHECK_BOX_STYLE("check-box"),
    CHECK_BOX_LIGHT_STYLE("check-box-light"),
    INNER_TAB_PANE_STYLE("inner-tab-pane"),
    LEFT_PANE_MAIN_PANE_STYLE("left_pane_main_pane"),
    CROSS_TABLE_STYLE("cross-table"),
    X_MARK_STYLE("x-style"),
    MAP_TITLE_STYLE("map-title"),
    DAYS_LETTER("d"),
    HOURS_LETTER("h"),
    MINUTES_LETTER("min"),
    SECONDS_LETTER("s"),
    EMPTY_STRING(""),
    SPACE_STRING(" "),
    ZERO("0"),
    X_MARK("x"),
    TEXT_POINT("."),
    COLON(":"),
    UNDERSCORE("_"),
    LEFT_BRACKET("("),
    RIGHT_BRACKET(")"),
    ONE("1"),
    DIALOG_BUTTON_YES_EN("Yes"),
    DIALOG_BUTTON_NO_EN("No"),
    DIALOG_BUTTON_YES_CZ("Ano"),
    DIALOG_BUTTON_NO_CZ("Ne"),
    BADGE_ONE("BADGE_1_"),
    BADGE_TWO("BADGE_2_"),
    BADGE_THREE("BADGE_3_"),
    MEDAL_PREFIX("MEDAL_"),
    RANK_PREFIX("RANK_"),
    RIBBON_PREFIX("RIBBON_"),
    KIT("KIT_"),
    VEHICLE("VEHICLE_"),
    WEAPON("WEAPON_"),
    MAP("MAP_"),
    GET_TIME("getTime"),
    GET_WIN("getWin"),
    GET_LOSS("getLoss"),
    GET_KILLS("getKills"),
    GET_DEATHS("getDeaths"),
    GET_HIT("getHit"),
    GET_FIRED("getFired"),
    GET_RK("getRk"),
    TITLE_TEXT("title"),
    STARS_COUNTER("starsCount"),
    LAST_EARNED("lastEarned"),
    LAST_EARNED_DATE("lastEarnedDate"),
    FIRST_EARNED("firstEarned"),
    FIRST_EARNED_DATE("firstEarnedDate"),
    TOOLTIP("TOOLTIP_"),
    ORDER("order"),
    NAME("name"),
    RANK("rankImage"),
    VALUE("value"),
    SCORE("score"),
    GET_RANK("getRank"),
    GET_COUNT_OF_AWARDS("getCountOfAwards"),
    GET_SCORE("getScore"),
    GET_WINS("getWins"),
    GET_LOSSES("getLosses"),
    GET_TEAM_KILLING("getTeamKilling"),
    GET_TEAM_PLAYER_SCORE("getTeamPlayerScore"),
    GET_KILL_STREAK("getKillStreak"),
    SORT_BY("SORT_BY_"),
    GET_PREFIX("GET_"),
    NAME_OF_KILLED_PLAYER("nameOfKilledPlayer"),
    COUNT_OF_KILLS_ANOTHER_PLAYER("countOfKillsAnotherPlayer"),
    NAME_OF_PLAYER_WHO_KILLED("nameOfPlayerWhoKilled"),
    COUNT_OF_KILLS_BY_ANOTHER_PLAYER("countOfKillsByAnotherPlayer"),
    FOR_RIBBON("TEXT_FOR_RIBBON_");

    private final String text;

    NamespaceEnum(String text) {
        this.text = text;
    }
}
