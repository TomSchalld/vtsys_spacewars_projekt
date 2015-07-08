package logic;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	protected String username;
	protected final int ownerId;
	private static int userCount = 0;
	protected int cash;
	protected int amountOfPlanets;
	protected Game gamePlaying;
	protected List<Spaceship> stock;
	protected boolean playerReady;

	public Player(String username, Game gamePlaying) {
		this.username = username;
		this.ownerId = userCount;
		this.cash = 10000;
		this.gamePlaying = gamePlaying;
		this.stock = new ArrayList<Spaceship>();
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

	protected void buyBattlestar() {
		if (this.cash > this.cash - Battlestar.getPrice()) {
			this.cash-=Battlestar.getPrice();
			this.stock.add(new Battlestar(this));
		}else {
			System.out.println("Not enough Credits to buy Battlestar");
		}
	}

	protected void buyFighter() {
		if (this.cash > this.cash - Fighter.getPrice()) {
			this.cash-=Fighter.getPrice();
			this.stock.add(new Fighter(this));
		}else {
			System.out.println("Not enough Credits to buy Fighter");
		}
	}

	protected void sendShip(Spaceship ship, Planet destination) {
		if (ship.orbiting == null) {
			this.stock.remove(this.stock.indexOf(ship));
			destination.addShipToOrbit(ship);
			ship.setOrbiting(destination);
		} else {
			ship.orbiting.removeShipFromOrbit(ship);
			ship.setOrbiting(destination);
		}
	}
	public String toString(){
		String string = this.username+" Cash: "+this.cash+" Planeten: "+this.amountOfPlanets+" ";
		for(Spaceship s:this.stock){
			if(s!=null){
				string+="\n"+s.toString();
			}
		}
		return string;
	}
}
