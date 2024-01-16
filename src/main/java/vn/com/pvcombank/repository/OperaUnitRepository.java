package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.OperaUnit;

/**
 * Spring Data SQL repository for the OperaUnit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperaUnitRepository extends JpaRepository<OperaUnit, Long>, JpaSpecificationExecutor<OperaUnit> {}
