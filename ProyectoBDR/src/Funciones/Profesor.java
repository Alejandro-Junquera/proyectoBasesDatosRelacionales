package Funciones;

public class Profesor extends Persona {
	private String email;

	public Profesor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Profesor(String dNI, String nombre, String apellidos, String clave, String img,String email) {
		super(dNI, nombre, apellidos, clave, img);
		this.email=email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return super.toString()+"Profesor [email=" + email + "]";
	}
	

	
	

}
