package registry;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import communication.RMIMessage;

import references.RemoteObjectReference;

public class RegistryImpl implements Registry440 {
	private HashMap<String, RemoteObjectReference> hm = new HashMap<String, RemoteObjectReference>();
	private final static int port = 1233;
	
	public RegistryImpl() {
	}

	@Override
	public RemoteObjectReference lookup(String serviceName) {
		return hm.get(serviceName);
	}

	@Override
	public void rebind(String serviceName, RemoteObjectReference ror) {
		if (serviceName == null || ror == null) return;
		hm.put(serviceName, ror);
		System.out.println("Finishing rebind.");
	}
	
	public static void main(String[] args) {
		RegistryImpl registry = new RegistryImpl();
		// create a socket.
		ServerSocket serverSoc;
		try {
			serverSoc = new ServerSocket(port);
		} catch (IOException e1) {
			System.out.println("Server Socket could not be created.");
			return;
		}
		while (true) {
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
