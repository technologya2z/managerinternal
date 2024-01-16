package vn.com.pvcombank.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.Partner1;
import vn.com.pvcombank.repository.Partner1Repository;

/**
 * Service Implementation for managing {@link Partner1}.
 */
@Service
@Transactional
public class Partner1Service {

    private final Logger log = LoggerFactory.getLogger(Partner1Service.class);

    private final Partner1Repository partner1Repository;

    public Partner1Service(Partner1Repository partner1Repository) {
        this.partner1Repository = partner1Repository;
    }

    /**
     * Save a partner1.
     *
     * @param partner1 the entity to save.
     * @return the persisted entity.
     */
    public Partner1 save(Partner1 partner1) {
        log.debug("Request to save Partner1 : {}", partner1);
        return partner1Repository.save(partner1);
    }

    /**
     * Update a partner1.
     *
     * @param partner1 the entity to save.
     * @return the persisted entity.
     */
    public Partner1 update(Partner1 partner1) {
        log.debug("Request to save Partner1 : {}", partner1);
        return partner1Repository.save(partner1);
    }

    /**
     * Partially update a partner1.
     *
     * @param partner1 the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Partner1> partialUpdate(Partner1 partner1) {
        log.debug("Request to partially update Partner1 : {}", partner1);

        return partner1Repository
            .findById(partner1.getId())
            .map(existingPartner1 -> {
                if (partner1.getName() != null) {
                    existingPartner1.setName(partner1.getName());
                }
                if (partner1.getCode() != null) {
                    existingPartner1.setCode(partner1.getCode());
                }
                if (partner1.getDescription() != null) {
                    existingPartner1.setDescription(partner1.getDescription());
                }

                return existingPartner1;
            })
            .map(partner1Repository::save);
    }

    /**
     * Get all the partner1s.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Partner1> findAll(Pageable pageable) {
        log.debug("Request to get all Partner1s");
        return partner1Repository.findAll(pageable);
    }

    /**
     * Get one partner1 by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Partner1> findOne(Long id) {
        log.debug("Request to get Partner1 : {}", id);
        return partner1Repository.findById(id);
    }

    /**
     * Delete the partner1 by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Partner1 : {}", id);
        partner1Repository.deleteById(id);
    }
}
