package Menus;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;


import Conexiones.Conexion;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import Funciones.ComprobarUsuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Funciones.insertarImagenes;
import Funciones.JFrameDiseño;

public class InicioSesion extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField textContrasenia;
	private Conexion connbd;
	private Connection conn;
	private JLabel lblVer,lblOcultar;

	public static void main(String[] args) {
		try {
			InicioSesion frame = new InicioSesion();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
			}	
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	public InicioSesion() {
		connbd=new Conexion();
		conn=connbd.conectarMySQL();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		setTitle("Iniciar sesión");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setIconImage(JFrameDiseño.logo.getImage());

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(189, 221, 253, 40);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textContrasenia = new JPasswordField();
		textContrasenia.setBounds(189, 330, 253, 40);
		textContrasenia.setEchoChar('\u25cf');
		contentPane.add(textContrasenia);
		textContrasenia.setColumns(10);
		
		JButton btnIniSesion = new JButton("Iniciar Sesión");
		btnIniSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ComprobarUsuario.comprobarAdmin(textUsuario.getText(),textContrasenia.getText())){
					//Instancia de la clase administrador
					AdminSeleccion a=new AdminSeleccion(conn);
					setVisible(false);
				}else if(ComprobarUsuario.comprobarAlumno(textUsuario.getText(),textContrasenia.getText(), conn)) {
					//Instancia de alumno
					AlumnoInfo al = new AlumnoInfo(conn, textUsuario.getText());
					al.setVisible(true);
					setVisible(false);
				}else if(ComprobarUsuario.comprobarProfesor(textUsuario.getText(),textContrasenia.getText(), conn)) {
					//Instancia de profesor
					ProfesorInfo p = new ProfesorInfo(conn, textUsuario.getText());
					p.setVisible(true);
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña erroneo");
				}
			}
		});
		btnIniSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniSesion.setBounds(94, 501, 120, 40);
		contentPane.add(btnIniSesion);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSalir.setBounds(322, 501, 120, 40);
		contentPane.add(btnSalir);
		
		JButton btnRegistro = new JButton("Registrarse");
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro r=new Registro();
			}
		});
		btnRegistro.setBounds(335, 387, 107, 21);
		contentPane.add(btnRegistro);
		
		lblVer = new JLabel("");
		lblVer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblVer.setVisible(false);
				lblOcultar.setVisible(true);
				textContrasenia.setEchoChar((char)0);
			}
		});
		lblVer.setBounds(452, 336, 45, 34);
		lblVer.setIcon(insertarImagenes.ResizableImage("Imagenes/Iconos/ver.png", lblVer));
		contentPane.add(lblVer);
		
		lblOcultar = new JLabel("");
		lblOcultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblVer.setVisible(true);
				lblOcultar.setVisible(false);
				textContrasenia.setEchoChar('\u25cf');
			}
		});
		lblOcultar.setBounds(452, 336, 45, 34);
		lblOcultar.setIcon(insertarImagenes.ResizableImage("Imagenes/Iconos/ocultar.png", lblOcultar));
		contentPane.add(lblOcultar);
		
		JLabel lblimgDNI = new JLabel("");
		lblimgDNI.setBounds(94, 227, 45, 34);
		lblimgDNI.setIcon(insertarImagenes.ResizableImage("Imagenes/Iconos/carnet-de-identidad.png", lblimgDNI));
		contentPane.add(lblimgDNI);
		
		JLabel lblimgCont = new JLabel("");
		lblimgCont.setBounds(94, 336, 45, 34);
		lblimgCont.setIcon(insertarImagenes.ResizableImage("Imagenes/Iconos/bloquear.png", lblimgCont));
		contentPane.add(lblimgCont);
		lblOcultar.setVisible(false);
		
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 536, 613);
		lblFondo.setIcon(new ImageIcon("Imagenes/Iconos/fondo.jpg"));
		lblFondo.setIcon(new ImageIcon("Imagenes/Iconos/salesianos.jpg"));
		contentPane.add(lblFondo);
		setVisible(true);
	}
}
