package logic;

import java.rmi.RemoteException;

import clientServer.Client;

public class PlayerPlayerVsPC extends PlayerVsPlayer {

	public PlayerPlayerVsPC(String gameName, int universeSize) throws RemoteException {
		super(gameName,universeSize, 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addPlayer(Client newPlayer) {
		if (this.players[0] == null) {
			this.players[0] = newPlayer;
			try {
				this.players[0].setGamePlaying(this);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (this.players[1] == null) {
			this.players[1] = newPlayer;
			try {
				this.players[1].setGamePlaying(this);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (this.players[2] == null) {
			this.players[2] = newPlayer;
			try {
				this.players[2].setGamePlaying(this);
				this.hasEnoughPlayer=true;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	

	@Override
	protected void endRound() {
		// TODO Auto-generated method stub
		
	}

}
