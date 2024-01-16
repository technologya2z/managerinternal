package vn.com.pvcombank.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import vn.com.pvcombank.domain.Jobtitle;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class JobtitleRepositoryWithBagRelationshipsImpl implements JobtitleRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Jobtitle> fetchBagRelationships(Optional<Jobtitle> jobtitle) {
        return jobtitle.map(this::fetchHumans);
    }

    @Override
    public Page<Jobtitle> fetchBagRelationships(Page<Jobtitle> jobtitles) {
        return new PageImpl<>(fetchBagRelationships(jobtitles.getContent()), jobtitles.getPageable(), jobtitles.getTotalElements());
    }

    @Override
    public List<Jobtitle> fetchBagRelationships(List<Jobtitle> jobtitles) {
        return Optional.of(jobtitles).map(this::fetchHumans).orElse(Collections.emptyList());
    }

    Jobtitle fetchHumans(Jobtitle result) {
        return entityManager
            .createQuery(
                "select jobtitle from Jobtitle jobtitle left join fetch jobtitle.humans where jobtitle is :jobtitle",
                Jobtitle.class
            )
            .setParameter("jobtitle", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Jobtitle> fetchHumans(List<Jobtitle> jobtitles) {
        return entityManager
            .createQuery(
                "select distinct jobtitle from Jobtitle jobtitle left join fetch jobtitle.humans where jobtitle in :jobtitles",
                Jobtitle.class
            )
            .setParameter("jobtitles", jobtitles)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
