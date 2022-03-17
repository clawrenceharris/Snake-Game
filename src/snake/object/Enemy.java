package snake.object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.Game;
import snake.main.GameObject;
import snake.main.Handler;
import snake.main.Tag;

public class Enemy extends GameObject{
	protected String direction;
	protected int timer = 0;
	protected Color color;
	
	public Enemy(int x, int y, Color color, Handler handler) {
		super(x, y, Tag.ENEMY, handler);
		speed = 5;
		isRemovable = false;
		direction = "DOWN";
		this.color = color;
	}
	
	public void render(Graphics2D g2d) {
		timer++;
		g2d.setColor(color);
		
		if(timer % 6 == 0) {
			g2d.fill3DRect(x, y, width, height, true);
		}
		else {
			g2d.fill3DRect(x, y, width, height, false);

		}
		
	}
	
	public void moveLeft() {
		if(canMove(this.x - Game.UNIT_SIZE, this.y))
			this.x -= Game.UNIT_SIZE;
		else
			direction = "UP";
		
	}
	public void moveUp() {
		if(canMove(this.x, this.y - Game.UNIT_SIZE))
			this.y -= Game.UNIT_SIZE;
		else
			direction = "RIGHT";
		
	}
	
	public void moveRight() {
		if(canMove(this.x + Game.UNIT_SIZE, this.y))
			this.x += Game.UNIT_SIZE;

		
		else 
			direction = "DOWN";
		
		
	}
	
	public void moveDown() {
		if(canMove(this.x, this.y + Game.UNIT_SIZE))
			this.y += Game.UNIT_SIZE;
		else
			direction = "LEFT";
			
		
	}
	
	public void move() {
		if(timer % speed == 0) {
			switch(direction) {
				case "DOWN":  	moveDown();
								break;
		
				case "RIGHT": 	moveRight();
								break;
								
				case "LEFT": 	moveLeft();
								break;
				case "UP":		moveUp();
								break;
		}
		
		
		}
		
		
		
		
		
	}
	
	public boolean canMove(int x, int y) {
		if(handler.getObject(x, y) == null)
			return true;
		
		
		return false;
	}
	
	
	@Override
	public void collision(Player player) {
		
		player.isDead = true;
		
	}
	@Override
	public Rectangle getBounds() {
		return null;
	}
	
	@Override
	public void tick() {
		move();
		
	}
}
