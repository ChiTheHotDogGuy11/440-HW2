package references;

import java.io.Serializable;

import stub.RemoteStub440;

@SuppressWarnings("serial")
/** RemoteObjectReference
 * 
 * Stores the necessary information to represent a local object living on a server
 * Localizes itself by creating a stub.
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 */
public class RemoteObjectReference implements Serializable {
	
	//Information needed to accurately represent the server's local object
	private String IP_address;
	private int port;
	private int object_key;
	private String RI_name;
	
	/** RemoteObjectReference(String ip, int port, int obj_key, String RI_name)
	 * 
	 * Constructor
	 * @param ip - IPAddress of server where the local object resides
	 * @param port - port of server where the local object resides
	 * @param obj_key - unique identifier the server can use to grab local object
	 * @param RI_name - name of the interface the local object implements
	 */
	public RemoteObjectReference(String ip, int port, int obj_key, String RI_name) {
		this.IP_address = ip;
		this.port = port;
		this.object_key = obj_key;
		this.RI_name = RI_name;
	}
	
	/** localize()
	 * 
	 * Uses reflection to create an instantiation of a stub.
	 * Assumes the stub is named RI_Name + "_Stub"
	 * @return an instantiation of a stub taking the name of RI_Name + "_Stub"
	 */
	public RemoteStub440 localize() {
		String stub_name = RI_name + "_Stub";
		Class<?> c = null;
		RemoteStub440 o = null;
		
		//Determines if class exists
		try {
			c = Class.forName(stub_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Attempts to create an instantiation of this class
		try {
			o = (RemoteStub440)c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//Sets itself as the ROR that created the stub
		o.setROR(this);
		
		return o;
	}
	
	/** getIP()
	 * 
	 * @return String IP_address
	 */
	public String getIP() {
		return IP_address;
	}
	
	/** getPort()
	 * 
	 * @return int port
	 */
	public int getPort() {
		return port;
	}

	/** getObjectKey()
	 * 
	 * @return int object_key
	 */
	public int getObjectKey() {
		return object_key;
	}
	
	/** getRIName()
	 * 
	 * @return String RI_name
	 */
	public String getRIName() {
		return RI_name;
	}
}
