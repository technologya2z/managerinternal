package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.ApiInfo;
import vn.com.pvcombank.domain.ApiOut;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.service.dto.ApiInfoDTO;
import vn.com.pvcombank.service.dto.ApiOutDTO;
import vn.com.pvcombank.service.dto.ApplicationDTO;

/**
 * Mapper for the entity {@link ApiOut} and its DTO {@link ApiOutDTO}.
 */
@Mapper(componentModel = "spring")
public interface ApiOutMapper extends EntityMapper<ApiOutDTO, ApiOut> {
    @Mapping(target = "apiInfo", source = "apiInfo", qualifiedByName = "apiInfoId")
    @Mapping(target = "application", source = "application", qualifiedByName = "applicationId")
    ApiOutDTO toDto(ApiOut s);

    @Named("apiInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApiInfoDTO toDtoApiInfoId(ApiInfo apiInfo);

    @Named("applicationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApplicationDTO toDtoApplicationId(Application application);
}
