package logic;

import java.util.Map;

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
	private boolean playersReady(){
		if(this.players[0].isPlayerReady()==true&&this.players[1].isPlayerReady()==true){
			return true;
		}
		return false;
	}
	private void setPlayersUnready(){
		this.players[0].setPlayerReady(false);
		this.players[1].setPlayerReady(false);				
	}
	@Override
	protected RoundReport endRound() {
		if(this.playersReady()){
			RoundReport report = new RoundReport();
			Map<String,Planet>planets = this.getUniverse().getPlanets();
			for(Planet p: planets.values()){
				if(p.isFightAfterRoundEnded()){
					report.addBattleReport(p.fight());
				}
			}
			for(Planet p: planets.values()){
				if(!p.getShipsInOrbit().isEmpty()){
					p.getShipsInOrbit().get(0).getOwner().addCash(p.getGeneratedCredits());
				}
			}

			this.setPlayersUnready();
			this.round++;
			if(this.players[0].amountOfPlanets==this.getUniverse().getPlanets().size()){
				this.gameFinished = true;
				this.winner = this.players[0];
			}else if(this.players[1].amountOfPlanets==this.getUniverse().getPlanets().size()){
				this.gameFinished = true;
				this.winner = this.players[1];
			}
			return report;
		}
		return null;
	}
	

	

	

}
