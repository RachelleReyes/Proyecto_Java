package principal.minijuego2.herramientas;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import principal.herramientas.DibujoDebug;

public class Animacion {

	private int velocidad;
	private int frames;
	private int indice = 0;
	private int cantidad = 0;
	
	private BufferedImage img1;
	private BufferedImage img2;
	private BufferedImage img3;
	private BufferedImage currentImg;
	
	
	//3 frame
	public Animacion(int velocidad, BufferedImage img1, BufferedImage img2, BufferedImage img3){
		this.velocidad = velocidad;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		frames = 3;
	}
	//2 frame
	public Animacion(int velocidad, BufferedImage img1, BufferedImage img2){
		this.velocidad = velocidad;
		this.img1 = img1;
		this.img2 = img2;
		frames = 2;
	}
	
	public Animacion(int velocidad, BufferedImage img1){
		this.velocidad = velocidad;
		this.img1 = img1;
		frames = 1;
	}
	
	public void runAnimacion(){
		indice++;
		if(indice > velocidad){
			indice = 0;
			nextFrame();
		}	
	}
	
	public void nextFrame(){
		
		//switch statement
		switch(frames){
		case 1:
			if(cantidad == 0)
				currentImg = img1;
			
			cantidad++;
			
			if(cantidad > frames)
				cantidad = 0;
			
			break;
		case 2:
			if(cantidad == 0)
				currentImg = img1;
			if(cantidad == 1)
				currentImg = img2;

			cantidad++;
			
			if(cantidad > frames)
				cantidad = 0;
			
			break;
		case 3:
			if(cantidad == 0)
				currentImg = img1;
			if(cantidad == 1)
				currentImg = img2;
			if(cantidad == 2)
				currentImg = img3;
			
			cantidad++;
			
			if(cantidad > frames)
				cantidad = 0;
			
			break;
		}
	}
	
	public void dibujarAnimacion(Graphics g, double x, double y, int offset){
		//g.drawImage(currentImg, (int)x - offset, (int)y, null);
		DibujoDebug.dibujarImagen(g, currentImg,(int)x-offset,(int)y);
	}
	
	public void setCantidad(int cantidad){
		this.cantidad = cantidad;
	}
	public int getCantidad(){
		return cantidad;
	}
	public int getVelocidad(){
		return velocidad;
	}
	public void setVelocidad(int velocidad){
		this.velocidad = velocidad;
	}
	
}