package logic;

import java.util.LinkedList;
import java.util.List;

import clientServer.Client;

public class EndReport implements Report {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4004984865885813172L;
	List<Report> roundReports;
	Client winner[];
	Client looser[];
	public EndReport(){
		this.roundReports = new LinkedList<Report>();
	}
	public void addWinner(Client winner){
		this.winner = new Client[1];
		this.winner[0]=winner;
	}
	public void addWinner(Client winner, Client otherWinner){
		this.winner = new Client[2];
		this.winner[0]=winner;
		this.winner[1]=otherWinner;
	}
	public void addLooser(Client looser){
		this.looser = new Client[1];
		this.looser[0]=looser;
	}
	public void addLooser(Client looser, Client otherLooser){
		this.looser = new Client[2];
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
		for(Client p:this.winner){
			s+=p.toString()+" ";
		}
		s+="\n";
		for(Client p:this.looser){
			s+=p.toString()+" ";
		}
		for(Report r:this.roundReports){
			s+=r.toString()+"\n";
		}
		return s;
	}
}
