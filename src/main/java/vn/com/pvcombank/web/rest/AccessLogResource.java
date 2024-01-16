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
import vn.com.pvcombank.domain.AccessLog;
import vn.com.pvcombank.repository.AccessLogRepository;
import vn.com.pvcombank.service.AccessLogQueryService;
import vn.com.pvcombank.service.AccessLogService;
import vn.com.pvcombank.service.criteria.AccessLogCriteria;
import vn.com.pvcombank.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.com.pvcombank.domain.AccessLog}.
 */
@RestController
@RequestMapping("/api")
public class AccessLogResource {

    private final Logger log = LoggerFactory.getLogger(AccessLogResource.class);

    private static final String ENTITY_NAME = "accessLog";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccessLogService accessLogService;

    private final AccessLogRepository accessLogRepository;

    private final AccessLogQueryService accessLogQueryService;

    public AccessLogResource(
        AccessLogService accessLogService,
        AccessLogRepository accessLogRepository,
        AccessLogQueryService accessLogQueryService
    ) {
        this.accessLogService = accessLogService;
        this.accessLogRepository = accessLogRepository;
        this.accessLogQueryService = accessLogQueryService;
    }

    /**
     * {@code POST  /access-logs} : Create a new accessLog.
     *
     * @param accessLog the accessLog to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accessLog, or with status {@code 400 (Bad Request)} if the accessLog has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/access-logs")
    public ResponseEntity<AccessLog> createAccessLog(@Valid @RequestBody AccessLog accessLog) throws URISyntaxException {
        log.debug("REST request to save AccessLog : {}", accessLog);
        if (accessLog.getId() != null) {
            throw new BadRequestAlertException("A new accessLog cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AccessLog result = accessLogService.save(accessLog);
        return ResponseEntity
            .created(new URI("/api/access-logs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /access-logs/:id} : Updates an existing accessLog.
     *
     * @param id the id of the accessLog to save.
     * @param accessLog the accessLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accessLog,
     * or with status {@code 400 (Bad Request)} if the accessLog is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accessLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/access-logs/{id}")
    public ResponseEntity<AccessLog> updateAccessLog(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AccessLog accessLog
    ) throws URISyntaxException {
        log.debug("REST request to update AccessLog : {}, {}", id, accessLog);
        if (accessLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accessLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!accessLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AccessLog result = accessLogService.update(accessLog);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, accessLog.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /access-logs/:id} : Partial updates given fields of an existing accessLog, field will ignore if it is null
     *
     * @param id the id of the accessLog to save.
     * @param accessLog the accessLog to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accessLog,
     * or with status {@code 400 (Bad Request)} if the accessLog is not valid,
     * or with status {@code 404 (Not Found)} if the accessLog is not found,
     * or with status {@code 500 (Internal Server Error)} if the accessLog couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/access-logs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AccessLog> partialUpdateAccessLog(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AccessLog accessLog
    ) throws URISyntaxException {
        log.debug("REST request to partial update AccessLog partially : {}, {}", id, accessLog);
        if (accessLog.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accessLog.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!accessLogRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AccessLog> result = accessLogService.partialUpdate(accessLog);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, accessLog.getId().toString())
        );
    }

    /**
     * {@code GET  /access-logs} : get all the accessLogs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accessLogs in body.
     */
    @GetMapping("/access-logs")
    public ResponseEntity<List<AccessLog>> getAllAccessLogs(
        AccessLogCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get AccessLogs by criteria: {}", criteria);
        Page<AccessLog> page = accessLogQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /access-logs/count} : count all the accessLogs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/access-logs/count")
    public ResponseEntity<Long> countAccessLogs(AccessLogCriteria criteria) {
        log.debug("REST request to count AccessLogs by criteria: {}", criteria);
        return ResponseEntity.ok().body(accessLogQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /access-logs/:id} : get the "id" accessLog.
     *
     * @param id the id of the accessLog to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accessLog, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/access-logs/{id}")
    public ResponseEntity<AccessLog> getAccessLog(@PathVariable Long id) {
        log.debug("REST request to get AccessLog : {}", id);
        Optional<AccessLog> accessLog = accessLogService.findOne(id);
        return ResponseUtil.wrapOrNotFound(accessLog);
    }

    /**
     * {@code DELETE  /access-logs/:id} : delete the "id" accessLog.
     *
     * @param id the id of the accessLog to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/access-logs/{id}")
    public ResponseEntity<Void> deleteAccessLog(@PathVariable Long id) {
        log.debug("REST request to delete AccessLog : {}", id);
        accessLogService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
