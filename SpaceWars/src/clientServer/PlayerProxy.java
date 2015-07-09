package clientServer;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

import logic.Player;

public class PlayerProxy extends UnicastRemoteObject implements PlayerProxyIf {
	Player player;
	Server server;
	
	public PlayerProxy(Player p) throws RemoteException {
		this.player=p;
	}

	

	

}
