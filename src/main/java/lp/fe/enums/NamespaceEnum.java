package lp.fe.enums;

import lombok.Getter;

@Getter
public enum NamespaceEnum {

    BASIC_STYLE("css/basic-styles.css"),
    TEMPORARY_CSS_FILE("file:///C://temp/temporary.css");

    private final String text;

    NamespaceEnum(String text) {
        this.text = text;
    }
}
