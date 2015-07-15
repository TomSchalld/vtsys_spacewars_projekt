package logic;

import java.io.Serializable;
import java.rmi.RemoteException;

import clientServer.Client;

public abstract class Spaceship implements Serializable{
	protected final int ownerId;
	protected final Client owner;
	protected Planet orbiting;
	public Spaceship(Client owner) throws RemoteException{
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
	public Client getOwner() {
		return owner;
	}
	public abstract int attack();
	
	public abstract boolean equals(Object obj);
	
	
}
