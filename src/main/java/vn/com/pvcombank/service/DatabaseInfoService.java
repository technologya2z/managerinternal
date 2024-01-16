package vn.com.pvcombank.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.DatabaseInfo;
import vn.com.pvcombank.service.dto.DatabaseInfoDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.DatabaseInfo}.
 */
public interface DatabaseInfoService {
    /**
     * Save a databaseInfo.
     *
     * @param databaseInfoDTO the entity to save.
     * @return the persisted entity.
     */
    DatabaseInfoDTO save(DatabaseInfoDTO databaseInfoDTO);

    /**
     * Updates a databaseInfo.
     *
     * @param databaseInfoDTO the entity to update.
     * @return the persisted entity.
     */
    DatabaseInfoDTO update(DatabaseInfoDTO databaseInfoDTO);

    /**
     * Partially updates a databaseInfo.
     *
     * @param databaseInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<DatabaseInfoDTO> partialUpdate(DatabaseInfoDTO databaseInfoDTO);

    /**
     * Get all the databaseInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DatabaseInfoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" databaseInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DatabaseInfo> findOne(Long id);

    /**
     * Delete the "id" databaseInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
