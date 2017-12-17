package cz.sobotik.ipblocker.api.model.dto;

import javax.annotation.Nonnull;

/**
 * Rest data may differ from entities in persistence
 * thats the reason to have separated them
 *
 * better solution is to have outgoing api in separate maven module
 *
 */
public class GpsCoordinates {

	@Nonnull
	private Double latitude;

	@Nonnull
	private Double longtitude;

	public GpsCoordinates() {
	}

	public GpsCoordinates(@Nonnull Double latitude, @Nonnull Double longtitude) {
		this.latitude = latitude;
		this.longtitude = longtitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

}
