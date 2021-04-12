package de.ma_vin.util.sample.content.domain.single;

import de.ma_vin.util.sample.content.domain.IIdentifiable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Generated domain class of SingleRefOneParent
 */
@Data
@EqualsAndHashCode(exclude = {"singleRef"})
@NoArgsConstructor
@SuppressWarnings("java:S1068")
@ToString(exclude = {"singleRef"})
public class SingleRefOneParent implements IIdentifiable {

	public static final String ID_PREFIX = "S1";

	private String description;

	/**
	 * Identification of SingleRefOneParent
	 */
	private String identification;

	private SingleRefTwoParents singleRef;

}