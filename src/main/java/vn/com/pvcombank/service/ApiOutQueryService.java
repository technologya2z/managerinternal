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
import vn.com.pvcombank.domain.ApiOut;
import vn.com.pvcombank.repository.ApiOutRepository;
import vn.com.pvcombank.service.criteria.ApiOutCriteria;
import vn.com.pvcombank.service.dto.ApiOutDTO;
import vn.com.pvcombank.service.mapper.ApiOutMapper;

/**
 * Service for executing complex queries for {@link ApiOut} entities in the database.
 * The main input is a {@link ApiOutCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApiOutDTO} or a {@link Page} of {@link ApiOutDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApiOutQueryService extends QueryService<ApiOut> {

    private final Logger log = LoggerFactory.getLogger(ApiOutQueryService.class);

    private final ApiOutRepository apiOutRepository;

    private final ApiOutMapper apiOutMapper;

    public ApiOutQueryService(ApiOutRepository apiOutRepository, ApiOutMapper apiOutMapper) {
        this.apiOutRepository = apiOutRepository;
        this.apiOutMapper = apiOutMapper;
    }

    /**
     * Return a {@link List} of {@link ApiOutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApiOutDTO> findByCriteria(ApiOutCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApiOut> specification = createSpecification(criteria);
        return apiOutMapper.toDto(apiOutRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApiOutDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApiOut> findByCriteria(ApiOutCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApiOut> specification = createSpecification(criteria);
        return apiOutRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApiOutCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApiOut> specification = createSpecification(criteria);
        return apiOutRepository.count(specification);
    }

    /**
     * Function to convert {@link ApiOutCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ApiOut> createSpecification(ApiOutCriteria criteria) {
        Specification<ApiOut> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ApiOut_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ApiOut_.description));
            }
            if (criteria.getDateConnect() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateConnect(), ApiOut_.dateConnect));
            }
            if (criteria.getApiInfoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getApiInfoId(), root -> root.join(ApiOut_.apiInfo, JoinType.LEFT).get(ApiInfo_.id))
                    );
            }
            if (criteria.getApplicationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationId(),
                            root -> root.join(ApiOut_.application, JoinType.LEFT).get(Application_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
