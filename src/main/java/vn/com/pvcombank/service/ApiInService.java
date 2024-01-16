package vn.com.pvcombank.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.ApiIn;
import vn.com.pvcombank.service.dto.ApiInDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.ApiIn}.
 */
public interface ApiInService {
    /**
     * Save a apiIn.
     *
     * @param apiInDTO the entity to save.
     * @return the persisted entity.
     */
    ApiInDTO save(ApiInDTO apiInDTO);

    /**
     * Updates a apiIn.
     *
     * @param apiInDTO the entity to update.
     * @return the persisted entity.
     */
    ApiInDTO update(ApiInDTO apiInDTO);

    /**
     * Partially updates a apiIn.
     *
     * @param apiInDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApiInDTO> partialUpdate(ApiInDTO apiInDTO);

    /**
     * Get all the apiIns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApiInDTO> findAll(Pageable pageable);

    /**
     * Get the "id" apiIn.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApiIn> findOne(Long id);

    /**
     * Delete the "id" apiIn.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
