package searchengine.model.statuses;

import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.util.EnumUtils;

/**
 * Enum содержит перечисления возможных статусов индексации сайта
 *
 * @author Grudinin.AM
 * @since 14.04.2023
 */
public enum IndexingStatus {
    INDEXING("Индексация"),
    INDEXED("Проиндексированно"),
    FAILED("Ошибка индексации");

    private final String status;

    IndexingStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

    public static IndexingStatus getEnum(String status) {
        try {
            return EnumUtils.findEnumInsensitiveCase(IndexingStatus.class, status);
        } catch (Exception e) {
            LoggerFactory.getLogger(IndexingStatus.class)
                    .atError()
                    .addKeyValue("status", status)
                    .setCause(e)
                    .log("Ошибка при получении статуса индексации");
            return null;
        }

    }
}