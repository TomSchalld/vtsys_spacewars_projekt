package logic;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import clientServer.Client;

public class Planet implements Serializable{
	private Client planetOwner;
	private String name;
	private List<Spaceship> shipsInOrbit;
	private List<Spaceship> shipsTryToOrbit;
	private int fighterInOrbit=0;
	private int battlestarsInOrbit=0;
	private int generatedCreditsPerShip;
	private int generatedCredits;
	private int planetId;
	private boolean fightAfterRoundEnded = false;

	public Planet(String planetName, int planetId) {
		this.name = planetName;
		this.planetId = planetId;
		this.generatedCreditsPerShip = (int) Math.random() * 200 + 50;
		this.shipsInOrbit = new ArrayList<Spaceship>();
		this.shipsTryToOrbit = new ArrayList<Spaceship>();
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

	public void addShipToOrbit(Spaceship newShip) {
		if (this.shipsInOrbit.isEmpty()) {
			this.shipsInOrbit.add(newShip);
			this.setPlanetOwner(newShip.getOwner());
			this.generatedCredits = this.getGeneratedCreditsPerShip();
		} else {
			if (this.shipsInOrbit.size() < 5) {
				if (this.shipsInOrbit.get(0).getOwnerId() == newShip.getOwnerId()) {
					this.shipsInOrbit.add(newShip);
					this.generatedCredits = this.generatedCreditsPerShip * this.getShipsInOrbit().size();
				} else {
					this.shipsTryToOrbit.add(newShip);
					this.setFightAfterRoundEnded(true);
				}
			}
		}
	}

	public boolean removeShipFromOrbit(Spaceship shipToRemove) {
		return this.shipsInOrbit.remove(shipToRemove);
		
	}
	
	public BattleReport fight() throws RemoteException {
		Client attacker = this.getShipsTryToOrbit().get(0).getOwner();
		Client defender = this.planetOwner;
		BattleReport report = new BattleReport(this);
		List<Spaceship> shipsDefeated = new ArrayList<Spaceship>();
		this.fighterInOrbit =0;
		this.battlestarsInOrbit =0;
		for (Spaceship s : this.getShipsTryToOrbit()) {
			if (!shipsDefeated.contains(s)) {
				for (Spaceship d : this.getShipsInOrbit()) {
					if (s.attack() < d.attack()) {
						shipsDefeated.add(s);
						break;
					} else {
						shipsDefeated.add(d);
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
			if(s instanceof Fighter){
				report.addFighterAfterBattle();
				this.fighterInOrbit++;
			}else{
				report.addBattlestarsAfterBattle();
				this.battlestarsInOrbit++;
			}
		}
		return report;
	}

	public int getFighterInOrbit() {
		return fighterInOrbit;
	}

	public int getBattlestarsInOrbit() {
		return battlestarsInOrbit;
	}

}
