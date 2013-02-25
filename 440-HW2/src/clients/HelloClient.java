package clients;

import examples.HelloServer;
import references.RemoteObjectReference;
import stub.RemoteException440;

public class HelloClient {

	/**
	 * @param args
	 * @throws RemoteException440 
	 */
	// the main takes three arguments:
    // (0) a host. 
    // (1) a port.
    // (2) a service name.
	public static void main(String[] args) throws RemoteException440 {
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		String serviceName = args[2];

		// locate the registry and get ror.
		SimpleRegistry sr = LocateSimpleRegistry.getRegistry(host, port);
		RemoteObjectRef ror = sr.lookup(serviceName);
		
		RemoteObjectReference ror = new RemoteObjectReference("128.128.128.128", 1234, 0xdeadbeef, "HelloInterface");
		HelloServer hi = (HelloServer) ror.localize();
		System.out.println(hi.sayHello("Billy Bob"));
	}

}
