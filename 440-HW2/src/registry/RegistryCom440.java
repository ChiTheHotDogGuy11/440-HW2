package registry;

import java.net.*;
import java.io.*;

import references.RemoteObjectReference;

public class RegistryCom440 implements Registry440 
{ 
    // registry holds its port and host, and connects to it each time. 
    String Host;
    int Port;
    
    // ultra simple constructor.
    public RegistryCom440(String IPAdr, int PortNum) {
		Host = IPAdr;
		Port = PortNum;
    }

    // returns the ROR (if found) or null (if else)
    public RemoteObjectReference lookup(String serviceName) {
		// open socket.
		// it assumes registry is already located by locate registry.
		// you should usually do try-catch here (and later).
		Socket soc;
		try {
			soc = new Socket(Host, Port);
		} catch (UnknownHostException e) {
			System.out.println("Host not recognized. Could not connect.");
			return null;
		} catch (IOException e) {
			System.out.println("Could not connect.");
			return null;
		}
		    
		// get TCP streams and wrap them. 
		ObjectInputStream in;
		ObjectOutputStream out;
		try {
			in = new ObjectInputStream(soc.getInputStream());
			out = new ObjectOutputStream(soc.getOutputStream());
		} catch (IOException e) {
			System.out.println("Communications could not be established with registry.");
			return null;
		}
	
		// it is locate request, with a service name.
		try {
			out.writeObject("lookup");
			out.flush();
			out.writeObject(serviceName);
		} catch (IOException e1) {
			System.out.println("Error communicating with registry.");
			return null;
		}
	
		// branch according to the answer.
		String res;
		try {
			res = (String) in.readObject();
		} catch (IOException e) {
			System.out.println("Error communicating with registry.");
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Error communicating with registry.");
			return null;
		}
		
		RemoteObjectReference ror;
		if (res.equals("found")) {

			try {
				ror = (RemoteObjectReference) in.readObject();
			} catch (IOException e) {
				System.out.println("Error communicating with registry.");
				return null;
			} catch (ClassNotFoundException e) {
				System.out.println("Error communicating with registry.");
				return null;
			}
		} else {
			System.out.println("it is not found!.");
			ror = null;
		}
	
		// close the socket.
		try {
			soc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		// return ROR.
		return ror;
    }

    // rebind a ROR. ROR can be null. again no check, on this or whatever. 
    // I hate this but have no time.
    public void rebind(String serviceName, RemoteObjectReference ror) {
		// open socket. same as before.
		Socket soc;
		try {
			soc = new Socket(Host, Port);
		} catch (UnknownHostException e) {
			System.out.println("Host not recognized. Could not connect.");
			return;
		} catch (IOException e) {
			System.out.println("Could not connect.");
			return;
		}
		    
		// get TCP streams and wrap them.
		ObjectOutputStream out;
		try {
			//in = new BufferedReader(new InputStreamReader (soc.getInputStream()));
			out = new ObjectOutputStream(soc.getOutputStream());
		} catch (IOException e) {
			System.out.println("Communications could not be established with registry.");
			return;
		}
	
		// it is a rebind request, with a service name and ROR.
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
		
		/* it also gets an ack, but this is not used.
		try {
			String ack = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	
		// close the socket.
		try {
			soc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
