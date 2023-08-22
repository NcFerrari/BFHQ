package lp.fe.enums;

import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import lombok.Getter;
import lp.Manager;

import java.util.HashMap;

@Getter
public enum NodeTextEnum {

    APPLICATION_TITLE("Battlefield 2 - Head Quarters", "Battlefield 2 - Informační přehledy"),
    DIALOG_TEXT_WINDOW_CLOSE_TITLE("Close application", "Ukončení aplikace"),
    DIALOG_TEXT_WINDOW_CLOSE("Do you want to exit application?", "Přejete si ukončit program?"),
    UPDATE_DATA_BUTTON("Update Data", "Aktualizovat Data"),
    PLAYER_NAME_TITLE("PLAYER NAME", "JMÉNO HRÁČE"),
    TAB_MENU_STATS("STATS", "STATISTIKY"),
    TAB_MENU_LEADERBOARDS("LEADERBOARDS", "ŽEBŘÍČKY"),
    TAB_MENU_AWARDS("AWARDS", "ODMĚNY"),
    RANK_TITLE("RANK", "HODNOST"),
    CURRENT_RANK("Current rank", "Aktuální hodnost"),
    EMPTY_STRING("", ""),
    NEXT_RANK("Next rank", "Následující hodnost"),
    PROGRESS_TOWARDS_NEXT_RANK("Progress towards next rank", "Postup pro další hodnost"),
    PERSONAL_INFO("PERSONAL INFO", "OSOBNÍ INFORMACE"),
    GLOBAL_SCORE("Global score", "Globální skóre"),
    TIME("Total time", "Celkový čas"),
    KILLS("Kills", "Počet zabití"),
    DEATHS("Deaths", "Počet smrtí"),
    TEAM_KILLS("Team kills", "Zabití kamaráda"),
    WINS("Wins", "Výhry"),
    LOSSES("Losses", "Prohry"),
    PRIVATE("Private", "Soukromý"),
    PRIVATE_FIRST_CLASS("Private First Class", "Vojín"),
    LANCE_CORPORAL("Lance Corporal", "Svobodník"),
    CORPORAL("Corporal", "Desátník"),
    SERGEANT("Sergeant", "Četař"),
    STAFF_SERGEANT("Staff Sergeant", "Rotný"),
    GUNNERY_SERGEANT("Gunnery Sergeant", "Četař dělostřelectva"),
    MASTER_SERGEANT("Master Sergeant", "Vrchní rotmistr"),
    FIRST_SERGEANT("First Sergeant", "První rotmistr"),
    MASTER_GUNNERY_SERGEANT("Master Gunnery Sergeant", "Vrchní četař dělostřelectva"),
    SERGEANT_MAJOR("Sergeant Major", "Hlavní četař"),
    SERGEANT_MAJOR_OF_THE_CORPS("Sergeant Major of the Corps", "Hlavní četař sboru"),
    SECOND_LIEUTENANT("2nd Lieutenant", "Poručík"),
    FIRST_LIEUTENANT("1st Lieutenant", "Nadporučík"),
    CAPTAIN("Captain", "Kapitán"),
    MAJOR("Major", "Major"),
    LIEUTENANT_COLONEL("Lieutenant Colonel", "Podplukovník"),
    COLONEL("Colonel", "Plukovník"),
    BRIGADIER_GENERAL("Brigadier General", "Brigádní generál"),
    MAJOR_GENERAL("Major General", "Generálmajor"),
    LIEUTENANT_GENERAL("Lieutenant General", "Generálporučík"),
    GENERAL("General", "Generál");

    private static final ObservableMap<StringProperty, NodeTextEnum> COMPONENTS_FOR_TRANSLATE =
            FXCollections.observableMap(new HashMap<>());
    private final Manager manager = Manager.getInstance();
    private final String engText;
    private final String czeText;

    static {
        COMPONENTS_FOR_TRANSLATE.addListener((InvalidationListener) change -> reloadTexts());
    }

    NodeTextEnum(String engText, String czeText) {
        this.engText = engText;
        this.czeText = czeText;
    }

    public static ObservableMap<StringProperty, NodeTextEnum> getComponentsForTranslate() {
        return COMPONENTS_FOR_TRANSLATE;
    }

    public static void reloadTexts() {
        COMPONENTS_FOR_TRANSLATE.forEach((component, text) -> component.set(text.getSelectedText()));
    }

    public static NodeTextEnum getRank(int rank) {
        switch (rank) {
            case 0:
                return PRIVATE;
            case 1:
                return PRIVATE_FIRST_CLASS;
            case 2:
                return LANCE_CORPORAL;
            case 3:
                return CORPORAL;
            case 4:
                return SERGEANT;
            case 5:
                return STAFF_SERGEANT;
            case 6:
                return GUNNERY_SERGEANT;
            case 7:
                return MASTER_SERGEANT;
            case 8:
                return FIRST_SERGEANT;
            case 9:
                return MASTER_GUNNERY_SERGEANT;
            case 10:
                return SERGEANT_MAJOR;
            case 11:
                return SERGEANT_MAJOR_OF_THE_CORPS;
            case 12:
                return SECOND_LIEUTENANT;
            case 13:
                return FIRST_LIEUTENANT;
            case 14:
                return CAPTAIN;
            case 15:
                return MAJOR;
            case 16:
                return LIEUTENANT_COLONEL;
            case 17:
                return COLONEL;
            case 18:
                return BRIGADIER_GENERAL;
            case 19:
                return MAJOR_GENERAL;
            case 20:
                return LIEUTENANT_GENERAL;
            case 21:
                return GENERAL;
            default:
                return EMPTY_STRING;
        }
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