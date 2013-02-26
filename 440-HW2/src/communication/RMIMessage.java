package communication;

import java.io.Serializable;

public class RMIMessage implements Serializable{
	private String methodName;
	private Object[] parameters;
	private int objectKey;
	private Object returnValue = null;
	
	public RMIMessage(String methodName, Object[] parameters, int objectKey) {
		this.methodName = methodName;
		this.parameters = parameters;
		this.objectKey = objectKey;
	}
	
	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public Object[] getParemeters() {
		return parameters;
	}
	
	public int getObjectKey() {
		return objectKey;
	}
	
	public Object getReturnValue() {
		return returnValue;
	}
}
