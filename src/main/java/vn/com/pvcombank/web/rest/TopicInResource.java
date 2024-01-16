package vn.com.pvcombank.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import vn.com.pvcombank.domain.TopicIn;
import vn.com.pvcombank.repository.TopicInRepository;
import vn.com.pvcombank.service.TopicInQueryService;
import vn.com.pvcombank.service.TopicInService;
import vn.com.pvcombank.service.criteria.TopicInCriteria;
import vn.com.pvcombank.service.dto.TopicInDTO;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.TopicIn}.
 */
@RestController
@RequestMapping("/api")
public class TopicInResource {

    private final Logger log = LoggerFactory.getLogger(TopicInResource.class);

    private static final String ENTITY_NAME = "topicIn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TopicInService topicInService;

    private final TopicInRepository topicInRepository;

    private final TopicInQueryService topicInQueryService;

    public TopicInResource(TopicInService topicInService, TopicInRepository topicInRepository, TopicInQueryService topicInQueryService) {
        this.topicInService = topicInService;
        this.topicInRepository = topicInRepository;
        this.topicInQueryService = topicInQueryService;
    }

    /**
     * {@code POST  /topic-ins} : Create a new topicIn.
     *
     * @param topicInDTO the topicInDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new topicInDTO, or with status {@code 400 (Bad Request)} if the topicIn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/topic-ins")
    public ResponseEntity<TopicInDTO> createTopicIn(@RequestBody TopicInDTO topicInDTO) throws URISyntaxException {
        log.debug("REST request to save TopicIn : {}", topicInDTO);
        if (topicInDTO.getId() != null) {
            throw new BadRequestAlertException("A new topicIn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TopicInDTO result = topicInService.save(topicInDTO);
        return ResponseEntity
            .created(new URI("/api/topic-ins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /topic-ins/:id} : Updates an existing topicIn.
     *
     * @param id the id of the topicInDTO to save.
     * @param topicInDTO the topicInDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated topicInDTO,
     * or with status {@code 400 (Bad Request)} if the topicInDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the topicInDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/topic-ins/{id}")
    public ResponseEntity<TopicInDTO> updateTopicIn(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TopicInDTO topicInDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TopicIn : {}, {}", id, topicInDTO);
        if (topicInDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, topicInDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!topicInRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TopicInDTO result = topicInService.update(topicInDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, topicInDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /topic-ins/:id} : Partial updates given fields of an existing topicIn, field will ignore if it is null
     *
     * @param id the id of the topicInDTO to save.
     * @param topicInDTO the topicInDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated topicInDTO,
     * or with status {@code 400 (Bad Request)} if the topicInDTO is not valid,
     * or with status {@code 404 (Not Found)} if the topicInDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the topicInDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/topic-ins/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TopicInDTO> partialUpdateTopicIn(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TopicInDTO topicInDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TopicIn partially : {}, {}", id, topicInDTO);
        if (topicInDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, topicInDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!topicInRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TopicInDTO> result = topicInService.partialUpdate(topicInDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, topicInDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /topic-ins} : get all the topicIns.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of topicIns in body.
     */
    @GetMapping("/topic-ins")
    public ResponseEntity<List<TopicIn>> getAllTopicIns(
        TopicInCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get TopicIns by criteria: {}", criteria);
        Page<TopicIn> page = topicInQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /topic-ins/count} : count all the topicIns.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/topic-ins/count")
    public ResponseEntity<Long> countTopicIns(TopicInCriteria criteria) {
        log.debug("REST request to count TopicIns by criteria: {}", criteria);
        return ResponseEntity.ok().body(topicInQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /topic-ins/:id} : get the "id" topicIn.
     *
     * @param id the id of the topicInDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the topicInDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/topic-ins/{id}")
    public ResponseEntity<TopicIn> getTopicIn(@PathVariable Long id) {
        log.debug("REST request to get TopicIn : {}", id);
        Optional<TopicIn> topicInDTO = topicInService.findOne(id);
        return ResponseUtil.wrapOrNotFound(topicInDTO);
    }

    /**
     * {@code DELETE  /topic-ins/:id} : delete the "id" topicIn.
     *
     * @param id the id of the topicInDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/topic-ins/{id}")
    public ResponseEntity<Void> deleteTopicIn(@PathVariable Long id) {
        log.debug("REST request to delete TopicIn : {}", id);
        topicInService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/topic-in-list")
    public ResponseEntity<List<TopicIn>> getAllTopicInsWithTopicId(@RequestBody List<String> topicIds) {
        List<TopicIn> page = new ArrayList<>();
        for (String id : topicIds) {
            TopicInCriteria criteria = new TopicInCriteria();
            LongFilter longFilter = new LongFilter();
            longFilter.setEquals(Long.parseLong(id));
            criteria.setTopicId(longFilter);
            page.addAll(topicInQueryService.findByCriteria(criteria));
        }
        return ResponseEntity.ok().body(page);
    }
}
