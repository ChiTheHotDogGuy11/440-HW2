package examples;

import stub.Remote440;

/** Container
 * 
 * An example usage of our RMI that tests all major aspects.
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 *
 */
public interface Container extends Remote440 {
	/** 
	 * 
	 * This method checks that passing a combination of remote
	 * objects and local objects works.
	 * @param elems The elements to get the average of.
	 * @return The arithmetic mean of the values of the elements.
	 */
	public double getAverage(Element[] elems);
	
	/**
	 * This method checks that returning a remote objects
	 * works alright.
	 * @param elems A group of elements.
	 * @return The minimum-valued element.
	 */
	public Element minElem(Element[] elems);
	
	/**
	 * This is just a convenient method for having each
	 * element identify itself.
	 * @param elems A group of elements
	 * @return The string that is the composition of all elements
	 * 		identifying themselves.
	 */
	public String identifyElems(Element[] elems);
	
	/**
	 * Check that passing primitive types works.
	 * @param value The value to shout out!
	 * @return A String that gives mad props to value.
	 */
	public String shoutout(int value);
}
