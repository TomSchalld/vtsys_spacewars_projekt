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

	public Human(String username, String serveraddress)
			throws MalformedURLException, RemoteException, NotBoundException {
		this.server = (Server) Naming.lookup("rmi://" + serveraddress + ":1099/GameServer");
		this.username = username;
		this.ownerId = userCount;
		this.cash = 5000;
		this.setStock(new ArrayList<SpaceshipIf>());
		userCount++;
	}

	@Override
	public void openGame(String gameName, int variation, int universeSize) throws RemoteException {
		
		try {
			Game newGame = this.server.openGameOnServer(gameName, variation, universeSize);
			newGame.addPlayer(this);
			this.gamePlaying = newGame;
			this.server.joinGame(this.getGamePlaying().getGameName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		
		
		if (variation == 0) {
			newGame = new PlayerVsPlayer(gameName, universeSize);
			System.out.println("erstelle neuese pvp game");
			try {
				this.server.openGame(newGame);
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			newGame.addPlayer(this);
		} else if (variation == 1) {
			newGame = new PlayerVsPC(gameName, universeSize);
			newGame.addPlayer(this);
			System.out.println("User added");
			try {
				this.server.openGame(newGame);
				System.out.println("send game to server");
			} catch (Exception e) {
				System.out.println("exception e");
				e.printStackTrace();
			}

		} else if (variation == 2) {
			// newGame = new PlayerPlayerVsPC(gameName, universeSize);
			try {
				// this.server.openGame(newGame);
				// } catch (RemoteException e) {
				// e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// newGame.addPlayer(this);
		}*/
	}

	@Override
	public void setAmountOfPlanets(int planets) throws RemoteException {
		this.amountOfPlanets = planets;
	}

	@Override
	public void joinGame(String gameName) throws RemoteException {
		// TODO just in case get game, manipulate, copy back and overwrite
		this.server.getGameByName(gameName).addPlayer(this);
		this.server.joinGame(gameName);

	}

	public JSONObject getRoundReport() throws RemoteException {
		return roundReport;
	}

	public void setRoundReport(JSONObject roundReport) throws RemoteException {
		this.roundReport = roundReport;
	}

	@Override
	public int getAmountOfPlanets() {
		return this.amountOfPlanets;
	}

	public void addCash(int cash) {
		this.cash += cash;
	}

	public int getCash() {
		return this.cash;
	}

	public Game getGamePlaying() {
		return gamePlaying;
	}

	public void setGamePlaying(Game gamePlaying) {
		this.gamePlaying = gamePlaying;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public boolean isPlayerReady() {
		return playerReady;
	}

	public void setPlayerReady(boolean playerReady) throws RemoteException {
		this.playerReady = playerReady;
		System.out.println("Ich: " + this.getUsername() + " bin fertig!");
		// if (this.getGamePlaying().playersReady()) {
		this.getGamePlaying().endRound();
		// }

	}

	public String getUsername() {
		return username;
	}

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

	public void buyFighter() throws RemoteException {
		if (this.cash - Fighter.getPrice() >= 0) {
			this.cash -= Fighter.getPrice();
			this.getStock().add(new Fighter(this));
		} else {
			System.out.println("Not enough Credits to buy Fighter");
		}
	}

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

	@Override
	public void sendAllShipsToStock(PlanetIf origin) throws RemoteException {
		List<SpaceshipIf> tmp = new LinkedList<SpaceshipIf>();
		for (SpaceshipIf s : origin.getShipsInOrbit()) {
			if (s.getOwner().equals(this)) {
				tmp.add(s);
			}
		}
		for (SpaceshipIf s : tmp) {
			sendShip(s, null);
		}

	}

	public String toString() {
		String string = this.username + " Cash: " + this.cash + " Planeten: " + this.amountOfPlanets + " ";
		for (SpaceshipIf s : this.getStock()) {
			if (s != null) {
				string += "\n" + s.toString();
			}
		}
		return string;
	}

	public List<SpaceshipIf> getStock() {
		return stock;
	}

	public void setStock(ArrayList<SpaceshipIf> arrayList) {
		this.stock = arrayList;
	}

	@Override
	public boolean equals(Client other) throws RemoteException {
		if (this.getOwnerId() == other.getOwnerId()) {
			return true;
		}
		return false;
	}

	@Override
	public void setCash(int cash) throws RemoteException {
		this.cash = cash;
	}

	@Override
	public void setStock(List<SpaceshipIf> stock) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
