package vn.com.pvcombank.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.domain.OperaUnit;
import vn.com.pvcombank.domain.RelApplicationOperaUnit;
import vn.com.pvcombank.repository.ApplicationRepository;
import vn.com.pvcombank.repository.OperaUnitRepository;
import vn.com.pvcombank.repository.RelApplicationOperaUnitRepo;
import vn.com.pvcombank.service.OperaUnitService;
import vn.com.pvcombank.service.dto.OperaUnitDTO;
import vn.com.pvcombank.service.mapper.OperaUnitMapper;

/**
 * Service Implementation for managing {@link OperaUnit}.
 */
@Service
@Transactional
public class OperaUnitServiceImpl implements OperaUnitService {

    private final Logger log = LoggerFactory.getLogger(OperaUnitServiceImpl.class);

    private final OperaUnitRepository operaUnitRepository;

    private final RelApplicationOperaUnitRepo relApplicationOperaUnitRepo;

    private final ApplicationRepository applicationRepository;

    private final OperaUnitMapper operaUnitMapper;

    public OperaUnitServiceImpl(
        OperaUnitRepository operaUnitRepository,
        RelApplicationOperaUnitRepo relApplicationOperaUnitRepo,
        ApplicationRepository applicationRepository,
        OperaUnitMapper operaUnitMapper
    ) {
        this.operaUnitRepository = operaUnitRepository;
        this.relApplicationOperaUnitRepo = relApplicationOperaUnitRepo;
        this.applicationRepository = applicationRepository;
        this.operaUnitMapper = operaUnitMapper;
    }

    @Override
    public OperaUnitDTO save(OperaUnitDTO operaUnitDTO) {
        log.debug("Request to save OperaUnit : {}", operaUnitDTO);
        OperaUnit operaUnit = operaUnitMapper.toEntity(operaUnitDTO);
        operaUnit = operaUnitRepository.save(operaUnit);
        return operaUnitMapper.toDto(operaUnit);
    }

    @Override
    public OperaUnitDTO update(OperaUnitDTO operaUnitDTO) {
        log.debug("Request to save OperaUnit : {}", operaUnitDTO);
        OperaUnit operaUnit = operaUnitMapper.toEntity(operaUnitDTO);
        operaUnit = operaUnitRepository.save(operaUnit);
        return operaUnitMapper.toDto(operaUnit);
    }

    @Override
    public Optional<OperaUnitDTO> partialUpdate(OperaUnitDTO operaUnitDTO) {
        log.debug("Request to partially update OperaUnit : {}", operaUnitDTO);

        return operaUnitRepository
            .findById(operaUnitDTO.getId())
            .map(existingOperaUnit -> {
                operaUnitMapper.partialUpdate(existingOperaUnit, operaUnitDTO);

                return existingOperaUnit;
            })
            .map(operaUnitRepository::save)
            .map(operaUnitMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OperaUnitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OperaUnits");
        return operaUnitRepository.findAll(pageable).map(operaUnitMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OperaUnit> findOne(Long id) {
        log.debug("Request to get OperaUnit : {}", id);
        return operaUnitRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OperaUnit : {}", id);
        operaUnitRepository.deleteById(id);
    }

    @Override
    public Optional<OperaUnit> findAllWithRelationShip(OperaUnit operaUnit) {
        List<Application> applicationList = new ArrayList<>();
        List<RelApplicationOperaUnit> rels = relApplicationOperaUnitRepo.findAllByOperaunitId(operaUnit.getId());
        for (RelApplicationOperaUnit rel : rels) {
            applicationList.add(applicationRepository.findById(rel.getApplicationId()).get());
        }
        operaUnit.applications((Set<Application>) applicationList);
        return Optional.of(operaUnit);
    }
}
