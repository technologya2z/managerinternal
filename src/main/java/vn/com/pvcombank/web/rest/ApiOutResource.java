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
import vn.com.pvcombank.domain.ApiOut;
import vn.com.pvcombank.repository.ApiOutRepository;
import vn.com.pvcombank.service.ApiOutQueryService;
import vn.com.pvcombank.service.ApiOutService;
import vn.com.pvcombank.service.criteria.ApiOutCriteria;
import vn.com.pvcombank.service.dto.ApiOutDTO;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.ApiOut}.
 */
@RestController
@RequestMapping("/api")
public class ApiOutResource {

    private final Logger log = LoggerFactory.getLogger(ApiOutResource.class);

    private static final String ENTITY_NAME = "apiOut";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApiOutService apiOutService;

    private final ApiOutRepository apiOutRepository;

    private final ApiOutQueryService apiOutQueryService;

    public ApiOutResource(ApiOutService apiOutService, ApiOutRepository apiOutRepository, ApiOutQueryService apiOutQueryService) {
        this.apiOutService = apiOutService;
        this.apiOutRepository = apiOutRepository;
        this.apiOutQueryService = apiOutQueryService;
    }

    /**
     * {@code POST  /api-outs} : Create a new apiOut.
     *
     * @param apiOutDTO the apiOutDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apiOutDTO, or with status {@code 400 (Bad Request)} if the apiOut has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/api-outs")
    public ResponseEntity<ApiOutDTO> createApiOut(@RequestBody ApiOutDTO apiOutDTO) throws URISyntaxException {
        log.debug("REST request to save ApiOut : {}", apiOutDTO);
        if (apiOutDTO.getId() != null) {
            throw new BadRequestAlertException("A new apiOut cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiOutDTO result = apiOutService.save(apiOutDTO);
        return ResponseEntity
            .created(new URI("/api/api-outs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /api-outs/:id} : Updates an existing apiOut.
     *
     * @param id the id of the apiOutDTO to save.
     * @param apiOutDTO the apiOutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiOutDTO,
     * or with status {@code 400 (Bad Request)} if the apiOutDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apiOutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/api-outs/{id}")
    public ResponseEntity<ApiOutDTO> updateApiOut(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ApiOutDTO apiOutDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ApiOut : {}, {}", id, apiOutDTO);
        if (apiOutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiOutDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiOutRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ApiOutDTO result = apiOutService.update(apiOutDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, apiOutDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /api-outs/:id} : Partial updates given fields of an existing apiOut, field will ignore if it is null
     *
     * @param id the id of the apiOutDTO to save.
     * @param apiOutDTO the apiOutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiOutDTO,
     * or with status {@code 400 (Bad Request)} if the apiOutDTO is not valid,
     * or with status {@code 404 (Not Found)} if the apiOutDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the apiOutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/api-outs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ApiOutDTO> partialUpdateApiOut(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ApiOutDTO apiOutDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ApiOut partially : {}, {}", id, apiOutDTO);
        if (apiOutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiOutDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiOutRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ApiOutDTO> result = apiOutService.partialUpdate(apiOutDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, apiOutDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /api-outs} : get all the apiOuts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apiOuts in body.
     */
    @GetMapping("/api-outs")
    public ResponseEntity<List<ApiOut>> getAllApiOuts(
        ApiOutCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ApiOuts by criteria: {}", criteria);
        Page<ApiOut> page = apiOutQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /api-outs/count} : count all the apiOuts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/api-outs/count")
    public ResponseEntity<Long> countApiOuts(ApiOutCriteria criteria) {
        log.debug("REST request to count ApiOuts by criteria: {}", criteria);
        return ResponseEntity.ok().body(apiOutQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /api-outs/:id} : get the "id" apiOut.
     *
     * @param id the id of the apiOutDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apiOutDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/api-outs/{id}")
    public ResponseEntity<ApiOut> getApiOut(@PathVariable Long id) {
        log.debug("REST request to get ApiOut : {}", id);
        Optional<ApiOut> apiOutDTO = apiOutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(apiOutDTO);
    }

    /**
     * {@code DELETE  /api-outs/:id} : delete the "id" apiOut.
     *
     * @param id the id of the apiOutDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/api-outs/{id}")
    public ResponseEntity<Void> deleteApiOut(@PathVariable Long id) {
        log.debug("REST request to delete ApiOut : {}", id);
        apiOutService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
