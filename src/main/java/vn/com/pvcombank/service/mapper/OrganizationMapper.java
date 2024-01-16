package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.Area;
import vn.com.pvcombank.domain.Organization;
import vn.com.pvcombank.service.dto.AreaDTO;
import vn.com.pvcombank.service.dto.OrganizationDTO;

/**
 * Mapper for the entity {@link Organization} and its DTO {@link OrganizationDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {
    @Mapping(target = "area", source = "area", qualifiedByName = "areaId")
    OrganizationDTO toDto(Organization s);

    @Named("areaId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AreaDTO toDtoAreaId(Area area);
}
