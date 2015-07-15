package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.*;

import clientServer.GameServer;
import clientServer.Server;
import logic.Human;

/**
 * Servlet implementation class hello
 */
@WebServlet(asyncSupported = true)
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		HttpSession session = request.getSession();
		String uID = session.getId();
		String uname = request.getParameter("username");
		PrintWriter out = response.getWriter();
		if (request.getParameter("createGame").equals("true")) {
			createGame(request, uID, out);
		}
		if (request.getParameter("logout").equals("true")) {
			logout(session, uname);
		}
		if (request.getParameter("joinGame").equals("true")) {
			out.write("?");
		}
		if (request.getParameter("getGames").equals("true")) {
			System.out.println("Get Games");
			JSONObject gamesList = null;
			try {
				Server gameServer = (Server) Naming.lookup("rmi://" + "192.168.178.23" + ":1099/GameServer");
				gamesList = new JSONObject(gameServer.gamesInLobby());
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/json");
			out.write(gamesList.toString());
			System.out.println(gamesList);
			out.close();
		} else {
			out.write("username=" + uname);
			out.close();
		}
		System.out.println(session.getId());

	}

	/**
	 * @param session
	 * @param uname
	 */
	private void logout(HttpSession session, String uname) {
		UserOnline.logout(session.getId());
		System.out.println(uname + " successfully logged out");
	}

	/**
	 * @param request
	 * @param uID
	 * @param out
	 * @throws NumberFormatException
	 * @throws RemoteException
	 */
	private void createGame(HttpServletRequest request, String uID, PrintWriter out)
			throws NumberFormatException, RemoteException {
		String gameName = request.getParameter("gameName");
		int variation = Integer.parseInt(request.getParameter("gameMode").trim()); // 0=pvp
																					// 1=pvpc
																					// 2=ppvpc
		int universeSize = Integer.parseInt(request.getParameter("universeSize").trim()); // 0=3planets
		// 1=5planets
		// 2=7planets
		if (variation == 0) {

			if (universeSize == 1) {
				out.write("gameThree.html?gameName=" + gameName + "&universeSize=" + universeSize + "&");

			} else if (universeSize == 2) {
				out.write("gameFive.html?gameName=" + gameName + "&universeSize=" + universeSize + "&");
			} else {
				out.write("gameSeven.html?gameName=" + gameName + "&universeSize=" + universeSize + "&");
			}
			UserOnline.getUserById(uID).openGame(gameName, variation, universeSize);
			System.out.println("createGame: " + gameName + " mode: " + variation + " universeSize: " + universeSize);
		} else if (variation == 1) {
			out.write("?");
			UserOnline.getUserById(uID).openGame(gameName, variation, universeSize);
			System.out.println("createGame: " + gameName + " mode: " + variation + " universeSize: " + universeSize);
		} else if (variation == 2) {
			out.write("?variation=2");
		} else {
			out.write("?gameName=" + gameName + "&universeSize=" + universeSize + "&");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("username");
		try {
			if (!UserOnline.isUserExisting(sessionId)) {
				UserOnline.addUser(sessionId, new Human(uname, "192.168.178.23"));
			}
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(uname + " with iD: " + sessionId + " has logged in!");
		// TODO log in user here!
		out.write("?username=" + uname);
		response.getWriter().close();

	}
}
