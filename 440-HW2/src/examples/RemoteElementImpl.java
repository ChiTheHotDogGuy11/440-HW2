package examples;

import communication.RMI440;

/** RemoteElementImpl
 * 
 * The remote implementation of Element.
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 *
 */
public class RemoteElementImpl implements RemoteElement {
	private int value;
	private String identifier;

	/**
	 * The necessary main method to set up a server 
	 * for the remote object.
	 * @param args
	 */
	public static void main(String[] args) {
		RMI440 serverInst = new RMI440("128.237.207.225", 1233, "elem2", new RemoteElementImpl(236, "Justin"));
		serverInst.run();
	}
	
	/**
	 * A constructor for Elements.
	 * @param value The element's value.
	 * @param identifier The element's identifier String.
	 */
	public RemoteElementImpl(int value, String identifier) {
		this.value = value;
		this.identifier = identifier;
	}

	/**
	 * 
	 * @return The element's value.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * 
	 * @return The element's self-identification.
	 */
	public String identify() {
		return ("I am REMOTE element " + identifier + " and my value is " + value);
	}
}
