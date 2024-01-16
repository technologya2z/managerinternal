package vn.com.pvcombank.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.TopicOut;
import vn.com.pvcombank.service.dto.TopicOutDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.TopicOut}.
 */
public interface TopicOutService {
    /**
     * Save a topicOut.
     *
     * @param topicOutDTO the entity to save.
     * @return the persisted entity.
     */
    TopicOutDTO save(TopicOutDTO topicOutDTO);

    /**
     * Updates a topicOut.
     *
     * @param topicOutDTO the entity to update.
     * @return the persisted entity.
     */
    TopicOutDTO update(TopicOutDTO topicOutDTO);

    /**
     * Partially updates a topicOut.
     *
     * @param topicOutDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TopicOutDTO> partialUpdate(TopicOutDTO topicOutDTO);

    /**
     * Get all the topicOuts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TopicOutDTO> findAll(Pageable pageable);

    /**
     * Get the "id" topicOut.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TopicOut> findOne(Long id);

    /**
     * Delete the "id" topicOut.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
