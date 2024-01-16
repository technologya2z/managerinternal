package vn.com.pvcombank.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.com.pvcombank.domain.TopicOut} entity.
 */
public class TopicOutDTO implements Serializable {

    private Long id;

    private String description;

    private LocalDate dateConnect;

    private TopicDTO topic;

    private ApplicationDTO application;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateConnect() {
        return dateConnect;
    }

    public void setDateConnect(LocalDate dateConnect) {
        this.dateConnect = dateConnect;
    }

    public TopicDTO getTopic() {
        return topic;
    }

    public void setTopic(TopicDTO topic) {
        this.topic = topic;
    }

    public ApplicationDTO getApplication() {
        return application;
    }

    public void setApplication(ApplicationDTO application) {
        this.application = application;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TopicOutDTO)) {
            return false;
        }

        TopicOutDTO topicOutDTO = (TopicOutDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, topicOutDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TopicOutDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", dateConnect='" + getDateConnect() + "'" +
            ", topic=" + getTopic() +
            ", application=" + getApplication() +
            "}";
    }
}
