package registry;

import references.RemoteObjectReference;

public interface Registry440 {
	
	public RemoteObjectReference lookup(String serviceName);
	public void rebind(String serviceName, RemoteObjectReference ror);
}
