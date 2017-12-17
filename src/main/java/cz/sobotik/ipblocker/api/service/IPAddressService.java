package cz.sobotik.ipblocker.api.service;

import java.net.InetAddress;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import cz.sobotik.ipblocker.api.model.dto.City;
import cz.sobotik.ipblocker.api.model.dto.IpAddressRange;


/**
 * Service for retrieving IP address related data.
 *
 * @see IpAddressRange
 * @see java.net.InetAddress
 */
public interface IPAddressService {

	/**
	 * @param cityId ID of city relates to {@link City#getId()}.
	 * @return List of IP address ranges currently associated to the city.
	 */
	@Nonnull
	List<IpAddressRange> getAllCityIPAddressRanges(@Nonnull Long cityId);

	/**
	 * Guess city in which is given IP address located.
	 *
	 * @param ipAddress IP address to locate.
	 * @return City in which is given IP address probably located.
	 */
	@Nullable
	City guessCityForIPAddress(@Nonnull InetAddress ipAddress);

}
