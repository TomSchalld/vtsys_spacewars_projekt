package logic;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class KI extends Human {

	/**
	 * @param username
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public KI(String username) throws MalformedURLException, RemoteException, NotBoundException {
		super(username);
		this.cash = 7500;
	}

	/** Rules the world.... automated user is doing its round
	 * @throws RemoteException
	 */
	public void ruleTheWorld() throws RemoteException {
		if (this.gamePlaying != null) {
			if(!this.gamePlaying.isGameFinished()){
				if(this.gamePlaying.hasEnoughPlayer()){
					this.buyNewShips();
					for (int i = 0; i < 5; i++) {
						this.sendShips();
					}
					this.setPlayerReady(true);
					this.setCash(cash * 3);
					System.out.println("Skynet is gonna rule");
				}
				
			}
			
		}

	}

	/* (non-Javadoc)
	 * @see logic.Human#setGamePlaying(logic.Game)
	 */
	@Override
	public void setGamePlaying(Game gamePlaying) {
		super.setGamePlaying(gamePlaying);
		try {
			this.ruleTheWorld();

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see logic.Human#setPlayerReady(boolean)
	 */
	@Override
	public void setPlayerReady(boolean playerReady) throws RemoteException {
		super.setPlayerReady(playerReady);
		if (!playerReady) {
			try {
				this.ruleTheWorld();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**Sends ships to random planets
	 * @throws RemoteException
	 */
	private void sendShips() throws RemoteException {
		PlanetIf randomPlanet;
		UniverseIf actual = this.getGamePlaying().getUniverse();
		SpaceshipIf randomShip;
		int shipsOnPlanetOfInterest;
		int shipsInStock = this.getStock().size();
		List<SpaceshipIf> shipsToBeSend = new LinkedList<SpaceshipIf>();
		if (shipsInStock > 0) {
			if (shipsInStock >= 5) {
				randomPlanet = actual.getRandomPlanet();
				if (randomPlanet.getPlanetOwner() != null) {
					while (randomPlanet.getPlanetOwner().equals(this)) {
						randomPlanet = actual.getRandomPlanet();
						if (randomPlanet.getPlanetOwner() == null) {
							break;
						}
					}
				}

				for (int i = 0; i < 5; i++) {
					randomShip = getRandomShip();
					if (!shipsToBeSend.contains(randomShip)) {
						this.sendShip(randomShip, randomPlanet);
						shipsToBeSend.add(randomShip);
					} else {
						i--;
					}

				}
			} else {
				for (int i = 0; i < shipsInStock; i++) {
					randomPlanet = actual.getRandomPlanet();
					if (randomPlanet.getPlanetOwner() != null) {
						if (randomPlanet.getPlanetOwner().equals(this)) {
							i--;
						} else {
							randomShip = getRandomShip();
							if (!shipsToBeSend.contains(randomShip)) {
								this.sendShip(randomShip, randomPlanet);
								shipsToBeSend.add(randomShip);
							} else {
								i--;
							}
						}
					}

				}
			}

		}
		this.getStock().removeAll(shipsToBeSend);

	}

	/**Buys new ships after komplex calculations
	 * @throws RemoteException
	 */
	private void buyNewShips() throws RemoteException {
		int battlestarsToBuy = calculateBattlestars();
		int countOfBS = 0;
		int countOfFighter = 0;
		int fightersToBuy;
		for (int i = 0; i < battlestarsToBuy; i++) {
			this.buyBattlestar();
			countOfBS++;
		}
		if (this.getAmountOfPlanets() < 2) {
			fightersToBuy = calculateFighters();
			for (int i = 0; i < fightersToBuy; i++) {
				this.buyFighter();
				countOfFighter++;
			}
		}

		System.out.println("skynet bought " + countOfBS + " Battlestars and " + countOfFighter + " Fighter");

	}

	/**
	 * @return
	 */
	private SpaceshipIf getRandomShip() {
		Random random = new Random();
		List<SpaceshipIf> ships = this.getStock();
		return ships.get(random.nextInt(ships.size()));
	}

	/**
	 * @return
	 * @throws RemoteException
	 */
	private int calculateFighters() throws RemoteException {
		int actualCash = this.getCash();
		return actualCash / Fighter.getPrice();
	}

	/**
	 * @return
	 * @throws RemoteException
	 */
	private int calculateBattlestars() throws RemoteException {
		int actualCash = this.getCash();
		int maxBattlestars = actualCash / Battlestar.getPrice();
		int universeSize = this.getGamePlaying().getUniverse().getPlanets().size();
		int maxPower = universeSize * 5;
		return maxBattlestars;
		/*
		 * if (maxBattlestars == maxPower) { return maxBattlestars; } else {
		 * return maxBattlestars - 1; }
		 */

	}

	/* (non-Javadoc)
	 * @see logic.Human#sendAllShipsToStock(logic.PlanetIf)
	 */
	@Override
	public void sendAllShipsToStock(PlanetIf origin) throws RemoteException {
		System.out.println("Skynet is not sending ships to stock");
	}

	/* (non-Javadoc)
	 * @see logic.Human#isKI()
	 */
	@Override
	public boolean isKI() throws RemoteException {

		return true;
	}

}
