package snake.gui;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button extends Rectangle {
	public Color color;
	public String label;
	public MyFont myFont;
	private int offsetX;
	private int offsetY;

	public boolean mouseOver;
	public boolean mousePressed;
	public boolean mouseClicked;

	public Button(int x, int y, int width, int height, String label, Color color) {
		super(x, y, width, height);
		this.color = color;
		this.label = label;
		myFont = new MyFont(20, 0,0);


		
	}
	
	public Button(int x, int y, int width, int height, String label, Color color, int offsetX, int offsetY) {
		super(x, y, width, height);
		this.color = color;
		this.label = label;
		myFont = new MyFont(20, offsetX, offsetY);


		
	}
	
	public Button(int x, int y, int width, int height, String label, Color color, MyFont myFont) {
		super(x, y, width, height);
		this.color = color;
		this.label = label;
		this.myFont = myFont;


		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		//Draws button box
		if(this.mouseOver) {
			g2d.setStroke(new BasicStroke(1.5f));
			g2d.setColor(color.darker());
		}
		else {
			g2d.setStroke(new BasicStroke(1f));

			g2d.setColor(color);
		}
		if(this.mousePressed || this.mouseClicked) {
			g2d.setColor(color.brighter());

		}

		g2d.fillRoundRect(x, y, width, height, 10, 10);
		g2d.setColor(Color.white);
		g2d.drawRoundRect(x, y, width, height, 10, 10);

		//Draws button text
		g2d.setColor(Color.white);
		g2d.setFont(myFont.font);
		g2d.drawString(label, x + myFont.offsetX, y + myFont.offsetY);
		
	}
}
