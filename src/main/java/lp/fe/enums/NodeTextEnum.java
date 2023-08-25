package lp.fe.enums;

import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import lombok.Getter;
import lp.Manager;
import lp.be.serviceimpl.PictureSourceEnum;

import java.util.HashMap;

@Getter
public enum NodeTextEnum {

    APPLICATION_TITLE("Battlefield 2 - Head Quarters", "Battlefield 2 - Informační přehledy"),
    DIALOG_TEXT_WINDOW_CLOSE_TITLE("Close application", "Ukončení aplikace"),
    DIALOG_TEXT_WINDOW_CLOSE("Do you want to exit application?", "Přejete si ukončit program?"),
    TOOLKIT_TEXT("Images tooltips", "Popisky obrázků"),
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
    LAST_THREE_AWARDS_TITLE("THREE LATEST AWARDS", "POSLEDNÍ TŘI OCENĚNÍ"),
    LAST_AWARD("LATEST AWARD", "POSLEDNÍ OCENĚNÍ"),

    LEVEL_1_1031105("Basic Engineer Combat", "Základní práce inženýra"),
    LEVEL_1_1031109("Basic Sniper Combat", "Základní odstřelovač"),
    LEVEL_1_1031113("Basic Medic Combat", "Základní práce zdravotníka"),
    LEVEL_1_1031115("Basic Spec Ops Combat", "Základní speciální jednotka"),
    LEVEL_1_1031119("Basic Assault Combat", "Základní pozemní boj"),
    LEVEL_1_1031120("Basic Anti-Tank Combat", "Protitankový boj"),
    LEVEL_1_1031121("Basic Support Combat", "Základní kulometčík"),
    LEVEL_1_1031406("Basic Knife Combat", "Základní boj s nožem"),
    LEVEL_1_1031619("Basic Pistol Combat", "Základní boj s pistolí"),
    LEVEL_1_1031923("Basic Ground Defense", "Základní pozemní obrana"),
    LEVEL_1_1032415("Basic Explosive Ordinance", "Základní práce s výbušninami"),
    LEVEL_1_1190304("Basic Command", "Základní rozkazy"),
    LEVEL_1_1190507("Basic Engineer", "Inženýr záčečník"),
    LEVEL_1_1190601("Basic First Aid", "Základní první pomoc"),
    LEVEL_1_1191819("Basic Resupply", "Základní doplňování zásob"),
    LEVEL_1_1220104("Basic Air Defense", "Základní protivzdušná obrana"),
    LEVEL_1_1220118("Basic Armor", "Základní obrněná jednotka"),
    LEVEL_1_1220122("Basic Aviator", "Pilot začátečník"),
    LEVEL_1_1220803("Basic Helicopter", "Pilot vrtulníku začátečník"),
    LEVEL_1_1222016("Basic Transport", "Základní transport"),

    LEVEL_2_1031105("Veteran Engineer Combat", "Veteránská práce inženýra"),
    LEVEL_2_1031109("Veteran Sniper Combat", "Veterán odstřelovač"),
    LEVEL_2_1031113("Veteran Medic Combat", "Veteránská práce zdravotníka"),
    LEVEL_2_1031115("Veteran Spec Ops Combat", "Veterán speciálních jednotek"),
    LEVEL_2_1031119("Veteran Assault Combat", "Veterán v pozemním boji"),
    LEVEL_2_1031120("Veteran Anti-Tank Combat", "Veterán protitankového boje"),
    LEVEL_2_1031121("Veteran Support Combat", "Veteránská práce kulometčíka"),
    LEVEL_2_1031406("Veteran Knife Combat", "Veterán v boji s nožem"),
    LEVEL_2_1031619("Veteran Pistol Combat", "Veterán v boji s pistolí"),
    LEVEL_2_1031923("Veteran Ground Defense", "Veterán v pozemní obraně"),
    LEVEL_2_1032415("Veteran Explosive Ordinance", "Veteránská práce s výbušninami"),
    LEVEL_2_1190304("Veteran Command", "Veteránské rozkazy"),
    LEVEL_2_1190507("Veteran Engineer", "Inženýr veterán"),
    LEVEL_2_1190601("Veteran First Aid", "Veterán v první pomoci"),
    LEVEL_2_1191819("Veteran Resupply", "Veterán v doplňování zásob"),
    LEVEL_2_1220104("Veteran Air Defense", "Veteránská protivzdušná obrana"),
    LEVEL_2_1220118("Veteran Armor", "Veteránská obrněná jednotka"),
    LEVEL_2_1220122("Veteran Aviator", "Pilot veterán"),
    LEVEL_2_1220803("Veteran Helicopter", "Pilot vrtulníku veterán"),
    LEVEL_2_1222016("Veteran Transport", "Veteránský transport"),

    LEVEL_3_1031105("Expert Engineer Combat", "Expertní práce inženýra"),
    LEVEL_3_1031109("Expert Sniper Combat", "Expert odstřelovač"),
    LEVEL_3_1031113("Expert Medic Combat", "Expertní práce zdravotníka"),
    LEVEL_3_1031115("Expert Spec Ops Combat", "Expert speciálních jednotek"),
    LEVEL_3_1031119("Expert Assault Combat", "Expert v pozemním boji"),
    LEVEL_3_1031120("Expert Anti-Tank Combat", "Expert protitankového boje"),
    LEVEL_3_1031121("Expert Support Combat", "Expertní práce kulometčíka"),
    LEVEL_3_1031406("Expert Knife Combat", "Expert v boji s nožem"),
    LEVEL_3_1031619("Expert Pistol Combat", "Expert v boji s pistolí"),
    LEVEL_3_1031923("Expert Ground Defense", "Expert v pozemní obraně"),
    LEVEL_3_1032415("Expert Explosive Ordinance", "Expertní práce s výbušninami"),
    LEVEL_3_1190304("Expert Command", "Expertní rozkazy"),
    LEVEL_3_1190507("Expert Engineer", "Inženýr expert"),
    LEVEL_3_1190601("Expert First Aid", "Expert v první pomoci"),
    LEVEL_3_1191819("Expert Resupply", "Expert v doplňování zásob"),
    LEVEL_3_1220104("Expert Air Defense", "Expertní protivzdušná obrana"),
    LEVEL_3_1220118("Expert Armor", "Expertní obrněná jednotka"),
    LEVEL_3_1220122("Expert Aviator", "Pilot expert"),
    LEVEL_3_1220803("Expert Helicopter", "Pilot vrtulníku expert"),
    LEVEL_3_1222016("Expert Transport", "Expertní transport"),

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

    public static NodeTextEnum getNodeTextEnum(PictureSourceEnum pictureSourceEnum, int pictureId) {
        switch (pictureSourceEnum) {
            case BRONZE_BADGES:
                return NodeTextEnum.valueOf("LEVEL_1_" + pictureId);
            case SILVER_BADGES:
                return null;
            case GOLD_BADGES:
                return null;
            case MEDALS:
                return null;
            case RIBBONS:
            case SMALL_RIBBONS:
                return null;
            case RANKS:
            case SMALL_RANKS:
                return getRank(pictureId);
            default:
                return null;
        }
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