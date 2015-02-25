package Pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;
import javax.swing.JButton;

import Actuadores.EscribirArchivo;
import Actuadores.InicializadorDirectorios;
import Actuadores.LeerArchivo;
import Objetos.Producto;

import java.awt.Toolkit;



public class TomaInv extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane =  null;
	
	private JLabel LabelBarcode = null;
	private JTextField jTextFieldBarcode = null;
	private JLabel jLabel = null;
	private JLabel JlabelFechaHora = null;
	private JLabel JlabelTotalUltimoCargado = null;
	private JLabel jLabelDescripcionItem = null;
	private JButton jButtonFinalizar = null;
	/**
	 * This is the default constructor
	 */
	private void cambiarFuente(Font fuente){//LabelBarcode.setFont(new java.awt.Font("Tahoma", 0, 20));
		LabelBarcode.setFont(fuente);
		jLabelDescripcionItem.setFont(fuente);
		JlabelTotalUltimoCargado.setFont(fuente);
		jLabel.setFont(fuente);
	
		JlabelFechaHora.setFont(fuente);
	}
	private void colorearLetrasLabels(Color color){
		LabelBarcode.setForeground(color);
		jLabelDescripcionItem.setForeground(color);
		JlabelTotalUltimoCargado.setForeground(color);
		jLabel.setForeground(color);
		
		JlabelFechaHora.setForeground(color);
	}
	public TomaInv() {
		super();
		initialize();
		colorearLetrasLabels(Color.BLACK);
		cambiarFuente(new java.awt.Font("CASTELLAR", 0, 20));
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		this.setSize(1003, 363);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/HavannaLogo.png")));
		this.setContentPane(getJContentPane());
		//cambiarFuente(new Font("ARIAL BLACK", 20, 12));//java.awt.
		//colorearLetrasLabels(Color.BLACK.brighter());
		this.setTitle("Toma de Inventario                                                         ***Creado por Leonardo Baini- leobaini@hotmail.com***");
		
		
		
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			
			jLabelDescripcionItem = new JLabel();
			jLabelDescripcionItem.setBounds(new Rectangle(14, 88, 594, 25));
			jLabelDescripcionItem.setText("");
			JlabelTotalUltimoCargado = new JLabel();
			JlabelTotalUltimoCargado.setBounds(new Rectangle(13, 153, 594, 25));
			JlabelTotalUltimoCargado.setText("TOTAL ULTIMO CARGADO =");
			JlabelFechaHora = new JLabel();
			JlabelFechaHora.setBounds(new Rectangle(14, 283, 594, 25));
			JlabelFechaHora.setText("LA FECHA Y HORA DE ULT ESCANEO");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(14, 218, 594, 25));
			jLabel.setText("ÚLTIMO CODIGO CARGADO");
			LabelBarcode = new JLabel();
			LabelBarcode.setBounds(new Rectangle(14, 6, 933, 25));
			LabelBarcode.setText("ESCANEE AQUÍ  LOS CODIGOS DE BARRAS, AL FINALIZAR C/U OPRIMA ENTER.");
			
			 
			
			
			jContentPane =new Actuadores.FondoPpal();//new JPanel();//
			
			jContentPane.setLayout(null);
			
			jContentPane.add(LabelBarcode, null);
			jContentPane.add(getJTextFieldBarcode(), null);
			jContentPane.add(jLabel, null);
			jContentPane.add(JlabelFechaHora, null);
			jContentPane.add(JlabelTotalUltimoCargado, null);
			jContentPane.add(jLabelDescripcionItem, null);
			jContentPane.add(getJButtonFinalizar(), null);
			
		}
		return jContentPane;
	}

	/**
	 * This method initializes jTextFieldBarcode	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private void removerVacios(ArrayList<String>arreglo){
		for(int i=0;i<arreglo.size();i++){
			if(arreglo.get(i).isEmpty()){
				arreglo.remove(i);
			}
		}
	}
	private boolean existeDescripcion(ArrayList<String>indices,String descripcion){
		
		for(int i=0;i<indices.size();i++){
			if(indices.get(i).substring(0,indices.get(i).indexOf("|")).equals(descripcion.toUpperCase())){			
			return true;
			}
		
		
	}
		return false;
	}
	
private String dameDescripcion(String barcode){
		String descripcion="No existe";
		LeerArchivo lector=new LeerArchivo();
		ArrayList<String>indices=lector.leer("C:\\INVENTARIOS\\INDICE.txt");
		for(int i=0;i<indices.size();i++){
			if(indices.get(i).substring(indices.get(i).indexOf("|")+1,indices.get(i).length()).equals(barcode.toUpperCase())){			
			descripcion=indices.get(i).substring(0,indices.get(i).indexOf("|"));
			}
		
		
	}
		return descripcion;
	}
	private int contarEscaneados(String codigo){
		int contador=0;
		ArrayList<String>codigos;
		LeerArchivo lector=new LeerArchivo();
		try{
		codigos=lector.leer("C:\\INVENTARIOS\\ESCANEOS.TXT");
		for(int i=0;i<codigos.size();i++){
			if(codigos.get(i).equals(codigo)){
				contador++;
			}
		}
		
		}catch(Exception e){
			
		}
		
		return contador;
	}
	private void actualizarEtiquetas(String barcode){
		String descripcion=null;
		descripcion=dameDescripcion(barcode);
		
		jLabelDescripcionItem.setText(descripcion);
		JlabelTotalUltimoCargado.setText("TOTAL ULTIMO CARGADO="+contarEscaneados(barcode));
		jTextFieldBarcode.setText("");
		JlabelFechaHora.setText("Fecha de último escaneo: "+dameFechaDeHoy());
		jLabel.setText("Último escaneo: "+barcode);
	}
	
	
	
	private int comprobarSiEstaBarcodeEnIndice(ArrayList<String>indices,String barcode){
		int esta=0;
	for(int i=0;i<indices.size();i++){
		if(indices.get(i).substring(indices.get(i).indexOf("|")+1,indices.get(i).length()).equals(barcode)){
			esta=1;//esta el codigo de barras en el indice
		// descripcion del prod
			
		}
		}//fin for
	return esta;
	
	}
	private JTextField getJTextFieldBarcode() {
		if (jTextFieldBarcode == null) {
			jTextFieldBarcode = new JTextField();
			jTextFieldBarcode.setBounds(new Rectangle(14, 46, 395, 26));
			jTextFieldBarcode.setBackground(Color.white);
			jTextFieldBarcode.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
					String barcode=jTextFieldBarcode.getText();
					if(!barcode.isEmpty()){
					
					
					  
					   String descripcion="";
					   String rutaAcarpetaInv="C:\\INVENTARIOS";
						InicializadorDirectorios ini=new InicializadorDirectorios();
						EscribirArchivo escritor=new EscribirArchivo();
						LeerArchivo lector=new LeerArchivo();
						ArrayList<String>indices=lector.leer(rutaAcarpetaInv+"\\INDICE.TXT");
						int esta=0;
						removerVacios(indices);
						
						esta=comprobarSiEstaBarcodeEnIndice(indices,barcode);// 1 si esta 0 si no esta
						
						if(esta==0){//si no está el barcode en el indice
							descripcion=JOptionPane.showInputDialog("No conozco éste código.\n¿Qué producto es?");
							
							if(!descripcion.isEmpty()){
								if(existeDescripcion(indices, descripcion)){
									JOptionPane.showMessageDialog(null,"Ya existe esa descripción, coloque otra");
								}
								else{//si no existe la descripcion
							escritor.escribirContinuacionUnaLinea(rutaAcarpetaInv+"\\INDICE.TXT",descripcion.toUpperCase()+"|"+barcode);
							escritor.escribirContinuacionUnaLinea(rutaAcarpetaInv+"\\ESCANEOS.TXT",barcode);
							
							JOptionPane.showMessageDialog(null,"Guardado!\n"+barcode);
							actualizarEtiquetas(barcode);
								}
							}//fin descrip vacia
							else{
								JOptionPane.showMessageDialog(null,"La descripción no puede quedar vacía!");
							}
						}else{//fin si no está
							escritor.escribirContinuacionUnaLinea(rutaAcarpetaInv+"\\ESCANEOS.TXT",barcode);
							
							JOptionPane.showMessageDialog(null,"Guardado!\n"+barcode);
							actualizarEtiquetas(barcode);
						}
					}else{
						JOptionPane.showMessageDialog(null,"No se permite el campo vacío");
					}
				}catch(Exception e1){
					System.out.println(e1.getMessage());
				}
					}
			});
			
			
			
			
			
		}
		return jTextFieldBarcode;
	}
	
	public String dameFechaDeHoy(){
		 SimpleDateFormat formateador = new SimpleDateFormat("yyyy'/'MM'/'dd'/'hh':'mm':'ss", new Locale("es_ES"));
		 Date fechaDate = new Date();
         String fecha=formateador.format(fechaDate);
	
	return fecha;
	}
	/**
	 * This method initializes jButtonFinalizar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private void contarStock(){
		LeerArchivo lector=new LeerArchivo();
		ArrayList<String> indices;
		ArrayList<String> barcodes;
		String rutaAcarpetaInv="C:\\INVENTARIOS";
		String rutaIndice=rutaAcarpetaInv+"\\INDICE.TXT";
		String rutaBarcodes=rutaAcarpetaInv+"\\ESCANEOS.TXT";
		indices=lector.leer(rutaIndice);
		barcodes=lector.leer(rutaBarcodes);
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	private JButton getJButtonFinalizar() {
		if (jButtonFinalizar == null) {
			jButtonFinalizar = new JButton();
			jButtonFinalizar.setBounds(new Rectangle(682, 47, 235, 260));
			jButtonFinalizar.setText("");
			jButtonFinalizar.setBackground(Color.yellow.darker());
			
			jButtonFinalizar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/HavannaLogo.png")));
			jButtonFinalizar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					LeerArchivo lector=new LeerArchivo();
					ArrayList<String>informe=new ArrayList<String>();
					ArrayList<Producto>productos;
					String barcode;
					String descripcion;
					int cantidad=0;
					productos=lector.darDetalleCantidadesProducto();
					for(int i=0;i<productos.size();i++){
						barcode=productos.get(i).getBarcode();;
						descripcion=productos.get(i).getDescripcion();
						cantidad=productos.get(i).getCantidad();
						if(cantidad>0)
						informe.add(descripcion+"\t"+barcode+"\t"+cantidad);
					}
					EscribirArchivo escritor=new EscribirArchivo();
					escritor.escribir("C:\\INVENTARIOS\\INFORME_FINAL.txt", informe);
					InicializadorDirectorios ini=new InicializadorDirectorios();
					ini.abrirDirectorio("C:\\INVENTARIOS");
					
				}
			});
		}
		return jButtonFinalizar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
