package snake.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import snake.main.Game;
import snake.main.Level;
import snake.main.LevelHandler;
import snake.main.Position;

public class LevelScreen extends Menu{
	private int page;
	public LevelScreen() {
		super();
	}
	
	
	
	public LevelScreen(Game game, int page) {
		super(game);
		this.page = page;
		
		int num = 0;
		
		if(page > 15)
			num = 5 * 3;
		
		//menu = game.getMainMenuButton(20, 10);
		//fills the array with 20 buttons
		for(int j = 0; j < 3; j++) {
			for(int i = 0; i < 5; i++) {
				buttons.add(new Button((100 * i) + 70, (j * 90) + 100 , Game.UNIT_SIZE * 3, Game.UNIT_SIZE * 3, "" + ++num, Color.gray, 20, 40));
				
			}		
		}
		
		if(page == 1)
			buttons.add(new Button(560, 200, 20, 30, ">", Color.gray, 5, 22));
		
		else
			buttons.add(new Button(10, 200, 20, 30, "<", Color.gray, 1 , 20));
	
	}
	


	public void mouseMoved(MouseEvent e) {
		for(int i = 0; i < buttons.size(); i++) {
			
			button = buttons.get(i);
			
			if(button.contains(e.getPoint())) 
				button.mouseOver = true;
			
			else 
				button.mouseOver = false;
		
		}
			
	}
	
	public void mousePressed(MouseEvent e) {
		for(int i = 0; i < buttons.size(); i++) {
			
			button = buttons.get(i);
			
			if(button.contains(e.getPoint())) 
				button.mousePressed = true;
			
			else 
				button.mouseOver = false;
		
		}
		
	}
	

	
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i < buttons.size(); i++) {
			
			button = buttons.get(i);
			if(button.contains(e.getPoint())) {
				if(button.label != ">" && button.label != "<")
					go(button);
			}
				
		}
		
	}
	public void startLevel(int num) {
		
		game.removeMouseListener(this);
		game.removeMouseMotionListener(this);
		game.startLevel(num);
	}
	public void go(Button button) {
		int levelNum = Integer.parseInt(button.label);
		
		//if(!LevelHandler.allLevels.get(levelNum).isLocked())
			startLevel(levelNum);
		
		
	}
	
	public void render(Graphics2D g2d) {
			
		int x;
		int y;
		
		for(Button b : buttons) {
			
			x = b.x + 5;
			y = b.y + 70;
				
			for(Level l : LevelHandler.completedLevels) {
				if(b.label != "<" && b.label != ">") {
					if(l.getNumber() == Integer.parseInt(b.label)){
						
					for(int i = 0; i < l.getStarsEarned(); i ++) {
						drawStar(x, y, 10, g2d);		
						x += 20;
					}
						
				}
				}
			}
			for (Level l : LevelHandler.completedLevels) {
				if(buttons.indexOf(b) + 1 == l.getNumber()) {
					if(l.isLocked()) {
						drawLock(g2d, b.x + 10, b.y);
						//System.out.println(l.getNumber() + "  " + l.isLocked());
					}
					
					
				}
			}
			
			
			
			
			
			b.render(g2d);
			
		}
		
		
			
		
		
		
		
	}
	
	public void drawLock(Graphics2D g2d, int x, int y) {
		g2d.setColor(Color.DARK_GRAY);
		g2d.setStroke(new BasicStroke(6.8f));
		g2d.drawRoundRect(x+7, y+7, 20, 20, 20, 20);
		g2d.fillRoundRect(x , y+20, 35, 35, 20, 20);
	}

	public void drawStar(int xPos, int yPos, int size, Graphics2D g2d){
		int[] x = {xPos + size /2, xPos + size, xPos + size /2, xPos};
		int[] y = {yPos, yPos + size /2, yPos + size, yPos + size/2};

		g2d.setColor(Color.yellow);
		g2d.setStroke(new BasicStroke(4f));
		
		g2d.setStroke(new BasicStroke(3f));
		
		g2d.fillPolygon(x , y , 4);
	}



	public ArrayList<Button> getButtons() {
		return buttons;
	}
	
	
}
