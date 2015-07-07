package logic;

public abstract class Player {
	protected String username;
	protected int ownerId;
	private static int userCount=0;
	protected int cash;
	protected int amountOfPlanets;
	protected Game gamePlaying;
	public Player(String username, Game gamePlaying){
		this.username=username;
		this.ownerId=userCount;
		this.cash = 10000;
		this.gamePlaying =gamePlaying;
		userCount++;
	}
	public void addCash(int cash){
		this.cash+=cash;
	}
	public int getCash(){
		return this.cash;
	}
	public Game getGamePlaying() {
		return gamePlaying;
	}
	public void setGamePlaying(Game gamePlaying) {
		this.gamePlaying = gamePlaying;
	}
	protected abstract void sendShips();
	protected abstract void buyBattlestar();
	protected abstract void buyFighter();
}
