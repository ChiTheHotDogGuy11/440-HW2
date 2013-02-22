package clients;

import examples.HelloInterface;
import references.RemoteObjectReference;
import stub.RemoteException440;

public class HelloClient {

	/**
	 * @param args
	 * @throws RemoteException440 
	 */
	public static void main(String[] args) throws RemoteException440 {
		RemoteObjectReference ror = new RemoteObjectReference("128.128.128.128", 1234, 0xdeadbeef, "HelloInterface");
		HelloInterface hi = (HelloInterface) ror.localize();
		System.out.println(hi.sayHello("Billy Bob"));
	}

}
