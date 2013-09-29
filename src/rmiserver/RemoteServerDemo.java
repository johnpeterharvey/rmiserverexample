package rmiserver;

import java.io.IOException;
import java.rmi.RemoteException;

public class RemoteServerDemo {

	/**
	 * Open a connection, accept function calls, and then close the connection
	 * when the user hits <Enter>
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String args[]) throws RemoteException{
		RemoteServer server = new RemoteServer();
		server.bind();
		
		System.out.println("Server started, press ENTER to stop.");
		
		try {
			System.in.read();
		}
		catch (IOException e)  {
			throw new RuntimeException(e);
		}
		
		server.unbind();
		
		System.out.println("Server has stopped.");
		System.exit(0);
	}
}