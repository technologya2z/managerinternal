package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.Jobtitle;
import vn.com.pvcombank.service.dto.JobtitleDTO;

/**
 * Mapper for the entity {@link Jobtitle} and its DTO {@link JobtitleDTO}.
 */
@Mapper(componentModel = "spring")
public interface JobtitleMapper extends EntityMapper<JobtitleDTO, Jobtitle> {}
