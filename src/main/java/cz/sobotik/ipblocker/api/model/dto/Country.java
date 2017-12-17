package cz.sobotik.ipblocker.api.model.dto;

import javax.annotation.Nonnull;

/**
 * Rest data may differ from entities in persistence
 * thats the reason to have separated them
 *
 * better solution is to have outgoing api in separate maven module
 *
 */
public class Country {

	/** Unique country ID (generated). */
	@Nonnull
	Long id;

	/** Unique ISO country code. */
	@Nonnull
	String code;

	/** Country name. */
	@Nonnull
	String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
