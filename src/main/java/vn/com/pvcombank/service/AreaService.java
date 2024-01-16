package vn.com.pvcombank.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.Area;
import vn.com.pvcombank.service.dto.AreaDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.Area}.
 */
public interface AreaService {
    /**
     * Save a area.
     *
     * @param areaDTO the entity to save.
     * @return the persisted entity.
     */
    AreaDTO save(AreaDTO areaDTO);

    /**
     * Updates a area.
     *
     * @param areaDTO the entity to update.
     * @return the persisted entity.
     */
    AreaDTO update(AreaDTO areaDTO);

    /**
     * Partially updates a area.
     *
     * @param areaDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AreaDTO> partialUpdate(AreaDTO areaDTO);

    /**
     * Get all the areas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AreaDTO> findAll(Pageable pageable);

    /**
     * Get the "id" area.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Area> findOne(Long id);

    /**
     * Delete the "id" area.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
