package vn.com.pvcombank.repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import vn.com.pvcombank.domain.Topic;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class TopicRepositoryWithBagRelationshipsImpl implements TopicRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Topic> fetchBagRelationships(Optional<Topic> topic) {
        return topic.map(this::fetchApplications);
    }

    @Override
    public Page<Topic> fetchBagRelationships(Page<Topic> topics) {
        return new PageImpl<>(fetchBagRelationships(topics.getContent()), topics.getPageable(), topics.getTotalElements());
    }

    @Override
    public List<Topic> fetchBagRelationships(List<Topic> topics) {
        return Optional.of(topics).map(this::fetchApplications).orElse(Collections.emptyList());
    }

    Topic fetchApplications(Topic result) {
        return entityManager
            .createQuery("select topic from Topic topic left join fetch topic.applications where topic is :topic", Topic.class)
            .setParameter("topic", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Topic> fetchApplications(List<Topic> topics) {
        return entityManager
            .createQuery("select distinct topic from Topic topic left join fetch topic.applications where topic in :topics", Topic.class)
            .setParameter("topics", topics)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
