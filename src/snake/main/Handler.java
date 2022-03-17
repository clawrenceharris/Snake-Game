package snake.main;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

import snake.object.Coin;
import snake.object.Door;
import snake.object.Enemy2;
import snake.object.Freeze;
import snake.object.Frenzy;
import snake.object.Ghost;
import snake.object.Magnet;
import snake.object.OneWay;
import snake.object.Player;
import snake.object.Portal;


public class Handler {
	public LinkedList <GameObject> objects = new LinkedList<GameObject>();

	public LinkedList <GameObject> doors = new LinkedList<GameObject>();
	public LinkedList<Portal> portals = new LinkedList<Portal>();



	public void tick() {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics2D g2d) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			
			tempObject.render(g2d);
		}
		
	}
	
	public void addObject(GameObject object) {
		this.objects.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.objects.remove(object);

	}
	
	
	
	public void removeAllObjects(Tag tag) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			if(tag == tempObject.getTag()) {
				objects.remove(tempObject);

			}
		}
	}
	
	public int getAmount(Tag tag) {
		int amount = 0; 
		
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			if(tag == tempObject.getTag()) {
				amount++;
			}
		}
		
		return amount;

	}
	
	public GameObject getObject(int x, int y) {
		GameObject object = null;

		for(GameObject temp : objects) {
			if(temp.x == x && temp.y == y) {
				object = temp;
				
			}
		}
		
		return object;
	}
	
	public void clearApples() {
		for(int i = 0; i < objects.size(); i++) {
			if(objects.get(i).getTag() == Tag.APPLE) {
				removeObject(objects.get(i));
			}
		}
	}

	public void addCoins(ArrayList<Coin> coins) {
		for(Coin coin : coins) {
			this.addObject(coin);
		}
	}

	public void removeAllObjects() {
		objects.clear();
		
	}

	
	
	public void removeAllObjects(Tag[] tag) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject tempObject = objects.get(i);
			for(int j = 0; j < tag.length; j++ ) {
				if(tempObject.tag == tag[j])
					objects.remove(tempObject);
				
			}
		}		
	}
	
	public ArrayList<Position> getEmpty() {
		ArrayList<Position> emptyPositions = new ArrayList<>();
		
		for(int y = 0; y < Game.SCREEN_HEIGHT / Game.UNIT_SIZE; y++) {
			for(int x = 0; x < Game.SCREEN_WIDTH / Game.UNIT_SIZE; x++) {
				if(this.getObject(x * Game.UNIT_SIZE, y * Game.UNIT_SIZE ) == null) {
					emptyPositions.add(new Position(x * Game.UNIT_SIZE,y * Game.UNIT_SIZE));
				}
			}

		}
		
		return emptyPositions;
	}
	
	public void addDoor(Door door) {
		addObject(door);
		doors.add(door);
	}
	
	public void addPortal(Portal portal) {
		addObject(portal);
		portals.add(portal);
	}
	
	public LinkedList<GameObject> getDoors() {
		return doors;
	}
	
	public LinkedList<Portal> getPortals() {
		return portals;
	}
	
	
	
	
	public void processCollisions(Player player) {

			
			for(int i = 0; i < objects.size(); i++) {
				GameObject obj = objects.get(i);
				if(player.getX() == obj.x && player.getY() == obj.y) {
					
					
					if(obj.isRemovable)
						removeObject(obj);
					obj.collision(player);
					
				

			}
				if(player.isMagnet()) {

					if(player.getMagnetRadius().intersects(obj.getBounds()) ||player.getMagnetRadius().contains(obj.getBounds()) ) {
						if(obj.getTag() == Tag.APPLE) {
	
								removeObject(obj);
								obj.collision(player);


							}
						}
					}
			
		}	
				
		
	}
	public void processEnemyCollsions(Enemy2 enemy) {
		for(int i = 0; i < objects.size(); i++) {
			GameObject obj = objects.get(i);
			if(obj.getTag() == Tag.ONEWAY) {
				OneWay oneWay = (OneWay)obj;
				
				if(enemy.getX() == oneWay.x && enemy.getY() == oneWay.y) {
					oneWay.isActivated = true;
					oneWay.timesEntered = 1;
				}
			}
			
		}
	}
	public void pairPortals() {
		
		for(Portal p : portals) {
			if(p.getId() == 0) {
					
			}
		}
		
	}

	public GameObject getObject(Tag tag) {
		for( int i = 0; i < objects.size(); i++){
			GameObject object = objects.get(i);
			if(object.tag == tag) {
				return object;
			}

		}
		return null;
	}
	
	
}
