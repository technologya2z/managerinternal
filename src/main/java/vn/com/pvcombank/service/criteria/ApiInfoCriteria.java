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
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.ApiInfo} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.ApiInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /api-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ApiInfoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter path;

    private StringFilter requestExample;

    private StringFilter responseExample;

    private LocalDateFilter dateCreate;

    private StringFilter description;

    private LongFilter apiInId;

    private LongFilter apiOutId;

    private LongFilter applicationId;

    private Boolean distinct;

    public ApiInfoCriteria() {}

    public ApiInfoCriteria(ApiInfoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.path = other.path == null ? null : other.path.copy();
        this.requestExample = other.requestExample == null ? null : other.requestExample.copy();
        this.responseExample = other.responseExample == null ? null : other.responseExample.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.apiInId = other.apiInId == null ? null : other.apiInId.copy();
        this.apiOutId = other.apiOutId == null ? null : other.apiOutId.copy();
        this.applicationId = other.applicationId == null ? null : other.applicationId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ApiInfoCriteria copy() {
        return new ApiInfoCriteria(this);
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

    public StringFilter getPath() {
        return path;
    }

    public StringFilter path() {
        if (path == null) {
            path = new StringFilter();
        }
        return path;
    }

    public void setPath(StringFilter path) {
        this.path = path;
    }

    public StringFilter getRequestExample() {
        return requestExample;
    }

    public StringFilter requestExample() {
        if (requestExample == null) {
            requestExample = new StringFilter();
        }
        return requestExample;
    }

    public void setRequestExample(StringFilter requestExample) {
        this.requestExample = requestExample;
    }

    public StringFilter getResponseExample() {
        return responseExample;
    }

    public StringFilter responseExample() {
        if (responseExample == null) {
            responseExample = new StringFilter();
        }
        return responseExample;
    }

    public void setResponseExample(StringFilter responseExample) {
        this.responseExample = responseExample;
    }

    public LocalDateFilter getDateCreate() {
        return dateCreate;
    }

    public LocalDateFilter dateCreate() {
        if (dateCreate == null) {
            dateCreate = new LocalDateFilter();
        }
        return dateCreate;
    }

    public void setDateCreate(LocalDateFilter dateCreate) {
        this.dateCreate = dateCreate;
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

    public LongFilter getApiInId() {
        return apiInId;
    }

    public LongFilter apiInId() {
        if (apiInId == null) {
            apiInId = new LongFilter();
        }
        return apiInId;
    }

    public void setApiInId(LongFilter apiInId) {
        this.apiInId = apiInId;
    }

    public LongFilter getApiOutId() {
        return apiOutId;
    }

    public LongFilter apiOutId() {
        if (apiOutId == null) {
            apiOutId = new LongFilter();
        }
        return apiOutId;
    }

    public void setApiOutId(LongFilter apiOutId) {
        this.apiOutId = apiOutId;
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
        final ApiInfoCriteria that = (ApiInfoCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(path, that.path) &&
            Objects.equals(requestExample, that.requestExample) &&
            Objects.equals(responseExample, that.responseExample) &&
            Objects.equals(dateCreate, that.dateCreate) &&
            Objects.equals(description, that.description) &&
            Objects.equals(apiInId, that.apiInId) &&
            Objects.equals(apiOutId, that.apiOutId) &&
            Objects.equals(applicationId, that.applicationId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            path,
            requestExample,
            responseExample,
            dateCreate,
            description,
            apiInId,
            apiOutId,
            applicationId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiInfoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (path != null ? "path=" + path + ", " : "") +
            (requestExample != null ? "requestExample=" + requestExample + ", " : "") +
            (responseExample != null ? "responseExample=" + responseExample + ", " : "") +
            (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (apiInId != null ? "apiInId=" + apiInId + ", " : "") +
            (apiOutId != null ? "apiOutId=" + apiOutId + ", " : "") +
            (applicationId != null ? "applicationId=" + applicationId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
