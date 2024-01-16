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
 * Criteria class for the {@link vn.com.pvcombank.domain.ApiOut} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.ApiOutResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /api-outs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ApiOutCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter description;

    private LocalDateFilter dateConnect;

    private LongFilter apiInfoId;

    private LongFilter applicationId;

    private Boolean distinct;

    public ApiOutCriteria() {}

    public ApiOutCriteria(ApiOutCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.dateConnect = other.dateConnect == null ? null : other.dateConnect.copy();
        this.apiInfoId = other.apiInfoId == null ? null : other.apiInfoId.copy();
        this.applicationId = other.applicationId == null ? null : other.applicationId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ApiOutCriteria copy() {
        return new ApiOutCriteria(this);
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

    public LocalDateFilter getDateConnect() {
        return dateConnect;
    }

    public LocalDateFilter dateConnect() {
        if (dateConnect == null) {
            dateConnect = new LocalDateFilter();
        }
        return dateConnect;
    }

    public void setDateConnect(LocalDateFilter dateConnect) {
        this.dateConnect = dateConnect;
    }

    public LongFilter getApiInfoId() {
        return apiInfoId;
    }

    public LongFilter apiInfoId() {
        if (apiInfoId == null) {
            apiInfoId = new LongFilter();
        }
        return apiInfoId;
    }

    public void setApiInfoId(LongFilter apiInfoId) {
        this.apiInfoId = apiInfoId;
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
        final ApiOutCriteria that = (ApiOutCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(description, that.description) &&
            Objects.equals(dateConnect, that.dateConnect) &&
            Objects.equals(apiInfoId, that.apiInfoId) &&
            Objects.equals(applicationId, that.applicationId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, dateConnect, apiInfoId, applicationId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiOutCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (dateConnect != null ? "dateConnect=" + dateConnect + ", " : "") +
            (apiInfoId != null ? "apiInfoId=" + apiInfoId + ", " : "") +
            (applicationId != null ? "applicationId=" + applicationId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
