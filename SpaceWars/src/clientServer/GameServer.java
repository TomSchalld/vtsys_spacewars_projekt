package clientServer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GameServer extends UnicastRemoteObject implements Server, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1339765285800347165L;
	public GameServer() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	@Override
	public GameProxy subscribeUser(String username, PlayerProxy handle) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean unsubscribeUser(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<GameProxy> getUser() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void sendMessage(String message, GameProxy server) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
