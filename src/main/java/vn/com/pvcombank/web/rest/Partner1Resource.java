package vn.com.pvcombank.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.com.pvcombank.domain.Partner1;
import vn.com.pvcombank.repository.Partner1Repository;
import vn.com.pvcombank.service.Partner1QueryService;
import vn.com.pvcombank.service.Partner1Service;
import vn.com.pvcombank.service.criteria.Partner1Criteria;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.Partner1}.
 */
@RestController
@RequestMapping("/api")
public class Partner1Resource {

    private final Logger log = LoggerFactory.getLogger(Partner1Resource.class);

    private static final String ENTITY_NAME = "partner1";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final Partner1Service partner1Service;

    private final Partner1Repository partner1Repository;

    private final Partner1QueryService partner1QueryService;

    public Partner1Resource(
        Partner1Service partner1Service,
        Partner1Repository partner1Repository,
        Partner1QueryService partner1QueryService
    ) {
        this.partner1Service = partner1Service;
        this.partner1Repository = partner1Repository;
        this.partner1QueryService = partner1QueryService;
    }

    /**
     * {@code POST  /partner-1-s} : Create a new partner1.
     *
     * @param partner1 the partner1 to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new partner1, or with status {@code 400 (Bad Request)} if the partner1 has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/partner-1-s")
    public ResponseEntity<Partner1> createPartner1(@Valid @RequestBody Partner1 partner1) throws URISyntaxException {
        log.debug("REST request to save Partner1 : {}", partner1);
        if (partner1.getId() != null) {
            throw new BadRequestAlertException("A new partner1 cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Partner1 result = partner1Service.save(partner1);
        return ResponseEntity
            .created(new URI("/api/partner-1-s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /partner-1-s/:id} : Updates an existing partner1.
     *
     * @param id the id of the partner1 to save.
     * @param partner1 the partner1 to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated partner1,
     * or with status {@code 400 (Bad Request)} if the partner1 is not valid,
     * or with status {@code 500 (Internal Server Error)} if the partner1 couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/partner-1-s/{id}")
    public ResponseEntity<Partner1> updatePartner1(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Partner1 partner1
    ) throws URISyntaxException {
        log.debug("REST request to update Partner1 : {}, {}", id, partner1);
        if (partner1.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, partner1.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!partner1Repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Partner1 result = partner1Service.update(partner1);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, partner1.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /partner-1-s/:id} : Partial updates given fields of an existing partner1, field will ignore if it is null
     *
     * @param id the id of the partner1 to save.
     * @param partner1 the partner1 to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated partner1,
     * or with status {@code 400 (Bad Request)} if the partner1 is not valid,
     * or with status {@code 404 (Not Found)} if the partner1 is not found,
     * or with status {@code 500 (Internal Server Error)} if the partner1 couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/partner-1-s/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Partner1> partialUpdatePartner1(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Partner1 partner1
    ) throws URISyntaxException {
        log.debug("REST request to partial update Partner1 partially : {}, {}", id, partner1);
        if (partner1.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, partner1.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!partner1Repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Partner1> result = partner1Service.partialUpdate(partner1);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, partner1.getId().toString())
        );
    }

    /**
     * {@code GET  /partner-1-s} : get all the partner1s.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of partner1s in body.
     */
    @GetMapping("/partner-1-s")
    public ResponseEntity<List<Partner1>> getAllPartner1s(
        Partner1Criteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Partner1s by criteria: {}", criteria);
        Page<Partner1> page = partner1QueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /partner-1-s/count} : count all the partner1s.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/partner-1-s/count")
    public ResponseEntity<Long> countPartner1s(Partner1Criteria criteria) {
        log.debug("REST request to count Partner1s by criteria: {}", criteria);
        return ResponseEntity.ok().body(partner1QueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /partner-1-s/:id} : get the "id" partner1.
     *
     * @param id the id of the partner1 to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the partner1, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/partner-1-s/{id}")
    public ResponseEntity<Partner1> getPartner1(@PathVariable Long id) {
        log.debug("REST request to get Partner1 : {}", id);
        Optional<Partner1> partner1 = partner1Service.findOne(id);
        return ResponseUtil.wrapOrNotFound(partner1);
    }

    /**
     * {@code DELETE  /partner-1-s/:id} : delete the "id" partner1.
     *
     * @param id the id of the partner1 to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/partner-1-s/{id}")
    public ResponseEntity<Void> deletePartner1(@PathVariable Long id) {
        log.debug("REST request to delete Partner1 : {}", id);
        partner1Service.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
