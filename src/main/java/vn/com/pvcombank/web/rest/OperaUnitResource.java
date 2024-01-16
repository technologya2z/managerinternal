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
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.domain.OperaUnit;
import vn.com.pvcombank.domain.RelApplicationOperaUnit;
import vn.com.pvcombank.repository.OperaUnitRepository;
import vn.com.pvcombank.repository.RelApplicationOperaUnitRepo;
import vn.com.pvcombank.service.OperaUnitQueryService;
import vn.com.pvcombank.service.OperaUnitService;
import vn.com.pvcombank.service.criteria.ApplicationCriteria;
import vn.com.pvcombank.service.criteria.OperaUnitCriteria;
import vn.com.pvcombank.service.dto.OperaUnitDTO;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.OperaUnit}.
 */
@RestController
@RequestMapping("/api")
public class OperaUnitResource {

    private final Logger log = LoggerFactory.getLogger(OperaUnitResource.class);

    private static final String ENTITY_NAME = "operaUnit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperaUnitService operaUnitService;

    private final OperaUnitRepository operaUnitRepository;

    private final RelApplicationOperaUnitRepo relApplicationOperaUnitRepo;

    private final OperaUnitQueryService operaUnitQueryService;

    public OperaUnitResource(
        OperaUnitService operaUnitService,
        OperaUnitRepository operaUnitRepository,
        RelApplicationOperaUnitRepo relApplicationOperaUnitRepo,
        OperaUnitQueryService operaUnitQueryService
    ) {
        this.operaUnitService = operaUnitService;
        this.operaUnitRepository = operaUnitRepository;
        this.relApplicationOperaUnitRepo = relApplicationOperaUnitRepo;
        this.operaUnitQueryService = operaUnitQueryService;
    }

    /**
     * {@code POST  /opera-units} : Create a new operaUnit.
     *
     * @param operaUnitDTO the operaUnitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operaUnitDTO, or with status {@code 400 (Bad Request)} if the operaUnit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/opera-units")
    public ResponseEntity<OperaUnitDTO> createOperaUnit(@RequestBody OperaUnitDTO operaUnitDTO) throws URISyntaxException {
        log.debug("REST request to save OperaUnit : {}", operaUnitDTO);
        if (operaUnitDTO.getId() != null) {
            throw new BadRequestAlertException("A new operaUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperaUnitDTO result = operaUnitService.save(operaUnitDTO);
        return ResponseEntity
            .created(new URI("/api/opera-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /opera-units/:id} : Updates an existing operaUnit.
     *
     * @param id the id of the operaUnitDTO to save.
     * @param operaUnitDTO the operaUnitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operaUnitDTO,
     * or with status {@code 400 (Bad Request)} if the operaUnitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operaUnitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/opera-units/{id}")
    public ResponseEntity<OperaUnitDTO> updateOperaUnit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OperaUnitDTO operaUnitDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OperaUnit : {}, {}", id, operaUnitDTO);
        if (operaUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operaUnitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operaUnitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OperaUnitDTO result = operaUnitService.update(operaUnitDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operaUnitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /opera-units/:id} : Partial updates given fields of an existing operaUnit, field will ignore if it is null
     *
     * @param id the id of the operaUnitDTO to save.
     * @param operaUnitDTO the operaUnitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operaUnitDTO,
     * or with status {@code 400 (Bad Request)} if the operaUnitDTO is not valid,
     * or with status {@code 404 (Not Found)} if the operaUnitDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the operaUnitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/opera-units/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OperaUnitDTO> partialUpdateOperaUnit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OperaUnitDTO operaUnitDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update OperaUnit partially : {}, {}", id, operaUnitDTO);
        if (operaUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, operaUnitDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!operaUnitRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OperaUnitDTO> result = operaUnitService.partialUpdate(operaUnitDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operaUnitDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /opera-units} : get all the operaUnits.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operaUnits in body.
     */
    @GetMapping("/opera-units")
    public ResponseEntity<List<OperaUnit>> getAllOperaUnits(
        OperaUnitCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get OperaUnits by criteria: {}", criteria);
        Page<OperaUnit> page = operaUnitQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /opera-units/count} : count all the operaUnits.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/opera-units/count")
    public ResponseEntity<Long> countOperaUnits(OperaUnitCriteria criteria) {
        log.debug("REST request to count OperaUnits by criteria: {}", criteria);
        return ResponseEntity.ok().body(operaUnitQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /opera-units/:id} : get the "id" operaUnit.
     *
     * @param id the id of the operaUnitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operaUnitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/opera-units/{id}")
    public ResponseEntity<OperaUnit> getOperaUnit(@PathVariable Long id) {
        log.debug("REST request to get OperaUnit : {}", id);
        Optional<OperaUnit> operaUnitDTO = operaUnitService.findOne(id);
        // operaUnitDTO = operaUnitService.findAllWithRelationShip(operaUnitDTO.get());
        return ResponseUtil.wrapOrNotFound(operaUnitDTO);
    }

    /**
     * {@code DELETE  /opera-units/:id} : delete the "id" operaUnit.
     *
     * @param id the id of the operaUnitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/opera-units/{id}")
    public ResponseEntity<Void> deleteOperaUnit(@PathVariable Long id) {
        log.debug("REST request to delete OperaUnit : {}", id);
        operaUnitService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
