package vn.com.pvcombank.service.dto;

import java.io.Serializable;
import java.util.Objects;
import vn.com.pvcombank.domain.enumeration.DatabaseType;

/**
 * A DTO for the {@link vn.com.pvcombank.domain.DatabaseInfo} entity.
 */
public class DatabaseInfoDTO implements Serializable {

    private Long id;

    private String name;

    private String serviceName;

    private String userName;

    private String passWord;

    private String ipServer;

    private String port;

    private DatabaseType dataType;

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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIpServer() {
        return ipServer;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public DatabaseType getDataType() {
        return dataType;
    }

    public void setDataType(DatabaseType dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DatabaseInfoDTO)) {
            return false;
        }

        DatabaseInfoDTO databaseInfoDTO = (DatabaseInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, databaseInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DatabaseInfoDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", serviceName='" + getServiceName() + "'" +
            ", userName='" + getUserName() + "'" +
            ", passWord='" + getPassWord() + "'" +
            ", ipServer='" + getIpServer() + "'" +
            ", port='" + getPort() + "'" +
            ", dataType='" + getDataType() + "'" +
            "}";
    }
}
