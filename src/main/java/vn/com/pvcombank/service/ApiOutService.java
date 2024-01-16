package vn.com.pvcombank.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.ApiOut;
import vn.com.pvcombank.service.dto.ApiOutDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.ApiOut}.
 */
public interface ApiOutService {
    /**
     * Save a apiOut.
     *
     * @param apiOutDTO the entity to save.
     * @return the persisted entity.
     */
    ApiOutDTO save(ApiOutDTO apiOutDTO);

    /**
     * Updates a apiOut.
     *
     * @param apiOutDTO the entity to update.
     * @return the persisted entity.
     */
    ApiOutDTO update(ApiOutDTO apiOutDTO);

    /**
     * Partially updates a apiOut.
     *
     * @param apiOutDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApiOutDTO> partialUpdate(ApiOutDTO apiOutDTO);

    /**
     * Get all the apiOuts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApiOutDTO> findAll(Pageable pageable);

    /**
     * Get the "id" apiOut.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApiOut> findOne(Long id);

    /**
     * Delete the "id" apiOut.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
