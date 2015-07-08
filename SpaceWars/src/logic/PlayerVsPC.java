package logic;

public class PlayerVsPC extends Game {

	public PlayerVsPC(String gameName,int universeSize) {
		super(gameName, universeSize);
		this.players = new Player[2];
	}

	@Override
	protected void addPlayer(Player newPlayer) {
		newPlayer.setGamePlaying(this);
		this.players[0]= newPlayer;
		this.players[1]=new Computer(this);
	}

	@Override
	protected void endRound() {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
