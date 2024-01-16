package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.OperaUnit;
import vn.com.pvcombank.service.dto.OperaUnitDTO;

/**
 * Mapper for the entity {@link OperaUnit} and its DTO {@link OperaUnitDTO}.
 */
@Mapper(componentModel = "spring")
public interface OperaUnitMapper extends EntityMapper<OperaUnitDTO, OperaUnit> {}
