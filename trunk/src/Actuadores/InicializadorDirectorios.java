package Actuadores;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.JOptionPane;
public class InicializadorDirectorios {
	
	public InicializadorDirectorios(){
		
	}
	/**
	 * // ejemplo ruta x:\\devtroce\\java
	 * @param ruta ej x:\\devtroce\\java
	 * @return true si se creo o false si no se pudo crear
	 */
	public boolean crearCarpetas(String ruta){
		File folder = new File(ruta);
		if (!folder.exists()) { 

			folder.mkdirs(); 
			return true;
		}
		else{

			return false;
		}
		

		
	}
	
	public boolean esDirectorio(String ruta){
		File folder = new File(ruta);
	
		if (folder.isDirectory()) 
		{ 
			return true;

	    }
		return false;

		}
	
	public boolean esFichero(String ruta){
		File folder = new File(ruta);	
		
		if (folder.isFile()) {
			return true;
		
	    }else{
		return false;
	    }
	}
	
	public boolean existeFichero(String ruta){
		File folder = new File(ruta);	
		
		if (folder.exists()) {
			return true;
		
	    }else{
		return false;
	    }
	}
	public boolean esDirectorioVacio(String ruta){
		File folder = new File(ruta);
		
		
		 File[] ficheros = folder.listFiles();		


		if (ficheros.length==0) 
		{ 
			return true;

	    }
		return false;

		}
	
	
	
	 
	public void copyFile(String sourceFile, String  destFile) throws IOException {
		File origen = new File(sourceFile);

		File destino = new File(destFile);
		
		InputStream in = new FileInputStream(origen);

		OutputStream out = new FileOutputStream(destino);
		
		byte[] buf = new byte[1024];

		int len;

		 

		while ((len = in.read(buf)) > 0) {

		  out.write(buf, 0, len);

		}

		in.close();

		out.close();




	}
	
	public String dameFechaDeHoy(){
		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy'-'MM'-'dd", new Locale("es_ES"));
		 Date fechaDate = new Date();
         String fecha=formateador.format(fechaDate);
	
	return fecha;
	}
	public String dameHora(){
	Calendar calendario = new GregorianCalendar();
	int hora, minutos;
	hora =calendario.get(Calendar.HOUR_OF_DAY);
	minutos = calendario.get(Calendar.MINUTE);
	
	
	return String.valueOf(hora)+String.valueOf(minutos);
	
	}

	public boolean ficherosSonIguales(String sourceFile, String  destFile) throws IOException {
		File origen = new File(sourceFile);

		File destino = new File(destFile);
		
		if(origen.equals(destino)){
			return true;
		
		}else{
			return false;
			
		}



	}
	public boolean borrarFichero(String rutaCompletaConNombreFichero){
		
		File fichero = new File(rutaCompletaConNombreFichero);
		if (fichero.delete())

			   return true;

			else

			  return false;
		


		
	}
	public boolean abrirDirectorio(String ruta){
		
		Runtime r = Runtime.getRuntime();
		@SuppressWarnings("unused")
		Process p = null;

		try {
			p = r.exec("explorer.exe "+ruta);
			return true;
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null,"Error! "+e.getMessage());
			return false;
		}

		
	}

}
