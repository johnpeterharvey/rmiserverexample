package rmiserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteServerInterface extends Remote {
	/**
	 * Send a string message that the server will print out
	 * @param message
	 * @throws RemoteException
	 */
	public void sendMessage(final String message) throws RemoteException;

	/**
	 * Send the server two doubles that it will add, followed by it printing the result
	 * @param a
	 * @param b
	 * @return
	 * @throws RemoteException
	 */
	public double addNumbers(final double a, final double b) throws RemoteException;
}