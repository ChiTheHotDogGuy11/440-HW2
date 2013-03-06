package examples;

import communication.RMI440;

public class Goodbye implements GoodbyeServer {

	public void main(String[] args) {
		RMI440 serverInst = new RMI440("128.237.207.225", 1233, "elem1", new Goodbye());
		serverInst.run();
	}
	
	public String sayGoodbye() {
		return "Goodbye brosef montana.";
	}
}
