package logic;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
	private List<Spaceship> stock;
	protected boolean playerReady;
	private JSONObject roundReport;

	public Human(String username, String serveraddress)
			throws MalformedURLException, RemoteException, NotBoundException {
		this.server = (Server) Naming.lookup("rmi://" + serveraddress + ":1099/GameServer");
		this.username = username;
		this.ownerId = userCount;
		this.cash = 10000;
		this.setStock(new ArrayList<Spaceship>());
		userCount++;
	}

	@Override
	public void openGame(String gameName, int variation, int universeSize) throws RemoteException {
		Game newGame;
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
		}
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
		if (this.getGamePlaying().playersReady()) {
			this.getGamePlaying().endRound();
		}

	}

	public String getUsername() {
		return username;
	}

	public void buyBattlestar() throws RemoteException {
		if (this.cash - Battlestar.getPrice() > 0) {
			this.cash -= Battlestar.getPrice();
			this.getStock().add(new Battlestar(this));
		} else {
			System.out.println("Not enough Credits to buy Battlestar");
		}
	}

	public void buyFighter() throws RemoteException {
		if (this.cash - Fighter.getPrice() > 0) {
			this.cash -= Fighter.getPrice();
			this.getStock().add(new Fighter(this));
		} else {
			System.out.println("Not enough Credits to buy Fighter");
		}
	}

	public void sendShip(Spaceship ship, Planet destination) {
		if (ship.orbiting == null) {
			this.getStock().remove(this.getStock().indexOf(ship));
			destination.addShipToOrbit(ship);
			ship.setOrbiting(destination);
		} else {
			ship.orbiting.removeShipFromOrbit(ship);
			if (destination != null) {
				destination.addShipToOrbit(ship);
				ship.setOrbiting(destination);
			}else{
				ship.setOrbiting(null);
				this.getStock().add(ship);
			}
		}

	}

	public String toString() {
		String string = this.username + " Cash: " + this.cash + " Planeten: " + this.amountOfPlanets + " ";
		for (Spaceship s : this.getStock()) {
			if (s != null) {
				string += "\n" + s.toString();
			}
		}
		return string;
	}

	public List<Spaceship> getStock() {
		return stock;
	}

	public void setStock(List<Spaceship> stock) {
		this.stock = stock;
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
}
