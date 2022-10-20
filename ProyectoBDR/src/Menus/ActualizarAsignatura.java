package Menus;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Conexiones.Conexion;
import Funciones.OperacionesBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ActualizarAsignatura extends JFrame {

	private JPanel contentPane;

	private JTextField textNombre;
	private JTextField textHorasSemanales;

	
	public ActualizarAsignatura(Connection conn, int idAsig, String nombre, Integer horasSemanales) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(60, 66, 58, 23);
		contentPane.add(lblNombre);
		
		JLabel lblHorasSemanales = new JLabel("Horas semanales");
		lblHorasSemanales.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorasSemanales.setBounds(60, 126, 109, 14);
		contentPane.add(lblHorasSemanales);
		
		textNombre = new JTextField(nombre);
		textNombre.setBounds(208, 69, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textHorasSemanales = new JTextField(horasSemanales.toString());
		textHorasSemanales.setBounds(208, 125, 86, 20);
		contentPane.add(textHorasSemanales);
		textHorasSemanales.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion con = new Conexion();
				if(textNombre.getText().equals("") || textHorasSemanales.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "No se puede dejar ningun campo en blanco");
				}else {
					OperacionesBD.actualizarAsignatura(textNombre.getText(),Integer.valueOf(textHorasSemanales.getText()),idAsig,conn);
					dispose();
					AdminAsignatura aa=new AdminAsignatura(conn);
					aa.setVisible(true);
				}
				
			}
		});
		btnAceptar.setBounds(96, 227, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminAsignatura AA = new AdminAsignatura(conn);
				AA.setVisible(true);
			}
		});
		
		btnCancelar.setBounds(232, 227, 89, 23);
		contentPane.add(btnCancelar);
	}

}
