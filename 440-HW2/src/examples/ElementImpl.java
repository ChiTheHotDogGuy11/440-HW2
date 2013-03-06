package examples;

import java.io.Serializable;

/** ElementImpl
 * 
 * A component that helps fill a Container.
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 *
 */
@SuppressWarnings("serial")
public class ElementImpl implements Element, Serializable {
	private int value;
	private String identifier;
	
	/**
	 * A constructor for Elements.
	 * @param value The element's value.
	 * @param identifier The element's identifier String.
	 */
	public ElementImpl(int value, String identifier) {
		this.value = value;
		this.identifier = identifier;
	}

	/**
	 * 
	 * @return The element's value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 
	 * @return The element's self-identification.
	 */
	public String identify() {
		return ("I am element " + identifier + " and my value is " + value);
	}

}
