package cz.sobotik.ipblocker.core.rest.controller;

import java.net.InetAddress;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.sobotik.ipblocker.api.model.dto.City;
import cz.sobotik.ipblocker.api.model.dto.Country;
import cz.sobotik.ipblocker.api.model.dto.IpAddressRange;
import cz.sobotik.ipblocker.api.model.dto.Region;
import cz.sobotik.ipblocker.api.service.IPAddressService;
import cz.sobotik.ipblocker.api.service.LocationService;
import cz.sobotik.ipblocker.core.annotation.LimitTraffic;

/**
 * RESTfull facade over {@link LocationService} and {@link IPAddressService}.
 */
@RestController
public class RestFacade implements LocationService, IPAddressService {

	private final LocationService locationService;
	private final IPAddressService ipAddressService;

	@Autowired
	public RestFacade(LocationService locationService, IPAddressService ipAddressService) {
		this.locationService = locationService;
		this.ipAddressService = ipAddressService;
	}

	//region LocationService

	@RequestMapping("country")
	@Override
	@Nonnull
	@LimitTraffic
	public List<Country> getAllCountries() {
		return locationService.getAllCountries();
	}

	@RequestMapping("country/{countryId}/region")
	@Override
	@Nonnull
	@LimitTraffic
	public List<Region> getAllCountryRegions(@PathVariable("countryId") @Nonnull Long countryId) {
		return locationService.getAllCountryRegions(countryId);
	}

	@RequestMapping("region/{regionId}/city")
	@Override
	@Nonnull
	@LimitTraffic
	public List<City> getAllRegionCities(@PathVariable("regionId") @Nonnull Long regionId) {
		return locationService.getAllRegionCities(regionId);
	}

	@RequestMapping("country/{countryId}/city")
	@Override
	@Nonnull
	@LimitTraffic
	public List<City> getAllCountryCitites(@PathVariable("countryId") @Nonnull Long countryId) {
		return locationService.getAllCountryCitites(countryId);
	}

	//endregion


	//region IPAddressService

	@RequestMapping("city/{cityId}/ipaddress")
	@Override
	@Nonnull
	@LimitTraffic
	public List<IpAddressRange> getAllCityIPAddressRanges(@PathVariable("cityId") @Nonnull Long cityId) {
		return ipAddressService.getAllCityIPAddressRanges(cityId);
	}

	@RequestMapping("ipaddress/guess")
	@Override
	@Nullable
	@LimitTraffic
	public City guessCityForIPAddress(@RequestParam("ip") @Nonnull InetAddress ipAddress) {
		return ipAddressService.guessCityForIPAddress(ipAddress);
	}

	//endregion
}
