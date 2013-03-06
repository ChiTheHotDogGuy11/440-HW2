package examples;

import communication.RMI440;

/** RemoteContainerImpl
 * 
 * A container for Element objects.
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 *
 */
public class RemoteContainerImpl implements Container{
	
	public RemoteContainerImpl() {
		
	}

	/**
	 * The necessary main method to set up a server 
	 * for the remote object.
	 * @param args
	 */
	public static void main(String[] args) {
		RMI440 serverInst = new RMI440("128.237.207.225", 1233, "container", new RemoteContainerImpl());
		serverInst.run();
	}
	
	/** 
	 * 
	 * This method checks that passing a combination of remote
	 * objects and local objects works.
	 * @param elems The elements to get the average of.
	 * @return The arithmetic mean of the values of the elements.
	 */
	public double getAverage(Element[] elems) {
		if (elems == null) return 0;
		int totalSum = 0;
		for (int i = 0; i < elems.length; i++) {
			totalSum += elems[i].getValue();
		}
		return ((double)(totalSum)) / ((double)elems.length);
	}

	/**
	 * This method checks that returning a remote objects
	 * works alright.
	 * @param elems A group of elements.
	 * @return The minimum-valued element.
	 */
	public Element minElem(Element[] elems) {
		if (elems == null) throw new IllegalArgumentException("Elems cannot be null!");
		Element curMin = null;
		int curMinValue = Integer.MAX_VALUE;
		for (int i = 0; i < elems.length; i++) {
			Element curElem = elems[i];
			if (curElem.getValue() < curMinValue) {
				curMin = curElem;
				curMinValue = curMin.getValue();
			}
		}
		return curMin;
	}
	
	/**
	 * This is just a convenient method for having each
	 * element identify itself.
	 * @param elems A group of elements
	 * @return The string that is the composition of all elements
	 * 		identifying themselves.
	 */
	public String identifyElems(Element[] elems) {
		if (elems == null) throw new IllegalArgumentException("Elems cannot be null!");
		String result = "";
		for (int i = 0; i < elems.length; i++) {
			result += elems[i].identify() + "\n";
		}
		return result;
	}

	/**
	 * Check that passing primitive types works.
	 * @param value The value to shout out!
	 * @return A String that gives mad props to value.
	 */
	public String shoutout(int value) {
		if (value < 0) throw new IllegalArgumentException("Negative values ain't worth my time!");
		return "This is a shoutout to my element with value " + value;
	}

}
