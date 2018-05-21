package archivos;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class EscribeArchivo {
	
	private String nombreArchivo, nombre, encabezado;
	private int puntos;
	
	EscribeArchivo(String nombreArchivo, String nombre, int puntos, String encabezado){
		this.nombreArchivo = nombreArchivo;
		this.nombre = nombre;
		this.puntos = puntos; 
		this.encabezado = encabezado; 
		escribir();
	}
		   
	public void escribir() {
		File file = new File("/"+nombreArchivo);
		boolean doesFileExist = file.exists();	
			
		FileWriter archivo = null;
		PrintWriter pw = null;
		
		try {
			archivo = new FileWriter(file, true);
			pw = new PrintWriter(archivo);
			
			if (doesFileExist) {
				pw.append(nombre+ ", "+ puntos + "\n");
			}
			else {	
				pw.println(encabezado);
				pw.append(nombre+ ", "+ puntos + "\n");
                         }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
 				if (null != archivo)
				archivo.close();
				} catch (Exception e2) {
						e2.printStackTrace();
					}
			}
		}
	}
