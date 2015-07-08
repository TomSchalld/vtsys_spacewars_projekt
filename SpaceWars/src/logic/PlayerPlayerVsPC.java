package logic;

public class PlayerPlayerVsPC extends Game {

	public PlayerPlayerVsPC(String gameName, int universeSize) {
		super(gameName,universeSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addPlayer(Player newPlayer) {
		if (this.players[0] == null) {
			this.players[0] = newPlayer;
		} else if (this.players[1] == null) {
			this.players[1] = newPlayer;
		} else if (this.players[2] == null) {
			this.players[2] = newPlayer;
		}
	}
	public void addPlayers(Player one, Player two){
		one.setGamePlaying(this);
		two.setGamePlaying(this);
		this.addPlayer(one);
		this.addPlayer(two);
		this.addPlayer(new Computer(this));
	}

	@Override
	protected void endRound() {
		// TODO Auto-generated method stub
		
	}

}
