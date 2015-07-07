package logik;

public class Spieler {
	protected String username;
	protected int ownerId;
	private static int userCount=0;
	public Spieler(String username){
		this.username=username;
		this.ownerId=userCount;
		userCount++;
	}
}
