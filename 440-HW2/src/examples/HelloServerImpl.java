package examples;

import stub.RemoteException440;

public class HelloServerImpl implements HelloServer {
	
	public String sayHello(String name) throws RemoteException440
	  {
		if (name.equals("")) {
			throw new RemoteException440("Empty String");
		}
	    return "Hello World! Hello " + name; 
	  }
}
