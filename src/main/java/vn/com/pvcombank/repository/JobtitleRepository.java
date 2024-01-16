package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.Jobtitle;

/**
 * Spring Data SQL repository for the Jobtitle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobtitleRepository extends JpaRepository<Jobtitle, Long>, JpaSpecificationExecutor<Jobtitle> {}
