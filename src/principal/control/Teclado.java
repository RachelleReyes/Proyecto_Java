package principal.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

	//ATRIBUTOS
	public Tecla arriba = new Tecla();
	public Tecla abajo = new Tecla();
	public Tecla izquierda = new Tecla();
	public Tecla derecha = new Tecla();
	
	public int numero =0;
	public boolean atacando = false;
	public boolean recogiendo = false;
	public boolean corriendo = false;
	public boolean debug = false;
	public boolean inventarioActivo = false;
	public boolean continuar = false;
	public boolean ingresar = false;
	
	
	public Tecla siguiente = new Tecla();
	//public boolean siguiente = false;
	public Tecla anterior = new Tecla();
	//public boolean anterior = false;

	//TECLAS PULSADAS
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			arriba.teclaPulsada();
			break;
		case KeyEvent.VK_S:
			abajo.teclaPulsada();
			break;
		case KeyEvent.VK_A:
			izquierda.teclaPulsada();
			break;
		case KeyEvent.VK_D:
			derecha.teclaPulsada();
			break;
		case KeyEvent.VK_E:
			recogiendo = true;
			break;
		case KeyEvent.VK_SHIFT:
			corriendo = true;
			break;
		case KeyEvent.VK_F1:
			debug = !debug;
			break;
		/*case KeyEvent.VK_I:
			inventarioActivo = !inventarioActivo;
			break;*/
		case KeyEvent.VK_ENTER:
			continuar= !continuar;
			ingresar = true;
			
		break;
		case KeyEvent.VK_SPACE:
			atacando = true;
			break;
		case KeyEvent.VK_0:
			numero = 0;
			break;
		case KeyEvent.VK_1:
			numero = 1;
			break;
		case KeyEvent.VK_2:
			numero = 2;
			break;
		case KeyEvent.VK_3:
			numero = 3;
			break;
		case KeyEvent.VK_4:
			numero = 4;
			break;
		case KeyEvent.VK_5:
			numero = 5;
			break;
		case KeyEvent.VK_6:
			numero = 6;
			break;
		case KeyEvent.VK_7:
			numero = 7;
			break;
		case KeyEvent.VK_8:
			numero = 8;
			break;
		case KeyEvent.VK_9:
			numero = 9;
			break;
		case KeyEvent.VK_RIGHT:
			siguiente.teclaPulsada();
			break; 
		case KeyEvent.VK_LEFT:
			anterior.teclaPulsada();
			break; 
			
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		break;
		}
	}

	//TECLAS LIBERADAS
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			arriba.teclaLiberada();
			break;
		case KeyEvent.VK_S:
			abajo.teclaLiberada();
			break;
		case KeyEvent.VK_A:
			izquierda.teclaLiberada();
			break;
		case KeyEvent.VK_D:
			derecha.teclaLiberada();
			break;
		case KeyEvent.VK_E:
			recogiendo = false;
			break;
		case KeyEvent.VK_SHIFT:
			corriendo = false;
			break;
		case KeyEvent.VK_SPACE:
			atacando = false;
			break;
		case KeyEvent.VK_RIGHT:
			siguiente.teclaLiberada();
			break;
		case KeyEvent.VK_LEFT:
			anterior.teclaLiberada();
			break;
		/*case KeyEvent.VK_ENTER:
			atacando = false;
			break;
			*/
		case KeyEvent.VK_ENTER:
			ingresar = false;
			
		break;
		}
	}
/*
	public  void obtenerNumeroKeyEvent(KeyEvent e) {
        if (e.getKeyCode() >= 96 && e.getKeyCode() <= 105) {
        	numero =  e.getKeyCode() - 96;
        } else if (e.getKeyCode() >= 48 && e.getKeyCode() <= 57) {
        	numero = e.getKeyCode() - 48;
        }
    }
	*/
	public void keyTyped(KeyEvent e) {
	}
}
