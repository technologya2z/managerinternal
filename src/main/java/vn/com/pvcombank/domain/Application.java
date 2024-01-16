package vn.com.pvcombank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
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
import vn.com.pvcombank.domain.enumeration.ApplicationType;

/**
 * A Application.
 */
@Entity
@Table(name = "application")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Application extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "link_source_repository")
    private String linkSourceRepository;

    @Column(name = "link_cm_repository")
    private String linkCmRepository;

    @Column(name = "serverlive")
    private String serverlive;

    @Column(name = "serveruat")
    private String serveruat;

    @Column(name = "enviroment")
    private String enviroment;

    @Column(name = "sub_component")
    private String subComponent;

    @Column(name = "epic_jira")
    private String epicJira;

    @Column(name = "date_start")
    private LocalDate dateStart;

    @Enumerated(EnumType.STRING)
    @Column(name = "app_type")
    private ApplicationType appType;

    @Column(name = "date_golive")
    private LocalDate dateGolive;

    @Column(name = "link_live")
    private String linkLive;

    @Column(name = "link_uat")
    private String linkUat;

    @Column(name = "document")
    private String document;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private Instant createdDate;

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

    @OneToMany(mappedBy = "application",fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "topic", "application" }, allowSetters = true)
    private Set<TopicIn> topicIns = new HashSet<>();

    @OneToMany(mappedBy = "application",fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "topic", "application" }, allowSetters = true)
    private Set<TopicOut> topicOuts = new HashSet<>();

    @OneToMany(mappedBy = "application",fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "apiInfo", "application" }, allowSetters = true)
    private Set<ApiIn> apiIns = new HashSet<>();

    @OneToMany(mappedBy = "application",fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "apiInfo", "application" }, allowSetters = true)
    private Set<ApiOut> apiOuts = new HashSet<>();

    @OneToMany(mappedBy = "application",fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "apiIns", "apiOuts", "application" }, allowSetters = true)
    private Set<ApiInfo> apiInfos = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_application__topic",
        joinColumns = @JoinColumn(name = "application_id"),
        inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "topicIns", "topicOuts", "applications" }, allowSetters = true)
    private Set<Topic> topics = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_application__operaunit",
        joinColumns = @JoinColumn(name = "application_id"),
        inverseJoinColumns = @JoinColumn(name = "operaunit_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "applications" }, allowSetters = true)
    private Set<OperaUnit> operaunits = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_application__databaseinfo",
        joinColumns = @JoinColumn(name = "application_id"),
        inverseJoinColumns = @JoinColumn(name = "databaseinfo_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "applications" }, allowSetters = true)
    private Set<DatabaseInfo> databaseinfos = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "rel_application__human",
        joinColumns = @JoinColumn(name = "application_id"),
        inverseJoinColumns = @JoinColumn(name = "human_id")
    )
    @JsonIgnoreProperties(value = { "applications", "jobtitles", "department" }, allowSetters = true)
    private Set<Humans> humans = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Application id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Application name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public Application code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public Application description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkSourceRepository() {
        return this.linkSourceRepository;
    }

    public Application linkSourceRepository(String linkSourceRepository) {
        this.setLinkSourceRepository(linkSourceRepository);
        return this;
    }

    public void setLinkSourceRepository(String linkSourceRepository) {
        this.linkSourceRepository = linkSourceRepository;
    }

    public String getLinkCmRepository() {
        return this.linkCmRepository;
    }

    public Application linkCmRepository(String linkCmRepository) {
        this.setLinkCmRepository(linkCmRepository);
        return this;
    }

    public void setLinkCmRepository(String linkCmRepository) {
        this.linkCmRepository = linkCmRepository;
    }

    public String getServerlive() {
        return this.serverlive;
    }

    public Application serverlive(String serverlive) {
        this.setServerlive(serverlive);
        return this;
    }

    public void setServerlive(String serverlive) {
        this.serverlive = serverlive;
    }

    public String getServeruat() {
        return this.serveruat;
    }

    public Application serveruat(String serveruat) {
        this.setServeruat(serveruat);
        return this;
    }

    public void setServeruat(String serveruat) {
        this.serveruat = serveruat;
    }

    public String getdocument() {
        return this.document;
    }

    public Application document(String document) {
        this.document(document);
        return this;
    }

    public void setdocument(String document) {
        this.document = document;
    }

    public String getEnviroment() {
        return this.enviroment;
    }

    public Application enviroment(String enviroment) {
        this.setEnviroment(enviroment);
        return this;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }

    public String getSubComponent() {
        return this.subComponent;
    }

    public Application subComponent(String subComponent) {
        this.setSubComponent(subComponent);
        return this;
    }

    public void setSubComponent(String subComponent) {
        this.subComponent = subComponent;
    }

    public String getEpicJira() {
        return this.epicJira;
    }

    public Application epicJira(String epicJira) {
        this.setEpicJira(epicJira);
        return this;
    }

    public void setEpicJira(String epicJira) {
        this.epicJira = epicJira;
    }

    public LocalDate getDateStart() {
        return this.dateStart;
    }

    public Application dateStart(LocalDate dateStart) {
        this.setDateStart(dateStart);
        return this;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public ApplicationType getAppType() {
        return this.appType;
    }

    public Application appType(ApplicationType appType) {
        this.setAppType(appType);
        return this;
    }

    public void setAppType(ApplicationType appType) {
        this.appType = appType;
    }

    public LocalDate getDateGolive() {
        return this.dateGolive;
    }

    public Application dateGolive(LocalDate dateGolive) {
        this.setDateGolive(dateGolive);
        return this;
    }

    public void setDateGolive(LocalDate dateGolive) {
        this.dateGolive = dateGolive;
    }

    public Set<TopicIn> getTopicIns() {
        return this.topicIns;
    }

    public void setTopicIns(Set<TopicIn> topicIns) {
        if (this.topicIns != null) {
            this.topicIns.forEach(i -> i.setApplication(null));
        }
        if (topicIns != null) {
            topicIns.forEach(i -> i.setApplication(this));
        }
        this.topicIns = topicIns;
    }

    public Application topicIns(Set<TopicIn> topicIns) {
        this.setTopicIns(topicIns);
        return this;
    }

    public Application addTopicIn(TopicIn topicIn) {
        this.topicIns.add(topicIn);
        topicIn.setApplication(this);
        return this;
    }

    public Application removeTopicIn(TopicIn topicIn) {
        this.topicIns.remove(topicIn);
        topicIn.setApplication(null);
        return this;
    }

    public Set<TopicOut> getTopicOuts() {
        return this.topicOuts;
    }

    public void setTopicOuts(Set<TopicOut> topicOuts) {
        if (this.topicOuts != null) {
            this.topicOuts.forEach(i -> i.setApplication(null));
        }
        if (topicOuts != null) {
            topicOuts.forEach(i -> i.setApplication(this));
        }
        this.topicOuts = topicOuts;
    }

    public Application topicOuts(Set<TopicOut> topicOuts) {
        this.setTopicOuts(topicOuts);
        return this;
    }

    public Application addTopicOut(TopicOut topicOut) {
        this.topicOuts.add(topicOut);
        topicOut.setApplication(this);
        return this;
    }

    public Application removeTopicOut(TopicOut topicOut) {
        this.topicOuts.remove(topicOut);
        topicOut.setApplication(null);
        return this;
    }

    public Set<ApiIn> getApiIns() {
        return this.apiIns;
    }

    public void setApiIns(Set<ApiIn> apiIns) {
        if (this.apiIns != null) {
            this.apiIns.forEach(i -> i.setApplication(null));
        }
        if (apiIns != null) {
            apiIns.forEach(i -> i.setApplication(this));
        }
        this.apiIns = apiIns;
    }

    public Application apiIns(Set<ApiIn> apiIns) {
        this.setApiIns(apiIns);
        return this;
    }

    public Application addApiIn(ApiIn apiIn) {
        this.apiIns.add(apiIn);
        apiIn.setApplication(this);
        return this;
    }

    public Application removeApiIn(ApiIn apiIn) {
        this.apiIns.remove(apiIn);
        apiIn.setApplication(null);
        return this;
    }

    public Set<ApiOut> getApiOuts() {
        return this.apiOuts;
    }

    public void setApiOuts(Set<ApiOut> apiOuts) {
        if (this.apiOuts != null) {
            this.apiOuts.forEach(i -> i.setApplication(null));
        }
        if (apiOuts != null) {
            apiOuts.forEach(i -> i.setApplication(this));
        }
        this.apiOuts = apiOuts;
    }

    public Application apiOuts(Set<ApiOut> apiOuts) {
        this.setApiOuts(apiOuts);
        return this;
    }

    public Application addApiOut(ApiOut apiOut) {
        this.apiOuts.add(apiOut);
        apiOut.setApplication(this);
        return this;
    }

    public Application removeApiOut(ApiOut apiOut) {
        this.apiOuts.remove(apiOut);
        apiOut.setApplication(null);
        return this;
    }

    public Set<ApiInfo> getApiInfos() {
        return this.apiInfos;
    }

    public void setApiInfos(Set<ApiInfo> apiInfos) {
        if (this.apiInfos != null) {
            this.apiInfos.forEach(i -> i.setApplication(null));
        }
        if (apiInfos != null) {
            apiInfos.forEach(i -> i.setApplication(this));
        }
        this.apiInfos = apiInfos;
    }

    public Application apiInfos(Set<ApiInfo> apiInfos) {
        this.setApiInfos(apiInfos);
        return this;
    }

    public Application addApiInfo(ApiInfo apiInfo) {
        this.apiInfos.add(apiInfo);
        apiInfo.setApplication(this);
        return this;
    }

    public Application removeApiInfo(ApiInfo apiInfo) {
        this.apiInfos.remove(apiInfo);
        apiInfo.setApplication(null);
        return this;
    }

    public Set<Topic> getTopics() {
        return this.topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Application topics(Set<Topic> topics) {
        this.setTopics(topics);
        return this;
    }

    public Application addTopic(Topic topic) {
        this.topics.add(topic);
        topic.getApplications().add(this);
        return this;
    }

    public Application removeTopic(Topic topic) {
        this.topics.remove(topic);
        topic.getApplications().remove(this);
        return this;
    }

    public Set<OperaUnit> getOperaunits() {
        return this.operaunits;
    }

    public void setOperaunits(Set<OperaUnit> operaUnits) {
        this.operaunits = operaUnits;
    }

    public Application operaunits(Set<OperaUnit> operaUnits) {
        this.setOperaunits(operaUnits);
        return this;
    }

    public Application addOperaunit(OperaUnit operaUnit) {
        this.operaunits.add(operaUnit);
        operaUnit.getApplications().add(this);
        return this;
    }

    public Application removeOperaunit(OperaUnit operaUnit) {
        this.operaunits.remove(operaUnit);
        operaUnit.getApplications().remove(this);
        return this;
    }

    public Set<DatabaseInfo> getDatabaseinfos() {
        return this.databaseinfos;
    }

    public void setDatabaseinfos(Set<DatabaseInfo> databaseInfos) {
        this.databaseinfos = databaseInfos;
    }

    public Application databaseinfos(Set<DatabaseInfo> databaseInfos) {
        this.setDatabaseinfos(databaseInfos);
        return this;
    }

    public Application addDatabaseinfo(DatabaseInfo databaseInfo) {
        this.databaseinfos.add(databaseInfo);
        databaseInfo.getApplications().add(this);
        return this;
    }

    public Application removeDatabaseinfo(DatabaseInfo databaseInfo) {
        this.databaseinfos.remove(databaseInfo);
        databaseInfo.getApplications().remove(this);
        return this;
    }

    public Set<Humans> getHumans() {
        return humans;
    }

    public void setHumans(Set<Humans> humans) {
        this.humans = humans;
    }

    public Application humans(Set<Humans> humans) {
        this.setHumans(humans);
        return this;
    }

    public Application addHuman(Humans humans) {
        this.humans.add(humans);
        humans.getApplications().add(this);
        return this;
    }

    public Application removeHuman(Humans humans) {
        this.humans.remove(humans);
        humans.getApplications().remove(this);
        return this;
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

    public String getLinkLive() {
        return linkLive;
    }

    public void setLinkLive(String linkLive) {
        this.linkLive = linkLive;
    }

    public String getLinkUat() {
        return linkUat;
    }

    public void setLinkUat(String linkUat) {
        this.linkUat = linkUat;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Application)) {
            return false;
        }
        return id != null && id.equals(((Application) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
    // prettier-ignore
}
