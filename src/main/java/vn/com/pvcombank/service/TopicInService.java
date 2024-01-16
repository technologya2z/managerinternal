package vn.com.pvcombank.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.TopicIn;
import vn.com.pvcombank.service.dto.TopicInDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.TopicIn}.
 */
public interface TopicInService {
    /**
     * Save a topicIn.
     *
     * @param topicInDTO the entity to save.
     * @return the persisted entity.
     */
    TopicInDTO save(TopicInDTO topicInDTO);

    /**
     * Updates a topicIn.
     *
     * @param topicInDTO the entity to update.
     * @return the persisted entity.
     */
    TopicInDTO update(TopicInDTO topicInDTO);

    /**
     * Partially updates a topicIn.
     *
     * @param topicInDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TopicInDTO> partialUpdate(TopicInDTO topicInDTO);

    /**
     * Get all the topicIns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TopicInDTO> findAll(Pageable pageable);

    /**
     * Get the "id" topicIn.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TopicIn> findOne(Long id);

    /**
     * Delete the "id" topicIn.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
