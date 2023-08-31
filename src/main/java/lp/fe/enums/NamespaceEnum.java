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
    TABLE_STYLE("css/table.css"),
    TAB_PANE_STYLE("css/tab-pane.css"),
    RIGHT_SIDE_STYLE("css/right-side-part.css"),
    TEMPORARY_CSS_FILE("file:///C://temp/temporary.css"),
    DATE_FORMAT("yyyy-MM-dd"),
    HOUR_FORMAT("HH"),
    LOG_PATTERN("[%d] %-8p (%-25c): %m%n"),
    LOG_FILE_FORMAT("logs/%s/%s.log"),
    TWO_DIGITS_FORMAT("%.2f%%"),
    LANG_COMBO_BOX_STYLE("lang-combo-box"),
    TITLE_STYLE("title"),
    LEFT_SIDE_PART_STYLE("left-side-part"),
    RIGHT_SIDE_PART_STYLE("right-side-part"),
    NAME_COMBO_BOX_STYLE("name-combo-box"),
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
    PREF_WIDTH_STYLE("-fx-pref-width: "),
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
    DIALOG_BUTTON_YES_EN("Yes"),
    DIALOG_BUTTON_NO_EN("No"),
    DIALOG_BUTTON_YES_CZ("Ano"),
    DIALOG_BUTTON_NO_CZ("Ne"),
    LEVEL_ONE("LEVEL_1_"),
    LEVEL_TWO("LEVEL_2_"),
    LEVEL_THREE("LEVEL_3_"),
    MEDAL_PREFIX("MEDAL_"),
    RANK_PREFIX("RANK_"),
    RIBBON_PREFIX("RIBBON_"),
    KIT("KIT_"),
    VEHICLE("VEHICLE_"),
    WEAPON("WEAPON_"),
    MAP("MAP_"),
    GET_TIME("getTime"),
    GET_WIN("getWin"),
    GET_LOSS("getLoss");

    private final String text;

    NamespaceEnum(String text) {
        this.text = text;
    }
}
