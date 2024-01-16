package vn.com.pvcombank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import vn.com.pvcombank.domain.Jobtitle;

public interface JobtitleRepositoryWithBagRelationships {
    Optional<Jobtitle> fetchBagRelationships(Optional<Jobtitle> jobtitle);

    List<Jobtitle> fetchBagRelationships(List<Jobtitle> jobtitles);

    Page<Jobtitle> fetchBagRelationships(Page<Jobtitle> jobtitles);
}
