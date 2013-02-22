package clients;

import references.RemoteObjectReference;

public class HelloClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RemoteObjectReference ror = new RemoteObjectReference("128.128.128.128", 1234, 0xdeadbeef, "HelloInterface440");
		HelloInterface hi = ror.localize();
		System.out.println(hi.sayHello(args[0]));
	}

}
