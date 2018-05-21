package principal.minijuego2.herramientas;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import principal.Constantes;
import principal.minijuego2.elementos.EnemigoFraccionImpropia;
import principal.minijuego2.elementos.EnemigoFraccionMixta;
import principal.minijuego2.elementos.EnemigoFraccionPropia;
import principal.minijuego2.entidades.EntidadA;
import principal.minijuego2.entidades.EntidadB;
import principal.minijuego2.main.GestorPrincipal2;

public class Controlador {
	private GestorPrincipal2 minijuego2;
	private ArrayList<EntidadA> ea = new ArrayList<EntidadA>();
	private ArrayList<EntidadB> eb = new ArrayList<EntidadB>();
	public Rectangle fraccion; 
	EntidadA enta;
	EntidadB entb;
	
	Random r = new Random();
	private int tipo_fraccion;
	private int [] ubicacionx;
	
	public Controlador(GestorPrincipal2 minijuego2, int tipo_fraccion) {
		this.minijuego2 =  minijuego2;
		this.tipo_fraccion = tipo_fraccion;
		ubicacionx = new int[4];  
	}
	
	public void calcularX() {
		ubicacionx[0]= r.nextInt(Constantes.ANCHO_JUEGO-Constantes.LADO_SPRITE2);
		for(int i =1; i<4; i++) {
			ubicacionx[i] =0;
			for(int j = 0; j<i; j++)
			{
				do {
					ubicacionx[i]= r.nextInt(Constantes.ANCHO_JUEGO-Constantes.LADO_SPRITE2);
				}while(ubicacionx[i] == ubicacionx[j] || Math.abs(ubicacionx[i]-ubicacionx[j])<Constantes.LADO_SPRITE2*3);
			}
		}
	}
	
	public void crearEnemigo() {
		calcularX();

		switch(tipo_fraccion) {
		case 0:
			agregarEntidad(new EnemigoFraccionPropia(ubicacionx[0],-10,this, minijuego2, tipo_fraccion));
			break;
		case 1: 	
			agregarEntidad(new EnemigoFraccionImpropia(ubicacionx[0],-10,this, minijuego2, tipo_fraccion));
			break;
		case 2: 
			agregarEntidad(new EnemigoFraccionMixta(ubicacionx[0],-10,this, minijuego2, tipo_fraccion));
			break;
		}
		
	agregarEntidad(new EnemigoFraccionPropia(ubicacionx[1],-10,this, minijuego2, tipo_fraccion));
	agregarEntidad(new EnemigoFraccionImpropia(ubicacionx[2],-10,this, minijuego2, tipo_fraccion));
	agregarEntidad(new EnemigoFraccionMixta(ubicacionx[3],-10,this, minijuego2, tipo_fraccion));
	minijuego2.setEnemigosVivos(minijuego2.getEnemigosVivos() +4);
}
	
	public void actualizar() {
		//CLASE A
		for(int i =0; i< ea.size(); i++) {
			enta = ea.get(i);
			enta.actualizar();
		}
		//CLASE B
		for(int i =0; i< eb.size(); i++) {
			entb = eb.get(i);
			entb.actualizar();
			if(entb.getY()>Constantes.ANCHO_JUEGO-Constantes.LADO_SPRITE2) {
				quitarEntidad(entb);
				minijuego2.setEnemigosVivos(minijuego2.getEnemigosVivos() -1);
			}
		}
	}
			
	public void dibujar(Graphics g) {
		//CLASE A
		for(int i =0; i< ea.size(); i++) {
			enta = ea.get(i);
			enta.dibujar(g);
			//DibujoDebug.dibujarImagen(g, enta, enta.getX(), enta.getY());

		}
		//CLASE B
		for(int i =0; i< eb.size(); i++) {
			entb = eb.get(i);
			entb.dibujar(g);
		}
	}
	
	public void agregarEntidad(EntidadA objeto) {
		ea.add(objeto);
	}
	public void quitarEntidad(EntidadA objeto) {
		ea.remove(objeto);
	}
	public void agregarEntidad(EntidadB objeto) {
		eb.add(objeto);
	}
	public void quitarEntidad(EntidadB objeto) {
		eb.remove(objeto);
	}
	public ArrayList<EntidadA> getEntidadA() {
		return ea;
	}
	public ArrayList<EntidadB> getEntidadB() {
		return eb;
	}
}
