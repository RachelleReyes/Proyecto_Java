package principal.minijuego2.fracciones;

import java.util.Random;

public class FraccionImpropia extends Fraccion{

	public FraccionImpropia(int numerador, int denominador) {
		super(numerador, denominador);
		obtenerNumeros();
	}
	
	public int getNumerador() {
		return numerador;
	}

	public int getDenominador() {
		return denominador;
	}

	 public void setNumerador(int numerador) {
		 this.numerador = numerador;
	 }
	
	 public void setDenominador(int denominador) {
		 this.denominador = denominador;
	 }
	
	private void obtenerNumeros() {
		Random random = new Random();
		int minimo = 1;
		int maximo = 50;
		
		numerador = random.nextInt(maximo-minimo)+minimo;
	    do {
	    	denominador = random.nextInt(maximo-minimo)+minimo;
	    } while (denominador >= numerador);

		setNumerador(numerador);
		setDenominador(denominador);
		
	}
}
