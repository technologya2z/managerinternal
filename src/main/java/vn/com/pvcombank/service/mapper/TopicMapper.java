package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.Topic;
import vn.com.pvcombank.service.dto.TopicDTO;

/**
 * Mapper for the entity {@link Topic} and its DTO {@link TopicDTO}.
 */
@Mapper(componentModel = "spring")
public interface TopicMapper extends EntityMapper<TopicDTO, Topic> {}
