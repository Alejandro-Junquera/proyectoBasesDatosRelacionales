package Menus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Conexiones.Conexion;
import Funciones.OperacionesBD;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class InsertarRA extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JTextField textPonderacion;

	public InsertarRA(int idAsig,String nombreAsig,Connection conn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		textNombre = new JTextField();
		textNombre.setBounds(195, 66, 221, 26);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textDescripcion = new JTextField();
		textDescripcion.setBounds(195, 115, 221, 26);
		contentPane.add(textDescripcion);
		textDescripcion.setColumns(10);
		
		textPonderacion = new JTextField();
		textPonderacion.setBounds(200, 166, 65, 24);
		contentPane.add(textPonderacion);
		textPonderacion.setColumns(10);
		
		JLabel lblporc = new JLabel("%");
		lblporc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblporc.setBounds(269, 169, 49, 18);
		contentPane.add(lblporc);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion con = new Conexion();
				OperacionesBD.insertarRA(textNombre.getText(),textDescripcion.getText(),textPonderacion.getText(),idAsig, conn);
				dispose();
				EditarRAs EA=new EditarRAs(idAsig,nombreAsig,conn);
				EA.setVisible(true);
			}
		});
		btnInsertar.setBounds(98, 240, 89, 23);
		contentPane.add(btnInsertar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditarRAs EA = new EditarRAs(idAsig,nombreAsig,conn);
				EA.setVisible(true);
			}
		});
		btnCancelar.setBounds(305, 240, 89, 23);
		contentPane.add(btnCancelar);
	}
}
