package principal.maquinaestado.estados.menujuego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.GestorPrincipal;
import principal.graficos.SuperficieDibujo;
import principal.herramientas.DibujoDebug;
import principal.herramientas.EscaladorElementos;
import principal.herramientas.MedidorString;
import principal.inventario.Objeto;
import principal.inventario.armas.Arma;

public class MenuInventario extends SeccionMenu {
	
	//private boolean menuInventarioActivo; 

	public MenuInventario(String nombreSeccion, Rectangle etiquetaMenu, EstructuraMenu em) {
		super(nombreSeccion, etiquetaMenu, em);

		int anchoBarra = 100; 
		
		//menuInventarioActivo = false; 
	}

	public void actualizar() {
		actualizarPosicionesMenu(); 
	}
	
	private void actualizarPosicionesMenu() {
		if (ElementosPrincipales.inventario.getConsumibles().isEmpty()) {
			return;
		}

		for (int i = 0; i < ElementosPrincipales.inventario.getConsumibles().size(); i++) {
			final Point puntoInicial = new Point(em.FONDO.x + 16, 10 + margenGeneral);

			final int lado = Constantes.LADO_SPRITE;

			int idActual = ElementosPrincipales.inventario.getConsumibles().get(i).getId();

			ElementosPrincipales.inventario.getObjeto(idActual).establecerPosicionMenu(
					new Rectangle(puntoInicial.x + i * (lado + margenGeneral), puntoInicial.y, lado, lado));
		}
	}

	public void dibujar(Graphics g, SuperficieDibujo sd, EstructuraMenu em) {
		//dibujarLimitePeso(g, em); 
 
		/*if(sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(barraPeso))) {
			dibujarTooltiPeso(g, sd, em);
		}
		 */
		dibujarElementosConsumibles(g,em); 
		dibujarPaginador(g, em);

	}

	
	private void dibujarElementosConsumibles(final Graphics g, EstructuraMenu em) {

		if (ElementosPrincipales.inventario.getConsumibles().isEmpty()) {
			return;
		}
		
		final Point puntoInicial = new Point(em.FONDO.x + 16, 10 + margenGeneral);

		final int lado = Constantes.LADO_SPRITE;

		for (int i = 0; i < ElementosPrincipales.inventario.getConsumibles().size(); i++) {

			int idActual = ElementosPrincipales.inventario.getConsumibles().get(i).getId();
			Objeto objetoActual = ElementosPrincipales.inventario.getObjeto(idActual);

			DibujoDebug.dibujarImagen(g, objetoActual.getSprite().getImagen(),
					objetoActual.getPosicionMenu().x, objetoActual.getPosicionMenu().y);

			g.setColor(Color.black);

			DibujoDebug.dibujarRectanguloRelleno(g, puntoInicial.x + i * (lado + margenGeneral) + lado - 12,
					puntoInicial.y + 32 - 8, 12, 8);

			g.setColor(Color.white);

			String texto = "";

			if (objetoActual.getCantidad() < 10) {
				texto = "0" + objetoActual.getCantidad();
			} else {
				texto = "" + objetoActual.getCantidad();
			}

			g.setFont(g.getFont().deriveFont(10f));
			DibujoDebug.dibujarString(g, texto,
					puntoInicial.x + i * (lado + margenGeneral) + lado - MedidorString.medirAnchoPixeles(g, texto),
					puntoInicial.y + 31);
		}
		g.setFont(g.getFont().deriveFont(12f));
	}
	
	
	private void dibujarPaginador(final Graphics g, EstructuraMenu em) {
		final int lado = 32;
		final int alto = 16;

		final Rectangle anterior = new Rectangle(em.FONDO.x + em.FONDO.width - margenGeneral * 2 - lado * 2 - 4,
				em.FONDO.y + em.FONDO.height - margenGeneral - alto, lado, alto);
		final Rectangle siguiente = new Rectangle(anterior.x + anterior.width + margenGeneral, anterior.y, lado, alto);

		g.setColor(Color.BLUE);

		g.fillRect(anterior.x, anterior.y, anterior.width, anterior.height);
		g.fillRect(siguiente.x, siguiente.y, siguiente.width, siguiente.height);
	}	
	
	/*public boolean menuInventarioActivo() {
		return menuInventarioActivo; 
	}
	
	public void getMenuInventarioActivo(final boolean menuInventarioActivo) {
		this.menuInventarioActivo = menuInventarioActivo; 
	}*/
}