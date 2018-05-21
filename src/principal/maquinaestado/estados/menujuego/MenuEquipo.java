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
import principal.inventario.armas.Desarmado;

public class MenuEquipo extends SeccionMenu {
	
	final Rectangle panelObjetos = new Rectangle(em.FONDO.x + margenGeneral, 10 + margenGeneral, 248,
			Constantes.ALTO_JUEGO - 10 - margenGeneral * 2);
	final Rectangle titularPanelObjetos = new Rectangle(panelObjetos.x, panelObjetos.y, panelObjetos.width, 24);
	
	final Rectangle panelEquipo = new Rectangle(panelObjetos.x + panelObjetos.width + margenGeneral, panelObjetos.y, 88,
			panelObjetos.height);
	final Rectangle titularPanelEquipo = new Rectangle(panelEquipo.x, panelEquipo.y, panelEquipo.width, 24);

	final Rectangle panelAtributos = new Rectangle(panelEquipo.x + panelEquipo.width + margenGeneral, panelObjetos.y,
			132, panelEquipo.height);
	final Rectangle titularPanelAtributos = new Rectangle(panelAtributos.x, panelAtributos.y, panelAtributos.width, 24);

	final Rectangle etiquetaArma = new Rectangle(titularPanelEquipo.x + margenGeneral,
			titularPanelEquipo.y + titularPanelEquipo.height + margenGeneral,
			titularPanelEquipo.width - margenGeneral * 2,
			margenGeneral * 2 + MedidorString.medirAltoPixeles(GestorPrincipal.sd.getGraphics(), "Arma"));
	final Rectangle contenedorArma = new Rectangle(etiquetaArma.x + 1, etiquetaArma.y + etiquetaArma.height,
			etiquetaArma.width - 2, Constantes.LADO_SPRITE);
	
	Objeto objetoSeleccionado = null; 

	public MenuEquipo(String nombreSeccion, Rectangle etiquetaMenu, EstructuraMenu em) {
		super(nombreSeccion, etiquetaMenu, em);
	}

	public void actualizar() {
		actualizarPosicionesMenu();
		actualizarSeleccionRaton();
		actualizarObjetoSeleccionado(); 
	}

	private void actualizarPosicionesMenu() {
		
		if (ElementosPrincipales.inventario.getArmas().isEmpty()) {
			return;
		}

		for (int i = 0; i < ElementosPrincipales.inventario.getArmas().size(); i++) {
			final Point puntoInicial = new Point(titularPanelObjetos.x + margenGeneral,
					titularPanelObjetos.y + titularPanelObjetos.height + margenGeneral);

			final int lado = Constantes.LADO_SPRITE;

			int idActual = ElementosPrincipales.inventario.getArmas().get(i).getId();

			ElementosPrincipales.inventario.getObjeto(idActual).establecerPosicionMenu(
					new Rectangle(puntoInicial.x + i * (lado + margenGeneral), puntoInicial.y, lado, lado));
		}
	}
	
	private void actualizarSeleccionRaton() {
		Rectangle posicionRaton = GestorPrincipal.sd.getRaton().getRectanguloPosicion();
		
		if (posicionRaton.intersects(EscaladorElementos.escalarRectanguloArriba(panelObjetos))) {
			if (ElementosPrincipales.inventario.getArmas().isEmpty()) {
				return;
			}
			
			for (Objeto objeto : ElementosPrincipales.inventario.getArmas()) {
				if (GestorPrincipal.sd.getRaton().getClick() && posicionRaton
						.intersects(EscaladorElementos.escalarRectanguloArriba(objeto.getPosicionMenu()))) {
					objetoSeleccionado = objeto;
				}
			}
			
		} else if (posicionRaton.intersects(EscaladorElementos.escalarRectanguloArriba(panelEquipo))) {
			if (objetoSeleccionado != null && objetoSeleccionado instanceof Arma && GestorPrincipal.sd.getRaton().getClick()
                    && posicionRaton.intersects(EscaladorElementos.escalarRectanguloArriba(contenedorArma))) {
                ElementosPrincipales.jugador.getAlmacenEquipo().cambiarArma1((Arma) objetoSeleccionado);
                objetoSeleccionado = null;
			}
			
		} else if (posicionRaton.intersects(EscaladorElementos.escalarRectanguloArriba(panelAtributos))) {
			
		}
	}

	private void actualizarObjetoSeleccionado() {
		if (objetoSeleccionado != null) {
			if (GestorPrincipal.sd.getRaton().getClick2()) {
				objetoSeleccionado = null;
				return;
			}

			Point posicionRaton = EscaladorElementos.escalarPuntoAbajo(GestorPrincipal.sd.getRaton().getPuntoPosicion());
			
			objetoSeleccionado.establecerPosicionFlotante(new Rectangle(posicionRaton.x, posicionRaton.y, 
					Constantes.LADO_SPRITE, Constantes.LADO_SPRITE));
		}
	}

	public void dibujar(Graphics g, SuperficieDibujo sd, EstructuraMenu em) {
		//dibujarLimitePeso(g, em); 
		
		dibujarPaneles(g);
			
		/*if(sd.getRaton().getRectanguloPosicion().intersects(EscaladorElementos.escalarRectanguloArriba(barraPeso))) {
			dibujarTooltiPeso(g, sd, em);
		}*/
	}
	
	private void dibujarPaneles(final Graphics g) {
		dibujarPanelObjetos(g, panelObjetos, titularPanelObjetos, "Equipables");
		dibujarPanelEquipo(g, panelEquipo, titularPanelEquipo, "Equipo");
		dibujarPanelAtributos(g, panelAtributos, titularPanelAtributos, "Atributos");
	}
	
	private void dibujarPanelObjetos(final Graphics g, final Rectangle panel, final Rectangle titularPanel,final String nombrePanel) {
		dibujarPanel(g, panel, titularPanel, nombrePanel);
		
		dibujarElementosEquipables(g, panel, titularPanel);
		
		//OBJETOS
		
	}
	
	private void dibujarElementosEquipables(final Graphics g, final Rectangle panelObjetos, final Rectangle titularPanel) {

		if (ElementosPrincipales.inventario.getArmas().isEmpty()) {
			return;
		}
		
		final Point puntoInicial = new Point(titularPanel.x + margenGeneral, titularPanel.y + titularPanel.height + margenGeneral);
	
		final int lado = Constantes.LADO_SPRITE;
		
		for (int i = 0; i < ElementosPrincipales.inventario.getArmas().size(); i++) {
			
			int idActual = ElementosPrincipales.inventario.getArmas().get(i).getId();
			Objeto objetoActual = ElementosPrincipales.inventario.getObjeto(idActual);
			
			DibujoDebug.dibujarImagen(g, objetoActual.getSprite().getImagen(),objetoActual.getPosicionMenu().x, 
					objetoActual.getPosicionMenu().y);
			
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
			DibujoDebug.dibujarString(g, texto, puntoInicial.x + i * (lado + margenGeneral) + lado 
					- MedidorString.medirAnchoPixeles(g, texto), puntoInicial.y + 31);
			}
		
			g.setFont(g.getFont().deriveFont(12f));
			
			if (objetoSeleccionado != null) {
				DibujoDebug.dibujarImagen(g, objetoSeleccionado.getSprite().getImagen(), new Point(
						objetoSeleccionado.getPosicionFlotante().x, objetoSeleccionado.getPosicionFlotante().y));
			}
			
	}
	
	private void dibujarPanelEquipo(final Graphics g, final Rectangle panel, final Rectangle titularPanel,final String nombrePanel) {
		dibujarPanel(g, panel, titularPanel, nombrePanel);
		
		//RANURAS EQUIPABLES
		g.setColor(Constantes.VERDE_OSCURO);
		
		DibujoDebug.dibujarRectanguloRelleno(g, etiquetaArma);
		DibujoDebug.dibujarRectanguloContorno(g, contenedorArma);
		
		g.setColor(Color.WHITE);
		DibujoDebug.dibujarString(g, "Arma",
				new Point(etiquetaArma.x + etiquetaArma.width / 2 - MedidorString.medirAnchoPixeles(g, "Arma") / 2,
						  etiquetaArma.y + etiquetaArma.height / 2 + MedidorString.medirAltoPixeles(g, "Arma") / 2));
				
		//SI EL JUGADOR NO ESTA DESARMADO
		if (!(ElementosPrincipales.jugador.getAlmacenEquipo().getArma1() instanceof Desarmado)) {
            Point coordenadaImagen = new Point(contenedorArma.x + contenedorArma.width / 2 - Constantes.LADO_SPRITE / 2,
                    contenedorArma.y);

            DibujoDebug.dibujarImagen(g, ElementosPrincipales.jugador.getAlmacenEquipo().getArma1().getSprite().getImagen(),
                    coordenadaImagen);
        }
		
	}
	
	private void dibujarPanelAtributos(final Graphics g, final Rectangle panel, final Rectangle titularPanel,final String nombrePanel) {
		dibujarPanel(g, panel, titularPanel, nombrePanel);
	}
	
	private void dibujarPanel(final Graphics g, final Rectangle panel, final Rectangle titularPanel,final String nombrePanel) {
		
		g.setColor(Color.GRAY);
		DibujoDebug.dibujarRectanguloContorno(g, panel);
		DibujoDebug.dibujarRectanguloRelleno(g, titularPanel);
		
		g.setColor(Color.WHITE);
		DibujoDebug.dibujarString(g, nombrePanel , new Point(
				titularPanel.x + titularPanel.width /2 - MedidorString.medirAnchoPixeles(g, nombrePanel)/2,
				titularPanel.y + titularPanel.height - MedidorString.medirAltoPixeles(g, nombrePanel)/2 - 4));
	}

	public void eliminarObjetoSeleccionado() {
        objetoSeleccionado = null;
    }
	
}