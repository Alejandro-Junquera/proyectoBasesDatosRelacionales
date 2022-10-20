package Funciones;

public class Matricula extends Alumno {
	String dni_alu;
	int id_asi;
	
	public Matricula() {
		super();
	}
	public Matricula(String dNI, String nombre, String apellidos, String fechaNac, int tlf, String clave, String img) {
		super(dNI, nombre, apellidos, fechaNac, tlf, clave, img);
	}
	public Matricula(String dni_alu, int id_asi) {
		super();
		this.dni_alu = dni_alu;
		this.id_asi = id_asi;
	}
	public String getDni_alu() {
		return dni_alu;
	}
	public void setDni_alu(String dni_alu) {
		this.dni_alu = dni_alu;
	}
	public int getId_asi() {
		return id_asi;
	}
	public void setId_asi(int id_asi) {
		this.id_asi = id_asi;
	}
	@Override
	public String toString() {
		return "Matricula [dni_alu=" + dni_alu + ", id_asi=" + id_asi + "]";
	}
	
	
	
}
