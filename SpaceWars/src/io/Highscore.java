package io;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import clientServer.Client;
import logic.BattleReport;
import logic.EndReport;
import logic.Report;
import logic.RoundReport;
import logic.Spaceship;

public class Highscore {
	


	
	public void generateHighscore(EndReport endreport) throws RemoteException {
		Date date;
		int defeatedShipsCount;
		EndReport tmpEnd=endreport;
		List<Report> rRep = tmpEnd.roundReports;
		Map<String,BattleReport> bRep;
		List<Spaceship> sRep;
		for(Client e:tmpEnd.winner){
			e.getUsername();
			date=new Date();
			
			/****** fill defeatedShipsCount **********/
			for(Report rp:rRep){
				bRep=((RoundReport)rp).getReports();
				for(BattleReport br:bRep.values()){
					sRep=br.getDefeatedShips();
					for(Spaceship sS: sRep ){
						if(sS.getOwner().equals(e.getUsername())){
							defeatedShipsCount++;
						}
								
					}
				}
			}
			
			/****** fill roundCount **********/
			
			
			
		
			
		}
	}
	//private void generateDefeatedShipsJ

}
