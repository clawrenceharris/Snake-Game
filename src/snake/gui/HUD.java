package snake.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import snake.main.Game;
import snake.main.Level;
import snake.main.STATE;
import snake.object.Freeze;
import snake.object.Frenzy;
import snake.object.Ghost;
import snake.object.Magnet;
import snake.object.Player;





public class HUD {
	public static int coins;
	private int length;
	private int score;
	private Player player;
	public int timer;
	private Level level;
	private Font font = new Font("arial", 1, 32);
	private Font font2 = new Font("arial", 0, 20);
	private Game game;
	private Menu tryAgainMenu;
	private Menu nextLevelMenu;
	public  BufferedImage star;

	private Button tryAgainButton; 
	private Button nextLevelButton; 
	
	

	public HUD(Game game) {
		this.player = game.getPlayer();
		this.game = game;
		this.level = game.getLevel();
		MyFont myFont = new MyFont(20, 10, 22 );
		tryAgainButton = new Button(265, 250, 125, 30, "TRY AGAIN", Color.GRAY, myFont);
		nextLevelButton = new Button(260, 260, 135, 30, "NEXT LEVEL", Color.GRAY, myFont);
		
		tryAgainMenu = new Menu(tryAgainButton);
		nextLevelMenu = new Menu(nextLevelButton);

	}
	
	
	
	
	
	
	
	public void tick() {
		
		
		score = player.getApplesEaten() * 10;
		
		
		if(Game.gameState == STATE.Levels) {
			if(level.getPlayer().isDead) {
				game.addListeners(tryAgainMenu, tryAgainMenu);
				
				if(tryAgainMenu.getButtonClicked() == tryAgainButton) {
					game.startLevel(level.getNumber());

				}
			}
			if(level.isLevelWon()) {
				game.addListeners(nextLevelMenu, nextLevelMenu);
				
				if(nextLevelMenu.getButtonClicked() == nextLevelButton) {
					game.startLevel(level.getNumber() + 1);
		
				}
			}
		} else if(Game.gameState == STATE.Game){
			game.addListeners(tryAgainMenu, tryAgainMenu);
			if(tryAgainMenu.getButtonClicked() == tryAgainButton) {
				game.startGame();

			}
			
		}
		
	}
	
	public void render(Graphics2D g2d) {

		if(Game.gameState == STATE.Game) {
			
			if(player.isDead) {
				drawGameOver(g2d);
				tryAgainButton.y = 280;
				tryAgainButton.x = 245;

				tryAgainButton.render(g2d);

			}
			else {
				drawGame(g2d);

			}
		} 
		
		else if(Game.gameState == STATE.Levels) {
			
			if(level.isLevelWon()) {
				drawYouWin(g2d);
				nextLevelButton.render(g2d);
			}
			else if(player.isDead) {
				drawLevelOver(g2d);
				tryAgainButton.render(g2d);

			} else 
				drawLevel(g2d);
		}
	
	}
	
	public void drawGame(Graphics2D g2d) {
		
				
		g2d.setColor(Color.white);
	
		//Shows length and score when player is in-game and dead
		g2d.setFont(font2);
		g2d.drawString("Length: " + player.getLength(), 50, 25);
		g2d.drawString("Score: " + score, 450 , 25);
			
		
		
		
		
		//When in frenzy mode, a big time bar is drawn with random colors 
		

		
		/* When in invisible mode, the top time bar is filled 
		 * with green color and decreases in size as the 
		 * its corresponding timer variable decreases in value
		 */

		if(player.isGhost()) {
		
			g2d.setColor(Color.green);
			g2d.fillRect(300, 15, 2 * Ghost.timer, 15);
		}
		
		/* When in stall mode, the bottom time bar is filled 
		 * with a cyan color and decreases in size as the 
		 * its corresponding timer variable decreases in value
		 */
		if(player.isFreeze()) {
			
			g2d.setColor(Color.cyan);
			g2d.fillRect(200, 15, 2 * Freeze.timer, 15);
		}
		
		if(player.isMagnet()) {
			g2d.setColor(Color.red);
			g2d.fillRect(250, 30,  Magnet.timer, 15);
		}	
		
		//Draws the timer bars that will indicate when a power-up's effect will deactivate
		if(!player.isFrenzy()) {
			g2d.setColor(Color.white);
			g2d.setStroke(new BasicStroke(1f));
					
			g2d.drawRect(250, 30, 100, 15);		
			g2d.drawRect(200, 15, 100, 15);
			g2d.drawRect(300, 15, 100, 15);		
				
		} else {
			Random rand = new Random();

			g2d.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			g2d.fillRect(200, 15, 2 * Frenzy.timer, 30);
			
			g2d.drawRect(200, 15, 200, 30);		

		}
				
	}
	
	public void drawGameOver(Graphics2D g2d) {
		
		g2d.setColor(Color.white);
		g2d.setFont(font);
		
		g2d.drawString("GAME OVER!", 210 , 150);
		
		g2d.setFont(font2);
		g2d.drawString("Final Length: " + game.getPlayer().getLength(), 240 , 210);
		g2d.drawString("Score: " + score, 260 , 250);
		
		
	}
	
	public void drawLevelOver(Graphics2D g2d) {
		int x = 150;
		int y = 100;
		Font font3 = new Font("arial", 0, 20);

		g2d.setColor(Color.DARK_GRAY);
		g2d.setFont(font);
		//g2d.fill3DRect(x, y, width, height, true);
		g2d.setColor(Color.white);
		g2d.drawString("LEVEL FAILED!", 210 , 150);
		
		g2d.setFont(font3);
		g2d.drawString("Apples: " + level.getPlayer().getApplesEaten(), 280 , 200);
		g2d.drawString("Coins: " + level.getPlayer().coinsCollected, 285 , 240);
		g2d.setFont(font3);
		
		g2d.drawString("", x + 130 , y + 176);
		
	}
	
	private void drawYouWin(Graphics2D g2d) {
		
		int width = Game.UNIT_SIZE;
		int height = Game.UNIT_SIZE;
		int x = 260;
		int y = 200;
		
		String title = "LEVEL COMPLETE!";
		Font font3 = new Font("arial", 0, 14);

		//g2d.setColor(Color.DARK_GRAY);
		//g2d.fill3DRect(x2, y2, width2, height2, true);

		g2d.setColor(Color.white);
		g2d.setFont(font);

		g2d.drawString(title ,  175 , 150);
		
		g2d.setFont(font2);
		
		for(int i = 0; i < 3; i++) {
			g2d.setColor(Color.gray);
			drawStar(x, y, width , height, g2d);
			x += 50;
		
		}
		x = 260;
		//draws stars on screen
		for(int i = 0; i < level.getStarsEarned(); i++) {
			g2d.setColor(Color.yellow);
			drawStar(x, y, width , height, g2d);
			x += 50;
		
		}
		
		g2d.setColor(Color.white);
		g2d.setFont(font2);
		g2d.drawString("Stars: " + level.getStarsEarned(), 285 , 180);

		g2d.drawString("Score: " + ((level.getPlayer().coinsCollected * 63) + (player.getApplesEaten() * 15)), 270 , 250);
		g2d.setFont(font3);
		
		

	}
	
	public void drawStar(int x, int y, int width, int height, Graphics2D g2d){
		int[] xPos = {x + (width/ 2), (x + width) + 5, x + (width / 2), x - 5  };
		int[] yPos = {(y) - 5, y + height /2, (y + height) + 5, y + height / 2  };		
		g2d.setStroke(new BasicStroke(4f));

		g2d.drawPolygon(xPos, yPos, 4);
		g2d.setStroke(new BasicStroke(3f));

		g2d.drawRect(x , y , width, height);
	}
	
	
	public void drawLevel(Graphics2D g2d) {
		Font font4 = new Font("arial", 0, 18);

		g2d.setColor(Color.white);
		g2d.setFont(font2);
		g2d.drawString("Apples: " + level.getPlayer().getApplesEaten() +" / " + level.getApples(), 50, 25);
		
		g2d.setFont(font4);
		FontMetrics metrics = g2d.getFontMetrics(g2d.getFont());
		
		String title = level.getTitle();
		String levelNum = "Level " + level.getNumber();
		g2d.drawString(title, (Game.SCREEN_WIDTH  - metrics.stringWidth(title)) / 2, 55);
		g2d.setFont(font2);


		g2d.drawString(levelNum, (Game.SCREEN_WIDTH  - metrics.stringWidth(levelNum)) / 2, 25);

		if(level.getPlayer().isFreeze()) {
			g2d.setColor(Color.white);
			g2d.setStroke(new BasicStroke(1f));
			
			g2d.drawRect(200, 400, 200, 15);	
			
			g2d.setColor(Color.cyan);
			g2d.fillRect(200, 400, 4 * Freeze.timer, 15);
		}
	}
//	public void freezeTut(Graphics2D g2d) {
//		 Font font2 = new Font("arial", 0, 15);
//		 Font font = new Font("arial", 0, 25);
//
//		g2d.setColor(Color.DARK_GRAY);
//		g2d.fill3DRect(190, 40, 300, 150, true);
//		g2d.setFont(font);
//		g2d.setColor(Color.white);
//		g2d.drawString("Freeze Blocks", 260, 70);
//		
//		g2d.setFont(font2);
//
//		g2d.drawString(" - Freeze blocks stop the snake in its tracks momentarily.", 200, 80);
//		
//		g2d.drawString("- After it is deactivated the snake will continue ", 200, 100);
//		g2d.drawString("in the direction that it entered the Freeze block.\n", 200, 110);
//		g2d.drawString("- You can change the direction in which you exit the freeze block by pressing the", 200, 125);
//	}
	
	//--------Getters and Setters----------
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
