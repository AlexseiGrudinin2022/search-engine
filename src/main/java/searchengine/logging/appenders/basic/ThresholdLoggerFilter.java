package searchengine.logging.appenders.basic;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * Класс реализует политику фильтрации логов по их Level
 * @author Grudinin.AM
 * @since 18.04.2023
 */
public class ThresholdLoggerFilter extends Filter<ILoggingEvent> {
    private Level level;

    @Override
    public FilterReply decide(ILoggingEvent event) {
        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }
        if (event.getLevel().isGreaterOrEqual(level)) {
            return FilterReply.NEUTRAL;
        } else {
            return FilterReply.DENY;
        }
    }

    @Override
    public void start() {
        if (this.level != null) {
            super.start();
        }
    }
    @Override
    public void stop() {
        if (super.isStarted()) {
            super.stop();
        }
    }
    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

}