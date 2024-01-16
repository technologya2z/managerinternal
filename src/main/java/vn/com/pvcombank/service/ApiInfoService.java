package vn.com.pvcombank.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.pvcombank.domain.ApiInfo;
import vn.com.pvcombank.domain.Application;
import vn.com.pvcombank.service.dto.ApiInfoDTO;
import vn.com.pvcombank.service.dto.JsonSwaggerDTO;

/**
 * Service Interface for managing {@link vn.com.pvcombank.domain.ApiInfo}.
 */
public interface ApiInfoService {
    /**
     * Save a apiInfo.
     *
     * @param apiInfoDTO the entity to save.
     * @return the persisted entity.
     */
    ApiInfoDTO save(ApiInfoDTO apiInfoDTO);

    /**
     * Updates a apiInfo.
     *
     * @param apiInfoDTO the entity to update.
     * @return the persisted entity.
     */
    ApiInfoDTO update(ApiInfoDTO apiInfoDTO);

    /**
     * Partially updates a apiInfo.
     *
     * @param apiInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApiInfoDTO> partialUpdate(ApiInfoDTO apiInfoDTO);

    /**
     * Get all the apiInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApiInfoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" apiInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApiInfo> findOne(Long id);

    /**
     * Delete the "id" apiInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void addListApi(List<JsonSwaggerDTO> jsonSwaggerDTOS, Application application);
}
