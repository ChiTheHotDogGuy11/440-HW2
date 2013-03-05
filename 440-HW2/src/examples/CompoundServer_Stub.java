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

public final class CompoundServer_Stub extends RemoteStub440
	implements CompoundServer, Remote440 {

	public CompoundServer_Stub() {
	}
	
	public CompoundServer_Stub(RemoteObjectReference ROR) {
		super(ROR);
	}
	
	public GoodbyeServer compoundGoodbye() throws RemoteException440 {
		Socket sock;
		ObjectOutputStream out;
		ObjectInputStream in;
		RMIMessage methodRequest = null;
		Object[] params = new Object[0];
		
		try {
			methodRequest = new RMIMessage("compoundGoodbye", params, super.getObjectKey());
			sock = new Socket(super.getIP(), super.getPortName());
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());	
			out.writeObject(methodRequest);
			RMIMessage resultMessage = (RMIMessage)(in.readObject());
			
			out.close();
			in.close();
			sock.close();
			
			resultMessage.printStackTraces();
			GoodbyeServer_Stub gbs = (GoodbyeServer_Stub)((RemoteObjectReference)resultMessage.getReturnValue()).localize();
			return gbs;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Result didn't have the right type.");
			e.printStackTrace();
		}
		return null;
	}
	
}