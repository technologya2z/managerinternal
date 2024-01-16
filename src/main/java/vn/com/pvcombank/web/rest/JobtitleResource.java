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
import vn.com.pvcombank.domain.Jobtitle;
import vn.com.pvcombank.repository.JobtitleRepository;
import vn.com.pvcombank.service.JobtitleQueryService;
import vn.com.pvcombank.service.JobtitleService;
import vn.com.pvcombank.service.criteria.JobtitleCriteria;
import vn.com.pvcombank.service.dto.JobtitleDTO;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.Jobtitle}.
 */
@RestController
@RequestMapping("/api")
public class JobtitleResource {

    private final Logger log = LoggerFactory.getLogger(JobtitleResource.class);

    private static final String ENTITY_NAME = "jobtitle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JobtitleService jobtitleService;

    private final JobtitleRepository jobtitleRepository;

    private final JobtitleQueryService jobtitleQueryService;

    public JobtitleResource(
        JobtitleService jobtitleService,
        JobtitleRepository jobtitleRepository,
        JobtitleQueryService jobtitleQueryService
    ) {
        this.jobtitleService = jobtitleService;
        this.jobtitleRepository = jobtitleRepository;
        this.jobtitleQueryService = jobtitleQueryService;
    }

    /**
     * {@code POST  /jobtitles} : Create a new jobtitle.
     *
     * @param jobtitleDTO the jobtitleDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobtitleDTO, or with status {@code 400 (Bad Request)} if the jobtitle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jobtitles")
    public ResponseEntity<JobtitleDTO> createJobtitle(@RequestBody JobtitleDTO jobtitleDTO) throws URISyntaxException {
        log.debug("REST request to save Jobtitle : {}", jobtitleDTO);
        if (jobtitleDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobtitle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobtitleDTO result = jobtitleService.save(jobtitleDTO);
        return ResponseEntity
            .created(new URI("/api/jobtitles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jobtitles/:id} : Updates an existing jobtitle.
     *
     * @param id the id of the jobtitleDTO to save.
     * @param jobtitleDTO the jobtitleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobtitleDTO,
     * or with status {@code 400 (Bad Request)} if the jobtitleDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jobtitleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jobtitles/{id}")
    public ResponseEntity<JobtitleDTO> updateJobtitle(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JobtitleDTO jobtitleDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Jobtitle : {}, {}", id, jobtitleDTO);
        if (jobtitleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jobtitleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jobtitleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JobtitleDTO result = jobtitleService.update(jobtitleDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jobtitleDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jobtitles/:id} : Partial updates given fields of an existing jobtitle, field will ignore if it is null
     *
     * @param id the id of the jobtitleDTO to save.
     * @param jobtitleDTO the jobtitleDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobtitleDTO,
     * or with status {@code 400 (Bad Request)} if the jobtitleDTO is not valid,
     * or with status {@code 404 (Not Found)} if the jobtitleDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the jobtitleDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/jobtitles/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JobtitleDTO> partialUpdateJobtitle(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JobtitleDTO jobtitleDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Jobtitle partially : {}, {}", id, jobtitleDTO);
        if (jobtitleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jobtitleDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jobtitleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JobtitleDTO> result = jobtitleService.partialUpdate(jobtitleDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jobtitleDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /jobtitles} : get all the jobtitles.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobtitles in body.
     */
    @GetMapping("/jobtitles")
    public ResponseEntity<List<JobtitleDTO>> getAllJobtitles(
        JobtitleCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Jobtitles by criteria: {}", criteria);
        Page<JobtitleDTO> page = jobtitleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jobtitles/count} : count all the jobtitles.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/jobtitles/count")
    public ResponseEntity<Long> countJobtitles(JobtitleCriteria criteria) {
        log.debug("REST request to count Jobtitles by criteria: {}", criteria);
        return ResponseEntity.ok().body(jobtitleQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /jobtitles/:id} : get the "id" jobtitle.
     *
     * @param id the id of the jobtitleDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jobtitleDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jobtitles/{id}")
    public ResponseEntity<Jobtitle> getJobtitle(@PathVariable Long id) {
        log.debug("REST request to get Jobtitle : {}", id);
        Optional<Jobtitle> jobtitleDTO = jobtitleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobtitleDTO);
    }

    /**
     * {@code DELETE  /jobtitles/:id} : delete the "id" jobtitle.
     *
     * @param id the id of the jobtitleDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jobtitles/{id}")
    public ResponseEntity<Void> deleteJobtitle(@PathVariable Long id) {
        log.debug("REST request to delete Jobtitle : {}", id);
        jobtitleService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
