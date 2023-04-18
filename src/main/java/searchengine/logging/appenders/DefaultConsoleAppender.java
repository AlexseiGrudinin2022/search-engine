package searchengine.logging.appenders;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.Encoder;
import searchengine.logging.appenders.basic.DefaultBasicAppender;


import java.util.Optional;

/**
 * Класс реализует конфигурирование и получение ConsoleAppender,
 *
 * @author Grudinin.AM
 * @since 18.04.2023
 */
public class DefaultConsoleAppender extends DefaultBasicAppender {
    private final String STANDARD_CONSOLE_APPENDER_NAME = "console";
    private final Logger rootLogger;

    public DefaultConsoleAppender(LoggerContext loggerContext, Logger rootLogger) {
        super(loggerContext);
        this.rootLogger = rootLogger;
    }

    private void stopStandardConsoleAppender() {
        Optional
                .ofNullable(this.rootLogger.getAppender(STANDARD_CONSOLE_APPENDER_NAME))
                .ifPresent(
                        appender -> {
                            if (appender.isStarted()) {
                                appender.stop();
                            }
                        }
                );
    }

    public void startStandardConsoleAppender() {
        Optional
                .ofNullable(this.rootLogger.getAppender(STANDARD_CONSOLE_APPENDER_NAME))
                .ifPresent(
                        appender -> {
                            if (!appender.isStarted()) {
                                appender.start();
                            }
                        }
                );
    }

    public ConsoleAppender<ILoggingEvent> getConsoleAppender(Encoder<ILoggingEvent> encoder) {
        stopStandardConsoleAppender();
        ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
        appender.setName(getAppenderName());
        appender.setContext(super.getLoggerContext());
        appender.setEncoder(encoder);
        appender.addFilter(super.getThresholdLoggerFilter());
        appender.start();
        return appender;
    }

    @Override
    public String getAppenderName() {
        return String.format("consoleAppender[%s]", super.getThresholdLoggerFilter().getLevel());
    }
}
