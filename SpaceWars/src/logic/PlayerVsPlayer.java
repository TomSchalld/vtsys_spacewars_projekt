package logic;

public class PlayerVsPlayer extends Game {

	public PlayerVsPlayer(String gameName) {
		super(gameName);
		this.players = new Player[2];
	}

	@Override
	protected void addPlayer(Player newPlayer) {
		
	}
	public void addPlayers(Player one, Player two){
		this.addPlayer(one);
		this.addPlayer(two);
	}

	

	

}
