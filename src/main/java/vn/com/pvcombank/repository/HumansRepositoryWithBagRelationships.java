package vn.com.pvcombank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import vn.com.pvcombank.domain.Humans;

public interface HumansRepositoryWithBagRelationships {
    Optional<Humans> fetchBagRelationships(Optional<Humans> humans);

    List<Humans> fetchBagRelationships(List<Humans> humans);

    Page<Humans> fetchBagRelationships(Page<Humans> humans);
}
