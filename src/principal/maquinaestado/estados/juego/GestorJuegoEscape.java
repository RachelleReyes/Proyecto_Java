package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElementos;
import principal.maquinaestado.EstadoJuego;

public class GestorJuegoEscape implements EstadoJuego {
	
	private final SuperficieDibujo sd;
	
	private boolean gestorMenuPrincipalActivo;
	
	private Habitacion[] habitaciones;
	
	private Habitacion habitacionActual;
	
	private int habitacionActualInt;
	
	private int ubicacionActualCasaInt = 0; 
	
	private int tiempoActual = 0;
	private int tiempoEspera = 20; 
	
	public  GestorJuegoEscape(final SuperficieDibujo sd) {
		
		this.sd = sd; 
		gestorMenuPrincipalActivo = false;
		
		iniciarHabitaciones(sd);
		iniciarHabitacionActual();
		habitacionActualInt = 0;
	
	}
	
	private void iniciarHabitacionActual() {
		habitacionActual = habitaciones[0];
	}

	public void actualizar() {
		gestionarVelocidadCambio();
		
	}

	public void dibujar(Graphics g) {
		
		Rectangle siguienteHabitacionDerecha = new Rectangle(Constantes.ANCHO_JUEGO - 100 , 0, 100, Constantes.ALTO_JUEGO);
		Rectangle siguienteHabitacionIzquierda = new Rectangle(0,0,25,Constantes.ALTO_JUEGO);
		
		habitacionActual.dibujar(g);
		
		if(sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.
				escalarRectanguloArriba(siguienteHabitacionIzquierda)) == true) {
			DibujoDebug.dibujarTriangulo(g,6,22, 22,160, 149, 171, Color.RED);
			
			if(sd.getRaton().getClick() && tiempoActual>=30) {
				
				switch (habitacionActualInt){
				case 0: cambiarHabitacionActual(2); tiempoActual = 0; break;
				case 1: cambiarHabitacionActual(0); tiempoActual = 0; break;
				case 2: cambiarHabitacionActual(3); tiempoActual = 0; break;
				case 3: cambiarHabitacionActual(1); tiempoActual = 0; break;
				}
			}
		}

		if(sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.
				escalarRectanguloArriba(siguienteHabitacionDerecha)) == true) {
			DibujoDebug.dibujarTriangulo(g,620,635, 620,149, 160, 171, Color.RED);
			
			if(sd.getRaton().getClick() && tiempoActual>=tiempoEspera) {
	
				switch (habitacionActualInt){
				case 0: if(sd.getRaton().getClick()) {cambiarHabitacionActual(1); tiempoActual = 0; break;}
				case 1: if(sd.getRaton().getClick()) {cambiarHabitacionActual(3); tiempoActual = 0; break;}
				case 2: if(sd.getRaton().getClick()) {cambiarHabitacionActual(0); tiempoActual = 0; break;}
				case 3: if(sd.getRaton().getClick()) {cambiarHabitacionActual(2); tiempoActual = 0; break;}
				}
			}
		}
	/*
		if(habitacionActual == habitaciones[0] && sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
				intersects(EscaladorElementos.escalarRectanguloArriba(new Rectangle(0, 0, 400, 400))) == true) {
			
		} 
*/		
	}
	private void gestionarVelocidadCambio() {
		if(tiempoActual <= tiempoEspera) {
			tiempoActual++;
		}
	}
	
	private void iniciarHabitaciones(final SuperficieDibujo sd) {
		habitaciones = new Habitacion[4];
		habitaciones[0] = new Habitacion("Sala", CargadorRecursos.cargarImagenCompatibleOpaca("/sala.jpg"));
		habitaciones[1] = new Habitacion("Cocina", CargadorRecursos.cargarImagenCompatibleOpaca("/cocina.jpg"));
		habitaciones[2] = new Habitacion("Cuarto", CargadorRecursos.cargarImagenCompatibleOpaca("/cuarto.jpg"));
		habitaciones[3] = new Habitacion("Cochera", CargadorRecursos.cargarImagenCompatibleOpaca("/cochera.jpg"));
	}
	
	public void cambiarGestorJuegoActivo() {
		this.gestorMenuPrincipalActivo = !gestorMenuPrincipalActivo; 
	}
	
	public void cambiarHabitacionActual(final int nuevaHabitacion) {
		habitacionActual = habitaciones[nuevaHabitacion];
		habitacionActualInt = nuevaHabitacion; 
	}

	//GETTERS
	
	public boolean getGestorJuegoActivo() {
		return gestorMenuPrincipalActivo;
	}
	
	public Habitacion getHabitacionActual() {
		return habitacionActual;
	}
		
	public int getHabitacionActualInt() {
		return habitacionActualInt; 
	}

}
