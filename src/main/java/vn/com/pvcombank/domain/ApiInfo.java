package vn.com.pvcombank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import vn.com.pvcombank.service.dto.JsonSwaggerDTO;

/**
 * A ApiInfo.
 */
@Entity
@Table(name = "api_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ApiInfo extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    @Column(name = "request_example")
    private String requestExample;

    @Column(name = "response_example")
    private String responseExample;

    @Column(name = "date_create")
    private LocalDate dateCreate;

    @Column(name = "description")
    private String description;

    @Column(name = "method")
    private String method;

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

    @OneToMany(mappedBy = "apiInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "apiInfo", "application" }, allowSetters = true)
    private Set<ApiIn> apiIns = new HashSet<>();

    @OneToMany(mappedBy = "apiInfo")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "apiInfo", "application" }, allowSetters = true)
    private Set<ApiOut> apiOuts = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "apiIns", "apiOuts", "apiInfos", "operaunits", "topics", "databaseinfos", "humans" },
        allowSetters = true
    )
    private Application application;

    public ApiInfo(JsonSwaggerDTO jsonSwaggerDTO, Application application) {
        this.name = jsonSwaggerDTO.getNameApi();
        this.path = jsonSwaggerDTO.getPathApi();
        this.method = jsonSwaggerDTO.getMethod();
        this.application = application;
    }

    public ApiInfo() {}

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ApiInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public ApiInfo name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return this.path;
    }

    public ApiInfo path(String path) {
        this.setPath(path);
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestExample() {
        return this.requestExample;
    }

    public ApiInfo requestExample(String requestExample) {
        this.setRequestExample(requestExample);
        return this;
    }

    public void setRequestExample(String requestExample) {
        this.requestExample = requestExample;
    }

    public String getResponseExample() {
        return this.responseExample;
    }

    public ApiInfo responseExample(String responseExample) {
        this.setResponseExample(responseExample);
        return this;
    }

    public void setResponseExample(String responseExample) {
        this.responseExample = responseExample;
    }

    public LocalDate getDateCreate() {
        return this.dateCreate;
    }

    public ApiInfo dateCreate(LocalDate dateCreate) {
        this.setDateCreate(dateCreate);
        return this;
    }

    public void setDateCreate(LocalDate dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDescription() {
        return this.description;
    }

    public ApiInfo description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<ApiIn> getApiIns() {
        return this.apiIns;
    }

    public void setApiIns(Set<ApiIn> apiIns) {
        if (this.apiIns != null) {
            this.apiIns.forEach(i -> i.setApiInfo(null));
        }
        if (apiIns != null) {
            apiIns.forEach(i -> i.setApiInfo(this));
        }
        this.apiIns = apiIns;
    }

    public ApiInfo apiIns(Set<ApiIn> apiIns) {
        this.setApiIns(apiIns);
        return this;
    }

    public ApiInfo addApiIn(ApiIn apiIn) {
        this.apiIns.add(apiIn);
        apiIn.setApiInfo(this);
        return this;
    }

    public ApiInfo removeApiIn(ApiIn apiIn) {
        this.apiIns.remove(apiIn);
        apiIn.setApiInfo(null);
        return this;
    }

    public Set<ApiOut> getApiOuts() {
        return this.apiOuts;
    }

    public void setApiOuts(Set<ApiOut> apiOuts) {
        if (this.apiOuts != null) {
            this.apiOuts.forEach(i -> i.setApiInfo(null));
        }
        if (apiOuts != null) {
            apiOuts.forEach(i -> i.setApiInfo(this));
        }
        this.apiOuts = apiOuts;
    }

    public ApiInfo apiOuts(Set<ApiOut> apiOuts) {
        this.setApiOuts(apiOuts);
        return this;
    }

    public ApiInfo addApiOut(ApiOut apiOut) {
        this.apiOuts.add(apiOut);
        apiOut.setApiInfo(this);
        return this;
    }

    public ApiInfo removeApiOut(ApiOut apiOut) {
        this.apiOuts.remove(apiOut);
        apiOut.setApiInfo(null);
        return this;
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public ApiInfo application(Application application) {
        this.setApplication(application);
        return this;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiInfo)) {
            return false;
        }
        return id != null && id.equals(((ApiInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
    // prettier-ignore
}
