package logic;

public abstract class Game {
	protected String gameName;
	protected int gameId;
	protected Player[] players;
	protected Universe universe;
	protected int round;
	private static int gameCount=0;
	public Game(String gameName, int universeSize){
		this.gameName=gameName;
		this.gameId=gameCount;
		this.universe=new Universe(universeSize);
		this.round = 0;
		gameCount++;
	}
	public Universe getUniverse() {
		return universe;
	}
	protected abstract void addPlayer(Player newPlayer);
	protected abstract RoundReport endRound();
}
