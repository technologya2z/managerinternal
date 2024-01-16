package vn.com.pvcombank.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.DatabaseInfo;
import vn.com.pvcombank.repository.DatabaseInfoRepository;
import vn.com.pvcombank.service.DatabaseInfoService;
import vn.com.pvcombank.service.dto.DatabaseInfoDTO;
import vn.com.pvcombank.service.mapper.DatabaseInfoMapper;

/**
 * Service Implementation for managing {@link DatabaseInfo}.
 */
@Service
@Transactional
public class DatabaseInfoServiceImpl implements DatabaseInfoService {

    private final Logger log = LoggerFactory.getLogger(DatabaseInfoServiceImpl.class);

    private final DatabaseInfoRepository databaseInfoRepository;

    private final DatabaseInfoMapper databaseInfoMapper;

    public DatabaseInfoServiceImpl(DatabaseInfoRepository databaseInfoRepository, DatabaseInfoMapper databaseInfoMapper) {
        this.databaseInfoRepository = databaseInfoRepository;
        this.databaseInfoMapper = databaseInfoMapper;
    }

    @Override
    public DatabaseInfoDTO save(DatabaseInfoDTO databaseInfoDTO) {
        log.debug("Request to save DatabaseInfo : {}", databaseInfoDTO);
        DatabaseInfo databaseInfo = databaseInfoMapper.toEntity(databaseInfoDTO);
        databaseInfo = databaseInfoRepository.save(databaseInfo);
        return databaseInfoMapper.toDto(databaseInfo);
    }

    @Override
    public DatabaseInfoDTO update(DatabaseInfoDTO databaseInfoDTO) {
        log.debug("Request to save DatabaseInfo : {}", databaseInfoDTO);
        DatabaseInfo databaseInfo = databaseInfoMapper.toEntity(databaseInfoDTO);
        databaseInfo = databaseInfoRepository.save(databaseInfo);
        return databaseInfoMapper.toDto(databaseInfo);
    }

    @Override
    public Optional<DatabaseInfoDTO> partialUpdate(DatabaseInfoDTO databaseInfoDTO) {
        log.debug("Request to partially update DatabaseInfo : {}", databaseInfoDTO);

        return databaseInfoRepository
            .findById(databaseInfoDTO.getId())
            .map(existingDatabaseInfo -> {
                databaseInfoMapper.partialUpdate(existingDatabaseInfo, databaseInfoDTO);

                return existingDatabaseInfo;
            })
            .map(databaseInfoRepository::save)
            .map(databaseInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DatabaseInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DatabaseInfos");
        return databaseInfoRepository.findAll(pageable).map(databaseInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DatabaseInfo> findOne(Long id) {
        log.debug("Request to get DatabaseInfo : {}", id);
        return databaseInfoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DatabaseInfo : {}", id);
        databaseInfoRepository.deleteById(id);
    }
}
