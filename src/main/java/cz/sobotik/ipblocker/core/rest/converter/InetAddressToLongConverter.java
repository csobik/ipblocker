package cz.sobotik.ipblocker.core.rest.converter;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



/**
 * IP address converter
 * Converter of {@link InetAddress} object to its {@link Long} representation
 */
@Component
public class InetAddressToLongConverter implements Converter<InetAddress, Long> {

	@Override
	public Long convert(InetAddress source) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES).order(ByteOrder.BIG_ENDIAN);
		buffer.put(new byte[] { 0,0,0,0 });
		buffer.put(source.getAddress());
		buffer.position(0);
		return buffer.getLong();
	}
}
