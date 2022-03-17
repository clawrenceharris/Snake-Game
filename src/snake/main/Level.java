package snake.main;

import java.awt.Graphics2D;
import java.util.*;
import java.util.List;

import snake.object.Player;

public class Level{
	private int number;
	private int starsEarned;
	private int score;
	private Player player;
	protected int growth;
	protected int coins;
	private int apples;
	private String title;
	private boolean isLocked;
	public static List<Level> levelsPlayed = new ArrayList<Level>(15);
	private boolean levelWon;
	public char[][] map;
	private int id;
	
	
	
	public Level(int number) {
		this.number = number;
	}
	
	public Level(int number, String title, char[][] map, Handler handler) {
		Random rand = new Random();
		this.id = rand.nextInt();
		this.map = map;
		this.number = number;
		this.title = title;
		
		
		
		this.id = rand.nextInt(10000000);

		
		levelsPlayed.add(this);

		
		
		
		

		this.player = new Player(0,0, handler);
		player.isDead = false;
	}
	
	public Level(int number, boolean isLocked) {
		this.number = number;
		this.isLocked = isLocked;

	}
	
	public int getStarsEarned() {
		starsEarned = calculateStarsEarned();
		return starsEarned;
		
	}
	public int calculateStarsEarned() {
		
		if(player.coinsCollected == 0){
				return 0;
			}
			if(player.coinsCollected == coins)
				return 3;
			 if (player.coinsCollected < coins && player.coinsCollected > coins/ 2){
				return 2;
			}
			 if(player.coinsCollected < coins && player.coinsCollected <= coins / 2){
				return 1;

			}
			 
		return 0;
	}
	
	


	
	
	public void render(Graphics2D g2d) {
		
	}
	public void checkForWin() {
		if(player.isAtFinish) {
			if(apples == getPlayer().getApplesEaten()) {
				levelWon = true;
				player.isWon = true;
				this.starsEarned = calculateStarsEarned();
				
					if(LevelHandler.contains(this.number)) {
						if(this.starsEarned > LevelHandler.getLevel(this.number).starsEarned) {
							LevelHandler.removeCompleted(LevelHandler.getLevel(this.number));
							LevelHandler.addCompleted(this);
							
						}
						
					}
					else {
						LevelHandler.addCompleted(this);

					}
				
					if(this.number != LevelHandler.allLevels.size())
					
						LevelHandler.unlockLevel(this.getNumber() );
				
				

			} else {
				player.isDead = true;

			}
		
		} 
		
	}
	
	
	
	
	public void tick() {
		
		checkForWin();		
		
	}
	
	public String toString() {
		String str = "";
		
		str += "Level: "+ getNumber() + "\n";
		str += "Id: " + id + "\n";
		str += "Player:\n" + player + "\n";
		return str;
		
	}

	
	//----------Getters and Setters-----------	
	public int getScore() {
		return score;
	}
	
	public void setPlayerStartPosition(int x, int y) {
		this.player.setX(x);
		this.player.setY(y);
		
	}
	
	public void setGrowth(int growth) {
		this.growth = growth;
	}

	public String getTitle() {
		return title;
	}

	public boolean isLocked() {
		return isLocked;
	}
	
	public void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public void setApples(int apples) {
		this.apples = apples;
	}
	
	public int getApples() {
		
		return apples;
	}

	public Player getPlayer() {
		return player;
	}

	public boolean isLevelWon() {
		return levelWon;
	}

	public void setLevelWon(boolean levelWon) {
		this.levelWon = levelWon;
	}

	public int getNumber() {
		return number;
	}

	public void setCoins(int amount) {
		coins = amount;
		
	}
	
	public int getId() {
		return id;
	}

	public int getCoins() {
	
		return coins;
	}
	
}
