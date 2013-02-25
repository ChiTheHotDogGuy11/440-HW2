package references;

//This is simple. ROR needs a new object key for each remote object (or its skeleton). 
//This can be done easily, for example by using a counter.
//We also assume a remote object implements only one interface, which is a remote interface.

public class RORTable440 {
	 // I omit all instance variables. you can use hash table, for example.
	 // The table would have a key by ROR.
	 
	 // make a new table. 
	 public RORTable440() {
	 }
	
	 // add a remote object to the table. 
	 // Given an object, you can get its class, hence its remote interface.
	 // Using it, you can construct a ROR. 
	 // The host and port are not used unless it is exported outside.
	 // In any way, it is better to have it for uniformity.
	 public void addObj(String host, int port, Object o) {
	 }
	
	 // given ror, find the corresponding object.
	 public Object findObj(RemoteObjectReference ror) {
		 // if you use a hash table this is easy.
		 return null;
	 }
}
