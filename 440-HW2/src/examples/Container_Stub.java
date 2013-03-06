package examples;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import communication.RMIMessage;

import references.RemoteObjectReference;
import stub.Remote440;
import stub.RemoteStub440;

public final class Container_Stub extends RemoteStub440 
	implements Container, Remote440 {

	@Override
	public double getAverage(Element[] elems) {
		Socket sock;
		ObjectOutputStream out;
		ObjectInputStream in;
		RMIMessage methodRequest = null;
		Object[] params = new Object[1];
		params[0] = elems;
		
		try {
			methodRequest = new RMIMessage("getAverage", params, super.getObjectKey());
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
			
			return (Double) resultMessage.getReturnValue();
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
	public Element minElem(Element[] elems) {
		Socket sock;
		ObjectOutputStream out;
		ObjectInputStream in;
		RMIMessage methodRequest = null;
		Object[] params = new Object[1];
		params[0] = elems;
		
		try {
			methodRequest = new RMIMessage("minElem", params, super.getObjectKey());
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
			Object gbs = resultMessage.getReturnValue();
			if (gbs instanceof RemoteObjectReference) {
				return ((RemoteElement_Stub)((RemoteObjectReference)gbs).localize());
			}
			else return (Element)gbs;
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

	@Override
	public String identifyElems(Element[] elems) {
		Socket sock;
		ObjectOutputStream out;
		ObjectInputStream in;
		RMIMessage methodRequest = null;
		Object[] params = new Object[1];
		params[0] = elems;
		
		try {
			methodRequest = new RMIMessage("identifyElems", params, super.getObjectKey());
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

	@Override
	public String shoutout(int value) {
		Socket sock;
		ObjectOutputStream out;
		ObjectInputStream in;
		RMIMessage methodRequest = null;
		Object[] params = new Object[1];
		params[0] = value;
		
		try {
			methodRequest = new RMIMessage("shoutout", params, super.getObjectKey());
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
			
			return (String) resultMessage.getReturnValue();
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
