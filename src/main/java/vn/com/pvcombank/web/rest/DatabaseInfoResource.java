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
import vn.com.pvcombank.domain.DatabaseInfo;
import vn.com.pvcombank.repository.DatabaseInfoRepository;
import vn.com.pvcombank.service.DatabaseInfoQueryService;
import vn.com.pvcombank.service.DatabaseInfoService;
import vn.com.pvcombank.service.criteria.DatabaseInfoCriteria;
import vn.com.pvcombank.service.dto.DatabaseInfoDTO;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.DatabaseInfo}.
 */
@RestController
@RequestMapping("/api")
public class DatabaseInfoResource {

    private final Logger log = LoggerFactory.getLogger(DatabaseInfoResource.class);

    private static final String ENTITY_NAME = "databaseInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DatabaseInfoService databaseInfoService;

    private final DatabaseInfoRepository databaseInfoRepository;

    private final DatabaseInfoQueryService databaseInfoQueryService;

    public DatabaseInfoResource(
        DatabaseInfoService databaseInfoService,
        DatabaseInfoRepository databaseInfoRepository,
        DatabaseInfoQueryService databaseInfoQueryService
    ) {
        this.databaseInfoService = databaseInfoService;
        this.databaseInfoRepository = databaseInfoRepository;
        this.databaseInfoQueryService = databaseInfoQueryService;
    }

    /**
     * {@code POST  /database-infos} : Create a new databaseInfo.
     *
     * @param databaseInfoDTO the databaseInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new databaseInfoDTO, or with status {@code 400 (Bad Request)} if the databaseInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/database-infos")
    public ResponseEntity<DatabaseInfoDTO> createDatabaseInfo(@RequestBody DatabaseInfoDTO databaseInfoDTO) throws URISyntaxException {
        log.debug("REST request to save DatabaseInfo : {}", databaseInfoDTO);
        if (databaseInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new databaseInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DatabaseInfoDTO result = databaseInfoService.save(databaseInfoDTO);
        return ResponseEntity
            .created(new URI("/api/database-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /database-infos/:id} : Updates an existing databaseInfo.
     *
     * @param id the id of the databaseInfoDTO to save.
     * @param databaseInfoDTO the databaseInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated databaseInfoDTO,
     * or with status {@code 400 (Bad Request)} if the databaseInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the databaseInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/database-infos/{id}")
    public ResponseEntity<DatabaseInfoDTO> updateDatabaseInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DatabaseInfoDTO databaseInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DatabaseInfo : {}, {}", id, databaseInfoDTO);
        if (databaseInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, databaseInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!databaseInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DatabaseInfoDTO result = databaseInfoService.update(databaseInfoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, databaseInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /database-infos/:id} : Partial updates given fields of an existing databaseInfo, field will ignore if it is null
     *
     * @param id the id of the databaseInfoDTO to save.
     * @param databaseInfoDTO the databaseInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated databaseInfoDTO,
     * or with status {@code 400 (Bad Request)} if the databaseInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the databaseInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the databaseInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/database-infos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DatabaseInfoDTO> partialUpdateDatabaseInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DatabaseInfoDTO databaseInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DatabaseInfo partially : {}, {}", id, databaseInfoDTO);
        if (databaseInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, databaseInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!databaseInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DatabaseInfoDTO> result = databaseInfoService.partialUpdate(databaseInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, databaseInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /database-infos} : get all the databaseInfos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of databaseInfos in body.
     */
    @GetMapping("/database-infos")
    public ResponseEntity<List<DatabaseInfoDTO>> getAllDatabaseInfos(
        DatabaseInfoCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get DatabaseInfos by criteria: {}", criteria);
        Page<DatabaseInfoDTO> page = databaseInfoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /database-infos/count} : count all the databaseInfos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/database-infos/count")
    public ResponseEntity<Long> countDatabaseInfos(DatabaseInfoCriteria criteria) {
        log.debug("REST request to count DatabaseInfos by criteria: {}", criteria);
        return ResponseEntity.ok().body(databaseInfoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /database-infos/:id} : get the "id" databaseInfo.
     *
     * @param id the id of the databaseInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the databaseInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/database-infos/{id}")
    public ResponseEntity<DatabaseInfo> getDatabaseInfo(@PathVariable Long id) {
        log.debug("REST request to get DatabaseInfo : {}", id);
        Optional<DatabaseInfo> databaseInfoDTO = databaseInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(databaseInfoDTO);
    }

    /**
     * {@code DELETE  /database-infos/:id} : delete the "id" databaseInfo.
     *
     * @param id the id of the databaseInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/database-infos/{id}")
    public ResponseEntity<Void> deleteDatabaseInfo(@PathVariable Long id) {
        log.debug("REST request to delete DatabaseInfo : {}", id);
        databaseInfoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
