package logic;

import java.util.HashMap;
import java.util.Map;

public class RoundReport implements Report {
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
}
