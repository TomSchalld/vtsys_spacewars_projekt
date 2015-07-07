package logic;

public class PlayerVsPlayer extends Game {

	public PlayerVsPlayer(String gameName, int universeSize) {
		super(gameName,universeSize);
		this.players = new Player[2];
		
	}

	@Override
	protected void addPlayer(Player newPlayer) {
		if(this.players[0]==null){
			this.players[0]=newPlayer;
		}else if(this.players[1]==null){
			this.players[1]=newPlayer;
		}
	}
	public void addPlayers(Player one, Player two){
		one.setGamePlaying(this);
		two.setGamePlaying(this);
		this.addPlayer(one);
		this.addPlayer(two);
	}

	

	

}
