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
import vn.com.pvcombank.domain.Topic;
import vn.com.pvcombank.domain.TopicIn;
import vn.com.pvcombank.domain.TopicOut;
import vn.com.pvcombank.repository.TopicOutRepository;
import vn.com.pvcombank.service.TopicOutQueryService;
import vn.com.pvcombank.service.TopicOutService;
import vn.com.pvcombank.service.criteria.TopicInCriteria;
import vn.com.pvcombank.service.criteria.TopicOutCriteria;
import vn.com.pvcombank.service.dto.TopicOutDTO;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.TopicOut}.
 */
@RestController
@RequestMapping("/api")
public class TopicOutResource {

    private final Logger log = LoggerFactory.getLogger(TopicOutResource.class);

    private static final String ENTITY_NAME = "topicOut";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TopicOutService topicOutService;

    private final TopicOutRepository topicOutRepository;

    private final TopicOutQueryService topicOutQueryService;

    public TopicOutResource(
        TopicOutService topicOutService,
        TopicOutRepository topicOutRepository,
        TopicOutQueryService topicOutQueryService
    ) {
        this.topicOutService = topicOutService;
        this.topicOutRepository = topicOutRepository;
        this.topicOutQueryService = topicOutQueryService;
    }

    /**
     * {@code POST  /topic-outs} : Create a new topicOut.
     *
     * @param topicOutDTO the topicOutDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new topicOutDTO, or with status {@code 400 (Bad Request)} if the topicOut has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/topic-outs")
    public ResponseEntity<TopicOutDTO> createTopicOut(@RequestBody TopicOutDTO topicOutDTO) throws URISyntaxException {
        log.debug("REST request to save TopicOut : {}", topicOutDTO);
        if (topicOutDTO.getId() != null) {
            throw new BadRequestAlertException("A new topicOut cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TopicOutDTO result = topicOutService.save(topicOutDTO);
        return ResponseEntity
            .created(new URI("/api/topic-outs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /topic-outs/:id} : Updates an existing topicOut.
     *
     * @param id the id of the topicOutDTO to save.
     * @param topicOutDTO the topicOutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated topicOutDTO,
     * or with status {@code 400 (Bad Request)} if the topicOutDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the topicOutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/topic-outs/{id}")
    public ResponseEntity<TopicOutDTO> updateTopicOut(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TopicOutDTO topicOutDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TopicOut : {}, {}", id, topicOutDTO);
        if (topicOutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, topicOutDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!topicOutRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TopicOutDTO result = topicOutService.update(topicOutDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, topicOutDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /topic-outs/:id} : Partial updates given fields of an existing topicOut, field will ignore if it is null
     *
     * @param id the id of the topicOutDTO to save.
     * @param topicOutDTO the topicOutDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated topicOutDTO,
     * or with status {@code 400 (Bad Request)} if the topicOutDTO is not valid,
     * or with status {@code 404 (Not Found)} if the topicOutDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the topicOutDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/topic-outs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TopicOutDTO> partialUpdateTopicOut(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TopicOutDTO topicOutDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TopicOut partially : {}, {}", id, topicOutDTO);
        if (topicOutDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, topicOutDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!topicOutRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TopicOutDTO> result = topicOutService.partialUpdate(topicOutDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, topicOutDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /topic-outs} : get all the topicOuts.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of topicOuts in body.
     */
    @GetMapping("/topic-outs")
    public ResponseEntity<List<TopicOut>> getAllTopicOuts(
        TopicOutCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get TopicOuts by criteria: {}", criteria);
        Page<TopicOut> page = topicOutQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /topic-outs/count} : count all the topicOuts.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/topic-outs/count")
    public ResponseEntity<Long> countTopicOuts(TopicOutCriteria criteria) {
        log.debug("REST request to count TopicOuts by criteria: {}", criteria);
        return ResponseEntity.ok().body(topicOutQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /topic-outs/:id} : get the "id" topicOut.
     *
     * @param id the id of the topicOutDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the topicOutDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/topic-outs/{id}")
    public ResponseEntity<TopicOut> getTopicOut(@PathVariable Long id) {
        log.debug("REST request to get TopicOut : {}", id);
        Optional<TopicOut> topicOutDTO = topicOutService.findOne(id);
        return ResponseUtil.wrapOrNotFound(topicOutDTO);
    }

    /**
     * {@code DELETE  /topic-outs/:id} : delete the "id" topicOut.
     *
     * @param id the id of the topicOutDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/topic-outs/{id}")
    public ResponseEntity<Void> deleteTopicOut(@PathVariable Long id) {
        log.debug("REST request to delete TopicOut : {}", id);
        topicOutService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    @PostMapping("/topic-out-list")
    public ResponseEntity<List<TopicOut>> getAllTopicOutsWithTopicId(@RequestBody List<String> topicIds) {
        List<TopicOut> page = new ArrayList<>();
        for (String id : topicIds) {
            TopicOutCriteria criteria = new TopicOutCriteria();
            LongFilter longFilter = new LongFilter();
            longFilter.setEquals(Long.parseLong(id));
            criteria.setTopicId(longFilter);
            page.addAll(topicOutQueryService.findByCriteria(criteria));
        }
        return ResponseEntity.ok().body(page);
    }
}
