package Objetos;

public class Producto {
	
	String barcode;
	String descripcion;
	int cantidad;
	public Producto(String barcode, String descripcion, int cantidad) {
		super();
		this.barcode = barcode;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	

}
