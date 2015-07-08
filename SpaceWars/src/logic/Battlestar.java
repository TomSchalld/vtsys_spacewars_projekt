package logic;

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
	public Battlestar(Player owner) {
		super(owner);
	}
	public static int getPrice() {
		return price;
	}
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
		return (int)Math.random()*200+this.rank.getAttackBoost();
	}

}
