package Funciones;

public class Alumno extends Persona{
	private String fechaNac;
	private int tlf;
	public Alumno() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Alumno(String dNI, String nombre, String apellidos,String fechaNac,int tlf, String clave, String img) {
		super(dNI, nombre, apellidos, clave, img);
		this.fechaNac=fechaNac;
		this.tlf=tlf;
	}
	public String getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
	public int getTlf() {
		return tlf;
	}
	public void setTlf(int tlf) {
		this.tlf = tlf;
	}
	@Override
	public String toString() {
		return super.toString()+ "Alumno [fechaNac=" + fechaNac + ", tlf=" + tlf + "]";
	}
	
	
	

}
