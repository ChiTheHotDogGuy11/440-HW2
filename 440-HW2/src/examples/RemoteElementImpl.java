package examples;

import communication.RMI440;

public class RemoteElementImpl implements RemoteElement {
	private int value;
	private String identifier;

	public static void main(String[] args) {
		RMI440 serverInst = new RMI440("128.237.207.225", 1233, "elem1", new RemoteElementImpl(235, "Tyler"));
		serverInst.run();
	}
	
	public RemoteElementImpl(int value, String identifier) {
		this.value = value;
		this.identifier = identifier;
	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public String identify() {
		return ("I am REMOTE element " + identifier + " and my value is " + value);
	}
}
