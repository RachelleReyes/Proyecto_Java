package principal.maquinaestado.estados.juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import principal.Constantes;
import principal.herramientas.DibujoDebug;

public class EstructuraMenuInventario {

	private final Rectangle areaInventario;
	private final Rectangle bordeAreaInventario;
	
	private final Color negroDesaturado;
	private final Color gris; 
	

	public EstructuraMenuInventario() {
		
		int altoMenu = 64;
		areaInventario = new Rectangle(0, Constantes.ALTO_JUEGO - altoMenu, Constantes.ANCHO_JUEGO, altoMenu);
		bordeAreaInventario = new Rectangle(areaInventario.x, areaInventario.y - 1, areaInventario.width, 1);

		negroDesaturado = new Color(23, 23, 23);
		gris = new Color(66, 73, 73);
		
	}

	public void actualizar() {

	}

	public void dibujar(final Graphics g) {
		dibujarAreaInventario(g);
		dibujarRanurasObjetos(g);
		DibujoDebug.dibujarTriangulo(g,6,22,22,160,149,171,Color.WHITE);
		DibujoDebug.dibujarTriangulo(g,620,635,620,149,160, 171,Color.WHITE);
	}
	
	private void dibujarAreaInventario(final Graphics g) {
		DibujoDebug.dibujarRectanguloRelleno(g, areaInventario, negroDesaturado);
		DibujoDebug.dibujarRectanguloTraslucidoRelleno(g, new Rectangle(0,0,25,Constantes.ALTO_JUEGO), Color.BLACK);
		DibujoDebug.dibujarRectanguloTraslucidoRelleno(g, new Rectangle(Constantes.ANCHO_JUEGO-25,0,Constantes.ANCHO_JUEGO,Constantes.ALTO_JUEGO), Color.BLACK);
	}
	
	private void dibujarRanurasObjetos(final Graphics g) {
		final int anchoRanura = 32;
		final int numeroRanuras = 10;
		final int espaciadoRanuras = 10;
		final int anchoTotal = anchoRanura * numeroRanuras + espaciadoRanuras * numeroRanuras + 20;
		final int xInicial = Constantes.ANCHO_JUEGO - anchoTotal;
		final int anchoRanuraYEspacio = anchoRanura + espaciadoRanuras;

		g.setColor(gris);

		for (int i = 0; i < numeroRanuras; i++) {
			int xActual = xInicial + anchoRanuraYEspacio * i;

			Rectangle ranura = new Rectangle(xActual, areaInventario.y + 4, anchoRanura, anchoRanura);
			DibujoDebug.dibujarRectanguloRelleno(g, ranura);
			DibujoDebug.dibujarString(g, "" + i, xActual + 13, areaInventario.y + 54);
		}
	}

}