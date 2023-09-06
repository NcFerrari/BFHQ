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
    MEDAL("MEDALS", "MEDAILE"),
    BADGE("BADGES", "ODZNAKY"),
    RIBBON("RIBBONS", "STUHY"),
    KITS("KITS", "VÝBAVA"),
    VEHICLE_CATEGORY("VEHICLE CATEGORY", "DOPRAVNÍ PROSTŘEDEK"),
    WEAPON("WEAPON", "ZBRAŇ"),
    MAP("MAP", "MAPA"),
    MOST_PLAYED_TAB("MOST PLAYED", "NEJVÍC HRANÉ"),
    ARMY_STATS_TAB("ARMY STATS", "ARMÁDNÍ STATISTIKY"),
    MAP_NAME("Map name", "Název mapy"),
    MAP_STATS_TAB("MAP STATS", "STATISTIKY MAP"),
    TEAM_ACTION("Team action", "Týmové akce"),
    SCORE("Score", "Body"),
    TEAM_WORK_SCORE("Teamwork score", "Skóre týmové práce"),
    CAPTURED_CP("Captured CP (command post)", "Zabrané velitelské stanoviště"),
    CAPTURE_ASSIST("Capture Assist", "Asistence zabrání"),
    DEFENDED_CP("Defended CP", "Obrana velitelského stanoviště"),
    KILL_ASSISTS("Kill assists", "Asistence zabití"),
    HEAL("Heal", "Uzdravování"),
    REVIVE("Revive", "Oživování"),
    SUPPORT("Support", "Doplňování munice"),
    REPAIR("Repair", "Opravování"),
    DRIVER_SPECIAL("Driver special", "Speciální bod z auta"),
    TEAM_WORK_TITLE("TEAMWORK", "TÝMOVÁ PRÁCE"),
    DRIVER_SPECIAL_TOOLTIP("A driver special ability point is when you're the driver of a vehicle, and your passenger" +
            "is an engineer, support or medic.\nWhen they support someone, heal someone, or repair someone, then you " +
            "get a driver special ability point.",
            "Speciální bod z auta získáš jako řidič vozidla a někdo z tvých pasažérů je inženýr, zásobovač nebo" +
                    "zdravotník.\nKdyž někoho zásobují, uzdraví nebo opraví, tak potom získáš speciální bod z auta."),
    PLAYER_ACTION("Player action", "Hráčské akce"),
    KILL_STREAK("Kill streak", "Série zabití"),
    DEATH_STREAK("Death streak", "Série smrtí"),
    DRIVER_ASSISTS("Driver assists", "Asistence zabití v autě"),
    CMD_SCORE("CMD score", "Body velitele"),
    CMD_TIME("CMD time", "Čas velení"),
    SQL_TIME("SQL time", "Čas vedení skupiny"),
    SQM_TIME("SQM time", "Čas ve skupině"),
    PLAYER_STATS("PLAYER STATS", "HRÁČSKÉ STATISTIKY"),
    LW_TIME("Lonely wolf time", "Čas jako vlk samotář"),
    OVERALL_ACCURACY("O. ACCURACY", "C. PŘESNOST"),
    ACCURACY("ACCURACY", "PŘESNOST"),
    KILLS_TEXT("KILLS", "ZABITÍ"),
    DEATHS_TEXT("DEATHS", "SMRTI"),
    KILL_DEATH_RATIO("K/D RATIO", "POMĚR Z/S"),
    TIME_USED("TIME USED", "DOBA POUŽÍVÁNÍ"),
    RESULT("Result", "Celkově"),
    KIT_EQUIPMENT("KIT EQUIPMENT", "VÝBAVY"),
    ROAD_KILLS("ROAD KILLS", "PŘEJETÍ AUTEM"),
    LAST_AWARDED("LAST AWARDED", "NAPOSLED ZÍSKÁNO"),
    FIRST_AWARDED("FIRST AWARDED", "POPRVÉ ZÍSKÁNO"),

    FACTION_USMC("United States Marine Corps", "Námořní pěchota spojených Států"),
    FACTION_PLA("People's Liberation Army", "Lidová Osvobozenecká Armáda"),
    FACTION_MEC("Middle Eastern Coalition", "Koalice Středo-východu"),
    FACTION_EU("European Union", "Evropská Unie"),

    KIT_0("Anti-tank", "Bazukář"),
    KIT_1("Assault", "Pěšák"),
    KIT_2("Engineer", "Inženýr"),
    KIT_3("Medic", "Zdravotník"),
    KIT_4("Special forces", "Speciální jednotky"),
    KIT_5("Support", "Podpora"),
    KIT_6("Sniper", "Odstřelovač"),

    VEHICLE_0("Tank or APC", "Tank nebo APC"),
    VEHICLE_1("Airplane", "Stíhačka"),
    VEHICLE_2("Anti-air", "Protivzdušná obrana"),
    VEHICLE_3("Helicopter", "Vrtulník"),
    VEHICLE_4("Car or boat", "Auto nebo člun"),
    VEHICLE_6("TOW or machine gun", "TOW nebo těžký kulomet"),
    VEHICLE_7("Parachute", "Skok padákem"),

    WEAPON_0("Assault Rifle", "Útočná zbraň"),
    WEAPON_1("Grenade Launcher", "Odpalovač granátů"),
    WEAPON_2("Carbine", "Karabina"),
    WEAPON_3("Light Machinegun", "Lehký Kulomet"),
    WEAPON_4("Sniper Rifle", "Odstřelovačka"),
    WEAPON_5("Pistol", "Pistole"),
    WEAPON_6("Rocket Launcher", "Raketomet"),
    WEAPON_7("Submachine gun", "Samopal"),
    WEAPON_8("Shotgun", "Brokovnice"),
    WEAPON_9("Knife", "Nůž"),
    WEAPON_10("C4", "C4"),
    WEAPON_11("Hand grenade", "Ruční granát"),
    WEAPON_12("Claymore", "Nášlapná mina"),
    WEAPON_13("Electric shocks", "Elektrošoky"),
    WEAPON_14("Anti tank mine", "Protitanková mina"),
    WEAPON_EXPLOSIVES("Explosives", "Výbušniny"),

    MAP_0("Kubra dam", "Přehrada Kubra"),
    MAP_1("Mashtuur city", "Město Mashtuur"),
    MAP_2("Operation clean sweep", "Operace čisté zametání"),
    MAP_3("Zatar wetlands", "Zatarské mokřady"),
    MAP_4("Strike at Karkand", "Útok na Karkand"),
    MAP_5("Sharqi peninsula", "Poloostrov Sharqi"),
    MAP_6("Gulf of Oman", "Ománského záliv"),
    MAP_10("Operation smoke screen", "Operace kouřová clona"),
    MAP_11("Taraba Quarry", "Lom v Tarabě"),
    MAP_12("Road to Jalalabad", "Cesta do Džalalabádu"),
    MAP_100("Daqing oilfields", "Ropná pole Daqing"),
    MAP_101("Dalian plant", "Rostlina Dalian"),
    MAP_102("Dragon valley", "Dračí údolí"),
    MAP_103("Fushe pass", "Procházení Fuschí"),
    MAP_105("Songhua stalemate", "Patová situace v Songhua"),
    MAP_110("Great wall", "Velká zeď"),
    MAP_200("Midnight sun", "Půlnoční slunce"),
    MAP_201("Operation road rage", "Operace silniční běs"),
    MAP_202("Operation harvest", "Operace sklizeň"),
    MAP_601("Wake island 2007", "Probouzející se ostrov 2007"),
    MAP_700("Highway tampa", "Dálniční tampa"),

    BADGE_1_1031105("Basic Engineer Combat", "Základní práce inženýra"),
    BADGE_1_1031109("Basic Sniper Combat", "Základní práce odstřelovače"),
    BADGE_1_1031113("Basic Medic Combat", "Základní práce zdravotníka"),
    BADGE_1_1031115("Basic Spec Ops Combat", "Základní práce spec. jednotek"),
    BADGE_1_1031119("Basic Assault Combat", "Základní boj"),
    BADGE_1_1031120("Basic Anti-Tank Combat", "Proti tankový boj"),
    BADGE_1_1031121("Basic Support Combat", "Základní práce kulometčíka"),
    BADGE_1_1031406("Basic Knife Combat", "Základní boj s nožem"),
    BADGE_1_1031619("Basic Pistol Combat", "Základní boj s pistolí"),
    BADGE_1_1031923("Basic Ground Defense", "Základní pozemní obrana"),
    BADGE_1_1032415("Basic Explosive Ordinance", "Základní práce s výbušninami"),
    BADGE_1_1190304("Basic Command", "Základní rozkazy"),
    BADGE_1_1190507("Basic Engineer", "Inženýr-začátečník"),
    BADGE_1_1190601("Basic First Aid", "První pomoc"),
    BADGE_1_1191819("Basic Resupply", "Doplňování zásob"),
    BADGE_1_1220104("Basic Air Defense", "Základní protivzdušná obrana"),
    BADGE_1_1220118("Basic Armor", "Základní obrn. jednotky"),
    BADGE_1_1220122("Basic Aviator", "Pilot-začátečník"),
    BADGE_1_1220803("Basic Helicopter", "Pilot vrtulníku-začátečník"),
    BADGE_1_1222016("Basic Transport", "Základní transport"),

    BADGE_2_1031105("Veteran Engineer Combat", "Veteránská práce inženýra"),
    BADGE_2_1031109("Veteran Sniper Combat", "Veterán odstřelovač"),
    BADGE_2_1031113("Veteran Medic Combat", "Veteránská práce zdravotníka"),
    BADGE_2_1031115("Veteran Spec Ops Combat", "Veterán speciálních jednotek"),
    BADGE_2_1031119("Veteran Assault Combat", "Veterán v pozemním boji"),
    BADGE_2_1031120("Veteran Anti-Tank Combat", "Veterán protitankového boje"),
    BADGE_2_1031121("Veteran Support Combat", "Veteránská práce kulometčíka"),
    BADGE_2_1031406("Veteran Knife Combat", "Veterán v boji s nožem"),
    BADGE_2_1031619("Veteran Pistol Combat", "Veterán v boji s pistolí"),
    BADGE_2_1031923("Veteran Ground Defense", "Veterán v pozemní obraně"),
    BADGE_2_1032415("Veteran Explosive Ordinance", "Veteránská práce s výbušninami"),
    BADGE_2_1190304("Veteran Command", "Veteránské rozkazy"),
    BADGE_2_1190507("Veteran Engineer", "Inženýr veterán"),
    BADGE_2_1190601("Veteran First Aid", "Veterán v první pomoci"),
    BADGE_2_1191819("Veteran Resupply", "Veterán v doplňování zásob"),
    BADGE_2_1220104("Veteran Air Defense", "Veteránská protivzdušná obrana"),
    BADGE_2_1220118("Veteran Armor", "Veteránská obrněná jednotka"),
    BADGE_2_1220122("Veteran Aviator", "Pilot veterán"),
    BADGE_2_1220803("Veteran Helicopter", "Pilot vrtulníku veterán"),
    BADGE_2_1222016("Veteran Transport", "Veteránský transport"),

    BADGE_3_1031105("Expert Engineer Combat", "Expertní práce inženýra"),
    BADGE_3_1031109("Expert Sniper Combat", "Expert odstřelovač"),
    BADGE_3_1031113("Expert Medic Combat", "Expertní práce zdravotníka"),
    BADGE_3_1031115("Expert Spec Ops Combat", "Expert speciálních jednotek"),
    BADGE_3_1031119("Expert Assault Combat", "Expert v pozemním boji"),
    BADGE_3_1031120("Expert Anti-Tank Combat", "Expert protitankového boje"),
    BADGE_3_1031121("Expert Support Combat", "Expertní práce kulometčíka"),
    BADGE_3_1031406("Expert Knife Combat", "Expert v boji s nožem"),
    BADGE_3_1031619("Expert Pistol Combat", "Expert v boji s pistolí"),
    BADGE_3_1031923("Expert Ground Defense", "Expert v pozemní obraně"),
    BADGE_3_1032415("Expert Explosive Ordinance", "Expertní práce s výbušninami"),
    BADGE_3_1190304("Expert Command", "Expertní rozkazy"),
    BADGE_3_1190507("Expert Engineer", "Inženýr expert"),
    BADGE_3_1190601("Expert First Aid", "Expert v první pomoci"),
    BADGE_3_1191819("Expert Resupply", "Expert v doplňování zásob"),
    BADGE_3_1220104("Expert Air Defense", "Expertní protivzdušná obrana"),
    BADGE_3_1220118("Expert Armor", "Expertní obrněná jednotka"),
    BADGE_3_1220122("Expert Aviator", "Pilot expert"),
    BADGE_3_1220803("Expert Helicopter", "Pilot vrtulníku expert"),
    BADGE_3_1222016("Expert Transport", "Expertní transport"),

    MEDAL_2020419("Distinguished Service Medal", "Medajle za slavné služby"),
    MEDAL_2020719("Golden Scimitar", "Zlatá šavle"),
    MEDAL_2020903("Combat Infantry Medal", "Medajle za boj pěchoty"),
    MEDAL_2020913("Marksman Infantry Medal", "Medajle za střelce pěchoty"),
    MEDAL_2020919("Sharpshooter Infa9ntry Medal", "Medaje za ostrostřelce pěchoty"),
    MEDAL_2021322("Medal of Valor", "Medajle hrdiny"),
    MEDAL_2021403("Navy Cross", "Námořní kříž"),
    MEDAL_2021613("People's Medallion", "Lidový medajlon"),
    MEDAL_2051902("Bronze Star", "Bronzová hvězda"),
    MEDAL_2051907("Gold Star", "Zlatá hvězda"),
    MEDAL_2051919("Silver Star", "Stříbrná hvězda"),
    MEDAL_2190303("Combat Action Medal", "Medajle za akci v boji"),
    MEDAL_2190308("Helicopter Combat Medal", "Medajle za létání s vrtulníkem"),
    MEDAL_2190309("Air Combat Medal", "Medajle za boj ve vzduchu"),
    MEDAL_2190318("Armor Combat Medal", "Medajle za práci obrněnců"),
    MEDAL_2190703("Good Conduct Medal", "Medajle za dobré chování"),
    MEDAL_2191319("Meritorious Service Medal", "Medajle za zasloužilé služby"),
    MEDAL_2191608("Purple Heart Medal", "Purpurové srdce"),
    MEDAL_3270519("European Union Special Service Medal", "Medajle za zvláštní službu Evropské unie"),

    RIBBON_3040109("Air Defense Ribbon", "Stuha za protivzdušnou obranu"),
    RIBBON_3040718("Ground Defense Ribbon", "Stuha za pozemní obranu"),
    RIBBON_3150914("Infantry Officer Ribbon", "Stuha důstojníka pěchoty"),
    RIBBON_3151920("Staff Officer Ribbon", "Stuha štábního důstojníka"),
    RIBBON_3190105("Aerial Service Ribbon", "Stuha za práci ve vzduchu"),
    RIBBON_3190118("Armored Service Ribbon", "Stuha za práci obrněnců"),
    RIBBON_3190318("Crew Service Ribbon", "Stuha za služby mužstva"),
    RIBBON_3190409("Distinguished Service Ribbon", "Stuha za významnou službu"),
    RIBBON_3190605("Far-East Service Ribbon", "Čínská služební stuha"),
    RIBBON_3190803("Helicopter Service Ribbon", "Stuha za práci s vrtulníkem"),
    RIBBON_3191305("Middle East Service Ribbon", "Středovýchodní služební stuha"),
    RIBBON_3211305("Meritorious Unit Ribbon", "Stuha zasloužilé jednotce"),
    RIBBON_3212201("Valorous Unit Ribbon", "Stuha za udatné zásluhy"),
    RIBBON_3240102("Airborne Ribbon", "Stuha letectva"),
    RIBBON_3240301("Combat Action Ribbon", "Stuha za akci v boji"),
    RIBBON_3240703("Good Conduct Ribbon", "Stuha za dobré chování"),
    RIBBON_3241213("Legion of Merit Ribbon", "Stuha udatné jednotce"),
    RIBBON_3242303("War College Ribbon", "Stuha od vojenské akademie"),

    RANK_0("Private", "Soukromý"),
    RANK_1("Private First Class", "Vojín"),
    RANK_2("Lance Corporal", "Svobodník"),
    RANK_3("Corporal", "Desátník"),
    RANK_4("Sergeant", "Četař"),
    RANK_5("Staff Sergeant", "Rotný"),
    RANK_6("Gunnery Sergeant", "Četař dělostřelectva"),
    RANK_7("Master Sergeant", "Vrchní rotmistr"),
    RANK_8("First Sergeant", "První rotmistr"),
    RANK_9("Master Gunnery Sergeant", "Vrchní četař dělostřelectva"),
    RANK_10("Sergeant Major", "Hlavní četař"),
    RANK_11("Sergeant Major of the Corps", "Hlavní četař sboru"),
    RANK_12("2nd Lieutenant", "Poručík"),
    RANK_13("1st Lieutenant", "Nadporučík"),
    RANK_14("Captain", "Kapitán"),
    RANK_15("Major", "Major"),
    RANK_16("Lieutenant Colonel", "Podplukovník"),
    RANK_17("Colonel", "Plukovník"),
    RANK_18("Brigadier General", "Brigádní generál"),
    RANK_19("Major General", "Generálmajor"),
    RANK_20("Lieutenant General", "Generálporučík"),
    RANK_21("General", "Generál");

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

    public String getText(StringProperty stringProperty) {
        COMPONENTS_FOR_TRANSLATE.put(stringProperty, this);
        return getSelectedText();
    }

    public String getSelectedText() {
        if (manager.getLanguage().equals(LangEnum.ENG)) {
            return getEngText();
        }
        return getCzeText();
    }

    public static NodeTextEnum getNodeTextEnum(PictureSourceEnum pictureSourceEnum, int pictureId) {
        NamespaceEnum namespaceEnum;
        switch (pictureSourceEnum) {
            case BRONZE_BADGES:
                namespaceEnum = NamespaceEnum.BADGE_ONE;
                break;
            case SILVER_BADGES:
                namespaceEnum = NamespaceEnum.BADGE_TWO;
                break;
            case GOLD_BADGES:
                namespaceEnum = NamespaceEnum.BADGE_THREE;
                break;
            case MEDALS:
                namespaceEnum = NamespaceEnum.MEDAL_PREFIX;
                break;
            case RIBBONS:
            case SMALL_RIBBONS:
                namespaceEnum = NamespaceEnum.RIBBON_PREFIX;
                break;
            case RANKS:
            case SMALL_RANKS:
                namespaceEnum = NamespaceEnum.RANK_PREFIX;
                break;
            case KITS:
                return NodeTextEnum.valueOf(NamespaceEnum.KIT.getText() + pictureId);
            default:
                return null;
        }
        return NodeTextEnum.valueOf(namespaceEnum.getText() + pictureId);
    }
}