package vn.com.pvcombank.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.TopicOut;
import vn.com.pvcombank.repository.TopicOutRepository;
import vn.com.pvcombank.service.TopicOutService;
import vn.com.pvcombank.service.dto.TopicOutDTO;
import vn.com.pvcombank.service.mapper.TopicOutMapper;

/**
 * Service Implementation for managing {@link TopicOut}.
 */
@Service
@Transactional
public class TopicOutServiceImpl implements TopicOutService {

    private final Logger log = LoggerFactory.getLogger(TopicOutServiceImpl.class);

    private final TopicOutRepository topicOutRepository;

    private final TopicOutMapper topicOutMapper;

    public TopicOutServiceImpl(TopicOutRepository topicOutRepository, TopicOutMapper topicOutMapper) {
        this.topicOutRepository = topicOutRepository;
        this.topicOutMapper = topicOutMapper;
    }

    @Override
    public TopicOutDTO save(TopicOutDTO topicOutDTO) {
        log.debug("Request to save TopicOut : {}", topicOutDTO);
        TopicOut topicOut = topicOutMapper.toEntity(topicOutDTO);
        topicOut = topicOutRepository.save(topicOut);
        return topicOutMapper.toDto(topicOut);
    }

    @Override
    public TopicOutDTO update(TopicOutDTO topicOutDTO) {
        log.debug("Request to save TopicOut : {}", topicOutDTO);
        TopicOut topicOut = topicOutMapper.toEntity(topicOutDTO);
        topicOut = topicOutRepository.save(topicOut);
        return topicOutMapper.toDto(topicOut);
    }

    @Override
    public Optional<TopicOutDTO> partialUpdate(TopicOutDTO topicOutDTO) {
        log.debug("Request to partially update TopicOut : {}", topicOutDTO);

        return topicOutRepository
            .findById(topicOutDTO.getId())
            .map(existingTopicOut -> {
                topicOutMapper.partialUpdate(existingTopicOut, topicOutDTO);

                return existingTopicOut;
            })
            .map(topicOutRepository::save)
            .map(topicOutMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TopicOutDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TopicOuts");
        return topicOutRepository.findAll(pageable).map(topicOutMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TopicOut> findOne(Long id) {
        log.debug("Request to get TopicOut : {}", id);
        return topicOutRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TopicOut : {}", id);
        topicOutRepository.deleteById(id);
    }
}
