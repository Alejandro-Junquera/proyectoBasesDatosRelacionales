package Menus;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexiones.Conexion;
import Funciones.OperacionesBD;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ActualizarRA extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textPonderacion;

	public ActualizarRA(Connection conn, int idRA, String nombre, String descripcion, Integer ponderacion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion con = new Conexion();
				if(textNombre.getText().equals("") || textDescripcion.getText().equals("") 
				|| textPonderacion.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se puede dejar ningun campo en blanco");
				}else {
					OperacionesBD.actualizarRA(textNombre.getText(),textDescripcion.getText(),
					Integer.valueOf(textPonderacion.getText()),idRA,conn);
					dispose();
				}
				
			}
		});
		btnAceptar.setBounds(81, 210, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(257, 210, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(98, 69, 65, 17);
		contentPane.add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescripcion.setBounds(98, 118, 92, 17);
		contentPane.add(lblDescripcion);
		
		JLabel lblPonderacion = new JLabel("Ponderacion");
		lblPonderacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPonderacion.setBounds(98, 163, 92, 26);
		contentPane.add(lblPonderacion);
		
		textNombre = new JTextField(nombre);
		textNombre.setBounds(195, 66, 221, 26);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textDescripcion = new JTextField(descripcion);
		textDescripcion.setBounds(195, 115, 221, 26);
		contentPane.add(textDescripcion);
		textDescripcion.setColumns(10);
		
		textPonderacion = new JTextField(ponderacion.toString());
		textPonderacion.setBounds(200, 166, 65, 24);
		contentPane.add(textPonderacion);
		textPonderacion.setColumns(10);
		
		JLabel lblporc = new JLabel("%");
		lblporc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblporc.setBounds(269, 169, 49, 18);
		contentPane.add(lblporc);
	}

}
