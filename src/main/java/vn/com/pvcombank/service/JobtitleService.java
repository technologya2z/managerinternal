package vn.com.pvcombank.service;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.Jobtitle;
import vn.com.pvcombank.service.dto.JobtitleDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.Jobtitle}.
 */
public interface JobtitleService {
    /**
     * Save a jobtitle.
     *
     * @param jobtitleDTO the entity to save.
     * @return the persisted entity.
     */
    JobtitleDTO save(JobtitleDTO jobtitleDTO);

    /**
     * Updates a jobtitle.
     *
     * @param jobtitleDTO the entity to update.
     * @return the persisted entity.
     */
    JobtitleDTO update(JobtitleDTO jobtitleDTO);

    /**
     * Partially updates a jobtitle.
     *
     * @param jobtitleDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<JobtitleDTO> partialUpdate(JobtitleDTO jobtitleDTO);

    /**
     * Get all the jobtitles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<JobtitleDTO> findAll(Pageable pageable);

    /**
     * Get the "id" jobtitle.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Jobtitle> findOne(Long id);

    /**
     * Delete the "id" jobtitle.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
