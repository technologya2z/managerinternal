package vn.com.pvcombank.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.TopicIn;
import vn.com.pvcombank.repository.TopicInRepository;
import vn.com.pvcombank.service.TopicInService;
import vn.com.pvcombank.service.dto.TopicInDTO;
import vn.com.pvcombank.service.mapper.TopicInMapper;

/**
 * Service Implementation for managing {@link TopicIn}.
 */
@Service
@Transactional
public class TopicInServiceImpl implements TopicInService {

    private final Logger log = LoggerFactory.getLogger(TopicInServiceImpl.class);

    private final TopicInRepository topicInRepository;

    private final TopicInMapper topicInMapper;

    public TopicInServiceImpl(TopicInRepository topicInRepository, TopicInMapper topicInMapper) {
        this.topicInRepository = topicInRepository;
        this.topicInMapper = topicInMapper;
    }

    @Override
    public TopicInDTO save(TopicInDTO topicInDTO) {
        log.debug("Request to save TopicIn : {}", topicInDTO);
        TopicIn topicIn = topicInMapper.toEntity(topicInDTO);
        topicIn = topicInRepository.save(topicIn);
        return topicInMapper.toDto(topicIn);
    }

    @Override
    public TopicInDTO update(TopicInDTO topicInDTO) {
        log.debug("Request to save TopicIn : {}", topicInDTO);
        TopicIn topicIn = topicInMapper.toEntity(topicInDTO);
        topicIn = topicInRepository.save(topicIn);
        return topicInMapper.toDto(topicIn);
    }

    @Override
    public Optional<TopicInDTO> partialUpdate(TopicInDTO topicInDTO) {
        log.debug("Request to partially update TopicIn : {}", topicInDTO);

        return topicInRepository
            .findById(topicInDTO.getId())
            .map(existingTopicIn -> {
                topicInMapper.partialUpdate(existingTopicIn, topicInDTO);

                return existingTopicIn;
            })
            .map(topicInRepository::save)
            .map(topicInMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TopicInDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TopicIns");
        return topicInRepository.findAll(pageable).map(topicInMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TopicIn> findOne(Long id) {
        log.debug("Request to get TopicIn : {}", id);
        return topicInRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TopicIn : {}", id);
        topicInRepository.deleteById(id);
    }
}
