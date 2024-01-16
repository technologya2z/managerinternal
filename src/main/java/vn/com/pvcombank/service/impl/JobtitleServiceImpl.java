package vn.com.pvcombank.service.impl;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.Jobtitle;
import vn.com.pvcombank.repository.JobtitleRepository;
import vn.com.pvcombank.service.JobtitleService;
import vn.com.pvcombank.service.dto.JobtitleDTO;
import vn.com.pvcombank.service.mapper.JobtitleMapper;

/**
 * Service Implementation for managing {@link Jobtitle}.
 */
@Service
@Transactional
public class JobtitleServiceImpl implements JobtitleService {

    private final Logger log = LoggerFactory.getLogger(JobtitleServiceImpl.class);

    private final JobtitleRepository jobtitleRepository;

    private final JobtitleMapper jobtitleMapper;

    public JobtitleServiceImpl(JobtitleRepository jobtitleRepository, JobtitleMapper jobtitleMapper) {
        this.jobtitleRepository = jobtitleRepository;
        this.jobtitleMapper = jobtitleMapper;
    }

    @Override
    public JobtitleDTO save(JobtitleDTO jobtitleDTO) {
        log.debug("Request to save Jobtitle : {}", jobtitleDTO);
        Jobtitle jobtitle = jobtitleMapper.toEntity(jobtitleDTO);
        jobtitle = jobtitleRepository.save(jobtitle);
        return jobtitleMapper.toDto(jobtitle);
    }

    @Override
    public JobtitleDTO update(JobtitleDTO jobtitleDTO) {
        log.debug("Request to save Jobtitle : {}", jobtitleDTO);
        Jobtitle jobtitle = jobtitleMapper.toEntity(jobtitleDTO);
        jobtitle = jobtitleRepository.save(jobtitle);
        return jobtitleMapper.toDto(jobtitle);
    }

    @Override
    public Optional<JobtitleDTO> partialUpdate(JobtitleDTO jobtitleDTO) {
        log.debug("Request to partially update Jobtitle : {}", jobtitleDTO);

        return jobtitleRepository
            .findById(jobtitleDTO.getId())
            .map(existingJobtitle -> {
                jobtitleMapper.partialUpdate(existingJobtitle, jobtitleDTO);

                return existingJobtitle;
            })
            .map(jobtitleRepository::save)
            .map(jobtitleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<JobtitleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Jobtitles");
        return jobtitleRepository.findAll(pageable).map(jobtitleMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Jobtitle> findOne(Long id) {
        log.debug("Request to get Jobtitle : {}", id);
        return jobtitleRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Jobtitle : {}", id);
        jobtitleRepository.deleteById(id);
    }
}
