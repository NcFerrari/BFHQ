package lp.fe.enums;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import lombok.Getter;
import lp.Manager;

import java.util.HashMap;

@Getter
public enum FXTextEnum {

    APPLICATION_TITLE("Battlefield 2 - Head Quarters", "Battlefield 2 - Informační přehledy");

    private static final ObservableMap<StringProperty, FXTextEnum> COMPONENTS_FOR_TRANSLATE =
            FXCollections.observableMap(new HashMap<>());
    private final Manager manager = Manager.getInstance();
    private final String engText;
    private final String czeText;

    FXTextEnum(String engText, String czeText) {
        this.engText = engText;
        this.czeText = czeText;
    }

    public static void reloadTexts() {
        COMPONENTS_FOR_TRANSLATE.forEach((component, text) -> component.set(text.getSelectedText()));
    }

    public String getText(StringProperty stringProperty) {
        COMPONENTS_FOR_TRANSLATE.put(stringProperty, this);
        return getSelectedText();
    }

    private String getSelectedText() {
        if (manager.getLanguage().equals(LangEnum.ENG)) {
            return getEngText();
        }
        return getCzeText();
    }
}