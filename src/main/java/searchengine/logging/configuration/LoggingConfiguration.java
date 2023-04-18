package searchengine.logging.configuration;

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
        consoleAppender = new DefaultConsoleAppender(context, rootLogger);
    }

    public static void activateConsoleLogging() {
        rootLogger.addAppender(consoleAppender.getConsoleAppender(new DefaultTextEncoder()));
    }
}
