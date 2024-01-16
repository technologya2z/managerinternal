package vn.com.pvcombank.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.ApiInfo;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.domain.Humans;
import vn.com.pvcombank.repository.*;
import vn.com.pvcombank.service.ApiInfoService;
import vn.com.pvcombank.service.ApplicationService;
import vn.com.pvcombank.service.dto.ApplicationDTO;
import vn.com.pvcombank.service.mapper.ApplicationMapper;

/**
 * Service Implementation for managing {@link Application}.
 */
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ApplicationMapper applicationMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public ApplicationDTO save(ApplicationDTO applicationDTO) {
        log.debug("Request to save Application : {}", applicationDTO);
        Application application = applicationMapper.toEntity(applicationDTO);
        application = applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    @Override
    public ApplicationDTO update(ApplicationDTO applicationDTO) {
        log.debug("Request to save Application : {}", applicationDTO);
        Application application = applicationMapper.toEntity(applicationDTO);
        application = applicationRepository.save(application);
        return applicationMapper.toDto(application);
    }

    @Override
    public Optional<ApplicationDTO> partialUpdate(ApplicationDTO applicationDTO) {
        log.debug("Request to partially update Application : {}", applicationDTO);

        return applicationRepository
            .findById(applicationDTO.getId())
            .map(existingApplication -> {
                applicationMapper.partialUpdate(existingApplication, applicationDTO);

                return existingApplication;
            })
            .map(applicationRepository::save)
            .map(applicationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Application> findAll(Pageable pageable) {
        log.debug("Request to get all Applications");
        return applicationRepository.findAllWithEagerRelationships(pageable);
    }

    public Page<ApplicationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return applicationRepository.findAllWithEagerRelationships(pageable).map(applicationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Application> findOne(Long id) {
        log.debug("Request to get Application : {}", id);
        return applicationRepository.findOneWithEagerRelationships(id);
    }

    @Autowired
    ApiInfoRepository apiInfoRepository;

    @Autowired
    ApiInRepository apiInRepository;

    @Autowired
    ApiOutRepository apiOutRepository;

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Application : {}", id);
        applicationRepository.deleterelApplicationToHuman(id);

        applicationRepository.deleteTopicInApplication(id);
        applicationRepository.deleteTopicOutApplication(id);

        apiInfoRepository.deleterelApiInfotoApplication(id);
        apiInRepository.deleterelApiIntoApplication(id);
        apiOutRepository.deleterelApiOuttoApplication(id);
        applicationRepository.deleterelapplicationtoTopic(id);
        applicationRepository.deleterelapplicationtoDatabaseInfo(id);
        applicationRepository.deleterelapplicationtoOperaunit(id);

        applicationRepository.deleteById(id);
    }
}
