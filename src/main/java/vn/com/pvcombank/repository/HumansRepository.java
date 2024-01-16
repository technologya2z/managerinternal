package vn.com.pvcombank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.Humans;

/**
 * Spring Data SQL repository for the Humans entity.
 */
@Repository
public interface HumansRepository
    extends HumansRepositoryWithBagRelationships, JpaRepository<Humans, Long>, JpaSpecificationExecutor<Humans> {
    default Optional<Humans> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Humans> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Humans> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
