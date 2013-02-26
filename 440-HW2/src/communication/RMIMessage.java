package communication;

public class RMIMessage {
<<<<<<< HEAD
	private String methodName;
	private Object[] parameters;
	private Object returnValue = null;
	
	public RMIMessage(String methodName, Object[] parameters) {
		this.methodName = methodName;
		this.parameters = parameters;
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
	
	public Object getReturnValue() {
		return returnValue;
	}
=======
	
>>>>>>> Updated RMI440
}
