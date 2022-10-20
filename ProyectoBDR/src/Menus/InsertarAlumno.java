package Menus;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Conexiones.Conexion;
import Funciones.insertarImagenes;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 

public class InsertarAlumno extends JFrame  {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textContrasenia;
	private JTextField textDNI;
	private JTextField textApellidos;
	private JTextField textTelefono;
	private JTextField textFecha;
	private JLabel lblFoto;
	protected insertarImagenes ii= new insertarImagenes();



	public InsertarAlumno() {
		setTitle("Insertar alumno");
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
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(172, 122, 109, 25);
		contentPane.add(lblNombre);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
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
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDNI.setBounds(172, 57, 34, 25);
		contentPane.add(lblDNI);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(314, 42, 253, 40);
		contentPane.add(textDNI);
		
		JButton btnAniadir = new JButton("Añadir");
		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion conn = new Conexion();
				Funciones.OperacionesBD.insertarAlumno(textDNI.getText(), textNombre.getText(),textApellidos.getText(),textFecha.getText(),Integer.parseInt(textTelefono.getText()),textContrasenia.getText(),lblFoto.getText(),conn.conectarMySQL());
				dispose();
				VistaAlumnos va= new VistaAlumnos();
				va.setVisible(true);
			}
		});
		btnAniadir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAniadir.setBounds(206, 456, 180, 40);
		contentPane.add(btnAniadir);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VistaAlumnos va= new VistaAlumnos();
				va.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(679, 456, 180, 40);
		contentPane.add(btnVolver);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(691, 93, 152, 190);
		lblFoto.setText(".\\Imagenes\\Fotos\\defecto.jfif");
		lblFoto.setIcon(ii.ResizableImage(lblFoto.getText(), lblFoto));
		contentPane.add(lblFoto);
		
		JButton btnAadirImagen = new JButton("Añadir Imagen");
		btnAadirImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ii = new insertarImagenes();
				lblFoto.setText(ii.generarRutaImg(lblFoto.getText(), lblFoto));
			}
		});
		btnAadirImagen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAadirImagen.setBounds(691, 301, 152, 40);
		contentPane.add(btnAadirImagen);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(172, 187, 109, 25);
		contentPane.add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(314, 172, 253, 40);
		contentPane.add(textApellidos);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(172, 251, 109, 25);
		contentPane.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(314, 236, 253, 40);
		contentPane.add(textTelefono);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaNacimiento.setBounds(172, 316, 132, 25);
		contentPane.add(lblFechaNacimiento);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(314, 301, 253, 40);
		contentPane.add(textFecha);
		
	}

}
