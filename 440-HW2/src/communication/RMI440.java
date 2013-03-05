package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import references.RORTable440;
import references.RemoteObjectReference;
import registry.LocateSimpleRegistry;
import registry.Registry440;
import stub.RemoteStub440;

public class RMI440 {
    static String host;
    static int port;

    // It will use a hash table, which contains ROR together with
    // reference to the remote object.
    // As you can see, the exception handling is not done at all.
    public static void main(String args[]) {
		String InitialClassName = "examples.HelloServerImpl";
		/*String registryHost = args[1];
		int registryPort = Integer.parseInt(args[2]);	
		String serviceName = args[3];*/
		
		String registryHost = "128.237.198.183";
		int registryPort = 1233;
		String serviceName = "sillyBilly";
	
		// it should have its own port. assume you hardwire it.
		host = "128.237.114.224";
		port = 1234;
		
		//create the registry
		Registry440 reg = LocateSimpleRegistry.getRegistry(registryHost, registryPort);
	
		// it now have two classes from MainClassName: 
		// (1) the class itself (say ZipCpdeServerImpl) and
		// (2) its skeleton.
		Class<?> initialclass;
		try {
			initialclass = Class.forName(InitialClassName);
		} catch (ClassNotFoundException e1) {
			System.out.println("Initial Class does not exist.");
			return;
		}
		
		// you should also create a remote object table here.
		// it is a table of a ROR and a skeleton.
		// as a hint, I give such a table's interface as RORtbl.java.
		RORTable440 tbl = new RORTable440();
		
		// after that, you create one remote object of initialclass.
		Object o;
		try {
			o = initialclass.newInstance();
		} catch (InstantiationException e1) {
			System.out.println("Initial Class could not be created.");
			return;
		} catch (IllegalAccessException e1) {
			System.out.println("Initial Class could not be accessed.");
			return;
		}
		
		// then register it into the table.
		RemoteObjectReference initROR = tbl.addObj(host, port, o);
		reg.rebind(serviceName, initROR);
	
		// create a socket.
		ServerSocket serverSoc;
		try {
			serverSoc = new ServerSocket(port);
		} catch (IOException e1) {
			System.out.println("Server Socket could not be created.");
			return;
		}
	
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
			
			Socket soc;
			ObjectOutputStream oos;
			ObjectInputStream ois;
			RMIMessage message;
			try {
				soc = serverSoc.accept();
				oos = new ObjectOutputStream(soc.getOutputStream());
				ois = new ObjectInputStream(soc.getInputStream());
				message = (RMIMessage) ois.readObject();
			} catch (IOException e1) {
				System.out.println("Socket connection error");
				return;
			} catch (ClassNotFoundException e) {
				System.out.println("Class of accepted object could not be found.");
				return;
			}
			
			
			int key = message.getObjectKey();
			Object obj = tbl.findObj(key);
			
			Object[] parameters = message.getParemeters();
			Class[] paramClasses = new Class[parameters.length];
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] instanceof RemoteObjectReference) {
					RemoteObjectReference paramROR = (RemoteObjectReference)parameters[i];
					RemoteStub440 newParam = paramROR.localize();
					parameters[i] = newParam;
				}
				paramClasses[i] = parameters[i].getClass();
			}

			try {
				Method method = obj.getClass().getMethod(message.getMethodName(), paramClasses);
				Object result = method.invoke(obj, message.getParemeters());
				message.setReturnValue(result);
			} catch (Exception e) {
				e.printStackTrace();
				message.addException(e);
			}
			
			try {
				oos.writeObject(message);
			} catch (IOException e) {
				System.out.println("Return message could not be written.");
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
