package logic;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class RoundReport implements Report {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String,BattleReport> reports;
	private final int round;
	/**
	 * @return
	 */
	public Map<String, BattleReport> getReports() {
		return reports;
	}
	/**
	 * @param round
	 */
	public RoundReport(int round){
		this.reports=new HashMap<String,BattleReport>();
		this.round = round;
	}
	/**
	 * @return
	 */
	public int getRoundNumber(){
		return this.round;
	}
	/**
	 * @param report
	 */
	private void addBattleReport(BattleReport report){
		this.reports.put(report.getNameOfbattleGround(), report);
	}
	/**
	 * @param nameOfBattleGround
	 * @return
	 */
	public BattleReport getBattleReport(String nameOfBattleGround){
		return reports.get(nameOfBattleGround);
	}
	/* (non-Javadoc)
	 * @see logic.Report#addReport(logic.Report)
	 */
	@Override
	public void addReport(Report report) {
		this.addBattleReport((BattleReport)report);
	}
	
	/**
	 * @return
	 */
	public JSONObject exportRoundToJSON(){
		JSONObject jo = new JSONObject();
		JSONObject val;
		jo.put("roundNumber", this.round);
		for(BattleReport br:this.reports.values()){
			val = new JSONObject();
			val.put("winner", br.getWinnersUsername());
			val.put("defeatedShips",br.getListOfDefeats());
			val.put("fighterLeft", br.getFighterAfterBattle());
			val.put("battlestarsLeft", br.getBattlestarsAfterBattle());
			jo.put(br.getNameOfbattleGround(), val);

		}
		return jo;
	}
	
	
}
