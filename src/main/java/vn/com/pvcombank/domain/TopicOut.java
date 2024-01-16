package vn.com.pvcombank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TopicOut.
 */
@Entity
@Table(name = "topic_out")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TopicOut implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "date_connect")
    private LocalDate dateConnect;

    @ManyToOne
    @JsonIgnoreProperties(value = { "topicIns", "topicOuts", "applications" }, allowSetters = true)
    private Topic topic;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "topicIns", "topicOuts", "apiIns", "apiOuts", "apiInfos", "topics", "operaunits", "databaseinfos", "humans" },
        allowSetters = true
    )
    private Application application;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TopicOut id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public TopicOut description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateConnect() {
        return this.dateConnect;
    }

    public TopicOut dateConnect(LocalDate dateConnect) {
        this.setDateConnect(dateConnect);
        return this;
    }

    public void setDateConnect(LocalDate dateConnect) {
        this.dateConnect = dateConnect;
    }

    public Topic getTopic() {
        return this.topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public TopicOut topic(Topic topic) {
        this.setTopic(topic);
        return this;
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public TopicOut application(Application application) {
        this.setApplication(application);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TopicOut)) {
            return false;
        }
        return id != null && id.equals(((TopicOut) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
