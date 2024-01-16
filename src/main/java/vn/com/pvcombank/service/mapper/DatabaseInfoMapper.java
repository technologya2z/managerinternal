package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.DatabaseInfo;
import vn.com.pvcombank.service.dto.DatabaseInfoDTO;

/**
 * Mapper for the entity {@link DatabaseInfo} and its DTO {@link DatabaseInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DatabaseInfoMapper extends EntityMapper<DatabaseInfoDTO, DatabaseInfo> {}
