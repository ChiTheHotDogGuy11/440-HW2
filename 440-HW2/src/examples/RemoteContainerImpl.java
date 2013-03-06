package examples;

public class RemoteContainerImpl implements Container{

	public double getAverage(Element[] elems) {
		if (elems == null) return 0;
		int totalSum = 0;
		for (int i = 0; i < elems.length; i++) {
			totalSum += elems[i].getValue();
		}
		return ((double)(totalSum)) / ((double)elems.length);
	}

	@Override
	public Element minElem(Element[] elems) {
		if (elems == null) throw new IllegalArgumentException("Elems cannot be null!");
		Element curMin = null;
		int curMinValue = Integer.MAX_VALUE;
		for (int i = 0; i < elems.length; i++) {
			Element curElem = elems[i];
			if (curElem.getValue() < curMinValue) {
				curMin = curElem;
				curMinValue = curMin.getValue();
			}
		}
		return curMin;
	}
	
	public String identifyElems(Element[] elems) {
		if (elems == null) throw new IllegalArgumentException("Elems cannot be null!");
		String result = "";
		for (int i = 0; i < elems.length; i++) {
			result += elems[i].identify() + "\n";
		}
		return result;
	}

}
