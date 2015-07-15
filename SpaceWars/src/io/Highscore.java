package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import clientServer.Client;
import logic.BattleReport;
import logic.EndReport;
import logic.Report;
import logic.RoundReport;
import logic.Spaceship;

public class Highscore {

	public void generateHighscore(EndReport endreport) throws RemoteException {
	
		int defeatedShipsCount=0;
		int roundCount;
		String username;
		EndReport tmpEnd = endreport;
		List<Report> rRep = tmpEnd.roundReports;
		Map<String, BattleReport> bRep;
		List<Spaceship> sRep;

		/****** fill roundCount **********/
		roundCount=tmpEnd.roundReports.size();
		generateJson(roundCount, "roundCount.json", username);
		
		for (Client e : tmpEnd.winner) {
			username=e.getUsername();
			date = new Date();

			/****** fill defeatedShipsCount **********/
			for (Report rp : rRep) {
				bRep = ((RoundReport) rp).getReports();
				for (BattleReport br : bRep.values()) {
					sRep = br.getDefeatedShips();
					for (Spaceship sS : sRep) {
						if (sS.getOwner().equals(e.getUsername())) {
							defeatedShipsCount++;
						}

					}
				}
			}

			generateJson(defeatedShipsCount, "defeatedShips.json", username);

		}

	}
	private void generateJson(int value, String filename, String username){
		Date date=new Date();
		JSONObject jo = new JSONObject();
		JSONObject val;

		val = new JSONObject();
		val.put("winner", username);
		val.put("Value", value);
		val.put("Datum", date);

		jo.put("Sieger", val);


		try (PrintWriter out =new PrintWriter(new BufferedWriter(new FileWriter(filename,true)))){
			out.println(jo.toString());
		

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print(jo);

	}

		
		
	
}
