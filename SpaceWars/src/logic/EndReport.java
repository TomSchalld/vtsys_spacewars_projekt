package logic;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import clientServer.Client;

public class EndReport implements Report {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4004984865885813172L;
	public List<Report> roundReports; 
	public Client winner[];
	public Client looser[];
	public String gameName;
	private int roundCount =0;
	public EndReport(){
		this.roundReports = new LinkedList<Report>();
	}
	/**Adds a winner 
	 * @param winner
	 */
	public void addWinner(Client winner){
		this.winner = new Client[1];
		this.winner[0]=winner;
		try {
			this.gameName = winner.getGamePlaying().getGameName();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**Adds a winner team
	 * @param winner
	 * @param otherWinner
	 */
	public void addWinner(Client winner, Client otherWinner){
		this.winner = new Client[2];
		this.winner[0]=winner;
		this.winner[1]=otherWinner;
		try {
			this.gameName = winner.getGamePlaying().getGameName();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**Adds a looser 
	 * @param looser
	 */
	public void addLooser(Client looser){
		this.looser = new Client[1];
		this.looser[0]=looser;
	}
	/**adds two loosers
	 * @param looser
	 * @param otherLooser
	 */
	public void addLooser(Client looser, Client otherLooser){
		this.looser = new Client[2];
		this.looser[0]=looser;
		this.looser[1]=otherLooser;
	}
	/**adds a roundreport
	 * @param roundReport
	 */
	private void addRoundReport(Report roundReport){
		this.roundReports.add(roundReport);
		this.roundCount = ((RoundReport)roundReport).getRoundNumber();
	}
	/* (non-Javadoc)
	 * @see logic.Report#addReport(logic.Report)
	 */
	@Override
	public void addReport(Report report) {
		this.addRoundReport(report);
	}
	public String getWinner(){
		String winner = "";
		
		try {
			for(Client c: this.winner){
				winner += c.getUsername()+", ";
			}
			
		} catch (JSONException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return winner;

	}
	/**
	 * @return a JSONObject with the information of winner an looser and the reports of the whole game
	 */
	public JSONObject endReportToJSON(){
		JSONObject report = new JSONObject();
		String winner = "Gewinner: ";
		String looser = "Verlierer: ";
		try {
			for(Client c: this.winner){
				winner += c.getUsername()+", ";
			}
			
			report.put("winner", winner);
		} catch (JSONException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			for(Client c: this.looser){
				looser += c.getUsername()+", ";
			}
			
			report.put("looser", looser);
		} catch (JSONException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		report.put("roundReports", this.roundReports);
		report.put("roundCount", this.roundCount);
		
		
		return report;
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
