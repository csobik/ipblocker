package cz.sobotik.ipblocker.core.service;

import java.net.InetAddress;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cz.sobotik.ipblocker.api.model.domain.IpAddressRangeEntity;
import cz.sobotik.ipblocker.api.model.dto.City;
import cz.sobotik.ipblocker.api.model.dto.IpAddressRange;
import cz.sobotik.ipblocker.api.repository.IAddressRangeRepository;
import cz.sobotik.ipblocker.api.service.IPAddressService;
import cz.sobotik.ipblocker.core.rest.converter.EntityToJsonMapper;
import cz.sobotik.ipblocker.core.rest.converter.InetAddressToLongConverter;

@Primary
@Service
public class IPAddressServiceImpl implements IPAddressService {

	@Autowired
	IAddressRangeRepository addressRangeRepository;

	@Autowired
	InetAddressToLongConverter inetAddressToLongConverter;

	@Autowired
	EntityToJsonMapper entityToJsonMapper;

	@Nonnull
	@Override
	public List<IpAddressRange> getAllCityIPAddressRanges(@Nonnull Long cityId) {
		List<IpAddressRangeEntity> allRanges = addressRangeRepository.findByCity_Id(cityId);
		return allRanges.stream().map(entityToJsonMapper::convertRange).collect(Collectors.toList());
	}

	@Nullable
	@Override
	public City guessCityForIPAddress(@Nonnull InetAddress ipAddress) {

		long numAddress = inetAddressToLongConverter.convert(ipAddress);
		IpAddressRangeEntity range = addressRangeRepository.findOneByIpFromLessThanEqualAndIpToGreaterThanEqual(numAddress, numAddress);

 		if (range == null) {
			return null;
		}

 		return entityToJsonMapper.convertCity(range.getCity());

	}

}
