package lp.fe.enums;

import lombok.Getter;

@Getter
public enum NamespaceEnum {

    PANES_STYLE("css/panes.css"),
    BUTTONS_STYLE("css/buttons.css"),
    TABLE_STYLE("css/table.css"),
    TAB_PANE_STYLE("css/tab-pane.css"),
    TEMPORARY_CSS_FILE("file:///C://temp/temporary.css"),
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
