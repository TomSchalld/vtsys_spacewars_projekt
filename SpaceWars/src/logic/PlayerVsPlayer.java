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
	private static int gameCount = 0;
	protected Report endReport;
	private int variation; //0 = Player vs. Player 
							//1= Player vs. PC
							//3= Player, Player vs PC

	public PlayerVsPlayer(String gameName, int universeSize, int variation) throws RemoteException {
		this.gameName = gameName;
		this.gameId = gameCount;
		this.universe = new Universe(universeSize);
		this.round = 0;
		this.endReport = new EndReport();
		gameCount++;
		this.players = new Client[2];
		this.variation = variation;
	}
	@Override
	public int getVariation() throws RemoteException {
		return variation;
	}
	@Override
	public void setVariation(int variation) throws RemoteException {
		this.variation = variation;
	}

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

	public void addPlayers(Client one, Client two) throws RemoteException {
		one.setGamePlaying(this);
		two.setGamePlaying(this);
		this.addPlayer(one);
		this.addPlayer(two);
	}

	@Override
	public boolean playersReady() throws RemoteException {
		if (this.players[0].isPlayerReady() == true && this.players[1].isPlayerReady() == true) {
			return true;
		}
		return false;
	}

	private void setPlayersUnready() throws RemoteException {
		this.players[0].setPlayerReady(false);
		this.players[1].setPlayerReady(false);
	}

	@Override
	public RoundReport endRound() throws RemoteException {
		int planetsPOne=0;
		int planetsPTwo=0;
		System.out.println("Round End ###########################################################################\n\n\n");
		if (this.playersReady()) {
			System.out.println("nach if");
			RoundReport report = new RoundReport(this.round);
			Map<String, PlanetIf> planets = this.getUniverse().getPlanets();
			for (PlanetIf p : planets.values()) {
				System.out.println(p.getName()+"wird auf kampf uberpruft");
				if (p.isFightAfterRoundEnded()) {
					report.addReport(p.fight());
				}else{
					System.out.println(p.getName()+" kein kampf hat stattgefunden");
					p.roundEnd();
				}
			}
			for (PlanetIf p : planets.values()) {
				if (!p.getShipsInOrbit().isEmpty()) {
					p.getShipsInOrbit().get(0).getOwner().addCash(p.getGeneratedCredits());
				}
			}

			this.setPlayersUnready();
			for (Client c : this.players) {
				c.setRoundReport(report.exportRoundToJSON());
				for (PlanetIf p : this.getUniverse().getPlanets().values()) {
					if (p.getPlanetOwner() != null) {
						if(p.getPlanetOwner().equals(this.players[0])){
							this.players[0].setAmountOfPlanets(++planetsPOne);
						}else{
							this.players[1].setAmountOfPlanets(++planetsPTwo);
						}
						if (p.getPlanetOwner().equals(c)) {
							c.sendAllShipsToStock(p);
						}
					}

				}
			}
			this.round++;
			System.out.println(this.players[0].getUsername()+" anzahl planeten = "+this.players[0].getAmountOfPlanets());
			System.out.println(this.players[1].getUsername()+" anzahl planeten = "+this.players[1].getAmountOfPlanets());
			if (this.players[0].getAmountOfPlanets() == this.getUniverse().getPlanets().keySet().size()) {
				this.gameFinished = true;
				this.winner = this.players[0];
			} else if (this.players[1].getAmountOfPlanets() == this.getUniverse().getPlanets().keySet().size()) {
				this.gameFinished = true;
				this.winner = this.players[1];
			}
			this.endReport.addReport(report);
			return report;
		}
		return null;
	}

	@Override
	public UniverseIf getUniverse() throws RemoteException {
		return this.universe;
	}

	@Override
	public boolean hasEnoughPlayer() throws RemoteException {
		return this.hasEnoughPlayer;
	}

	@Override
	public boolean isGameFinished() throws RemoteException {
		return this.gameFinished;
	}
	
	@Override
	public Report getEndreport() throws RemoteException {
		return this.endReport;
	}
	
	@Override
	public String getGameName() throws RemoteException {
		return this.gameName;
	}

	@Override
	public boolean killAllReferences() throws RemoteException {
		return false;
	}

	@Override
	public int getRound() throws RemoteException {
		return round;
	}

	@Override
	public String getWinnerByName() throws RemoteException {
		// TODO Auto-generated method stub
		return this.winner.getUsername();
	}
	@Override
	public String getHostName() throws RemoteException {
		// TODO Auto-generated method stub
		return this.players[0].getUsername();
	}

}
