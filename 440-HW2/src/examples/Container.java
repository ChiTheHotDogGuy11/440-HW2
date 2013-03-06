package examples;

import stub.Remote440;

public interface Container extends Remote440 {
	public double getAverage(Element[] elems);
	
	public Element minElem(Element[] elems);
	
	public String identifyElems(Element[] elems);
}
