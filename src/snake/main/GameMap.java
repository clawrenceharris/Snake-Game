package snake.main;

import snake.object.Apple;
import snake.object.Border;
import snake.object.Coin1;
import snake.object.Door;
import snake.object.Enemy1;
import snake.object.Enemy2;
import snake.object.Finish;
import snake.object.Freeze;
import snake.object.Frenzy;
import snake.object.Ghost;
import snake.object.Key;
import snake.object.OneWay;
import snake.object.Portal;

public class GameMap {
	private Level level;
	private Handler handler;
	int portalCount = 0;
	private char[][] map;

	
	public GameMap(){
		
	}
	public void fillBorders(char[][] map) {
	
		 for( int j = 0; j < 22; j++) {
		 
			 for(int i = 0; i < 30; i++) {
					
				if(map[j][i] == '1')
					handler.addObject(new Border(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, handler));	
				
			 }
		 }
	}
	
	public GameMap(char[][] map, Handler handler) {	
		this.handler = handler;
		for( int j = 0; j < 22; j++) {
			 for(int i = 0; i < 30; i++) {
					
				if(map[j][i] == '1')
					handler.addObject(new Border(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, handler));
				
				
			 }
		 }
	}
	
	
	public GameMap(Level level, Handler handler) {
		this.handler = handler;
		this.level = level;
		this.map = level.map;
		 
		//C = coin
		//0 = empty
		//1 = border
		//@ = player
		//3 = apple
		//4 = finish
		//5 = freeze
		//6 = ghost
		//P = portal 1a
		//p = portal 2a
		//W = portal 1b
		//w = portal 2b
		//Q = portal 1c
		//q = portal 2c
		//6 = one way
		//D = door 1
		//d = door 2
		//b = door 3
		//K = key 1
		//k = key 2
		//y = key 3
		//E = enemy 1
		
		  
				
	}
	
	public void fillEnemies() {
		for( int j = 0; j < 22; j++) {
			 
			 for(int i = 0; i < 30; i++) {
				
				 if(map[j][i] == 'E') {
						handler.addObject(new Enemy1(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, handler));
					}
				 
				  if(map[j][i] == 'e') {
						handler.addObject(new Enemy2(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, handler));
					}
					
			 }
		}
	}
	
	public void fillMap() {
		
		Portal portal1 = null;
		Portal portal2 = null;
		Door door1 = null;
		Key key1 = null;
		Door door2 = null;
		Key key2 = null;
		
		for( int j = 0; j < 22; j++) {
			 
			 for(int i = 0; i < 30; i++) {
				
				if(map[j][i] == 'C') {

					Coin1 coin = new Coin1(i * Game.UNIT_SIZE , j * Game.UNIT_SIZE, handler);
					handler.addObject(coin);
					level.setCoins(level.getCoins() + 1);
				}
					
				
				else if(map[j][i] == '6') {
					handler.addObject(new OneWay(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, handler));
				}
				
				else if(map[j][i] == '3') {
					handler.addObject(new Apple(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, handler));
					level.setApples(level.getApples() + 1);

				}
				
		
				else if(map[j][i] == '5') {
					handler.addObject(new Freeze(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, handler));
				}
				
				else if(map[j][i] == 'P') {
					portal1 = new Portal(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, 0, handler);
					portal2 = getSecondPortal('p', 0);		
					

					portal1.setPortal(portal2);
					portal2.setPortal(portal1);
							
					handler.addPortal(portal1);
					handler.addPortal(portal2);
				}
				
				else if(map[j][i] == 'Q') {
					portal1 = new Portal(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, 1, handler);
					portal2 = getSecondPortal('q', 1);		
				
					portal1.setPortal(portal2);
					portal2.setPortal(portal1);
							
					handler.addPortal(portal1);
					handler.addPortal(portal2);
			}
			
				else if(map[j][i] == 'T') {
					portal1 = new Portal(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, 2, handler);
					portal2 = getSecondPortal('t', 2);		
				
					portal1.setPortal(portal2);
					portal2.setPortal(portal1);
							
					handler.addPortal(portal1);
					handler.addPortal(portal2);
			}
			
				
				
				
				
				else if(map[j][i] == '@') {
					level.setPlayerStartPosition(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE);

				}
				
				
				else if(map[j][i] == 'D') {
					door1 = new Door(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, 0, handler);
					handler.addDoor(door1);
				}
				
				else if(map[j][i] == 'd') {
					handler.addDoor(new Door(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, 1, handler));
					
				}
				
				else if(map[j][i] == 'F') {
					handler.addObject(new Frenzy(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, handler));
				}
				
				else if(map[j][i] == 'G') {
					handler.addObject(new Ghost(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, handler));
				}
				
				else if(map[j][i] == 'K') {
					handler.addObject(new Key(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, 0, handler));
					
				}
					
				
				else if(map[j][i] == 'k') {
					
					handler.addObject(new Key(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, 1, handler));
					
				}
				
				else if(map[j][i] == '4') {
					handler.addObject(new Finish(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, handler));
				}
				
				
				
				else if(map[j][i] == 'b') {
					handler.addDoor(new Door(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, 2, handler));
				}
				
				else if(map[j][i] == 'y') {
					handler.addObject(new Key(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, 2, handler));
				}
				

			 }//end inner for		
		}//end outer for
		handler.pairPortals();
	}//end fillMap()	
		
	public Portal getSecondPortal(char portal, int id) {
		for( int j = 0; j < 22; j++) {
			 
			 for(int i = 0; i < 30; i++) {
				 if(map[j][i] == portal) {
					 return new Portal(i * Game.UNIT_SIZE, j * Game.UNIT_SIZE, id, handler);
				 }
			 }
		}
		return null;
	}
	
}
