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
import vn.com.pvcombank.repository.ApplicationRepository;
import vn.com.pvcombank.service.criteria.ApplicationCriteria;
import vn.com.pvcombank.service.dto.ApplicationDTO;
import vn.com.pvcombank.service.mapper.ApplicationMapper;

/**
 * Service for executing complex queries for {@link Application} entities in the database.
 * The main input is a {@link ApplicationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApplicationDTO} or a {@link Page} of {@link ApplicationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApplicationQueryService extends QueryService<Application> {

    private final Logger log = LoggerFactory.getLogger(ApplicationQueryService.class);

    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    public ApplicationQueryService(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    /**
     * Return a {@link List} of {@link ApplicationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApplicationDTO> findByCriteria(ApplicationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Application> specification = createSpecification(criteria);
        return applicationMapper.toDto(applicationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApplicationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Application> findByCriteria(ApplicationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Application> specification = createSpecification(criteria);
        return applicationRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApplicationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Application> specification = createSpecification(criteria);
        return applicationRepository.count(specification);
    }

    /**
     * Function to convert {@link ApplicationCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Application> createSpecification(ApplicationCriteria criteria) {
        Specification<Application> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Application_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Application_.name));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Application_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Application_.description));
            }
            if (criteria.getLinkSourceRepository() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getLinkSourceRepository(), Application_.linkSourceRepository));
            }
            if (criteria.getLinkCmRepository() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLinkCmRepository(), Application_.linkCmRepository));
            }
            if (criteria.getServerlive() != null) {
                specification = specification.and(buildStringSpecification(criteria.getServerlive(), Application_.serverlive));
            }
            if (criteria.getServeruat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getServeruat(), Application_.serveruat));
            }
             if (criteria.getDocument() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDocument(), Application_.document));
            }

            if (criteria.getEnviroment() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEnviroment(), Application_.enviroment));
            }
            if (criteria.getSubComponent() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSubComponent(), Application_.subComponent));
            }
            if (criteria.getEpicJira() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEpicJira(), Application_.epicJira));
            }
            if (criteria.getDateStart() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateStart(), Application_.dateStart));
            }
            if (criteria.getAppType() != null) {
                specification = specification.and(buildSpecification(criteria.getAppType(), Application_.appType));
            }
            if (criteria.getDateGolive() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateGolive(), Application_.dateGolive));
            }
            if (criteria.getTopicInId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTopicInId(),
                            root -> root.join(Application_.topicIns, JoinType.LEFT).get(TopicIn_.id)
                        )
                    );
            }
            if (criteria.getTopicOutId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTopicOutId(),
                            root -> root.join(Application_.topicOuts, JoinType.LEFT).get(TopicOut_.id)
                        )
                    );
            }
            if (criteria.getApiInId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getApiInId(), root -> root.join(Application_.apiIns, JoinType.LEFT).get(ApiIn_.id))
                    );
            }
            if (criteria.getApiOutId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getApiOutId(), root -> root.join(Application_.apiOuts, JoinType.LEFT).get(ApiOut_.id))
                    );
            }
            if (criteria.getApiInfoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApiInfoId(),
                            root -> root.join(Application_.apiInfos, JoinType.LEFT).get(ApiInfo_.id)
                        )
                    );
            }
            if (criteria.getTopicId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getTopicId(), root -> root.join(Application_.topics, JoinType.LEFT).get(Topic_.id))
                    );
            }
            if (criteria.getOperaunitId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getOperaunitId(),
                            root -> root.join(Application_.operaunits, JoinType.LEFT).get(OperaUnit_.id)
                        )
                    );
            }
            if (criteria.getDatabaseinfoId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getDatabaseinfoId(),
                            root -> root.join(Application_.databaseinfos, JoinType.LEFT).get(DatabaseInfo_.id)
                        )
                    );
            }
            if (criteria.getHumansId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getHumansId(), root -> root.join(Application_.humans, JoinType.LEFT).get(Humans_.id))
                    );
            }
        }
        return specification;
    }
}
