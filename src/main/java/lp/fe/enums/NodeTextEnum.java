package lp.fe.enums;

import javafx.beans.InvalidationListener;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import lombok.Getter;
import lp.Manager;
import lp.be.serviceimpl.PictureSourceEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    TAB_MENU_MAP_INFO("MAP INFO", "INFORMACE O MAPÁCH"),
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
    CATEGORY_TITLE("CATEGORY", "KATEGORIE"),
    SORT_BY_RANK("Ranks", "Hodnosti"),
    SORT_BY_KILLS("Kills", "Zabití"),
    SORT_BY_DEATHS("Deaths", "Smrti"),
    SORT_BY_COUNT_OF_AWARDS("Awards count", "Počet ocenění"),
    SORT_BY_TIME("Time", "Čas"),
    SORT_BY_SCORE("Score", "Skóre"),
    SORT_BY_WINS("Wins", "Výhry"),
    SORT_BY_LOSSES("Losses", "Prohry"),
    SORT_BY_TEAM_KILLING("Team kills", "Týmové zabití"),
    SORT_BY_TEAM_PLAYER_SCORE("Team score", "Týmové skóre"),
    SORT_BY_KILL_STREAK("Kill streaks", "Zabití v řadě"),
    ORDER_TITLE("Order", "Pořadí"),
    NAME_TITLE("Name", "Jméno"),
    RANK_TITLE_2("Rank", "Hodnost"),
    SHOW_LEADERBOARD("Show leaderboard", "Zobrazit žebříčky"),
    SHOW_KILLING("Show kills", "Zobrazit zabití"),
    PLAYER_KILLS_COUNT("Player killed count", "Počet zabití hráče"),
    KILLS_BY_PLAYER_COUNT("Killed by player count", "Počet zabití od hráče"),
    COUNT("Count", "Počet"),
    SHOW_KILLS_BUTTON_TITLE("Show kills data", "Zobraz data zabití"),
    SHOW_CROSS_TABLE("Show cross table", "Zobraz křížovou tabulku"),

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
    MEDAL_2020919("Sharpshooter Infantry Medal", "Medaje za ostrostřelce pěchoty"),
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
    RANK_21("General", "Generál"),

    TOOLTIP_BADGE_1_1031105("10 Kills as an Engineer in a Round", "10 zabití v jednom kole jako Inženýr"),
    TOOLTIP_BADGE_1_1031109("10 Kills as a Sniper in a Round", "10 zabití v jednom kole jako Odstřelovač"),
    TOOLTIP_BADGE_1_1031113("10 Kills as a Medic in a Round", "10 zabití v jednom kole jako Zdravotník"),
    TOOLTIP_BADGE_1_1031115("5 Kills as a Spec Ops in a Round", "5 zabití v jednom kole jako Speciální jednotka"),
    TOOLTIP_BADGE_1_1031119("5 Kills with Assault in a Round", "5 zabití v jednom kole jako Pěšák"),
    TOOLTIP_BADGE_1_1031120("10 Kills as Anti-Tank in a Round", "10 zabití v jednom kole jako Bazukář"),
    TOOLTIP_BADGE_1_1031121("10 Kills as Support in a Round", "10 zabití v jednom kole jako Kulometčík"),
    TOOLTIP_BADGE_1_1031406("7 Kills with a Knife in a Round", "7 zabití v jednom kole s nožem"),
    TOOLTIP_BADGE_1_1031619("5 Kills with a Pistol in a Round", "5 zabití v jednom kole s pistolí"),
    TOOLTIP_BADGE_1_1031923("5 Minutes in TOW or Mounted Machine Gun", "5 minut v jednom kole v TOW nebo nemobilním" +
            " těžkém kulometu"),
    TOOLTIP_BADGE_1_1032415("5 Kills with any combination of AT Mine, C4, or Claymore in a Round", "5 zabití v jednom" +
            " kole použitím miny, C4 nebo claymore (v jakékoliv kombinaci)"),
    TOOLTIP_BADGE_1_1190304("40 Command Points in a Round", "40 bodů jako commander v jednom kole"),
    TOOLTIP_BADGE_1_1190507("5 Repair Points in a Round", "5 bodů za opravy v jednom kole"),
    TOOLTIP_BADGE_1_1190601("5 Heal Points in a Round", "5 bodů za uzdravování v jednom kole (ne za oživení!)"),
    TOOLTIP_BADGE_1_1191819("5 Ammo Points in a Round", "5 bodů za doplňování munice v jednom kole"),
    TOOLTIP_BADGE_1_1220104("10 Minutes on Stinger or AA Vehicle in a Round", "10 minut v jednom kole v" +
            " protiletadlovém autě nebo stroji"),
    TOOLTIP_BADGE_1_1220118("10 Minutes in Armor in a Round", "10 minut v jednom kole v tanku nebo APC (jako řidič" +
            " nebo střelec)"),
    TOOLTIP_BADGE_1_1220122("10 Minutes in Air Vehicles in a Round", "10 minut v jednom kole v letadle (bombardéru" +
            " či stíhačce) (jako pilot nebo střelec)"),
    TOOLTIP_BADGE_1_1220803("15 Minutes in a Helicopter in a Round", "15 minut v jednom kole v helikoptéře (útočné" +
            " či transp.) (jako pilot, střelec či pasažér)"),
    TOOLTIP_BADGE_1_1222016("10 Minutes in a Transport Vehicle in a Round", "10 minut v jednom kole v autě nebo člunu" +
            " (jako řidič, střelec či spolujezdec)"),

    TOOLTIP_BADGE_2_1031105("-Basic Engineer Combat Badge\n-15 Hours as Engineer\n-20 Kills as Engineer in a Round",
            "-Základní práce inženýra\n-Celkově 15 hodin jako Inženýr\n-20 zabití v jednom kole jako Inženýr"),
    TOOLTIP_BADGE_2_1031109("-Basic Sniper Combat Badge\n-15 Hours as Sniper\n-15 Kills as a Sniper in a Round",
            "-Základní práce odstřelovače\n-Celkově 15 hodin jako Odstřelovač\n-15 zabití v jednom kole jako" +
                    " Odstřelovač"),
    TOOLTIP_BADGE_2_1031113("-Basic Medic Combat Badge\n-15 Hours as a Medic\n-20 Kills as a Medic in a Round",
            "-Základní práce zdravotníka\n-Celkově 15 hodin jako Zdravotník\n-20 zabití v jednom kole jako Zdravotník"),
    TOOLTIP_BADGE_2_1031115("-Basic Spec Ops Combat Badge\n-15 Hours as Spec Ops\n-20 Kills as a Spec Ops in a Round",
            "-Základní práce spec. jednotek\n-Celkově 15 hodin jako Speciální jednotka\n-20 zabití v jednom kole" +
                    " jako spec. jednotka"),
    TOOLTIP_BADGE_2_1031119("-Basic Assault Combat Badge\n-15 Hours as Assault\n-20 Kills with Assault in a Round",
            "-Základní boj\n-Celkově 15 hodin jako Pěšák\n-20 zabití v jednom kole jako Pěšák"),
    TOOLTIP_BADGE_2_1031120("-Basic Anti-Tank Combat Badge\n-15 Hours as Anti-Tank\n-20 Kills as Anti-Tank in a Round",
            "-Proti tankový boj\n-Celkově 15 hodin jako Bazukář\n-20 zabití v jednom kole jako Bazukář"),
    TOOLTIP_BADGE_2_1031121("-Basic Support Combat Badge\n-15 Hours as Support\n-20 Kills as Support in a Round",
            "-Základní práce kulometčíka\n-Celkově 15 hodin jako Kulometčík\n-20 zabití v jednom kole jako Kulometčík"),
    TOOLTIP_BADGE_2_1031406("-Basic Knife Combat Badge\n-50 Kills with a Knife", "-Základní boj s nožem\n-50" +
            " celkových zabití s nožem"),
    TOOLTIP_BADGE_2_1031619("-Basic Pistol Combat Badge\n-50 Kills with Pistols\n-7 Kills with a Pistol in a Round",
            "-Základní boj s pistolí\n-50 celkových zabití s pistolí\n-7 zabití v jednom kole s pistolí"),
    TOOLTIP_BADGE_2_1031923("-Basic Ground Defense Badge\n-10 Kills With TOW or Mounted Machine Gun", "-Základní" +
            " pozemní obrana\n-10 zabití v jednom kole s TOW nebo nemobilním těžkém kulometu"),
    TOOLTIP_BADGE_2_1032415("-Basic Explosive Ordinance Badge\n-50 Kills with Explosives\n-20 Kills with any" +
            " combination of AT Mine, C4, or Claymore in a Round", "-Základní práce s výbušninami\n-50 celkových" +
            " zabití s výbušninami\n-20 zabití v jednom kole použitím miny, C4 nebo claymore (v jakékoliv kombinaci)" +
            " (je potřeba mít 50 bodů z výbušnin před tímto bodem)"),
    TOOLTIP_BADGE_2_1190304("-Basic Command Badge\n-1000 Command Points\n-25 Minutes as Commander in a Round",
            "-Základní rozkazy\n-Celkově 1.000 bodů velitele\n-25 minut v jednom kole jako velitel"),
    TOOLTIP_BADGE_2_1190507("-Basic Engineer Badge\n-5 Repair Points in a Round",
            "-Inženýr-začátečník\n-Celkově 15 hodin jako Inženýr\n-10 bodů za opravy v jednom kole"),
    TOOLTIP_BADGE_2_1190601("-Basic First Aid Badge\n-15 Hours as Medic\n-10 Heal Points in a Round",
            "-První pomoc\n-Celkově 15 hodin jako Zdravotník\n-10 bodů za uzdravování v jednom kole (ne za oživení!)"),
    TOOLTIP_BADGE_2_1191819("-Basic Resupply Badge\n-15 Hours as Support\n-10 Ammo Points in a Round",
            "-Doplňování zásob\n-Celkově 15 hodin jako Kulometčík\n-10 bodů za doplňování munice v jednom kole"),
    TOOLTIP_BADGE_2_1220104("-Basic Air Defense Badge\n-10 Kills With Stinger or AA Vehicle in a Round",
            "-Základní protivzdušná obrana\n-10 zabití v jednom kole v protiletadlovém autě nebo stroji"),
    TOOLTIP_BADGE_2_1220118("-Basic Armor Badge\n-100 Hours in Armor\n-12 Kills With Armor in a Round",
            "-Základní obrn. jednotky\n-Celkově 100 hodin v tanku nebo APC\n-12 zabití v jednom kole tankem nebo APC"),
    TOOLTIP_BADGE_2_1220122("-Basic Aviator Badge\n-50 Hours in Air Vehicles\n-12 Kills With Air Vehicles in a Round",
            "-Pilot-začátečník\n-Celkově 50 hodin v letadle\n-12 zabití v jednom kole v letadle"),
    TOOLTIP_BADGE_2_1220803("-Basic Helicopter Badge\n-50 Hours in a Helicopter\n-12 Kills With a" +
            " Helicopter in a Round",
            "-Pilot vrtulníku-začátečník\n-Celkově 50 hodin v helikoptéře\n-12 zabití v jednom kole v helikoptéře"),
    TOOLTIP_BADGE_2_1222016("-Basic Transport Badge\n-25 Hours in a Transport Vehicle\n-200 Driver Special Ability" +
            " Points\n-5 Road Kills in a Transport Vehicle in a Round", "-Základní transport\n-Celkově 25 hodin v" +
            " autě nebo člunu\n-200 speciálních dovedností\n-5 přejetím v jednom kole autem"),

    TOOLTIP_BADGE_3_1031105("-Veteran Engineer Combat Badge\n-100 Hours as Engineer\n-40 Kills as Engineer in a Round",
            "-Veterán-práce Inženýra\n-Celkově 100 hodin jako Inženýr\n-40 zabití v jednom kole jako Inženýr"),
    TOOLTIP_BADGE_3_1031109("-Veteran Sniper Combat Badge\n-100 Hours as Sniper\n-35 Kills as a Sniper in a Round",
            "-Veterán-Odstřelovač\n-Celkově 100 hodin jako Odstřelovač\n-35 zabití v jednom kole jako Odstřelovač"),
    TOOLTIP_BADGE_3_1031113("-Veteran Medic Combat Badge\n-100 Hours as a Medic\n-40 Kills as a Medic in a Round",
            "-Veterán-Zdravotník\n-Celkově 100 hodin jako Zdravotník\n-40 zabití v jednom kole jako Zdravotník"),
    TOOLTIP_BADGE_3_1031115("-Veteran Spec Ops Combat Badge\n-100 Hours as Spec Ops\n" +
            "-40 Kills as a Spec Ops in a Round", "-Veterán-Sepciálních jednotek\n-Celkově 100 hodin jako Speciální" +
            " jednotka\n-40 zabití v jednom kole jako Spec. jednotka"),
    TOOLTIP_BADGE_3_1031119("-Veteran Assault Combat Badge\n-100 Hours as Assault\n-40 Kills with Assault in a Round",
            "-Veterán-Pozemní boj\n-Celkově 100 hodin jako Pěšák\n-40 zabití v jednom kole jako Pěšák"),
    TOOLTIP_BADGE_3_1031120("-Veteran Anti-Tank Combat Badge\n-100 Hours as Anti-Tank\n-40 Kills as" +
            " Anti-Tank in a Round", "-Veterán-proti-tankový-boj\n-Celkově 100 hodin jako Bazukář\n-40 zabití v" +
            " jednom kole jako Bazukář"),
    TOOLTIP_BADGE_3_1031121("-Veteran Support Combat Badge\n-100 Hours as Support\n-40 Kills as Support",
            "-Veterán-Kulometčík\n-Celkově 100 hodin jako Kulometčík\n-40 zabití v jednom kole jako Kulometčík"),
    TOOLTIP_BADGE_3_1031406("-Veteran Knife Combat Badge\n-100 Kills with a Knife", "-Veterán-boj s nožem\n-100" +
            " celkových zabití s nožem"),
    TOOLTIP_BADGE_3_1031619("-Veteran Pistol Combat Badge\n-500 Kills with Pistols\n-18 Kills with a Pistol in a Round",
            "-Veterán-boj s pistolí\n-500 celkových zabití s pistolí\n-18 zabití v jednom kole s pistolí"),
    TOOLTIP_BADGE_3_1031923("-Veteran Ground Defense Badge\n-20 Kills With TOW or Mounted Machine Gun",
            "-Veterán-pozemní obrana\n-20 zabití v jednom kole s TOW nebo nemobilním těžkém kulometu"),
    TOOLTIP_BADGE_3_1032415("-Veteran Explosive Ordinance Badge\n-300 Kills with Explosives\n-30 Kills with any" +
            " combination of AT Mine, C4, or Claymroe in a Round", "-Veterán-práce s výbušninami\n-300 celkových" +
            " zabití s výbušninami\n-30 zabití v jednom kole použitím miny, C4 nebo claymore (v jakékoliv kombinaci)" +
            " (je potřeba mít 300 bodů z výbušnin před tímto bodem)"),
    TOOLTIP_BADGE_3_1190304("-Veteran Command Badge\n-10.000 Command Points\n-30 Minutes as Commander in a Round",
            "-Veterán-rozkazy\n-Celkově 10.000 bodů velitele\n-30 minut v jednom kole jako velitel"),
    TOOLTIP_BADGE_3_1190507("-Veteran Engineer Badge\n-100 Hours as Engineer\n-250 Repair Points\n-20 Repair Point in" +
            " a Round", "-Veterán-inženýr\n-Celkově 100 hodin jako Inženýr\n-250 celkových bodů za opravy\n-20 bodů" +
            " za opravy v jednom kole"),
    TOOLTIP_BADGE_3_1190601("-Veteran First Aid Badge\n-100 Hours as Medic\n-750 Heals\n-20 Heal Points in a Round",
            "-Veterán-první pomoc\n-Celkově 100 hodin jako Zdravotník\n-750 celkových bodů za uzdravování\n-20 bodů" +
                    " za uzdravování v jednom kole"),
    TOOLTIP_BADGE_3_1191819("-Veteran Resupply Badge\n-100 Hours as Support\n-500 Resupply Points\n-25 Ammo Points in" +
            " a Round", "-Veterán-zásobování\n-Celkově 100 hodin jako Kulometčík\n-500 celkových bodů za doplňování" +
            " munice\n-25 bodů za doplňování munice v jednom kole"),
    TOOLTIP_BADGE_3_1220104("-Veteran Air Defense Badge\n-20 Kills With Stinger or AA Vehicle in a Round",
            "-Veterán-protivzdušná obrana\n-20 zabití v jednom kole v protiletadlovém autě nebo stroji"),
    TOOLTIP_BADGE_3_1220118("-Veteran Armor Badge\n-400 Hours in Armor\n-24 Kills With Armor in a Round",
            "-Veterán-obránce\n-Celkově 400 hodin (16 dní) v tanku nebo APC\n-24 zabití v jednom kole tankem nebo APC"),
    TOOLTIP_BADGE_3_1220122("-Veteran Aviator Badge\n-150 Hours in Air Vehicles\n-24 Kills With Air" +
            " Vehicles in a Round", "-Veterán-pilot\n-Celkově 150 hodin v letadle\n-24 zabití v jednom kole v letadle"),
    TOOLTIP_BADGE_3_1220803("-Veteran Helicopter Badge\n-150 Hours in a Helicopter\n-24 Kills With a Helicopter in a" +
            " Round", "-Veterán-helikoptéra\n-Celkově 150 hodin v helikoptéře\n-24 zabití v jednom kole v helikoptéře"),
    TOOLTIP_BADGE_3_1222016("-Veteran Transport Badge\n-75 Hours in a Transport Vehicle\n-2.000 Driver Special" +
            " Ability Points\n-11 Road Kills in a Transport Vehicle", "-Veterán-transport\n-Celkově 75 hodin v autě" +
            " nebo člunu\n-2.000 speciálních dovedností\n-11 přejetím v jednom kole autem"),

    TOOLTIP_MEDAL_2020419("-100 Hours as Commander\n-100 Hours as Squad Leader\n-100 Hours as Squad Member\n-Team" +
            " Score of 45 in a Round", "-Celkově 100 hodin jako velitel týmu\n-Celkově 100 hodin jako tým" +
            " leader\n-Celkově 100 hodin jako člen týmu\n-45 týmových bodů v jednom kole"),
    TOOLTIP_MEDAL_2020719("-Play 100 Hours as MEC\n-100 Wins as MEC\n-100 score as MEC in a Round", "-Celkově 100" +
            " hodin hrát za blízkovýchodní koalici\n-Celkově 100x vyhrát za blízkovýchodní koalici\n-Mít aspoň 100" +
            " skóre za blízkovýchodní koalici v jednom kole"),
    TOOLTIP_MEDAL_2020903("-200 Hours Played\n-9 basic badges (7 weapons + pistole + knife)", "-Celkově hrát 200" +
            " hodin\n-9 základních odznaků (za 7 zbraní + pistole + nůž)\npozn.: Pokud získáte nejdřív odznaky a až" +
            " pak dovršíte 200 hodin herní doby, tak dotstanete medajli až v následující hře"),
    TOOLTIP_MEDAL_2020913("-300 Hours Played\n-9 veteran badges (7 weapons + pistole + knife)", "-Celkově hrát 300" +
            " hodin\n-9 odznaků veterána (za 7 zbraní + pistole + nůž)\npozn.: Pokud získáte nejdřív odznaky a až" +
            " pak dovršíte 300 hodin herní doby, tak dotstanete medajli až v následující hře"),
    TOOLTIP_MEDAL_2020919("-400 Hours Played\n-9 expert badges (7 weapons + pistole + knife)", "-Celkově hrát 400" +
            " hodin\n-9 expertních odznaků (za 7 zbraní + pistole + nůž)\npozn.: Pokud získáte nejdřív odznaky a až" +
            " pak dovršíte 400 hodin herní doby, tak dotstanete medajli až v následující hře"),
    TOOLTIP_MEDAL_2021322("-250 Hours Played\n-5.000 Driver Special Ability Points\n-30.000 Team Points\n-1.000 Flag" +
            " Defense Points", "-Celkově hrát 250 hodin\n-Celkově 5.000 speciálních schopností v autě\n-Celkově" +
            " 30.000 týmových bodů\n-Celkově 1.000 bodů za ubránění vlajky\npozn.: Tuto medajli dostanete v další" +
            " hře po splnění výše uvedených kritérií"),
    TOOLTIP_MEDAL_2021403("-Play 100 Hours as USMC\n-Win 100 Rounds as USMC\n-100 score as USMC in a Round\n",
            "-Celkově 100 hodin hrát za američany\n-Celkově 100x vyhrát za američany\n-Mít aspoň 100 skóre za" +
                    " američany v jednom kole"),
    TOOLTIP_MEDAL_2021613("-Play 100 Hours as PLA\n-100 Wins as PLA\n-100 score as PLA in a Round", "-Celkově 100" +
            " hodin hrát za číňany\n-Celkově 100x vyhrát za číňany\n-Mít aspoň 100 skóre za číňany v jednom kole"),
    TOOLTIP_MEDAL_2051902("Third Place in a Round", "Mít 3. největší skóre z obou týmů na konci kola"),
    TOOLTIP_MEDAL_2051907("First Place in a Round", "Mít 1. největší skóre z obou týmů na konci kola"),
    TOOLTIP_MEDAL_2051919("Second Place in a Round", "Mít 2. největší skóre z obou týmů na konci kola"),
    TOOLTIP_MEDAL_2190303("-250 Hours Played\n-25.000 Kills\n-25 Kills Streak\n-Play for 33 Minutes in a Round",
            "-Celkově hrát 250 hodin\n-Celkově 25.000 zabití\n-25 zabití po sobě bez smrti (killstreak)\n-Hrát 33" +
                    " minut v jednom kole"),
    TOOLTIP_MEDAL_2190308("-100 Hours in Helicopters\n-5.000 Kills With Helicopters\n-30 Kills with Helicopters in a" +
            " Round", "-Celkově 100 hodin být v helikoptéře\n-Celkově 5.000 zabití helikoptérou\n-30 zabití" +
            " helikoptérou v jednom kole"),
    TOOLTIP_MEDAL_2190309("-100 Hours in Air Vehicles\n-5.000 Kills With Air Vehicles\n-25 Kills with Air Vehicle" +
            " in a Round", "-Celkově 100 hodin být v letadle\n-Celkově 5.000 zabití letadlem\n-25 zabití letadlem v" +
            " jednom kole"),
    TOOLTIP_MEDAL_2190318("-100 Hours in Armored Vehicles\n-5.000 Kills With Armored Vehicles\n-25 Kills in an" +
            " Armored Vehicle in a Round", "-Celkově 100 hodin být v tanku nebo v APC\n-Celkově 5.000 zabití tankem" +
            " nebo APC\n-25 zabití tankem nebo APC v jednom kole"),
    TOOLTIP_MEDAL_2190703("-250 Hours Played\n-27 Kills with No Team Kills or Damage to Friendly Vehicles in a Round",
            "-Celkově hrát 250 hodin\n-27 zabití v jednom kole\n-NESMÍTE ZABÍT ANI ZRANIT SPOLUHRÁČE V TOMTO KOLE" +
                    " (ani poškodit vozidlo)"),
    TOOLTIP_MEDAL_2191319("-250 Hours Played\n-1.000 Heal Points\n-1.000 Repair Points\n-1.000 Resupply Points",
            "-Celkově hrát 250 hodin\n-Celkově 1.000 bodů za uzdravení\n-Celkově 1.000 bodů za opravy\n-Celkově" +
                    " 1.000 bodů za doplňování munice\npozn.: Tuto medajli dostanete v další hře po splnění výše" +
                    " uvedených kritérií"),
    TOOLTIP_MEDAL_2191608("Obtain a K/D of 1:4 With a Minimum of 5 Kills/20 Deaths in a Round",
            "Získat body zabití:smrt v poměru 1:4 s minimálním poměrem 5:20 v jednom kole"),
    TOOLTIP_MEDAL_3270519("-50 Hours as European Union\n-Win 100 Rounds as European Union in a Round",
            "-Celkově 50 hodin hrát za evropskou unii\n-Celkově 100x vyhrát za evropskou unii\n-Mít aspoň 100 skóre" +
                    " za evropskou unii v jednom kole"),

    TOOLTIP_RIBBON_3040109("-3 Minutes in SAM or AA Vehicle in a Round\n-11 Kills With SAM or AA Vehicle in a Round",
            "-3 minuty v AA autě nebo stroji v jednom kole\n-11 zabití s AA autem nebo strojem v jednom kole"),
    TOOLTIP_RIBBON_3040718("-3 Minutes in TOW or Mounted Machine Gun in a Round\n-5 Kills in TOW or Mounted Machine" +
            " Gun in a Round", "-3 minuty v TOW nebo nemobilním těžkém kulometu v jednom kole\n-5 zabití s TOW nebo" +
            " nemobilním těžkém kulometu v jednom kole"),
    TOOLTIP_RIBBON_3150914("-250 Team Points\n-25 Minutes as Squad Leader in a Round", "-Celkově 250 týmových" +
            " bodů\n-25 minut jako tým leader v jednom kole"),
    TOOLTIP_RIBBON_3151920("-28 Minutes as Commander in a Round\n-50 Command Points in a Round", "-28 minut být" +
            " velitelem týmu v jednom kole\n-50 velitelských bodů v jednom kole"),
    TOOLTIP_RIBBON_3190105("-15 Minutes in Air Vehicles in a Round\n-19 Kills in With Air Vehicles in a Round",
            "-15 minut být v letadle (bombardér nebo stíhačka) (pilot nebo střelec) v jednom kole\n-19 zabití" +
                    " letadlem (bombardérem nebo stíhačkou) v jednom kole"),
    TOOLTIP_RIBBON_3190118("-20 Minutes in Armored Vehicle in a Round\n-19 Kills with Armored Vehicles in a Round",
            "-20 minut být v tanku nebo APC v jednom kole\n-19 zabití tankem nebo APC v jednom kole"),
    TOOLTIP_RIBBON_3190318("-5 Kills in a Round\n-13 Driver Kill Assists in a Round\n-1 Driver Special Ability Point" +
            " in a Round", "-5 zabití v jednom kole\n-13 asistencí zabití v autě v jednom kole\n-1 bod speciální" +
            " dovednosti v autě v jednom kole"),
    TOOLTIP_RIBBON_3190409("-10 Hours as Commander\n-10 Hours as Squad Leader\n-10 Hours as Squad Member\n-15 Team" +
            " Points in a Round", "-Celkově 10 hodin jako velitel týmů\n-Celkově 10 hodin jako team leader\n-Celkově" +
            " 10 hodin jako člen týmu\n-15 týmových bodů v jednom kole"),
    TOOLTIP_RIBBON_3190605("-Play on Daqing Oilfields\n-Play on Dalian Plant\n-Play on Dragon Valley\n-Play on FuShe" +
            " Pass\n-Play on Songhua Stalemate\n-Play on Wake Island 2007", "-Zahrát si mapu Daqing Oilfields\n" +
            "-Zahrát si mapu Dalian Plant\n-Zahrát si mapu Dragon Valley\n-Zahrát si mapu FuShe Pass\n-Zahrát si" +
            " mapu Songhua Stalemate\n-Zahrát si mapu Wake Island 2007"),
    TOOLTIP_RIBBON_3190803("-15 Minutes in Helicopter in a Round\n-19 Kills with Helicopter in a Round",
            "-15 minut být v helikoptéře (jako pilot, střelec nebo pasažér) v jednom kole\n-19 zabití helikoptérou" +
                    " (pilotem nebo střelcem) v jednom kole"),
    TOOLTIP_RIBBON_3191305("-Play on Kubra Dam\n-Play on Mashtuur City\n-Play on Operation Clean Sweep\n-Play on" +
            " Zatar Wetlands\n-Play on Strike at Karkand\n-Play on Sharqi Peninsula\n-Play on Gulf of Oman",
            "-Zahrát si mapu Kubra Dam\n-Zahrát si mapu Mashtuur City\n-Zahrát si mapu Operation Clean Sweep\n" +
                    "-Zahrát si mapu Zatar Wetlands\n-Zahrát si mapu Strike at Karkand\n-Zahrát si mapu Sharqi" +
                    " Peninsula\n-Zahrát si mapu Gulf of Oman"),
    TOOLTIP_RIBBON_3211305("-26 Minutes in a Squad in a Round\n-40 Team Points in a Round", "-26 minut být členem" +
            " nějakého týmu v jednom kole\n-40 týmových bodů v jednom kole"),
    TOOLTIP_RIBBON_3212201("-25 Hours as Squad Leader\n-25 Hours in Squad\n-45 Team Points in a Round",
            "-Celkově 25 hodin jako team leader\n-Celkově 25 hodin jako člen týmu\n-45 týmových bodů v jednom kole"),
    TOOLTIP_RIBBON_3240102("10 Seconds Using Parachute in a Round", "10 sekund padat padákem v jednom kole"),
    TOOLTIP_RIBBON_3240301("-10 Killstreak\n-18 Kills in a Round", "-10 zabití po sobě bez smrti (killstreak)\n-18" +
            " zabití (až v dalším kole)\npozn.: Tato stuha je na dvě kola - v prvním kole killstreak," +
            " ve druhém zabití"),
    TOOLTIP_RIBBON_3240703("-50 Hours Played\n-14 Kills in a Round\nnote: No Team Kills or Damage to Friendly" +
            " Vehicles in a Round", "-Celkově 50 hodin hraní\n-14 zabití v jednom kole\npozn.: NESMÍTE ZABÍT ANI" +
            " ZRANIT SPOLUHRÁČE V TOMTO KOLE (ani poškodit vozidlo)"),
    TOOLTIP_RIBBON_3241213("-200 Hours Played\n-10 Kill Streak\n-8 Death Streak\n-50 Team Points in a Round",
            "-Celkově 200 hodin hraní\n-10 zabití po sobě bez smrti (killstreak)\n-8 smrtí po sobě bez zabití" +
                    " (deathstreak)\n-50 týmových bodů v jednom kole"),
    TOOLTIP_RIBBON_3242303("-25.000 Command Points\n-100 Hours as Commander\n-200 Wins", "-Celkově 25.000" +
            " velitelských bodů\n-Celkově 100 hodin jako velitel\n-Celkově 200 vítězství\npozn.: Tuto stužku" +
            " dostanete při připojení do další hry, až splníte všechna 3 kritéria");

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

    public static NodeTextEnum getRankTitleFromInt(int rank) {
        return NodeTextEnum.valueOf(NamespaceEnum.RANK_PREFIX.getText() + rank);
    }

    public static List<NodeTextEnum> getAwards() {
        NodeTextEnum[] awards = {BADGE_1_1031105, BADGE_1_1031109, BADGE_1_1031113, BADGE_1_1031115, BADGE_1_1031119,
                BADGE_1_1031120, BADGE_1_1031121, BADGE_1_1031406, BADGE_1_1031619, BADGE_1_1031923, BADGE_1_1032415,
                BADGE_1_1190304, BADGE_1_1190507, BADGE_1_1190601, BADGE_1_1191819, BADGE_1_1220104, BADGE_1_1220118,
                BADGE_1_1220122, BADGE_1_1220803, BADGE_1_1222016, BADGE_2_1031105, BADGE_2_1031109, BADGE_2_1031113,
                BADGE_2_1031115, BADGE_2_1031119, BADGE_2_1031120, BADGE_2_1031121, BADGE_2_1031406, BADGE_2_1031619,
                BADGE_2_1031923, BADGE_2_1032415, BADGE_2_1190304, BADGE_2_1190507, BADGE_2_1190601, BADGE_2_1191819,
                BADGE_2_1220104, BADGE_2_1220118, BADGE_2_1220122, BADGE_2_1220803, BADGE_2_1222016, BADGE_3_1031105,
                BADGE_3_1031109, BADGE_3_1031113, BADGE_3_1031115, BADGE_3_1031119, BADGE_3_1031120, BADGE_3_1031121,
                BADGE_3_1031406, BADGE_3_1031619, BADGE_3_1031923, BADGE_3_1032415, BADGE_3_1190304, BADGE_3_1190507,
                BADGE_3_1190601, BADGE_3_1191819, BADGE_3_1220104, BADGE_3_1220118, BADGE_3_1220122, BADGE_3_1220803,
                BADGE_3_1222016, MEDAL_2020419, MEDAL_2020719, MEDAL_2020903, MEDAL_2020913, MEDAL_2020919,
                MEDAL_2021322, MEDAL_2021403, MEDAL_2021613, MEDAL_2051902, MEDAL_2051907, MEDAL_2051919, MEDAL_2190303,
                MEDAL_2190308, MEDAL_2190309, MEDAL_2190318, MEDAL_2190703, MEDAL_2191319, MEDAL_2191608, MEDAL_3270519,
                RIBBON_3040109, RIBBON_3040718, RIBBON_3150914, RIBBON_3151920, RIBBON_3190105, RIBBON_3190118,
                RIBBON_3190318, RIBBON_3190409, RIBBON_3190605, RIBBON_3190803, RIBBON_3191305, RIBBON_3211305,
                RIBBON_3212201, RIBBON_3240102, RIBBON_3240301, RIBBON_3240703, RIBBON_3241213, RIBBON_3242303,};
        return Arrays.asList(awards);
    }

    public static List<NodeTextEnum> getMaps() {
        NodeTextEnum[] maps = {MAP_0, MAP_1, MAP_2, MAP_3, MAP_4, MAP_5, MAP_6, MAP_10, MAP_11, MAP_12, MAP_100,
                MAP_101, MAP_102, MAP_103, MAP_105, MAP_110, MAP_200, MAP_201, MAP_202, MAP_601, MAP_700};
        return Arrays.asList(maps);
    }
}