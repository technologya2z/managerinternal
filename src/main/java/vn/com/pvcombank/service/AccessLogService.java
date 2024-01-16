package vn.com.pvcombank.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.AccessLog;
import vn.com.pvcombank.repository.AccessLogRepository;

/**
 * Service Implementation for managing {@link AccessLog}.
 */
@Service
@Transactional
public class AccessLogService {

    private final Logger log = LoggerFactory.getLogger(AccessLogService.class);

    private final AccessLogRepository accessLogRepository;

    public AccessLogService(AccessLogRepository accessLogRepository) {
        this.accessLogRepository = accessLogRepository;
    }

    /**
     * Save a accessLog.
     *
     * @param accessLog the entity to save.
     * @return the persisted entity.
     */
    public AccessLog save(AccessLog accessLog) {
        log.debug("Request to save AccessLog : {}", accessLog);
        return accessLogRepository.save(accessLog);
    }

    /**
     * Update a accessLog.
     *
     * @param accessLog the entity to save.
     * @return the persisted entity.
     */
    public AccessLog update(AccessLog accessLog) {
        log.debug("Request to save AccessLog : {}", accessLog);
        return accessLogRepository.save(accessLog);
    }

    /**
     * Partially update a accessLog.
     *
     * @param accessLog the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AccessLog> partialUpdate(AccessLog accessLog) {
        log.debug("Request to partially update AccessLog : {}", accessLog);

        return accessLogRepository
            .findById(accessLog.getId())
            .map(existingAccessLog -> {
                if (accessLog.getEmpCode() != null) {
                    existingAccessLog.setEmpCode(accessLog.getEmpCode());
                }
                if (accessLog.getEmpUsername() != null) {
                    existingAccessLog.setEmpUsername(accessLog.getEmpUsername());
                }
                if (accessLog.getEmpFullName() != null) {
                    existingAccessLog.setEmpFullName(accessLog.getEmpFullName());
                }
                if (accessLog.getAccessResource() != null) {
                    existingAccessLog.setAccessResource(accessLog.getAccessResource());
                }
                if (accessLog.getDescription() != null) {
                    existingAccessLog.setDescription(accessLog.getDescription());
                }
                if (accessLog.getIpAddress() != null) {
                    existingAccessLog.setIpAddress(accessLog.getIpAddress());
                }
                if (accessLog.getAccessTime() != null) {
                    existingAccessLog.setAccessTime(accessLog.getAccessTime());
                }

                return existingAccessLog;
            })
            .map(accessLogRepository::save);
    }

    /**
     * Get all the accessLogs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AccessLog> findAll(Pageable pageable) {
        log.debug("Request to get all AccessLogs");
        return accessLogRepository.findAll(pageable);
    }

    /**
     * Get one accessLog by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AccessLog> findOne(Long id) {
        log.debug("Request to get AccessLog : {}", id);
        return accessLogRepository.findById(id);
    }

    /**
     * Delete the accessLog by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete AccessLog : {}", id);
        accessLogRepository.deleteById(id);
    }
}
