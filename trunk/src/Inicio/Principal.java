package Inicio;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Actuadores.EscribirArchivo;
import Actuadores.InicializadorDirectorios;
import Actuadores.LeerArchivo;
import Objetos.Producto;
import Pantallas.TomaInv;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String rutaAcarpetaInv="C:\\INVENTARIOS";
		InicializadorDirectorios ini=new InicializadorDirectorios();
		/*EscribirArchivo escritor=new EscribirArchivo();
		escritor.escribirContinuacionUnaLinea(rutaAcarpetaInv+"\\INDICE.TXT", "");
		escritor.escribirContinuacionUnaLinea(rutaAcarpetaInv+"\\ESCANEOS.TXT", "");*/
		if(ini.esDirectorio("C:\\INVENTARIOS")==false)
		ini.crearCarpetas("C:\\INVENTARIOS");
		
		TomaInv inventario=new TomaInv();
		inventario.setVisible(true);
		inventario.setLocationRelativeTo(null);
		inventario.setResizable(false);
		inventario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*LeerArchivo lector=new LeerArchivo();
		ArrayList<Producto>productos;
		productos=lector.darDetalleCantidadesProducto();
		for(int i=0;i<productos.size();i++){
			System.out.println(productos.get(i).getBarcode()+" "+productos.get(i).getDescripcion()+" "+productos.get(i).getCantidad());
		}*/
		
		

	}

}
