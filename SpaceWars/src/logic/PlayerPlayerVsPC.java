package logic;

import java.rmi.RemoteException;

import clientServer.Client;

public class PlayerPlayerVsPC extends PlayerVsPlayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlayerPlayerVsPC(String gameName, int universeSize) throws RemoteException {
		super(gameName, universeSize, 2);
		this.players = new Client[3];
	}
	@Override
	public boolean playersReady() throws RemoteException {
		System.out.println("players ready unterklasse");
		if (this.players[0] == null || this.players[1] == null || this.players[2]==null) {
			return false;
		}
		if (this.players[0].isPlayerReady() == true && this.players[1].isPlayerReady() == true && this.players[2].isPlayerReady() == true) {
			return true;
		}
		return false;
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
				this.hasEnoughPlayer = true;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void setPlayersUnready() throws RemoteException {
		this.players[0].setPlayerReady(false);
		this.players[1].setPlayerReady(false);
		this.players[2].setPlayerReady(false);
	}
	@Override
	protected void checkAndSetWinner() throws RemoteException {
		System.out.println("check and set winner unterklasse");
		EndReport er = (EndReport) this.getEndreport();
		if (this.players[0].getAmountOfPlanets() == 0 && this.players[0].getCash() < Fighter.price) {
			this.gameFinished = true;
			er.addWinner(this.players[1]);
			er.addLooser(this.players[0], this.players[2]);
			System.out.println("game is over. Winner is: " + this.players[1].getUsername());
		} else if (this.players[1].getAmountOfPlanets() == 0 && this.players[1].getCash() < Fighter.price) {
			this.gameFinished = true;
			er.addWinner(this.players[0], this.players[2]);
			er.addLooser(this.players[1]);
			System.out.println("game is over. Winner is: " + this.players[0].getUsername() + " and "
					+ this.players[2].getUsername());
		} else if (this.players[2].getAmountOfPlanets() == 0 && this.players[2].getCash() < Fighter.price) {
			this.gameFinished = true;
			er.addWinner(this.players[1]);
			er.addLooser(this.players[0], this.players[2]);
			System.out.println("game is over. Winner is: " + this.players[1].getUsername());
		}
		if(this.players[0].getAmountOfPlanets()+this.players[2].getAmountOfPlanets()==this.getUniverse().getPlanets().keySet().size()){
			this.gameFinished = true;
			er.addWinner(this.players[0], this.players[2]);
			er.addLooser(this.players[1]);
			System.out.println("game is over. Winner is: " + this.players[0].getUsername() + " and "
					+ this.players[2].getUsername());
		}
	}

	

}
