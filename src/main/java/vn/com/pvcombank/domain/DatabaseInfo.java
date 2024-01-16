package vn.com.pvcombank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import vn.com.pvcombank.domain.enumeration.DatabaseType;

/**
 * A DatabaseInfo.
 */
@Entity
@Table(name = "database_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DatabaseInfo extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_word")
    private String passWord;

    @Column(name = "ip_server")
    private String ipServer;

    @Column(name = "port")
    private String port;

    @Enumerated(EnumType.STRING)
    @Column(name = "data_type")
    private DatabaseType dataType;

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

    @ManyToMany(mappedBy = "databaseinfos", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "apiIns", "apiOuts", "apiInfos", "operaunits", "topics", "databaseinfos", "humans" },
        allowSetters = true
    )
    private Set<Application> applications = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DatabaseInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public DatabaseInfo name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public DatabaseInfo serviceName(String serviceName) {
        this.setServiceName(serviceName);
        return this;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUserName() {
        return this.userName;
    }

    public DatabaseInfo userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public DatabaseInfo passWord(String passWord) {
        this.setPassWord(passWord);
        return this;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIpServer() {
        return this.ipServer;
    }

    public DatabaseInfo ipServer(String ipServer) {
        this.setIpServer(ipServer);
        return this;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }

    public String getPort() {
        return this.port;
    }

    public DatabaseInfo port(String port) {
        this.setPort(port);
        return this;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public DatabaseType getDataType() {
        return this.dataType;
    }

    public DatabaseInfo dataType(DatabaseType dataType) {
        this.setDataType(dataType);
        return this;
    }

    public void setDataType(DatabaseType dataType) {
        this.dataType = dataType;
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

    public Set<Application> getApplications() {
        return this.applications;
    }

    public void setApplications(Set<Application> applications) {
        if (this.applications != null) {
            this.applications.forEach(i -> i.removeDatabaseinfo(this));
        }
        if (applications != null) {
            applications.forEach(i -> i.addDatabaseinfo(this));
        }
        this.applications = applications;
    }

    public DatabaseInfo applications(Set<Application> applications) {
        this.setApplications(applications);
        return this;
    }

    public DatabaseInfo addApplication(Application application) {
        this.applications.add(application);
        application.getDatabaseinfos().add(this);
        return this;
    }

    public DatabaseInfo removeApplication(Application application) {
        this.applications.remove(application);
        application.getDatabaseinfos().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DatabaseInfo)) {
            return false;
        }
        return id != null && id.equals(((DatabaseInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DatabaseInfo{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", serviceName='" + getServiceName() + "'" +
            ", userName='" + getUserName() + "'" +
            ", passWord='" + getPassWord() + "'" +
            ", ipServer='" + getIpServer() + "'" +
            ", port='" + getPort() + "'" +
            ", dataType='" + getDataType() + "'" +
            "}";
    }
}
