package vn.com.pvcombank.service.mapper;

import org.mapstruct.*;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.domain.Topic;
import vn.com.pvcombank.domain.TopicIn;
import vn.com.pvcombank.service.dto.ApplicationDTO;
import vn.com.pvcombank.service.dto.TopicDTO;
import vn.com.pvcombank.service.dto.TopicInDTO;

/**
 * Mapper for the entity {@link TopicIn} and its DTO {@link TopicInDTO}.
 */
@Mapper(componentModel = "spring")
public interface TopicInMapper extends EntityMapper<TopicInDTO, TopicIn> {
    @Mapping(target = "topic", source = "topic", qualifiedByName = "topicId")
    @Mapping(target = "application", source = "application", qualifiedByName = "applicationId")
    TopicInDTO toDto(TopicIn s);

    @Named("topicId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TopicDTO toDtoTopicId(Topic topic);

    @Named("applicationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ApplicationDTO toDtoApplicationId(Application application);
}
