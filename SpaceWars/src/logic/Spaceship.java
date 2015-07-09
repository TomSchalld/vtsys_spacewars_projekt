package logic;

import java.io.Serializable;

public abstract class Spaceship implements Serializable{
	protected final int ownerId;
	protected final Player owner;
	protected Planet orbiting;
	public Spaceship(Player owner){
		this.ownerId=owner.getOwnerId();
		this.owner=owner;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public Planet getOrbiting() {
		return orbiting;
	}
	public void setOrbiting(Planet orbiting) {
		this.orbiting = orbiting;
	}
	public Player getOwner() {
		return owner;
	}
	public abstract int attack();
	
}
