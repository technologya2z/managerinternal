package vn.com.pvcombank.service.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.domain.DatabaseInfo;
import vn.com.pvcombank.domain.Humans;
import vn.com.pvcombank.domain.OperaUnit;
import vn.com.pvcombank.domain.Topic;
import vn.com.pvcombank.service.dto.ApplicationDTO;
import vn.com.pvcombank.service.dto.DatabaseInfoDTO;
import vn.com.pvcombank.service.dto.HumansDTO;
import vn.com.pvcombank.service.dto.OperaUnitDTO;
import vn.com.pvcombank.service.dto.TopicDTO;

/**
 * Mapper for the entity {@link Application} and its DTO {@link ApplicationDTO}.
 */
@Mapper(componentModel = "spring")
public interface ApplicationMapper extends EntityMapper<ApplicationDTO, Application> {
    @Mapping(target = "topics", source = "topics", qualifiedByName = "topicIdSet")
    @Mapping(target = "operaunits", source = "operaunits", qualifiedByName = "operaUnitIdSet")
    @Mapping(target = "databaseinfos", source = "databaseinfos", qualifiedByName = "databaseInfoIdSet")
    @Mapping(target = "humans", source = "humans", qualifiedByName = "humansId")
    ApplicationDTO toDto(Application s);

    @Mapping(target = "removeTopic", ignore = true)
    @Mapping(target = "removeOperaunit", ignore = true)
    @Mapping(target = "removeDatabaseinfo", ignore = true)
    Application toEntity(ApplicationDTO applicationDTO);

    @Named("topicId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TopicDTO toDtoTopicId(Topic topic);

    @Named("topicIdSet")
    default Set<TopicDTO> toDtoTopicIdSet(Set<Topic> topic) {
        return topic.stream().map(this::toDtoTopicId).collect(Collectors.toSet());
    }

    @Named("operaUnitId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OperaUnitDTO toDtoOperaUnitId(OperaUnit operaUnit);

    @Named("operaUnitIdSet")
    default Set<OperaUnitDTO> toDtoOperaUnitIdSet(Set<OperaUnit> operaUnit) {
        return operaUnit.stream().map(this::toDtoOperaUnitId).collect(Collectors.toSet());
    }

    @Named("databaseInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DatabaseInfoDTO toDtoDatabaseInfoId(DatabaseInfo databaseInfo);

    @Named("databaseInfoIdSet")
    default Set<DatabaseInfoDTO> toDtoDatabaseInfoIdSet(Set<DatabaseInfo> databaseInfo) {
        return databaseInfo.stream().map(this::toDtoDatabaseInfoId).collect(Collectors.toSet());
    }

    @Named("humansId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    HumansDTO toDtoHumansId(Humans humans);
}
