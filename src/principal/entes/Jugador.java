package principal.entes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.herramientas.DibujoDebug;
import principal.inventario.RegistroObjetos;
import principal.inventario.armas.Arma;
import principal.inventario.armas.Desarmado;
import principal.sprites.HojaSprites;
import principal.entes.AlmacenEquipo; 


//ELEMENTOS PRINCIPALES
public class Jugador {

	private double posicionX;
	private double posicionY;
	
	private int direccion;
	
	private double velocidad = 1; 
	
	private boolean enMovimiento;

	private HojaSprites hs;
	
	private BufferedImage imagenActual; 
	
	private final int ANCHO_JUGADOR = 16;
	private final int ALTO_JUGADOR = 16;
	
	private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, ANCHO_JUGADOR, 1);
	private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y + ALTO_JUGADOR, ANCHO_JUGADOR, 1);
	private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);
	private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);
	
	private int animacion;
	private int estado;
	
	//MULTIPLO DE 60 POR LAS 60 ACTUALIZACIONES POR SEG 
	public static final int RESISTENCIA_TOTAL = 600;
	private int resistencia = 600;
	private int recuperacion = 0;
	private final int RECUPERACION_MAXIMA = 400;
	private boolean recuperado = true;
	
	//public int limitePeso = 100; 
	//public int pesoActual = 30;
	
	private AlmacenEquipo ae; 
	
	private ArrayList<Rectangle> alcanceActual;
	
	public Jugador() {
		posicionX = ElementosPrincipales.mapa.getPosicionInicial().getX();
		posicionY = ElementosPrincipales.mapa.getPosicionInicial().getY();
	
		direccion = 0;
		
		enMovimiento = false;
		
		hs = new HojaSprites(Constantes.RUTA_PERSONAJE, Constantes.LADO_SPRITE, false); 
		
		imagenActual = hs.getSprite(0).getImagen();
		
		animacion = 0;
		estado = 0; 
		ae = new AlmacenEquipo((Arma) RegistroObjetos.getObjeto(599));
		
		alcanceActual = new ArrayList<>();
	}
	
	public void actualizar() {
		//cambiarHojaSprites(); 
		gestionarVelocidadResistencia(); 
		cambiarAnimacionEstado();
		enMovimiento = false; 
		determinarDireccion();
		animar();
		actualizarArmas(); 
	}
	
	private void actualizarArmas() {
        if (ae.getArma1() instanceof  Desarmado) {
            return;
        }
        calcularAlcanceAtaque();
        ae.getArma1().actualizar();
    }
	
	private void calcularAlcanceAtaque() {
		if (ae.getArma1() instanceof  Desarmado) {
            return;
        }
        alcanceActual = ae.getArma1().getAlcance(this);
    }
	
	/*private void cambiarHojaSprites() {
        if (ae.getArma1() instanceof Arma && !(ae.getArma1() instanceof Desarmado)) {
            hs = new HojaSprites(Constantes.RUTA_PERSONAJE_PISTOLA, Constantes.LADO_SPRITE, false);
        }
    }*/
	
	private void gestionarVelocidadResistencia() {
		if (GestorControles.teclado.corriendo && resistencia > 0) {
			velocidad = 2; // 2 PIXELES POR ACTUALIZACION
			recuperado = false;
			recuperacion = 0;
		} else {
			velocidad = 1;
			if (!recuperado && recuperacion < RECUPERACION_MAXIMA) {
				recuperacion++;
			}
			if (recuperacion == RECUPERACION_MAXIMA && resistencia < 600) {
				resistencia++;
			}
		}
	}
	
	private void cambiarAnimacionEstado() {
		//SOLO 3 ANIMACIONES AL CAMINAR
		if (animacion < 30) {
			animacion++;
		} else {
			animacion = 0;
		}

		if (animacion < 15) {
			estado = 1;
		} else {
			estado = 2;
		}
	}
	
	private void determinarDireccion() {
		final int velocidadX = evaluarVelocidadX();
		final int velocidadY = evaluarVelocidadY();

		if (velocidadX == 0 && velocidadY == 0) {
			return;
		}

		//CAMINAR SOLO EN VERTICAL U HORIZONTA. NO DIAGONAL
		if ((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0)) {
			mover(velocidadX, velocidadY);
		} else {
			// IZQUIERDA Y ARRIBA A LA VEZ. ASIGNAR LA ULTIMA TECLA PULSADA
			if (velocidadX == -1 && velocidadY == -1) {
				if (GestorControles.teclado.izquierda.getUltimaPulsacion() > GestorControles.teclado.arriba.getUltimaPulsacion()) {
					mover(velocidadX, 0); //IZQUIERDA
				} else {
					mover(0, velocidadY); //ARRIBA
				}
			}
			// IZQUIERDA Y ABAJO A LA VEZ. ASIGNAR LA ULTIMA TECLA PULSADA
			if (velocidadX == -1 && velocidadY == 1) {
				if (GestorControles.teclado.izquierda.getUltimaPulsacion() > GestorControles.teclado.abajo.getUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// DERECHA Y ARRIBA A LA VEZ
			if (velocidadX == 1 && velocidadY == -1) {
				if (GestorControles.teclado.derecha.getUltimaPulsacion() > GestorControles.teclado.arriba
						.getUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// DERECHA Y ABAJO A LA VEZ
			if (velocidadX == 1 && velocidadY == 1) {
				if (GestorControles.teclado.derecha.getUltimaPulsacion() > GestorControles.teclado.abajo.getUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
		}
	}
	
	private int evaluarVelocidadX() {
		int velocidadX = 0; //SIN MOVIMIENTO 

		if (GestorControles.teclado.izquierda.estaPulsada() && !GestorControles.teclado.derecha.estaPulsada()) {
			velocidadX = -1; //MOV. IZQUIERDA
		} else if (GestorControles.teclado.derecha.estaPulsada() && !GestorControles.teclado.izquierda.estaPulsada()) {
			velocidadX = 1;
		}
		return velocidadX;
	}
	
	private int evaluarVelocidadY() {
		int velocidadY = 0;

		if (GestorControles.teclado.arriba.estaPulsada() && !GestorControles.teclado.abajo.estaPulsada()) {
			velocidadY = -1;
		} else if (GestorControles.teclado.abajo.estaPulsada() && !GestorControles.teclado.arriba.estaPulsada()) {
			velocidadY = 1;
		}
		return velocidadY;
	}
	
	private void mover(final int velocidadX, final int velocidadY) {
		enMovimiento = true;

		cambiarDireccion( velocidadX,  velocidadY);
		
		if (!fueraMapa(velocidadX, velocidadY)) {
			
			if (velocidadX == -1 && !enColisionIzquierda(velocidadX)) {
				posicionX += velocidadX * velocidad;
				restarResistencia();
			}
			if (velocidadX == 1 && !enColisionDerecha(velocidadX)) {
				posicionX += velocidadX * velocidad;
				restarResistencia();
			}
			if (velocidadY == -1 && !enColisionArriba(velocidadY)) {
				posicionY += velocidadY * velocidad;
				restarResistencia();
			}
			if (velocidadY == 1 && !enColisionAbajo(velocidadY)) {
				posicionY += velocidadY * velocidad;
				restarResistencia();
			}	
		}
	}
	
	private void restarResistencia() {
		if (GestorControles.teclado.corriendo && resistencia > 0) {
			resistencia--;
		}
	}
	
	private boolean enColisionArriba(int velocidadY) {
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x;
			//VELOCIDAD A LA QUE SE MUEVE MÁS 3 PIXELES POR EL GROSOR DEL RECTANGULO. 3 EL QUE SE COMPARA REALMENTE 
			int origenY = area.y + velocidadY * (int) velocidad + 3 * (int) velocidad;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

			if (LIMITE_ARRIBA.intersects(areaFutura)) {
				return true;
			}
		}
		return false;		
	}

	private boolean enColisionAbajo(int velocidadY) {
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x;
			int origenY = area.y + velocidadY * (int) velocidad - 3 * (int) velocidad;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

			if (LIMITE_ABAJO.intersects(areaFutura)) {
				return true;
			}
		}
		return false;
	}

	private boolean enColisionIzquierda(int velocidadX) {
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x + velocidadX * (int) velocidad + 3 * (int) velocidad;
			int origenY = area.y;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

			if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
				return true;
			}
		}
		return false;
	}

	private boolean enColisionDerecha(int velocidadX) {
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x + velocidadX * (int) velocidad - 3 * (int) velocidad;
			int origenY = area.y;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width, area.height);

			if (LIMITE_DERECHA.intersects(areaFutura)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean fueraMapa(final int velocidadX, final int velocidadY) {

		int posicionFuturaX = (int) posicionX + velocidadX * (int) velocidad;
		int posicionFuturaY = (int) posicionY + velocidadY * (int) velocidad;

		final Rectangle bordesMapa = ElementosPrincipales.mapa.getBordes(posicionFuturaX, posicionFuturaY);

		final boolean fuera;

		if (LIMITE_ARRIBA.intersects(bordesMapa) || LIMITE_ABAJO.intersects(bordesMapa)
				|| LIMITE_IZQUIERDA.intersects(bordesMapa) || LIMITE_DERECHA.intersects(bordesMapa)) {
			fuera = false;
		} else {
			fuera = true;
		}
		return fuera;
	}
	
	private void cambiarDireccion(final int velocidadX, final int velocidadY) {
		if (velocidadX == -1) {
			direccion = 3;
		} else if (velocidadX == 1) {
			direccion = 2;
		}

		if (velocidadY == -1) {
			direccion = 1;
		} else if (velocidadY == 1) {
			direccion = 0;
		}
	}
	
	private void animar() {
		if (!enMovimiento) {
			estado = 0;
			animacion = 0;
		}
		imagenActual = hs.getSprite(direccion, estado).getImagen();
	}
	
	public void dibujar(Graphics g) {
		final int centroX = Constantes.ANCHO_JUEGO/2 - Constantes.LADO_SPRITE/2;
		final int centroY = Constantes.ALTO_JUEGO/2 - Constantes.LADO_SPRITE/2;
		
		DibujoDebug.dibujarImagen(g, imagenActual, centroX, centroY);
		
		if(!alcanceActual.isEmpty()) {
			dibujarAlcance(g); 
		}
		
		//g.drawRect(LIMITE_ARRIBA.x, LIMITE_ARRIBA.y, LIMITE_ARRIBA.width, LIMITE_ARRIBA.height);
		//g.drawRect(LIMITE_ABAJO.x, LIMITE_ABAJO.y, LIMITE_ABAJO.width, LIMITE_ABAJO.height);
		//g.drawRect(LIMITE_IZQUIERDA.x, LIMITE_IZQUIERDA.y, LIMITE_IZQUIERDA.width, LIMITE_IZQUIERDA.height);
		//g.drawRect(LIMITE_DERECHA.x, LIMITE_DERECHA.y, LIMITE_DERECHA.width, LIMITE_DERECHA.height);
	}
	
	private void dibujarAlcance(final Graphics g) {
		g.setColor(Color.RED); 
        DibujoDebug.dibujarRectanguloRelleno(g, alcanceActual.get(0));
    }
	
	public void establecerPosicionX(double posicionX) {
		this.posicionX = posicionX;
	}
	public void establecerPosicionY(double posicionY) {
		this.posicionY = posicionY;
	}
	public double getPosicionX() {
		return posicionX;
	}
	public double getPosicionY() {
		return posicionY; 
	}
	public int getPosicionXInt() {
		return (int)posicionX;
	}
	public int getPosicionYInt() {
		return (int)posicionY; 
	}
	public int getAncho() {
		return ANCHO_JUGADOR;
	}
	public int getAlto() {
		return ALTO_JUGADOR;
	}
	public int getResistencia() {
		return resistencia;
	}
	public Rectangle get_LIMITE_ARRIBA() {
		return LIMITE_ARRIBA;
	}
	public AlmacenEquipo getAlmacenEquipo() {
        return ae;
    }
	public int getDireccion() {
		return direccion; 
	}
	public Point getPosicion() {
	    return new Point((int)posicionX, (int)posicionY);
	}
	public ArrayList<Rectangle> getAlcanceActual() {
	    return  alcanceActual;
	}
}
