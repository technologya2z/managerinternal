package vn.com.pvcombank.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import vn.com.pvcombank.domain.enumeration.ApplicationType;

/**
 * A DTO for the {@link vn.com.pvcombank.domain.Application} entity.
 */
public class ApplicationDTO implements Serializable {

    private Long id;

    private String name;

    private String code;

    private String description;

    private String linkSourceRepository;

    private String linkCmRepository;

    private String serverlive;

    private String serveruat;

    private String linkLive;

    private String linkUat;

    private String enviroment;

    private String subComponent;

    private String epicJira;

    private String swaggerLink;

    private LocalDate dateStart;

    private ApplicationType appType;

    private LocalDate dateGolive;

    private Set<TopicDTO> topics = new HashSet<>();

    private Set<OperaUnitDTO> operaunits = new HashSet<>();

    private Set<DatabaseInfoDTO> databaseinfos = new HashSet<>();

    private Set<HumansDTO> humans = new HashSet<>();

    public ApplicationDTO() {
    }

    public ApplicationDTO(Long id, String name, String code, String description, String linkSourceRepository, String linkCmRepository, String serverlive, String serveruat, String linkLive, String linkUat, String enviroment, String subComponent, String epicJira, String swaggerLink, LocalDate dateStart, ApplicationType appType, LocalDate dateGolive, Set<TopicDTO> topics, Set<OperaUnitDTO> operaunits, Set<DatabaseInfoDTO> databaseinfos, Set<HumansDTO> humans) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.linkSourceRepository = linkSourceRepository;
        this.linkCmRepository = linkCmRepository;
        this.serverlive = serverlive;
        this.serveruat = serveruat;
        this.linkLive = linkLive;
        this.linkUat = linkUat;
        this.enviroment = enviroment;
        this.subComponent = subComponent;
        this.epicJira = epicJira;
        this.swaggerLink = swaggerLink;
        this.dateStart = dateStart;
        this.appType = appType;
        this.dateGolive = dateGolive;
        this.topics = topics;
        this.operaunits = operaunits;
        this.databaseinfos = databaseinfos;
        this.humans = humans;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkSourceRepository() {
        return linkSourceRepository;
    }

    public void setLinkSourceRepository(String linkSourceRepository) {
        this.linkSourceRepository = linkSourceRepository;
    }

    public String getLinkCmRepository() {
        return linkCmRepository;
    }

    public void setLinkCmRepository(String linkCmRepository) {
        this.linkCmRepository = linkCmRepository;
    }

    public String getServerlive() {
        return serverlive;
    }

    public void setServerlive(String serverlive) {
        this.serverlive = serverlive;
    }

    public String getServeruat() {
        return serveruat;
    }

    public void setServeruat(String serveruat) {
        this.serveruat = serveruat;
    }

    public String getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }

    public String getSubComponent() {
        return subComponent;
    }

    public void setSubComponent(String subComponent) {
        this.subComponent = subComponent;
    }

    public String getEpicJira() {
        return epicJira;
    }

    public void setEpicJira(String epicJira) {
        this.epicJira = epicJira;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public ApplicationType getAppType() {
        return appType;
    }

    public void setAppType(ApplicationType appType) {
        this.appType = appType;
    }

    public LocalDate getDateGolive() {
        return dateGolive;
    }

    public void setDateGolive(LocalDate dateGolive) {
        this.dateGolive = dateGolive;
    }

    public Set<TopicDTO> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicDTO> topics) {
        this.topics = topics;
    }

    public Set<OperaUnitDTO> getOperaunits() {
        return operaunits;
    }

    public void setOperaunits(Set<OperaUnitDTO> operaunits) {
        this.operaunits = operaunits;
    }

    public Set<DatabaseInfoDTO> getDatabaseinfos() {
        return databaseinfos;
    }

    public void setDatabaseinfos(Set<DatabaseInfoDTO> databaseinfos) {
        this.databaseinfos = databaseinfos;
    }

    public Set<HumansDTO> getHumans() {
        return humans;
    }

    public void setHumans(Set<HumansDTO> humans) {
        this.humans = humans;
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

    public String getSwaggerLink() {
        return swaggerLink;
    }

    public void setSwaggerLink(String swaggerLink) {
        this.swaggerLink = swaggerLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApplicationDTO)) {
            return false;
        }

        ApplicationDTO applicationDTO = (ApplicationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, applicationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore

    @Override
    public String toString() {
        return "ApplicationDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", code='" + code + '\'' +
            ", description='" + description + '\'' +
            ", linkSourceRepository='" + linkSourceRepository + '\'' +
            ", linkCmRepository='" + linkCmRepository + '\'' +
            ", serverlive='" + serverlive + '\'' +
            ", serveruat='" + serveruat + '\'' +
            ", linkLive='" + linkLive + '\'' +
            ", linkUat='" + linkUat + '\'' +
            ", enviroment='" + enviroment + '\'' +
            ", subComponent='" + subComponent + '\'' +
            ", epicJira='" + epicJira + '\'' +
            ", dateStart=" + dateStart +
            ", appType=" + appType +
            ", dateGolive=" + dateGolive +
            ", topics=" + topics +
            ", operaunits=" + operaunits +
            ", databaseinfos=" + databaseinfos +
            ", humans=" + humans +
            '}';
    }
}
