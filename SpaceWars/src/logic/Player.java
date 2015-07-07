package logic;

public abstract class Player {
	protected String username;
	protected int ownerId;
	private static int userCount=0;
	protected int cash;
	public Player(String username){
		this.username=username;
		this.ownerId=userCount;
		this.cash = 10000;
		userCount++;
	}
	public void addCash(int cash){
		this.cash+=cash;
	}
	public int getCash(){
		return this.cash;
	}
	
	protected abstract void sendShips();
	protected abstract void buyBattlestar();
	protected abstract void buyFighter();
}
