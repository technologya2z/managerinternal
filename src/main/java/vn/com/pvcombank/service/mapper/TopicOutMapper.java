package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.domain.Topic;
import vn.com.pvcombank.domain.TopicOut;
import vn.com.pvcombank.service.dto.ApplicationDTO;
import vn.com.pvcombank.service.dto.TopicDTO;
import vn.com.pvcombank.service.dto.TopicOutDTO;

/**
 * Mapper for the entity {@link TopicOut} and its DTO {@link TopicOutDTO}.
 */
@Mapper(componentModel = "spring")
public interface TopicOutMapper extends EntityMapper<TopicOutDTO, TopicOut> {
    @Mapping(target = "topic", source = "topic", qualifiedByName = "topicId")
    @Mapping(target = "application", source = "application", qualifiedByName = "applicationId")
    TopicOutDTO toDto(TopicOut s);

    @Named("topicId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TopicDTO toDtoTopicId(Topic topic);

    @Named("applicationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApplicationDTO toDtoApplicationId(Application application);
}
