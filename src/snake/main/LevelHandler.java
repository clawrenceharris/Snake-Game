package snake.main;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class LevelHandler {
	public static ArrayList<Level> allLevels = new ArrayList<>();
	public static ArrayList<Level> completedLevels = new ArrayList<>();
	public static HashMap<Level, Integer> levelStars = new HashMap<Level, Integer>();

	public LevelHandler() {
		//fills the array with all levels only passing in the level number and whether or not it is locked 
		for(int i = 0; i < 24; i++) {
			
			if(i != 0) {
				allLevels.add(new Level(i + 1, true));

			} else {
				allLevels.add(new Level(i + 1, false)); //Ensures level 1 is unlocked


			}
		}
		
	}
	
	public static void addLevelStars(Level level, int numStars) {
		levelStars.put(level, numStars);
		
	}
	
	/*
	 * Unlocks a level given the level number to be unlocked
	 * @param number - the level number to be unlocked
	 */
	public static void unlockLevel(int number) {
		if(number < allLevels.size())
			allLevels.get((number)).setIsLocked(false);
	}
	
	
	
	
	public static void addCompleted(Level level) {	
		completedLevels.add(level);

	}
	
	
	/*
	 * Determines the attempt receiving the most stars and returns the number of stars applicable
	 * @param level - the level to be evaluated
	 */
	public static int getMostStars(Level level) {
		int mostStars = 0;
		ArrayList<Integer> numberOfStars = new ArrayList<>();
		for(Level l : completedLevels) {
			if(l.getNumber() == level.getNumber() && l.getId() != level.getId()) {
				if(levelStars.containsKey(level)) {
					numberOfStars.add(levelStars.get(level));
				}
			}
		}
		for(Integer i : numberOfStars) {
			
			if(i > mostStars) {
				mostStars = i;
			}
		}
		
		
		return mostStars;
		
	}


	public static void removeCompleted(Level level) {
		completedLevels.remove(level);
		
	}
	
	public static Level getLevel(int number) {
		for(Level level : completedLevels) {
			if(level.getNumber() == number) {
				return level;
			}
		}
		return null;
	}
	
	public static boolean contains(int number) {
		for(Level level : completedLevels) {
			if(level.getNumber() == number){
				return true;
			}
		}
		return false;

	}
}
