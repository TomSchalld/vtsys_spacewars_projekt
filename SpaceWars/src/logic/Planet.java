package logic;

import java.util.ArrayList;
import java.util.List;

public class Planet {
	private String name;
	private List<Spaceship> shipsInOrbit;
	private List<Spaceship> shipsTryToOrbit;
	private int generatedCreditsPerShip;
	private int generatedCredits;
	private int planetId;
	private boolean fightAfterRoundEnded = false;
	public Planet(String planetName,int planetId){
		this.name = planetName;
		this.planetId=planetId;
		this.generatedCreditsPerShip = (int)Math.random()*200+50;
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
	public void addShipToOrbit(Spaceship newShip){
		if(this.shipsInOrbit.isEmpty()){
			this.shipsInOrbit.add(newShip);
		}else{
			if(this.shipsInOrbit.size()<6){
				if(this.shipsInOrbit.get(0).getOwnerId()==newShip.getOwnerId()){
					this.shipsInOrbit.add(newShip);
				}else{
					this.shipsTryToOrbit.add(newShip);
					this.setFightAfterRoundEnded(true);
				}
			}
		}
	}
	public boolean removeShipFromOrbit(Spaceship shipToRemove){
		int i=0;
		if(this.shipsInOrbit.size()>0){
			for(Spaceship s:shipsInOrbit){
				if(s.getClass()==shipToRemove.getClass()){
					this.shipsInOrbit.remove(i);
				}
				i++;
			}
			return true;
		}
		return false;
	}
	public BattleReport fight(){
		BattleReport report = new BattleReport();
		int countOfAtackingFighters =0;
		int countOfAtackingBattleships =0;		
		int countOfDefendingFighters =0;
		int countOfDefendingBattleships =0;
		
		for(Spaceship s:this.shipsInOrbit){
			if(s instanceof Battlestar){
				countOfDefendingBattleships++;
			}else{
				countOfDefendingFighters++;
			}
		}
		for(Spaceship s:this.shipsTryToOrbit){
			if(s instanceof Battlestar){
				countOfAtackingBattleships++;
			}else{
				countOfAtackingFighters++;
			}
		}
		if()
		
		
		this.setFightAfterRoundEnded(false);
		return report;
	}
	
}
