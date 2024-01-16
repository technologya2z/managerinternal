package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.TopicOut;

/**
 * Spring Data SQL repository for the TopicOut entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicOutRepository extends JpaRepository<TopicOut, Long>, JpaSpecificationExecutor<TopicOut> {}
