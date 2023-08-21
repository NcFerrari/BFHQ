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
    TEMPORARY_CSS_FILE("file:///C://temp/temporary.css"),
    DATE_FORMAT("yyyy-MM-dd"),
    HOUR_FORMAT("HH"),
    LOG_PATTERN("[%d] %-8p (%-25c): %m%n"),
    LOG_FILE_FORMAT("logs/%s/%s.log"),
    LANG_COMBO_BOX_STYLE("lang-combo-box"),
    TITLE_STYLE("title"),
    LEFT_SIDE_PART_STYLE("left-side-part"),
    NAME_COMBO_BOX_STYLE("name-combo-box"),
    EMPTY_STRING(""),
    DIALOG_BUTTON_YES_EN("Yes"),
    DIALOG_BUTTON_NO_EN("No"),
    DIALOG_BUTTON_YES_CZ("Ano"),
    DIALOG_BUTTON_NO_CZ("Ne");

    private final String text;

    NamespaceEnum(String text) {
        this.text = text;
    }
}
