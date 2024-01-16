package vn.com.pvcombank.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import vn.com.pvcombank.domain.enumeration.ApplicationType;
import vn.com.pvcombank.domain.enumeration.HumanType;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.Humans} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.HumansResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /humans?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class HumansCriteria implements Serializable, Criteria {

    public static class HumanTypeFilter extends Filter<HumanTypeFilter> {

        public HumanTypeFilter() {}

        public HumanTypeFilter(HumansCriteria.HumanTypeFilter filter) {
            super(filter);
        }

        @Override
        public HumansCriteria.HumanTypeFilter copy() {
            return new HumanTypeFilter(this);
        }
    }


    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter fullName;

    private StringFilter code;

    private StringFilter userName;

    private StringFilter active;

    private StringFilter email;

    private StringFilter phoneNumber;

    private StringFilter dateOfBirth;

    private StringFilter homePhome;

    private StringFilter address;


    private StringFilter wards;

    private StringFilter district;

    private StringFilter province;

    private StringFilter joinDate;

    private StringFilter married;

    private StringFilter gender;

    private StringFilter description;

    private StringFilter jobtitleName;

    private LongFilter applicationId;

    private LongFilter jobtitleId;

    private LongFilter departmentId;

    private HumanTypeFilter humanTypeFilter;

    private Boolean distinct;

    public HumansCriteria() {}

    public HumansCriteria(HumansCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.fullName = other.fullName == null ? null : other.fullName.copy();
        this.code = other.code == null ? null : other.code.copy();
        this.userName = other.userName == null ? null : other.userName.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.phoneNumber = other.phoneNumber == null ? null : other.phoneNumber.copy();
        this.dateOfBirth = other.dateOfBirth == null ? null : other.dateOfBirth.copy();
        this.homePhome = other.homePhome == null ? null : other.homePhome.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.wards = other.wards == null ? null : other.wards.copy();
        this.district = other.district == null ? null : other.district.copy();
        this.province = other.province == null ? null : other.province.copy();
        this.joinDate = other.joinDate == null ? null : other.joinDate.copy();
        this.married = other.married == null ? null : other.married.copy();
        this.gender = other.gender == null ? null : other.gender.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.jobtitleName = other.jobtitleName == null ? null : other.jobtitleName.copy();
        this.applicationId = other.applicationId == null ? null : other.applicationId.copy();
        this.jobtitleId = other.jobtitleId == null ? null : other.jobtitleId.copy();
        this.departmentId = other.departmentId == null ? null : other.departmentId.copy();
        this.active = other.active == null ? null : other.active.copy();
        this.humanTypeFilter = other.humanTypeFilter == null ? null : other.humanTypeFilter.copy();
        this.distinct = other.distinct;
    }

    @Override
    public HumansCriteria copy() {
        return new HumansCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFullName() {
        return fullName;
    }

    public StringFilter fullName() {
        if (fullName == null) {
            fullName = new StringFilter();
        }
        return fullName;
    }

    public void setFullName(StringFilter fullName) {
        this.fullName = fullName;
    }

    public StringFilter getCode() {
        return code;
    }

    public StringFilter code() {
        if (code == null) {
            code = new StringFilter();
        }
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getUserName() {
        return userName;
    }

    public StringFilter userName() {
        if (userName == null) {
            userName = new StringFilter();
        }
        return userName;
    }

    public void setUserName(StringFilter userName) {
        this.userName = userName;
    }

    public StringFilter getactive() {
        return active;
    }

    public StringFilter active() {
        if (active == null) {
            active = new StringFilter();
        }
        return active;
    }

    public void setactive(StringFilter active) {
        this.active = active;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getPhoneNumber() {
        return phoneNumber;
    }

    public StringFilter phoneNumber() {
        if (phoneNumber == null) {
            phoneNumber = new StringFilter();
        }
        return phoneNumber;
    }

    public void setPhoneNumber(StringFilter phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public StringFilter getDateOfBirth() {
        return dateOfBirth;
    }

    public StringFilter dateOfBirth() {
        if (dateOfBirth == null) {
            dateOfBirth = new StringFilter();
        }
        return dateOfBirth;
    }

    public void setDateOfBirth(StringFilter dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public StringFilter getHomePhome() {
        return homePhome;
    }

    public StringFilter homePhome() {
        if (homePhome == null) {
            homePhome = new StringFilter();
        }
        return homePhome;
    }

    public void setHomePhome(StringFilter homePhome) {
        this.homePhome = homePhome;
    }

    public StringFilter getAddress() {
        return address;
    }

    public StringFilter address() {
        if (address == null) {
            address = new StringFilter();
        }
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getWards() {
        return wards;
    }

    public StringFilter wards() {
        if (wards == null) {
            wards = new StringFilter();
        }
        return wards;
    }

    public void setWards(StringFilter wards) {
        this.wards = wards;
    }

    public StringFilter getDistrict() {
        return district;
    }

    public StringFilter district() {
        if (district == null) {
            district = new StringFilter();
        }
        return district;
    }

    public void setDistrict(StringFilter district) {
        this.district = district;
    }

    public StringFilter getProvince() {
        return province;
    }

    public StringFilter province() {
        if (province == null) {
            province = new StringFilter();
        }
        return province;
    }

    public void setProvince(StringFilter province) {
        this.province = province;
    }

    public StringFilter getJoinDate() {
        return joinDate;
    }

    public StringFilter joinDate() {
        if (joinDate == null) {
            joinDate = new StringFilter();
        }
        return joinDate;
    }

    public void setJoinDate(StringFilter joinDate) {
        this.joinDate = joinDate;
    }

    public StringFilter getMarried() {
        return married;
    }

    public StringFilter married() {
        if (married == null) {
            married = new StringFilter();
        }
        return married;
    }

    public void setMarried(StringFilter married) {
        this.married = married;
    }

    public StringFilter getGender() {
        return gender;
    }

    public StringFilter gender() {
        if (gender == null) {
            gender = new StringFilter();
        }
        return gender;
    }

    public void setGender(StringFilter gender) {
        this.gender = gender;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getJobtitleName() {
        return jobtitleName;
    }

    public StringFilter jobtitleName() {
        if (jobtitleName == null) {
            jobtitleName = new StringFilter();
        }
        return jobtitleName;
    }

    public void setJobtitleName(StringFilter jobtitleName) {
        this.jobtitleName = jobtitleName;
    }

    public LongFilter getApplicationId() {
        return applicationId;
    }

    public LongFilter applicationId() {
        if (applicationId == null) {
            applicationId = new LongFilter();
        }
        return applicationId;
    }

    public void setApplicationId(LongFilter applicationId) {
        this.applicationId = applicationId;
    }

    public LongFilter getJobtitleId() {
        return jobtitleId;
    }

    public LongFilter jobtitleId() {
        if (jobtitleId == null) {
            jobtitleId = new LongFilter();
        }
        return jobtitleId;
    }

    public void setJobtitleId(LongFilter jobtitleId) {
        this.jobtitleId = jobtitleId;
    }

    public LongFilter getDepartmentId() {
        return departmentId;
    }

    public LongFilter departmentId() {
        if (departmentId == null) {
            departmentId = new LongFilter();
        }
        return departmentId;
    }

    public void setDepartmentId(LongFilter departmentId) {
        this.departmentId = departmentId;
    }


    public HumanTypeFilter humanTypeFilter() {
        if (humanTypeFilter == null) {
            humanTypeFilter = new HumanTypeFilter();
        }
        return humanTypeFilter;
    }
    public HumanTypeFilter getHumanTypeFilter() {
        return humanTypeFilter;
    }

    public StringFilter getActive() {
        return active;
    }

    public void setActive(StringFilter active) {
        this.active = active;
    }



    public void setHumanTypeFilter(HumanTypeFilter humanTypeFilter) {
        this.humanTypeFilter = humanTypeFilter;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final HumansCriteria that = (HumansCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(fullName, that.fullName) &&
            Objects.equals(code, that.code) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(active, that.active) &&
            Objects.equals(email, that.email) &&
            Objects.equals(phoneNumber, that.phoneNumber) &&
            Objects.equals(dateOfBirth, that.dateOfBirth) &&
            Objects.equals(homePhome, that.homePhome) &&
            Objects.equals(address, that.address) &&
            Objects.equals(wards, that.wards) &&
            Objects.equals(district, that.district) &&
            Objects.equals(province, that.province) &&
            Objects.equals(joinDate, that.joinDate) &&
            Objects.equals(married, that.married) &&
            Objects.equals(gender, that.gender) &&
            Objects.equals(description, that.description) &&
            Objects.equals(jobtitleName, that.jobtitleName) &&
            Objects.equals(applicationId, that.applicationId) &&
            Objects.equals(jobtitleId, that.jobtitleId) &&
            Objects.equals(departmentId, that.departmentId) &&
            Objects.equals(humanTypeFilter, that.humanTypeFilter) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            fullName,
            code,
            userName,
            active,
            email,
            phoneNumber,
            dateOfBirth,
            homePhome,
            address,
            wards,
            district,
            province,
            joinDate,
            married,
            gender,
            description,
            jobtitleName,
            applicationId,
            jobtitleId,
            departmentId,
            humanTypeFilter,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HumansCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (fullName != null ? "fullName=" + fullName + ", " : "") +
            (code != null ? "code=" + code + ", " : "") +
            (userName != null ? "userName=" + userName + ", " : "") +
            (active != null ? "active=" + active + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "") +
            (dateOfBirth != null ? "dateOfBirth=" + dateOfBirth + ", " : "") +
            (homePhome != null ? "homePhome=" + homePhome + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (wards != null ? "wards=" + wards + ", " : "") +
            (district != null ? "district=" + district + ", " : "") +
            (province != null ? "province=" + province + ", " : "") +
            (joinDate != null ? "joinDate=" + joinDate + ", " : "") +
            (married != null ? "married=" + married + ", " : "") +
            (gender != null ? "gender=" + gender + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (jobtitleName != null ? "jobtitleName=" + jobtitleName + ", " : "") +
            (applicationId != null ? "applicationId=" + applicationId + ", " : "") +
            (jobtitleId != null ? "jobtitleId=" + jobtitleId + ", " : "") +
            (departmentId != null ? "departmentId=" + departmentId + ", " : "") +
            (humanTypeFilter != null ? "humanTypeFilter=" + humanTypeFilter + ", " : "") +
            (active != null ? "active=" + active + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
