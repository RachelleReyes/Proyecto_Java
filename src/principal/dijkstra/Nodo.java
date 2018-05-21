package principal.dijkstra;

import java.awt.Point;
import java.awt.Rectangle;

import principal.Constantes;

public class Nodo {
	
	//ATRIBUTOS
	private Point posicion;
	private double distancia;
	
	//CONSTRUCTOR
	public Nodo(final Point posicion, final double distancia) {
		this.posicion = posicion;
		this.distancia = distancia;
	}
	
	//GETTERS Y SETTERS
	public Rectangle getAreaPixeles() {
		return new Rectangle(posicion.x * Constantes.LADO_SPRITE, posicion.y * Constantes.LADO_SPRITE,
				Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
	}
	
	public Rectangle getArea() {
		return new Rectangle(posicion.x, posicion.y, Constantes.LADO_SPRITE, Constantes.LADO_SPRITE);
	}
	
	public Point getPosicion() {
		return posicion;
	}
	
	public void cambiarDistancia(double distancia) {
		this.distancia = distancia;
	}
	
	public double getDistancia() {
		return distancia;
	}
}