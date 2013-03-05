package examples;

import java.rmi.RemoteException;

import stub.Remote440;

public interface CompoundServer extends Remote440{
	public GoodbyeServer compoundGoodbye() throws RemoteException;
}
