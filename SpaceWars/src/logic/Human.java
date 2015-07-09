package logic;

public class Human extends Player {
	private String chatMessage;
	private boolean hasNewMessage;
	public Human(String username, Game gamePlaying) {
		super(username, gamePlaying);
	}

}
