package searchengine.logging.appenders.basic;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;

/**
 * Класс описывает стандартную реализацию Appender
 *
 * @author Grudinin.AM
 * @since 18.04.2023
 */
public abstract class DefaultBasicAppender {
    private final LoggerContext loggerContext;
    private final ThresholdLoggerFilter thresholdLoggerFilter;

    protected DefaultBasicAppender(LoggerContext loggerContext) {
        this.loggerContext = loggerContext;
        this.thresholdLoggerFilter = new ThresholdLoggerFilter();
        this.thresholdLoggerFilter.setLevel(Level.ALL);
    }

    public void setThresholdLevel(Level level) {
        if (level != this.thresholdLoggerFilter.getLevel()) {
            thresholdLoggerFilter.stop();
            thresholdLoggerFilter.setLevel(level);
            thresholdLoggerFilter.start();
        }
    }

    protected LoggerContext getLoggerContext() {
        return loggerContext;
    }

    protected ThresholdLoggerFilter getThresholdLoggerFilter() {
        return thresholdLoggerFilter;
    }

    protected abstract String getAppenderName();
}
