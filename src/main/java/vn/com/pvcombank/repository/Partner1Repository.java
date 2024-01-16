package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.Partner1;

/**
 * Spring Data SQL repository for the Partner1 entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Partner1Repository extends JpaRepository<Partner1, Long>, JpaSpecificationExecutor<Partner1> {}
