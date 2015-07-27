package logic;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import clientServer.Client;

public class PlayerVsPC extends PlayerVsPlayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerVsPC(String gameName,int universeSize) throws RemoteException {
		super(gameName, universeSize,1);
	}

	

	
	
}
