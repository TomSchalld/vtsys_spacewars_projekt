package logic;

import java.rmi.RemoteException;

import clientServer.Client;

public class Battlestar extends Spaceship {
	static final int price = 2000;
	private enum Rank{
		Capitan(10),
		ViceAdmiral(20),
		Admiral(40),
		AdmiralOfFleet(80);
		private int attackBoost;
		private Rank(int attackBoost){
			this.attackBoost=attackBoost;
		}
		public int getAttackBoost(){
			return this.attackBoost;
		}
		
	}
	private Rank rank=Rank.Capitan;
	public Battlestar(Client owner) throws RemoteException {
		super(owner);
		
		
	}
	public static int getPrice() {
		return price;
	}
	@Override
	public void increaseRank(){
		boolean rankIncreased = false;
		for(Rank r:Rank.values()){
			if(r.getAttackBoost()>this.rank.getAttackBoost()){
				if(!rankIncreased){
					this.rank=r;
					rankIncreased=true;
				}
			}
		}
	}
	@Override
	public int attack() {
		int random = (int)(Math.random()*100+this.rank.getAttackBoost());
		return random;
	}
	public String toString(){
	
		String s = super.toString()+" mit Rang ";
		s+=rank;
		return s;
	}
	@Override
	public String shipInfo() throws RemoteException {
		String s = "Battlestar von : ";
		
		try {
			s += this.getOwner().getUsername() + " mit iD: " + this.getShipID() + " ";
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (this.getOrbiting() != null) {
				try {
					s += "im Orbit von: "+ this.getOrbiting().getName();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				s += "Ship ist in Stock";
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s+=" mit Rang ";
		s+=rank;
		return s;
	}
	@Override
	public boolean isFighter() throws RemoteException {

		return false;
	}
	
}
