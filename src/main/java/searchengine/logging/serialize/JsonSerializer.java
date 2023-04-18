package searchengine.logging.serialize;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Класс реализуется возможность сериализации объектов в Json
 * @author Grudinin.AM
 * @since 18.04.2023
 */
public class JsonSerializer {
    /**
     *
     * @param object объект который необходимо сериализовать
     * @return String (json or ErrorMessage)
     */
    public static String serializeObjectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter writer = objectMapper.setVisibility(objectMapper
                        .getVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                )
                .writer();
        try {
            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return "Ошибка при сериализации: " + e;
        }
    }

}