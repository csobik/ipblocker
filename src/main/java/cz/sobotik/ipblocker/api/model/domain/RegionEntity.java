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

@Table(name = "REGION")
@Entity(name = "REGION")
public class RegionEntity {

	/** Unique region ID (generated). */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** RegionEntity name. */
	@Nonnull
	@Column
	private String name;

	/** Reference to {@link CountryEntity#getId()}. */
	@Nonnull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	private CountryEntity country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(@Nonnull CountryEntity country) {
		this.country = country;
	}
}
