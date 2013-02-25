package examples;

import stub.RemoteException440;

public class HelloServerImpl implements HelloServer {
	
	public String sayHello(String name) throws RemoteException440
	  {
	    return "Hello World! Hello " + name; 
	  }
}
