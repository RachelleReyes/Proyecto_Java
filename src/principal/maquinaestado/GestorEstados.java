package principal.maquinaestado;
import java.awt.Graphics;
import java.awt.Rectangle;

import principal.Constantes;
import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.EscaladorElementos;
import principal.maquinaestado.estados.juego.GestorJuego;
import principal.maquinaestado.estados.juego.GestorJuegoEscape;
import principal.maquinaestado.estados.juego.GestorMenuMinijuego2;
import principal.maquinaestado.estados.menuPrincipal.GestorMenuMinijuegos;
import principal.maquinaestado.estados.menuPrincipal.GestorMenuPrincipal;
import principal.maquinaestado.estados.menujuego.GestorMenu;
import principal.minijuego2.main.GestorPrincipal2;
import principal.minijuego3.GestorCodigoSeguridad;

public class GestorEstados {
	
	//ATRIBUTOS
	private EstadoJuego[] estados;
	private EstadoJuego estadoActual;
	private int estadoActualInt = 0; 

	//CONSTRUCTOR
	public GestorEstados(final SuperficieDibujo sd) {
		iniciarEstados(sd);
		iniciarEstadoActual();
	}

	//METODOS
	private void iniciarEstados(final SuperficieDibujo sd) {
		estados = new EstadoJuego[8];
		estados[0] = new GestorMenuPrincipal(sd); 
		estados[1] = new GestorMenu(sd); 
		estados[2] = new GestorJuego();
		estados[3] = new GestorMenuMinijuegos(sd); 
		estados[4] = new GestorJuegoEscape(sd);
		estados[5] = new GestorMenuMinijuego2(sd);
		estados[6] = new GestorPrincipal2();
		estados[7] = new GestorCodigoSeguridad(sd);
	}
	
	private void iniciarEstadoActual() {
		estadoActual = estados[0];
	}
	
	public void actualizar(final SuperficieDibujo sd) {
		estadoActual.actualizar();
		
		switch (estadoActualInt) {
		
		case 0:
			if(sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
				intersects(EscaladorElementos.escalarRectanguloArriba(new Rectangle(Constantes.ANCHO_JUEGO - 190, 125, 100, 25))) == true) {
					cambiarEstadoActual(4);
			} else if (sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
					intersects(EscaladorElementos.escalarRectanguloArriba(new Rectangle(Constantes.ANCHO_JUEGO  - 190 , 160, 100, 25))) == true){
				cambiarEstadoActual(3);
			}else if (sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
					intersects(EscaladorElementos.escalarRectanguloArriba(new Rectangle(Constantes.ANCHO_JUEGO - 190, 230, 100, 25))) == true){
				//System.exit(0);
				cambiarEstadoActual(7);
			}else if (sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
					intersects(EscaladorElementos.escalarRectanguloArriba(new Rectangle(Constantes.ANCHO_JUEGO - 190, 265, 100, 25))) == true){
				System.exit(0);
			}
			break;
			
		case 3:
			if(sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
					intersects(EscaladorElementos.escalarRectanguloArriba(new Rectangle(Constantes.ANCHO_JUEGO - 170, 325, 100, 25))) == true) {
						cambiarEstadoActual(0);
			} else if(sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
					intersects(EscaladorElementos.escalarRectanguloArriba(new Rectangle(Constantes.ANCHO_JUEGO - 580, 130, 150, 150))) == true) {
						cambiarEstadoActual(2);
			} else if(sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
					intersects(EscaladorElementos.escalarRectanguloArriba(new Rectangle(Constantes.ANCHO_JUEGO - 400, 130, 150, 150))) == true) {
						cambiarEstadoActual(5);
			} 
			
			/*else if(sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
					intersects(EscaladorElementos.escalarRectanguloArriba(new Rectangle(Constantes.ANCHO_JUEGO - 220, 130, 150, 150))) == true) {
				cambiarEstadoActual(7);
			}*/
		break; 
		
		case 5:
			
			if (GestorControles.teclado.continuar==true) {
				cambiarEstadoActual(6);
			} else if(sd.getRaton().getClick() && sd.getRaton().getRectanguloPosicion().
			intersects(EscaladorElementos.escalarRectanguloArriba(new Rectangle(Constantes.ANCHO_JUEGO-620, 330, 85,25))) == true) {
				cambiarEstadoActual(3);
			} 
		break;
		case 6:
			if(GestorControles.teclado.continuar==false){
				cambiarEstadoActual(5);
			}
			break;
		
		case 7:
			break;
			
		}
			
	}
	
	public void dibujar(final Graphics g) {
		estadoActual.dibujar(g);
	}
	
	public void cambiarEstadoActual(final int nuevoEstado) {
		estadoActual = estados[nuevoEstado];
		estadoActualInt = nuevoEstado; 
	}
	
	//GETTERS
	public EstadoJuego getEstadoActual() {
		return estadoActual;
	}
	
	public int getEstadoActualInt() {
		return estadoActualInt; 
	}
	
}
