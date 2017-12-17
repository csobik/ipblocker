package cz.sobotik.ipblocker.api.model.domain;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 */
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "IP_FROM", "IP_TO" } ) } )
@Entity(name="IP_ADDRESS_RANGE")
public class IpAddressRangeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name="IP_FROM")
	private Long ipFrom;

	@Column(name="IP_TO")
	private Long ipTo;

	@Nonnull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID", nullable = false)
	private CityEntity city;

	public IpAddressRangeEntity() {
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	public Long getIpFrom() {
		return ipFrom;
	}

	public void setIpFrom(Long ipFrom) {
		this.ipFrom = ipFrom;
	}

	public Long getIpTo() {
		return ipTo;
	}

	public void setIpTo(Long ipTo) {
		this.ipTo = ipTo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
