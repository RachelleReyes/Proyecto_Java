package principal.minijuego2.elementos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.herramientas.DibujoDebug;
import principal.minijuego2.entidades.EntidadA;
import principal.sprites.HojaSprites;

public class Bala extends ObjetoJuego implements EntidadA {
	private HojaSprites hs;
	private BufferedImage imagen; 
	
	public Bala(double x, double y) {
		super(x,y);
		hs = new HojaSprites("/principal.png",Constantes.LADO_SPRITE2, false); 
		imagen= hs.getSprite(8).getImagen();
	}
	
	public void actualizar() {
		y-= 10;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, Constantes.LADO_SPRITE2,Constantes.LADO_SPRITE2);
	}
	
	public void dibujar(Graphics g) {
		DibujoDebug.dibujarImagen(g, imagen,(int)x,(int)y);
	}
	
	public double getY() {
		return y;
	}
	public double getX() {
		return x;
	}
}
