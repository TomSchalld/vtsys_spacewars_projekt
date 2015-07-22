package web;


import org.json.JSONObject;

public class Highscore {
	private static JSONObject highscore = new JSONObject();
	private static int gameCount=0;
	public static void addHighscore(JSONObject score){
		highscore.put(""+(++gameCount), score);
	}
	public static JSONObject getScores(){
		return highscore;
	}
}
