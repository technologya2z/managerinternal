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
import vn.com.pvcombank.domain.AccessLog;
import vn.com.pvcombank.repository.AccessLogRepository;
import vn.com.pvcombank.service.criteria.AccessLogCriteria;

/**
 * Service for executing complex queries for {@link AccessLog} entities in the database.
 * The main input is a {@link AccessLogCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AccessLog} or a {@link Page} of {@link AccessLog} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AccessLogQueryService extends QueryService<AccessLog> {

    private final Logger log = LoggerFactory.getLogger(AccessLogQueryService.class);

    private final AccessLogRepository accessLogRepository;

    public AccessLogQueryService(AccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    /**
     * Return a {@link List} of {@link AccessLog} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AccessLog> findByCriteria(AccessLogCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AccessLog> specification = createSpecification(criteria);
        return accessLogRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link AccessLog} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AccessLog> findByCriteria(AccessLogCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AccessLog> specification = createSpecification(criteria);
        return accessLogRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AccessLogCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AccessLog> specification = createSpecification(criteria);
        return accessLogRepository.count(specification);
    }

    /**
     * Function to convert {@link AccessLogCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AccessLog> createSpecification(AccessLogCriteria criteria) {
        Specification<AccessLog> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AccessLog_.id));
            }
            if (criteria.getEmpCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmpCode(), AccessLog_.empCode));
            }
            if (criteria.getEmpUsername() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmpUsername(), AccessLog_.empUsername));
            }
            if (criteria.getEmpFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmpFullName(), AccessLog_.empFullName));
            }
            if (criteria.getAccessResource() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccessResource(), AccessLog_.accessResource));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), AccessLog_.description));
            }
            if (criteria.getIpAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIpAddress(), AccessLog_.ipAddress));
            }
            if (criteria.getAccessTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAccessTime(), AccessLog_.accessTime));
            }
        }
        return specification;
    }
}
