package cz.sobotik.ipblocker.api.model.dto;

import javax.annotation.Nonnull;

/**
 * Rest data may differ from entities in persistence
 * thats the reason to have separated them
 *
 * better solution is to have outgoing api in separate maven module
 *
 */
public class Region {

	/** Unique region ID (generated). */
	@Nonnull
	Long id;

	/** Region name. */
	@Nonnull
	String name;

	/** Reference to {@link Country#getId()}. */
	@Nonnull
	Long countryId;

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

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(@Nonnull Long countryId) {
		this.countryId = countryId;
	}

}
