package registry;

import java.net.*;
import java.io.*;

public class LocateSimpleRegistry { 
    // this is the SOLE static method.
    // you use it as: LocateSimpleRegistry.getRegistry(123.123.123.123, 2048)
    // and it returns null if there is none, else it returns the registry.
    // actually the registry is just a pair of host IP and port. 
    // inefficient? well you can change it as you like. 
    // for the rest, you can see SimpleRegistry.java.
    public static RegistryCom440 getRegistry(String host, int port) {
		// open socket.
		try {
		    Socket soc = new Socket(host, port);
		    
		    // get TCP streams and wrap them. 
		    ObjectInputStream in = new ObjectInputStream(soc.getInputStream());
		    ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
		    
		    // ask.
		    out.writeObject("who are you?");
		    
		    // gets answer.
		    if (((String)(in.readObject())).equals("I am a registry.")) {
			    return new RegistryCom440(host, port);
			} else {
			    System.out.println("somebody is there but not a registry!");
			    return null;
			}
		}
		catch (Exception e) { 
			System.out.println("nobody is there!"+e);
			return null;
		}
    }
}
