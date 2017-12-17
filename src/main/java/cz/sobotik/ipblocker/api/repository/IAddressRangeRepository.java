package cz.sobotik.ipblocker.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.sobotik.ipblocker.api.model.domain.IpAddressRangeEntity;

/**
 * CRUD repository extension
 */
@Repository
public interface IAddressRangeRepository extends JpaRepository<IpAddressRangeEntity, Long> {
  IpAddressRangeEntity findByIpFromAndIpTo(Long ipFrom, Long ipTo);
  IpAddressRangeEntity findOneByIpFromLessThanEqualAndIpToGreaterThanEqual(Long ipFrom, Long ipTo);
  List<IpAddressRangeEntity> findByCity_Id(Long cityId);
}