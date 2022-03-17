package snake.object;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import snake.main.Game;
import snake.main.GameObject;
import snake.main.Handler;
import snake.main.PlayerState;
import snake.main.Position;
import snake.main.Tag;

public class Player extends GameObject {
	public int length;
	public String direction;
	public boolean isDead;
	public int applesEaten;
	public int coinsCollected;
	private int x[] = new int[Game.GAME_UNITS];
	private int y[] = new int[Game.GAME_UNITS];
	protected boolean isGhost;
	protected boolean isMagnet;
	protected boolean isFreeze;
	protected boolean isFrenzy;
	protected boolean isNormal;
	public boolean isAtFinish;
	public boolean isWon;
	private Key key;
	Random r = new Random();	 

	public ArrayList<Position> positions = new ArrayList<>(); 
	
	public Player(int x, int y, Handler handler) {
		super(x, y, Tag.PLAYER, handler);
		
		this.handler = handler;
		this.length = 1;
		this.x[0] = x;
		this.y[0] = y;
		this.direction = "STILL";
		isNormal = true;
		isRemovable = false;
	}
	
	

	
	public Player() {
		
	}




	@Override
	public Rectangle getBounds() {
		return new Rectangle(x[0], y[0], Game.UNIT_SIZE, Game.UNIT_SIZE);
	}
	
	public Rectangle getMagnetRadius() {
		Rectangle radius = new Rectangle(x[0] - 40, y[0] -40, 100, 100);
		
		return radius;
	}

	
	public void move() {
		if(!isFreeze) {
			for (int i = length; i > 0; i--) {
				
				x[i] = x[i - 1];
				y[i] = y[i - 1];
			}
			
			switch(direction) {
			case "RIGHT": 	x[0] += Game.UNIT_SIZE;
							x[0] = Game.clamp(x[0], 0, Game.SCREEN_WIDTH - Game.UNIT_SIZE);
							break;
							
			case "LEFT": 	x[0] -= Game.UNIT_SIZE;
							x[0] = Game.clamp(x[0], 0, Game.SCREEN_WIDTH);	
							break;
			
			
			case "DOWN": 	y[0] += Game.UNIT_SIZE;
							y[0] = Game.clamp(y[0], 0, Game.SCREEN_HEIGHT - Game.UNIT_SIZE);
							break;
			
			case "UP":		y[0] -= Game.UNIT_SIZE;
							y[0] = Game.clamp(y[0], 0, Game.SCREEN_HEIGHT);
							break;
			}
		} else {
			for (int i = length; i > 0; i--) {
				x[i] = x[i - 1];
				y[i] = y[i - 1];
			}
			
			x[0] = x[0];
			y[0] = y[0];
		}
		
		positions.add(new Position(x[0], y[0]));

		if(positions.size() > 2) {
			positions.remove(0);
		}
	}
	
	public Position getLastPosition() {
		if(positions.size() > 1)
			return positions.get(positions.size() - 2);
		return new Position(0,0);
	}
	public void activatePowerUps() {
		if(isFrenzy) {
			Frenzy.timer--;
		
			if(Frenzy.timer <= 0) {				
				Frenzy.timer = 100;
				isFrenzy = false;
				isNormal = true;
			}
		}
		
		 if(isMagnet) {
			Magnet.timer--;
		
			if(Magnet.timer <= 0) {				
				Magnet.timer = 100;
				isMagnet = false;
			}
		}
		
		 if(isGhost) {
			Ghost.timer--;
			
			if(Ghost.timer <= 0) {
				isGhost = false;

				Ghost.timer = 50;
			}
		}
	}
	@Override
	public void tick() {
		
		
		if(isWon) {
			for (int i = length; i > 0; i--) {
				x[i] = x[i - 1];
				y[i] = y[i - 1];
			}
		}
		
		if(!isDead && !isWon) {
			activatePowerUps();
			move();
			
			if(!isFrenzy && !isGhost &&  !isFreeze)
				checkCollision();
		}
			
			
	}
	public void setDead() {
		isDead = true;
		isGhost = false;
		isMagnet = false;
		isFrenzy = false;
		
	}
	public void checkCollision(){
		for(int i = length ; i > 0; i--) {
			if(x[0] == x[i + 1] && y[0] == y[i + 1]) {
				setDead();
					
			}

		}

	}

	@Override
	public void render(Graphics2D g2d) {
		

		
		if(isWon) {
			drawWin(g2d);
		}
		
		
		
		
			if(!isFrenzy && !isGhost && !isMagnet || isFreeze) {
				drawSnake(true, true, Color.green, Color.black, g2d);

			}
	 
			if (isGhost){				 
				drawGhost(g2d);
			}
		
		
		
		if(isFrenzy) { 
			drawSnake(false, true, null, new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)), g2d);
		 }
		
		drawSnakeHead(g2d, isMagnet);

		
		
	}
	

	
	
	public void drawSnakeHead(Graphics2D g2d, boolean isMagnet) {
		
		if(!isDead) {
		 
			//draws magnet circles

			if(isMagnet) { 
				
				//draws inner circle
				g2d.setColor(Color.red);
			
				g2d.drawOval(x[0] - 10, y[0] - 10, Game.UNIT_SIZE * 2, Game.UNIT_SIZE * 2);
			
				//draws middle circle
				g2d.setStroke(new BasicStroke(2f));
				g2d.drawOval(x[0] - 20, y[0] - 20, Game.UNIT_SIZE * 3, Game.UNIT_SIZE * 3);
			
				//draws outer circle
				g2d.setStroke(new BasicStroke(1f));
				g2d.drawOval(x[0] - 30, y[0] - 30, Game.UNIT_SIZE * 4, Game.UNIT_SIZE * 4);

			}
			
			g2d.setColor(Color.green);		

		} else
			g2d.setColor(Color.white);

		g2d.fillRect(x[0], y[0], Game.UNIT_SIZE, Game.UNIT_SIZE);
		g2d.setStroke(new BasicStroke(1f));
		g2d.setColor(Color.black);
		g2d.drawRect(x[0], y[0], Game.UNIT_SIZE, Game.UNIT_SIZE);
	}
	public void drawSnake(boolean fill, boolean stroke, Color color, Color strokeColor, Graphics2D g2d) {
		for(int i = 0; i < length; i++) {
			
			//draws body
			if(i != 0) {
		
				if(fill) {
					g2d.setColor(color);

					g2d.fillRect(x[i], y[i], Game.UNIT_SIZE, Game.UNIT_SIZE);

				} else {
					
					g2d.drawRect(x[i], y[i], Game.UNIT_SIZE, Game.UNIT_SIZE);
					
				}
				

				if(stroke) {
					g2d.setColor(strokeColor);
					g2d.setStroke(new BasicStroke(1f));
					g2d.drawRect(x[i], y[i], Game.UNIT_SIZE, Game.UNIT_SIZE);
					

				}
				
				

			}
		}
		
	}
	
	

	public void drawWin(Graphics2D g2d) {
		Random rand = new Random();
	
		for(int i = 0; i < length; i++) {
			if(i == 0) {
				 if(!isDead)
						g2d.setColor(Color.green);
					else
						g2d.setColor(Color.white);
				
				g2d.fillRect(x[i], y[i], Game.UNIT_SIZE, Game.UNIT_SIZE);

				g2d.setStroke(new BasicStroke(4f));
				g2d.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)) );	

				g2d.drawRect(x[i], y[i], Game.UNIT_SIZE, Game.UNIT_SIZE);
			
			} else {
				g2d.setColor(Color.green);

				g2d.fillRect(x[i], y[i], Game.UNIT_SIZE, Game.UNIT_SIZE);
				
				g2d.setColor(Color.black);
				g2d.setStroke(new BasicStroke(1f));
				g2d.drawRect(x[i], y[i], Game.UNIT_SIZE, Game.UNIT_SIZE);
	
				}
			}
		}
	
	
	public void drawNormal(Graphics2D g2d){
		for(int i = 0; i < length; i++) {
			if(i != 0) {
				g2d.setColor(Color.green);

				g2d.fillRect(x[i], y[i], Game.UNIT_SIZE, Game.UNIT_SIZE);
				
				g2d.setColor(Color.green);
				g2d.setStroke(new BasicStroke(1f));
				g2d.drawRect(x[i], y[i], Game.UNIT_SIZE, Game.UNIT_SIZE);
			}
		}
			
	}
	
	public void drawGhost(Graphics2D g2d){
		for(int i = 0; i < length; i++) {
			if(i != 0) {
				g2d.setColor(Color.black);

				
				g2d.setStroke(new BasicStroke(1f));
				g2d.fillRect(x[i], y[i], Game.UNIT_SIZE, Game.UNIT_SIZE);
				g2d.setColor(Color.green);
				g2d.drawRect(x[i], y[i], width, height );
			}
		}
	}
	
	
	
	
	public int getLength() {
		return length;
	}


	public int getApplesEaten() {
		return applesEaten;
	}


	public void setY(int y) {
		this.y[0] = y;
		
	}


	public void setX(int x) {
		this.x[0] = x;
	}


	@Override
	public int getX() {
		return x[0];
	}
	
	@Override
	public int getY() {
		return y[0];
	}
	

	public Key getKey() {
		return key;
	}


	public void setKey(Key key) {
		this.key = key;
	}

	public void setPosition(int x, int y) {
		this.x[0] = x;
		this.y[0] = y;
		positions.add(new Position(x, y));
		
	}
	
	


	@Override
	public void collision(Player player) {
		
		
		
	}
		
	public String toString() {
		String str = "";
		str += "Object: " + tag + "\n";
		str += "Id: " + getId() + "\n";
		str += "Location: " + x[0] + ", " + y[0] + "\n";
		return str;
	}




	public void setDirection(String direction) {
		this.direction = direction;
		
	}
	
	public boolean isFrenzy() {
		return isFrenzy;
	}
	
	public boolean isGhost() {
		return isGhost;
	}
	
	public boolean isMagnet() {
		return isMagnet;
	}
	
	public boolean isNormal() {
		return isNormal;
	}




	public boolean isFreeze() {
		return isFreeze;
	}

	
	
	
}
