package references;

public class RemoteObjectReference {
	
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
	
	public Object localize() {
		String stub_name = RI_name + "_stub";
		Class<?> c = null;
		Object o = null;
		
		try {
			c = Class.forName(stub_name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			o = c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
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
