package Funciones;

public class Asignatura {
	private int id;
	private String nombre;
	private int horasSemanales;
	private String dni_prof;
	public Asignatura() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Asignatura(int id, String nombre, int horasSemanales, String dni_prof) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.horasSemanales = horasSemanales;
		this.dni_prof = dni_prof;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getHorasSemanales() {
		return horasSemanales;
	}
	public void setHorasSemanales(int horasSemanales) {
		this.horasSemanales = horasSemanales;
	}
	public String getDni_prof() {
		return dni_prof;
	}
	public void setDni_prof(String dni_prof) {
		this.dni_prof = dni_prof;
	}
	@Override
	public String toString() {
		return "Asignatura [id=" + id + ", nombre=" + nombre + ", horasSemanales=" + horasSemanales + ", dni_prof="
				+ dni_prof + "]";
	}
	
}
