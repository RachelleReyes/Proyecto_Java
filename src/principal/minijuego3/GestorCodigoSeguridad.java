package principal.minijuego3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import principal.Constantes;
import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElementos;
import principal.maquinaestado.EstadoJuego;

public class GestorCodigoSeguridad implements EstadoJuego{

	private final SuperficieDibujo sd;
	
	//private String operacion;
	private boolean gestorMenuPrincialActivo;
	private int [] numeros;
	
	private int tiempoActual = 0;
	private int tiempoEspera = 20; 
	private int casillaActiva=0;
	
	private int numero1, numero2;
	private Operacion resolverOperacion;
	private int resultado;
	private String tipoOperacion;
	Random r = new Random();
	
	private boolean juegoGanado;
	
	public Rectangle [] digitos;

	public final Rectangle botonIngresar = new Rectangle(Constantes.ANCHO_JUEGO - 170, 325, 100, 25);
	
	public GestorCodigoSeguridad(final SuperficieDibujo sd) {
		this.sd = sd; 
		inicializar();
		gestorMenuPrincialActivo = false; 
		numeros = new int[4];
	}

	public void actualizar() {
		gestionarVelocidadCambio();
	}
	
	public void inicializar() {
		digitos = new Rectangle[4];
		for(int i=0; i<4; i++) {
				digitos[i] = new Rectangle(Constantes.ANCHO_JUEGO - 550+120*i, 130, 100, 100);
		}
		
		obtenerOperacion();
		generarNumeros();
		realizarOperacion();
	}
	private void gestionarVelocidadCambio() {
		if(tiempoActual <= tiempoEspera) {
			tiempoActual++;
		}
	}
	
	
	public void dibujar(Graphics g) {
	
		if (GestorControles.teclado.siguiente.estaPulsada() && !GestorControles.teclado.anterior.estaPulsada() && tiempoActual>=tiempoEspera) {
			casillaActiva++;
			tiempoActual=0;
			
			
		}else if (!GestorControles.teclado.siguiente.estaPulsada() && GestorControles.teclado.anterior.estaPulsada() && tiempoActual>=tiempoEspera) {
			casillaActiva--;
			tiempoActual =0;
		}
		
		if(casillaActiva>3) {casillaActiva =3;}
		else if(casillaActiva<0) {casillaActiva =0;}
		
		
		DibujoDebug.dibujarRectanguloTraslucidoRelleno(g,digitos[casillaActiva], Color.RED);
		numeros[casillaActiva]= GestorControles.teclado.numero;
		
		Font fnt1 =  new Font("chiller",Font.BOLD, 20);
		g.setFont(fnt1);
		g.setColor(Color.white);
	
		DibujoDebug.dibujarString(g, "INGRESE LA " +tipoOperacion+ " DE LOS DOS NUMEROS OBTENIDOS", 100, 50);
		
		
		Font fnt2 =  new Font("chiller",Font.BOLD, 90);
		g.setFont(fnt2);
		
		for(int i =0; i<4;i++) {
			DibujoDebug.dibujarRectanguloContorno(g,digitos[i]); 
			DibujoDebug.dibujarString(g, String.valueOf(numeros[i]), digitos[i].x + 32,digitos[i].y + 82);
		}
		
		
		DibujoDebug.dibujarRectanguloContorno(g, botonIngresar);
		
		Font fnt3 =  new Font("chiller",Font.BOLD, 14);
		g.setFont(fnt3);
		DibujoDebug.dibujarString(g, "INGRESAR", botonIngresar.x + 22, botonIngresar.y + 17);
		
		if (sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(botonIngresar))){
			DibujoDebug.dibujarRectanguloTraslucidoRelleno(g,botonIngresar, Color.RED);
		
		}
		
		if(sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
				intersects(EscaladorElementos.escalarRectanguloArriba(botonIngresar)) == true) {
				compararRespuesta();
		}
		
	}
	
	private void obtenerOperacion() {
		switch(r.nextInt(3)) {
		case 0: tipoOperacion = "SUMA";
			break;
		case 1: tipoOperacion = "RESTA";
			break;
		case 2: tipoOperacion = "MULTIPLICACION";
			break;
		}
	}
	
	private void generarNumeros() {
		int temp;
		numero1 = r.nextInt(50)+10;
		numero2 = r.nextInt(50)+10;
		
		if(numero2>numero1) {
			temp = numero1;
			numero1= numero2;
			numero2= temp;
		}
		
		System.out.println(numero1);
		System.out.println(numero2);
	}
	
	private void compararRespuesta() {
		int respuestaUsuario;
		
		respuestaUsuario = numeros[0]*1000 + numeros[1]*100 + numeros[2]*10 + numeros[3]*1;
		if(respuestaUsuario== resultado) {
			juegoGanado = true;
			System.out.println("Ganaste el juego");
		}
	}
	
	private void realizarOperacion() {
		resolverOperacion = new Operacion(numero1, numero2);
		
		if(tipoOperacion == "SUMA") {
			resultado = resolverOperacion.suma();
		} else if(tipoOperacion == "RESTA") {
			resultado = resolverOperacion.resta();
		} else if(tipoOperacion == "MULTIPLICACION") {
			resultado = resolverOperacion.multiplicacion();
		}
	}

}
