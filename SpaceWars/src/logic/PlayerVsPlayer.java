package logic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import clientServer.Client;

public class PlayerVsPlayer extends UnicastRemoteObject implements Game {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final String gameName;
	protected String passwort;
	protected int gameId;
	protected boolean hasEnoughPlayer = false;
	protected boolean gameFinished = false;
	protected Client winner;
	protected Client[] players;
	protected UniverseIf universe;
	protected int round;
	protected static int gameCount = 0;
	protected Report endReport;
	protected int variation; // 0 = Player vs. Player
								// 1= Player vs. PC
								// 2= Player, Player vs PC

	/**
	 * @param gameName
	 * @param universeSize
	 * @param variation
	 * @throws RemoteException
	 */
	public PlayerVsPlayer(String gameName, int universeSize, int variation) throws RemoteException {
		this.gameName = gameName;
		this.gameId = gameCount;
		if (variation != 2) {
			this.universe = new Universe(universeSize);
		} else {
			this.universe = new Universe(universeSize, true);
		}
		this.round = 0;
		this.endReport = new EndReport();
		gameCount++;
		this.players = new Client[2];
		this.variation = variation;
	}

	/* (non-Javadoc)
	 * @see logic.Game#getVariation()
	 */
	@Override
	public int getVariation() throws RemoteException {
		return variation;
	}

	/* (non-Javadoc)
	 * @see logic.Game#setVariation(int)
	 */
	@Override
	public void setVariation(int variation) throws RemoteException {
		this.variation = variation;
	}

	/* (non-Javadoc)
	 * @see logic.Game#addPlayer(clientServer.Client)
	 */
	@Override
	public void addPlayer(Client newPlayer) throws RemoteException {
		if (this.players[0] == null) {
			this.players[0] = newPlayer;
			System.out.println(newPlayer.getUsername() + " has logged in");
		} else {
			this.players[1] = newPlayer;
			this.hasEnoughPlayer = true;
			System.out.println(newPlayer.getUsername() + " has logged in");

		}
		newPlayer.setGamePlaying(this);

	}

	/**
	 * @param one
	 * @param two
	 * @throws RemoteException
	 */
	public void addPlayers(Client one, Client two) throws RemoteException {
		one.setGamePlaying(this);
		two.setGamePlaying(this);
		this.addPlayer(one);
		this.addPlayer(two);
	}

	/* (non-Javadoc)
	 * @see logic.Game#playersReady()
	 */
	@Override
	public boolean playersReady() throws RemoteException {
		if (this.players[0] == null || this.players[1] == null) {
			return false;
		}
		if (this.players[0].isPlayerReady() == true && this.players[1].isPlayerReady() == true) {
			return true;
		}
		return false;
	}

	/**
	 * @throws RemoteException
	 */
	private void setPlayersUnready() throws RemoteException {
		this.players[0].setPlayerReady(false);
		this.players[1].setPlayerReady(false);
	}

	/* (non-Javadoc)
	 * @see logic.Game#endRound()
	 */
	@Override
	public RoundReport endRound() throws RemoteException {
		int planetsPOne = 0;
		int planetsPTwo = 0;
		System.out
				.println("Round End ###########################################################################\n\n\n");
		if (this.playersReady()) {
			for(Client c:this.players){
				c.setAmountOfPlanets(0);
			}
			System.out.println("nach if");
			RoundReport report = new RoundReport(this.round);
			Map<String, PlanetIf> planets = this.getUniverse().getPlanets();
			for (PlanetIf p : planets.values()) {
				System.out.println(p.getName() + "wird auf kampf uberpruft");
				if (p.isFightAfterRoundEnded()) {
					report.addReport(p.fight());
				} else {
					System.out.println(p.getName() + " kein kampf hat stattgefunden");
					p.roundEnd();
				}
			}
			for (PlanetIf p : planets.values()) {
				if (!p.getShipsInOrbit().isEmpty()) {
					p.payCash();
				}
			}

			this.setPlayersUnready();
			for (Client c : this.players) {
				planetsPOne = 0;
				planetsPTwo = 0;
				c.setRoundReport(report.exportRoundToJSON());
				for (PlanetIf p : this.getUniverse().getPlanets().values()) {
					if (p.getPlanetOwner() != null) {
						// if (p.getPlanetOwner().equals(this.players[0])) {
						// this.players[0].setAmountOfPlanets(++planetsPOne);
						// } else {
						// this.players[1].setAmountOfPlanets(++planetsPTwo);
						// }
						if (p.getPlanetOwner().equals(c)) {
							c.sendAllShipsToStock(p);
						}
					}

				}
			}
			
			System.out.println(
					this.players[0].getUsername() + " anzahl planeten = " + this.players[0].getAmountOfPlanets());
			System.out.println(
					this.players[1].getUsername() + " anzahl planeten = " + this.players[1].getAmountOfPlanets());
			System.out.println("Universe size is: " + this.getUniverse().getPlanets().keySet().size());
			this.checkAndSetWinner();
			this.endReport.addReport(report);
			this.round++;
			return report;
			
		}
		return null;
	}

	/**
	 * @throws RemoteException
	 */
	@Override
	public void checkAndSetWinner() throws RemoteException {
		EndReport er = (EndReport) this.getEndreport();
		if (this.players[0].getAmountOfPlanets() == 0 && this.players[0].getCash() < Fighter.price && this.players[0].getStock().size()==0) {
			this.gameFinished = true;
			this.winner = this.players[1];
			er.addWinner(this.players[1]);
			er.addLooser(this.players[0]);
			System.out.println("game is over. Winner is: " + this.players[1].getUsername());
		} else if (this.players[1].getAmountOfPlanets() == 0 && this.players[1].getCash() < Fighter.price  && this.players[1].getStock().size()==0) {
			this.gameFinished = true;
			this.winner = this.players[0];
			er.addWinner(this.players[0]);
			er.addLooser(this.players[1]);
			System.out.println("game is over. Winner is: " + this.players[0].getUsername());
		}
		if (this.players[0].getAmountOfPlanets() == this.getUniverse().getPlanets().keySet().size()) {
			this.gameFinished = true;
			this.winner = this.players[0];
			er.addWinner(this.players[0]);
			er.addLooser(this.players[1]);
			System.out.println("game is over. Winner is: " + this.players[0].getUsername());
		} else if (this.players[1].getAmountOfPlanets() == this.getUniverse().getPlanets().keySet().size()) {
			this.gameFinished = true;
			this.winner = this.players[1];
			er.addWinner(this.players[1]);
			er.addLooser(this.players[0]);
			System.out.println("game is over. Winner is: " + this.players[1].getUsername());

		}
	}

	/* (non-Javadoc)
	 * @see logic.Game#getUniverse()
	 */
	@Override
	public UniverseIf getUniverse() throws RemoteException {
		return this.universe;
	}

	/* (non-Javadoc)
	 * @see logic.Game#hasEnoughPlayer()
	 */
	@Override
	public boolean hasEnoughPlayer() throws RemoteException {
		return this.hasEnoughPlayer;
	}

	/* (non-Javadoc)
	 * @see logic.Game#isGameFinished()
	 */
	@Override
	public boolean isGameFinished() throws RemoteException {
		return this.gameFinished;
	}

	/* (non-Javadoc)
	 * @see logic.Game#getEndreport()
	 */
	@Override
	public Report getEndreport() throws RemoteException {
		return this.endReport;
	}

	/* (non-Javadoc)
	 * @see logic.Game#getGameName()
	 */
	@Override
	public String getGameName() throws RemoteException {
		return this.gameName;
	}

	/* (non-Javadoc)
	 * @see logic.Game#killAllReferences()
	 */
	@Override
	public boolean killAllReferences() throws RemoteException {
		this.winner = null;

		for (Client c : this.players) {
			c.setGamePlaying(null);
			c = null;
		}
		this.universe = null;
		this.endReport = null;
		return true;
	}

	/* (non-Javadoc)
	 * @see logic.Game#getRound()
	 */
	@Override
	public int getRound() throws RemoteException {
		return round;
	}

	/* (non-Javadoc)
	 * @see logic.Game#getWinnerByName()
	 */
	@Override
	public String getWinnerByName() throws RemoteException {
		// TODO Auto-generated method stub
		return this.winner.getUsername();
	}

	/* (non-Javadoc)
	 * @see logic.Game#getHostName()
	 */
	@Override
	public String getHostName() throws RemoteException {
		// TODO Auto-generated method stub
		return this.players[0].getUsername();
	}

}
