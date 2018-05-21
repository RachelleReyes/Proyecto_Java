package principal.minijuego2.fracciones;

public class Fraccion {
	protected int numerador;
	protected int denominador;
	
	Fraccion(int numerador, int denominador) {
		this.numerador = numerador;
		this.denominador = denominador;
	}
	
	 public void setNumerador(int numerador) {
		 this.numerador = numerador;
	 }

	 public void setDenominador(int denominador) {
		 this.denominador = denominador;
	 }
	 
	 public int getNumerador() {
		 return numerador;
	 }
	 
	 public int getDenominador() {
		 return denominador;
	 }
}
