package vn.com.pvcombank.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.com.pvcombank.domain.TopicIn} entity.
 */
public class TopicInDTO implements Serializable {

    private Long id;

    private LocalDate dateConnect;

    private String description;

    private TopicDTO topic;

    private ApplicationDTO application;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateConnect() {
        return dateConnect;
    }

    public void setDateConnect(LocalDate dateConnect) {
        this.dateConnect = dateConnect;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof TopicInDTO)) {
            return false;
        }

        TopicInDTO topicInDTO = (TopicInDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, topicInDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TopicInDTO{" +
            "id=" + getId() +
            ", dateConnect='" + getDateConnect() + "'" +
            ", description='" + getDescription() + "'" +
            ", topic=" + getTopic() +
            ", application=" + getApplication() +
            "}";
    }
}
