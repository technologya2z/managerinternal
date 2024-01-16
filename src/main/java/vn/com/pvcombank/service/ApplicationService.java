package vn.com.pvcombank.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.service.dto.ApplicationDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.Application}.
 */
public interface ApplicationService {
    /**
     * Save a application.
     *
     * @param applicationDTO the entity to save.
     * @return the persisted entity.
     */
    ApplicationDTO save(ApplicationDTO applicationDTO);

    /**
     * Updates a application.
     *
     * @param applicationDTO the entity to update.
     * @return the persisted entity.
     */
    ApplicationDTO update(ApplicationDTO applicationDTO);

    /**
     * Partially updates a application.
     *
     * @param applicationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApplicationDTO> partialUpdate(ApplicationDTO applicationDTO);

    /**
     * Get all the applications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Application> findAll(Pageable pageable);

    /**
     * Get all the applications with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApplicationDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" application.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Application> findOne(Long id);

    /**
     * Delete the "id" application.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
