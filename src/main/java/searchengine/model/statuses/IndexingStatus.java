package searchengine.model.statuses;

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
        for (IndexingStatus e : IndexingStatus.values()) {
            if (status.equalsIgnoreCase(e.status)) return e;
        }
        return null;
    }
}