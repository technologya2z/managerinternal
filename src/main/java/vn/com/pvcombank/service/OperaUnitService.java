package vn.com.pvcombank.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.OperaUnit;
import vn.com.pvcombank.domain.RelApplicationOperaUnit;
import vn.com.pvcombank.service.dto.OperaUnitDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.OperaUnit}.
 */
public interface OperaUnitService {
    /**
     * Save a operaUnit.
     *
     * @param operaUnitDTO the entity to save.
     * @return the persisted entity.
     */
    OperaUnitDTO save(OperaUnitDTO operaUnitDTO);

    /**
     * Updates a operaUnit.
     *
     * @param operaUnitDTO the entity to update.
     * @return the persisted entity.
     */
    OperaUnitDTO update(OperaUnitDTO operaUnitDTO);

    /**
     * Partially updates a operaUnit.
     *
     * @param operaUnitDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OperaUnitDTO> partialUpdate(OperaUnitDTO operaUnitDTO);

    /**
     * Get all the operaUnits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OperaUnitDTO> findAll(Pageable pageable);

    /**
     * Get the "id" operaUnit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OperaUnit> findOne(Long id);

    /**
     * Delete the "id" operaUnit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Optional<OperaUnit> findAllWithRelationShip(OperaUnit operaUnit);
}
