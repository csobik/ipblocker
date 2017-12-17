package cz.sobotik.ipblocker.api.model.domain;
import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.persistence.Embeddable;

/**
 * GPS latitude/longitude.
 */
@Embeddable
public class GpsCoordinatesEmbedded implements Serializable {

	@Nonnull
	private Double latitude;

	@Nonnull
	private Double longtitude;

	public GpsCoordinatesEmbedded() {
	}

	public GpsCoordinatesEmbedded(@Nonnull Double latitude, @Nonnull Double longtitude) {
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
