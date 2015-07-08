package logic;

import java.util.HashMap;
import java.util.Map;

public class RoundReport {
	Map<String,BattleReport> reports;
	public RoundReport(){
		this.reports=new HashMap<String,BattleReport>();
	}
	public void addBattleReport(BattleReport report){
		this.reports.put(report.getNameOfbattleGround(), report);
	}
}
