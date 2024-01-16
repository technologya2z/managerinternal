package vn.com.pvcombank.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.Humans;
import vn.com.pvcombank.domain.Jobtitle;
import vn.com.pvcombank.repository.HumansRepository;
import vn.com.pvcombank.repository.JobtitleRepository;
import vn.com.pvcombank.service.HumansService;
import vn.com.pvcombank.service.dto.HumansDTO;
import vn.com.pvcombank.service.mapper.HumansMapper;

/**
 * Service Implementation for managing {@link Humans}.
 */
@Service
@Transactional
public class HumansServiceImpl implements HumansService {

    private final Logger log = LoggerFactory.getLogger(HumansServiceImpl.class);

    private final HumansRepository humansRepository;

    private final HumansMapper humansMapper;

    private final JobtitleRepository jobtitleRepository;

    public HumansServiceImpl(HumansRepository humansRepository, HumansMapper humansMapper, JobtitleRepository jobtitleRepository) {
        this.humansRepository = humansRepository;
        this.humansMapper = humansMapper;
        this.jobtitleRepository = jobtitleRepository;
    }

    @Override
    public HumansDTO save(HumansDTO humansDTO) {
        log.debug("Request to save Humans : {}", humansDTO);
        Humans humans = humansMapper.toEntity(humansDTO);
        humans = setnameHuman(humans);
        humans = humansRepository.save(humans);
        return humansMapper.toDto(humans);
    }

    @Override
    public HumansDTO update(HumansDTO humansDTO) {
        log.debug("Request to save Humans : {}", humansDTO);
        Humans humans = humansMapper.toEntity(humansDTO);
        humans = setnameHuman(humans);
        humans = humansRepository.save(humans);
        return humansMapper.toDto(humans);
    }

    private Humans setnameHuman(Humans humans) {
        if (humans.getJobtitles().size() > 0) {
            String name = "";
            for (Jobtitle job : humans.getJobtitles()) {
                Jobtitle jobtitle = jobtitleRepository.getById(job.getId());
                name += jobtitle.getCode();
            }
            humans.setJobtitleName(name);
        }
        return humans;
    }

    @Override
    public Optional<HumansDTO> partialUpdate(HumansDTO humansDTO) {
        log.debug("Request to partially update Humans : {}", humansDTO);

        return humansRepository
            .findById(humansDTO.getId())
            .map(existingHumans -> {
                humansMapper.partialUpdate(existingHumans, humansDTO);

                return existingHumans;
            })
            .map(humansRepository::save)
            .map(humansMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<HumansDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Humans");
        return humansRepository.findAll(pageable).map(humansMapper::toDto);
    }

    public Page<HumansDTO> findAllWithEagerRelationships(Pageable pageable) {
        return humansRepository.findAllWithEagerRelationships(pageable).map(humansMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Humans> findOne(Long id) {
        log.debug("Request to get Humans : {}", id);
        return humansRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Humans : {}", id);
        humansRepository.deleteById(id);
    }
}
