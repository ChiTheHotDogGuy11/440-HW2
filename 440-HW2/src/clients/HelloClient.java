package clients;

import java.net.UnknownHostException;

import examples.GoodbyeServer;
import examples.HelloServer;
import references.RemoteObjectReference;
import registry.LocateSimpleRegistry;
import registry.Registry440;
import stub.RemoteException440;

public class HelloClient {

	/**
	 * @param args
	 * @throws RemoteException440 
	 * @throws UnknownHostException 
	 */
	// the main takes three arguments:
    // (0) a host. 
    // (1) a port.
    // (2) a service name.
	public static void main(String[] args) throws RemoteException440, UnknownHostException {
		/*String host = args[0];
		int port = Integer.parseInt(args[1]);
		String serviceName = args[2];*/
		
		String host = "128.237.198.183";
		int port = 1233;

		// locate the registry and get ror.
		Registry440 sr = LocateSimpleRegistry.getRegistry(host, port);
		RemoteObjectReference rorHello = sr.lookup("sillyBilly");
		RemoteObjectReference rorGoodbye = sr.lookup("Jackson");
		
		if (rorHello == null || rorGoodbye == null) {
			System.out.println("FUCK!");
			return;
		}
		
		HelloServer hi = (HelloServer) rorHello.localize();
		GoodbyeServer g = (GoodbyeServer) rorGoodbye.localize();
		System.out.println(hi.sayHello("Tyler", g));
	}
}
