package vn.com.pvcombank.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.com.pvcombank.domain.ApiOut} entity.
 */
public class ApiOutDTO implements Serializable {

    private Long id;

    private String description;

    private LocalDate dateConnect;

    private ApiInfoDTO apiInfo;

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

    public ApiInfoDTO getApiInfo() {
        return apiInfo;
    }

    public void setApiInfo(ApiInfoDTO apiInfo) {
        this.apiInfo = apiInfo;
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
        if (!(o instanceof ApiOutDTO)) {
            return false;
        }

        ApiOutDTO apiOutDTO = (ApiOutDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, apiOutDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiOutDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", dateConnect='" + getDateConnect() + "'" +
            ", apiInfo=" + getApiInfo() +
            ", application=" + getApplication() +
            "}";
    }
}
