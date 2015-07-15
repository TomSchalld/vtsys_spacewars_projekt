package logic;

import java.rmi.RemoteException;

import clientServer.Client;

public class Fighter extends Spaceship {
	static final int price = 200;
	static int fighterID=0;
	private int id;
	private enum Rank{
		Lieutenant(1),
		LieutenantCommander(2),
		Commander(4),
		Capitan(8);
		private int attackBoost;
		private Rank(int attackBoost){
			this.attackBoost=attackBoost;
		}
		public int getAttackBoost(){
			return this.attackBoost;
		}
	}
	private Rank rank=Rank.Lieutenant;
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
	public Fighter(Client owner) throws RemoteException {
		super(owner);
		this.id = fighterID;
		fighterID++;
	}
	public static int getPrice() {
		return price;
	}
	public String toString(){
		String s ="Fighter von ";
		try {
			s+=this.owner.getUsername()+" mit Rang ";
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s+=rank;
		return s;
	}
	@Override
	public int attack() {
		int random=(int)(Math.random()*10+this.rank.getAttackBoost());
		return random;
	}
	@Override
	public boolean equals(Object obj) {
		/*if(this.id==((Fighter)obj).id){
			return true;
		}*/
		return false;
	}
}
