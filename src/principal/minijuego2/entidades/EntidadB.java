package principal.minijuego2.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntidadB {
	public void actualizar();
	public void dibujar(Graphics g);
	public Rectangle getBounds();

	public double getX();
	public double getY();
}
