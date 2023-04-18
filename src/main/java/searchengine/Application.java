package searchengine;

import ch.qos.logback.classic.Level;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import searchengine.logging.configuration.LoggingConfiguration;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
            LoggingConfiguration.activateConsoleLogging(Level.INFO);
            LoggerFactory.getLogger(Application.class)
                    .atInfo()
                    .addKeyValue("@args", args)
                    .log("Приложение успешно инициализированно");
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при инициализации приложения");
        }
    }
}
