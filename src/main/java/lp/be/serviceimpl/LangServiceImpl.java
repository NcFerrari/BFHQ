package lp.be.serviceimpl;

import lombok.Getter;
import lombok.Setter;
import lp.be.service.LangService;
import lp.fe.enums.LangEnum;

@Setter
@Getter
public class LangServiceImpl implements LangService {

    private static LangService langService;
    private LangEnum selectedLanguage;

    public static LangService getInstance() {
        if (langService == null) {
            langService = new LangServiceImpl();
        }
        return langService;
    }

    private LangServiceImpl() {
        setSelectedLanguage(LangEnum.CZE);
    }
}
