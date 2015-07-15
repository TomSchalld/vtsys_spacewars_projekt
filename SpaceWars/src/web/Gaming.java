package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.json.JSONException;
import org.json.JSONObject;

import clientServer.Client;
import logic.Battlestar;
import logic.Fighter;
import logic.Game;
import logic.PlanetIf;
import logic.Report;
import logic.Spaceship;
import logic.Universe;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		JSONObject roundObject = requestParamsToJSON(request);
		System.out.println("input::::::");
		System.out.println(roundObject.toString());
		System.out.println("input::::::\n\n\n\n\n");
		try {
			doRound(roundObject, request.getRequestedSessionId());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("output::::::");
		System.out.println(roundObject.toString());
		System.out.println("output::::::\n\n\n\n\n");

		response.getWriter().write(roundObject.toString());
		response.getWriter().close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO check if sessionID is valid
		doGet(request, response);
	}

	private JSONObject requestParamsToJSON(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		JSONObject roundObject = new JSONObject();
		JSONObject planetObject;
		String planets[] = { "atlantis", "caprica", "coruscant", "endor", "erde", "gemini", "tatooine" };

		for (String s : planets) {
			planetObject = new JSONObject();
			// System.out.println( params.get(s+"[newFighter]")[0]);
			planetObject.put("newFighter", params.get(s + "[newFighter]")[0]);
			planetObject.put("newBattlestar", params.get(s + "[newBattlestar]")[0]);
			planetObject.put("sum", params.get(s + "[sum]")[0]);
			roundObject.put(s, planetObject);
		}
		roundObject.put("fightersInStock", Integer.parseInt(params.get("fightersInStock")[0].trim()));
		roundObject.put("battlestarsInStock", Integer.parseInt(params.get("battlestarsInStock")[0].trim()));
		roundObject.put("fightersToBuy", Integer.parseInt(params.get("fightersToBuy")[0].trim()));
		roundObject.put("battlestarsToBuy", Integer.parseInt(params.get("battlestarsToBuy")[0].trim()));
		roundObject.put("playersCash", Integer.parseInt(params.get("playersCash")[0].trim()));

		return roundObject;

	}

	private void doRound(JSONObject roundObject, String sID)
			throws RemoteException, JSONException, InterruptedException {
		List<Spaceship> tmpShips = new LinkedList<Spaceship>();
		Client user = UserOnline.getUserById(sID);
		Game actual = user.getGamePlaying();
		int actualRound = actual.getRound();
		Universe universe = user.getGamePlaying().getUniverse();
		int battlestarsInStockAfterRound = 0;
		int fighterInStockAfterRound = 0;
		String planets[] = { "atlantis", "caprica", "coruscant", "endor", "erde", "gemini", "tatooine" };
		JSONObject planet;
		user.setCash(roundObject.getInt("playersCash"));
		System.out.println("user: " + user.getUsername() + " kauft fighter: " + roundObject.getInt("fightersToBuy"));
		for (int i = 0; i < roundObject.getInt("fightersToBuy"); i++) {
			user.buyFighter();
		}
		System.out.println(
				"user: " + user.getUsername() + " kauft battlestar: " + roundObject.getInt("battlestarsToBuy"));

		for (int i = 0; i < roundObject.getInt("battlestarsToBuy"); i++) {
			user.buyBattlestar();
		}
		System.out.println("-------------------------------------------------------------------------doRound");
		for (String planetName : planets) {
			planet = roundObject.getJSONObject(planetName);
			System.out.println(planetName);
			System.out.println("anzahl fighter soll " + planet.getInt("newFighter"));
			for (int i = 0; i < planet.getInt("newFighter"); i++) {
				for (Spaceship ship : user.getStock()) {
					if (ship instanceof Fighter) {
						if (!tmpShips.contains(ship)) {
							tmpShips.add(ship);
							user.sendShip(ship, universe.getPlanetByName(planetName));
							break;
						}

					}
				}
			}
			System.out.println("anzahl battlestar soll " + planet.getInt("newBattlestar"));

			for (int i = 0; i < planet.getInt("newBattlestar"); i++) {
				for (Spaceship ship : user.getStock()) {
					if (ship instanceof Battlestar) {
						if (!tmpShips.contains(ship)) {
							tmpShips.add(ship);
							user.sendShip(ship, universe.getPlanetByName(planetName));
							break;
						}
					}
				}
			}
		}
		System.out.println("----------------------------------------------------------------------------doRoundEnd");
		user.getStock().removeAll(tmpShips);
		// ships sendet
		if (!user.getStock().isEmpty()) {
			for (Spaceship ships : user.getStock()) {
				if (ships instanceof Fighter) {
					fighterInStockAfterRound++;
				} else {
					battlestarsInStockAfterRound++;
				}
			}
		}
		System.out.println("fighters in stock after round: -->"+fighterInStockAfterRound);
		System.out.println("battlestars in stock after round: -->"+battlestarsInStockAfterRound);
		roundObject.put("fightersInStock", fighterInStockAfterRound);
		roundObject.put("battlestarsInStock", battlestarsInStockAfterRound);
		roundObject.put("fightersToBuy", 0);
		roundObject.put("battlestarsToBuy", 0);
		user.setPlayerReady(true);
		int count=0;
		while (actualRound == actual.getRound()) {
			System.out.println("Schleifendurchläufe: "+count+++"username: "+user.getUsername());
			Thread.sleep(1000);
		}
		roundObject.put("playersCash", user.getCash());
		roundObject.put("roundReport", user.getRoundReport());
		System.out.println(user.getUsername() + " has got: " + user.getCash() + " Credits");
		System.out.println("------------------------------------put JSON-----------------------");
		for (String planetName : planets) {
			PlanetIf pl = universe.getPlanetByName(planetName);
			if (pl != null) {
				if (pl.getPlanetOwner() != null) {
					if (pl.getPlanetOwner().equals(user)) {
						planet = roundObject.getJSONObject(planetName);
						planet.put("newFighter", pl.getFighterInOrbit());
						planet.put("newBattlestar", pl.getBattlestarsInOrbit());
						planet.put("sum", (pl.getFighterInOrbit() + pl.getBattlestarsInOrbit()));
						System.out.println(
								pl.getName() + " zu JSON nach Runde user:" + user.getUsername() + " anzahlFighter: "
										+ pl.getFighterInOrbit() + " anzahl batlestar: " + pl.getBattlestarsInOrbit());
					}else {
						planet = roundObject.getJSONObject(planetName);
						planet.put("newFighter", 0);
						planet.put("newBattlestar", 0);
						planet.put("sum", 0);
					}
				}

			}

		}
		System.out.println("------------------------------------put JSON Ende-----------------------");

	}

	private void generateHighscore(Report endreport) {

	}

}
