package logic;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import clientServer.Client;

public class Planet implements Serializable {
	private Client planetOwner;
	private String name;
	private List<Spaceship> shipsInOrbit;
	private List<Spaceship> shipsTryToOrbit;
	private int fighterInOrbit = 0;
	private int battlestarsInOrbit = 0;
	private int generatedCreditsPerShip;
	private int generatedCredits;
	private int planetId;
	private boolean fightAfterRoundEnded = false;

	public Planet(String planetName, int planetId) {
		this.name = planetName;
		this.planetId = planetId;
		this.generatedCreditsPerShip = (int) Math.random() * 200 + 50;
		this.shipsInOrbit = new LinkedList<Spaceship>();
		this.shipsTryToOrbit = new LinkedList<Spaceship>();
	}

	@Override
	public int hashCode() {
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

	public String getName() {
		return name;
	}

	public int getGeneratedCreditsPerShip() {
		return generatedCreditsPerShip;
	}

	public int getGeneratedCredits() {
		return generatedCredits;
	}

	public int getPlanetId() {
		return planetId;
	}

	public boolean isFightAfterRoundEnded() {
		return fightAfterRoundEnded;
	}

	public void setFightAfterRoundEnded(boolean fightAfterRoundEnded) {
		this.fightAfterRoundEnded = fightAfterRoundEnded;
	}

	public List<Spaceship> getShipsInOrbit() {
		return shipsInOrbit;
	}

	public List<Spaceship> getShipsTryToOrbit() {
		return shipsTryToOrbit;
	}

	public Client getPlanetOwner() {
		return planetOwner;
	}

	public void setPlanetOwner(Client planetOwner) {
		this.planetOwner = planetOwner;
	}

	public void addShipToOrbit(Spaceship newShip) throws RemoteException {
		if (this.shipsInOrbit.isEmpty()) {
			this.shipsInOrbit.add(newShip);
			this.setPlanetOwner(newShip.getOwner());
			this.generatedCredits = this.getGeneratedCreditsPerShip();
			System.out.println(
					this.getName() + " schiff von " + newShip.getOwner().getUsername() + " zu Orbit hinzugefügt");
		} else {
			if (this.shipsInOrbit.size() <= 5) {
				if (this.shipsInOrbit.get(0).getOwner().equals(newShip.getOwner())) {
					this.shipsInOrbit.add(newShip);
					this.generatedCredits = this.generatedCreditsPerShip * this.getShipsInOrbit().size();
					System.out.println(this.getName() + " schiff von " + newShip.getOwner().getUsername()
							+ " zu Orbit hinzugefügt");

				} else {
					this.shipsTryToOrbit.add(newShip);
					this.setFightAfterRoundEnded(true);
					System.out.println(this.getName() + " schiff von " + newShip.getOwner().getUsername()
							+ " zu tryTo Orbit hinzugefügt");

				}
			}
		}
	}

	public boolean removeShipFromOrbit(Spaceship shipToRemove) {
		return this.shipsInOrbit.remove(shipToRemove);

	}

	public void roundEnd() throws RemoteException {
		for (Spaceship s : this.getShipsInOrbit()) {
			if (s instanceof Fighter) {
				this.fighterInOrbit++;
				System.out.println(s);
			} else {
				this.battlestarsInOrbit++;
				System.out.println(s);

			}
		}
	}

	public BattleReport fight() throws RemoteException {
		Client attacker = this.getShipsTryToOrbit().get(0).getOwner();
		Client defender = this.planetOwner;
		BattleReport report = new BattleReport(this);
		List<Spaceship> shipsDefeated = new LinkedList<Spaceship>();
		this.fighterInOrbit = 0;
		this.battlestarsInOrbit = 0;
		System.out.println(this.getName() + " kampf startet");
		int sAttack;
		int dAttack;
		for (Spaceship angreifer : this.getShipsTryToOrbit()) {

			for (Spaceship d : this.getShipsInOrbit()) {
				if (!shipsDefeated.contains(angreifer)) {
					if (!shipsDefeated.contains(d)) {
						sAttack = angreifer.attack();
						dAttack = d.attack();
						System.out.println("sAttack: " + sAttack + " dAttack:" + dAttack);
						if (sAttack <= dAttack) {
							System.out.println(d.getOwner().getUsername() + " hat gewonnen als defender mit: " + dAttack
									+ " zu : " + sAttack);
							shipsDefeated.add(angreifer);
						} else {
							System.out.println(angreifer.getOwner().getUsername() + "hat gewonnen als atacker mit: "
									+ dAttack + " zu : " + sAttack);
							shipsDefeated.add(d);
						}
					}
				}

			}
		}
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
		for (Spaceship s : this.getShipsInOrbit()) {
			if (s instanceof Fighter) {
				report.addFighterAfterBattle();
				this.fighterInOrbit++;
				System.out.println(s);
			} else {
				report.addBattlestarsAfterBattle();
				this.battlestarsInOrbit++;
				System.out.println(s);

			}
		}
		System.out.println("-----------------------------------ships in Orbit of planet: "+this.getName()+"--------------------------------------");
		for(Spaceship s:this.getShipsInOrbit()){
			System.out.println(s);
		}
		System.out.println("-----------------------------------ende--------------------------------------");

		return report;
	}

	public int getFighterInOrbit() {
		return fighterInOrbit;
	}

	public int getBattlestarsInOrbit() {
		return battlestarsInOrbit;
	}

}
