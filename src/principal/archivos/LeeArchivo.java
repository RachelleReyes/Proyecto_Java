package archivos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class LeeArchivo {
	
	  File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      ArrayList<Datos> datos;
      String nombreArchivo;
	
      LeeArchivo(String nombreArchivo) {
    	  this.nombreArchivo = nombreArchivo;
    	  leer();
      }
      
	  public void leer() {
	      try {
	         archivo = new File ("/"+nombreArchivo);
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);
	         String linea;
	         datos = new ArrayList<Datos>();
	         while((linea=br.readLine())!=null) {
				 String [] separado = linea.split(", ");
	            //System.out.println(linea);
				if (separado.length == 2) {
	                    datos.add(new Datos(separado[0], Integer.parseInt(separado[1])));
	            }
			 }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	   }
	   
}
