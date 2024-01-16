package vn.com.pvcombank.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AccessLog.
 */
@Entity
@Table(name = "access_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AccessLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 50)
    @Column(name = "emp_code", length = 50)
    private String empCode;

    @Size(max = 50)
    @Column(name = "emp_username", length = 50)
    private String empUsername;

    @Size(max = 50)
    @Column(name = "emp_full_name", length = 50)
    private String empFullName;

    @Size(max = 100)
    @Column(name = "access_resource", length = 100)
    private String accessResource;

    @Column(name = "description")
    private String description;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "access_time")
    private Instant accessTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AccessLog id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpCode() {
        return this.empCode;
    }

    public AccessLog empCode(String empCode) {
        this.setEmpCode(empCode);
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpUsername() {
        return this.empUsername;
    }

    public AccessLog empUsername(String empUsername) {
        this.setEmpUsername(empUsername);
        return this;
    }

    public void setEmpUsername(String empUsername) {
        this.empUsername = empUsername;
    }

    public String getEmpFullName() {
        return this.empFullName;
    }

    public AccessLog empFullName(String empFullName) {
        this.setEmpFullName(empFullName);
        return this;
    }

    public void setEmpFullName(String empFullName) {
        this.empFullName = empFullName;
    }

    public String getAccessResource() {
        return this.accessResource;
    }

    public AccessLog accessResource(String accessResource) {
        this.setAccessResource(accessResource);
        return this;
    }

    public void setAccessResource(String accessResource) {
        this.accessResource = accessResource;
    }

    public String getDescription() {
        return this.description;
    }

    public AccessLog description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public AccessLog ipAddress(String ipAddress) {
        this.setIpAddress(ipAddress);
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Instant getAccessTime() {
        return this.accessTime;
    }

    public AccessLog accessTime(Instant accessTime) {
        this.setAccessTime(accessTime);
        return this;
    }

    public void setAccessTime(Instant accessTime) {
        this.accessTime = accessTime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccessLog)) {
            return false;
        }
        return id != null && id.equals(((AccessLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccessLog{" +
            "id=" + getId() +
            ", empCode='" + getEmpCode() + "'" +
            ", empUsername='" + getEmpUsername() + "'" +
            ", empFullName='" + getEmpFullName() + "'" +
            ", accessResource='" + getAccessResource() + "'" +
            ", description='" + getDescription() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", accessTime='" + getAccessTime() + "'" +
            "}";
    }
}
