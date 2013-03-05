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
    	
    	//Opens a socket at the given host/port
	    Socket soc;
		try {
			soc = new Socket(host, port);
		} catch (UnknownHostException e1) {
			System.out.println("Host not recognized.");
			return null;
		} catch (IOException e1) {
			System.out.println("Could not connect to host");
			return null;
		}
		
		//Creates object input/output streams
	    ObjectInputStream in;
	    ObjectOutputStream out;
		try {
			in = new ObjectInputStream(soc.getInputStream());
			out = new ObjectOutputStream(soc.getOutputStream());
		} catch (IOException e1) {
			System.out.println("Could not establish communication with registry.");
			return null;
		}
	    
	    //Asks what is living at this port
	    try {
			out.writeObject("who are you?");
		} catch (IOException e) {
			System.out.println("Error communicating with registry.");
			return null;
		}
	    
	  //Reads the response
	    try {
			if (((String)(in.readObject())).equals("I am a registry.")) {
			    return new RegistryCom440(host, port);
			} else {
			    System.out.println("somebody is there but not a registry!");
			    return null;
			}
		} catch (IOException e) {
			System.out.println("Error communicating with registry.");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Error communicating with registry.");
			return null;
		}
    }
}
