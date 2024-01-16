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
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import vn.com.pvcombank.domain.ApiIn;
import vn.com.pvcombank.repository.ApiInRepository;
import vn.com.pvcombank.service.ApiInQueryService;
import vn.com.pvcombank.service.ApiInService;
import vn.com.pvcombank.service.criteria.ApiInCriteria;
import vn.com.pvcombank.service.dto.ApiInDTO;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.ApiIn}.
 */
@RestController
@RequestMapping("/api")
public class ApiInResource {

    private final Logger log = LoggerFactory.getLogger(ApiInResource.class);

    private static final String ENTITY_NAME = "apiIn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApiInService apiInService;

    private final ApiInRepository apiInRepository;

    private final ApiInQueryService apiInQueryService;

    public ApiInResource(ApiInService apiInService, ApiInRepository apiInRepository, ApiInQueryService apiInQueryService) {
        this.apiInService = apiInService;
        this.apiInRepository = apiInRepository;
        this.apiInQueryService = apiInQueryService;
    }

    /**
     * {@code POST  /api-ins} : Create a new apiIn.
     *
     * @param apiInDTO the apiInDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apiInDTO, or with status {@code 400 (Bad Request)} if the apiIn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/api-ins")
    public ResponseEntity<ApiInDTO> createApiIn(@RequestBody ApiInDTO apiInDTO) throws URISyntaxException {
        log.debug("REST request to save ApiIn : {}", apiInDTO);
        if (apiInDTO.getId() != null) {
            throw new BadRequestAlertException("A new apiIn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiInDTO result = apiInService.save(apiInDTO);
        return ResponseEntity
            .created(new URI("/api/api-ins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /api-ins/:id} : Updates an existing apiIn.
     *
     * @param id the id of the apiInDTO to save.
     * @param apiInDTO the apiInDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiInDTO,
     * or with status {@code 400 (Bad Request)} if the apiInDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apiInDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/api-ins/{id}")
    public ResponseEntity<ApiInDTO> updateApiIn(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ApiInDTO apiInDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ApiIn : {}, {}", id, apiInDTO);
        if (apiInDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiInDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiInRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ApiInDTO result = apiInService.update(apiInDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, apiInDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /api-ins/:id} : Partial updates given fields of an existing apiIn, field will ignore if it is null
     *
     * @param id the id of the apiInDTO to save.
     * @param apiInDTO the apiInDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiInDTO,
     * or with status {@code 400 (Bad Request)} if the apiInDTO is not valid,
     * or with status {@code 404 (Not Found)} if the apiInDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the apiInDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/api-ins/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ApiInDTO> partialUpdateApiIn(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ApiInDTO apiInDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ApiIn partially : {}, {}", id, apiInDTO);
        if (apiInDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiInDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiInRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ApiInDTO> result = apiInService.partialUpdate(apiInDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, apiInDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /api-ins} : get all the apiIns.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apiIns in body.
     */
    @GetMapping("/api-ins")
    public ResponseEntity<List<ApiIn>> getAllApiIns(
        ApiInCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ApiIns by criteria: {}", criteria);
        Page<ApiIn> page = apiInQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /api-ins/count} : count all the apiIns.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/api-ins/count")
    public ResponseEntity<Long> countApiIns(ApiInCriteria criteria) {
        log.debug("REST request to count ApiIns by criteria: {}", criteria);
        return ResponseEntity.ok().body(apiInQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /api-ins/:id} : get the "id" apiIn.
     *
     * @param id the id of the apiInDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apiInDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/api-ins/{id}")
    public ResponseEntity<ApiIn> getApiIn(@PathVariable Long id) {
        log.debug("REST request to get ApiIn : {}", id);
        Optional<ApiIn> apiInDTO = apiInService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiInDTO);
    }

    /**
     * {@code DELETE  /api-ins/:id} : delete the "id" apiIn.
     *
     * @param id the id of the apiInDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/api-ins/{id}")
    public ResponseEntity<Void> deleteApiIn(@PathVariable Long id) {
        log.debug("REST request to delete ApiIn : {}", id);
        apiInService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
