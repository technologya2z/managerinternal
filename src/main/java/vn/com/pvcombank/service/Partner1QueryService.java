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
import vn.com.pvcombank.domain.Partner1;
import vn.com.pvcombank.repository.Partner1Repository;
import vn.com.pvcombank.service.criteria.Partner1Criteria;

/**
 * Service for executing complex queries for {@link Partner1} entities in the database.
 * The main input is a {@link Partner1Criteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Partner1} or a {@link Page} of {@link Partner1} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class Partner1QueryService extends QueryService<Partner1> {

    private final Logger log = LoggerFactory.getLogger(Partner1QueryService.class);

    private final Partner1Repository partner1Repository;

    public Partner1QueryService(Partner1Repository partner1Repository) {
        this.partner1Repository = partner1Repository;
    }

    /**
     * Return a {@link List} of {@link Partner1} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Partner1> findByCriteria(Partner1Criteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Partner1> specification = createSpecification(criteria);
        return partner1Repository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Partner1} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Partner1> findByCriteria(Partner1Criteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Partner1> specification = createSpecification(criteria);
        return partner1Repository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(Partner1Criteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Partner1> specification = createSpecification(criteria);
        return partner1Repository.count(specification);
    }

    /**
     * Function to convert {@link Partner1Criteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Partner1> createSpecification(Partner1Criteria criteria) {
        Specification<Partner1> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Partner1_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Partner1_.name));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCode(), Partner1_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Partner1_.description));
            }
        }
        return specification;
    }
}
