package logic;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

public class BattleReport implements Report,Serializable{
	private PlanetIf battleGround;
	private String winnersUsername;
	private String loosersUsername;
	private String nameOfbattleGround;
	private int countOfAtackingFighters =0;
	private int countOfAtackingBattleships =0;		
	private int countOfDefendingFighters =0;
	private int countOfDefendingBattleships =0;
	private int fighterAfterBattle =0;
	private int battlestarsAfterBattle =0;
	private List<SpaceshipIf> defeatedShips;
	private String listOfDefeats[];
	public BattleReport(PlanetIf battleGround) throws RemoteException{
		this.battleGround =battleGround;
		this.init();
	}
	/**Initializes the report
	 * @throws RemoteException
	 */
	private void init() throws RemoteException{
		this.nameOfbattleGround=this.battleGround.getName();
		for(SpaceshipIf s:this.battleGround.getShipsInOrbit()){
			if(!s.isFighter()){
				this.countOfDefendingBattleships++;
			}else{
				this.countOfDefendingFighters++;
			}
		}
		for(SpaceshipIf s:this.battleGround.getShipsTryToOrbit()){
			if(!s.isFighter()){
				this.countOfAtackingBattleships++;
			}else{
				this.countOfAtackingFighters++;
			}
		}
		if(this.battleGround.isMultiPlanet()){
			MultiPlanet mP = (MultiPlanet)this.battleGround;
			for(SpaceshipIf s:mP.getSecondOwnerOrbit()){
				if(!s.isFighter()){
					this.countOfDefendingBattleships++;
				}else{
					this.countOfDefendingFighters++;
				}
			}
		}
	}
	
	public int getCountOfAtackingFighters() {
		return countOfAtackingFighters;
	}
	public int getCountOfAtackingBattleships() {
		return countOfAtackingBattleships;
	}
	public int getCountOfDefendingFighters() {
		return countOfDefendingFighters;
	}
	public int getCountOfDefendingBattleships() {
		return countOfDefendingBattleships;
	}
	public String getWinnersUsername() {
		return winnersUsername;
	}
	
	public List<SpaceshipIf> getDefeatedShips() {
		return defeatedShips;
	}
	public void setWinnersUsername(String winnersUsername) {
		this.winnersUsername = winnersUsername;
	}
	public String getLoosersUsername() {
		return loosersUsername;
	}
	public void setLoosersUsername(String loosersUsername) {
		this.loosersUsername = loosersUsername;
	}
	public String getNameOfbattleGround() {
		return nameOfbattleGround;
	}
	public void setNameOfbattleGround(String nameOfbattleGround) {
		this.nameOfbattleGround = nameOfbattleGround;
	}
	/**Sets a list of defeated ships an calls writeListOfDefeats()
	 * @param defeatedShips
	 */
	public void setDefeatedShips(List<SpaceshipIf> defeatedShips) {
		this.defeatedShips = defeatedShips;
		try {
			this.writeListOfDefeats();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**Writes a String Array of the defeated ships
	 * @throws RemoteException
	 */
	private void writeListOfDefeats() throws RemoteException{
		this.listOfDefeats = new String[this.defeatedShips.size()];
		int i=0;
		for(SpaceshipIf s:this.defeatedShips){
			this.listOfDefeats[i++]=s.shipInfo();
		}
		this.defeatedShips.clear();
	}
	public String[] getListOfDefeats() {
		return listOfDefeats;
	}
	
	public int getFighterAfterBattle() {
		return fighterAfterBattle;
	}
	public void addFighterAfterBattle() {
		this.fighterAfterBattle++;
	}
	public int getBattlestarsAfterBattle() {
		return battlestarsAfterBattle;
	}
	public void addBattlestarsAfterBattle() {
		this.battlestarsAfterBattle++;
	}
	@Override
	public void addReport(Report report) {
		
	}
	
}
