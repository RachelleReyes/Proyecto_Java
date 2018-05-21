package principal.maquinaestado.estados.menuPrincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.herramientas.CargadorRecursos;
import principal.herramientas.DibujoDebug;

public class EstructuraMenu {
	
	private BufferedImage menuPrincipal_fondo;
	
	public EstructuraMenu() {

		menuPrincipal_fondo = menuPrincipal_fondo = CargadorRecursos.cargarImagenCompatibleOpaca("/casa.jpg");
		
	}
	
	public void dibujar(Graphics g) {
		
		g.drawImage(menuPrincipal_fondo, 0, 0, null);
		DibujoDebug.dibujarImagen(g, menuPrincipal_fondo, 0,0);
		
		Font fnt0 =  new Font("chiller", Font.PLAIN, 50);
		g.setFont(fnt0);
		
		g.setColor(Color.white);
		DibujoDebug.dibujarString(g, "OPERACIÓN ESCAPE", 150, 80);
		
		//MOMENTANEAS / BORRAR DESPUES 
		fnt0 =  new Font("chiller", Font.PLAIN, 14);
		g.setFont(fnt0);
		
	}

}
