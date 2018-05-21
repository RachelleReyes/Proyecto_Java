package principal.minijuego2.fracciones;

import java.util.Random;

public class FraccionMixta extends Fraccion{
	int numeroEntero;
	
	public FraccionMixta(int numerador, int denominador, int numeroEntero) {
		super(numerador, denominador);
		this.numeroEntero = numeroEntero;
		obtenerNumeros();
	}
	
	public int getNumerador() {
		return numerador;
	}

	public int getDenominador() {
		return denominador;
	}

	public int getNumeroEntero() {
		return numeroEntero;
	}
	 public void setNumerador(int numerador) {
		 this.numerador = numerador;
	 }
	
	 public void setDenominador(int denominador) {
		 this.denominador = denominador;
	 }
	 
	 public void setNumeroEntero(int numeroEntero) {
		 this.numeroEntero = numeroEntero;
	 }
	
	private void obtenerNumeros() {
		Random random = new Random();
		int minimo = 1;
		int maximo = 50;
		
		numerador = random.nextInt(maximo-minimo)+minimo;
	    do {
	    	denominador = random.nextInt(maximo-minimo)+minimo;
	    } while (denominador <= numerador);
		
	    setNumerador(numerador);
	  	setDenominador(denominador);
	 
		numeroEntero = random.nextInt(9)+1;
		setNumeroEntero(numeroEntero);
	}
}
