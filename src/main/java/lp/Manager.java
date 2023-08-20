package lp;

import lp.be.service.LangService;
import lp.be.serviceimpl.LangServiceImpl;
import lp.fe.enums.LangEnum;
import lp.fe.enums.NodeTextEnum;
import lp.fe.javafx.MainApp;

public class Manager {

    private static Manager manager;
    private final LangService langService = LangServiceImpl.getInstance();

    public static Manager getInstance() {
        if (manager == null) {
            manager = new Manager();
            javafx.application.Application.launch(MainApp.class);
        }
        return manager;
    }

    private Manager() {

    }

    public static void main(String[] args) {
        getInstance();
    }

    public LangEnum getLanguage() {
        return langService.getSelectedLanguage();
    }

    public void setLanguage(LangEnum language) {
        langService.setSelectedLanguage(language);
        NodeTextEnum.reloadTexts();
    }
}
