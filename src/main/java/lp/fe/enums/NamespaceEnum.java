package lp.fe.enums;

import lombok.Getter;

@Getter
public enum NamespaceEnum {

    BASIC_STYLE("css/basic-styles.css"),
    TEMPORARY_CSS_FILE("file:///C://temp/temporary.css"),
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
