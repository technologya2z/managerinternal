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

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.Topic} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.TopicResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /topics?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class TopicCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter bootrapServer;

    private StringFilter locationTopic;

    private StringFilter rootproducer;

    private StringFilter rootTable;

    private StringFilter message;

    private StringFilter linkDoc;

    private LongFilter topicInId;

    private LongFilter topicOutId;

    private LongFilter applicationId;

    private Boolean distinct;

    public TopicCriteria() {}

    public TopicCriteria(TopicCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.bootrapServer = other.bootrapServer == null ? null : other.bootrapServer.copy();
        this.locationTopic = other.locationTopic == null ? null : other.locationTopic.copy();
        this.rootproducer = other.rootproducer == null ? null : other.rootproducer.copy();
        this.rootTable = other.rootTable == null ? null : other.rootTable.copy();
        this.message = other.message == null ? null : other.message.copy();
        this.linkDoc = other.linkDoc == null ? null : other.linkDoc.copy();
        this.topicInId = other.topicInId == null ? null : other.topicInId.copy();
        this.topicOutId = other.topicOutId == null ? null : other.topicOutId.copy();
        this.applicationId = other.applicationId == null ? null : other.applicationId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TopicCriteria copy() {
        return new TopicCriteria(this);
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

    public StringFilter getBootrapServer() {
        return bootrapServer;
    }

    public StringFilter bootrapServer() {
        if (bootrapServer == null) {
            bootrapServer = new StringFilter();
        }
        return bootrapServer;
    }

    public void setBootrapServer(StringFilter bootrapServer) {
        this.bootrapServer = bootrapServer;
    }

    public StringFilter getLocationTopic() {
        return locationTopic;
    }

    public StringFilter locationTopic() {
        if (locationTopic == null) {
            locationTopic = new StringFilter();
        }
        return locationTopic;
    }

    public void setLocationTopic(StringFilter locationTopic) {
        this.locationTopic = locationTopic;
    }

    public StringFilter getRootproducer() {
        return rootproducer;
    }

    public StringFilter rootproducer() {
        if (rootproducer == null) {
            rootproducer = new StringFilter();
        }
        return rootproducer;
    }

    public void setRootproducer(StringFilter rootproducer) {
        this.rootproducer = rootproducer;
    }

    public StringFilter getRootTable() {
        return rootTable;
    }

    public StringFilter rootTable() {
        if (rootTable == null) {
            rootTable = new StringFilter();
        }
        return rootTable;
    }

    public void setRootTable(StringFilter rootTable) {
        this.rootTable = rootTable;
    }

    public StringFilter getMessage() {
        return message;
    }

    public StringFilter message() {
        if (message == null) {
            message = new StringFilter();
        }
        return message;
    }

    public void setMessage(StringFilter message) {
        this.message = message;
    }

    public StringFilter getLinkDoc() {
        return linkDoc;
    }

    public StringFilter linkDoc() {
        if (linkDoc == null) {
            linkDoc = new StringFilter();
        }
        return linkDoc;
    }

    public void setLinkDoc(StringFilter linkDoc) {
        this.linkDoc = linkDoc;
    }

    public LongFilter getTopicInId() {
        return topicInId;
    }

    public LongFilter topicInId() {
        if (topicInId == null) {
            topicInId = new LongFilter();
        }
        return topicInId;
    }

    public void setTopicInId(LongFilter topicInId) {
        this.topicInId = topicInId;
    }

    public LongFilter getTopicOutId() {
        return topicOutId;
    }

    public LongFilter topicOutId() {
        if (topicOutId == null) {
            topicOutId = new LongFilter();
        }
        return topicOutId;
    }

    public void setTopicOutId(LongFilter topicOutId) {
        this.topicOutId = topicOutId;
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
        final TopicCriteria that = (TopicCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(bootrapServer, that.bootrapServer) &&
            Objects.equals(locationTopic, that.locationTopic) &&
            Objects.equals(rootproducer, that.rootproducer) &&
            Objects.equals(rootTable, that.rootTable) &&
            Objects.equals(message, that.message) &&
            Objects.equals(linkDoc, that.linkDoc) &&
            Objects.equals(topicInId, that.topicInId) &&
            Objects.equals(topicOutId, that.topicOutId) &&
            Objects.equals(applicationId, that.applicationId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            bootrapServer,
            locationTopic,
            rootproducer,
            rootTable,
            message,
            linkDoc,
            topicInId,
            topicOutId,
            applicationId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TopicCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (bootrapServer != null ? "bootrapServer=" + bootrapServer + ", " : "") +
            (locationTopic != null ? "locationTopic=" + locationTopic + ", " : "") +
            (rootproducer != null ? "rootproducer=" + rootproducer + ", " : "") +
            (rootTable != null ? "rootTable=" + rootTable + ", " : "") +
            (message != null ? "message=" + message + ", " : "") +
            (linkDoc != null ? "linkDoc=" + linkDoc + ", " : "") +
            (topicInId != null ? "topicInId=" + topicInId + ", " : "") +
            (topicOutId != null ? "topicOutId=" + topicOutId + ", " : "") +
            (applicationId != null ? "applicationId=" + applicationId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
