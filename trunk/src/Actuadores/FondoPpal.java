package Actuadores;

import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;

@SuppressWarnings("unused")
public class FondoPpal extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public FondoPpal() {
		super();
		initialize();
	}

	public void paint(Graphics g){
		
		Dimension tamanio=getSize();
		ImageIcon imagen=new ImageIcon(getClass().getResource("/Imagenes/FondoAmarillo.png"));
		g.drawImage(imagen.getImage(),0,0,tamanio.width,tamanio.height,null);
		setOpaque(false);
		super.paint(g);
		
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(681, 297);
		this.setLayout(null);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"

