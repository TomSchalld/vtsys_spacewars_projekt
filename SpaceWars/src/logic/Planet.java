package logic;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import clientServer.Client;

public class Planet extends UnicastRemoteObject implements PlanetIf {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Client planetOwner;
	private String name;
	private List<SpaceshipIf> shipsInOrbit;
	private List<SpaceshipIf> shipsTryToOrbit;
	protected int fighterInOrbit = 0;
	protected int battlestarsInOrbit = 0;
	protected int generatedCreditsPerShip;
	protected int generatedCredits;
	private int planetId;
	private boolean fightAfterRoundEnded = false;

	/**Creates new Planet
	 * @param planetName
	 * @param planetId
	 * @throws RemoteException
	 */
	public Planet(String planetName, int planetId) throws RemoteException {
		this.name = planetName;
		this.planetId = planetId;
		this.generatedCreditsPerShip = (int) Math.random() * 450 + 150;
		this.shipsInOrbit = new LinkedList<SpaceshipIf>();
		this.shipsTryToOrbit = new LinkedList<SpaceshipIf>();
	}

	/* (non-Javadoc)
	 * @see java.rmi.server.RemoteObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.planetId;
	}

	/* (non-Javadoc)
	 * @see java.rmi.server.RemoteObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planet other = (Planet) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (planetId != other.planetId)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getName()
	 */
	@Override
	public String getName() throws RemoteException {
		return name;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getGeneratedCreditsPerShip()
	 */
	@Override
	public int getGeneratedCreditsPerShip() throws RemoteException {
		return generatedCreditsPerShip;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getGeneratedCredits()
	 */
	@Override
	public int getGeneratedCredits() throws RemoteException {
		return generatedCredits;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getPlanetId()
	 */
	@Override
	public int getPlanetId() throws RemoteException {
		return planetId;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#isFightAfterRoundEnded()
	 */
	@Override
	public boolean isFightAfterRoundEnded() throws RemoteException {
		return fightAfterRoundEnded;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#setFightAfterRoundEnded(boolean)
	 */
	@Override
	public void setFightAfterRoundEnded(boolean fightAfterRoundEnded) throws RemoteException {
		this.fightAfterRoundEnded = fightAfterRoundEnded;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getShipsInOrbit()
	 */
	@Override
	public List<SpaceshipIf> getShipsInOrbit() throws RemoteException {
		return shipsInOrbit;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getShipsTryToOrbit()
	 */
	@Override
	public List<SpaceshipIf> getShipsTryToOrbit() throws RemoteException {
		return shipsTryToOrbit;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getPlanetOwner()
	 */
	@Override
	public Client getPlanetOwner() throws RemoteException {
		return planetOwner;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#setPlanetOwner(clientServer.Client)
	 */
	@Override
	public void setPlanetOwner(Client planetOwner) throws RemoteException {
		this.planetOwner = planetOwner;
		planetOwner.setAmountOfPlanets(planetOwner.getAmountOfPlanets()+1);
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#addShipToOrbit(logic.SpaceshipIf)
	 */
	@Override
	public void addShipToOrbit(SpaceshipIf newShip) throws RemoteException {
		if (newShip != null) {
			System.out.println("newShip ownerId: " + newShip.getOwner().getOwnerId());
			if (this.getPlanetOwner() == null
					|| this.getPlanetOwner().getOwnerId() == newShip.getOwner().getOwnerId()) {
				if (this.getShipsInOrbit().size() < 5) {
					this.shipsInOrbit.add(newShip);
					this.setPlanetOwner(newShip.getOwner());
					this.generatedCredits = this.generatedCreditsPerShip * this.getShipsInOrbit().size();
					System.out.println(newShip.shipInfo() + " zu Orbit hinzugefügt planet: " + this.getName());
				}

			} else {

				this.shipsTryToOrbit.add(newShip);
				this.setFightAfterRoundEnded(true);
				System.out.println(newShip.shipInfo() + " zu tryTo Orbit hinzugefügt planet: " + this.getName());

			}

		}

	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#removeShipFromOrbit(logic.SpaceshipIf)
	 */
	@Override
	public boolean removeShipFromOrbit(SpaceshipIf shipToRemove) throws RemoteException {
		return this.shipsInOrbit.remove(shipToRemove);

	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#delShips()
	 */
	@Override
	public void delShips() throws RemoteException {
		this.shipsInOrbit.clear();
		this.shipsTryToOrbit.clear();
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#roundEnd()
	 */
	@Override
	public void roundEnd() throws RemoteException {
		this.fighterInOrbit = 0;
		this.battlestarsInOrbit = 0;
		System.out.println("[ round end kein kampf\n");
		for (SpaceshipIf s : this.getShipsInOrbit()) {
			if (s.isFighter()) {
				this.fighterInOrbit++;
				System.out.println("Fighter " + s.shipInfo());
			} else {
				this.battlestarsInOrbit++;
				System.out.println("Battlestar " + s.shipInfo());
			}
		}
		
		System.out.println("\t\t]");
		System.out.println("-----------------------------------ships in Orbit of planet: " + this.getName()
				+ "--------------------------------------");
		for (SpaceshipIf s : this.getShipsInOrbit()) {
			System.out.println(s.shipInfo());
		}
		System.out.println("Anzahl battlestar in orbit: " + this.battlestarsInOrbit);
		System.out.println("Anzahl fighter in orbit: " + this.fighterInOrbit);
		if(this.planetOwner!=null){
			this.planetOwner.setAmountOfPlanets(this.planetOwner.getAmountOfPlanets()+1);
		}
		System.out.println("-----------------------------------ende--------------------------------------");
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#fight()
	 */
	@Override
	public BattleReport fight() throws RemoteException {
		Client attacker = this.getShipsTryToOrbit().get(0).getOwner();
		Client defender = this.planetOwner;
		BattleReport report = new BattleReport(this);
		List<SpaceshipIf> shipsDefeated = new LinkedList<SpaceshipIf>();
		this.fighterInOrbit = 0;
		this.battlestarsInOrbit = 0;
		System.out.println(this.getName() + " kampf startet");
		int angreiferAttack;
		int defendingShipAttack;
		for (SpaceshipIf angreifer : this.getShipsTryToOrbit()) {
			System.out.println("[for1]" + angreifer.shipInfo());
			for (SpaceshipIf defendingShip : this.getShipsInOrbit()) {
				System.out.println("[for2]" + defendingShip.shipInfo());
				if (!shipsDefeated.contains(angreifer)) {
					if (!shipsDefeated.contains(defendingShip)) {
						System.out.println(angreifer.shipInfo() + "ist angreifendes Schiff\n");
						System.out.println(defendingShip.shipInfo() + "ist verteidigendes Schiff\n");
						angreiferAttack = angreifer.attack();
						defendingShipAttack = defendingShip.attack();
						System.out.println(
								"angreiferAttack: " + angreiferAttack + " defendingShipAttack:" + defendingShipAttack);
						if (angreiferAttack <= defendingShipAttack) {
							System.out
									.println(defendingShip.getOwner().getUsername() + " hat gewonnen als defender mit: "
											+ defendingShipAttack + " zu : " + angreiferAttack);
							shipsDefeated.add(angreifer);
						} else {
							System.out.println(angreifer.getOwner().getUsername() + "hat gewonnen als atacker mit: "
									+ angreiferAttack + " zu : " + defendingShipAttack);
							shipsDefeated.add(defendingShip);
						}
					}
				}

			}
		}
		System.out.println();
		this.getShipsInOrbit().removeAll(shipsDefeated);
		this.getShipsTryToOrbit().removeAll(shipsDefeated);
		report.setDefeatedShips(shipsDefeated);
		if (this.getShipsInOrbit().isEmpty()) {
			this.getShipsInOrbit().addAll(this.getShipsTryToOrbit());
			this.getShipsTryToOrbit().clear();
			report.setWinnersUsername(attacker.getUsername());
			report.setLoosersUsername(defender.getUsername());
			this.setPlanetOwner(attacker);
			defender.setAmountOfPlanets(defender.getAmountOfPlanets()-1);
		} else {

			report.setWinnersUsername(defender.getUsername());
			report.setLoosersUsername(attacker.getUsername());
			defender.setAmountOfPlanets(defender.getAmountOfPlanets()+1);
		}
		this.setFightAfterRoundEnded(false);
		for (SpaceshipIf s : this.getShipsInOrbit()) {
			s.increaseRank();
			if (s.isFighter()) {
				report.addFighterAfterBattle();
				this.fighterInOrbit++;
				System.out.println(s.shipInfo());
			} else {
				report.addBattlestarsAfterBattle();
				this.battlestarsInOrbit++;
				System.out.println(s.shipInfo());

			}
		}
		System.out.println("-----------------------------------ships in Orbit of planet: " + this.getName()
				+ "--------------------------------------");
		for (SpaceshipIf s : this.getShipsInOrbit()) {
			System.out.println(s.shipInfo());
		}
		System.out.println("-----------------------------------ende--------------------------------------");

		return report;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getFighterInOrbit()
	 */
	@Override
	public int getFighterInOrbit() throws RemoteException {
		return fighterInOrbit;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getBattlestarsInOrbit()
	 */
	@Override
	public int getBattlestarsInOrbit() throws RemoteException {
		return battlestarsInOrbit;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getSecondOwner()
	 */
	@Override
	public Client getSecondOwner() throws RemoteException {
		return null;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getFighterInOrbit(clientServer.Client)
	 */
	
	@Override
	public int getFighterInOrbit(Client owner) throws RemoteException {

		return this.getFighterInOrbit();
	}	

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getBattlestarsInOrbit(clientServer.Client)
	 */
	@Override
	public int getBattlestarsInOrbit(Client owner) throws RemoteException {
		return this.getBattlestarsInOrbit();
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#isMultiPlanet()
	 */
	@Override
	public boolean isMultiPlanet() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#payCash()
	 */
	@Override
	public void payCash() throws RemoteException {
		this.getPlanetOwner().addCash(this.getGeneratedCreditsPerShip());
		
	}

	/* (non-Javadoc)
	 * @see logic.PlanetIf#getSecondOwnerOrbit()
	 */
	@Override
	public List<SpaceshipIf> getSecondOwnerOrbit() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
