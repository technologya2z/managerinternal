package vn.com.pvcombank.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import vn.com.pvcombank.domain.enumeration.DatabaseType;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.DatabaseInfo} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.DatabaseInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /database-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class DatabaseInfoCriteria implements Serializable, Criteria {

    /**
     * Class for filtering DatabaseType
     */
    public static class DatabaseTypeFilter extends Filter<DatabaseType> {

        public DatabaseTypeFilter() {}

        public DatabaseTypeFilter(DatabaseTypeFilter filter) {
            super(filter);
        }

        @Override
        public DatabaseTypeFilter copy() {
            return new DatabaseTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter serviceName;

    private StringFilter userName;

    private StringFilter passWord;

    private StringFilter ipServer;

    private StringFilter port;

    private DatabaseTypeFilter dataType;

    private LongFilter applicationId;

    private Boolean distinct;

    public DatabaseInfoCriteria() {}

    public DatabaseInfoCriteria(DatabaseInfoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.serviceName = other.serviceName == null ? null : other.serviceName.copy();
        this.userName = other.userName == null ? null : other.userName.copy();
        this.passWord = other.passWord == null ? null : other.passWord.copy();
        this.ipServer = other.ipServer == null ? null : other.ipServer.copy();
        this.port = other.port == null ? null : other.port.copy();
        this.dataType = other.dataType == null ? null : other.dataType.copy();
        this.applicationId = other.applicationId == null ? null : other.applicationId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public DatabaseInfoCriteria copy() {
        return new DatabaseInfoCriteria(this);
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

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getServiceName() {
        return serviceName;
    }

    public StringFilter serviceName() {
        if (serviceName == null) {
            serviceName = new StringFilter();
        }
        return serviceName;
    }

    public void setServiceName(StringFilter serviceName) {
        this.serviceName = serviceName;
    }

    public StringFilter getUserName() {
        return userName;
    }

    public StringFilter userName() {
        if (userName == null) {
            userName = new StringFilter();
        }
        return userName;
    }

    public void setUserName(StringFilter userName) {
        this.userName = userName;
    }

    public StringFilter getPassWord() {
        return passWord;
    }

    public StringFilter passWord() {
        if (passWord == null) {
            passWord = new StringFilter();
        }
        return passWord;
    }

    public void setPassWord(StringFilter passWord) {
        this.passWord = passWord;
    }

    public StringFilter getIpServer() {
        return ipServer;
    }

    public StringFilter ipServer() {
        if (ipServer == null) {
            ipServer = new StringFilter();
        }
        return ipServer;
    }

    public void setIpServer(StringFilter ipServer) {
        this.ipServer = ipServer;
    }

    public StringFilter getPort() {
        return port;
    }

    public StringFilter port() {
        if (port == null) {
            port = new StringFilter();
        }
        return port;
    }

    public void setPort(StringFilter port) {
        this.port = port;
    }

    public DatabaseTypeFilter getDataType() {
        return dataType;
    }

    public DatabaseTypeFilter dataType() {
        if (dataType == null) {
            dataType = new DatabaseTypeFilter();
        }
        return dataType;
    }

    public void setDataType(DatabaseTypeFilter dataType) {
        this.dataType = dataType;
    }

    public LongFilter getApplicationId() {
        return applicationId;
    }

    public LongFilter applicationId() {
        if (applicationId == null) {
            applicationId = new LongFilter();
        }
        return applicationId;
    }

    public void setApplicationId(LongFilter applicationId) {
        this.applicationId = applicationId;
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
        final DatabaseInfoCriteria that = (DatabaseInfoCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(serviceName, that.serviceName) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(passWord, that.passWord) &&
            Objects.equals(ipServer, that.ipServer) &&
            Objects.equals(port, that.port) &&
            Objects.equals(dataType, that.dataType) &&
            Objects.equals(applicationId, that.applicationId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, serviceName, userName, passWord, ipServer, port, dataType, applicationId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DatabaseInfoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (serviceName != null ? "serviceName=" + serviceName + ", " : "") +
            (userName != null ? "userName=" + userName + ", " : "") +
            (passWord != null ? "passWord=" + passWord + ", " : "") +
            (ipServer != null ? "ipServer=" + ipServer + ", " : "") +
            (port != null ? "port=" + port + ", " : "") +
            (dataType != null ? "dataType=" + dataType + ", " : "") +
            (applicationId != null ? "applicationId=" + applicationId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
