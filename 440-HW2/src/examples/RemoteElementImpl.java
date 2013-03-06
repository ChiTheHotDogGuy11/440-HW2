package examples;

import communication.RMI440;

import stub.Remote440;

public class RemoteElementImpl extends ElementImpl implements Remote440 {

	public static void main(String[] args) {
		RMI440 serverInst = new RMI440("128.237.207.225", 1233, "elem1", new RemoteElementImpl(235, "Tyler"));
		serverInst.run();
	}
	
	public RemoteElementImpl(int value, String identifier) {
		super(value, identifier);
	}

}
