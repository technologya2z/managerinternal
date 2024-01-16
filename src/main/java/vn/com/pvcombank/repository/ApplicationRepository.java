package vn.com.pvcombank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.com.pvcombank.domain.Application;

/**
 * Spring Data SQL repository for the Application entity.
 */
@Repository
public interface ApplicationRepository
    extends ApplicationRepositoryWithBagRelationships, JpaRepository<Application, Long>, JpaSpecificationExecutor<Application> {
    default Optional<Application> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Application> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Application> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Modifying
    @Query(value = "delete from rel_application__databaseinfo u where u.application_id = ?1", nativeQuery = true)
    Integer deleterelapplicationtoDatabaseInfo(long id);

    @Modifying
    @Query(value = "delete from rel_application__operaunit u where u.application_id = ?1", nativeQuery = true)
    Integer deleterelapplicationtoOperaunit(long id);

    @Modifying
    @Query(value = "delete from rel_application__topic u where u.application_id = ?1", nativeQuery = true)
    Integer deleterelapplicationtoTopic(long id);

    @Modifying
    @Query(value = "delete from rel_topic__application u where u.application_id = ?1", nativeQuery = true)
    Integer deleterelTopictoApplication(long id);

    @Modifying
    @Query(value = "delete from rel_application__human u where u.application_id = ?1", nativeQuery = true)
    Integer deleterelApplicationToHuman(long id);

    @Modifying
    @Query(value = "delete from topic_in u where u.application_id = ?1", nativeQuery = true)
    Integer deleteTopicInApplication(long id);

    @Modifying
    @Query(value = "delete from topic_out u where u.application_id = ?1", nativeQuery = true)
    Integer deleteTopicOutApplication(long id);

    @Query(value = "select * from application\n" +
        "join api_info ai on application.id = ai.application_id\n" +
        "join api_in a on ai.id = a.api_info_id\n" +
        "where api_info_id = ?1", nativeQuery = true)
    List<Application> findAllApplicationByapiId(long apiId);

    @Query(value = "select * from application\n" +
        "join api_info ai on application.id = ai.application_id\n" +
        "join api_out a on ai.id = a.api_info_id\n" +
        "where api_info_id = ?1", nativeQuery = true)
    List<Application> findAllApplicationOutByapiId(long apiId);

}
