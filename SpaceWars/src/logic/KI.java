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

	public KI(String username, String serveraddress) throws MalformedURLException, RemoteException, NotBoundException {
		super(username, serveraddress);

	}

	public void ruleTheWorld() throws RemoteException {

		this.buyNewShips();
		for (int i = 0; i < 5; i++) {
			this.sendShips();
		}
		this.setPlayerReady(true);
		System.out.println("Skynet is gonna rule");

	}

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

	private void sendShips() throws RemoteException {
		Planet randomPlanet;
		Universe actual = this.getGamePlaying().getUniverse();
		Spaceship randomShip;
		int shipsOnPlanetOfInterest;
		int shipsInStock = this.getStock().size();
		List<Spaceship> shipsToBeSend = new LinkedList<Spaceship>();
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

	private void buyNewShips() throws RemoteException {
		int battlestarsToBuy = calculateBattlestars();
		int countOfBS = 0;
		int countOfFighter = 0;
		int fightersToBuy;
		for (int i = 0; i < battlestarsToBuy; i++) {
			this.buyBattlestar();
			countOfBS++;
		}
		fightersToBuy = calculateFighters();
		for (int i = 0; i < fightersToBuy; i++) {
			this.buyFighter();
			countOfFighter++;
		}
		System.out.println("skynet bought " + countOfBS + " Battlestars and " + countOfFighter + " Fighter");

	}

	private Spaceship getRandomShip() {
		Random random = new Random();
		List<Spaceship> ships = this.getStock();
		return ships.get(random.nextInt(ships.size()));
	}

	private int calculateFighters() throws RemoteException {
		int actualCash = this.getCash();
		return actualCash / Fighter.getPrice();
	}

	private int calculateBattlestars() throws RemoteException {
		int actualCash = this.getCash();
		int maxBattlestars = actualCash / Battlestar.getPrice();
		int universeSize = this.getGamePlaying().getUniverse().getPlanets().size();
		int maxPower = universeSize * 5;
		if (maxBattlestars == maxPower) {
			return maxBattlestars;
		} else {
			return maxBattlestars - 1;
		}

	}

	@Override
	public void sendAllShipsToStock(Planet origin) throws RemoteException {
		System.out.println("Skynet is not sending ships to stock");
	}

}
