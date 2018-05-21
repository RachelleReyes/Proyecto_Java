package principal.minijuego2.elementos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.control.GestorControles;
import principal.herramientas.DibujoDebug;
import principal.minijuego2.entidades.EntidadA;
import principal.minijuego2.entidades.EntidadB;
import principal.minijuego2.herramientas.Colisiones;
import principal.minijuego2.herramientas.Controlador;
import principal.minijuego2.main.GestorPrincipal2;
import principal.sprites.HojaSprites;

public class Jugador extends ObjetoJuego implements EntidadA{

	private double velX = 0;
	private double velY = 0;
	private boolean disparando = false;
	private String direccion = "modoEspera";
	
	private HojaSprites hs;
	
	private BufferedImage imagenActual; 
	GestorPrincipal2 minijuego2;
	Controlador controlador;
	
	public Jugador(double x, double y,GestorPrincipal2 minijuego2, Controlador controlador) {
		super(x,y);
		this.minijuego2 = minijuego2;
		this.controlador = controlador;
		
		hs = new HojaSprites("/principal.png",Constantes.LADO_SPRITE2, false); 
		imagenActual = hs.getSprite(1).getImagen();
	
	}

	public void actualizar() {
		determinarTeclapulsada();
		determinarImagen(direccion); 
		x+= velX;
		y+= velY;
		
		if(x<=0) {
			x=0;
		}
		if(x>=Constantes.ANCHO_JUEGO-Constantes.LADO_SPRITE2) {
			x=Constantes.ANCHO_JUEGO-Constantes.LADO_SPRITE2;
		}
		if(y<=0) {
			y=0;
		}
		if(y>=(Constantes.ALTO_JUEGO-Constantes.LADO_SPRITE2-25)) {
			y=Constantes.ALTO_JUEGO-Constantes.LADO_SPRITE2-25;
		}
		
		for(int i= 0; i<minijuego2.eb.size();i++) {
			EntidadB tempEnt = minijuego2.eb.get(i);
			
			if(Colisiones.Colision(this, tempEnt)) {
				controlador.quitarEntidad(tempEnt);
				GestorPrincipal2.VIDA -=10;
				minijuego2.perdervida.reproducir();
				minijuego2.setEnemigos_disparados(minijuego2.getEnemigos_disparados() +1);
			}
		}
	}
	
	public void determinarImagen(String direccion) {
		
		if(direccion.equals("modoEspera")) {
			 imagenActual = hs.getSprite(1).getImagen();
		} else if(direccion.equals("izquierda")) {
			 imagenActual = hs.getSprite(0).getImagen();
		} else if(direccion.equals("derecha")) {
			 imagenActual = hs.getSprite(2).getImagen();
		}
	}
	
	public void determinarTeclapulsada() {
		if (GestorControles.teclado.izquierda.estaPulsada() && !GestorControles.teclado.derecha.estaPulsada()) {
			setVelX(-8);
			direccion = "izquierda";
		} else if (GestorControles.teclado.derecha.estaPulsada() && !GestorControles.teclado.izquierda.estaPulsada()) {
			setVelX(8);
			direccion = "derecha";
			//anim = new Animacion(5, this.tex.jugador[2]);
		}  else if (!GestorControles.teclado.izquierda.estaPulsada() || !GestorControles.teclado.derecha.estaPulsada()) {
			setVelX(0);
			//direccion = 1;
			direccion = "modoEspera";
			//anim = new Animacion(5, this.tex.jugador[1]);
		} 
		
		if (GestorControles.teclado.arriba.estaPulsada() && !GestorControles.teclado.abajo.estaPulsada()) {
			setVelY(-8);
			//direccion = 1;
		} else if (GestorControles.teclado.abajo.estaPulsada() && !GestorControles.teclado.arriba.estaPulsada()) {
			setVelY(8);
		} else if (!GestorControles.teclado.arriba.estaPulsada() || !GestorControles.teclado.abajo.estaPulsada()) {
			setVelY(0);
		} 
		
		 if (GestorControles.teclado.atacando && !disparando) {
			controlador.agregarEntidad(new Bala (getX(),getY()));
			disparando =true;
		} else if (!GestorControles.teclado.atacando && disparando) {
			disparando =false;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, Constantes.LADO_SPRITE2, Constantes.LADO_SPRITE2);
	}
	public void dibujar(Graphics g) {
		DibujoDebug.dibujarImagen(g, imagenActual,(int)x,(int)y);
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
}
