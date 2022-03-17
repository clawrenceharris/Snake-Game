package snake.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.GameObject;
import snake.main.Handler;
import snake.main.Tag;

public class Key extends GameObject{
	private Door door;
	private Color color;
	public Key(int x, int y, int id,  Handler handler) {
		super(x, y, Tag.KEY, handler);
		this.setId(id);
		
		if(id == 0 ) {
			color = new Color( 0,100,0);
		}
		else if(id == 1) {
			color = Color.pink.darker();
		}
		else {
			color = new Color(42,35,82);
		}
		
	}
	public Key() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics2D g2d) {
		
		g2d.setStroke(new BasicStroke(4));
		
		g2d.setColor(color);

		g2d.drawRect(x+5, y + 5, width - 10, height -  10);

		g2d.fillRect(x + 8, y+ 15, width - 15, 15);
		



	}
	public Door getDoor() {
		return door;
	}
	public void setDoor(Door door) {
		this.door = door;
		//door.setKey(this);
	}
	@Override
	public void collision(Player player) {
		for(GameObject door : handler.getDoors()) {
			if(door.getId() == this.getId()) {
				door.setActivated(true);
			}
		}
 		
	}
	
	
}
