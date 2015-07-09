package logic;

import java.util.LinkedList;
import java.util.List;

public class EndReport implements Report {
	List<Report> roundReports;
	Player winner[];
	Player looser[];
	public EndReport(){
		this.roundReports = new LinkedList<Report>();
	}
	public void addWinner(Player winner){
		this.winner = new Player[1];
		this.winner[0]=winner;
	}
	public void addWinner(Player winner, Player otherWinner){
		this.winner = new Player[2];
		this.winner[0]=winner;
		this.winner[1]=otherWinner;
	}
	public void addLooser(Player looser){
		this.looser = new Player[1];
		this.looser[0]=looser;
	}
	public void addLooser(Player looser, Player otherLooser){
		this.looser = new Player[2];
		this.looser[0]=looser;
		this.looser[1]=otherLooser;
	}
	private void addRoundReport(Report roundReport){
		this.roundReports.add(roundReport);
	}
	@Override
	public void addReport(Report report) {
		this.addRoundReport((RoundReport)report);
	}
	@Override
	public String toString() {
		String s="";
		for(Player p:this.winner){
			s+=p.toString()+" ";
		}
		s+="\n";
		for(Player p:this.looser){
			s+=p.toString()+" ";
		}
		for(Report r:this.roundReports){
			s+=r.toString()+"\n";
		}
		return s;
	}
}
