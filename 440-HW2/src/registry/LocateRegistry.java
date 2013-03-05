package registry;

import java.net.*;
import java.io.*;

/** LocateRegistry
 * 
 * Contains static method to determine if a registry exists at a given host/port
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 */
public class LocateRegistry { 
    
	//TODO improve error/exception handling
	/** getRegistry(String host, int port)
	 * 
	 * Static method that determines if a global registry is living at given
	 * host and port. Returns a RegistryCom440 used to communicate with this
	 * global registry.
	 * 
	 * @param host - host where global registry could be living
	 * @param port - port where global registry could be living
	 * @return RegistryCom440 used to communicate with registry
	 */
    public static RegistryCom440 getRegistry(String host, int port) {
		
		try {
			//Opens a socket at the given host/port
		    Socket soc = new Socket(host, port);
		    
		    //Creates object input/output streams
		    ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
		    ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
		    
		    //Asks what is living at this port
		    out.writeObject("who are you?");
		    
		    //Reads the response
		    if (((String)(in.readObject())).equals("I am a registry.")) {
			    return new RegistryCom440(host, port);
			} else {
			    System.out.println("somebody is there but not a registry!");
			    return null;
			}
		}
		catch (Exception e) { 
			System.out.println("nobody is there!"+e);
			return null;
		}
    }
}
