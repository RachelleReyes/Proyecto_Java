package principal.maquinaestado.estados.menuPrincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElementos;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.estados.menuPrincipal.EstructuraMenu;

public class GestorMenuPrincipal implements EstadoJuego {
	
	private final SuperficieDibujo sd;
	
	private final EstructuraMenu estructuraMenu;
	
	private boolean gestorMenuPrincialActivo;
	
	public Rectangle botonJugar = new Rectangle(Constantes.ANCHO_JUEGO - 190, 125, 100, 25);
	public Rectangle botonMinijuegos = new Rectangle(Constantes.ANCHO_JUEGO  - 190 , 160, 100, 25);
	public Rectangle botonPuntajes = new Rectangle(Constantes.ANCHO_JUEGO - 190, 195, 100, 25);
	public Rectangle botonAyuda = new Rectangle(Constantes.ANCHO_JUEGO - 190, 230, 100, 25);
	public Rectangle botonSalir = new Rectangle(Constantes.ANCHO_JUEGO - 190, 265, 100, 25);
	
	
	public GestorMenuPrincipal(final SuperficieDibujo sd) {
		
		estructuraMenu = new EstructuraMenu(); 
		
		this.sd = sd; 
		
		gestorMenuPrincialActivo = false; 
	
	}

	public void actualizar() {
		
	}

	public void dibujar(Graphics g) {
		
		estructuraMenu.dibujar(g);

		Font fnt1 =  new Font("chiller",Font.BOLD, 14);
		g.setFont(fnt1);
		
		DibujoDebug.dibujarRectanguloContorno(g,botonJugar); 
		DibujoDebug.dibujarString(g, "JUGAR", botonJugar.x + 32, botonJugar.y + 17);
		DibujoDebug.dibujarRectanguloContorno(g,botonMinijuegos); 
		DibujoDebug.dibujarString(g, "MINIJUEGOS", botonMinijuegos.x + 16, botonMinijuegos.y + 17);
		DibujoDebug.dibujarRectanguloContorno(g, botonPuntajes);
		DibujoDebug.dibujarString(g, "PUNTAJES", botonPuntajes.x + 23, botonPuntajes.y + 17);
		DibujoDebug.dibujarRectanguloContorno(g, botonAyuda);
		DibujoDebug.dibujarString(g, "AYUDA", botonAyuda.x + 32, botonAyuda.y + 17);
		DibujoDebug.dibujarRectanguloContorno(g, botonSalir);
		DibujoDebug.dibujarString(g, "SALIR", botonSalir.x + 32, botonSalir.y + 17);
		
		if (sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(botonJugar))){
			DibujoDebug.dibujarRectanguloTraslucidoRelleno(g,botonJugar, Color.RED);
		}else if (sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(botonMinijuegos))){
			DibujoDebug.dibujarRectanguloTraslucidoRelleno(g,botonMinijuegos, Color.RED);
		}
		else if (sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(botonPuntajes))){
			DibujoDebug.dibujarRectanguloTraslucidoRelleno(g, botonPuntajes, Color.RED);
		}
		else if (sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(botonAyuda))){
			DibujoDebug.dibujarRectanguloTraslucidoRelleno(g, botonAyuda, Color.RED);
		} else if (sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(botonSalir))){
			DibujoDebug.dibujarRectanguloTraslucidoRelleno(g, botonSalir, Color.RED);
		}
		
	}
	
	public boolean getGestorMenuPrincialActivo() {
		return gestorMenuPrincialActivo;
	}
	
	public void cambiarGestorMenuPrincialActivo() {
		this.gestorMenuPrincialActivo = !gestorMenuPrincialActivo; 
	}
	

}
