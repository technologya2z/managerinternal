package vn.com.pvcombank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.ApiInfo;

/**
 * Spring Data SQL repository for the ApiInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiInfoRepository extends JpaRepository<ApiInfo, Long>, JpaSpecificationExecutor<ApiInfo> {
    @Modifying
    @Query(value = "update api_info set application_id = null where application_id = ?1", nativeQuery = true)
    void deleterelApiInfotoApplication(long id);

    @Query(value = "select * from api_info where application_id = ?1", nativeQuery = true)
    List<ApiInfo> findAllByApplication(long id);
}
