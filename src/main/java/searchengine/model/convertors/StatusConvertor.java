package searchengine.model.convertors;

import searchengine.model.statuses.IndexingStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
