package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import logic.Report;

/**
 * Servlet implementation class Gaming
 */
@WebServlet(asyncSupported = true)
public class Gaming extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gaming() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestedSessionId());
		JSONObject roundObject = requestParamsToJSON(request);
		System.out.println(roundObject.toString());
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO check if sessionID is valid
		doGet(request, response);
	}
	private JSONObject requestParamsToJSON(HttpServletRequest request){
		Map<String, String[]> params = request.getParameterMap();
		JSONObject roundObject = new JSONObject();
		JSONObject planetObject;
		String planets[]={
				"atlantis","caprica","coruscant","endor","erde","gemini","tatooine"
			};
		
		
		for(String s:planets){
			planetObject = new JSONObject();
			//System.out.println( params.get(s+"[newFighter]")[0]);
			planetObject.put("newFighter", params.get(s+"[newFighter]")[0]);
			planetObject.put("newBattlestar",params.get(s+"[newBattlestar]")[0]);
			planetObject.put("sum",params.get(s+"[sum]")[0]);
			roundObject.put(s, planetObject);
		}
		roundObject.put("fightersInStock",Integer.parseInt(params.get("fightersInStock")[0].trim()));
		roundObject.put("battlestarsInStock",Integer.parseInt(params.get("battlestarsInStock")[0].trim()));
		roundObject.put("fightersToBuy",Integer.parseInt(params.get("fightersToBuy")[0].trim()));
		roundObject.put("battlestarsToBuy",Integer.parseInt(params.get("battlestarsToBuy")[0].trim()));
		roundObject.put("playersCash",Integer.parseInt(params.get("playersCash")[0].trim()));
		
		
		
		return roundObject;
		
	}
	private void doRound(JSONObject roundObject){
		
	}
	
	private void generateHighscore(Report endreport){
		
	}
	

}
