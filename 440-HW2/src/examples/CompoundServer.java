package examples;

import stub.Remote440;
import stub.RemoteException440;

public interface CompoundServer extends Remote440{
	public GoodbyeServer compoundGoodbye() throws RemoteException440;
}
