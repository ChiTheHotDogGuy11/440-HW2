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
		BufferedReader in;
		PrintWriter out;
		try {
			in = new BufferedReader(new InputStreamReader (soc.getInputStream()));
			out = new PrintWriter(soc.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Communications could not be established with registry.");
			return null;
		}
	
		// it is locate request, with a service name.
		out.println("lookup");
		out.println(serviceName);
	
		// branch according to the answer.
		String res;
		try {
			res = in.readLine();
		} catch (IOException e) {
			System.out.println("Error communicating with registry.");
			return null;
		}
		RemoteObjectReference ror;
	
		if (res.equals("found")) {
			System.out.println("it is found!.");
	
			// receive ROR data, witout check.
			String ro_IPAdr;
			int ro_PortNum;
			int ro_ObjKey;
			String ro_InterfaceName;
			try {
				ro_IPAdr = in.readLine();
				ro_PortNum = Integer.parseInt(in.readLine());
				ro_ObjKey = Integer.parseInt(in.readLine());
				ro_InterfaceName = in.readLine();
			} catch (IOException e) {
				System.out.println("Error communicating with registry.");
				return null;
			}
	
			// make ROR.
			ror = new RemoteObjectReference(ro_IPAdr, ro_PortNum, ro_ObjKey, ro_InterfaceName);
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
		//BufferedReader in;
		PrintWriter out;
		try {
			//in = new BufferedReader(new InputStreamReader (soc.getInputStream()));
			out = new PrintWriter(soc.getOutputStream(), true);
		} catch (IOException e) {
			System.out.println("Communications could not be established with registry.");
			return;
		}
	
		// it is a rebind request, with a service name and ROR.
		out.println("rebind");
		out.println(serviceName);
		out.println(ror.getIP());
		out.println(ror.getPort()); 
		out.println(ror.getObjectKey());
		out.println(ror.getRIName());
	
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
