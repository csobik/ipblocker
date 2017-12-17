package cz.sobotik.ipblocker.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.sobotik.ipblocker.api.model.domain.CityEntity;

/**
 * CRUD repository extension
 */
@Repository
public interface ICityRepository extends JpaRepository<CityEntity, Long> {
  CityEntity findByNameAndRegion_Id(String name, Long regionId);
  List<CityEntity> findByRegion_Id(Long regionId);

  List<CityEntity> findByRegion_Country_Id(Long countryId);
}