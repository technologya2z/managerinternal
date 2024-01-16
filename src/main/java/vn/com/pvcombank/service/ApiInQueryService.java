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
import vn.com.pvcombank.domain.ApiIn;
import vn.com.pvcombank.repository.ApiInRepository;
import vn.com.pvcombank.service.criteria.ApiInCriteria;
import vn.com.pvcombank.service.dto.ApiInDTO;
import vn.com.pvcombank.service.mapper.ApiInMapper;

/**
 * Service for executing complex queries for {@link ApiIn} entities in the database.
 * The main input is a {@link ApiInCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApiInDTO} or a {@link Page} of {@link ApiInDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApiInQueryService extends QueryService<ApiIn> {

    private final Logger log = LoggerFactory.getLogger(ApiInQueryService.class);

    private final ApiInRepository apiInRepository;

    private final ApiInMapper apiInMapper;

    public ApiInQueryService(ApiInRepository apiInRepository, ApiInMapper apiInMapper) {
        this.apiInRepository = apiInRepository;
        this.apiInMapper = apiInMapper;
    }

    /**
     * Return a {@link List} of {@link ApiInDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApiInDTO> findByCriteria(ApiInCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApiIn> specification = createSpecification(criteria);
        return apiInMapper.toDto(apiInRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApiInDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApiIn> findByCriteria(ApiInCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApiIn> specification = createSpecification(criteria);
        return apiInRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApiInCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApiIn> specification = createSpecification(criteria);
        return apiInRepository.count(specification);
    }

    /**
     * Function to convert {@link ApiInCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ApiIn> createSpecification(ApiInCriteria criteria) {
        Specification<ApiIn> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ApiIn_.id));
            }
            if (criteria.getDateConnect() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateConnect(), ApiIn_.dateConnect));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ApiIn_.description));
            }
            if (criteria.getApiInfoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getApiInfoId(), root -> root.join(ApiIn_.apiInfo, JoinType.LEFT).get(ApiInfo_.id))
                    );
            }
            if (criteria.getApplicationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationId(),
                            root -> root.join(ApiIn_.application, JoinType.LEFT).get(Application_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
