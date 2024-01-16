package vn.com.pvcombank.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.domain.Topic;

/**
 * Spring Data SQL repository for the Topic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TopicRepository extends TopicRepositoryWithBagRelationships, JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic> {
    default Optional<Topic> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }
}
