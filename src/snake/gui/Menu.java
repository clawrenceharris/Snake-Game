package snake.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import snake.main.Game;

public class Menu extends MouseAdapter{
	protected ArrayList<Button> buttons = new ArrayList<>();
	protected boolean mouseClicked;
	protected boolean mouseOver;
	protected Button selectedButton;
	protected Button button;
	protected Game game;
	
	public Menu() {
		
	}
	
	public Menu(ArrayList<Button> buttons) {
		
		this.buttons = buttons;
	}
	
	public Menu(Button button) {
		buttons.add(button);
	}

	public Menu(Game game) {
		this.game = game;
	}

	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for(Button b : buttons) {
			if(b.contains(e.getPoint())) {
				b.mouseClicked = true;
			}
		}	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		for(Button b : buttons) {
			if(b.contains(e.getPoint())) {
				b.mouseOver = true;

			}
			else
				b.mouseOver = false;

		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		for(Button b : buttons) {
			if(b.contains(e.getPoint())) {
				b.mousePressed = true;

			}
		}
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		for(Button b : buttons) {
			if(b.contains(e.getPoint()) && b.mousePressed) {
				selectedButton = b;
			}
		}
		
	}

	public Button getButtonClicked() {

		return selectedButton;
	}
	
	public ArrayList<Button> getButtons() {

		return buttons;
	}
	
	

}
