package snake.main;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
		
		this.add(new Game());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Snake");
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		

	}
}
