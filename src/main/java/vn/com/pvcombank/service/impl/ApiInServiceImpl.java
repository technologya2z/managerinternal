package vn.com.pvcombank.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.ApiIn;
import vn.com.pvcombank.repository.ApiInRepository;
import vn.com.pvcombank.service.ApiInService;
import vn.com.pvcombank.service.dto.ApiInDTO;
import vn.com.pvcombank.service.mapper.ApiInMapper;

/**
 * Service Implementation for managing {@link ApiIn}.
 */
@Service
@Transactional
public class ApiInServiceImpl implements ApiInService {

    private final Logger log = LoggerFactory.getLogger(ApiInServiceImpl.class);

    private final ApiInRepository apiInRepository;

    private final ApiInMapper apiInMapper;

    public ApiInServiceImpl(ApiInRepository apiInRepository, ApiInMapper apiInMapper) {
        this.apiInRepository = apiInRepository;
        this.apiInMapper = apiInMapper;
    }

    @Override
    public ApiInDTO save(ApiInDTO apiInDTO) {
        log.debug("Request to save ApiIn : {}", apiInDTO);
        ApiIn apiIn = apiInMapper.toEntity(apiInDTO);
        apiIn = apiInRepository.save(apiIn);
        return apiInMapper.toDto(apiIn);
    }

    @Override
    public ApiInDTO update(ApiInDTO apiInDTO) {
        log.debug("Request to save ApiIn : {}", apiInDTO);
        ApiIn apiIn = apiInMapper.toEntity(apiInDTO);
        apiIn = apiInRepository.save(apiIn);
        return apiInMapper.toDto(apiIn);
    }

    @Override
    public Optional<ApiInDTO> partialUpdate(ApiInDTO apiInDTO) {
        log.debug("Request to partially update ApiIn : {}", apiInDTO);

        return apiInRepository
            .findById(apiInDTO.getId())
            .map(existingApiIn -> {
                apiInMapper.partialUpdate(existingApiIn, apiInDTO);

                return existingApiIn;
            })
            .map(apiInRepository::save)
            .map(apiInMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ApiInDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApiIns");
        return apiInRepository.findAll(pageable).map(apiInMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApiIn> findOne(Long id) {
        log.debug("Request to get ApiIn : {}", id);
        return apiInRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApiIn : {}", id);
        apiInRepository.deleteById(id);
    }
}
