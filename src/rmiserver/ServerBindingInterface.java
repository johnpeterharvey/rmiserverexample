package rmiserver;

public interface ServerBindingInterface {
	/**
	 * Registers the RemoteServer with the RMI registry for clients to access
	 */
	void bind();

	/**
	 * Unbind the connection
	 * Remove the application from the RMI registry
	 */
	void unbind();
}