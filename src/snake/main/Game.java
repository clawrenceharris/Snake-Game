package snake.main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.Timer;

import snake.gui.Button;
import snake.gui.HUD;
import snake.gui.LevelScreen;
import snake.gui.Menu;
import snake.input.KeyInput;
import snake.object.Apple;
import snake.object.Border;
import snake.object.Player;



public class Game extends JPanel implements ActionListener {
	public static final int SCREEN_WIDTH = 600;
	public static final int SCREEN_HEIGHT = (SCREEN_WIDTH / 12  * 9) - 9;
	public static final int UNIT_SIZE = 20;
	private static final int DELAY = 90;
	public static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE ;
	public final int grid[][] = new int[SCREEN_HEIGHT][SCREEN_WIDTH];
	public static ArrayList<Position> validPositions = new ArrayList<>();
	private HUD hud;
	private final Handler handler = new Handler();
	private Timer timer;
	public  Spawner spawner;
	private Player player;
	public static STATE gameState = STATE.Menu;
	private Level currentLevel;
	private LevelScreen levelScreen;
	private KeyInput keyInput;
	private Menu mainMenu;
	private Handler menuHandler;
	private Button MainMenuButton;
	private Button replayButton;
	private Thread thread;

	public Game() {
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setFocusable(true);
		
		thread = new Thread();
		thread.start();
		
		menuHandler = new Handler();
		
		MainMenuButton = getMainMenuButton(20, 10);
		initMainMenu();
		replayButton = new Button(265, 250, 125, 30, "REPLAY", Color.GRAY);
		
		new LevelHandler(); 
		
		timer = new Timer(DELAY, this);
		timer.start();
		for(int y = 0; y < SCREEN_HEIGHT / UNIT_SIZE; y++ ) {
			for(int x = 0; x < SCREEN_WIDTH / UNIT_SIZE; x++ ) {
				menuHandler.addObject(new Border(x * UNIT_SIZE, y * UNIT_SIZE, handler));
			}

		}
		
		keyInput = new KeyInput(this);
		this.addKeyListener(keyInput);
	}
	
	public void initMainMenu(){
		String[] labels = {"ENDLESS", "LEVELS", "SETTINGS"};
		ArrayList<Button> buttons = new ArrayList<>();
		int[] offsetX = {55 ,60 ,50};
		int offsetY = 25;
		int x = 200;
		int y = 62;
		int width = 200;
		int height = 40;

		
		for(int i = 0; i < 3; i ++) {
			Button button = new Button(x,  (y * i) + Game.SCREEN_HEIGHT / 4, width, height, labels[i], Color.gray, offsetX[i], offsetY);
			buttons.add(button);


		}
		mainMenu = new Menu(buttons);
		this.addListeners(mainMenu, mainMenu);



	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		render(g2d);
		g2d.dispose();
		
	}
	
	
	public static int clamp(int value, int min, int max) {
		if(value >= max) {
			return value = max;
			
		}
		else if( value <= min) {
			return value = min;
		}
		else {
			return value;
		}
	}
	
	public Level getLevel() {
		return currentLevel;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void startLevel(int levelNum){
		Game.gameState = STATE.Levels;
		
		handler.removeAllObjects();	

		//Assigns the current level to the user's input
		currentLevel = getLevel(levelNum);		
		
		player = currentLevel.getPlayer();
		hud = new HUD(this);
				
		//Creates game map according to the given level
		GameMap gameMap = new GameMap(currentLevel, handler);
		gameMap.fillBorders(currentLevel.map);
		gameMap.fillMap();
		gameMap.fillEnemies();

		
		//Adds player to game - should be called after the GameMap so that the player is drawn over all other game objects
		handler.addObject(currentLevel.getPlayer());



	}
	
	
	
	
	
	public void startGame() {
		char[][] map = {
				
				{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
				{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
				{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','1'},
				{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},
				{'1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1'},

				

				
				
		};
		gameState = STATE.Game;
		handler.removeAllObjects();
		player = new Player(((Game.SCREEN_WIDTH/ UNIT_SIZE) / 2) * Game.UNIT_SIZE , ((Game.SCREEN_HEIGHT/ UNIT_SIZE) / 2) * Game.UNIT_SIZE, handler);
		Apple apple = new Apple(100, 220, handler);
		hud = new HUD(this);
		
		spawner = new Spawner(handler, player, hud, this);
		
		GameMap gameMap = new GameMap(map, handler);
		
		gameMap.fillBorders(map);
		
		handler.addObject(player);
		handler.addObject(apple);

		
		

	}
	
	
	
	public void drawGrid(Graphics2D g2d) {
		//Draws grid for debugging
		for(int y = 0; y < SCREEN_HEIGHT / UNIT_SIZE; y++) {
			for(int x = 0; x < SCREEN_WIDTH / UNIT_SIZE; x++) {
				g2d.setStroke(new BasicStroke(0.01f));
				g2d.setColor(Color.white);
				g2d.drawRect(x * UNIT_SIZE, y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
			}
		}
	}
	public Button getMainMenuButton(int x, int y) {
		Button mainMenuButton = new Button(x, y, 40, 40," ☰ ", Color.gray, 5, 25);
		Menu menu = new Menu(mainMenuButton);
		addListeners(menu, menu);

		return mainMenuButton;
	}
	public void render(Graphics2D g2d) {
		if(gameState == STATE.Levels || gameState == STATE.Game ) {
			handler.render(g2d);
			hud.render(g2d);
			//MainMenuButton.render(g2d);

			
		}
	
		if(gameState == STATE.Menu) {
			menuHandler.render(g2d);
			for(Button b : mainMenu.getButtons()) {
				b.render(g2d);
			}
		}
		
		if(gameState == STATE.LevelScreen) {
			menuHandler.render(g2d);
			levelScreen.render(g2d);
			//MainMenuButton.render(g2d);

		}
		
		if(gameState == STATE.GameOver) {
			//MainMenuButton.render(g2d);
		}
		
	

	}
	
	
	
	
	public Level getLevel(int num) {
		char[][] map = new char[22][30];
		Level level = null;
		String title = "";
		String pathName = "/Users/caleb/Desktop/Snake Game Level Builder/Levels/Level";
		pathName += num + ".txt";	
		File file = new File(pathName);
		Scanner scan = null;
		
			
			try {

				scan = new Scanner(file);
				

				title += scan.nextLine();
				for(int y = 0; y < 22; y++) {
			        map[y] = scan.nextLine().toCharArray(); 

				}	
					
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
				
			
		
		
		level = new Level(num, title, map, handler);

		return level;
	}
			
	
	
	public void addListeners(MouseListener ml, MouseMotionListener mml) {
		addMouseListener(ml);
		addMouseMotionListener(mml);
	
	}
	
	public void removeListeners(MouseListener ml, MouseMotionListener mml) {
		removeMouseListener(ml);
		removeMouseMotionListener(mml);

	}
	public static void main(String args[]) {
		
		new GameFrame();
	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(MainMenuButton.mouseClicked) {
			this.removeListeners(levelScreen, levelScreen);
			initMainMenu();
			
			Game.gameState = STATE.Menu;


		}
		if(gameState == STATE.LevelScreen) {
			if(levelScreen.getButtonClicked() != null) {
				if(levelScreen.getButtonClicked().label == ">") {
					this.initLevelScreen(16);
				}
				else if (levelScreen.getButtonClicked().label == "<") {
					this.initLevelScreen(1);

				}
				else {
					int levelNum = Integer.parseInt(levelScreen.getButtonClicked().label);
					
					this.startLevel(levelNum);
				}
				

			}
		}

		
		if(gameState == STATE.Menu) {
			if(mainMenu.getButtonClicked() != null) {
				if(mainMenu.getButtonClicked().label == "ENDLESS") {
					this.startGame();
					
				}
				else if(mainMenu.getButtonClicked().label == "LEVELS") {
					gameState = STATE.LevelScreen;
					this.initLevelScreen(1);
					
				}
			
				this.removeListeners(mainMenu, mainMenu);

			}
			
		}
		
		if(gameState == STATE.Game ) {
			if(!player.isDead)
				spawner.tick();
			hud.tick();
			handler.tick();
			handler.processCollisions(player);
			if (player.isFrenzy()) {
				timer.setDelay(55);

			}
			else {
				timer.setDelay(DELAY);

			}
		}
		
		if(gameState == STATE.Levels){
			handler.tick();
			hud.tick();
			if (player.isFreeze() || player.isFrenzy()) {
				timer.setDelay(55);

			}
			else {
				timer.setDelay(DELAY);
				
			}
		
		
			if(currentLevel != null && !currentLevel.getPlayer().isDead && !currentLevel.getPlayer().isWon) {
				handler.processCollisions(currentLevel.getPlayer());
				currentLevel.tick();
			}
		}
		
		
		
		repaint();

	}
	
	public Handler getHandler() {
		return handler;
	}

	public void initLevelScreen(int page) {
		levelScreen = new LevelScreen(this, page);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.addListeners(levelScreen, levelScreen);
			
	}
	
}
