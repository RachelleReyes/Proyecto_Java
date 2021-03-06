package principal;

import java.awt.Color;
import java.awt.Font;

import principal.herramientas.CargadorRecursos;

public class Constantes {
	
	public static final int LADO_SPRITE = 32;
	public static final int LADO_TILE = 32;
	public static final int LADO_SPRITE2 = 60;
	
	public static int ANCHO_JUEGO = 640;//640;
	public static int ALTO_JUEGO = 360;//360;
	
	public static int ANCHO_PANTALLA_COMPLETA = 1280;//1280;
	public static int ALTO_PANTALLA_COMPLETA = 720;//720;
	
	public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) ANCHO_JUEGO;
	public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) ALTO_JUEGO;
	
	public static int CENTRO_VENTANA_X = ANCHO_JUEGO/ 2;
	public static int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;
	
	public static int MARGEN_X = ANCHO_JUEGO / 2 - LADO_SPRITE / 2;
	public static int MARGEN_Y = ALTO_JUEGO / 2 - LADO_SPRITE / 2;

	public static String RUTA_MAPA = "/mapas/03";
	public static String RUTA_ICONO_RATON = "/imagenes/iconos/iconoCursor.png";
	public static String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/2.png";
	public static String RUTA_PERSONAJE_PISTOLA = "/imagenes/hojasPersonajes/4.png";
	public static String RUTA_ICONO_VENTANA = "/imagenes/iconos/iconoVentana.png";
	public static String RUTA_LOGOTIPO = "/imagenes/iconos/logo.png";
	public static String RUTA_OBJETOS = "/imagenes/hojasObjetos/1.png";
	public static String RUTA_OBJETOS_ARMAS = "/imagenes/hojasObjetos/armas.png";
	public static String RUTA_ENEMIGOS = "/imagenes/hojasEnemigos/";
	
	public static Font FUENTE_PIXEL = CargadorRecursos.cargarFuente("/fuentes/px10.ttf");
	
	public static Color VERDE_CLARO = new Color(0, 255, 0);
	public static Color VERDE_OSCURO = new Color(0, 150, 0); 
}
