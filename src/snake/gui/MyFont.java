package snake.gui;

import java.awt.Font;

public class MyFont {
	public int size;
	public int offsetX;
	public int offsetY;
	public Font font;
	
	
	public MyFont(int size, int offsetX, int offsetY) {
		this.size = size;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		font = new Font("ariel", 0, size);
		
		
	}
}
