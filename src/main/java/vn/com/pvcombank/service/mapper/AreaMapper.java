package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.Area;
import vn.com.pvcombank.service.dto.AreaDTO;

/**
 * Mapper for the entity {@link Area} and its DTO {@link AreaDTO}.
 */
@Mapper(componentModel = "spring")
public interface AreaMapper extends EntityMapper<AreaDTO, Area> {}
