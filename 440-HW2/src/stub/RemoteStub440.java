package stub;

import java.io.Serializable;

import references.RemoteObjectReference;

public class RemoteStub440 implements Serializable, Remote440 {
	RemoteObjectReference ROR;
	
	public RemoteStub440() {
		
	}
	
	public RemoteStub440(RemoteObjectReference ROR) {
		this.ROR = ROR;
	}
}
