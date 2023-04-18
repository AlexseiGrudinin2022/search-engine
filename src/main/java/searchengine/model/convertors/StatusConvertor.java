package searchengine.model.convertors;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import searchengine.model.statuses.IndexingStatus;


@Converter(autoApply = true)
public class StatusConvertor implements AttributeConverter<IndexingStatus, String> {

    @Override
    public String convertToDatabaseColumn(IndexingStatus indexingStatus) {
        return indexingStatus.toString();
    }

    @Override
    public IndexingStatus convertToEntityAttribute(String indexingStatus) {
        return IndexingStatus.getEnum(indexingStatus);
    }
}
