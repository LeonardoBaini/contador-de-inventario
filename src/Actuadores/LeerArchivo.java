package Actuadores;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Objetos.Producto;


public class LeerArchivo  {
	
	public LeerArchivo(){
		
	}
	public static String formatearFecha(String fecha){
		
		String anio=fecha.substring(0,4);
		String mes=fecha.substring(5,7);
		String dia=fecha.substring(8,10);
		
		return dia+"/"+mes+"/"+anio;
	}
	public ArrayList<Producto> entregarProductosDesdeIndice(){
		ArrayList<Producto>productos=new ArrayList<Producto>();
		String rutaAcarpetaInv="C:\\INVENTARIOS";
		ArrayList<String>indices;
		String rutaIndice=rutaAcarpetaInv+"\\indice.TXT";
		String codigo;
		String descripcion;
		int cantidad=0;
		LeerArchivo lector=new LeerArchivo();
		indices=lector.leer(rutaIndice);
		
		for(int i=0;i<indices.size();i++){
		if(!indices.get(i).isEmpty()){
		codigo=indices.get(i).substring(indices.get(i).lastIndexOf("|")+1);
		descripcion=indices.get(i).substring(0,indices.get(i).lastIndexOf("|"));
		descripcion=descripcion.replaceAll(" ","_");
		Producto prod=new Producto(codigo,descripcion,cantidad);
		if(!productos.contains(prod)){
			productos.add(prod);
		}
		}
		
		}	//fin for	
		return productos;
		
	}
	public ArrayList<Producto>darDetalleCantidadesProducto(){
		ArrayList<Producto>prodDetallado;
		ArrayList<String>barcodes;
		prodDetallado=entregarProductosDesdeIndice();
		barcodes=leer("C:\\INVENTARIOS\\ESCANEOS.txt");
		int flag=-1;
		int tamanioBarcodes=barcodes.size();
		for(int i=0;i<prodDetallado.size();i++){
			for(int j=0;j<tamanioBarcodes;j++){
				flag=barcodes.indexOf(prodDetallado.get(i).getBarcode());
				if(flag!=-1){
					prodDetallado.get(i).setCantidad(prodDetallado.get(i).getCantidad()+1);	
					barcodes.remove(flag);
					
				}				
			}						
		}		
		
		return prodDetallado;
		
	}
	
	public static String formatear(String strLinea){
		String legajo=strLinea.substring(4, 9).replace(' ','0');            	
    	String fecha=formatearFecha(strLinea.substring(10,20));
    	String hora=strLinea.substring(21,26);
    	String item1="01";
    	String item2="99";
        return legajo+" "+fecha+" "+hora+" "+item1+" "+item2;
		
	}

	public String ultimoUsuario(String rutaAlArchivo){
		String ultimoUsr=null;
		ArrayList<String> lista=leer(rutaAlArchivo);
		int ultimoRegistro=lista.size()-1;
		String subUltimoUsr=lista.get(ultimoRegistro).substring(65, lista.get(ultimoRegistro).length());
		ultimoUsr=subUltimoUsr.substring(0,subUltimoUsr.lastIndexOf("  ")); // el último indice con 2 espacios
		return ultimoUsr;
		
	}
	/**
	 * 
	 * Toma la ruta a un archivo y devuelve las lines leidas en forma de ArrayList
	 */
   public ArrayList<String> leer(String rutaAlArchivo){
	   ArrayList<String> lista=new ArrayList<String>();
	   //rutaAlArchivo=rutaAlArchivo.replaceAll("\\\\", "\\");
        try{
        	
            // Abrimos el archivo
            FileInputStream fstream = new FileInputStream(rutaAlArchivo);//("D:\\Desktop\\Ford.txt");
            // Creamos el objeto de entrada
            DataInputStream entrada = new DataInputStream(fstream);
            // Creamos el Buffer de Lectura
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;
               
            
            
            
            
            // Leer el archivo linea por linea
            while ((strLinea = buffer.readLine()) != null)   {
                // Imprimimos la línea por pantalla
            	/*String legajo=strLinea.substring(4, 9).replace(' ','0');            	
            	String fecha=strLinea.substring(10,20).replace('-', '/');
            	String hora=strLinea.substring(21,26);
            	String item1=strLinea.substring(23,25);
            	String item2=strLinea.substring(26,28);*/
            	//System.out.println(strLinea);
                lista.add(strLinea);
            }
            // Cerramos el archivo
            entrada.close();
            
        }catch (Exception e){ //Catch de excepciones
        	 System.out.println("Error->"+e.getMessage());
        }
        return lista;
    }
   
   public int contarLineas(String rutaAlArchivo){
	  
	   int lineas=0;
        try{
        	
           
            FileInputStream fstream = new FileInputStream(rutaAlArchivo);//("D:\\Desktop\\Ford.txt");
           
            DataInputStream entrada = new DataInputStream(fstream);
           
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            
            while ((buffer.readLine()) != null)   {
               lineas++;
            }
           
            entrada.close();
            
        }catch (Exception e){ 
        	 JOptionPane.showConfirmDialog(null,"Error "+e.getMessage());
        }
        return lineas;
    }
   
   public ArrayList<String> leerHastaLaComa(String rutaAlArchivo){
	   ArrayList<String> lista=new ArrayList<String>();
	  
        try{
        	
            // Abrimos el archivo
            FileInputStream fstream = new FileInputStream(rutaAlArchivo);//("D:\\Desktop\\Ford.txt");
            // Creamos el objeto de entrada
            DataInputStream entrada = new DataInputStream(fstream);
            // Creamos el Buffer de Lectura
            BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
            String strLinea;            
            
            // Leer el archivo linea por linea
            while ((strLinea = buffer.readLine()) != null)   {
                // Imprimimos la línea por pantalla
            	if(strLinea.length()>0){
                lista.add(strLinea.substring(0,strLinea.indexOf(",")));// hasta la coma
            	}
            }
            // Cerramos el archivo
            entrada.close();
            
        }catch (Exception e){ //Catch de excepciones
        	 JOptionPane.showMessageDialog(null,"Error "+e.getMessage());
        }
        return lista;
    }
}
