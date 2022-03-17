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
import snake.main.Position;
import snake.main.Tag;

public class Portal extends GameObject{
	private int time;
	private int destY;
	private int destX;
	private int id;
	public static ArrayList<Portal> portals = new ArrayList<>();
	private Portal[][] allPortals;
	private Color color;
	private Portal portal;
	private static int timesCollided;
	public boolean collisionOff;
	
	public Portal(int x, int y, int id,  Handler handler) {
		super(x,y,Tag.PORTAL, handler);
		Random rand = new Random();
		this.setId(id);
		if(id == 0) {
			color = Color.cyan.brighter();
		}
		else if (id == 1) {
			color = new Color(255, 16, 240);
		}
		else if(id == 2) {
			color = Color.GREEN.darker();
		}
		timesCollided = 0;

		isRemovable = false;
		
	}
	
	
	
	public Portal() {
		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void tick() {
		time++;
		if(isCollision) {
			
		}
	}

	@Override
	public void render(Graphics2D g2d) {
		
		drawPortal(x, y, id,g2d);
		//drawPortal(destX, destY, g2d);
//		System.out.println(destX +", " + destY);
		
		
	}
	public void drawPortal(int x, int y, int id, Graphics2D g2d) {
		boolean raised = true;
		if(time % 10 == 0) {
			
			g2d.setColor(color);

			
			g2d.draw3DRect(x, y, width, height, raised);
			g2d.draw3DRect(x -5, y -5, width+10, height+10, raised);
			
			g2d.setColor(Color.white);
			g2d.draw3DRect(x +5, y +5, width/2, height/2, true);
			g2d.draw3DRect(x -5, y -5, width+10, height+10, raised);


		}
		
		else {
			g2d.setStroke(new BasicStroke(1.6f));
			g2d.setColor(Color.white);
			g2d.draw3DRect(x -5, y -5, width+10, height+10, raised);

			g2d.draw3DRect(x, y, width, height, raised);
			g2d.setColor(color);

			g2d.draw3DRect(x +5, y +5, width/2, height/2, raised);
			g2d.draw3DRect(x -5, y - 5, width+10, height+10, raised);


		}
	}

	public int getDestX() {
		return destX;
	}

	public void setDestX(int destX) {
		this.destX = destX;
	}

	public int getDestY() {
		return destY;
	}

	public void setDestY(int destY) {
		this.destY = destY;
	}

	

	
	public void setPortal2(int x, int y) {
		this.destX = x;
		this.destY = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		if(id == 0) {
			color = Color.cyan.brighter();
		}
		else if (id == 1) {
			color = new Color(255, 16, 240);
		}
	}


	public void setDestination(int x, int y) {
		this.destX = x;
		this.destY = y;
	}
	
	

	
	
	
	public String toString() {
		String str = "";
		
		str += "Id: " +id + "\n";
		str += "Location: " + x + ", " + y + "\n";
		str += "Destination: " + destX + ", " + destY + "\n";
		return str;
	}



	



	@Override
	public void collision(Player player) {
		Position lastPos = player.getLastPosition();
		
		if(handler.getObject(lastPos.x , lastPos.y ) == null) {	

			player.setPosition(getPortal().x, getPortal().y );
			
		
		}


		

	}



	public void setPortal(Portal portal) {
		this.portal = portal;
		
	}



	public Portal getPortal() {
		return portal;
	}
	

}
