package examples;

import communication.RMI440;

import stub.RemoteException440;

public class Compound implements CompoundServer {
	
	public GoodbyeServer compoundGoodbye() throws RemoteException440 {
		GoodbyeServer gb = new Goodbye();
		try {
			System.out.println(gb.sayGoodbye());
		} catch (RemoteException440 e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gb;
	}

}
