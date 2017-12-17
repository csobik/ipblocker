package cz.sobotik.ipblocker.core.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cz.sobotik.ipblocker.api.model.domain.CityEntity;
import cz.sobotik.ipblocker.api.model.domain.CountryEntity;
import cz.sobotik.ipblocker.api.model.domain.GpsCoordinatesEmbedded;
import cz.sobotik.ipblocker.api.model.domain.IpAddressRangeEntity;
import cz.sobotik.ipblocker.api.model.domain.RegionEntity;
import cz.sobotik.ipblocker.api.repository.IAddressRangeRepository;
import cz.sobotik.ipblocker.api.repository.ICityRepository;
import cz.sobotik.ipblocker.api.repository.ICountryRepository;
import cz.sobotik.ipblocker.api.repository.IRegionRepository;

/**
 * simple class to store csv data to DB
 *
 */
@Component
public class CsvLineReader {

  @Autowired
  private ICityRepository cityRepository;

  @Autowired
  private ICountryRepository countryRepository;

  @Autowired
  private IRegionRepository regionRepository;

  @Autowired
  private IAddressRangeRepository addressRangeRepository;

  // FIXME use some of cache ie ehCache
  // caches are used to avoid so many requests to DB
  Map<String, CountryEntity> cachedCountries = new HashMap<>();
  Map<String, RegionEntity> cachedRegions = new HashMap<>();
  Map<String, CityEntity> cachedCities = new HashMap<>();

  /**
   * store csv record to DB
   * @param record
   */
  public void mapLine(CSVRecord record) {

    // map, find or save country
    CountryEntity savedCountry = saveCountry(record);

    // map, find or save region
    RegionEntity region = saveRegion(record, savedCountry);

    // map, find or save city
    CityEntity city = saveCity(record, region);

    // map, find or save IP Range
    IpAddressRangeEntity ipAddressRange = IpAddressRange(record, city);
  }

  /**
   * store {@link IpAddressRangeEntity}
   *
   * @param record
   * @param city
   * @return
   */
  private IpAddressRangeEntity IpAddressRange(CSVRecord record, CityEntity city) {
    IpAddressRangeEntity found = addressRangeRepository.findByIpFromAndIpTo(Long.parseLong(record.get(0)), Long.parseLong(record.get(1)));
    if (found != null) {
      return found;
    }

    IpAddressRangeEntity ipRange = new IpAddressRangeEntity();
    ipRange.setIpFrom(Long.parseLong(record.get(0)));
    ipRange.setIpTo(Long.parseLong(record.get(1)));
    ipRange.setCity(city);
    return addressRangeRepository.save(ipRange);
  }

  /**
   * store {@link CityEntity}
   */
  private CityEntity saveCity(CSVRecord record, RegionEntity region) {
    String uqKey = record.get(5) + "." + record.get(4) + "." + record.get(2);
    if (cachedCities.containsKey(uqKey)) {
      return cachedCities.get(uqKey);
    }

    CityEntity city = new CityEntity();
    city.setName(record.get(5));
    GpsCoordinatesEmbedded gpsCoordinatesEmbedded = new GpsCoordinatesEmbedded();
    gpsCoordinatesEmbedded.setLatitude(Double.parseDouble(record.get(6)));
    gpsCoordinatesEmbedded.setLongtitude(Double.parseDouble(record.get(7)));
    city.setGpsCoordinatesEmbedded(gpsCoordinatesEmbedded);
    city.setRegion(region);
    CityEntity saved = cityRepository.save(city);
    cachedCities.put(uqKey, saved);

    return saved;
  }

  /**
   * store {@link RegionEntity}
   *
   * @param record
   * @param savedCountry
   * @return
   */
  private RegionEntity saveRegion(CSVRecord record, CountryEntity savedCountry) {
    String uqKey = record.get(4) + "." + record.get(2);
    if (cachedRegions.containsKey(uqKey)) {
      return cachedRegions.get(uqKey);
    }

    RegionEntity region = new RegionEntity();
    region.setName(record.get(4));
    region.setCountry(savedCountry);
    RegionEntity saved = regionRepository.save(region);
    cachedRegions.put(uqKey, saved);
    return saved;
  }

  /**
   * store {@link CountryEntity}
   *
   * @param record
   * @return
   */
  private CountryEntity saveCountry(CSVRecord record) {

    if (cachedCountries.containsKey(record.get(2))) {
      return cachedCountries.get(record.get(2));
    }

    CountryEntity country = new CountryEntity();
    country.setName(record.get(3));
    country.setCode(record.get(2));

    CountryEntity saved = countryRepository.save(country);
    cachedCountries.put(saved.getCode(), saved);

    return saved;
  }
}
