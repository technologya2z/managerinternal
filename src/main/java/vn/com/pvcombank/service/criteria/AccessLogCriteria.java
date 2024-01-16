package vn.com.pvcombank.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.AccessLog} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.AccessLogResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /access-logs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class AccessLogCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter empCode;

    private StringFilter empUsername;

    private StringFilter empFullName;

    private StringFilter accessResource;

    private StringFilter description;

    private StringFilter ipAddress;

    private InstantFilter accessTime;

    private Boolean distinct;

    public AccessLogCriteria() {}

    public AccessLogCriteria(AccessLogCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.empCode = other.empCode == null ? null : other.empCode.copy();
        this.empUsername = other.empUsername == null ? null : other.empUsername.copy();
        this.empFullName = other.empFullName == null ? null : other.empFullName.copy();
        this.accessResource = other.accessResource == null ? null : other.accessResource.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.ipAddress = other.ipAddress == null ? null : other.ipAddress.copy();
        this.accessTime = other.accessTime == null ? null : other.accessTime.copy();
        this.distinct = other.distinct;
    }

    @Override
    public AccessLogCriteria copy() {
        return new AccessLogCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getEmpCode() {
        return empCode;
    }

    public StringFilter empCode() {
        if (empCode == null) {
            empCode = new StringFilter();
        }
        return empCode;
    }

    public void setEmpCode(StringFilter empCode) {
        this.empCode = empCode;
    }

    public StringFilter getEmpUsername() {
        return empUsername;
    }

    public StringFilter empUsername() {
        if (empUsername == null) {
            empUsername = new StringFilter();
        }
        return empUsername;
    }

    public void setEmpUsername(StringFilter empUsername) {
        this.empUsername = empUsername;
    }

    public StringFilter getEmpFullName() {
        return empFullName;
    }

    public StringFilter empFullName() {
        if (empFullName == null) {
            empFullName = new StringFilter();
        }
        return empFullName;
    }

    public void setEmpFullName(StringFilter empFullName) {
        this.empFullName = empFullName;
    }

    public StringFilter getAccessResource() {
        return accessResource;
    }

    public StringFilter accessResource() {
        if (accessResource == null) {
            accessResource = new StringFilter();
        }
        return accessResource;
    }

    public void setAccessResource(StringFilter accessResource) {
        this.accessResource = accessResource;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getIpAddress() {
        return ipAddress;
    }

    public StringFilter ipAddress() {
        if (ipAddress == null) {
            ipAddress = new StringFilter();
        }
        return ipAddress;
    }

    public void setIpAddress(StringFilter ipAddress) {
        this.ipAddress = ipAddress;
    }

    public InstantFilter getAccessTime() {
        return accessTime;
    }

    public InstantFilter accessTime() {
        if (accessTime == null) {
            accessTime = new InstantFilter();
        }
        return accessTime;
    }

    public void setAccessTime(InstantFilter accessTime) {
        this.accessTime = accessTime;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final AccessLogCriteria that = (AccessLogCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(empCode, that.empCode) &&
            Objects.equals(empUsername, that.empUsername) &&
            Objects.equals(empFullName, that.empFullName) &&
            Objects.equals(accessResource, that.accessResource) &&
            Objects.equals(description, that.description) &&
            Objects.equals(ipAddress, that.ipAddress) &&
            Objects.equals(accessTime, that.accessTime) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, empCode, empUsername, empFullName, accessResource, description, ipAddress, accessTime, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccessLogCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (empCode != null ? "empCode=" + empCode + ", " : "") +
            (empUsername != null ? "empUsername=" + empUsername + ", " : "") +
            (empFullName != null ? "empFullName=" + empFullName + ", " : "") +
            (accessResource != null ? "accessResource=" + accessResource + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (ipAddress != null ? "ipAddress=" + ipAddress + ", " : "") +
            (accessTime != null ? "accessTime=" + accessTime + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
