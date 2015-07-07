package logic;

public class PlayerVsPC extends Game {

	public PlayerVsPC(String gameName) {
		super(gameName);
		this.players = new Player[2];
	}

	@Override
	protected void addPlayer(Player newPlayer) {
		this.players[0]= newPlayer;
		this.players[1]=new Computer();
	}

	

	
	
}
