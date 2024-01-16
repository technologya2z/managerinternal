package vn.com.pvcombank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import liquibase.pro.packaged.in;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.ApiIn;
import vn.com.pvcombank.domain.ApiInfo;
import vn.com.pvcombank.domain.ApiOut;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.repository.ApiInRepository;
import vn.com.pvcombank.repository.ApiInfoRepository;
import vn.com.pvcombank.repository.ApiOutRepository;
import vn.com.pvcombank.repository.ApplicationRepository;
import vn.com.pvcombank.service.ApiInService;
import vn.com.pvcombank.service.ApiInfoService;
import vn.com.pvcombank.service.ApiOutService;
import vn.com.pvcombank.service.dto.ApiInfoDTO;
import vn.com.pvcombank.service.dto.JsonSwaggerDTO;
import vn.com.pvcombank.service.mapper.ApiInfoMapper;

/**
 * Service Implementation for managing {@link ApiInfo}.
 */
@Service
@Transactional
public class ApiInfoServiceImpl implements ApiInfoService {

    private final Logger log = LoggerFactory.getLogger(ApiInfoServiceImpl.class);

    private final ApiInfoRepository apiInfoRepository;

    private final ApiInfoMapper apiInfoMapper;

    @Autowired
    ApiInService apiInService;

    @Autowired
    ApiOutService apiOutService;

    @Autowired
    ApplicationRepository applicationRepository;

    public ApiInfoServiceImpl(ApiInfoRepository apiInfoRepository, ApiInfoMapper apiInfoMapper) {
        this.apiInfoRepository = apiInfoRepository;
        this.apiInfoMapper = apiInfoMapper;
    }

    @Override
    public ApiInfoDTO save(ApiInfoDTO apiInfoDTO) {
        log.debug("Request to save ApiInfo : {}", apiInfoDTO);
        ApiInfo apiInfo = apiInfoMapper.toEntity(apiInfoDTO);
        apiInfo = apiInfoRepository.save(apiInfo);
        return apiInfoMapper.toDto(apiInfo);
    }

    @Override
    public ApiInfoDTO update(ApiInfoDTO apiInfoDTO) {
        log.debug("Request to save ApiInfo : {}", apiInfoDTO);
        ApiInfo apiInfo = apiInfoMapper.toEntity(apiInfoDTO);
        apiInfo = apiInfoRepository.save(apiInfo);
        return apiInfoMapper.toDto(apiInfo);
    }

    @Override
    public Optional<ApiInfoDTO> partialUpdate(ApiInfoDTO apiInfoDTO) {
        log.debug("Request to partially update ApiInfo : {}", apiInfoDTO);

        return apiInfoRepository
            .findById(apiInfoDTO.getId())
            .map(existingApiInfo -> {
                apiInfoMapper.partialUpdate(existingApiInfo, apiInfoDTO);

                return existingApiInfo;
            })
            .map(apiInfoRepository::save)
            .map(apiInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ApiInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApiInfos");
        return apiInfoRepository.findAll(pageable).map(apiInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ApiInfo> findOne(Long id) {
        log.debug("Request to get ApiInfo : {}", id);
        Optional<ApiInfo> apiInfo = apiInfoRepository.findById(id);
        return apiInfo;
    }

    @Autowired
    ApiInRepository apiInRepository;

    @Autowired
    ApiOutRepository apiOutRepository;

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApiInfo : {}", id);
        apiInfoRepository.deleterelApiInfotoApplication(id);
        apiInRepository.deleterelApiIntoApiInfo(id);
        apiOutRepository.deleterelApiOuttoApiInfo(id);
        apiInfoRepository.deleteById(id);
    }

    @Override
    @Async("taskExecutor")
    public void addListApi(List<JsonSwaggerDTO> jsonSwaggerDTOS, Application application) {
        List<ApiInfo> apiInfoList = new ArrayList<>();
        List<ApiInfo> listApiInfo = apiInfoRepository.findAllByApplication(application.getId());
        for (JsonSwaggerDTO jsonSwaggerDTO : jsonSwaggerDTOS) {
            if (!listApiInfo.contains(jsonSwaggerDTO.getPathApi())) {
                apiInfoList.add(new ApiInfo(jsonSwaggerDTO, application));
            }
        }
        apiInfoRepository.saveAll(apiInfoList);
    }
}
