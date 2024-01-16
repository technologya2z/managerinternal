package vn.com.pvcombank.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import vn.com.pvcombank.domain.Application;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ApplicationRepositoryWithBagRelationshipsImpl implements ApplicationRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Application> fetchBagRelationships(Optional<Application> application) {
        return application.map(this::fetchTopics).map(this::fetchOperaunits).map(this::fetchDatabaseinfos).map(this::fetchHumans);
    }

    @Override
    public Page<Application> fetchBagRelationships(Page<Application> applications) {
        return new PageImpl<>(
            fetchBagRelationships(applications.getContent()),
            applications.getPageable(),
            applications.getTotalElements()
        );
    }

    @Override
    public List<Application> fetchBagRelationships(List<Application> applications) {
        return Optional
            .of(applications)
            .map(this::fetchTopics)
            .map(this::fetchOperaunits)
            .map(this::fetchDatabaseinfos)
            .map(this::fetchHumans)
            .orElse(Collections.emptyList());
    }

    Application fetchTopics(Application result) {
        return entityManager
            .createQuery(
                "select application from Application application left join fetch application.topics where application is :application",
                Application.class
            )
            .setParameter("application", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Application> fetchTopics(List<Application> applications) {
        return entityManager
            .createQuery(
                "select distinct application from Application application left join fetch application.topics where application in :applications",
                Application.class
            )
            .setParameter("applications", applications)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }

    Application fetchOperaunits(Application result) {
        return entityManager
            .createQuery(
                "select application from Application application left join fetch application.operaunits where application is :application",
                Application.class
            )
            .setParameter("application", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Application> fetchOperaunits(List<Application> applications) {
        return entityManager
            .createQuery(
                "select distinct application from Application application left join fetch application.operaunits where application in :applications",
                Application.class
            )
            .setParameter("applications", applications)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }

    Application fetchDatabaseinfos(Application result) {
        return entityManager
            .createQuery(
                "select application from Application application left join fetch application.databaseinfos where application is :application",
                Application.class
            )
            .setParameter("application", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Application> fetchDatabaseinfos(List<Application> applications) {
        return entityManager
            .createQuery(
                "select distinct application from Application application left join fetch application.databaseinfos where application in :applications",
                Application.class
            )
            .setParameter("applications", applications)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }

    Application fetchHumans(Application result) {
        return entityManager
            .createQuery(
                "select application from Application application left join fetch application.humans where application is :application",
                Application.class
            )
            .setParameter("application", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Application> fetchHumans(List<Application> applications) {
        return entityManager
            .createQuery(
                "select distinct application from Application application left join fetch application.humans where application in :applications",
                Application.class
            )
            .setParameter("applications", applications)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
