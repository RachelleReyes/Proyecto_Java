package principal.minijuego2.herramientas;

import principal.minijuego2.entidades.EntidadA;
import principal.minijuego2.entidades.EntidadB;

public class Colisiones {
	
	public static boolean Colision (EntidadA enta, EntidadB entb) {
		
		if(enta.getBounds().intersects(entb.getBounds())) {
			return true;
		}
		return false;
	}
	public static boolean Colision (EntidadB entb, EntidadA enta) {
	
		if(entb.getBounds().intersects(enta.getBounds())) {
			return true;
		}
		return false;
	}
	
}
