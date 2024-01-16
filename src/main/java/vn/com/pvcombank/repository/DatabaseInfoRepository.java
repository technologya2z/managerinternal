package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.DatabaseInfo;

/**
 * Spring Data SQL repository for the DatabaseInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DatabaseInfoRepository extends JpaRepository<DatabaseInfo, Long>, JpaSpecificationExecutor<DatabaseInfo> {}
