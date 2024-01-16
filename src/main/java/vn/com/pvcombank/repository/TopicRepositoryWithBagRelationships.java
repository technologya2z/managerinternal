package vn.com.pvcombank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import vn.com.pvcombank.domain.Topic;

public interface TopicRepositoryWithBagRelationships {
    Optional<Topic> fetchBagRelationships(Optional<Topic> topic);

    List<Topic> fetchBagRelationships(List<Topic> topics);

    Page<Topic> fetchBagRelationships(Page<Topic> topics);
}
