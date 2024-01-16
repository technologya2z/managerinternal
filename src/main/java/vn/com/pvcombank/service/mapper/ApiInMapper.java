package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.ApiIn;
import vn.com.pvcombank.domain.ApiInfo;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.service.dto.ApiInDTO;
import vn.com.pvcombank.service.dto.ApiInfoDTO;
import vn.com.pvcombank.service.dto.ApplicationDTO;

/**
 * Mapper for the entity {@link ApiIn} and its DTO {@link ApiInDTO}.
 */
@Mapper(componentModel = "spring")
public interface ApiInMapper extends EntityMapper<ApiInDTO, ApiIn> {
    @Mapping(target = "apiInfo", source = "apiInfo", qualifiedByName = "apiInfoId")
    @Mapping(target = "application", source = "application", qualifiedByName = "applicationId")
    ApiInDTO toDto(ApiIn s);

    @Named("apiInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApiInfoDTO toDtoApiInfoId(ApiInfo apiInfo);

    @Named("applicationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApplicationDTO toDtoApplicationId(Application application);
}
