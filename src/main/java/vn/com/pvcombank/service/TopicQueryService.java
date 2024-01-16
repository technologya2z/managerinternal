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
import vn.com.pvcombank.domain.Topic;
import vn.com.pvcombank.repository.TopicRepository;
import vn.com.pvcombank.service.criteria.TopicCriteria;
import vn.com.pvcombank.service.dto.TopicDTO;
import vn.com.pvcombank.service.mapper.TopicMapper;

/**
 * Service for executing complex queries for {@link Topic} entities in the database.
 * The main input is a {@link TopicCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TopicDTO} or a {@link Page} of {@link TopicDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TopicQueryService extends QueryService<Topic> {

    private final Logger log = LoggerFactory.getLogger(TopicQueryService.class);

    private final TopicRepository topicRepository;

    private final TopicMapper topicMapper;

    public TopicQueryService(TopicRepository topicRepository, TopicMapper topicMapper) {
        this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

    /**
     * Return a {@link List} of {@link TopicDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TopicDTO> findByCriteria(TopicCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Topic> specification = createSpecification(criteria);
        return topicMapper.toDto(topicRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TopicDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Topic> findByCriteria(TopicCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Topic> specification = createSpecification(criteria);
        return topicRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TopicCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Topic> specification = createSpecification(criteria);
        return topicRepository.count(specification);
    }

    /**
     * Function to convert {@link TopicCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Topic> createSpecification(TopicCriteria criteria) {
        Specification<Topic> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Topic_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Topic_.name));
            }
            if (criteria.getBootrapServer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBootrapServer(), Topic_.bootrapServer));
            }
            if (criteria.getLocationTopic() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocationTopic(), Topic_.locationTopic));
            }
            if (criteria.getRootproducer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRootproducer(), Topic_.rootproducer));
            }
            if (criteria.getRootTable() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRootTable(), Topic_.rootTable));
            }
            if (criteria.getMessage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessage(), Topic_.message));
            }
            if (criteria.getLinkDoc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLinkDoc(), Topic_.linkDoc));
            }
            if (criteria.getTopicInId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getTopicInId(), root -> root.join(Topic_.topicIns, JoinType.LEFT).get(TopicIn_.id))
                    );
            }
            if (criteria.getTopicOutId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getTopicOutId(), root -> root.join(Topic_.topicOuts, JoinType.LEFT).get(TopicOut_.id))
                    );
            }
            if (criteria.getApplicationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationId(),
                            root -> root.join(Topic_.applications, JoinType.LEFT).get(Application_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
