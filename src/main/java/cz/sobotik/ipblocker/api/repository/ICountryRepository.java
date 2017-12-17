package cz.sobotik.ipblocker.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.sobotik.ipblocker.api.model.domain.CountryEntity;


/**
 * CRUD repository extension
 */
@Repository
public interface ICountryRepository extends JpaRepository<CountryEntity, Long> {
  CountryEntity findByCode(String code);
}