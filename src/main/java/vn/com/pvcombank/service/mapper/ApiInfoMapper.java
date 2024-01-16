package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.ApiInfo;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.service.dto.ApiInfoDTO;
import vn.com.pvcombank.service.dto.ApplicationDTO;

/**
 * Mapper for the entity {@link ApiInfo} and its DTO {@link ApiInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface ApiInfoMapper extends EntityMapper<ApiInfoDTO, ApiInfo> {
    @Mapping(target = "application", source = "application", qualifiedByName = "applicationId")
    ApiInfoDTO toDto(ApiInfo s);

    @Named("applicationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApplicationDTO toDtoApplicationId(Application application);
}
