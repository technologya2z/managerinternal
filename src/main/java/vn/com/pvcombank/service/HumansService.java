package vn.com.pvcombank.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.Humans;
import vn.com.pvcombank.service.dto.HumansDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.Humans}.
 */
public interface HumansService {
    /**
     * Save a humans.
     *
     * @param humansDTO the entity to save.
     * @return the persisted entity.
     */
    HumansDTO save(HumansDTO humansDTO);

    /**
     * Updates a humans.
     *
     * @param humansDTO the entity to update.
     * @return the persisted entity.
     */
    HumansDTO update(HumansDTO humansDTO);

    /**
     * Partially updates a humans.
     *
     * @param humansDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<HumansDTO> partialUpdate(HumansDTO humansDTO);

    /**
     * Get all the humans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HumansDTO> findAll(Pageable pageable);

    /**
     * Get all the humans with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HumansDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" humans.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Humans> findOne(Long id);

    /**
     * Delete the "id" humans.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
