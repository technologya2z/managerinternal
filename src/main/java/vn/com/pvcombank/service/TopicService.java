package vn.com.pvcombank.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.Topic;
import vn.com.pvcombank.service.dto.TopicDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.Topic}.
 */
public interface TopicService {
    /**
     * Save a topic.
     *
     * @param topicDTO the entity to save.
     * @return the persisted entity.
     */
    TopicDTO save(TopicDTO topicDTO);

    /**
     * Updates a topic.
     *
     * @param topicDTO the entity to update.
     * @return the persisted entity.
     */
    TopicDTO update(TopicDTO topicDTO);

    /**
     * Partially updates a topic.
     *
     * @param topicDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TopicDTO> partialUpdate(TopicDTO topicDTO);

    /**
     * Get all the topics.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TopicDTO> findAll(Pageable pageable);

    /**
     * Get the "id" topic.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Topic> findOne(Long id);

    /**
     * Delete the "id" topic.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
