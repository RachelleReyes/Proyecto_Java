package principal.minijuego3;

public class Operacion {

	int x, y;
	
	public Operacion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public int suma () {
		return x+y;
	}
	
	public int resta () {
		return x-y;
	}
	
	public int multiplicacion () {
		return x*y;
	}
	
}
