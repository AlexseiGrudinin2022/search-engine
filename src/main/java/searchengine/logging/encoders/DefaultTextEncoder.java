package searchengine.logging.encoders;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import searchengine.logging.layouts.StructureTextLayout;

public class DefaultTextEncoder extends LayoutWrappingEncoder<ILoggingEvent> {
    public DefaultTextEncoder() {
        super.setLayout(new StructureTextLayout());
    }
}
