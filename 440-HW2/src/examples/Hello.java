package examples;

public class Hello implements HelloServer {

	public String sayHello(String nameOfPerson) {
		return "Hey there " + nameOfPerson + ", my buddy-ole pal.";
	}
}
