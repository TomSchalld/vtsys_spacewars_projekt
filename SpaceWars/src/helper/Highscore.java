package helper;


import org.json.JSONObject;

public class Highscore {
	private static JSONObject highscore = new JSONObject();
	private static int gameCount=0;
	/**adds a new score to highscores
	 * @param score
	 */
	public static void addHighscore(JSONObject score){
		highscore.put(""+(++gameCount), score);
	}
	/**
	 * @return JSONObject with al highscores
	 */
	public static JSONObject getScores(){
		return highscore;
	}
}
