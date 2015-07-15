package logic;

import java.rmi.RemoteException;

import clientServer.Client;

public class Fighter extends Spaceship {
	static final int price = 200;
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
	public Fighter(Client owner) throws RemoteException {
		super(owner);
	}
	public static int getPrice() {
		return price;
	}
	public String toString(){
		String s = super.toString()+" mit Rang ";
		s+=rank;
		return s;
	}
	@Override
	public int attack() {
		int random=(int)(Math.random()*10+this.rank.getAttackBoost());
		return random;
	}
	
}
