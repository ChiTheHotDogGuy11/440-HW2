package examples;

import communication.RMI440;

import stub.RemoteException440;

public class HelloServerImpl implements HelloServer {
	
	public String sayHello(String name, GoodbyeServer g) throws RemoteException440
	  {
		if (name.equals("")) {
			throw new RemoteException440("Empty String");
		}
	    return "Hello World! Hello " + name + ". " + g.sayGoodbye(); 
	  }
}
