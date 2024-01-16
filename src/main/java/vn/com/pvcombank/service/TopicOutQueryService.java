package vn.com.pvcombank.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import vn.com.pvcombank.domain.*; // for static metamodels
import vn.com.pvcombank.domain.TopicOut;
import vn.com.pvcombank.repository.TopicOutRepository;
import vn.com.pvcombank.service.criteria.TopicOutCriteria;
import vn.com.pvcombank.service.dto.TopicOutDTO;
import vn.com.pvcombank.service.mapper.TopicOutMapper;

/**
 * Service for executing complex queries for {@link TopicOut} entities in the database.
 * The main input is a {@link TopicOutCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TopicOutDTO} or a {@link Page} of {@link TopicOutDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TopicOutQueryService extends QueryService<TopicOut> {

    private final Logger log = LoggerFactory.getLogger(TopicOutQueryService.class);

    private final TopicOutRepository topicOutRepository;

    private final TopicOutMapper topicOutMapper;

    public TopicOutQueryService(TopicOutRepository topicOutRepository, TopicOutMapper topicOutMapper) {
        this.topicOutRepository = topicOutRepository;
        this.topicOutMapper = topicOutMapper;
    }

    /**
     * Return a {@link List} of {@link TopicOutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TopicOut> findByCriteria(TopicOutCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TopicOut> specification = createSpecification(criteria);
        return topicOutRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link TopicOutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TopicOut> findByCriteria(TopicOutCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TopicOut> specification = createSpecification(criteria);
        return topicOutRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TopicOutCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TopicOut> specification = createSpecification(criteria);
        return topicOutRepository.count(specification);
    }

    /**
     * Function to convert {@link TopicOutCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TopicOut> createSpecification(TopicOutCriteria criteria) {
        Specification<TopicOut> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TopicOut_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TopicOut_.description));
            }
            if (criteria.getDateConnect() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateConnect(), TopicOut_.dateConnect));
            }
            if (criteria.getTopicId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getTopicId(), root -> root.join(TopicOut_.topic, JoinType.LEFT).get(Topic_.id))
                    );
            }
            if (criteria.getApplicationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationId(),
                            root -> root.join(TopicOut_.application, JoinType.LEFT).get(Application_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
