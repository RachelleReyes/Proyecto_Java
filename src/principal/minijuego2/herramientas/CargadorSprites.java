package principal.minijuego2.herramientas;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CargadorSprites {

	private BufferedImage imagen;
	
	public BufferedImage cargarImagen(String ruta) throws IOException {
		imagen = ImageIO.read(getClass().getResource(ruta));
		return imagen;
	}
}
