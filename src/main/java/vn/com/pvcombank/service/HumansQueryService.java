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
import vn.com.pvcombank.repository.HumansRepository;
import vn.com.pvcombank.service.criteria.HumansCriteria;
import vn.com.pvcombank.service.dto.HumansDTO;
import vn.com.pvcombank.service.mapper.HumansMapper;

/**
 * Service for executing complex queries for {@link Humans} entities in the database.
 * The main input is a {@link HumansCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HumansDTO} or a {@link Page} of {@link HumansDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HumansQueryService extends QueryService<Humans> {

    private final Logger log = LoggerFactory.getLogger(HumansQueryService.class);

    private final HumansRepository humansRepository;

    private final HumansMapper humansMapper;

    public HumansQueryService(HumansRepository humansRepository, HumansMapper humansMapper) {
        this.humansRepository = humansRepository;
        this.humansMapper = humansMapper;
    }

    /**
     * Return a {@link List} of {@link HumansDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HumansDTO> findByCriteria(HumansCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Humans> specification = createSpecification(criteria);
        return humansMapper.toDto(humansRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HumansDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Humans> findByCriteria(HumansCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Humans> specification = createSpecification(criteria);
        return humansRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HumansCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Humans> specification = createSpecification(criteria);
        return humansRepository.count(specification);
    }

    /**
     * Function to convert {@link HumansCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Humans> createSpecification(HumansCriteria criteria) {
        Specification<Humans> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Humans_.id));
            }
            if (criteria.getFullName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullName(), Humans_.fullName));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Humans_.code));
            }
            if (criteria.getUserName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserName(), Humans_.userName));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Humans_.email));
            }
            if (criteria.getactive() != null) {
                specification = specification.and(buildStringSpecification(criteria.getactive(), Humans_.active));
            }
            if (criteria.getPhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneNumber(), Humans_.phoneNumber));
            }
            if (criteria.getDateOfBirth() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateOfBirth(), Humans_.dateOfBirth));
            }
            if (criteria.getHomePhome() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHomePhome(), Humans_.homePhome));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Humans_.address));
            }
            if (criteria.getWards() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWards(), Humans_.wards));
            }
            if (criteria.getDistrict() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDistrict(), Humans_.district));
            }
            if (criteria.getProvince() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProvince(), Humans_.province));
            }
            if (criteria.getJoinDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJoinDate(), Humans_.joinDate));
            }
            if (criteria.getMarried() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMarried(), Humans_.married));
            }
            if (criteria.getGender() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGender(), Humans_.gender));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Humans_.description));
            }
            if (criteria.getJobtitleName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobtitleName(), Humans_.jobtitleName));
            }
            if (criteria.getApplicationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationId(),
                            root -> root.join(Humans_.applications, JoinType.LEFT).get(Application_.id)
                        )
                    );
            }
            if (criteria.getJobtitleId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getJobtitleId(), root -> root.join(Humans_.jobtitles, JoinType.LEFT).get(Jobtitle_.id))
                    );
            }
            if (criteria.getDepartmentId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getDepartmentId(),
                            root -> root.join(Humans_.department, JoinType.LEFT).get(Department_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
