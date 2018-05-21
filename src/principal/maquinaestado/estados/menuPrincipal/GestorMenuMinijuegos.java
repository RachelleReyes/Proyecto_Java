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

public class GestorMenuMinijuegos implements EstadoJuego{
	
	private final SuperficieDibujo sd;
	
	private final EstructuraMenu estructuraMenu;
	
	public final Rectangle Minijuego1 = new Rectangle(Constantes.ANCHO_JUEGO - 580, 130, 150, 150);
	public final Rectangle Minijuego2 = new Rectangle(Constantes.ANCHO_JUEGO - 400, 130, 150, 150);
	public final Rectangle Minijuego3 = new Rectangle(Constantes.ANCHO_JUEGO - 220, 130, 150, 150);
	public final Rectangle botonRegresar = new Rectangle(Constantes.ANCHO_JUEGO - 170, 325, 100, 25);
	
	public GestorMenuMinijuegos(final SuperficieDibujo sd) {
		
		estructuraMenu = new EstructuraMenu(); 
		
		this.sd = sd; 
	}

	public void actualizar() {
		
	}

	public void dibujar(Graphics g) {
		estructuraMenu.dibujar(g);
		
		Font fnt1 =  new Font("chiller",Font.BOLD, 14);
		g.setFont(fnt1);
		
		DibujoDebug.dibujarRectanguloContorno(g,Minijuego1); 
		DibujoDebug.dibujarString(g, "MINIJUEGO1", Minijuego1.x + 32, Minijuego1.y + 17);
		DibujoDebug.dibujarRectanguloContorno(g,Minijuego2); 
		DibujoDebug.dibujarString(g, "MINIJUEGO2", Minijuego2.x + 16, Minijuego2.y + 17);
		DibujoDebug.dibujarRectanguloContorno(g, Minijuego3);
		DibujoDebug.dibujarString(g, "MINIJUEGO3", Minijuego3.x + 23, Minijuego3.y + 17);
		DibujoDebug.dibujarRectanguloContorno(g, botonRegresar);
		DibujoDebug.dibujarString(g, "REGRESAR", botonRegresar.x + 22, botonRegresar.y + 17);
		
		if (sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(botonRegresar))){
			DibujoDebug.dibujarRectanguloTraslucidoRelleno(g,botonRegresar, Color.RED);
		}

	}

}
