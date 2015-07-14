package logic;

import java.io.Serializable;
import java.util.List;

public class BattleReport implements Report,Serializable{
	private Planet battleGround;
	private String winnersUsername;
	private String loosersUsername;
	private String nameOfbattleGround;
	private int countOfAtackingFighters =0;
	private int countOfAtackingBattleships =0;		
	private int countOfDefendingFighters =0;
	private int countOfDefendingBattleships =0;
	private int fighterAfterBattle =0;
	private int battlestarsAfterBattle =0;
	private List<Spaceship> defeatedShips;
	private String listOfDefeats[];
	public BattleReport(Planet battleGround){
		this.battleGround =battleGround;
		this.init();
	}
	private void init(){
		this.nameOfbattleGround=this.battleGround.getName();
		for(Spaceship s:this.battleGround.getShipsInOrbit()){
			if(s instanceof Battlestar){
				this.countOfDefendingBattleships++;
			}else{
				this.countOfDefendingFighters++;
			}
		}
		for(Spaceship s:this.battleGround.getShipsTryToOrbit()){
			if(s instanceof Battlestar){
				this.countOfAtackingBattleships++;
			}else{
				this.countOfAtackingFighters++;
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
	
	public List<Spaceship> getDefeatedShips() {
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
	public void setDefeatedShips(List<Spaceship> defeatedShips) {
		this.defeatedShips = defeatedShips;
		this.writeListOfDefeats();
	}
	private void writeListOfDefeats(){
		this.listOfDefeats = new String[this.defeatedShips.size()];
		int i=0;
		for(Spaceship s:this.defeatedShips){
			this.listOfDefeats[i++]=s.toString();
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
