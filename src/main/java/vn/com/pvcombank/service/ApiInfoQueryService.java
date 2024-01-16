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
import vn.com.pvcombank.domain.ApiInfo;
import vn.com.pvcombank.repository.ApiInfoRepository;
import vn.com.pvcombank.service.criteria.ApiInfoCriteria;
import vn.com.pvcombank.service.dto.ApiInfoDTO;
import vn.com.pvcombank.service.mapper.ApiInfoMapper;

/**
 * Service for executing complex queries for {@link ApiInfo} entities in the database.
 * The main input is a {@link ApiInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApiInfoDTO} or a {@link Page} of {@link ApiInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApiInfoQueryService extends QueryService<ApiInfo> {

    private final Logger log = LoggerFactory.getLogger(ApiInfoQueryService.class);

    private final ApiInfoRepository apiInfoRepository;

    private final ApiInfoMapper apiInfoMapper;

    public ApiInfoQueryService(ApiInfoRepository apiInfoRepository, ApiInfoMapper apiInfoMapper) {
        this.apiInfoRepository = apiInfoRepository;
        this.apiInfoMapper = apiInfoMapper;
    }

    /**
     * Return a {@link List} of {@link ApiInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApiInfoDTO> findByCriteria(ApiInfoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApiInfo> specification = createSpecification(criteria);
        return apiInfoMapper.toDto(apiInfoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApiInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApiInfo> findByCriteria(ApiInfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApiInfo> specification = createSpecification(criteria);
        return apiInfoRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApiInfoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApiInfo> specification = createSpecification(criteria);
        return apiInfoRepository.count(specification);
    }

    /**
     * Function to convert {@link ApiInfoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ApiInfo> createSpecification(ApiInfoCriteria criteria) {
        Specification<ApiInfo> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ApiInfo_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ApiInfo_.name));
            }
            if (criteria.getPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPath(), ApiInfo_.path));
            }
            if (criteria.getRequestExample() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRequestExample(), ApiInfo_.requestExample));
            }
            if (criteria.getResponseExample() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResponseExample(), ApiInfo_.responseExample));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), ApiInfo_.dateCreate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ApiInfo_.description));
            }
            if (criteria.getApiInId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getApiInId(), root -> root.join(ApiInfo_.apiIns, JoinType.LEFT).get(ApiIn_.id))
                    );
            }
            if (criteria.getApiOutId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getApiOutId(), root -> root.join(ApiInfo_.apiOuts, JoinType.LEFT).get(ApiOut_.id))
                    );
            }
            if (criteria.getApplicationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationId(),
                            root -> root.join(ApiInfo_.application, JoinType.LEFT).get(Application_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
