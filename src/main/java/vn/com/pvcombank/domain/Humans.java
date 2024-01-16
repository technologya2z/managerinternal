package vn.com.pvcombank.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import vn.com.pvcombank.domain.enumeration.ApplicationType;
import vn.com.pvcombank.domain.enumeration.HumanType;

/**
 * A Humans.
 */
@Entity
@Table(name = "humans")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Humans extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "code")
    private String code;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private String active;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "home_phome")
    private String homePhome;

    @Column(name = "address")
    private String address;

    @Column(name = "wards")
    private String wards;

    @Column(name = "district")
    private String district;

    @Column(name = "province")
    private String province;

    @Column(name = "join_date")
    private String joinDate;

    @Column(name = "married")
    private String married;

    @Column(name = "gender")
    private String gender;

    @Column(name = "description")
    private String description;

    @Column(name = "jobtitle_name")
    private String jobtitleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "human_type")
    private HumanType humanType;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "created_by", length = 50, updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "last_modified_date", insertable = false)
    @LastModifiedDate
    private Instant lastModifiedDate;

    @Size(max = 50)
    @Column(name = "last_modified_by", length = 50, insertable = false)
    @LastModifiedBy
    private String lastModifiedBy;

    @ManyToMany
    @JoinTable(
        name = "rel_application__human",
        joinColumns = @JoinColumn(name = "human_id"),
        inverseJoinColumns = @JoinColumn(name = "application_id")
    )
    @JsonIgnoreProperties(
        value = { "apiIns", "apiOuts", "apiInfos", "operaunits", "topics", "databaseinfos", "humans" },
        allowSetters = true
    )
    private Set<Application> applications = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_humans__jobtitle",
        joinColumns = @JoinColumn(name = "humans_id"),
        inverseJoinColumns = @JoinColumn(name = "jobtitle_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "applications" }, allowSetters = true)
    private Set<Jobtitle> jobtitles = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "humans", "organization" }, allowSetters = true)
    private Department department;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Humans id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Humans fullName(String fullName) {
        this.setFullName(fullName);
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return this.code;
    }

    public Humans code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return this.userName;
    }

    public Humans userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getactive() {
        return this.active;
    }

    public Humans active(String active) {
        this.setactive(active);
        return this;
    }

    public void setactive(String active) {
        this.active = active;
    }

    public String getEmail() {
        return this.email;
    }

    public Humans email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Humans phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Humans dateOfBirth(String dateOfBirth) {
        this.setDateOfBirth(dateOfBirth);
        return this;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHomePhome() {
        return this.homePhome;
    }

    public Humans homePhome(String homePhome) {
        this.setHomePhome(homePhome);
        return this;
    }

    public void setHomePhome(String homePhome) {
        this.homePhome = homePhome;
    }

    public String getAddress() {
        return this.address;
    }

    public Humans address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWards() {
        return this.wards;
    }

    public Humans wards(String wards) {
        this.setWards(wards);
        return this;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }

    public String getDistrict() {
        return this.district;
    }

    public Humans district(String district) {
        this.setDistrict(district);
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return this.province;
    }

    public Humans province(String province) {
        this.setProvince(province);
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getJoinDate() {
        return this.joinDate;
    }

    public Humans joinDate(String joinDate) {
        this.setJoinDate(joinDate);
        return this;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getMarried() {
        return this.married;
    }

    public Humans married(String married) {
        this.setMarried(married);
        return this;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getGender() {
        return this.gender;
    }

    public Humans gender(String gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return this.description;
    }

    public Humans description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobtitleName() {
        return this.jobtitleName;
    }

    public Humans jobtitleName(String jobtitleName) {
        this.setJobtitleName(jobtitleName);
        return this;
    }

    public void setJobtitleName(String jobtitleName) {
        this.jobtitleName = jobtitleName;
    }

    public Set<Application> getApplications() {
        return this.applications;
    }

    public void setApplications(Set<Application> applications) {
        if (this.applications != null) {
            this.applications.forEach(i -> i.removeHuman(this));
        }
        if (applications != null) {
            applications.forEach(i -> i.addHuman(this));
        }
        this.applications = applications;
    }

    public Humans applications(Set<Application> applications) {
        this.setApplications(applications);
        return this;
    }

    public Humans addApplication(Application application) {
        this.applications.add(application);
        application.getHumans().add(this);
        return this;
    }

    public Humans removeApplication(Application application) {
        this.applications.remove(application);
        application.getHumans().remove(this);
        return this;
    }

    public Set<Jobtitle> getJobtitles() {
        return this.jobtitles;
    }

    public void setJobtitles(Set<Jobtitle> jobtitles) {
        this.jobtitles = jobtitles;
    }

    public Humans jobtitles(Set<Jobtitle> jobtitles) {
        this.setJobtitles(jobtitles);
        return this;
    }

    public Humans addJobtitle(Jobtitle jobtitle) {
        this.jobtitles.add(jobtitle);
        jobtitle.getApplications().add(this);
        return this;
    }

    public Humans removeJobtitle(Jobtitle jobtitle) {
        this.jobtitles.remove(jobtitle);
        jobtitle.getApplications().remove(this);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Humans department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public HumanType getHumanType() {
        return humanType;
    }

    public void setHumanType(HumanType humanType) {
        this.humanType = humanType;
    }

    @Override
    public Instant getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Humans)) {
            return false;
        }
        return id != null && id.equals(((Humans) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
