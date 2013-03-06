package clients;


import java.net.UnknownHostException;

import examples.Container;
import examples.Element;
import examples.ElementImpl;

import references.RemoteObjectReference;
import registry.LocateRegistry;
import registry.Registry440;
import stub.RemoteException440;

public class ContainerClient {
	public static void main(String[] args) throws RemoteException440, UnknownHostException {
		/*String host = args[0];
		int port = Integer.parseInt(args[1]);*/
		
		String host = "128.237.198.183";
		int port = 1233;

		// locate the registry and get ror.
		Registry440 sr = LocateRegistry.getRegistry(host, port);
		RemoteObjectReference containerROR = sr.lookup("container");
		RemoteObjectReference elem1ROR = sr.lookup("elem1");
		RemoteObjectReference elem2ROR = sr.lookup("elem2");
		
		Element elem1 = (Element)elem1ROR.localize();
		Element elem2 = (Element)elem2ROR.localize();
		Element elem3 = new ElementImpl(1500, "Jackson");
		Element elem4 = new ElementImpl(6, "Silly Beans");
		Container container = (Container)containerROR.localize();
		
		Element[] elems = new Element[]{elem1, elem2, elem3, elem4};
		System.out.println(container.identifyElems(elems));
		System.out.println("Average: " + container.getAverage(elems));
		System.out.println("Minimum Element:" + container.minElem(elems).identify());
		
		container.minElem(null);
		
	}
}
