package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.TopicIn;

/**
 * Spring Data SQL repository for the TopicIn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicInRepository extends JpaRepository<TopicIn, Long>, JpaSpecificationExecutor<TopicIn> {}
