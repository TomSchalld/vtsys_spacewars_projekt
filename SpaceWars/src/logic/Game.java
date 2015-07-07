package logic;

public abstract class Game {
	protected String gameName;
	protected int gameId;
	protected Player[] players;
	private static int gameCount=0;
	public Game(String gameName){
		this.gameName=gameName;
		this.gameId=gameCount;
		gameCount++;
	}
	protected abstract void addPlayer(Player newPlayer);
}
