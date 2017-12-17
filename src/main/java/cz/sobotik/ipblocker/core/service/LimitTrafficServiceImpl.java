package cz.sobotik.ipblocker.core.service;

import java.net.InetAddress;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.sobotik.ipblocker.api.model.domain.CityEntity;
import cz.sobotik.ipblocker.api.model.domain.Counter;
import cz.sobotik.ipblocker.api.model.domain.CountryEntity;
import cz.sobotik.ipblocker.api.model.domain.IpAddressRangeEntity;
import cz.sobotik.ipblocker.api.model.domain.RegionEntity;
import cz.sobotik.ipblocker.api.repository.IAddressRangeRepository;
import cz.sobotik.ipblocker.api.repository.ICityRepository;
import cz.sobotik.ipblocker.api.repository.ICountryRepository;
import cz.sobotik.ipblocker.api.repository.IRegionRepository;
import cz.sobotik.ipblocker.api.service.LimitTrafficService;
import cz.sobotik.ipblocker.core.SystemException;
import cz.sobotik.ipblocker.core.rest.converter.InetAddressToLongConverter;

/**
 * {@inheritDoc}
 */
@Service
@Transactional
public class LimitTrafficServiceImpl implements LimitTrafficService {

  @Autowired
  IAddressRangeRepository addressRangeRepository;

  @Autowired
  ICityRepository cityRepository;

  @Autowired
  IRegionRepository regionRepository;

  @Autowired
  ICountryRepository countryRepository;

  @Autowired
  InetAddressToLongConverter inetAddressToLongConverter;

  private static final Long CITY_REQUEST_LIMIT = 10L;
  private static final Long REGION_REQUEST_LIMIT = 100L;
  private static final Long COUNTRY_REQUEST_LIMIT = 1000L;
  private static final Long UNKNOWN_REQUEST_LIMIT = 10L;

  private static final int ADD_HOURS = 1;

  // map of city id and counter
  private Map<Long, Counter> cities = new HashMap<>();

  // map of region id and counter
  private Map<Long, Counter> regions = new HashMap<>();

  // map of country id and counter
  private Map<Long, Counter> countries = new HashMap<>();

  // map of unknown addreses
  private Map<Long, Counter> unknown = new HashMap<>();

  @Override
  public boolean isValid(InetAddress ipAddress) {

    // FIXME IPv6 does not work
    if (ipAddress.getAddress().length > 8) {
      throw new SystemException("IPv6AddressNotSupported", "Request from IPv6 Address is Not Supported");
    }

    long numAddress = inetAddressToLongConverter.convert(ipAddress);
    IpAddressRangeEntity range = addressRangeRepository.findOneByIpFromLessThanEqualAndIpToGreaterThanEqual(numAddress, numAddress);

    if (range != null) {
      CityEntity city = range.getCity();
      RegionEntity region = range.getCity().getRegion();
      CountryEntity country = range.getCity().getRegion().getCountry();

      increaseCounter(country.getId(), countries);
      increaseCounter(region.getId(), regions);
      increaseCounter(city.getId(), cities);

      if (countries.get(country.getId()).getCounter().longValue()<COUNTRY_REQUEST_LIMIT
              && regions.get(region.getId()).getCounter().longValue()<REGION_REQUEST_LIMIT
              && cities.get(city.getId()).getCounter().longValue()<CITY_REQUEST_LIMIT) {
        return true;
      }

    } else {
      increaseCounter(numAddress, unknown);
      if (unknown.get(numAddress).getCounter().longValue()<UNKNOWN_REQUEST_LIMIT) {
        return true;
      }
    }

    return false;
  }

  private void increaseCounter(Long counterId, Map<Long, Counter> counterMap) {
    // reset country limit if condition passes
    if (!counterMap.containsKey(counterId) || Instant.now().isAfter(counterMap.get(counterId).getExpireAt())) {
      Counter counter = createCounter();
      counterMap.put(counterId, counter);
    } else {
      Long c = counterMap.get(counterId).getCounter();
      counterMap.get(counterId).setCounter(c+1);
    }
  }

  private Counter createCounter() {
    Counter counter = new Counter();
    counter.setExpireAt(Instant.now().plus(ADD_HOURS, ChronoUnit.HOURS));
    counter.setCounter(1L);
    return counter;
  }
}
