package searchengine.logging.layouts;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import org.slf4j.event.KeyValuePair;
import searchengine.logging.serialize.JsonSerializer;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Класс реализует формирование полей и отображение в них сообщений для ConsoleAppender и RollingFileAppender
 *
 * @author Grudinin.AM
 * @since 18.04.2023
 */
public class StructureTextLayout extends LayoutBase<ILoggingEvent> {
    private String keyValuesPairToString(List<KeyValuePair> keyValuePairs) {
        StringBuilder objectBuilder = new StringBuilder();
        keyValuePairs.forEach(item -> {
            objectBuilder
                    .append(CoreConstants.TAB);
            if (item.key.startsWith("@")) {
                String key = item.key.substring(item.key.indexOf("@") + 1);
                objectBuilder.append(key).append("=").append(JsonSerializer.serializeObjectToJson(item.value));
            } else {
                objectBuilder.append(item.key).append("=").append(item.value);
            }
            objectBuilder.append(CoreConstants.LINE_SEPARATOR);
        });
        return objectBuilder.toString();
    }

    private String throwableProxyToString(IThrowableProxy proxy) {
        String stackTrace = Arrays.toString(proxy.getStackTraceElementProxyArray());
        stackTrace = stackTrace.substring(stackTrace.indexOf("[") + 1, stackTrace.lastIndexOf("]"));
        return String.format("%s: %s%n\t%s", proxy.getClassName(), proxy.getMessage(), stackTrace);
    }

    @Override
    public String doLayout(ILoggingEvent iLoggingEvent) {
        String keyValuePairs = "";
        String exceptionMessage = "";

        if (iLoggingEvent.getKeyValuePairs() != null && !iLoggingEvent.getKeyValuePairs().isEmpty()) {
            keyValuePairs = this.keyValuesPairToString(iLoggingEvent.getKeyValuePairs());
        }

        if (iLoggingEvent.getThrowableProxy() != null) {
            exceptionMessage = this.throwableProxyToString(iLoggingEvent.getThrowableProxy());
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS");
        return String.format("%s %s [%s] [%s] - %s%n%s%s",
                format.format(new Date(iLoggingEvent.getTimeStamp())),
                iLoggingEvent.getLevel(),
                iLoggingEvent.getThreadName(),
                iLoggingEvent.getLoggerName(),
                iLoggingEvent.getFormattedMessage(),
                keyValuePairs,
                exceptionMessage);
    }
}
