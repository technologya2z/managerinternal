package vn.com.pvcombank.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.domain.Department;
import vn.com.pvcombank.domain.Humans;
import vn.com.pvcombank.domain.Jobtitle;
import vn.com.pvcombank.service.dto.ApplicationDTO;
import vn.com.pvcombank.service.dto.DepartmentDTO;
import vn.com.pvcombank.service.dto.HumansDTO;
import vn.com.pvcombank.service.dto.JobtitleDTO;

/**
 * Mapper for the entity {@link Humans} and its DTO {@link HumansDTO}.
 */
@Mapper(componentModel = "spring")
public interface HumansMapper extends EntityMapper<HumansDTO, Humans> {
    @Mapping(target = "jobtitles", source = "jobtitles", qualifiedByName = "jobtitleIdSet")
    @Mapping(target = "department", source = "department", qualifiedByName = "departmentId")
    @Mapping(target = "applications", source = "applications", qualifiedByName = "applicationId")
    HumansDTO toDto(Humans s);

    @Mapping(target = "removeJobtitle", ignore = true)
    Humans toEntity(HumansDTO humansDTO);

    @Named("jobtitleId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    JobtitleDTO toDtoJobtitleId(Jobtitle jobtitle);

    @Named("jobtitleIdSet")
    default Set<JobtitleDTO> toDtoJobtitleIdSet(Set<Jobtitle> jobtitle) {
        return jobtitle.stream().map(this::toDtoJobtitleId).collect(Collectors.toSet());
    }

    @Named("departmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepartmentDTO toDtoDepartmentId(Department department);

    @Named("applicationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApplicationDTO toDtoApplicationId(Application application);

    @Named("applicationIdSet")
    default Set<ApplicationDTO> toDtoApplicationIdSet(Set<Application> applications) {
        return applications.stream().map(this::toDtoApplicationId).collect(Collectors.toSet());
    }
}
