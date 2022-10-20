package Menus;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textContrasenia;
	private JLabel lblConfirmarContrasea;
	private JTextField textConfirContr;
	private JTextField textDNI;
	private JTextField textApellidos;
	private JTextField textTelefono;

	public Registro() {
		setBounds(100, 100, 1080, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		componentes();	
	}

	private void componentes() {
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(42, 66, 109, 25);
		contentPane.add(lblNombre);
		
		JLabel lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasenia.setBounds(42, 294, 109, 25);
		contentPane.add(lblContrasenia);
		
		textNombre = new JTextField();
		textNombre.setBounds(161, 61, 253, 40);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textContrasenia = new JTextField();
		textContrasenia.setBounds(161, 289, 253, 40);
		contentPane.add(textContrasenia);
		textContrasenia.setColumns(10);
		
		lblConfirmarContrasea = new JLabel("Confirmar \r\nContraseña");
		lblConfirmarContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmarContrasea.setBounds(424, 294, 137, 25);
		contentPane.add(lblConfirmarContrasea);
		
		textConfirContr = new JTextField();
		textConfirContr.setColumns(10);
		textConfirContr.setBounds(566, 289, 253, 40);
		contentPane.add(textConfirContr);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDNI.setBounds(42, 175, 34, 25);
		contentPane.add(lblDNI);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(161, 170, 253, 40);
		contentPane.add(textDNI);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegistrarse.setBounds(206, 456, 180, 40);
		contentPane.add(btnRegistrarse);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(679, 456, 180, 40);
		contentPane.add(btnVolver);
		
		JLabel lblNewLabel = new JLabel("Imagen");
		lblNewLabel.setBounds(904, 50, 152, 190);
		contentPane.add(lblNewLabel);
		
		JButton btnAadirImagen = new JButton("Añadir Imagen");
		btnAadirImagen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAadirImagen.setBounds(904, 258, 152, 40);
		contentPane.add(btnAadirImagen);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(447, 66, 109, 25);
		contentPane.add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(566, 61, 253, 40);
		contentPane.add(textApellidos);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(447, 183, 109, 25);
		contentPane.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(566, 180, 253, 40);
		contentPane.add(textTelefono);
		setVisible(true);	
	}
}
