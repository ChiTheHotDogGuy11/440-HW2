package registry;

import references.RemoteObjectReference;

/** Registry440
 * 
 * Interface for objects that are either implementing a registry or
 * communicating with one. Client/server looks for this interface, not a
 * specific class. Abstracts away the difference between the actual registry
 * and registry communications.
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 */
public interface Registry440 {
	
	public RemoteObjectReference lookup(String serviceName);
	public void rebind(String serviceName, RemoteObjectReference ror);
}
