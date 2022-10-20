package Menus;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import Conexiones.Conexion;
import Funciones.Alumno;
import Funciones.OperacionesBD;
import Funciones.Profesor;
import Funciones.insertarImagenes;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textContrasenia;
	private JTextField textDNI;
	private JTextField textApellidos;
	private JTextField textTelefono;
	private JLabel lblFoto;
	protected insertarImagenes ii= new insertarImagenes();
	private JCalendar fecha;
	private JTextField textFecha;
	private ArrayList<Alumno> alumnos= new ArrayList<Alumno>();
	private ArrayList<Profesor> profesores= new ArrayList<Profesor>();
	protected boolean existe;

	public Registro() {
		Conexion conn = new Conexion();
		this.alumnos=OperacionesBD.ExtraccionTablaAlumno(conn.conectarMySQL());
		this.profesores=OperacionesBD.ExtraccionTablaProfesor(conn.conectarMySQL());
		setTitle("Registrar alumno");
		setBounds(100, 100, 1080, 561);
		contentPane = new JPanel();
		Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((pantallaTamano.width/2)-(this.getWidth()/2), (pantallaTamano.height/2)-(this.getHeight()/2));
		componentes();	
	}

	private void componentes() {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(172, 90, 109, 25);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblNombre);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setBounds(172, 421, 109, 25);
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblContrasenia);
		
		textNombre = new JTextField();
		textNombre.setBounds(314, 75, 253, 40);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textContrasenia = new JTextField();
		textContrasenia.setBounds(314, 406, 253, 40);
		contentPane.add(textContrasenia);
		textContrasenia.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(172, 25, 34, 25);
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblDNI);
		
		textDNI = new JTextField();
		textDNI.setBounds(314, 10, 253, 40);
		textDNI.setColumns(10);
		contentPane.add(textDNI);
		
		JButton btnAniadir = new JButton("Registrar");
		btnAniadir.setBounds(206, 456, 180, 40);
		btnAniadir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				existe=false;
				for(Alumno alum:alumnos) {
					if(alum.getDNI().equals(textDNI.getText())) {
						existe=true;
						break;
					}
				}
				for(Profesor prof:profesores) {
					if(prof.getDNI().equals(textDNI.getText())) {
						existe=true;
						break;
					}
				}
				Conexion conn = new Conexion();
				if(!existe) {
					if(combrobarCamposVacios(textDNI, textNombre, textApellidos, textFecha, textTelefono,textContrasenia)) {
						try {
							Funciones.OperacionesBD.insertarAlumno(textDNI.getText(), textNombre.getText(),textApellidos.getText(),textFecha.getText(),Integer.parseInt(textTelefono.getText()),textContrasenia.getText(),lblFoto.getText(),conn.conectarMySQL());
							JOptionPane.showMessageDialog(null, "Ya puedes iniciar sesión!");
							dispose();
							InicioSesion is= new InicioSesion();
							is.setVisible(true);
						}catch(NumberFormatException nfe1) {
							JOptionPane.showMessageDialog(null, "El campo teléfono solo puede contener números");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "No puede haber campos vacios");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese Dni");
				}		
			}
		});
		btnAniadir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnAniadir);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(679, 456, 180, 40);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InicioSesion is= new InicioSesion();
				is.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnVolver);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(691, 93, 152, 190);
		lblFoto.setText(".\\Imagenes\\Fotos\\defecto.jfif");
		lblFoto.setIcon(ii.ResizableImage(lblFoto.getText(), lblFoto));
		contentPane.add(lblFoto);
		
		JButton btnAadirImagen = new JButton("Añadir Imagen");
		btnAadirImagen.setBounds(691, 301, 152, 40);
		btnAadirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ii = new insertarImagenes();
				lblFoto.setText(ii.generarRutaImg(lblFoto.getText(), lblFoto));
			}
		});
		btnAadirImagen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnAadirImagen);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(172, 155, 109, 25);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.setBounds(314, 140, 253, 40);
		textApellidos.setColumns(10);
		contentPane.add(textApellidos);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(172, 219, 109, 25);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setBounds(314, 204, 253, 40);
		textTelefono.setColumns(10);
		contentPane.add(textTelefono);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(172, 316, 132, 25);
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(lblFechaNacimiento);
		
		textFecha = new JTextField("");
		textFecha.setColumns(10);
		textFecha.setBounds(523, 311, 146, 40);
		textFecha.setEditable(false);
		contentPane.add(textFecha);
		
		fecha = new JCalendar();
		fecha.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getOldValue()!=null) {
					SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
					textFecha.setText(ff.format(fecha.getCalendar().getTime()));
				}
			}
		});
		fecha.setWeekOfYearVisible(false);
		fecha.setBounds(314, 259, 199, 137);
		contentPane.add(fecha);
		
		
		 
	}
	
	public boolean combrobarCamposVacios(JTextField dni,JTextField nombre,JTextField apellidos,JTextField fecha,JTextField telefono,JTextField contrasenia) {
		if(!dni.getText().isEmpty() && !nombre.getText().isEmpty() && !apellidos.getText().isEmpty() && !fecha.getText().isEmpty() && !telefono.getText().isEmpty()&& !contrasenia.getText().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
}