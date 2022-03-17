package snake.input;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import snake.main.Game;
import snake.main.PlayerState;
import snake.main.STATE;
import snake.object.Player;

public class KeyInput implements KeyListener{
		private Game game;
		
		
		
		public KeyInput(Game game) {
			this.game = game;
		}
		public KeyInput() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		public String getOppositeDirection(String direction) {
			switch(direction) {
			case "UP": return "DOWN";
			case "DOWN": return "UP";
			case "LEFT": return "RIGHT";
			case "RIGHT": return "LEFT";
			
			}
			return "STILL";

		}
		
		public String getDirection(int key) {
			switch(key) {
			case KeyEvent.VK_UP: return "UP";
			case KeyEvent.VK_DOWN: return "DOWN";
			case KeyEvent.VK_LEFT: return "LEFT";
			case KeyEvent.VK_RIGHT: return "RIGHT";
			
 
			}
			
			return "STILL";
		}
		
		
		
		public void move(String direction) {
			String oppositeDirection = getOppositeDirection(direction);
			
			if(game.getPlayer().direction != oppositeDirection || game.getPlayer().getLength() == 1 || game.getPlayer().isFreeze()) {
				game.getPlayer().setDirection(direction);
			}
		}
		

		

		@Override
		public void keyPressed(KeyEvent e) {
			
			int key = e.getKeyCode();
			
			if(Game.gameState == STATE.Levels) {
				move(getDirection(key));
				
		
				if(game.getPlayer().isWon || game.getPlayer().isDead) {
					if(key == KeyEvent.VK_SPACE) {
						Game.gameState = STATE.LevelScreen;
						game.initLevelScreen(game.getLevel().getNumber());

					}
				}
				
				if(key == KeyEvent.VK_R ) {
					game.startLevel(game.getLevel().getNumber());		
				}
			
			}
			else if(Game.gameState == STATE.Game) {
				move(getDirection(key));

				if(game.getPlayer().isWon || game.getPlayer().isDead) {
					if(key == KeyEvent.VK_SPACE) {
						Game.gameState = STATE.Menu;
						game.initMainMenu();

					}
				}
				
				if(key == KeyEvent.VK_R ) {
					game.startGame();		
				}
			}
			
			 
			 
		
				
	}
			

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
		
		
	}