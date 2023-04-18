package searchengine.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import searchengine.model.Site;


@Transactional
@Repository
public class SiteService {

    private final static Logger logger = LoggerFactory.getLogger(SiteService.class);
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Site site) {
        try {
            this.entityManager.persist(site);
            logger.atInfo()
                    .addKeyValue("@site", site)
                    .addKeyValue("siteId", site.getId())
                    .log("Данные сохранены!");
        } catch (Exception e) {
            logger.atError()
                    .setCause(e)
                    .addKeyValue("siteId", site.getId())
                    .log("Ошибка сохранения данных!");
        }
    }
}
