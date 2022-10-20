package Funciones;

public class Persona {
	private String DNI;
	private String nombre;
	private String apellidos;
	private String clave;
	private String img;
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Persona(String dNI, String nombre, String apellidos, String clave, String img) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.clave = clave;
		this.img = img;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "Persona [DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", clave=" + clave + ", img="
				+ img + "]";
	}
	

}
