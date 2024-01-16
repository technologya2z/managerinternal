package vn.com.pvcombank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Topic.
 */
@Entity
@Table(name = "topic")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Topic extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bootrap_server")
    private String bootrapServer;

    @Column(name = "location_topic")
    private String locationTopic;

    @Column(name = "rootproducer")
    private String rootproducer;

    @Column(name = "root_table")
    private String rootTable;

    @Column(name = "message")
    private String message;

    @Column(name = "link_doc")
    private String linkDoc;

    @Column(name = "enviroment")
    private String enviroment;

    @OneToMany(mappedBy = "topic")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "topic", "application" }, allowSetters = true)
    private Set<TopicIn> topicIns = new HashSet<>();

    @OneToMany(mappedBy = "topic")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "topic", "application" }, allowSetters = true)
    private Set<TopicOut> topicOuts = new HashSet<>();

    @ManyToMany(mappedBy = "topics")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "topicIns", "topicOuts", "apiIns", "apiOuts", "apiInfos", "topics", "operaunits", "databaseinfos", "humans" },
        allowSetters = true
    )
    private Set<Application> applications = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Topic id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Topic name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBootrapServer() {
        return this.bootrapServer;
    }

    public Topic bootrapServer(String bootrapServer) {
        this.setBootrapServer(bootrapServer);
        return this;
    }

    public void setBootrapServer(String bootrapServer) {
        this.bootrapServer = bootrapServer;
    }

    public String getLocationTopic() {
        return this.locationTopic;
    }

    public Topic locationTopic(String locationTopic) {
        this.setLocationTopic(locationTopic);
        return this;
    }

    public void setLocationTopic(String locationTopic) {
        this.locationTopic = locationTopic;
    }

    public String getRootproducer() {
        return this.rootproducer;
    }

    public Topic rootproducer(String rootproducer) {
        this.setRootproducer(rootproducer);
        return this;
    }

    public void setRootproducer(String rootproducer) {
        this.rootproducer = rootproducer;
    }

    public String getRootTable() {
        return this.rootTable;
    }

    public Topic rootTable(String rootTable) {
        this.setRootTable(rootTable);
        return this;
    }

    public void setRootTable(String rootTable) {
        this.rootTable = rootTable;
    }

    public String getMessage() {
        return this.message;
    }

    public Topic message(String message) {
        this.setMessage(message);
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLinkDoc() {
        return this.linkDoc;
    }

    public Topic linkDoc(String linkDoc) {
        this.setLinkDoc(linkDoc);
        return this;
    }

    public void setLinkDoc(String linkDoc) {
        this.linkDoc = linkDoc;
    }

    public Set<TopicIn> getTopicIns() {
        return this.topicIns;
    }

    public void setTopicIns(Set<TopicIn> topicIns) {
        if (this.topicIns != null) {
            this.topicIns.forEach(i -> i.setTopic(null));
        }
        if (topicIns != null) {
            topicIns.forEach(i -> i.setTopic(this));
        }
        this.topicIns = topicIns;
    }

    public Topic topicIns(Set<TopicIn> topicIns) {
        this.setTopicIns(topicIns);
        return this;
    }

    public Topic addTopicIn(TopicIn topicIn) {
        this.topicIns.add(topicIn);
        topicIn.setTopic(this);
        return this;
    }

    public Topic removeTopicIn(TopicIn topicIn) {
        this.topicIns.remove(topicIn);
        topicIn.setTopic(null);
        return this;
    }

    public Set<TopicOut> getTopicOuts() {
        return this.topicOuts;
    }

    public void setTopicOuts(Set<TopicOut> topicOuts) {
        if (this.topicOuts != null) {
            this.topicOuts.forEach(i -> i.setTopic(null));
        }
        if (topicOuts != null) {
            topicOuts.forEach(i -> i.setTopic(this));
        }
        this.topicOuts = topicOuts;
    }

    public Topic topicOuts(Set<TopicOut> topicOuts) {
        this.setTopicOuts(topicOuts);
        return this;
    }

    public Topic addTopicOut(TopicOut topicOut) {
        this.topicOuts.add(topicOut);
        topicOut.setTopic(this);
        return this;
    }

    public Topic removeTopicOut(TopicOut topicOut) {
        this.topicOuts.remove(topicOut);
        topicOut.setTopic(null);
        return this;
    }

    public Set<Application> getApplications() {
        return this.applications;
    }

    public void setApplications(Set<Application> applications) {
        if (this.applications != null) {
            this.applications.forEach(i -> i.removeTopic(this));
        }
        if (applications != null) {
            applications.forEach(i -> i.addTopic(this));
        }
        this.applications = applications;
    }

    public Topic applications(Set<Application> applications) {
        this.setApplications(applications);
        return this;
    }

    public Topic addApplication(Application application) {
        this.applications.add(application);
        application.getTopics().add(this);
        return this;
    }

    public Topic removeApplication(Application application) {
        this.applications.remove(application);
        application.getTopics().remove(this);
        return this;
    }

    public String getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Topic)) {
            return false;
        }
        return id != null && id.equals(((Topic) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
    // prettier-ignore
}
