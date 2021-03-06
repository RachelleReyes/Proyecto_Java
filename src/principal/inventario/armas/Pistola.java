package principal.inventario.armas;

import principal.Constantes;
import principal.entes.Jugador;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Pistola extends Arma {
    
    public Pistola(int id, String nombre, String descripcion, int ataqueMinimo, int ataqueMaximo, 
    		boolean automatica, boolean penetrante, double ataquesPorSegundo) {
        
    	super(id, nombre, descripcion, ataqueMinimo, ataqueMaximo, 
        		automatica, penetrante, ataquesPorSegundo, "/sonidos/disparo.wav");
    }

    public ArrayList<Rectangle> getAlcance(final Jugador jugador) {
    	final ArrayList<Rectangle> alcance = new ArrayList<>();

        final Rectangle alcance1 = new Rectangle();
        final int spritesAlcance = 3 ; 

        // DIRECCIONES 
        // 0 = ABAJO    
        // 1 = ARRIBA     
        // 2 = DERECHA   
        // 3 = IZQUIERDA 
        
        if (jugador.getDireccion() == 0 || jugador.getDireccion() == 1) {
            alcance1.width = 1;
            alcance1.height = spritesAlcance * Constantes.LADO_SPRITE;
            alcance1.x = Constantes.CENTRO_VENTANA_X;
            
            if (jugador.getDireccion() == 0) {
                alcance1.y = Constantes.CENTRO_VENTANA_Y - 9;
            } else {
                alcance1.y = Constantes.CENTRO_VENTANA_Y - 9 - alcance1.height;
            }
            
        } else {
            alcance1.width = spritesAlcance * Constantes.LADO_SPRITE;
            alcance1.height = 1;
            alcance1.y = Constantes.CENTRO_VENTANA_Y - 3;

            if (jugador.getDireccion() == 3) {
                alcance1.x = Constantes.CENTRO_VENTANA_X - alcance1.width;
            } else {

                alcance1.x = Constantes.CENTRO_VENTANA_X;
            }
        }

        alcance.add(alcance1);

        return alcance;
    }

}