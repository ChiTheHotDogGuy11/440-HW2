package examples;


import examples.HelloInterface;
import references.RemoteObjectReference;
import stub.Remote440;
import stub.RemoteException440;
import stub.RemoteStub440;

public final class HelloInterface_Stub extends RemoteStub440
	implements HelloInterface, Remote440 {

	public HelloInterface_Stub() {
	}
	
	public HelloInterface_Stub(RemoteObjectReference ROR) {
		super(ROR);
	}
	
	public String sayHello(String nameOfPerson) throws RemoteException440 {
		return "sayHello called on stub!";
	}
	
}