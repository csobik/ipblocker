package cz.sobotik.ipblocker.core.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cz.sobotik.ipblocker.api.model.dto.City;
import cz.sobotik.ipblocker.api.model.dto.Country;
import cz.sobotik.ipblocker.api.model.dto.Region;
import cz.sobotik.ipblocker.api.repository.ICityRepository;
import cz.sobotik.ipblocker.api.repository.ICountryRepository;
import cz.sobotik.ipblocker.api.repository.IRegionRepository;
import cz.sobotik.ipblocker.api.service.LocationService;
import cz.sobotik.ipblocker.core.rest.converter.EntityToJsonMapper;

@Primary
@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private ICountryRepository countryRepository;

	@Autowired
	private IRegionRepository regionRepository;

	@Autowired
	private ICityRepository cityRepository;

	@Autowired
	EntityToJsonMapper entityToJsonMapper;

	@Nonnull
	@Override
	public List<Country> getAllCountries() {
		return countryRepository.findAll().stream().map(entityToJsonMapper::convertCountry).collect(Collectors.toList());
	}

	@Nonnull
	@Override
	public List<Region> getAllCountryRegions(@Nonnull Long countryId) {
		return regionRepository.findByCountry_Id(countryId).stream().map(entityToJsonMapper::convertRegion).collect(Collectors.toList());
	}

	@Nonnull
	@Override
	public List<City> getAllRegionCities(@Nonnull Long regionId) {
		return cityRepository.findByRegion_Id(regionId).stream().map(entityToJsonMapper::convertCity).collect(Collectors.toList());
	}

	@Nonnull
	@Override
	public List<City> getAllCountryCitites(@Nonnull Long countryId) {
		return cityRepository.findByRegion_Country_Id(countryId).stream().map(entityToJsonMapper::convertCity).collect(Collectors.toList());
	}

}
