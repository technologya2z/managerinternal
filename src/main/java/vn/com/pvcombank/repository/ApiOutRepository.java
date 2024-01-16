package vn.com.pvcombank.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.ApiOut;

/**
 * Spring Data SQL repository for the ApiOut entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiOutRepository extends JpaRepository<ApiOut, Long>, JpaSpecificationExecutor<ApiOut> {
    @Modifying
    @Query(value = "update api_out set application_id = null where application_id = ?1", nativeQuery = true)
    void deleterelApiOuttoApplication(long id);

    @Modifying
    @Query(value = "update api_out set api_info_id = null where api_info_id = ?1", nativeQuery = true)
    void deleterelApiOuttoApiInfo(long id);
}
