package principal.inventario;

import java.awt.Point;

public class ObjetoUnicoTiled {
	
	//ATRRIBUTOS
	private Point posicion;
	private Objeto objeto;
	
	//CONSTRUCTOR
	public ObjetoUnicoTiled(Point posicion, Objeto objeto) {
		this.posicion = posicion;
		this.objeto = objeto;
	}
	
	//GETTERS
	public Point getPosicion() {
		return posicion;
	}
	
	public Objeto getObjeto() {
		return objeto;
	}

}