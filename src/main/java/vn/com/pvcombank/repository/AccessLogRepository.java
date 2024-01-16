package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.AccessLog;

/**
 * Spring Data SQL repository for the AccessLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long>, JpaSpecificationExecutor<AccessLog> {}
