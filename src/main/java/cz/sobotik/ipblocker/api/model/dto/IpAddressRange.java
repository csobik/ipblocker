package cz.sobotik.ipblocker.api.model.dto;

import java.net.InetAddress;

/**
 * IP address range as interval &lt;{@link #from}; {@link #to}&gt;.
 *
 * Rest data may differ from entities in persistence
 * thats the reason to have separated them
 *
 * better solution is to have outgoing api in separate maven module
 *
 */
public class IpAddressRange {

	private InetAddress from;
	private InetAddress to;

	public IpAddressRange() {
	}

	public IpAddressRange(InetAddress from, InetAddress to) {
		this.from = from;
		this.to = to;
	}

	public InetAddress getFrom() {
		return from;
	}

	public void setFrom(InetAddress from) {
		this.from = from;
	}

	public InetAddress getTo() {
		return to;
	}

	public void setTo(InetAddress to) {
		this.to = to;
	}
}
