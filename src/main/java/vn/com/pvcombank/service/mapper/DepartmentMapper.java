package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.Department;
import vn.com.pvcombank.domain.Organization;
import vn.com.pvcombank.service.dto.DepartmentDTO;
import vn.com.pvcombank.service.dto.OrganizationDTO;

/**
 * Mapper for the entity {@link Department} and its DTO {@link DepartmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface DepartmentMapper extends EntityMapper<DepartmentDTO, Department> {
    @Mapping(target = "organization", source = "organization", qualifiedByName = "organizationId")
    DepartmentDTO toDto(Department s);

    @Named("organizationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrganizationDTO toDtoOrganizationId(Organization organization);
}
