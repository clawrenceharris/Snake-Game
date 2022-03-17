package snake.main;

import java.util.ArrayList;
import java.util.Random;

import snake.gui.HUD;
import snake.object.Apple;
import snake.object.Freeze;
import snake.object.Frenzy;
import snake.object.Ghost;
import snake.object.Magnet;
import snake.object.Player;



public class Spawner {
	private Handler handler;
	private Player player;
	private int time;
	private HUD hud;
	private Game game;
	private Level level;
	private Random random = new Random();
	
	public Spawner(Handler handler, Player player, HUD hud, Game game) {
		this.handler = handler;
		this.player = player;
		this.hud = hud;
		this.game = game;
	}
	
	
	
	public void tick() {
		time++;
		
		
 
		ArrayList<Position> emptyPositions = handler.getEmpty();
		Random r = new Random();

		
		if(handler.getAmount(Tag.APPLE) == 0 ) {
			
			spawnObject(Tag.APPLE, emptyPositions.get(random.nextInt(emptyPositions.size())));
		}
		
		Tag objects[] = {Tag.GHOST, Tag.FREEZE, Tag.FRENZY, Tag.MAGNET};
		
		
		for (int i = 0; i < objects.length; i++) {
			if(!player.isFrenzy())	
			if(time % 100 == 0)
				spawnObject(objects[i], emptyPositions.get(random.nextInt(emptyPositions.size())));
			
			
		}
		
		if(player.isFrenzy()) {
			Integer[] ints1 = {1, 2, 3};
			int rand = r.nextInt(ints1.length);

			//Spawns an apple when time is equal to 1, 3 or 5; a very rapid spawn frequency
			if(time % 2 == 0) {
				spawnObject(Tag.APPLE, emptyPositions.get(random.nextInt(emptyPositions.size())));

			}


			
		}
		//After frenzy is down all apples disappear
		if(!player.isFrenzy() && handler.getAmount(Tag.APPLE) > 1) {
			 
			handler.clearApples();
		}
		

	}
	
	

	
	

	

	public void spawnObject(Tag tag, Position pos) {
		GameObject object = GameObject.Instantiate(tag, pos.x, pos.y, handler);
		handler.addObject(object);


		
	}
	
	
	
	
}
