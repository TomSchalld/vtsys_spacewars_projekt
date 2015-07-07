package logic;

public class Planet {
	private String name;
	private Spaceship[] shipsInOrbit;
	private int generatedCreditsPerShip;
	private int generatedCredits;
	private int planetId;
	public Planet(String planetName,int planetId){
		this.name = planetName;
		this.planetId=planetId;
		this.generatedCreditsPerShip = (int)Math.random()*200+50;
		this.shipsInOrbit = new Spaceship[5];
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
	public Spaceship[] getShipsInOrbit() {
		return shipsInOrbit;
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
	public boolean addShipToOrbit(Spaceship newShip){
		int i=0;
		if(this.shipsInOrbit.length<5){
			for(Spaceship s:shipsInOrbit){
				if(s==null){
					this.shipsInOrbit[i]=newShip;
				}
				i++;
			}
			return true;
		}
		return false;
	}
	public boolean removeShipFromOrbit(Spaceship shipToRemove){
		int i=0;
		if(this.shipsInOrbit.length>0){
			for(Spaceship s:shipsInOrbit){
				if(s.getClass()==shipToRemove.getClass()){
					this.shipsInOrbit[i]=null;
				}
				i++;
			}
			return true;
		}
		return false;
	}
}
