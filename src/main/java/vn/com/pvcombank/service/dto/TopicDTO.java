package vn.com.pvcombank.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link vn.com.pvcombank.domain.Topic} entity.
 */
public class TopicDTO implements Serializable {

    private Long id;

    private String name;

    private String bootrapServer;

    private String locationTopic;

    private String rootproducer;

    private String rootTable;

    private String message;

    private String linkDoc;

    private String enviroment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBootrapServer() {
        return bootrapServer;
    }

    public void setBootrapServer(String bootrapServer) {
        this.bootrapServer = bootrapServer;
    }

    public String getLocationTopic() {
        return locationTopic;
    }

    public void setLocationTopic(String locationTopic) {
        this.locationTopic = locationTopic;
    }

    public String getRootproducer() {
        return rootproducer;
    }

    public void setRootproducer(String rootproducer) {
        this.rootproducer = rootproducer;
    }

    public String getRootTable() {
        return rootTable;
    }

    public void setRootTable(String rootTable) {
        this.rootTable = rootTable;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLinkDoc() {
        return linkDoc;
    }

    public void setLinkDoc(String linkDoc) {
        this.linkDoc = linkDoc;
    }

    public String getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TopicDTO)) {
            return false;
        }

        TopicDTO topicDTO = (TopicDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, topicDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "TopicDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", bootrapServer='" + bootrapServer + '\'' +
            ", locationTopic='" + locationTopic + '\'' +
            ", rootproducer='" + rootproducer + '\'' +
            ", rootTable='" + rootTable + '\'' +
            ", message='" + message + '\'' +
            ", linkDoc='" + linkDoc + '\'' +
            ", enviroment='" + enviroment + '\'' +
            '}';
    }
}
