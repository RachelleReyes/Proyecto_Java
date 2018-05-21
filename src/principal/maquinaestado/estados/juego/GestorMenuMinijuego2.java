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
import principal.maquinaestado.estados.menuPrincipal.GestorMenuMinijuegos;
import principal.minijuego2.main.GestorPrincipal2;

public class GestorMenuMinijuego2 implements EstadoJuego {
	
	private final SuperficieDibujo sd;
	
	private BufferedImage minijuego2_fondo = null;
//	private final EstructuraMenu estructuraMenu;
	private boolean gestorMenuPrincipalActivo;
	private int estado =0;
	Graphics g;
	public Rectangle botonRegresar = new Rectangle(Constantes.ANCHO_JUEGO-620, 330, 85,25);
	
	public GestorMenuMinijuego2 (final SuperficieDibujo sd) {
		this.sd = sd; 
		gestorMenuPrincipalActivo = false; 
	
	}

	public void actualizar() {
			
	}
	
	public void dibujar(Graphics g) {

		minijuego2_fondo = CargadorRecursos.cargarImagenCompatibleOpaca("/instruccion2.png");
		DibujoDebug.dibujarImagen(g, minijuego2_fondo , 0,0);
	
		Font fnt1 =  new Font("chiller", Font.BOLD,16);
		g.setFont(fnt1);
		g.setColor(Color.white);
	
		DibujoDebug.dibujarRectanguloContorno(g,botonRegresar); 
		DibujoDebug.dibujarString(g, "REGRESAR", botonRegresar.x + 10,botonRegresar.y + 17);
		
		if (sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(botonRegresar))){
			DibujoDebug.dibujarRectanguloTraslucidoRelleno(g,botonRegresar, Color.RED);
		}
		
	}
}
