package logic;

import java.io.Serializable;
import java.rmi.RemoteException;

import javax.print.attribute.standard.MediaSize.Other;

import clientServer.Client;

public abstract class Spaceship implements Serializable{
	protected final int ownerId;
	protected final Client owner;
	protected Planet orbiting;
	static int shipID=0;
	private int shipNumber;
	public Spaceship(Client owner) throws RemoteException{
		this.ownerId=owner.getOwnerId();
		this.owner=owner;
		shipNumber = shipID++;
	}
	public int getShipID(){
		return this.shipNumber;
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
	
	@Override
	public String toString() {
		String s ="";
		if(this instanceof Fighter){
			s+= "Fighter von: ";
		}else{
			s+= "Battlestar von: ";
		}
		try {
			s+=this.getOwner().getUsername()+" mit iD: "+this.getShipID()+" im Orbit von: ";
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(this.getOrbiting()!=null){
			s+= this.getOrbiting().getName();
		}else{
			s+= "Ship ist in Stock";
		}
		return s;
	}
	@Override
	public boolean equals(Object other){
		
		try {
			if(this.getOwner().equals(((Spaceship)other).getOwner())){
				if(this.getOrbiting().equals(((Spaceship) other).getOrbiting())){
					if (this.getShipID() == ((Spaceship) other).getShipID()) {
						if (this instanceof Fighter) {
							if (other instanceof Fighter) {
								return true;
							}
						}else if(this instanceof Battlestar){
							if(other instanceof Battlestar){
								return true;
							}
						}
						
					}
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
