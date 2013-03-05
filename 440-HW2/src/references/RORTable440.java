package references;

import java.util.HashMap;


/** RORTable440
 * 
 * Uses a HashMap to store key-ROR pairs
 * A counter is used to create a unique ID for each object
 * When an object is added, a RemoteObjectReference for that object is returned
 * An object is found using its uniqueID
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 */
public class RORTable440 {
	
	//Implementation of Table
	HashMap<Integer, Object> hm = new HashMap<Integer, Object>();
	
	//Counter to generate unique ID
	private int nextKey = 0; 
	
	 public RORTable440() {
	 }
	
	 /** RemoteObjectReference addObj(String host, int port, Object o)
	  * 
	  * @param host - host of server where Object lives
	  * @param port - port of server where Object lives
	  * @param o - the actual object to be stored
	  * @return RemoteObjectReference representing the local object
	  */
	 public RemoteObjectReference addObj(String host, int port, Object o) {
		 //Determine the Remote Interface this object implements
		 String interfaceName = o.getClass().getInterfaces()[0].getCanonicalName();
		 
		 /* Using the given host and port, the generated unique ID, and the
		  * remote interface name, a RemoteObjectReference is created */
		 RemoteObjectReference newROR = new RemoteObjectReference(host, port, nextKey, interfaceName);
		 
		 hm.put(nextKey, o);	//add object to table
		 nextKey += 1;			//increase counter to be next unique ID
		 
		 return newROR;
	 }
	
	 /** findObj(int key)
	  * 
	  * Finds the Object in the table using the key
	  * @param key - unique Identifier that represents the Object to be returned
	  * @return the Object associated with the unique ID key
	  */
	 public Object findObj(int key) {
		 return hm.get(key);
	 }
}
