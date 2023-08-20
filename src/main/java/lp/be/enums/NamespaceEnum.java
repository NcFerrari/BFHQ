package lp.be.enums;

import lombok.Getter;

@Getter
public enum NamespaceEnum {
    NAME();

    private final String text;

    NamespaceEnum() {
        this.text = "name";
    }
}
