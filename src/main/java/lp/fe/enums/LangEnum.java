package lp.fe.enums;

import lombok.Getter;

@Getter
public enum LangEnum {
    ENG("Anglicky"),
    CZE("Česky");

    private final String lang;

    LangEnum(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return getLang();
    }
}
