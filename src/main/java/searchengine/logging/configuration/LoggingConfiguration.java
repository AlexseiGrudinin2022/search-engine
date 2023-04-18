package searchengine.logging.configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import searchengine.logging.appenders.DefaultConsoleAppender;
import searchengine.logging.encoders.DefaultTextEncoder;


/**
 * Класс реализует возможность активировать необходимые проекту Appender
 *
 * @author Grudinin.AM
 * @since 18.04.2023
 */
public final class LoggingConfiguration {

    private static final LoggerContext context;
    private static final Logger rootLogger;
    private static final DefaultConsoleAppender consoleAppender;


    static {
        context = (LoggerContext) LoggerFactory.getILoggerFactory();
        rootLogger = context.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.setAdditive(true);
        context.reset();
        consoleAppender = new DefaultConsoleAppender(context, rootLogger);
    }

    /**
     * Метод активирует логирование в консоль используя Pattern
     */
    public static void activateConsoleLogging() {
        rootLogger.addAppender(consoleAppender.getConsoleAppender(new DefaultTextEncoder()));
    }

    /**
     * Метод активирует логирование в консоль используя StructureTextLayout с установленным уровнем логирования
     */
    public static void activateConsoleLogging(Level level) {
        consoleAppender.setThresholdLevel(level);
        activateConsoleLogging();
    }

}
