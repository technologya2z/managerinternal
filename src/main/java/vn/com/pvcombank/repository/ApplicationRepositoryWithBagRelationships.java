package vn.com.pvcombank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import vn.com.pvcombank.domain.Application;

public interface ApplicationRepositoryWithBagRelationships {
    Optional<Application> fetchBagRelationships(Optional<Application> application);

    List<Application> fetchBagRelationships(List<Application> applications);

    Page<Application> fetchBagRelationships(Page<Application> applications);
}
