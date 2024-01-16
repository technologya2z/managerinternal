package vn.com.pvcombank.web.rest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import vn.com.pvcombank.service.*;
import vn.com.pvcombank.service.criteria.ApiInfoCriteria;
import vn.com.pvcombank.service.criteria.ApplicationCriteria;
import vn.com.pvcombank.service.criteria.DatabaseInfoCriteria;
import vn.com.pvcombank.service.criteria.HumansCriteria;
import vn.com.pvcombank.web.rest.vm.DashboardDataVM;

/**
 * Dashboard Resource
 */
@RestController
@RequestMapping("/api")
public class DashboardResource {

    private final Logger log = LoggerFactory.getLogger(DashboardResource.class);

    @Autowired
    HumansQueryService humansQueryService;

    @Autowired
    ApplicationQueryService applicationQueryService;

    @Autowired
    DatabaseInfoQueryService databaseInfoQueryService;

    @Autowired
    ApiInfoQueryService apiInfoQueryService;

    /**
     * {@code GET  /dashboard} : get the dashboard data
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gifts in body.
     */
    @GetMapping("/public/dashboard")
    public ResponseEntity<DashboardDataVM> getDashboard() {
        log.debug("REST request to get dashboard data");
        DashboardDataVM data = new DashboardDataVM();
        // tổng số nhân sự
        data.setTotalHumans(humansQueryService.countByCriteria(new HumansCriteria()));

        // tổng số ứng dụng
        data.setTotalApplications(applicationQueryService.countByCriteria(new ApplicationCriteria()));

        // tổng số database
        data.setTotalDatabases(databaseInfoQueryService.countByCriteria(new DatabaseInfoCriteria()));

        // tổng số APi
        data.setTotalApi(apiInfoQueryService.countByCriteria(new ApiInfoCriteria()));
        return ResponseEntity.ok().body(data);
    }
}
