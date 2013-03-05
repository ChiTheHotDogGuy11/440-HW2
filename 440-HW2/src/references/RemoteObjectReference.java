package references;

import java.io.Serializable;

import stub.RemoteStub440;

public class RemoteObjectReference implements Serializable {
	
	private String IP_address;
	private int port;
	private int object_key;
	private String RI_name;
	
	public RemoteObjectReference(String ip, int port, int obj_key, String RI_name) {
		this.IP_address = ip;
		this.port = port;
		this.object_key = obj_key;
		this.RI_name = RI_name;
	}
	
	public RemoteStub440 localize() {
		String stub_name = RI_name + "_Stub";
		Class<?> c = null;
		RemoteStub440 o = null;
		
		try {
			c = Class.forName(stub_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			o = (RemoteStub440)c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		o.setROR(this);
		return o;
	}
	
	public String getIP() {
		return IP_address;
	}
	
	public int getPort() {
		return port;
	}

	public int getObjectKey() {
		return object_key;
	}
	
	public String getRIName() {
		return RI_name;
	}
}
