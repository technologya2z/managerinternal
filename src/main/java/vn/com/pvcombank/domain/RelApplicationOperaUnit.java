package vn.com.pvcombank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "rel_application__operaunit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RelApplicationOperaUnit {

    @Id
    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "operaunit_id")
    private Long operaunitId;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getOperaunitId() {
        return operaunitId;
    }

    public void setOperaunitId(Long operaunitId) {
        this.operaunitId = operaunitId;
    }
}
