package examples;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import communication.RMIMessage;

import stub.Remote440;
import stub.RemoteStub440;

public final class RemoteElement_Stub extends RemoteStub440
	implements Element, Remote440 {

	@Override
	public int getValue() {
		Socket sock;
		ObjectOutputStream out;
		ObjectInputStream in;
		RMIMessage methodRequest = null;
		Object[] params = new Object[0];
		Class[] classes = new Class[0];
		
		try {
			methodRequest = new RMIMessage("getValue", params, classes, super.getObjectKey());
			sock = new Socket(super.getIP(), super.getPortName());
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());	
			out.writeObject(methodRequest);
			RMIMessage resultMessage = (RMIMessage)(in.readObject());
			
			out.close();
			in.close();
			sock.close();
			
			Throwable e = resultMessage.getException();
			if (e != null) {
				throw e;
			}
			
			return (Integer) resultMessage.getReturnValue();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Result didn't have the right type.");
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}

	@Override
	public String identify() {
		Socket sock;
		ObjectOutputStream out;
		ObjectInputStream in;
		RMIMessage methodRequest = null;
		Object[] params = new Object[0];
		Class[] classes = new Class[0];
		
		try {
			methodRequest = new RMIMessage("identify", params, classes, super.getObjectKey());
			sock = new Socket(super.getIP(), super.getPortName());
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());	
			out.writeObject(methodRequest);
			RMIMessage resultMessage = (RMIMessage)(in.readObject());
			
			out.close();
			in.close();
			sock.close();
			
			Throwable e = resultMessage.getException();
			if (e != null) {
				throw e;
			}
			
			return (String)resultMessage.getReturnValue();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Result didn't have the right type.");
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
