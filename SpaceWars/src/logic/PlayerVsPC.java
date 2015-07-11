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
		super(gameName, universeSize);
	}

	@Override
	public void addPlayer(Client newPlayer) throws RemoteException {
		newPlayer.setGamePlaying(this);
		this.players[0]= newPlayer;
		System.out.println("Player added");
		try {
			this.players[1]=new KI("Computer","192.168.178.23");
			this.players[1].setGamePlaying(this);
			this.hasEnoughPlayer = true;
		} catch (MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
	}

	
	
}
