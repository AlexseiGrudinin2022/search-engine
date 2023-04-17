package searchengine.services;

import org.springframework.stereotype.Repository;
import searchengine.model.Site;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@Repository
public class SiteService {
    @PersistenceContext
    private EntityManager entityManager;
    public void save(Site site) {
        try {
            entityManager.persist(site);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
