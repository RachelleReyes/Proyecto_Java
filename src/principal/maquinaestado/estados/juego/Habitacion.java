package principal.maquinaestado.estados.juego;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.herramientas.DibujoDebug;

public class Habitacion {
	
	final String nombreHabitacion;
	final BufferedImage imagenHabitacion;
	
	final EstructuraMenuInventario emi;
	
	public Habitacion(final String nombreHabitacion, final BufferedImage imagenHabitacion) {
		this.nombreHabitacion = nombreHabitacion;
		this.imagenHabitacion = imagenHabitacion;
		
		emi = new EstructuraMenuInventario();
	}
	
	public void dibujar(final Graphics g){
		DibujoDebug.dibujarImagen(g, imagenHabitacion, 0,0); 
		emi.dibujar(g);
	}
	
	public void actualizar(){
		
	}

}
