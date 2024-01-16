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
import vn.com.pvcombank.domain.Jobtitle;
import vn.com.pvcombank.repository.JobtitleRepository;
import vn.com.pvcombank.service.criteria.JobtitleCriteria;
import vn.com.pvcombank.service.dto.JobtitleDTO;
import vn.com.pvcombank.service.mapper.JobtitleMapper;

/**
 * Service for executing complex queries for {@link Jobtitle} entities in the database.
 * The main input is a {@link JobtitleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JobtitleDTO} or a {@link Page} of {@link JobtitleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JobtitleQueryService extends QueryService<Jobtitle> {

    private final Logger log = LoggerFactory.getLogger(JobtitleQueryService.class);

    private final JobtitleRepository jobtitleRepository;

    private final JobtitleMapper jobtitleMapper;

    public JobtitleQueryService(JobtitleRepository jobtitleRepository, JobtitleMapper jobtitleMapper) {
        this.jobtitleRepository = jobtitleRepository;
        this.jobtitleMapper = jobtitleMapper;
    }

    /**
     * Return a {@link List} of {@link JobtitleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<JobtitleDTO> findByCriteria(JobtitleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Jobtitle> specification = createSpecification(criteria);
        return jobtitleMapper.toDto(jobtitleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link JobtitleDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JobtitleDTO> findByCriteria(JobtitleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Jobtitle> specification = createSpecification(criteria);
        return jobtitleRepository.findAll(specification, page).map(jobtitleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(JobtitleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Jobtitle> specification = createSpecification(criteria);
        return jobtitleRepository.count(specification);
    }

    /**
     * Function to convert {@link JobtitleCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Jobtitle> createSpecification(JobtitleCriteria criteria) {
        Specification<Jobtitle> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Jobtitle_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Jobtitle_.name));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Jobtitle_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Jobtitle_.description));
            }
            if (criteria.getApplicationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationId(),
                            root -> root.join(Jobtitle_.applications, JoinType.LEFT).get(Humans_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
