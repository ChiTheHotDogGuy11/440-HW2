package stub;

import java.io.Serializable;

import references.RemoteObjectReference;

@SuppressWarnings("serial")
/** RemoteStub440
 * 
 * Class which stubs will extend. Contains basic functionality that is
 * necessary and consistent across all stubs
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 */
public class RemoteStub440 implements Serializable, Remote440 {
	RemoteObjectReference ROR;
	
	public RemoteStub440() {
		
	}
	
	/** RemoteStub440(RemoteObjectReference ROR)
	 * 
	 * @param ROR - RemoteObjectReference that (presumably) created this stub
	 */
	public RemoteStub440(RemoteObjectReference ROR) {
		this.ROR = ROR;
	}
	
	/** getROR()
	 * 
	 * @return RemoteObjectReference ROR
	 */
	public RemoteObjectReference getROR() {
		return ROR;
	}
	
	/** setROR(RemoteObjectReference ROR)
	 * 
	 * @param ROR - RemoteObjectReference that (presumably) created this stub
	 */
	public void setROR(RemoteObjectReference ROR) {
		this.ROR = ROR;
	}
	
	/** getPortName()
	 * 
	 * @return int port held within ROR
	 */
	public int getPortName() {
		if (ROR != null) {
		  return ROR.getPort();
		} else {
			return -1;
		}	
	}
	
	/** getIP()
	 * 
	 * @return int IP held within ROR
	 */
	public String getIP() {
		if (ROR != null) {
		  return ROR.getIP();
		} else {
			return null;
		}
	}
	
	/** getObjectKey()
	 * 
	 * @return int objectKey held within ROR
	 */
	public int getObjectKey() {
		if (ROR != null) {
		  return ROR.getObjectKey();
		} else {
			return -1;
		}
	}
}
