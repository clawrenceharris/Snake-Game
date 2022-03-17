package snake.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import snake.object.Apple;
import snake.object.Freeze;
import snake.object.Frenzy;
import snake.object.Ghost;
import snake.object.Magnet;
import snake.object.Player;

public abstract class GameObject {
	protected  boolean isActivated;
	protected Handler handler;
	protected int speed;
	protected Tag tag;
	protected int width;
	protected int height;
	protected int x;
	protected int y;
	protected boolean isCollision;
	protected Color color;
	protected int id;
	protected boolean isRemovable;
	protected int[] spawnTimes = new int[3];


	

	
	
	public GameObject(int x, int y, Tag tag, Handler handler) {
		this.tag = tag;
		this.x = x;
		this.y = y;
		this.width = Game.UNIT_SIZE;
		this.height = Game.UNIT_SIZE;
		this.handler = handler;
		
		isRemovable = true;
		spawnTimes[0] = 100;
		spawnTimes[1] = 200;
		spawnTimes[2] = 500;		
	}
	
	
	
	
	
	public GameObject() {
	}
	
	public static GameObject Instantiate(Tag tag, int x, int y, Handler handler) {
		
		switch(tag) {
		case APPLE: return new Apple(x, y, handler);

		case GHOST: return new Ghost(x, y, handler);
		case FREEZE: return new Freeze(x,y, handler);
		case FRENZY: return new Frenzy(x,y, handler);
		case MAGNET: return new Magnet(x,y, handler);
		default:
			break;

		}
		return null;
	}

	
	public void setCollisionDetected(boolean isCollision) {
		this.isCollision = isCollision;
	}
	public abstract Rectangle getBounds();
	
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g2d);
	
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public Tag getTag() {
		return tag;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	
	public String toString() {
		String str = "";
		
		str += "Object: " + tag + "\n";
		str += "Id: " + getId() + "\n";
		str += "Location: " + x +", "+ y + "\n";
		
		return str;
	}

	public  abstract void collision(Player player);





	public boolean isActivated() {
		return isActivated;
	}





	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}
	
	
	
	
	
}
