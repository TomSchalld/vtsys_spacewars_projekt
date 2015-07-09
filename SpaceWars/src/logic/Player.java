package logic;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import clientServer.Client;
import clientServer.Server;

public abstract class Player extends UnicastRemoteObject implements Client,Serializable{
	protected Server server;
	protected final String username;
	protected final int ownerId;
	private static int userCount = 0;
	protected int cash;
	protected int amountOfPlanets;
	protected Game gamePlaying;
	private List<Spaceship> stock;
	protected boolean playerReady;

	public Player(String username, Game gamePlaying) throws MalformedURLException, RemoteException, NotBoundException {
		this.server =  (Server) Naming.lookup("rmi://localhost:1099/GameServer");
		this.username = username;
		this.ownerId = userCount;
		this.cash = 10000;
		this.gamePlaying = gamePlaying;
		this.setStock(new ArrayList<Spaceship>());
		userCount++;
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

	public void setPlayerReady(boolean playerReady) {
		this.playerReady = playerReady;
		/*if(playerReady){ for online use only
			this.getGamePlaying().endRound();
		}*/
	}

	public String getUsername() {
		return username;
	}

	public void buyBattlestar() {
		if (this.cash > this.cash - Battlestar.getPrice()) {
			this.cash-=Battlestar.getPrice();
			this.getStock().add(new Battlestar(this));
		}else {
			System.out.println("Not enough Credits to buy Battlestar");
		}
	}

	public void buyFighter() {
		if (this.cash > this.cash - Fighter.getPrice()) {
			this.cash-=Fighter.getPrice();
			this.getStock().add(new Fighter(this));
		}else {
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
			ship.setOrbiting(destination);
		}
	}
	public String toString(){
		String string = this.username+" Cash: "+this.cash+" Planeten: "+this.amountOfPlanets+" ";
		for(Spaceship s:this.getStock()){
			if(s!=null){
				string+="\n"+s.toString();
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
}
