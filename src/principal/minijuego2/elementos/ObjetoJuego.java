package principal.minijuego2.elementos;

import java.awt.Rectangle;

public class ObjetoJuego {

	public double x,y;

	public ObjetoJuego(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Rectangle getBounds(int ancho, int alto) {
		return new Rectangle((int)x, (int)y, ancho, alto);
	}
	
}
 