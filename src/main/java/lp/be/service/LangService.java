package lp.be.service;

import lp.fe.enums.LangEnum;

public interface LangService {

    LangEnum getSelectedLanguage();

    void setSelectedLanguage(LangEnum language);
}
