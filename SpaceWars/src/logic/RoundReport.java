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
	public RoundReport(){
		this.reports=new HashMap<String,BattleReport>();
	}
	private void addBattleReport(BattleReport report){
		this.reports.put(report.getNameOfbattleGround(), report);
	}
	public BattleReport getBattleReport(String nameOfBattleGround){
		return reports.get(nameOfBattleGround);
	}
	@Override
	public void addReport(Report report) {
		this.addBattleReport((BattleReport)report);
	}
	public JSONObject exportRoundToJSON(){
		JSONObject jo = new JSONObject();
		JSONObject val;
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
