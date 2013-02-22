package clients;

import references.RemoteObjectReference;
import stub.RemoteException440;
import examples.HelloInterface;

public class HelloClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RemoteObjectReference ror = new RemoteObjectReference("128.128.128.128", 1234, 1, "HelloInterface");
		HelloInterface hi = (HelloInterface) ror.localize();
		
		try {
			System.out.println(hi.sayHello("Tyler"));
		} catch (RemoteException440 e) {
			e.printStackTrace();
		}
	}

}
