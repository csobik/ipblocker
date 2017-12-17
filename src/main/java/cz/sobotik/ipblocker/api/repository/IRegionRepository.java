package cz.sobotik.ipblocker.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.sobotik.ipblocker.api.model.domain.RegionEntity;


/**
 * CRUD repository extension
 */
@Repository
public interface IRegionRepository extends JpaRepository<RegionEntity, Long> {
  RegionEntity findByNameAndCountry_Id(String name, Long countryId);
  List<RegionEntity> findByCountry_Id(Long regionId);
}