package logic;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Map;

public class PlayerVsPlayer extends Game {
	
	public PlayerVsPlayer(String gameName, int universeSize) throws RemoteException {
		super(gameName,universeSize);
		this.players = new Player[2];
		
	}

	@Override
	public void addPlayer(Player newPlayer) {
		if(this.players[0]==null){
			this.players[0]=newPlayer;
		}else{
			this.players[1]=newPlayer;
			this.hasEnoughPlayer=true;
		}
		newPlayer.setGamePlaying(this);

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
	public RoundReport endRound() {
		if(this.playersReady()){
			RoundReport report = new RoundReport();
			Map<String,Planet>planets = this.getUniverse().getPlanets();
			for(Planet p: planets.values()){
				if(p.isFightAfterRoundEnded()){
					report.addReport(p.fight());
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
			this.endReport.addReport(report);
			return report;
		}
		return null;
	}

}
