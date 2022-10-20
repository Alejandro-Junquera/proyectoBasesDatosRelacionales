package Menus;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Conexiones.Conexion;
import Funciones.OperacionesBD;

public class InsertarNota extends JFrame {

	private JPanel contentPane;
	private JTextField textNota;
	private float nota;

	
	public InsertarNota(Connection conn, String dniAlu,int idRA) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 252, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNota = new JLabel("Nota: ");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNota.setBounds(27, 76, 51, 23);
		contentPane.add(lblNota);
		
		textNota = new JTextField();
		textNota.setBounds(88, 79, 86, 20);
		contentPane.add(textNota);
		textNota.setColumns(10);
		
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nota=Float.valueOf(textNota.getText());
				Conexion con = new Conexion();
				OperacionesBD.CalificarAlumno(conn, dniAlu, idRA, nota);
				dispose();
			}
		});
		btnInsertar.setBounds(27, 146, 71, 23);
		contentPane.add(btnInsertar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			}
		});
		btnCancelar.setBounds(108, 146, 95, 23);
		contentPane.add(btnCancelar);
	}

}
