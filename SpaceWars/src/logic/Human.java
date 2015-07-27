package logic;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import clientServer.Client;
import clientServer.Server;
import helper.NameHelper;

public class Human extends UnicastRemoteObject implements Serializable, Client {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Server server;
	protected final String username;
	protected final int ownerId;
	private static int userCount = 0;
	protected int cash;
	protected int amountOfPlanets;
	protected Game gamePlaying;
	private List<SpaceshipIf> stock;
	protected boolean playerReady;
	private JSONObject roundReport;
	private String team;

	/**
	 * @param username
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public Human(String username)
			throws MalformedURLException, RemoteException, NotBoundException {
		this.server = (Server) Naming.lookup(NameHelper.getServeraddress());
		this.username = username;
		this.ownerId = userCount;
		this.cash = 5000;
		this.setStock(new ArrayList<SpaceshipIf>());
		userCount++;
		System.out.println(userCount);
		this.team = this.username+ownerId;
	}
	/* (non-Javadoc)
	 * @see clientServer.Client#setTeam(java.lang.String)
	 */
	@Override
	public void setTeam(String team) throws RemoteException{
		this.team = team;
	}
	
	/* (non-Javadoc)
	 * @see clientServer.Client#openGame(java.lang.String, int, int)
	 */
	@Override
	public void openGame(String gameName, int variation, int universeSize) throws RemoteException {

		try {
			this.gamePlaying = this.server.openGameOnServer(gameName, variation, universeSize, this);
			if (this.getGamePlaying().getVariation()==1) {
				System.out.println("PvPC");
				this.getGamePlaying().addPlayer(new KI("Computer"));
				this.server.joinGame(gameName);
			}else if(this.getGamePlaying().getVariation()==2){
				System.out.println("Player, Player vs PC");
				this.getGamePlaying().addPlayer(new KI("Computer"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/* (non-Javadoc)
	 * @see clientServer.Client#closeGame()
	 */
	@Override
	public void closeGame() throws RemoteException{
		this.server.closeGame(this.getGamePlaying().getGameName());
	}
	/* (non-Javadoc)
	 * @see clientServer.Client#setAmountOfPlanets(int)
	 */
	@Override
	public void setAmountOfPlanets(int planets) throws RemoteException {
		this.amountOfPlanets = planets;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#joinGame(java.lang.String)
	 */
	@Override
	public void joinGame(String gameName) throws RemoteException {
		// TODO just in case get game, manipulate, copy back and overwrite
		this.gamePlaying = this.server.getGameByName(gameName);
		this.gamePlaying.addPlayer(this);
		this.server.joinGame(gameName);

	}

	/* (non-Javadoc)
	 * @see clientServer.Client#getRoundReport()
	 */
	public JSONObject getRoundReport() throws RemoteException {
		return roundReport;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#setRoundReport(org.json.JSONObject)
	 */
	public void setRoundReport(JSONObject roundReport) throws RemoteException {
		this.roundReport = roundReport;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#getAmountOfPlanets()
	 */
	@Override
	public int getAmountOfPlanets() {
		return this.amountOfPlanets;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#addCash(int)
	 */
	public void addCash(int cash) {
		this.cash += cash;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#getCash()
	 */
	public int getCash() {
		return this.cash;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#getGamePlaying()
	 */
	public Game getGamePlaying() {
		return gamePlaying;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#setGamePlaying(logic.Game)
	 */
	public void setGamePlaying(Game gamePlaying) {
		this.gamePlaying = gamePlaying;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#getOwnerId()
	 */
	public int getOwnerId() {
		return ownerId;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#isPlayerReady()
	 */
	public boolean isPlayerReady() {
		return playerReady;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#setPlayerReady(boolean)
	 */
	public void setPlayerReady(boolean playerReady) throws RemoteException {
		this.playerReady = playerReady;
		System.out.println("Ich: " + this.getUsername() + " bin fertig!");
		// if (this.getGamePlaying().playersReady()) {
		if(!this.getGamePlaying().isGameFinished()){
			this.getGamePlaying().endRound();
		}
		else{
			System.out.println("game is finished and the winner is: "+this.getGamePlaying().getWinnerByName());
		}
		
		// }

	}

	/* (non-Javadoc)
	 * @see clientServer.Client#getUsername()
	 */
	public String getUsername() {
		return username;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#buyBattlestar()
	 */
	@Override
	public void buyBattlestar() throws RemoteException {
		if (this.cash - Battlestar.getPrice() >= 0) {
			System.out.println("cash before buy battlestar " + this.cash);
			this.cash -= Battlestar.getPrice();
			System.out.println("cash after" + this.cash);
			this.getStock().add(new Battlestar(this));
		} else {
			System.out.println("Not enough Credits to buy Battlestar " + this.cash);
		}
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#buyFighter()
	 */
	@Override
	public void buyFighter() throws RemoteException {
		if (this.cash - Fighter.getPrice() >= 0) {
			this.cash -= Fighter.getPrice();
			this.getStock().add(new Fighter(this));
		} else {
			System.out.println("Not enough Credits to buy Fighter");
		}
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#sendShip(logic.SpaceshipIf, logic.PlanetIf)
	 */
	@Override
	public void sendShip(SpaceshipIf ship, PlanetIf destination) throws RemoteException {
		if (ship.getOrbiting() == null) {
			if (destination != null) {
				destination.addShipToOrbit(ship);
			}
			ship.setOrbiting(destination);
		} else {
			ship.getOrbiting().removeShipFromOrbit(ship);
			if (destination != null) {
				destination.addShipToOrbit(ship);
				ship.setOrbiting(destination);
			} else {
				ship.setOrbiting(null);
				this.getStock().add(ship);
			}
		}

	}

	/* (non-Javadoc)
	 * @see clientServer.Client#sendAllShipsToStock(logic.PlanetIf)
	 */
	@Override
	public void sendAllShipsToStock(PlanetIf origin) throws RemoteException {
		List<SpaceshipIf> tmp = new LinkedList<SpaceshipIf>();
		for (SpaceshipIf s : origin.getShipsInOrbit()) {
			if (s.getOwner().equals(this)) {
				tmp.add(s);
			}
		}
		if(origin.getSecondOwnerOrbit()!=null){
			for (SpaceshipIf s : origin.getSecondOwnerOrbit()) {
				if (s.getOwner().equals(this)) {
					tmp.add(s);
				}
			}
		}
		for (SpaceshipIf s : tmp) {
			sendShip(s, null);
		}

	}

	/* (non-Javadoc)
	 * @see java.rmi.server.RemoteObject#toString()
	 */
	public String toString() {
		String string = this.username + " Cash: " + this.cash + " Planeten: " + this.amountOfPlanets + " ";
		for (SpaceshipIf s : this.getStock()) {
			if (s != null) {
				string += "\n" + s.toString();
			}
		}
		return string;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#getStock()
	 */
	public List<SpaceshipIf> getStock() {
		return stock;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#equals(clientServer.Client)
	 */
	@Override
	public boolean equals(Client other) throws RemoteException {
		if (this.getOwnerId() == other.getOwnerId()) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#setCash(int)
	 */
	@Override
	public void setCash(int cash) throws RemoteException {
		this.cash = cash;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#setStock(java.util.List)
	 */
	@Override
	public void setStock(List<SpaceshipIf> stock) throws RemoteException {
		// TODO Auto-generated method stub
		this.stock=stock;
	}

	/* (non-Javadoc)
	 * @see clientServer.Client#isKI()
	 */
	@Override
	public boolean isKI() throws RemoteException {
	
		return false;
	}
	/* (non-Javadoc)
	 * @see clientServer.Client#getTeam()
	 */
	@Override
	public String getTeam() throws RemoteException {
		// TODO Auto-generated method stub
		return this.team;
	}
}
