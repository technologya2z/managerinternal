package vn.com.pvcombank.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import vn.com.pvcombank.domain.ApiInfo;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.repository.ApiInfoRepository;
import vn.com.pvcombank.repository.ApplicationRepository;
import vn.com.pvcombank.service.ApiInfoQueryService;
import vn.com.pvcombank.service.ApiInfoService;
import vn.com.pvcombank.service.criteria.ApiInfoCriteria;
import vn.com.pvcombank.service.dto.ApiInfoDTO;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.ApiInfo}.
 */
@RestController
@RequestMapping("/api")
public class ApiInfoResource {

    private final Logger log = LoggerFactory.getLogger(ApiInfoResource.class);

    private static final String ENTITY_NAME = "apiInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ApiInfoService apiInfoService;

    private final ApiInfoRepository apiInfoRepository;

    private final ApiInfoQueryService apiInfoQueryService;

    @Autowired
    ApplicationRepository applicationRepository;

    public ApiInfoResource(ApiInfoService apiInfoService, ApiInfoRepository apiInfoRepository, ApiInfoQueryService apiInfoQueryService) {
        this.apiInfoService = apiInfoService;
        this.apiInfoRepository = apiInfoRepository;
        this.apiInfoQueryService = apiInfoQueryService;
    }

    /**
     * {@code POST  /api-infos} : Create a new apiInfo.
     *
     * @param apiInfoDTO the apiInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apiInfoDTO, or with status {@code 400 (Bad Request)} if the apiInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/api-infos")
    public ResponseEntity<ApiInfoDTO> createApiInfo(@RequestBody ApiInfoDTO apiInfoDTO) throws URISyntaxException {
        log.debug("REST request to save ApiInfo : {}", apiInfoDTO);
        if (apiInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new apiInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApiInfoDTO result = apiInfoService.save(apiInfoDTO);
        return ResponseEntity
            .created(new URI("/api/api-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /api-infos/:id} : Updates an existing apiInfo.
     *
     * @param id the id of the apiInfoDTO to save.
     * @param apiInfoDTO the apiInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiInfoDTO,
     * or with status {@code 400 (Bad Request)} if the apiInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apiInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/api-infos/{id}")
    public ResponseEntity<ApiInfoDTO> updateApiInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ApiInfoDTO apiInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ApiInfo : {}, {}", id, apiInfoDTO);
        if (apiInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ApiInfoDTO result = apiInfoService.update(apiInfoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, apiInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /api-infos/:id} : Partial updates given fields of an existing apiInfo, field will ignore if it is null
     *
     * @param id the id of the apiInfoDTO to save.
     * @param apiInfoDTO the apiInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apiInfoDTO,
     * or with status {@code 400 (Bad Request)} if the apiInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the apiInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the apiInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/api-infos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ApiInfoDTO> partialUpdateApiInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ApiInfoDTO apiInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ApiInfo partially : {}, {}", id, apiInfoDTO);
        if (apiInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apiInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!apiInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ApiInfoDTO> result = apiInfoService.partialUpdate(apiInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, apiInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /api-infos} : get all the apiInfos.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apiInfos in body.
     */
    @GetMapping("/api-infos")
    public ResponseEntity<List<ApiInfo>> getAllApiInfos(
        ApiInfoCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get ApiInfos by criteria: {}", criteria);
        Page<ApiInfo> page = apiInfoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /api-infos/count} : count all the apiInfos.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/api-infos/count")
    public ResponseEntity<Long> countApiInfos(ApiInfoCriteria criteria) {
        log.debug("REST request to count ApiInfos by criteria: {}", criteria);
        return ResponseEntity.ok().body(apiInfoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /api-infos/:id} : get the "id" apiInfo.
     *
     * @param id the id of the apiInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apiInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/api-infos/{id}")
    public ResponseEntity<ApiInfoDTO> getApiInfo(@PathVariable Long id) {
        log.debug("REST request to get ApiInfo : {}", id);
        Optional<ApiInfo> apiInfo = apiInfoService.findOne(id);
        List<Application> listIns = applicationRepository.findAllApplicationByapiId(id);
        List<Application> listOut = applicationRepository.findAllApplicationOutByapiId(id);
        ApiInfoDTO apiInfoDTO = new ApiInfoDTO(apiInfo.get(),listIns,listOut);
        return ResponseEntity.ok().body(apiInfoDTO);
    }

    /**
     * {@code DELETE  /api-infos/:id} : delete the "id" apiInfo.
     *
     * @param id the id of the apiInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/api-infos/{id}")
    public ResponseEntity<Void> deleteApiInfo(@PathVariable Long id) {
        log.debug("REST request to delete ApiInfo : {}", id);
        apiInfoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
