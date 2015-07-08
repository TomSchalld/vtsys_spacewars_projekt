package clientServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface Server extends Remote {
	public GameProxy subscribeUser(String username, PlayerProxy handle) throws RemoteException;

	public boolean unsubscribeUser(String username) throws RemoteException;

	public List<GameProxy> getUser() throws RemoteException;

	public void sendMessage(String message, GameProxy server) throws RemoteException;
}
