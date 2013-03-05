package examples;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import communication.RMIMessage;

import references.RemoteObjectReference;
import stub.Remote440;
import stub.RemoteException440;
import stub.RemoteStub440;

public final class HelloServer_Stub extends RemoteStub440
	implements HelloServer, Remote440 {

	public HelloServer_Stub() {
	}
	
	public HelloServer_Stub(RemoteObjectReference ROR) {
		super(ROR);
	}
	
	public String sayHello(String nameOfPerson, GoodbyeServer g) throws RemoteException440 {
		Socket sock;
		ObjectOutputStream out;
		ObjectInputStream in;
		RMIMessage methodRequest = null;
		Object[] params = new Object[2];
		params[0] = nameOfPerson;
		params[1] = g;
		
		try {
			methodRequest = new RMIMessage("sayHello", params, super.getObjectKey());
			sock = new Socket(super.getIP(), super.getPortName());
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());	
			out.writeObject(methodRequest);
			RMIMessage resultMessage = (RMIMessage)(in.readObject());
			
			out.close();
			in.close();
			sock.close();
			
			resultMessage.printStackTraces();
			return (String)resultMessage.getReturnValue();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Result didn't have the right type.");
			e.printStackTrace();
		}

		return "SHOULD NEVER GET HERE";
	}
	
}