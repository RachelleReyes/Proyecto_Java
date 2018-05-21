package principal.minijuego2.elementos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import principal.Constantes;
import principal.herramientas.DibujoDebug;
import principal.minijuego2.entidades.EntidadA;
import principal.minijuego2.entidades.EntidadB;
import principal.minijuego2.fracciones.FraccionPropia;
import principal.minijuego2.herramientas.Animacion;
import principal.minijuego2.herramientas.Colisiones;
import principal.minijuego2.herramientas.Controlador;
import principal.minijuego2.main.GestorPrincipal2;
import principal.sprites.HojaSprites;

public class EnemigoFraccionPropia extends ObjetoJuego implements EntidadB{

	Random r = new Random();
	private GestorPrincipal2 minijuego2;
	private Controlador controlador;
	private int speed = r.nextInt(2) +1;
	private int tipoFraccion;
	private int imagenAleatoria;
	private FraccionPropia f_propia;
	private int numerador, denominador, numEntero;
	private int distanciax1, distanciax2, distanciax3;	
	
	private HojaSprites hs;
	private Animacion anim;
	
	
	public EnemigoFraccionPropia(double x, double y, Controlador controlador, GestorPrincipal2 minijuego2, int tipoFraccion) {
		super(x,y);
		this.controlador = controlador;
		this.minijuego2 = minijuego2;
		this.tipoFraccion = tipoFraccion;
		hs = new HojaSprites("/spriteMinijuego2.png",Constantes.LADO_SPRITE2, false); 
		
		imagenAleatoria = r.nextInt(2);
		
		switch(imagenAleatoria) {
			case 0:
				anim = new Animacion(5,hs.getSprite(0).getImagen(),hs.getSprite(1).getImagen(),hs.getSprite(2).getImagen());
				break;
			case 1:
				anim = new Animacion(5,hs.getSprite(3).getImagen(),hs.getSprite(4).getImagen(),hs.getSprite(5).getImagen());
				break;
		}
		
		
		 f_propia = new FraccionPropia(numerador, denominador);
		 
	}
	
	public void actualizar() {
		y += speed;
		if(y>(Constantes.ALTO_JUEGO* 2)) {
			speed = r.nextInt(2) +1;
			x= r.nextInt(Constantes.ANCHO_JUEGO);
			y=-10;
		}
		
		for(int i =0; i<minijuego2.ea.size(); i++) {
			EntidadA tempEnt = minijuego2.ea.get(i); 
			
			if(Colisiones.Colision(this, tempEnt ) &&  tipoFraccion==0 && imagenAleatoria == 0) {
				controlador.quitarEntidad(tempEnt);
				controlador.quitarEntidad(this);
				GestorPrincipal2.PUNTAJE +=10;
				minijuego2.puntos.reproducir();
				minijuego2.setEnemigos_disparados(minijuego2.getEnemigos_disparados() +1);
				minijuego2.setEnemigosVivos(minijuego2.getEnemigosVivos() -1);
			} else if(Colisiones.Colision(this, tempEnt ) &&  tipoFraccion==0 && imagenAleatoria == 1) {
				controlador.quitarEntidad(tempEnt);
				controlador.quitarEntidad(this);
				GestorPrincipal2.PUNTAJE +=10;
				minijuego2.puntos.reproducir();
				minijuego2.setEnemigos_disparados(minijuego2.getEnemigos_disparados() +1);
				minijuego2.setEnemigosVivos(minijuego2.getEnemigosVivos() -1);
			}
			else if(Colisiones.Colision(this, tempEnt)) {
				controlador.quitarEntidad(tempEnt);
				controlador.quitarEntidad(this);
				GestorPrincipal2.PUNTAJE -=10;
				minijuego2.equivocada.reproducir();
				minijuego2.setEnemigos_disparados(minijuego2.getEnemigos_disparados() +1);
				minijuego2.setEnemigosVivos(minijuego2.getEnemigosVivos() -1);
			}
		}
		anim.runAnimacion();
	}
	
	private void imprimirFraccion(Graphics g) {
		numerador = f_propia.getNumerador();
		denominador = f_propia.getDenominador();
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 16));	
		
		if(numerador>9) {
			distanciax1 = 18;
		}else {
			distanciax1 = 22; 
		}
		
		if(denominador>9) {
			distanciax2 = 18;
		}else {
			distanciax2 = 22; 
		}
		
		if(numEntero>9) {
			distanciax3 = 7;
		}else {
			distanciax3 = 10; 
		}
		
		if(numEntero!=0) {
			DibujoDebug.dibujarString(g, String.valueOf(numEntero), (int)x + distanciax3, (int)y+ 22);
			distanciax1+=2;
			distanciax2+=2;
		}
		
		
		DibujoDebug.dibujarString(g, String.valueOf(numerador), (int)x + distanciax1, (int)y +17);
		DibujoDebug.dibujarString(g, "---", (int)x + (distanciax1 +distanciax2)/2, (int)y + 27);
		DibujoDebug.dibujarString(g, String.valueOf(denominador), (int)x + distanciax2, (int)y + 40);
	}
	
	
	public void dibujar(Graphics g) {
		anim.dibujarAnimacion(g,x,y,0);
		imprimirFraccion(g);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, Constantes.LADO_SPRITE2, Constantes.LADO_SPRITE2);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
}