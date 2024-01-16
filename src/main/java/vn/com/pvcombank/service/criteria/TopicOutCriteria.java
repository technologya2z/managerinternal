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
 * Criteria class for the {@link vn.com.pvcombank.domain.TopicOut} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.TopicOutResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /topic-outs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class TopicOutCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter description;

    private LocalDateFilter dateConnect;

    private LongFilter topicId;

    private LongFilter applicationId;

    private Boolean distinct;

    public TopicOutCriteria() {}

    public TopicOutCriteria(TopicOutCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.dateConnect = other.dateConnect == null ? null : other.dateConnect.copy();
        this.topicId = other.topicId == null ? null : other.topicId.copy();
        this.applicationId = other.applicationId == null ? null : other.applicationId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TopicOutCriteria copy() {
        return new TopicOutCriteria(this);
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

    public LongFilter getTopicId() {
        return topicId;
    }

    public LongFilter topicId() {
        if (topicId == null) {
            topicId = new LongFilter();
        }
        return topicId;
    }

    public void setTopicId(LongFilter topicId) {
        this.topicId = topicId;
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
        final TopicOutCriteria that = (TopicOutCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(description, that.description) &&
            Objects.equals(dateConnect, that.dateConnect) &&
            Objects.equals(topicId, that.topicId) &&
            Objects.equals(applicationId, that.applicationId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, dateConnect, topicId, applicationId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TopicOutCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (dateConnect != null ? "dateConnect=" + dateConnect + ", " : "") +
            (topicId != null ? "topicId=" + topicId + ", " : "") +
            (applicationId != null ? "applicationId=" + applicationId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
