package examples;

/** Element
 * 
 * A component that helps fill a Container.
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 *
 */
public interface Element {
	/**
	 * 
	 * @return The element's value.
	 */
	public int getValue();
	
	/**
	 * 
	 * @return The element's self-identification.
	 */
	public String identify();
}
