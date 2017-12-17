package cz.sobotik.ipblocker.api.service;

import java.net.InetAddress;

import cz.sobotik.ipblocker.core.annotation.LimitTraffic;

/**
 * Service to validate traffic limit
 *
 * Its called by AspectJ when Facade method is annoted with {@link LimitTraffic}
 * enables to limit only some of methods
 */
public interface LimitTrafficService {

  /**
   * validates given address and increase counter in CityEntity, RegionEntity and CountryEntity
   * if any of counter exceeds pre-defined limit returns null
   * limitation: IPv6 addresses are not supported
   *
   * @param convert
   * @return
   */
  boolean isValid(InetAddress convert);
}
