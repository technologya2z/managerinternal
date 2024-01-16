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
import vn.com.pvcombank.domain.TopicIn;
import vn.com.pvcombank.repository.TopicInRepository;
import vn.com.pvcombank.service.criteria.TopicInCriteria;
import vn.com.pvcombank.service.dto.TopicInDTO;
import vn.com.pvcombank.service.mapper.TopicInMapper;

/**
 * Service for executing complex queries for {@link TopicIn} entities in the database.
 * The main input is a {@link TopicInCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TopicInDTO} or a {@link Page} of {@link TopicInDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TopicInQueryService extends QueryService<TopicIn> {

    private final Logger log = LoggerFactory.getLogger(TopicInQueryService.class);

    private final TopicInRepository topicInRepository;

    private final TopicInMapper topicInMapper;

    public TopicInQueryService(TopicInRepository topicInRepository, TopicInMapper topicInMapper) {
        this.topicInRepository = topicInRepository;
        this.topicInMapper = topicInMapper;
    }

    /**
     * Return a {@link List} of {@link TopicInDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TopicIn> findByCriteria(TopicInCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TopicIn> specification = createSpecification(criteria);
        return topicInRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link TopicInDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TopicIn> findByCriteria(TopicInCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TopicIn> specification = createSpecification(criteria);
        return topicInRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TopicInCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TopicIn> specification = createSpecification(criteria);
        return topicInRepository.count(specification);
    }

    /**
     * Function to convert {@link TopicInCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TopicIn> createSpecification(TopicInCriteria criteria) {
        Specification<TopicIn> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TopicIn_.id));
            }
            if (criteria.getDateConnect() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateConnect(), TopicIn_.dateConnect));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TopicIn_.description));
            }
            if (criteria.getTopicId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getTopicId(), root -> root.join(TopicIn_.topic, JoinType.LEFT).get(Topic_.id))
                    );
            }
            if (criteria.getApplicationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationId(),
                            root -> root.join(TopicIn_.application, JoinType.LEFT).get(Application_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
