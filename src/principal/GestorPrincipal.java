package principal;

import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.maquinaestado.GestorEstados;
import principal.sonido.Sonido;

public class GestorPrincipal {
	
	private boolean enFuncionamiento = false; 
	private String titulo;
	
	public static final int ANCHO = 1280; //Ancho de la pantalla
	public static final int ALTO = 720;
	
	public static SuperficieDibujo sd;
	private Ventana ventana;
	private GestorEstados ge;
	
	private static int aps = 0;
	private static int fps = 0; 
	
	private Sonido musica = new Sonido("/musica.wav");
	
	private GestorPrincipal(final String titulo) {
		this.titulo = titulo;
	}
	
	public static void main(String[] args) {
		
		GestorPrincipal gp = new GestorPrincipal("MINIJUEGO");
		
		gp.iniciarJuego();
		gp.iniciarBuclePrincipal();
	}

	private void iniciarJuego() {
		enFuncionamiento = true;
		inicializar();
		musica.repetir();
	}
	
	private void inicializar() {
		sd = new SuperficieDibujo(ANCHO, ALTO);
		ventana = new Ventana(titulo, sd);
		ge = new GestorEstados(sd);
	}

	private void iniciarBuclePrincipal() {
		int actualizacionesAcumuladas=0;
		int framesAcumulados=0;
		
		final int NS_POR_SEGUNDO = 1000000000; //NUMERO DE NANOSEG EN UN SEG. 
		final byte APS_OBJETIVO = 60 ; //ACTUALIZACIONES POR SEGUNDO
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO; //CUANTOS NANOSEGUNDOS POR ACTUALIZACIÓN
		
		//CANTIDAD DE TIEMPO EN NANOSEG EN ESE TIEMPO EXACTO. 
		long referenciaActualizacion = System.nanoTime();// SYSTEM.NANOTIME NO TOMA EN CUENTA EL S.O. SINO EL PROCESADOR
		long referenciaContador = System.nanoTime();
		
		double tiempoTranscurrido; 
		double delta=0; //CANTIDAD DE TIEMPO QUE HA TRANSCURRIDO DESDE UNA ACTUALIZACION
		
		while(enFuncionamiento) {
			final long inicioBucle =System.nanoTime(); // INICIA CRONOMETRO
			
			tiempoTranscurrido = inicioBucle - referenciaActualizacion; 
			referenciaActualizacion = inicioBucle; 
			
			delta += tiempoTranscurrido/ NS_POR_ACTUALIZACION;
			
			//TEMPORIZADOR 60 POR SEG
			while(delta >= 1) { 
				actualizar();
				actualizacionesAcumuladas++; 
				delta--; 
			}
			
			dibujar(); 
			framesAcumulados++;
			
			//SI LA REFERENCIA ES MÁS DE 1 SEG SE ACTUALIZARÁ
			if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {

				aps = actualizacionesAcumuladas; 
				//System.out.println(""+fps);
				fps = framesAcumulados;
				
				actualizacionesAcumuladas=0;
				framesAcumulados=0;
				referenciaContador = System.nanoTime();
			}
		}
	}

	private void actualizar() {
		
		ge.actualizar(sd);
		sd.actualizar();
	}
	
	private void dibujar() {
		sd.dibujar(ge);
	}
	
	public static int getAPS() {
		return aps; 
	}
	public static int getFPS() {
		return fps;
	}
}
