package logic;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;

public class KI extends Human {

	public KI(String username, String serveraddress) throws MalformedURLException, RemoteException, NotBoundException {
		super(username, serveraddress);
		
	}

	public void ruleTheWorld() throws RemoteException {
		
		this.buyNewShips();
		this.sendShips();
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
	public void setPlayerReady(boolean playerReady) {
		super.setPlayerReady(playerReady);
		if(!playerReady){
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
		int shipsOnPlanetOfInterest;
		int shipsInStock = this.getStock().size();
		if (shipsInStock > 0) {
			if (shipsInStock >= 5) {
				randomPlanet = actual.getRandomPlanet();
				if (randomPlanet.getPlanetOwner() != null) {
					while (randomPlanet.getPlanetOwner().equals(this)) {
						randomPlanet = actual.getRandomPlanet();
						if(randomPlanet.getPlanetOwner()==null){
							break;
						}
					}
				}

				for (int i = 0; i < 5; i++) {
					this.sendShip(getRandomShip(), randomPlanet);
				}
			}else{
				for(int i=0;i<shipsInStock;i++){
					randomPlanet=actual.getRandomPlanet();
					if(randomPlanet.getPlanetOwner().equals(this)){
						i--;
					}else{
						sendShip(getRandomShip(), randomPlanet);
					}
				}
			}
		}

	}

	private void buyNewShips() throws RemoteException {
		int battlestarsToBuy = calculateBattlestars();
		int fightersToBuy;
		for (int i = 0; i < battlestarsToBuy; i++) {
			this.buyBattlestar();
		}
		fightersToBuy = calculateFighters();
		for (int i = 0; i < fightersToBuy; i++) {
			this.buyFighter();
		}

	}
	private Spaceship getRandomShip(){
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
}
