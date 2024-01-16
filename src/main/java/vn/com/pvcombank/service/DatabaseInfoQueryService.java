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
import vn.com.pvcombank.domain.DatabaseInfo;
import vn.com.pvcombank.repository.DatabaseInfoRepository;
import vn.com.pvcombank.service.criteria.DatabaseInfoCriteria;
import vn.com.pvcombank.service.dto.DatabaseInfoDTO;
import vn.com.pvcombank.service.mapper.DatabaseInfoMapper;

/**
 * Service for executing complex queries for {@link DatabaseInfo} entities in the database.
 * The main input is a {@link DatabaseInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DatabaseInfoDTO} or a {@link Page} of {@link DatabaseInfoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DatabaseInfoQueryService extends QueryService<DatabaseInfo> {

    private final Logger log = LoggerFactory.getLogger(DatabaseInfoQueryService.class);

    private final DatabaseInfoRepository databaseInfoRepository;

    private final DatabaseInfoMapper databaseInfoMapper;

    public DatabaseInfoQueryService(DatabaseInfoRepository databaseInfoRepository, DatabaseInfoMapper databaseInfoMapper) {
        this.databaseInfoRepository = databaseInfoRepository;
        this.databaseInfoMapper = databaseInfoMapper;
    }

    /**
     * Return a {@link List} of {@link DatabaseInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DatabaseInfoDTO> findByCriteria(DatabaseInfoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DatabaseInfo> specification = createSpecification(criteria);
        return databaseInfoMapper.toDto(databaseInfoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DatabaseInfoDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DatabaseInfoDTO> findByCriteria(DatabaseInfoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DatabaseInfo> specification = createSpecification(criteria);
        return databaseInfoRepository.findAll(specification, page).map(databaseInfoMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DatabaseInfoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DatabaseInfo> specification = createSpecification(criteria);
        return databaseInfoRepository.count(specification);
    }

    /**
     * Function to convert {@link DatabaseInfoCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DatabaseInfo> createSpecification(DatabaseInfoCriteria criteria) {
        Specification<DatabaseInfo> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DatabaseInfo_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), DatabaseInfo_.name));
            }
            if (criteria.getServiceName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getServiceName(), DatabaseInfo_.serviceName));
            }
            if (criteria.getUserName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserName(), DatabaseInfo_.userName));
            }
            if (criteria.getPassWord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPassWord(), DatabaseInfo_.passWord));
            }
            if (criteria.getIpServer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIpServer(), DatabaseInfo_.ipServer));
            }
            if (criteria.getPort() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPort(), DatabaseInfo_.port));
            }
            if (criteria.getDataType() != null) {
                specification = specification.and(buildSpecification(criteria.getDataType(), DatabaseInfo_.dataType));
            }
            if (criteria.getApplicationId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationId(),
                            root -> root.join(DatabaseInfo_.applications, JoinType.LEFT).get(Application_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
