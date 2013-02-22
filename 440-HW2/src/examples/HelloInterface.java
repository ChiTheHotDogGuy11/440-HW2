package examples;

import stub.Remote440;
import stub.RemoteException440;

public interface HelloInterface extends Remote440{
	public String sayHello(String nameOfPerson) throws RemoteException440;
}
