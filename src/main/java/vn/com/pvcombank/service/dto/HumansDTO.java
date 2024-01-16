package vn.com.pvcombank.service.dto;

import vn.com.pvcombank.domain.enumeration.ApplicationType;
import vn.com.pvcombank.domain.enumeration.HumanType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link vn.com.pvcombank.domain.Humans} entity.
 */
public class HumansDTO implements Serializable {

    private Long id;

    private String fullName;

    private String code;

    private String userName;

    private String email;

    private String active;

    private String phoneNumber;

    private String dateOfBirth;

    private String homePhome;

    private String address;

    private String wards;

    private String district;

    private String province;

    private String joinDate;

    private String married;

    private String gender;

    private String description;

    private String jobtitleName;

    private HumanType humanType;

    private Set<JobtitleDTO> jobtitles = new HashSet<>();

    private Set<ApplicationDTO> applications = new HashSet<>();

    private DepartmentDTO department;

    public HumansDTO() {
    }

    public HumansDTO(Long id, String fullName, String code, String userName, String email, String active, String phoneNumber, String dateOfBirth, String homePhome, String address, String wards, String district, String province, String joinDate, String married, String gender, String description, String jobtitleName, HumanType humanType, Set<JobtitleDTO> jobtitles, Set<ApplicationDTO> applications, DepartmentDTO department) {
        this.id = id;
        this.fullName = fullName;
        this.code = code;
        this.userName = userName;
        this.email = email;
        this.active = active;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.homePhome = homePhome;
        this.address = address;
        this.wards = wards;
        this.district = district;
        this.province = province;
        this.joinDate = joinDate;
        this.married = married;
        this.gender = gender;
        this.description = description;
        this.jobtitleName = jobtitleName;
        this.humanType = humanType;
        this.jobtitles = jobtitles;
        this.applications = applications;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomePhome() {
        return homePhome;
    }

    public void setHomePhome(String homePhome) {
        this.homePhome = homePhome;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobtitleName() {
        return jobtitleName;
    }

    public void setJobtitleName(String jobtitleName) {
        this.jobtitleName = jobtitleName;
    }

    public Set<JobtitleDTO> getJobtitles() {
        return jobtitles;
    }

    public void setJobtitles(Set<JobtitleDTO> jobtitles) {
        this.jobtitles = jobtitles;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public Set<ApplicationDTO> getApplications() {
        return applications;
    }

    public void setApplications(Set<ApplicationDTO> applications) {
        this.applications = applications;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public HumanType getHumanType() {
        return humanType;
    }

    public void setHumanType(HumanType humanType) {
        this.humanType = humanType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HumansDTO)) {
            return false;
        }

        HumansDTO humansDTO = (HumansDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, humansDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HumansDTO{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", code='" + getCode() + "'" +
            ", userName='" + getUserName() + "'" +
            ", active='" + getActive() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", homePhome='" + getHomePhome() + "'" +
            ", address='" + getAddress() + "'" +
            ", wards='" + getWards() + "'" +
            ", district='" + getDistrict() + "'" +
            ", province='" + getProvince() + "'" +
            ", joinDate='" + getJoinDate() + "'" +
            ", married='" + getMarried() + "'" +
            ", gender='" + getGender() + "'" +
            ", description='" + getDescription() + "'" +
            ", jobtitleName='" + getJobtitleName() + "'" +
            ", jobtitles=" + getJobtitles() +
            ", department=" + getDepartment() +
            "}";
    }
}
