package communication;

import java.io.Serializable;

@SuppressWarnings("serial")
/** RMIMessage
 * 
 * Packages data to be sent from client to server (or visa versa) such as
 * method to be invoked, parameters, key of local object, return value, and
 * exceptions
 * 
 * @author Tyler Healy - thealy, Justin Greet - jgreet
 */
public class RMIMessage implements Serializable {
	private String methodName;
	private Object[] parameters;
	private int objectKey;
	private Object returnValue = null;
	private Throwable exception;
	
	/** RMIMessage(String methodName, Object[] parameters, int objectKey)
	 * 
	 * In our implementation, the RMIMessage is created in the client. Therefore,
	 * the constructor needs only take the following three values:
	 * @param methodName - method to be invoked (as a String)
	 * @param parameters - array of objects that are the parameters to the method
	 * @param objectKey - key of the object on which the method is to be called
	 */
	public RMIMessage(String methodName, Object[] parameters, int objectKey) {
		this.methodName = methodName;
		this.parameters = parameters;
		this.objectKey = objectKey;
	}
	
	/** setReturnValue(Object returnValue)
	 * 
	 * Called by the server. Marshalls return value.
	 * @param Object returnValue
	 */
	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}
	
	/** setException(Throwable e)
	 * 
	 * Called by the server. Marshalls exception/error
	 * @param Throwable e
	 */
	public void setException(Throwable e) {
		exception = e;
	}
	
	/** getMethodName()
	 * 
	 * @return String methodName
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/** getParemeters() 
	 * 
	 * @return Object[] parameters
	 */
	public Object[] getParemeters() {
		return parameters;
	}
	
	/** getObjectKey()
	 * 
	 * @return int objectKey
	 */
	public int getObjectKey() {
		return objectKey;
	}
	
	/** getReturnValue()
	 * 
	 * @return Object returnValue
	 */
	public Object getReturnValue() {
		return returnValue;
	}
	
	/** getException()
	 * 
	 * @return Throwable exception
	 */
	public Throwable getException() {
		return exception;
	}
}
