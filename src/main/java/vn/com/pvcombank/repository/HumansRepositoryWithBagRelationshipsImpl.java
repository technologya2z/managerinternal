package vn.com.pvcombank.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import vn.com.pvcombank.domain.Humans;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class HumansRepositoryWithBagRelationshipsImpl implements HumansRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Humans> fetchBagRelationships(Optional<Humans> humans) {
        return humans.map(this::fetchJobtitles).map(this::fetchApplications);
    }

    @Override
    public Page<Humans> fetchBagRelationships(Page<Humans> humans) {
        return new PageImpl<>(fetchBagRelationships(humans.getContent()), humans.getPageable(), humans.getTotalElements());
    }

    @Override
    public List<Humans> fetchBagRelationships(List<Humans> humans) {
        return Optional.of(humans).map(this::fetchJobtitles).map(this::fetchApplications).orElse(Collections.emptyList());
    }

    Humans fetchJobtitles(Humans result) {
        return entityManager
            .createQuery("select humans from Humans humans left join fetch humans.jobtitles where humans is :humans", Humans.class)
            .setParameter("humans", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Humans> fetchJobtitles(List<Humans> humans) {
        return entityManager
            .createQuery("select distinct humans from Humans humans left join fetch humans.jobtitles where humans in :humans", Humans.class)
            .setParameter("humans", humans)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }

    Humans fetchApplications(Humans result) {
        return entityManager
            .createQuery("select humans from Humans humans left join fetch humans.applications where humans is :humans", Humans.class)
            .setParameter("humans", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Humans> fetchApplications(List<Humans> humans) {
        return entityManager
            .createQuery(
                "select distinct humans from Humans humans left join fetch humans.applications where humans in :humans",
                Humans.class
            )
            .setParameter("humans", humans)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
