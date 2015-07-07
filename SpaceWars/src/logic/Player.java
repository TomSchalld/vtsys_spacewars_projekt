package logic;

public class Player {
	protected String username;
	protected int ownerId;
	private static int userCount=0;
	public Player(String username){
		this.username=username;
		this.ownerId=userCount;
		userCount++;
	}
}
