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
import vn.com.pvcombank.domain.OperaUnit;
import vn.com.pvcombank.repository.OperaUnitRepository;
import vn.com.pvcombank.service.criteria.OperaUnitCriteria;
import vn.com.pvcombank.service.dto.OperaUnitDTO;
import vn.com.pvcombank.service.mapper.OperaUnitMapper;

/**
 * Service for executing complex queries for {@link OperaUnit} entities in the database.
 * The main input is a {@link OperaUnitCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OperaUnitDTO} or a {@link Page} of {@link OperaUnitDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OperaUnitQueryService extends QueryService<OperaUnit> {

    private final Logger log = LoggerFactory.getLogger(OperaUnitQueryService.class);

    private final OperaUnitRepository operaUnitRepository;

    private final OperaUnitMapper operaUnitMapper;

    public OperaUnitQueryService(OperaUnitRepository operaUnitRepository, OperaUnitMapper operaUnitMapper) {
        this.operaUnitRepository = operaUnitRepository;
        this.operaUnitMapper = operaUnitMapper;
    }

    /**
     * Return a {@link List} of {@link OperaUnitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OperaUnitDTO> findByCriteria(OperaUnitCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OperaUnit> specification = createSpecification(criteria);
        return operaUnitMapper.toDto(operaUnitRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OperaUnitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OperaUnit> findByCriteria(OperaUnitCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OperaUnit> specification = createSpecification(criteria);
        return operaUnitRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OperaUnitCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OperaUnit> specification = createSpecification(criteria);
        return operaUnitRepository.count(specification);
    }

    /**
     * Function to convert {@link OperaUnitCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<OperaUnit> createSpecification(OperaUnitCriteria criteria) {
        Specification<OperaUnit> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), OperaUnit_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), OperaUnit_.name));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), OperaUnit_.code));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), OperaUnit_.address));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), OperaUnit_.email));
            }
            if (criteria.getPhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneNumber(), OperaUnit_.phoneNumber));
            }
            if (criteria.getOwner() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOwner(), OperaUnit_.owner));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), OperaUnit_.description));
            }
            if (criteria.getApplicationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationId(),
                            root -> root.join(OperaUnit_.applications, JoinType.LEFT).get(Application_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
