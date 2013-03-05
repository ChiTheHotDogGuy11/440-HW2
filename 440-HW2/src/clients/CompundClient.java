package clients;

import java.net.UnknownHostException;

import references.RemoteObjectReference;
import registry.LocateSimpleRegistry;
import registry.Registry440;
import stub.RemoteException440;
import examples.CompoundServer;

public class CompundClient {

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
		RemoteObjectReference rorCompound = sr.lookup("Jimmy");
		
		if (rorCompound == null) {
			System.out.println("FUCK!");
			return;
		}
		
		CompoundServer cs = (CompoundServer) rorCompound.localize();
		System.out.println(cs.compoundGoodbye().sayGoodbye());
	}
}
