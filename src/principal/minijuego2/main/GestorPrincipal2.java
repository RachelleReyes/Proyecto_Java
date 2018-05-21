package principal.minijuego2.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import principal.Constantes;
import principal.maquinaestado.EstadoJuego;
import principal.minijuego2.elementos.Jugador;
import principal.minijuego2.entidades.EntidadA;
import principal.minijuego2.entidades.EntidadB;
import principal.minijuego2.herramientas.CargadorSprites;
import principal.minijuego2.herramientas.Controlador;
import principal.sonido.Sonido;

public class GestorPrincipal2 implements EstadoJuego{

	private BufferedImage hojaSprite = null;
	private BufferedImage fondo2 = null;
	
	public String tipo_fraccion;
	private int randomFraccion;
	Random r = new Random();
	
	public int cantidad_enemigo =6;
	private int enemigosVivos;
	private int enemigo_disparado =0;
	private int record = 0;
	
	public Sonido puntos = new Sonido("/puntos.wav");
	public Sonido perdervida = new Sonido("/perdervida.wav");
	public Sonido equivocada = new Sonido("/equivocada.wav");
	
	public Jugador p;
	private Controlador c;
	public ArrayList<EntidadA> ea;
	public ArrayList<EntidadB> eb;
	
	public static int VIDA =150;
	public static int ENEMIGOS =220;
	public static int PUNTAJE = 0;
	
	Graphics g;

	public GestorPrincipal2() {
			inicializar();
	}
	
	public void inicializar() {
		
		CargadorSprites cargador = new CargadorSprites();
		try {
			hojaSprite = cargador.cargarImagen("/principal.png");

		}catch(IOException e) {
			e.printStackTrace();
		}
		
		randomFraccion= r.nextInt(3);
		
		c = new Controlador(this, randomFraccion);
		p = new Jugador(Constantes.ANCHO_JUEGO, Constantes.ALTO_JUEGO, this, c);
		
		ea = c.getEntidadA();
		eb = c.getEntidadB();
		
		c.crearEnemigo();
		//cargarPuntaje();
	}
	
	public void actualizar() {
			p.actualizar();
			c.actualizar();
		
		if(enemigosVivos<=5 && VIDA>0) {
			enemigo_disparado = 0;
			c.crearEnemigo();
		}
	}
	
	public void dibujar(Graphics g) {
		//fondo2 = CargadorRecursos.cargarImagenCompatibleOpaca("/fondo3b.png");
		//DibujoDebug.dibujarImagen(g, fondo2 , 0,0);
		
		Font fnt0 =  new Font("chiller", Font.BOLD,30);
		g.setFont(fnt0);
	//	g.setColor(Color.gray);
		
	//	g.fillRect( Constantes.ANCHO_JUEGO-585, 15, Constantes.ANCHO_JUEGO-100,28);
		g.setColor(Color.white);
		switch(randomFraccion) {
			
			case 0: g.drawString("ELIMINA TODAS LAS FRACCIONES PROPIAS", Constantes.ANCHO_JUEGO-575, 40);
			tipo_fraccion = "PROPIA";
			break;
			case 1: g.drawString("ELIMINA TODAS LAS FRACCIONES IMPROPIAS",Constantes.ANCHO_JUEGO-585, 40);
			tipo_fraccion = "IMPROPIA";
			break;
			case 2: g.drawString("ELIMINA TODAS LAS FRACCIONES MIXTAS", Constantes.ANCHO_JUEGO-550, 40);
			tipo_fraccion = "MIXTA";
			break;
		}
		
		p.dibujar(g);
		c.dibujar(g);
		
		Font fnt1 =  new Font("arial", Font.BOLD,14);
		g.setFont(fnt1);
		g.setColor(Color.white);
		
		//VIDA DEL JUGADOR
		g.drawString("VIDA: ", 100, 345);
		g.setColor(Color.red);
		g.fillRect(150,330,150,20);
		
		g.setColor(Color.green);
		g.fillRect(150,330,VIDA,20);
		
		g.setColor(Color.white);
		g.drawRect(150,330,150,20);
		
		
		g.setColor(Color.white);
		g.drawString("PUNTAJE: " + PUNTAJE, 360, 345);
		
		if(VIDA<= 0 || PUNTAJE>=250) {
			juegoTerminado(g);
			
			record = PUNTAJE + VIDA;
			//guardarPuntaje();
			PUNTAJE = 0;
			VIDA =0;
		}
	}
	
	private void juegoTerminado(Graphics g){
		
		
		g.setColor(Color.black);
		g.fillRect(0,0,Constantes.ANCHO_JUEGO*2+100,Constantes.ALTO_JUEGO*2+100);
		
		Font fnt1 =  new Font("chiller", Font.BOLD,20);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.drawString("JUEGO TERMINADO, PRESIONA ENTER PARA CONTINUAR", Constantes.ANCHO_JUEGO/2-215, Constantes.ALTO_JUEGO/2);
		
	}
	/*
	private void guardarPuntaje() {
	    BufferedWriter bw = null;
	    try {
	        bw = new BufferedWriter(new FileWriter(System.getProperty("Users.dir") + "/puntajes.txt", false)); //append - set to false
	        bw.write("Minijuego2 termimnado!!!\n");
	        bw.write("" + record);
	        bw.flush();
	        bw.close();
	    } catch (IOException e) {
	       // JOptionPane.showMessageDialog(this, e.getMessage(), "Error al guardar", JOptionPane.ERROR_MESSAGE);
	    }
}

	private void cargarPuntaje(){
    BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(System.getProperty("Users.dir") + "/puntajes.txt"));
            line = br.readLine();
            br.close();
        } catch (IOException e) {
            line = "";
        }

        if(line != ""){
            record = Integer.parseInt(line);
            System.out.println("Record: " + record);
            //recordLabel.setText("Record: " + record);
        }
 }*/
	
	
	public BufferedImage gethojaSprite() {
		return hojaSprite;
	}
	
	
	public int getEnemigos_cantidad() {
		return cantidad_enemigo;
	}

	public void setEnemigos_cantidad(int cantidad_enemigo) {
		this.cantidad_enemigo = cantidad_enemigo;
	}

	public int getEnemigos_disparados() {
		return enemigo_disparado;
	}

	public void setEnemigos_disparados(int enemigo_disparado) {
		this.enemigo_disparado = enemigo_disparado;
	}
	
	public int getEnemigosVivos() {
		return enemigosVivos;
	}

	public void setEnemigosVivos(int enemigosVivos) {
		this.enemigosVivos = enemigosVivos;
	}
}