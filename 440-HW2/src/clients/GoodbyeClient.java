package clients;

import java.net.UnknownHostException;

import references.RemoteObjectReference;
import registry.LocateRegistry;
import registry.Registry440;
import stub.RemoteException440;
import examples.GoodbyeServer;
import examples.HelloServer;

public class GoodbyeClient {
	public static void main(String[] args) throws RemoteException440, UnknownHostException {
		/*String host = args[0];
		int port = Integer.parseInt(args[1]);
		String serviceName = args[2];*/
		
		String host = "128.237.207.225";
		int port = 1233;

		// locate the registry and get ror.
		Registry440 sr = LocateRegistry.getRegistry(host, port);
		RemoteObjectReference rorHello = sr.lookup("elem1");
		
		if (rorHello == null) {
			System.out.println("FUCK!");
			return;
		}
		
		GoodbyeServer g = (GoodbyeServer) rorHello.localize();
		System.out.println(g.sayGoodbye());
	}
}
