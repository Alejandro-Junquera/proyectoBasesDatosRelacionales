package Menus;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Conexiones.Conexion;
import Funciones.insertarImagenes;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.awt.event.ActionEvent;
 

public class ActualizarAlumno extends JFrame  {

	private JPanel contentPane;
	private JTextField textNombre,textContrasenia,textDNI,textApellidos,textTelefono,textFecha;
	private JLabel lblFoto,lblFechaNacimiento,lblTelefono,lblApellidos,lblDNI,lblContrasenia,lblNombre;
	private File ruta;
	private Image img;
	private JButton btnActualizar,btnAadirImagen;
	private JFileChooser carpeta;
	private String relativaFoto;

	public ActualizarAlumno() {
		setTitle("Actualizar alumno");
		setBounds(100, 100, 1080, 561);
		contentPane = new JPanel();
		Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((pantallaTamano.width/2)-(this.getWidth()/2), (pantallaTamano.height/2)-(this.getHeight()/2));
		componentes();
	}
	
	public ActualizarAlumno(String dni, String nombre, String apellidos, String fecha,int tel ,String clave,String imagen, Connection conn) {
		setTitle("Actualizar alumno");
		setBounds(100, 100, 1080, 561);
		contentPane = new JPanel();
		Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((pantallaTamano.width/2)-(this.getWidth()/2), (pantallaTamano.height/2)-(this.getHeight()/2));
		componentes();
		textDNI.setText(dni);
		textNombre.setText(nombre);
		textApellidos.setText(apellidos);
		textFecha.setText(fecha);
		textTelefono.setText(String.valueOf(tel));
		textContrasenia.setText(clave);
		relativaFoto=imagen;
		lblFoto.setText(imagen);
		this.img=getToolkit().getImage(imagen);
		this.img=this.img.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(),Image.SCALE_DEFAULT);
		lblFoto.setIcon(new ImageIcon(img));

	}

	private void componentes() {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(172, 122, 109, 25);
		contentPane.add(lblNombre);
		
		lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasenia.setBounds(172, 379, 109, 25);
		contentPane.add(lblContrasenia);
		
		textNombre = new JTextField();
		textNombre.setBounds(314, 107, 253, 40);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textContrasenia = new JTextField();
		textContrasenia.setBounds(314, 364, 253, 40);
		contentPane.add(textContrasenia);
		textContrasenia.setColumns(10);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDNI.setBounds(172, 57, 34, 25);
		contentPane.add(lblDNI);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(314, 42, 253, 40);
		textDNI.setEditable(false);
		contentPane.add(textDNI);
		
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion conn = new Conexion();
				Funciones.OperacionesBD.actualizarAlumno(textDNI.getText(), textNombre.getText(),textApellidos.getText(),textFecha.getText(),Integer.parseInt(textTelefono.getText()),textContrasenia.getText(),relativaFoto,conn.conectarMySQL());
				dispose();
				VistaAlumnos va= new VistaAlumnos();
				va.setVisible(true);
			}
		});
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizar.setBounds(206, 456, 180, 40);
		contentPane.add(btnActualizar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VistaAlumnos va = new VistaAlumnos();
				va.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(679, 456, 180, 40);
		contentPane.add(btnVolver);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(691, 93, 152, 190);
		contentPane.add(lblFoto);
		
		btnAadirImagen = new JButton("Añadir Imagen");
		btnAadirImagen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				insertarImagenes ii = new insertarImagenes();
				relativaFoto=ii.generarRutaImg(relativaFoto, lblFoto);
				
				
			}
		});
		btnAadirImagen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAadirImagen.setBounds(691, 301, 152, 40);
		contentPane.add(btnAadirImagen);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(172, 187, 109, 25);
		contentPane.add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(314, 172, 253, 40);
		contentPane.add(textApellidos);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(172, 251, 109, 25);
		contentPane.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(314, 236, 253, 40);
		contentPane.add(textTelefono);
		
		lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaNacimiento.setBounds(172, 316, 132, 25);
		contentPane.add(lblFechaNacimiento);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(314, 301, 253, 40);
		contentPane.add(textFecha);
		
	}

}
