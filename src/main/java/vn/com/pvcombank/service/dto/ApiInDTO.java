package vn.com.pvcombank.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link vn.com.pvcombank.domain.ApiIn} entity.
 */
public class ApiInDTO implements Serializable {

    private Long id;

    private LocalDate dateConnect;

    private String description;

    private ApiInfoDTO apiInfo;

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
        if (!(o instanceof ApiInDTO)) {
            return false;
        }

        ApiInDTO apiInDTO = (ApiInDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, apiInDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApiInDTO{" +
            "id=" + getId() +
            ", dateConnect='" + getDateConnect() + "'" +
            ", description='" + getDescription() + "'" +
            ", apiInfo=" + getApiInfo() +
            ", application=" + getApplication() +
            "}";
    }
}
