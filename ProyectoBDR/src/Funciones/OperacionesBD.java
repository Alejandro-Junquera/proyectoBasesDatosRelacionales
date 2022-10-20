package Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class OperacionesBD {

	public static ArrayList<Profesor> ExtraccionTablaProfesor(Connection conn) {
		String sql="select dni,nombre,apellidos,clave,email,img from profesor;";
		ArrayList<Profesor> profesores=new ArrayList<>();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				profesores.add(new Profesor(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("clave"),rs.getString("img"),rs.getString("email")));
			}
			statement.close();
			return profesores;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void insertarProfesor(String dni,String nombre,String apellidos,String email,String contrasenia,String img,ArrayList<Asignatura> asignaturas,Connection conn) {
		String sql="INSERT INTO profesor VALUES (?,?,?,?,?,?);";
		String sql2="UPDATE asignatura set dni_pro=? where nombre=?";
		
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			PreparedStatement statement2=conn.prepareStatement(sql2);
			statement.setString(1,dni);
			statement.setString(2,nombre);
			statement.setString(3,apellidos);
			statement.setString(4,email);
			statement.setString(5,contrasenia);
			statement.setString(6,img);
			int rs=statement.executeUpdate();
			for(int i=0;i<asignaturas.size();i++) {
				statement2.setString(1,dni);
				statement2.setString(2,asignaturas.get(i).getNombre());
				int rs2=statement2.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ArrayList<Asignatura> ExtraccionAsignaturas(Connection conn){
		String sql="select * from asignatura where dni_pro is null;";
		ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				asignaturas.add(new Asignatura(rs.getInt("id"),rs.getString("nombre"),rs.getInt("horasSemanales"),rs.getString("dni_pro")));
			}
			statement.close();
			return asignaturas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	public static ArrayList<Asignatura> ExtraccionAsignaturasProf(String dni,Connection conn){
		String sql="select * from asignatura where dni_pro=?;";
		ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,dni);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				asignaturas.add(new Asignatura(rs.getInt("id"),rs.getString("nombre"),rs.getInt("horasSemanales"),rs.getString("dni_pro")));
			}
			statement.close();
			return asignaturas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	public static ArrayList<Asignatura> ExtraccionTodasAsignaturas(Connection conn){
		String sql="select * from asignatura;";
		ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				asignaturas.add(new Asignatura(rs.getInt("id"),rs.getString("nombre"),rs.getInt("horasSemanales"),rs.getString("dni_pro")));
			}
			statement.close();
			return asignaturas;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;	
	}
	
	public static ArrayList<Asignatura> extraccionAsignaturasAlumno(Connection conn, String dni) {
		ArrayList<Asignatura> res=new ArrayList<Asignatura>();
		String sql="select * from asignatura where id in(select id_asi from matricula where dni_alu=?);";
		
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,dni);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				res.add(new Asignatura(rs.getInt("id"),rs.getString("nombre"),
						rs.getInt("horasSemanales"),rs.getString("dni_pro")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	public static void borrarDNIProfAsignatura(String dni,Connection conn) {
		String sql="update asignatura set dni_pro=? where dni_pro=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,null);
			statement.setString(2,dni);
			int rs=statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void borrarDNIProfAsignatura(int idAsig,Connection conn) {
		String sql="update asignatura set dni_pro=? where id=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,null);
			statement.setString(2,null);
			int rs=statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void borrarProfesor(String dni,Connection conn) {
		String sql="delete from profesor where dni=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,dni);
			int rs=statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void actualizarProfesor(String dni,String nombre,String apellidos,String email,String contrasenia,String img,ArrayList<Asignatura> asigLibres,ArrayList<Asignatura> asigProp,Connection conn) {
		String sql="update asignatura set dni_pro=? where dni_pro=?;";
		String sql2="update profesor set nombre=?,apellidos=?,email=?,clave=?,img=? where dni=?";
		String sql3="update asignatura set dni_pro=? where nombre=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			PreparedStatement statement2=conn.prepareStatement(sql2);
			PreparedStatement statement3=conn.prepareStatement(sql3);
			for(int i=0;i<asigLibres.size();i++) {
				statement.setString(1,null);
				statement.setString(2,dni);
				int rs=statement.executeUpdate();
			}
			statement2.setString(1,nombre);
			statement2.setString(2,apellidos);
			statement2.setString(3,email);
			statement2.setString(4,contrasenia);
			statement2.setString(5,img);
			statement2.setString(6,dni);
			int rs2=statement2.executeUpdate();
			for(int i=0;i<asigProp.size();i++) {
				statement3.setString(1,dni);
				statement3.setString(2,asigProp.get(i).getNombre());
				int rs3=statement3.executeUpdate();
			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void actualizarAsignatura(String nombre,int horasSemanales,int idAsig,Connection conn) {
		String sql="update asignatura set nombre=? where id=?;";
		String sql2="update asignatura set horasSemanales=? where id=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			PreparedStatement statement2=conn.prepareStatement(sql2);
			statement.setString(1,nombre);
			statement.setInt(2,idAsig);
			statement2.setInt(1,horasSemanales);
			statement2.setInt(2,idAsig);
			int rs=statement.executeUpdate();
			int rs2=statement2.executeUpdate();
			statement.close();
			statement2.close();
			JOptionPane.showMessageDialog(null, "Se han actualizado la asignatura correctamente");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getErrorCode());
		}
		
	}
	
	public static void actualizarRA(String nombre,String descripcion,int ponderacion, int idRA,Connection conn) {
		String sql="update ra set nombre=? where id=?;";
		String sql2="update ra set descripcion=? where id=?;";
		String sql3="update ra set ponderacion=? where id=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			PreparedStatement statement2=conn.prepareStatement(sql2);
			PreparedStatement statement3=conn.prepareStatement(sql3);
			statement.setString(1,nombre);
			statement.setInt(2,idRA);
			statement2.setString(1,descripcion);
			statement2.setInt(2,idRA);
			statement3.setInt(1,ponderacion);
			statement3.setInt(1,idRA);
			statement.executeUpdate();
			statement2.executeUpdate();
			statement.close();
			statement2.close();
			JOptionPane.showMessageDialog(null, "Se han actualizado la asignatura correctamente");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getErrorCode());
		}
		
	}
	
	public static ArrayList<Alumno> ExtraccionTablaAlumno(Connection conn) {
		String sql="select dni,nombre,apellidos,fecha_nacimiento,telefono,clave,img from alumno;";
		ArrayList<Alumno> alumnos=new ArrayList<>();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				alumnos.add(new Alumno(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("fecha_nacimiento"),rs.getInt("telefono"),rs.getString("clave"),rs.getString("img")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumnos;
		
	}
	
	public static ArrayList<Alumno> ExtraccionTablaAlumnoAsig(Connection conn, int idAsig) {
		String sql="select dni,nombre,apellidos,fecha_nacimiento,telefono,clave,img from alumno where dni in (select dni_alu from matricula where id_asi=?);";
		ArrayList<Alumno> alumnos=new ArrayList<>();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1, idAsig);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				alumnos.add(new Alumno(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("fecha_nacimiento"),rs.getInt("telefono"),rs.getString("clave"),rs.getString("img")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alumnos;
		
	}
	public static Alumno insertarAlumno(String dni, String nombre, String apellidos,String fNacimiento , int telefono,String clave, String foto, Connection conn){
        PreparedStatement ps;
        String sql;
        Alumno alum = new Alumno();
        alum.setDNI(dni);
        alum.setNombre(nombre);
        alum.setApellidos(apellidos);
        alum.setFechaNac(fNacimiento);
        alum.setTlf(telefono);
        alum.setClave(clave);
        alum.setImg(foto);
        try{
            sql = "insert into alumno(dni, nombre, apellidos, fecha_nacimiento, telefono, clave, img) values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, alum.getDNI());
            ps.setString(2, alum.getNombre());
            ps.setString(3, alum.getApellidos());
            ps.setString(4, alum.getFechaNac());
            ps.setInt(5, alum.getTlf());
            ps.setString(6, alum.getClave());
            ps.setString(7, alum.getImg());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha añadido el alumno satisfactoriamente");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
		return alum;
    }
	
	public static void actualizarAlumno(String dni,String nombre,String apellidos,String fecha,int telefono,String contrasenia,String img,Connection conn) {
		String sql="update alumno set nombre=?,apellidos=?,fecha_nacimiento=?,telefono=?,clave=?,img=? where dni=?";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,nombre);
			statement.setString(2,apellidos);
			statement.setString(3,fecha);
			statement.setInt(4,telefono);
			statement.setString(5,contrasenia);
			statement.setString(6,img);
			statement.setString(7,dni);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void insertarAsignatura(String nombre,String horasSemanales,Connection conn){
        PreparedStatement ps;
        String sql;
        Asignatura asig = new Asignatura();
        asig.setNombre(nombre);
        asig.setHorasSemanales(Integer.parseInt(horasSemanales));
        try{
            sql = "insert into asignatura(nombre, horasSemanales) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, asig.getNombre());
            ps.setInt(2, asig.getHorasSemanales());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se han insertado los datos");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
    }
	
	public static void insertarRA(String nombre,String descripcion,String ponderacion, int idAsig,Connection conn){
        PreparedStatement ps;
        String sql;
        RA ra = new RA();
        ra.setNombre(nombre);
        ra.setDescripcion(descripcion);
        ra.setPonderacion(Integer.parseInt(ponderacion));
        ra.setId_asi(idAsig);
        try{
            sql = "insert into ra(nombre, descripcion, ponderacion, id_asi) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ra.getNombre());
            ps.setString(2, ra.getDescripcion());
            ps.setInt(3, ra.getPonderacion());
            ps.setInt(4, ra.getId_asi());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se han insertado los datos");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
    }
	
	public static void CalificarAlumno(Connection conn, String dniAlu, int idRA, float nota) {
		PreparedStatement ps;
        String sql;
        try {
        	sql = "insert into califica values(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dniAlu);
            ps.setInt(2, idRA);
            ps.setFloat(3, nota);
            ps.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getMessage());
        }
	}
	
	public static void BorrarAlumno(String dni,Connection conn){

		String consulta= "delete from alumno where dni = ?;";
		try {
			PreparedStatement statement = conn.prepareStatement(consulta);
			statement.setString(1, dni);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se han eliminado el alumno correctamente");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getErrorCode());
			;
		}

	}
	//Incluye el borrado de los RA de dicha asignatura y la entrada de la tabla matriculacion 
	public static void borrarAsignatura(int idAsig,Connection conn){
		borrarRAsAsignatura(idAsig,conn);
		borrarMatriculasAsignatura(idAsig,conn);
		
		String consulta= "delete from asignatura where id = ?;";
		try {
			PreparedStatement statement = conn.prepareStatement(consulta);
			statement.setInt(1, idAsig);
			statement.executeUpdate();
			JOptionPane.showMessageDialog(null, "Se han eliminado la asignatura correctamente");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error de conexión:" + e.getErrorCode());
		}
	}
	public static void borrarRAsAsignatura(int idAsig,Connection conn) {
		String sql="delete from ra where id_asi=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1,idAsig);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void borrarRA(int idRA,Connection conn) {
		String sql="delete from ra where id=?;";
		borrarCalificacionesRA(idRA,conn);
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1,idRA);
			int rs=statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void borrarCalificacionesRA(int idRA, Connection conn) {
		String sql="delete from califica where id_ra=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1,idRA);
			int rs=statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void borrarMatriculasAsignatura(int idAsig,Connection conn) {
		String sql="delete from matricula where id_asi=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1,idAsig);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<RA> extraerRAsAsig(Connection conn, int idAsig){
		ArrayList<RA> res=new ArrayList<RA>();
		String sql="select * from RA where id_asi=?;";
		
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1,idAsig);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				res.add(new RA(rs.getInt("id"),rs.getString("nombre"), rs.getString("descripcion"),
						rs.getInt("ponderacion"),rs.getInt("id_asi")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static float extraerNotaRaAlumno(Connection conn, String dniAlumno, int idRA) {
		float res=0;
		String sql="select * from califica where (id_ra=? and dni_alu=?);";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1,idRA);
			statement.setString(2,dniAlumno);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				res=rs.getFloat("nota");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public static Profesor extraerProfesorAsignatura(Connection conn, int idAsig) {
		Profesor p=new Profesor();
		String sql="select * from profesor where dni=(select dni_pro from asignatura where id=?);";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setInt(1,idAsig);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				p=new Profesor(rs.getString("dni"),rs.getString("nombre"),rs.getString("apellidos"),
						rs.getString("clave"),rs.getString("img"),rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	public static boolean existeProf(Connection conn,String dni) {
		String sql="select * from profesor where dni=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,dni);
			ResultSet rs=statement.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
}
