import java.awt.Graphics;

public class Nodo {
	public int x,y;
	public static final int d=60;
	
	public Nodo(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	public void pintar(Graphics g) {
		g.drawOval(this.x-d/2, this.y-d/2, d, d);
	}
}
