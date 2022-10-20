package Funciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import Conexiones.Conexion;

public class ComprobarUsuario {
	public static boolean comprobarAdmin(String nombre,String contrasenia) {
		if(nombre.equals("admin") && contrasenia.equals("admin")) {
			return true;
		}else {
			return false;
		}
	}
	public static boolean comprobarAlumno(String DNI,String contrasenia,Connection conn) {
		String sql="select dni,clave from alumno where dni=? and clave=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,DNI);
			statement.setString(2,contrasenia);
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
	public static boolean comprobarProfesor(String DNI,String contrasenia,Connection conn) {
		String sql="select dni,clave from profesor where dni=? and clave=?;";
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1,DNI);
			statement.setString(2,contrasenia);
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
