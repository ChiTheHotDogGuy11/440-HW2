package examples;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

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
		Socket sock;
		ObjectOutputStream out;
		ObjectInputStream in;
		try {
			sock = new Socket(super.getIP(), super.getPortName());
			System.out.println("HERE");
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());	
			out.writeObject("sayHello");
			out.writeObject(nameOfPerson);
			out.writeObject("END OF INPUT");
			String result = (String)(in.readObject());
			out.close();
			in.close();
			sock.close();
			return result;
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