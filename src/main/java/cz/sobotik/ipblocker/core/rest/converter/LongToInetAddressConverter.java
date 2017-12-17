package cz.sobotik.ipblocker.core.rest.converter;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * IP address converter
 * Converter of {@link Long} object to its {@link InetAddress} representation
 */
@Component
public class LongToInetAddressConverter implements Converter<Long, InetAddress> {

	@Override
	public InetAddress convert(Long source) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) ((source & 0xFF000000) >> 24);
		bytes[1] = (byte) ((source & 0x00FF0000) >> 16);
		bytes[2] = (byte) ((source & 0x0000FF00) >> 8);
		bytes[3] = (byte) (source & 0x000000FF);

		// or we can use ByteBuffer as in InetAddresToLongConverter

		try {
			return InetAddress.getByAddress(bytes);
		} catch (UnknownHostException e) {
			throw new IllegalArgumentException("Input string is not represent a valid IP address: " + source, e);
		}
	}
}
