package logic;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import clientServer.Client;
import clientServer.GameIf;

public class Human extends Player implements Serializable,Client{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chatMessage;
	private boolean hasNewMessage;

	public Human(String username, Game gamePlaying) throws MalformedURLException, RemoteException, NotBoundException {
		super(username, gamePlaying);
	}

	@Override
	public void openGame(String gameName, int variation, int universeSize) throws RemoteException {
		Game newGame;
		if (variation == 0) {
			newGame = new PlayerVsPlayer(gameName, universeSize);
			try {
				this.server.openGame(newGame);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			newGame.addPlayer(this);

		}
	}

	@Override
	public void joinGame(String gameName) throws RemoteException {
		//TODO just in case get game, manipulate, copy back and overwrite
		this.server.getGameByName(gameName).addPlayer(this);
		this.server.joinGame(gameName);

	}

}
