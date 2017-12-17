package cz.sobotik.ipblocker.api.model.dto;

import javax.annotation.Nonnull;

/**
 * Rest data may differ from entities in persistence
 * thats the reason to have separated them
 *
 * better solution is to have outgoing api in separate maven module
 *
 */
public class City {

	/** Unique city ID (generated). */
	@Nonnull
	Long id;

	/** City name. */
	@Nonnull
	String name;

	/** GPS coordinates of the city center. */
	@Nonnull
	GpsCoordinates gpsCoordinates;

	/** Reference to {@link Region#getId()}. */
	@Nonnull
	Long regionId;

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

	public GpsCoordinates getGpsCoordinates() {
		return gpsCoordinates;
	}

	public void setGpsCoordinates(GpsCoordinates gpsCoordinates) {
		this.gpsCoordinates = gpsCoordinates;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}
}
