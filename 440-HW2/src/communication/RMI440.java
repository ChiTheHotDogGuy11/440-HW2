package communication;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import references.RORTable440;
import references.RemoteObjectReference;
import registry.SimpleRegistry;

public class RMI440 {
    static String host;
    static int port;

    // It will use a hash table, which contains ROR together with
    // reference to the remote object.
    // As you can see, the exception handling is not done at all.
    public static void main(String args[]) throws Exception {
		String InitialClassName = "examples.HelloServerImpl";
		/*String registryHost = args[1];
		int registryPort = Integer.parseInt(args[2]);	
		String serviceName = args[3];*/
	
		// it should have its own port. assume you hardwire it.
		host = "128.237.198.29";
		port = 1234;
		
		//create the registry
		//SimpleRegistry reg = new SimpleRegistry(registryHost, registryPort);
	
		// it now have two classes from MainClassName: 
		// (1) the class itself (say ZipCpdeServerImpl) and
		// (2) its skeleton.
		Class<?> initialclass = Class.forName(InitialClassName);
		//Class<?> initialskeleton = Class.forName(InitialClassName+"_skel");
		
		// you should also create a remote object table here.
		// it is a table of a ROR and a skeleton.
		// as a hint, I give such a table's interface as RORtbl.java.
		RORTable440 tbl = new RORTable440();
		
		// after that, you create one remote object of initialclass.
		Object o = initialclass.newInstance();
		
		// then register it into the table.
		RemoteObjectReference initROR = tbl.addObj(host, port, o);
		//reg.rebind(serviceName, initROR);
	
		// create a socket.
		ServerSocket serverSoc = new ServerSocket(port);
	
		// Now we go into a loop.
		// Look at rmiregistry.java for a simple server programming.
		// The code is far from optimal but in any way you can get basics.
		// Actually you should use multiple threads, or this easily
		// deadlocks. But for your implementation I do not ask it.
		// For design, consider well.
		while (true) {
			// (1) receives an invocation request.
			// (2) creates a socket and input/output streams.
			// (3) gets the invocation, in martiallled form.
			// (4) gets the real object reference from tbl.
			// (5) Either:
			//      -- using the interface name, asks the skeleton,
			//         together with the object reference, to unmartial
			//         and invoke the real object.
			//      -- or do unmarshalling directly and involkes that
			//         object directly.
			// (6) receives the return value, which (if not marshalled
			//     you should marshal it here) and send it out to the 
			//     the source of the invoker.
			// (7) closes the socket.
			
			Socket soc = serverSoc.accept();
			ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
			
			RMIMessage message = (RMIMessage) ois.readObject();
			int key = message.getObjectKey();
			Object obj = tbl.findObj(key);
			System.out.println("Object found!");
		}
    }
}
