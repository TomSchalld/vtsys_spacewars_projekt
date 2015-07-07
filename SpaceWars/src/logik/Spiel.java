package logik;

public class Spiel {
	protected String gameName;
	protected int gameId;
	private static int gameCount=0;
	public Spiel(String gameName){
		this.gameName=gameName;
		this.gameId=gameCount;
		gameCount++;
	}
}
