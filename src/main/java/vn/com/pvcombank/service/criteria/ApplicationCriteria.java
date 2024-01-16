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
import vn.com.pvcombank.domain.enumeration.ApplicationType;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.Application} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.ApplicationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /applications?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class ApplicationCriteria implements Serializable, Criteria {

    /**
     * Class for filtering ApplicationType
     */
    public static class ApplicationTypeFilter extends Filter<ApplicationType> {

        public ApplicationTypeFilter() {}

        public ApplicationTypeFilter(ApplicationTypeFilter filter) {
            super(filter);
        }

        @Override
        public ApplicationTypeFilter copy() {
            return new ApplicationTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter code;

    private StringFilter description;

    private StringFilter linkSourceRepository;

    private StringFilter linkCmRepository;

    private StringFilter serverlive;

    private StringFilter serveruat;

    private StringFilter document;


    private StringFilter enviroment;

    private StringFilter subComponent;

    private StringFilter epicJira;

    private LocalDateFilter dateStart;

    private ApplicationTypeFilter appType;

    private LocalDateFilter dateGolive;

    private LongFilter topicInId;

    private LongFilter topicOutId;

    private LongFilter apiInId;

    private LongFilter apiOutId;

    private LongFilter apiInfoId;

    private LongFilter topicId;

    private LongFilter operaunitId;

    private LongFilter databaseinfoId;

    private LongFilter humansId;

    private Boolean distinct;

    public ApplicationCriteria() {}

    public ApplicationCriteria(ApplicationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.linkSourceRepository = other.linkSourceRepository == null ? null : other.linkSourceRepository.copy();
        this.linkCmRepository = other.linkCmRepository == null ? null : other.linkCmRepository.copy();
        this.serverlive = other.serverlive == null ? null : other.serverlive.copy();
        this.serveruat = other.serveruat == null ? null : other.serveruat.copy();
        this.document = other.document == null ? null : other.document.copy();
        this.enviroment = other.enviroment == null ? null : other.enviroment.copy();
        this.subComponent = other.subComponent == null ? null : other.subComponent.copy();
        this.epicJira = other.epicJira == null ? null : other.epicJira.copy();
        this.dateStart = other.dateStart == null ? null : other.dateStart.copy();
        this.appType = other.appType == null ? null : other.appType.copy();
        this.dateGolive = other.dateGolive == null ? null : other.dateGolive.copy();
        this.topicInId = other.topicInId == null ? null : other.topicInId.copy();
        this.topicOutId = other.topicOutId == null ? null : other.topicOutId.copy();
        this.apiInId = other.apiInId == null ? null : other.apiInId.copy();
        this.apiOutId = other.apiOutId == null ? null : other.apiOutId.copy();
        this.apiInfoId = other.apiInfoId == null ? null : other.apiInfoId.copy();
        this.topicId = other.topicId == null ? null : other.topicId.copy();
        this.operaunitId = other.operaunitId == null ? null : other.operaunitId.copy();
        this.databaseinfoId = other.databaseinfoId == null ? null : other.databaseinfoId.copy();
        this.humansId = other.humansId == null ? null : other.humansId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ApplicationCriteria copy() {
        return new ApplicationCriteria(this);
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

    public StringFilter getCode() {
        return code;
    }

    public StringFilter code() {
        if (code == null) {
            code = new StringFilter();
        }
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
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

    public StringFilter getLinkSourceRepository() {
        return linkSourceRepository;
    }

    public StringFilter linkSourceRepository() {
        if (linkSourceRepository == null) {
            linkSourceRepository = new StringFilter();
        }
        return linkSourceRepository;
    }

    public void setLinkSourceRepository(StringFilter linkSourceRepository) {
        this.linkSourceRepository = linkSourceRepository;
    }

    public StringFilter getLinkCmRepository() {
        return linkCmRepository;
    }

    public StringFilter linkCmRepository() {
        if (linkCmRepository == null) {
            linkCmRepository = new StringFilter();
        }
        return linkCmRepository;
    }

    public void setLinkCmRepository(StringFilter linkCmRepository) {
        this.linkCmRepository = linkCmRepository;
    }

    public StringFilter getServerlive() {
        return serverlive;
    }

    public StringFilter serverlive() {
        if (serverlive == null) {
            serverlive = new StringFilter();
        }
        return serverlive;
    }

    public void setServerlive(StringFilter serverlive) {
        this.serverlive = serverlive;
    }

    public StringFilter getServeruat() {
        return serveruat;
    }

    public StringFilter serveruat() {
        if (serveruat == null) {
            serveruat = new StringFilter();
        }
        return serveruat;
    }

    public void setServeruat(StringFilter serveruat) {
        this.serveruat = serveruat;
    }


    public StringFilter getDocument() {
        return document;
    }

    public StringFilter document() {
        if (document == null) {
            document = new StringFilter();
        }
        return document;
    }

    public void setdocument(StringFilter document) {
        this.document = document;
    }

    public StringFilter getEnviroment() {
        return enviroment;
    }

    public StringFilter enviroment() {
        if (enviroment == null) {
            enviroment = new StringFilter();
        }
        return enviroment;
    }

    public void setEnviroment(StringFilter enviroment) {
        this.enviroment = enviroment;
    }

    public StringFilter getSubComponent() {
        return subComponent;
    }

    public StringFilter subComponent() {
        if (subComponent == null) {
            subComponent = new StringFilter();
        }
        return subComponent;
    }

    public void setSubComponent(StringFilter subComponent) {
        this.subComponent = subComponent;
    }

    public StringFilter getEpicJira() {
        return epicJira;
    }

    public StringFilter epicJira() {
        if (epicJira == null) {
            epicJira = new StringFilter();
        }
        return epicJira;
    }

    public void setEpicJira(StringFilter epicJira) {
        this.epicJira = epicJira;
    }

    public LocalDateFilter getDateStart() {
        return dateStart;
    }

    public LocalDateFilter dateStart() {
        if (dateStart == null) {
            dateStart = new LocalDateFilter();
        }
        return dateStart;
    }

    public void setDateStart(LocalDateFilter dateStart) {
        this.dateStart = dateStart;
    }

    public ApplicationTypeFilter getAppType() {
        return appType;
    }

    public ApplicationTypeFilter appType() {
        if (appType == null) {
            appType = new ApplicationTypeFilter();
        }
        return appType;
    }

    public void setAppType(ApplicationTypeFilter appType) {
        this.appType = appType;
    }

    public LocalDateFilter getDateGolive() {
        return dateGolive;
    }

    public LocalDateFilter dateGolive() {
        if (dateGolive == null) {
            dateGolive = new LocalDateFilter();
        }
        return dateGolive;
    }

    public void setDateGolive(LocalDateFilter dateGolive) {
        this.dateGolive = dateGolive;
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

    public LongFilter getOperaunitId() {
        return operaunitId;
    }

    public LongFilter operaunitId() {
        if (operaunitId == null) {
            operaunitId = new LongFilter();
        }
        return operaunitId;
    }

    public void setOperaunitId(LongFilter operaunitId) {
        this.operaunitId = operaunitId;
    }

    public LongFilter getDatabaseinfoId() {
        return databaseinfoId;
    }

    public LongFilter databaseinfoId() {
        if (databaseinfoId == null) {
            databaseinfoId = new LongFilter();
        }
        return databaseinfoId;
    }

    public void setDatabaseinfoId(LongFilter databaseinfoId) {
        this.databaseinfoId = databaseinfoId;
    }

    public LongFilter getHumansId() {
        return humansId;
    }

    public LongFilter humansId() {
        if (humansId == null) {
            humansId = new LongFilter();
        }
        return humansId;
    }

    public void setHumansId(LongFilter humansId) {
        this.humansId = humansId;
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
        final ApplicationCriteria that = (ApplicationCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(code, that.code) &&
            Objects.equals(description, that.description) &&
            Objects.equals(linkSourceRepository, that.linkSourceRepository) &&
            Objects.equals(linkCmRepository, that.linkCmRepository) &&
            Objects.equals(serverlive, that.serverlive) &&
            Objects.equals(serveruat, that.serveruat) &&
            Objects.equals(document, that.document) &&
            Objects.equals(enviroment, that.enviroment) &&
            Objects.equals(subComponent, that.subComponent) &&
            Objects.equals(epicJira, that.epicJira) &&
            Objects.equals(dateStart, that.dateStart) &&
            Objects.equals(appType, that.appType) &&
            Objects.equals(dateGolive, that.dateGolive) &&
            Objects.equals(topicInId, that.topicInId) &&
            Objects.equals(topicOutId, that.topicOutId) &&
            Objects.equals(apiInId, that.apiInId) &&
            Objects.equals(apiOutId, that.apiOutId) &&
            Objects.equals(apiInfoId, that.apiInfoId) &&
            Objects.equals(topicId, that.topicId) &&
            Objects.equals(operaunitId, that.operaunitId) &&
            Objects.equals(databaseinfoId, that.databaseinfoId) &&
            Objects.equals(humansId, that.humansId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            code,
            description,
            linkSourceRepository,
            linkCmRepository,
            serverlive,
            serveruat,
            document,
            enviroment,
            subComponent,
            epicJira,
            dateStart,
            appType,
            dateGolive,
            topicInId,
            topicOutId,
            apiInId,
            apiOutId,
            apiInfoId,
            topicId,
            operaunitId,
            databaseinfoId,
            humansId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApplicationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (linkSourceRepository != null ? "linkSourceRepository=" + linkSourceRepository + ", " : "") +
            (linkCmRepository != null ? "linkCmRepository=" + linkCmRepository + ", " : "") +
            (serverlive != null ? "serverlive=" + serverlive + ", " : "") +
            (serveruat != null ? "serveruat=" + serveruat + ", " : "") +
            (document != null ? "document=" + document + ", " : "") +
            (enviroment != null ? "enviroment=" + enviroment + ", " : "") +
            (subComponent != null ? "subComponent=" + subComponent + ", " : "") +
            (epicJira != null ? "epicJira=" + epicJira + ", " : "") +
            (dateStart != null ? "dateStart=" + dateStart + ", " : "") +
            (appType != null ? "appType=" + appType + ", " : "") +
            (dateGolive != null ? "dateGolive=" + dateGolive + ", " : "") +
            (topicInId != null ? "topicInId=" + topicInId + ", " : "") +
            (topicOutId != null ? "topicOutId=" + topicOutId + ", " : "") +
            (apiInId != null ? "apiInId=" + apiInId + ", " : "") +
            (apiOutId != null ? "apiOutId=" + apiOutId + ", " : "") +
            (apiInfoId != null ? "apiInfoId=" + apiInfoId + ", " : "") +
            (topicId != null ? "topicId=" + topicId + ", " : "") +
            (operaunitId != null ? "operaunitId=" + operaunitId + ", " : "") +
            (databaseinfoId != null ? "databaseinfoId=" + databaseinfoId + ", " : "") +
            (humansId != null ? "humansId=" + humansId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
