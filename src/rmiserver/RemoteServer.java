package rmiserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class RemoteServer extends UnicastRemoteObject implements RemoteServerInterface, ServerBindingInterface, Serializable {
		
	private int PORT = 10000; //Port needs to be over 1024 on *nix when run as normal user
	private static String BIND_NAME = "RemoteServer";
	private Registry registry;
	private static final long serialVersionUID = 1L;

	/**
	 * Checks if an RMI registry already exists on the specified port
	 * If not, creates it
	 * Then set the registry to that registry for later application binding
	 * @throws RemoteException
	 */
	protected RemoteServer() throws RemoteException {
		super();
		
		// Make sure the registry exists
		try {
			LocateRegistry.createRegistry(PORT);
			System.out.println("Java RMI registry created.");
		} catch (RemoteException e) {
			System.out.println("Fine, Java RMI registry already exists.");
		}
	
		//Now get the registry and bind this instance of the server to it	
		registry = LocateRegistry.getRegistry(PORT);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see rmiserver.RemoteServerInterface#sendMessage(java.lang.String)
	 */
	public void sendMessage(String message) throws RemoteException {
		System.out.println(message);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rmiserver.RemoteServerInterface#addNumbers(double, double)
	 */
	public double addNumbers(double a, double b) throws RemoteException {
		double sum = a + b;
		return sum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rmiserver.ServerBindingInterface#bind()
	 */
	public void bind() {
		try{
			registry.bind(BIND_NAME, this);
		} catch (Exception e) {
			System.err.println("Failed to bind to registry.");
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see rmiserver.ServerBindingInterface#unbind()
	 */
	public void unbind() {
		try {
			registry.unbind(BIND_NAME);
		} catch (Exception e) {
			System.out.println("Error unbinding object from registry.");
			e.printStackTrace();
		}
	}
}