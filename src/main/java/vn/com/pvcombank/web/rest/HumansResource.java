package vn.com.pvcombank.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
import vn.com.pvcombank.domain.Humans;
import vn.com.pvcombank.repository.HumansRepository;
import vn.com.pvcombank.service.HumansQueryService;
import vn.com.pvcombank.service.HumansService;
import vn.com.pvcombank.service.criteria.HumansCriteria;
import vn.com.pvcombank.service.dto.HumansDTO;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.Humans}.
 */
@RestController
@RequestMapping("/api")
public class HumansResource {

    private final Logger log = LoggerFactory.getLogger(HumansResource.class);

    private static final String ENTITY_NAME = "humans";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HumansService humansService;

    private final HumansRepository humansRepository;

    private final HumansQueryService humansQueryService;

    public HumansResource(HumansService humansService, HumansRepository humansRepository, HumansQueryService humansQueryService) {
        this.humansService = humansService;
        this.humansRepository = humansRepository;
        this.humansQueryService = humansQueryService;
    }

    /**
     * {@code POST  /humans} : Create a new humans.
     *
     * @param humansDTO the humansDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new humansDTO, or with status {@code 400 (Bad Request)} if the humans has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/humans")
    public ResponseEntity<HumansDTO> createHumans(@RequestBody HumansDTO humansDTO) throws URISyntaxException {
        log.debug("REST request to save Humans : {}", humansDTO);
        if (humansDTO.getId() != null) {
            throw new BadRequestAlertException("A new humans cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HumansDTO result = humansService.save(humansDTO);
        return ResponseEntity
            .created(new URI("/api/humans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /humans/:id} : Updates an existing humans.
     *
     * @param id the id of the humansDTO to save.
     * @param humansDTO the humansDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated humansDTO,
     * or with status {@code 400 (Bad Request)} if the humansDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the humansDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/humans/{id}")
    public ResponseEntity<HumansDTO> updateHumans(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HumansDTO humansDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Humans : {}, {}", id, humansDTO);
        if (humansDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, humansDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!humansRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        HumansDTO result = humansService.update(humansDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, humansDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /humans/:id} : Partial updates given fields of an existing humans, field will ignore if it is null
     *
     * @param id the id of the humansDTO to save.
     * @param humansDTO the humansDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated humansDTO,
     * or with status {@code 400 (Bad Request)} if the humansDTO is not valid,
     * or with status {@code 404 (Not Found)} if the humansDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the humansDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/humans/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<HumansDTO> partialUpdateHumans(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody HumansDTO humansDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Humans partially : {}, {}", id, humansDTO);
        if (humansDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, humansDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!humansRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<HumansDTO> result = humansService.partialUpdate(humansDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, humansDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /humans} : get all the humans.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of humans in body.
     */
    @GetMapping("/humans")
    public ResponseEntity<List<Humans>> getAllHumans(
        HumansCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Humans by criteria: {}", criteria);
        Page<Humans> page = humansQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /humans/count} : count all the humans.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/humans/count")
    public ResponseEntity<Long> countHumans(HumansCriteria criteria) {
        log.debug("REST request to count Humans by criteria: {}", criteria);
        return ResponseEntity.ok().body(humansQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /humans/:id} : get the "id" humans.
     *
     * @param id the id of the humansDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the humansDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/humans/{id}")
    public ResponseEntity<Humans> getHumans(@PathVariable Long id) {
        log.debug("REST request to get Humans : {}", id);
        Optional<Humans> humansDTO = humansService.findOne(id);
        return ResponseUtil.wrapOrNotFound(humansDTO);
    }

    /**
     * {@code DELETE  /humans/:id} : delete the "id" humans.
     *
     * @param id the id of the humansDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/humans/{id}")
    public ResponseEntity<Void> deleteHumans(@PathVariable Long id) {
        log.debug("REST request to delete Humans : {}", id);
        humansService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
