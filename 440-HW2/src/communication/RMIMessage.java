package communication;

import java.io.Serializable;
import java.util.ArrayList;

public class RMIMessage implements Serializable{
	private String methodName;
	private Object[] parameters;
	private int objectKey;
	private Object returnValue = null;
	private ArrayList<Exception> exceptions = new ArrayList<Exception>();
	
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
	
	public ArrayList<Exception> getExceptions() {
		return exceptions;
	}
	
	public void printStackTraces() {
		for (int i = 0; i < exceptions.size(); i++) {
			exceptions.get(i).printStackTrace();
		}
	}
	
	public void addException(Exception e) {
		exceptions.add(e);
	}
}
