package examples;

import stub.RemoteException440;

public class Hello implements HelloInterface {

	public String sayHello(String nameOfPerson) {
		return "Hey there " + nameOfPerson + ", my buddy-ole pal.";
	}
}
