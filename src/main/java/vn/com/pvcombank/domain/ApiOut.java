package vn.com.pvcombank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * A ApiOut.
 */
@Entity
@Table(name = "api_out")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApiOut extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "date_connect")
    private LocalDate dateConnect;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "created_by", length = 50, updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "last_modified_date", insertable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;

    @Size(max = 50)
    @Column(name = "last_modified_by", length = 50, insertable = false)
    @LastModifiedBy
    private String lastModifiedBy;

    @ManyToOne
    @JsonIgnoreProperties(value = { "apiIns", "apiOuts", "application" }, allowSetters = true)
    private ApiInfo apiInfo;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "apiIns", "apiOuts", "apiInfos", "operaunits", "topics", "databaseinfos", "humans" },
        allowSetters = true
    )
    private Application application;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ApiOut id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public ApiOut description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateConnect() {
        return this.dateConnect;
    }

    public ApiOut dateConnect(LocalDate dateConnect) {
        this.setDateConnect(dateConnect);
        return this;
    }

    public void setDateConnect(LocalDate dateConnect) {
        this.dateConnect = dateConnect;
    }

    @Override
    public Instant getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ApiInfo getApiInfo() {
        return this.apiInfo;
    }

    public void setApiInfo(ApiInfo apiInfo) {
        this.apiInfo = apiInfo;
    }

    public ApiOut apiInfo(ApiInfo apiInfo) {
        this.setApiInfo(apiInfo);
        return this;
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public ApiOut application(Application application) {
        this.setApplication(application);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiOut)) {
            return false;
        }
        return id != null && id.equals(((ApiOut) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
