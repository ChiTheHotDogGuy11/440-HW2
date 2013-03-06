package registry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import references.RemoteObjectReference;

/** RegistryImpl
 * 
 * This class, which implements Registry440, is the actual registry that will
 * be running on some host/port on which the clients and servers can access
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 */
public class RegistryImpl implements Registry440 {
	
	//The table mapping service names to RemoteObjectReferences
	private HashMap<String, RemoteObjectReference> hm = new HashMap<String, RemoteObjectReference>();
	
	//Hardwired port on which this service will run
	private final static int port = 1233;
	
	public RegistryImpl() {
	}

	@Override
	/** lookup(String serviceName)
	 * 
	 * @return RemoteObjectReference represented by String serviceName
	 */
	public RemoteObjectReference lookup(String serviceName) {
		return hm.get(serviceName);
	}

	@Override
	/** rebind(String serviceName, RemoteObjectReference ror)
	 * 
	 * Inserts the RemoteObjectReference into the registry table with lookup key
	 * serviceName
	 */
	public void rebind(String serviceName, RemoteObjectReference ror) {
		if (serviceName == null || ror == null) return;
		hm.put(serviceName, ror);
		System.out.println("Finishing rebind.");
	}
	
	/** main(String[] args)
	 * 
	 * Main function which initiates the registry and waits for requests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Create the registry
		RegistryImpl registry = new RegistryImpl();
		
		//Create a ServerSocket that will listen for requests
		ServerSocket serverSoc;
		try {
			serverSoc = new ServerSocket(port);
		} catch (IOException e1) {
			System.out.println("Server Socket could not be created.");
			return;
		}
		
		/* While running, listens for a request and does the following:
		 * 
		 * 1.) Accepts the request
		 * 2.) Opens a socket and creates object input/output streams
		 * 3.) Read in the command
		 * 4.) Determine what to do based on the command
		 * 5.) Do that action (lookup, rebind, identify)
		 * 6.) If lookup or identify, return result
		 * 7.) Close the connection
		 */
		//TODO Make multithreaded
		while (true) {
			
			//Creates a socket and object input/output streams, accepts command
			Socket soc;
			ObjectOutputStream oos;
			ObjectInputStream ois;
			String commandToRun;
			try {
				soc = serverSoc.accept();
				oos = new ObjectOutputStream(soc.getOutputStream());
				ois = new ObjectInputStream(soc.getInputStream());
				commandToRun = (String)ois.readObject();
				if (commandToRun.equals("lookup")) {
					System.out.println("lookup called!");
					String lookupTerm = (String)ois.readObject();
					RemoteObjectReference result = registry.lookup(lookupTerm);
					oos.writeObject(result);
				}
				else if (commandToRun.equals("rebind")) {
					System.out.println("rebind called!");
					String newName = (String)ois.readObject();
					RemoteObjectReference objToRename = (RemoteObjectReference)ois.readObject();
					registry.rebind(newName, objToRename);
				}
				else if (commandToRun.equals("who are you?")) {
					oos.writeObject("I am a registry.");
				}
			} catch (IOException e1) {
				System.out.println("Socket connection error");
				return;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			
			//Closes connection
			try {
				ois.close();
				oos.close();
				soc.close();
			} catch (IOException e) {
				System.out.println("Error closing connection.");
				return;
			}
		}
	}
}
