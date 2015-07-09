package logic;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Human extends Player {
	private String chatMessage;
	private boolean hasNewMessage;
	public Human(String username, Game gamePlaying) throws MalformedURLException, RemoteException, NotBoundException {
		super(username, gamePlaying);
	}
	public void openGame(String gameName,int variation, int universeSize){
		Game newGame;
		if(variation==0){
			newGame= new PlayerVsPlayer(gameName,universeSize);
			try {
				this.server.openGame(newGame, null);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			newGame.addPlayer(this);
			
		}
	}
	public void joinGame(String gameName){
		try {
			this.server.joinGame(gameName, this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
