package snake.main;

public class Position {
	public int x;
	public int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Position(Position pos) {
		this.setPos(pos);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setPos(Position pos) {
		this.x = pos.getX();
		this.y = pos.getY();
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean contains(Position position) {
		if(position.x == x && position.y == y) {
			return true;
		}
		
		return false;
	}
}
