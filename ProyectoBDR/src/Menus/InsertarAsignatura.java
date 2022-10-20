package Menus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexiones.Conexion;
import Funciones.OperacionesBD;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class InsertarAsignatura extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textHorasSemanales;

	
	public InsertarAsignatura(Connection conn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminAsignatura AA = new AdminAsignatura(conn);
				AA.setVisible(true);
			}
		});
		btnCancelar.setBounds(218, 288, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(60, 66, 58, 23);
		contentPane.add(lblNombre);
		
		JLabel lblHorasSemanales = new JLabel("Horas semanales");
		lblHorasSemanales.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHorasSemanales.setBounds(60, 126, 109, 14);
		contentPane.add(lblHorasSemanales);
		
		textNombre = new JTextField();
		textNombre.setBounds(208, 69, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textHorasSemanales = new JTextField();
		textHorasSemanales.setBounds(208, 125, 86, 20);
		contentPane.add(textHorasSemanales);
		textHorasSemanales.setColumns(10);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion con = new Conexion();
				OperacionesBD.insertarAsignatura(textNombre.getText(),textHorasSemanales.getText(),con.conectarMySQL());
				dispose();
				AdminAsignatura aa=new AdminAsignatura(conn);
				aa.setVisible(true);
			}
		});
		btnInsertar.setBounds(69, 288, 89, 23);
		contentPane.add(btnInsertar);
	}
}
