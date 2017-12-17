package cz.sobotik.ipblocker.core.rest.converter;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * IP address converter
 * Converter to support {@link InetAddress} as a valid parameter in Spring MVC. Conversion is performed via
 * {@link InetAddress#getByName(String)}.
 */
@Component
public class StringToInetAddressConverter implements Converter<String, InetAddress> {

	@Override
	public InetAddress convert(String source) {
		try {
			return InetAddress.getByName(source);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("Input string is not represent a valid IP address: " + source, e);
		}
	}
}
