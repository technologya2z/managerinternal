package vn.com.pvcombank.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.ApiOut;
import vn.com.pvcombank.repository.ApiOutRepository;
import vn.com.pvcombank.service.ApiOutService;
import vn.com.pvcombank.service.dto.ApiOutDTO;
import vn.com.pvcombank.service.mapper.ApiOutMapper;

/**
 * Service Implementation for managing {@link ApiOut}.
 */
@Service
@Transactional
public class ApiOutServiceImpl implements ApiOutService {

    private final Logger log = LoggerFactory.getLogger(ApiOutServiceImpl.class);

    private final ApiOutRepository apiOutRepository;

    private final ApiOutMapper apiOutMapper;

    public ApiOutServiceImpl(ApiOutRepository apiOutRepository, ApiOutMapper apiOutMapper) {
        this.apiOutRepository = apiOutRepository;
        this.apiOutMapper = apiOutMapper;
    }

    @Override
    public ApiOutDTO save(ApiOutDTO apiOutDTO) {
        log.debug("Request to save ApiOut : {}", apiOutDTO);
        ApiOut apiOut = apiOutMapper.toEntity(apiOutDTO);
        apiOut = apiOutRepository.save(apiOut);
        return apiOutMapper.toDto(apiOut);
    }

    @Override
    public ApiOutDTO update(ApiOutDTO apiOutDTO) {
        log.debug("Request to save ApiOut : {}", apiOutDTO);
        ApiOut apiOut = apiOutMapper.toEntity(apiOutDTO);
        apiOut = apiOutRepository.save(apiOut);
        return apiOutMapper.toDto(apiOut);
    }

    @Override
    public Optional<ApiOutDTO> partialUpdate(ApiOutDTO apiOutDTO) {
        log.debug("Request to partially update ApiOut : {}", apiOutDTO);

        return apiOutRepository
            .findById(apiOutDTO.getId())
            .map(existingApiOut -> {
                apiOutMapper.partialUpdate(existingApiOut, apiOutDTO);

                return existingApiOut;
            })
            .map(apiOutRepository::save)
            .map(apiOutMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ApiOutDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApiOuts");
        return apiOutRepository.findAll(pageable).map(apiOutMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApiOut> findOne(Long id) {
        log.debug("Request to get ApiOut : {}", id);
        return apiOutRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApiOut : {}", id);
        apiOutRepository.deleteById(id);
    }
}
