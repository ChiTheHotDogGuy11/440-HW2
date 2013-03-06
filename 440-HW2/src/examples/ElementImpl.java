package examples;

import java.io.Serializable;

public class ElementImpl implements Element, Serializable {
	private int value;
	private String identifier;
	
	public ElementImpl(int value, String identifier) {
		this.value = value;
		this.identifier = identifier;
	}

	public int getValue() {
		return value;
	}

	public String identify() {
		return ("I am element " + identifier + " and my value is " + value);
	}

}
