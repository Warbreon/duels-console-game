package game;

//MAIN STATS CLASS (WINS; LOSES; WINRATE)
public class Stats{
	private String characterName;
	private int wins;
	private int loses;
	private int totalBattles;
	
	public Stats(String characterName) {
		this.characterName = characterName;
		this.wins = 0;
		this.loses = 0;
		this.totalBattles = 0;
	}
	
	public void addWin() {
		wins++;
		totalBattles++;
	}
	
	public void addLose() {
		loses++;
		totalBattles++;
	}
	
	public int getTotalBattles() {
		totalBattles = wins + loses;
		return totalBattles;
	}
	
	public double getWinRate() {
		if(getTotalBattles() == 0) {
			return 0.0;
		}
		else {
			return (double) wins / getTotalBattles() * 100.0;
		}
	}

	public void setWins(int wins) {
		if(wins < 0) {
			throw new IllegalArgumentException("Number of wins cannot be negative");
		}
		this.wins = wins;
		
	}
	
	public void setLoses(int loses) {
		if(loses < 0) {
			throw new IllegalArgumentException("Numbers of loses cannot be negative");
		}
		this.loses = loses;
		
	}
	
	public void setTotalBattles(int totalBattles) {
		if(totalBattles < 0) {
			throw new IllegalArgumentException("Total number of battles cannot be negative");
		}
		this.totalBattles = totalBattles;
	}
	
	public int getWins() {
		if(wins < 0) {
			throw new IllegalArgumentException("Number of wins cannot be negative");
		}
		return wins;
	}
	
	public int getLoses() {
		if(loses < 0) {
			throw new IllegalArgumentException("Number of loses cannot be negative");
		}
		return loses;
	}
	
	public String getCharacterName() {
		if(characterName == null) {
			throw new IllegalStateException("Character name cannot be null");
		}
		return characterName;
	}

	@Override
	public String toString() {
		return characterName + " Wins: " + wins + " Loses: " + loses + " Winrate: " + String.format("%.2f", getWinRate()) + "%";
	}
	
	
}