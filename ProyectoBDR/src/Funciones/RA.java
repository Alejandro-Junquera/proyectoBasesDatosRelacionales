package Funciones;

public class RA {
	private int id;
	private String nombre;
	private String descripcion;
	private int ponderacion;
	private int id_asi;
	
	
	public RA() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RA(int id, String nombre, String descripcion, int ponderacion, int id_asi) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ponderacion = ponderacion;
		this.id_asi = id_asi;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPonderacion() {
		return ponderacion;
	}
	public void setPonderacion(int ponderacion) {
		this.ponderacion = ponderacion;
	}
	public int getId_asi() {
		return id_asi;
	}
	public void setId_asi(int id_asi) {
		this.id_asi = id_asi;
	}
	@Override
	public String toString() {
		return "RA [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", ponderacion=" + ponderacion
				+ ", id_asi=" + id_asi + "]";
	}
	
	
	
	
}
