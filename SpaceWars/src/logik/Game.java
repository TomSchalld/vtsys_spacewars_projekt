package logik;

public abstract class Game {
	protected String gameName;
	protected int gameId;
	private static int gameCount=0;
	public Game(String gameName){
		this.gameName=gameName;
		this.gameId=gameCount;
		gameCount++;
	}
}
