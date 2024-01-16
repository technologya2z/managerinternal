package vn.com.pvcombank.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.domain.OperaUnit;
import vn.com.pvcombank.domain.RelApplicationOperaUnit;

public interface RelApplicationOperaUnitRepo
    extends JpaRepository<RelApplicationOperaUnit, Long>, JpaSpecificationExecutor<RelApplicationOperaUnit> {
    List<RelApplicationOperaUnit> findAllByOperaunitId(Long id);
}
