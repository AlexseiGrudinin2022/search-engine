package searchengine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import searchengine.model.statuses.IndexingStatus;
import searchengine.model.convertors.StatusConvertor;

import java.util.Date;

@Getter
@Setter
@Entity
public class Site {
    private static final String ID_COMMENT = "ID информации о сайте";
    private static final String INDEXING_STATUS_COMMENT = "Текущий статус полной индексации сайта, отражающий готовность поискового движка осуществлять поиск по сайту";
    private static final String STATUS_TIME_COMMENT = "Дата и время статуса";
    private static final String LAST_ERROR_COMMENT = "Текст ошибки индексации";
    private static final String URL_COMMENT = "Адрес главной страницы сайта";
    private static final String NAME_COMMENT = "Имя сайта";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int auto_increment NOT NULL COMMENT '" + ID_COMMENT + "'")
    private Integer id;

    @Convert(attributeName = "status", converter = StatusConvertor.class)
    @Enumerated(EnumType.STRING)
    private IndexingStatus status;

    @Column(name = "status_time", columnDefinition = "DATETIME NOT NULL COMMENT '" + STATUS_TIME_COMMENT + "'")
    private Date statusTime;

    @Column(name = "last_error", columnDefinition = "TEXT NULL COMMENT '" + LAST_ERROR_COMMENT + "'")
    private String lastError;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT '" + URL_COMMENT + "'")
    private String url;

    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT '" + NAME_COMMENT + "'")
    private String name;
}
