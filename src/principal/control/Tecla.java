package principal.control;

public class Tecla {
	
	private boolean pulsada = false;
	private long ultimaPulsacion = System.nanoTime();

	public void teclaPulsada() {
		pulsada = true;
		ultimaPulsacion = System.nanoTime();
	}

	//SOLTAR TECLA
	public void teclaLiberada() {
		pulsada = false;
	}

	public boolean estaPulsada() {
		return pulsada;
	}

	public long getUltimaPulsacion() {
		return ultimaPulsacion;
	}

}
