package archivos;


import java.util.ArrayList;
 
public class ManipularArchivo {

      EscribeArchivo escrituraArchivo;
      LeeArchivo lecturaArchivo;
      ArrayList<Datos> datos;
      
      public void correr() {
		  	 ManipularArchivo archivo = new ManipularArchivo();
		  	archivo.escrituraArchivo = new EscribeArchivo("testing.txt", "Rachelle", 200, "NOMBRE:PUNTOS");
		  	archivo.lecturaArchivo = new LeeArchivo("testing.txt");
      }
     
}
   