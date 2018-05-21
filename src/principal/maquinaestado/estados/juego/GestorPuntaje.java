package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import principal.Constantes;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElementos;
import principal.maquinaestado.EstadoJuego;
import principal.maquinaestado.estados.menuPrincipal.EstructuraMenu;

public class GestorPuntaje implements EstadoJuego{
	
	private final SuperficieDibujo sd;
	
	private final EstructuraMenu estructuraMenu;
	
	public final Rectangle botonRegresar = new Rectangle(Constantes.ANCHO_JUEGO - 170, 325, 100, 25);
	
	public GestorPuntaje(final SuperficieDibujo sd) {
		
		estructuraMenu = new EstructuraMenu(); 
		
		this.sd = sd; 
	}

	public void actualizar() {
		
	}

	public void dibujar(Graphics g) {
		estructuraMenu.dibujar(g);
		
		Font fnt1 =  new Font("chiller",Font.BOLD, 14);
		g.setFont(fnt1);
		
		DibujoDebug.dibujarRectanguloContorno(g, botonRegresar);
		DibujoDebug.dibujarString(g, "REGRESAR", botonRegresar.x + 22, botonRegresar.y + 17);
		
		if (sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(botonRegresar))){
			DibujoDebug.dibujarRectanguloTraslucidoRelleno(g,botonRegresar, Color.RED);
		}

	}

}
