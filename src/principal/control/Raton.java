package principal.control;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import principal.graficos.SuperficieDibujo;
import principal.herramientas.CargadorRecursos;
import principal.herramientas.DatosDebug;

public class Raton extends MouseAdapter{

	private final Cursor cursor;
	private Point posicion; //GUARDA COORDENADAS. X Y Y
	private boolean click;
	private boolean click2;

	public final int ladoCursor;
	
	public Raton(final SuperficieDibujo sd) {
		Toolkit configuracion = Toolkit.getDefaultToolkit();

		final BufferedImage iconoCargado = CargadorRecursos.cargarImagenCompatibleTranslucida("/imagenes/iconos/iconoCursor.png");

		ladoCursor = iconoCargado.getHeight();

		BufferedImage icono = iconoCargado;

		Point punta = new Point(0, 0);

		this.cursor = configuracion.createCustomCursor(icono, punta, "Cursor por defecto");

		posicion = new Point();
		actualizarPosicion(sd);

		click = false;
		click2 = false;
	}
	
	public void actualizar(final SuperficieDibujo sd) {
		actualizarPosicion(sd);
	}
	
	public void dibujar(final Graphics g) {
		DatosDebug.enviarDato("RX: " + posicion.getX());
		DatosDebug.enviarDato("RY: " + posicion.getY());
	}
	
	private void actualizarPosicion(final SuperficieDibujo sd) {
		final Point posicionInicial = MouseInfo.getPointerInfo().getLocation();

		SwingUtilities.convertPointFromScreen(posicionInicial, sd);

		posicion.setLocation(posicionInicial.getX(), posicionInicial.getY());
	}
	
	//GETTERS
	public Cursor getCursor() {
		return this.cursor;
	}
	
	public Point getPuntoPosicion() {
		return posicion;
	}
	
	public Rectangle getRectanguloPosicion() {
		final Rectangle area = new Rectangle(posicion.x, posicion.y, 1, 1);

		return area;
	}
	
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			click = true;
		} else if (SwingUtilities.isRightMouseButton(e)) {
			click2 = true;
		}
	}

	public void mouseReleased(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			click = false;
		} else if (SwingUtilities.isRightMouseButton(e)) {
			click2 = false;
		}
	}
	
	public boolean getClick() {
		return click;
	}

	public boolean getClick2() {
		return click2;
	}
}