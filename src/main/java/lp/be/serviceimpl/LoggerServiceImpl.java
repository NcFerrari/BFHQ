package lp.be.serviceimpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lp.be.service.LoggerService;
import lp.fe.enums.NamespaceEnum;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class LoggerServiceImpl implements LoggerService {
    private static LoggerService loggerService;
    private static Logger log;

    public static <T> LoggerService getInstance(Class<T> classLogging) {
        return getInstance(classLogging, false);
    }

    public static <T> LoggerService getInstance(Class<T> classLogging, boolean saveToFile) {
        if (loggerService == null) {
            loggerService = new LoggerServiceImpl();
        }

        if (saveToFile) {
            saveToFile();
        }

        log = Logger.getLogger(classLogging);
        return loggerService;
    }

    private static void saveToFile() {
        String dailyFolder = LocalDate.now().format(DateTimeFormatter.ofPattern(
                NamespaceEnum.DATE_FORMAT.getText()));
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(NamespaceEnum.HOUR_FORMAT.getText()));
        DailyRollingFileAppender daily = null;

        try {
            daily = new DailyRollingFileAppender(new PatternLayout(NamespaceEnum.LOG_PATTERN.getText()),
                    String.format(NamespaceEnum.LOG_FILE_FORMAT.getText(), dailyFolder, time),
                    NamespaceEnum.DATE_FORMAT.getText());
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        Logger.getRootLogger().addAppender(daily);
    }

    private LoggerServiceImpl() {
    }

    public Logger getLog() {
        return log;
    }
}
