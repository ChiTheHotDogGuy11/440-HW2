package registry;

import java.net.*;
import java.io.*;

import references.RemoteObjectReference;

/** RegistryCom440
 * 
 * Implements the Registry440
 * Used (by client) to communicate with an implementation of the registry
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 */
public class RegistryCom440 implements Registry440 
{ 
    //Host and port where registry is located 
    String host;
    int port;
    
    /** RegistryCom440(String IPAdr, int PortNum)
     * 
     * Constructor
     * @param host - host where registry is located
     * @param port - port where registry is located
     */
    public RegistryCom440(String host, int port) {
		this.host = host;
		this.port = port;
    }

    /** lookup(String serviceName)
     * 
     * Lookup by serviceName is performed at the registry. Returns the
     * RemoteObjectReference returned by the registry
     * 
     * @return RemoteObjectReference returned by registry
     */
    public RemoteObjectReference lookup(String serviceName) {
		
    	//Opens socket at host/port.
    	//Assumes LocateRegistry has detected that a registry is there
		Socket soc;
		try {
			soc = new Socket(host, port);
		} catch (UnknownHostException e) {
			System.out.println("Host not recognized. Could not connect.");
			return null;
		} catch (IOException e) {
			System.out.println("Could not connect.");
			return null;
		}
		    
		//Gets object input/output streams
		ObjectInputStream in;
		ObjectOutputStream out;
		try {
			in = new ObjectInputStream(soc.getInputStream());
			out = new ObjectOutputStream(soc.getOutputStream());
		} catch (IOException e) {
			System.out.println("Communications could not be established with registry.");
			return null;
		}
	
		//Makes a lookup request to the registry
		try {
			out.writeObject("lookup");
			out.flush();
			out.writeObject(serviceName);
		} catch (IOException e1) {
			System.out.println("Error communicating with registry.");
			return null;
		}
		
		//Reads the result returned by the registry
		RemoteObjectReference ror;
		try {
			ror = (RemoteObjectReference) in.readObject();
		} catch (IOException e) {
			System.out.println("Error communicating with registry.");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Error communicating with registry.");
			return null;
		}
	
		//Closes the socket
		try {
			soc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		//Returns the RemoteObjectReference given by the registry
		return ror;
    }

    /** rebind(String serviceName, RemoteObjectReference ror)
     * 
     * Stores an object in the registry under a given serviceName
     */
    public void rebind(String serviceName, RemoteObjectReference ror) {
		
    	//Opens socket a host/port
    	//Assumes that LocateRegistry has determined a registry to exist there
		Socket soc;
		try {
			soc = new Socket(host, port);
		} catch (UnknownHostException e) {
			System.out.println("Host not recognized. Could not connect.");
			return;
		} catch (IOException e) {
			System.out.println("Could not connect.");
			return;
		}
		    
		//Creates object output stream
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(soc.getOutputStream());
		} catch (IOException e) {
			System.out.println("Communications could not be established with registry.");
			return;
		}
	
		//Writes the rebind request with serviceName and RemoteObjectReference
		try {
			out.writeObject("rebind");
			out.flush();
			out.writeObject(serviceName);
			out.flush();
			out.writeObject(ror);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Error communicating with registry.");
			return;
		}
	
		//Closes the socket
		try {
			soc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
