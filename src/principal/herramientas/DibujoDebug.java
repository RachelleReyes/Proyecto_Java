package principal.herramientas;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class DibujoDebug {

	//WRAPPER. CREAR METODOS PARA ENVOLVER LA CLASE DE GRAFICOS
	//METODOS ABREVIADOS QUE JUNTAN OTROS METODOS
	
	private static int objetosDibujados = 0;

	//IMAGENES
	public static void dibujarImagen(final Graphics g, final BufferedImage img, final int x, final int y) {
		objetosDibujados++;
		g.drawImage(img, x, y, null);
	}

	public static void dibujarImagen(final Graphics g, final BufferedImage img, final Point p) {
		objetosDibujados++;
		g.drawImage(img, p.x, p.y, null);
	}

	//TEXTO
	public static void dibujarString(final Graphics g, final String s, final int x, final int y) {
		objetosDibujados++;
		g.drawString(s, x, y);
	}

	public static void dibujarString(final Graphics g, final String s, final Point p) {
		objetosDibujados++;
		g.drawString(s, p.x, p.y);
	}

	public static void dibujarString(final Graphics g, final String s, final int x, final int y, final Color c) {
		objetosDibujados++;
		g.setColor(c);
		g.drawString(s, x, y);
	}

	public static void dibujarString(final Graphics g, final String s, final Point p, final Color c) {
		objetosDibujados++;
		g.setColor(c);
		g.drawString(s, p.x, p.y);
	}

	//RECTANGULOS
	public static void dibujarRectanguloRelleno(final Graphics g, final int x, final int y, final int ancho, final int alto) {
		objetosDibujados++;
		g.fillRect(x, y, ancho, alto);
	}

	public static void dibujarRectanguloRelleno(final Graphics g, final Rectangle r) {
		objetosDibujados++;
		g.fillRect(r.x, r.y, r.width, r.height);
	}

	public static void dibujarRectanguloRelleno(final Graphics g, final int x, final int y, final int ancho, final int alto, final Color c) {
		objetosDibujados++;
		g.setColor(c);
		g.fillRect(x, y, ancho, alto);
	}

	public static void dibujarRectanguloRelleno(final Graphics g, final Rectangle r, final Color c) {
		objetosDibujados++;
		g.setColor(c);
		g.fillRect(r.x, r.y, r.width, r.height);
	}

	public static void dibujarRectanguloTraslucidoRelleno(final Graphics g, final Rectangle r, final Color c) {
		Graphics2D g2d= (Graphics2D) g; 

		AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
		g2d.setComposite(transparency);
		g2d.setColor(c);
		g2d.fill(r);
	}

	public static void dibujarRectanguloContorno(final Graphics g, final int x, final int y, final int ancho, final int alto) {
		objetosDibujados++;
		g.drawRect(x, y, ancho, alto);
	}

	public static void dibujarRectanguloContorno(final Graphics g, final Rectangle r) {
		objetosDibujados++;
		g.drawRect(r.x, r.y, r.width, r.height);
	}

	public static void dibujarRectanguloContorno(final Graphics g, final int x, final int y, final int ancho, final int alto, final Color c) {
		objetosDibujados++;
		g.setColor(c);
		g.drawRect(x, y, ancho, alto);
	}

	public static void dibujarRectanguloContorno(final Graphics g, final Rectangle r, final Color c) {
		objetosDibujados++;
		g.setColor(c);
		g.drawRect(r.x, r.y, r.width, r.height);
	}
	
	public static void dibujarTriangulo(final Graphics g, final int x1, final int x2, final int x3, final int y1,
			final int y2, final int y3,final Color c) {
		g.setColor(c); 
		int [] vx = {x1, x2, x3};
        int [] vy = {y1, y2, y3};
	    g.fillPolygon (vx, vy, 3);
	}

	//GETTERS Y SETTERS
	public static int getContadorObjetos() {
		return objetosDibujados;
	}

	public static void reiniciarContadorObjetos() {
		objetosDibujados = 0;
	}
}