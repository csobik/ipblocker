package cz.sobotik.ipblocker.core.rest.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.sobotik.ipblocker.api.model.domain.CityEntity;
import cz.sobotik.ipblocker.api.model.domain.CountryEntity;
import cz.sobotik.ipblocker.api.model.domain.IpAddressRangeEntity;
import cz.sobotik.ipblocker.api.model.domain.RegionEntity;
import cz.sobotik.ipblocker.api.model.dto.City;
import cz.sobotik.ipblocker.api.model.dto.Country;
import cz.sobotik.ipblocker.api.model.dto.GpsCoordinates;
import cz.sobotik.ipblocker.api.model.dto.IpAddressRange;
import cz.sobotik.ipblocker.api.model.dto.Region;
import cz.sobotik.ipblocker.core.rest.converter.LongToInetAddressConverter;

/**
 * Simple mapper from domain entities to json outputs objects
 */

@Component
public class EntityToJsonMapper {

  @Autowired
  LongToInetAddressConverter longToInetAddressConverter;

  public Country convertCountry(CountryEntity entity) {
    Country dto = new Country();
    dto.setName(entity.getName());
    dto.setCode(entity.getCode());
    dto.setId(entity.getId());
    return dto;
  }

  public Region convertRegion(RegionEntity entity) {
    Region dto = new Region();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setCountryId(entity.getCountry().getId());
    return dto;
  }

  public City convertCity(CityEntity entity) {
    City dto = new City();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    GpsCoordinates gpsCoordinates = new GpsCoordinates();
    gpsCoordinates.setLatitude(entity.getGpsCoordinatesEmbedded().getLatitude());
    gpsCoordinates.setLongtitude(entity.getGpsCoordinatesEmbedded().getLongtitude());
    dto.setGpsCoordinates(gpsCoordinates);
    dto.setRegionId(entity.getRegion().getId());
    return dto;
  }

  public IpAddressRange convertRange(IpAddressRangeEntity entity) {
    IpAddressRange dto = new IpAddressRange();
    dto.setFrom(longToInetAddressConverter.convert(entity.getIpFrom()));
    dto.setTo(longToInetAddressConverter.convert(entity.getIpTo()));
    return dto;
  }
}
