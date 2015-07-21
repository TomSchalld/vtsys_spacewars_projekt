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
	private Client planetOwner;
	private String name;
	private List<SpaceshipIf> shipsInOrbit;
	private List<SpaceshipIf> shipsTryToOrbit;
	private int fighterInOrbit = 0;
	private int battlestarsInOrbit = 0;
	private int generatedCreditsPerShip;
	private int generatedCredits;
	private int planetId;
	private boolean fightAfterRoundEnded = false;

	public Planet(String planetName, int planetId) throws RemoteException{
		this.name = planetName;
		this.planetId = planetId;
		this.generatedCreditsPerShip = (int) Math.random() * 450 + 150;
		this.shipsInOrbit = new LinkedList<SpaceshipIf>();
		this.shipsTryToOrbit = new LinkedList<SpaceshipIf>();
	}

	@Override
	public int hashCode(){
		return this.planetId;
	}

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

	@Override public String getName() throws RemoteException{
		return name;
	}

	@Override public int getGeneratedCreditsPerShip() throws RemoteException{
		return generatedCreditsPerShip;
	}

	@Override public int getGeneratedCredits() throws RemoteException{
		return generatedCredits;
	}

	@Override public int getPlanetId() throws RemoteException{
		return planetId;
	}

	@Override public boolean isFightAfterRoundEnded() throws RemoteException{
		return fightAfterRoundEnded;
	}

	@Override public void setFightAfterRoundEnded(boolean fightAfterRoundEnded) throws RemoteException{
		this.fightAfterRoundEnded = fightAfterRoundEnded;
	}

	@Override public List<SpaceshipIf> getShipsInOrbit() throws RemoteException{
		return shipsInOrbit;
	}

	@Override public List<SpaceshipIf> getShipsTryToOrbit() throws RemoteException{
		return shipsTryToOrbit;
	}

	@Override public Client getPlanetOwner() throws RemoteException{
		return planetOwner;
	}

	@Override public void setPlanetOwner(Client planetOwner) throws RemoteException{
		this.planetOwner = planetOwner;
	}

	@Override 
	public void addShipToOrbit(SpaceshipIf newShip) throws RemoteException {
		if (newShip != null) {
			System.out.println("newShip ownerId: "+newShip.getOwner().getOwnerId());
			if(this.getPlanetOwner()!=null){
				System.out.println("newShip ownerId: "+this.getPlanetOwner().getOwnerId());
			}
			if (this.getPlanetOwner() == null || this.getPlanetOwner().getOwnerId()==newShip.getOwner().getOwnerId()) {
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

	@Override public boolean removeShipFromOrbit(SpaceshipIf shipToRemove) throws RemoteException{
		return this.shipsInOrbit.remove(shipToRemove);

	}

	@Override public void roundEnd() throws RemoteException {
		this.fighterInOrbit = 0;
		this.battlestarsInOrbit = 0;
		System.out.println("[ round end kein kampf\n");
		for (SpaceshipIf s : this.getShipsInOrbit()) {
			if (s instanceof Fighter) {
				this.fighterInOrbit++;
				System.out.println("Fighter "+s.shipInfo());
			} else {
				this.battlestarsInOrbit++;
				System.out.println("Battlestar "+s.shipInfo());
			}
		}
		System.out.println("\t\t]");
		System.out.println("-----------------------------------ships in Orbit of planet: " + this.getName()
				+ "--------------------------------------");
		for (SpaceshipIf s : this.getShipsInOrbit()) {
			System.out.println(s.shipInfo());
		}
		System.out.println("Anzahl battlestar in orbit: "+this.battlestarsInOrbit);
		System.out.println("Anzahl fighter in orbit: "+this.fighterInOrbit);
		System.out.println("-----------------------------------ende--------------------------------------");
	}

	@Override public BattleReport fight() throws RemoteException {
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
		} else {

			report.setWinnersUsername(defender.getUsername());
			report.setLoosersUsername(attacker.getUsername());
		}
		this.setFightAfterRoundEnded(false);
		for (SpaceshipIf s : this.getShipsInOrbit()) {
			s.increaseRank();
			if (s instanceof Fighter) {
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

	@Override public int getFighterInOrbit() throws RemoteException{
		return fighterInOrbit;
	}

	@Override public int getBattlestarsInOrbit() throws RemoteException{
		return battlestarsInOrbit;
	}

}
