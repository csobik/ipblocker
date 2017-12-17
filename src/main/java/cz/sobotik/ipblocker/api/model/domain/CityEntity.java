package cz.sobotik.ipblocker.api.model.domain;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="CITY")
@Entity(name="CITY")
public class CityEntity {

	/** Unique city ID (generated). */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	/** CityEntity name. */
	@Nonnull
	@Column
	private String name;

	/** GPS coordinates of the city center. */
	@Nonnull
	@Embedded
	private GpsCoordinatesEmbedded gpsCoordinatesEmbedded;

	/** Reference to {@link RegionEntity#getId()}. */
	@Nonnull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "REGION_ID", nullable = false)
	private RegionEntity region;

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

	public GpsCoordinatesEmbedded getGpsCoordinatesEmbedded() {
		return gpsCoordinatesEmbedded;
	}

	public void setGpsCoordinatesEmbedded(GpsCoordinatesEmbedded gpsCoordinatesEmbedded) {
		this.gpsCoordinatesEmbedded = gpsCoordinatesEmbedded;
	}

	public RegionEntity getRegion() {
		return region;
	}

	public void setRegion(RegionEntity region) {
		this.region = region;
	}
}
