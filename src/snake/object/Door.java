package snake.object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.main.GameObject;
import snake.main.Handler;
import snake.main.Tag;

public class Door extends GameObject {
	private Key key;
	private Color color;
	private boolean isOpen;
	
	public Door(int x, int y , int id,  Handler handler) {
		super(x, y, Tag.DOOR, handler);
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
		
		isRemovable = false;
		
	}
	
	public Door(int x, int y, Key key,  Handler handler) {
		super(x, y, Tag.DOOR, handler);
	}
	
	public Door() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void tick() {
	
		
		
		if(isActivated()) {


				

			
				

			
			
			
		}
	}
	


	@Override
	public void render(Graphics2D g2d) {
		if(!isActivated() ) {
		g2d.setColor(color);
		g2d.fill3DRect(x, y, width, height, true);
		
		g2d.setColor(new Color(0,35, 0));
		int xPos[] = {x, x + 6, x + 6 , x};
		int yPos[] = {y,  y + 6, y + 15 , y + width  };
		
		
		int xPos1[] = {x, x + 6, x + 6, x + width};
		int yPos1[] = {y + width,  y + 6 , y + 6 , y + width  };

		g2d.setColor(color.darker());
		g2d.fillPolygon(xPos, yPos, 4);
		g2d.fillPolygon(xPos1, yPos1, 4);
		g2d.setStroke(new BasicStroke(2f));
		g2d.drawRect(x + 5, y + 5, 10, 10);

		g2d.setColor(color);
		g2d.fillRect(x + 5, y + 5, 10, 10);
		}
		
		else {
			g2d.setColor(color);
			g2d.draw3DRect(x, y, width, height, true);

		}
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
		//key.setDoor(this);
	}

	@Override
	public void collision(Player player) {
		if(!isActivated())
			player.setDead();
			
	}		
	
	
	
	
}
