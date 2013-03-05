package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import references.RORTable440;
import references.RemoteObjectReference;
import registry.LocateRegistry;
import registry.Registry440;
import stub.Remote440;
import stub.RemoteStub440;

/** RMI440
 * 
 * Communications Class
 * Takes four arguments at Command Line:
 * 1.) InitialClassName - name of the initial remote object to be accessed
 * 2.) registryHost - the host where the global registry resides
 * 3.) registryPort - the port where the global registry resides
 * 4.) serviceName - the name to be bound to the initialClass in the registry
 * 
 * This class establishes a connection with the global registry, creates a
 * local instance of the initial class and binds it to the registry, then
 * listens for requests from a client to call a method on a local object.
 * This request is unmarshalled, completed, and the return value (or exceptions)
 * is packaged in an RMIMessage and sent back to the client.
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 */
public class RMI440 {
	
	//Host and port on which the server resides
    static String host;
    static int port;

    public static void main(String args[]) {
		String InitialClassName = "examples.HelloServerImpl";
		/*String registryHost = args[1];
		int registryPort = Integer.parseInt(args[2]);	
		String serviceName = args[3];*/
		
		String registryHost = "128.237.185.147";
		int registryPort = 1233;
		String serviceName = "sillyBilly";
	
		//TODO automate setting of host
		host = "128.237.114.224";
		port = 1234;	//hardwired value
		
		//Locate the global registry
		Registry440 reg = LocateRegistry.getRegistry(registryHost, registryPort);
	
		//Attempts to find the Class tied to InitialClassName
		Class<?> initialclass;
		try {
			initialclass = Class.forName(InitialClassName);
		} catch (ClassNotFoundException e1) {
			System.out.println("Initial Class does not exist.");
			return;
		}
		
		//Creates a RORTable to map RemoteObjectReference to local objects
		RORTable440 tbl = new RORTable440();
		
		//Attempts to create a local instantiation of the initialClass
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
		
		/* Registers the above local instantiation in the RORTable and adds it
		 * to the registry */
		RemoteObjectReference initROR = tbl.addObj(host, port, o);
		reg.rebind(serviceName, initROR);
	
		//Creates a ServerSocket to listen for requests
		ServerSocket serverSoc;
		try {
			serverSoc = new ServerSocket(port);
		} catch (IOException e1) {
			System.out.println("Server Socket could not be created.");
			return;
		}
	
		//TODO Multiple threads
		/* Listens for requests and takes the following action
		 * 
		 * 1.) Receives a invocation request
		 * 2.) Creates a socket and object input/output streams
		 * 3.) Gets the RMIMessage and unpackages the request
		 * 4.) Gets the local object from the RORTable
		 * 5.) Unmarshalls the request and invokes the method on the local object
		 * 6.) Marshalls the return value (or exceptions) into the RMIMessage
		 * 7.) Sends RMIMessage to client
		 * 8.) Closes socket
		 */
		while (true) {
			
			//Accepts a connection and creates object input/output streams
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
			
			//Gets local object from RORTable
			int key = message.getObjectKey();
			Object obj = tbl.findObj(key);
			
			//Determines the parameters of the method
			Object[] parameters = message.getParemeters();
			Class[] paramClasses = new Class[parameters.length];
			for (int i = 0; i < parameters.length; i++) {
				if (parameters[i] instanceof RemoteObjectReference) {
					RemoteObjectReference paramROR = (RemoteObjectReference)parameters[i];
					RemoteStub440 newParam = paramROR.localize();
					parameters[i] = newParam;
					try {
						paramClasses[i] = Class.forName(paramROR.getRIName());
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else paramClasses[i] = parameters[i].getClass();
			}

			//Uses reflection to obtain method to invoke on the local object
			Method method = null;
			try {
				method = obj.getClass().getMethod(message.getMethodName(), paramClasses);
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Invoke method on the local object and marshall result
			try {
				Object result = method.invoke(obj, parameters);
				if (result instanceof Remote440) {
				  if (result instanceof RemoteStub440) {
					  message.setReturnValue(((RemoteStub440) result).getROR());
				  } else {
					  message.setReturnValue(tbl.addObj(host, port, result)); 
				  }
				} else {
				  message.setReturnValue(result);
				}
			} catch (InvocationTargetException e) {
				message.setException(e.getCause());
			} catch (IllegalArgumentException e) {
				message.setException(e.getCause());
			} catch (IllegalAccessException e) {
				message.setException(e.getCause());
			}
			
			//Write marshalled result (in RMIMessage) to output stream
			try {
				oos.writeObject(message);
			} catch (IOException e) {
				System.out.println("Return message could not be written.");
				return;
			}
			
			//Close socket
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
