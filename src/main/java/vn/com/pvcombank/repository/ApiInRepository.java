package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.ApiIn;

/**
 * Spring Data SQL repository for the ApiIn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiInRepository extends JpaRepository<ApiIn, Long>, JpaSpecificationExecutor<ApiIn> {
    @Modifying
    @Query(value = "update api_in set application_id = null where application_id = ?1", nativeQuery = true)
    void deleterelApiIntoApplication(long id);

    @Modifying
    @Query(value = "update api_in set api_info_id = null where api_info_id = ?1", nativeQuery = true)
    void deleterelApiIntoApiInfo(long id);
}
