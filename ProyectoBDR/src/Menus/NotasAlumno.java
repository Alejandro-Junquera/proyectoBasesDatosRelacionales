package Menus;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Funciones.Alumno;
import Funciones.Asignatura;
import Funciones.OperacionesBD;
import Funciones.Profesor;
import Funciones.RA;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class NotasAlumno extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Object[] fila;
	

	public NotasAlumno(int idAsig,String nombreAsig, String dni_alu,String nombreAlu, ArrayList<RA> rasAsig, Connection conn) {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Notas de "+nombreAsig+" de "+nombreAlu);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(70, 26, 456, 43);
		contentPane.add(lblNewLabel);
		setTitle("Notas de "+nombreAsig+" de "+nombreAlu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 189, 412, 316);
		contentPane.add(scrollPane);

		Object[]columna={"Nombre","Descripcion","Ponderacion","Nota"};
		model=new DefaultTableModel(columna,0) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int x,int y) {
				return false;
			}	
		};
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(195, 516, 119, 43);
		contentPane.add(btnNewButton);
		Profesor p=OperacionesBD.extraerProfesorAsignatura(conn, idAsig);
		JLabel lblNewLabel_1 = new JLabel("Profesor de la asignatura: "+p.getNombre()+" "+p.getApellidos());
		lblNewLabel_1.setBounds(70, 80, 412, 43);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Correo de "+p.getNombre()+": "+p.getEmail());
		lblNewLabel_2.setBounds(70, 134, 412, 37);
		contentPane.add(lblNewLabel_2);
		llenarTabla(conn, rasAsig, dni_alu);
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
	private void llenarTabla(Connection conn, ArrayList<RA> rasAsig, String dniAlu) {
		model.setRowCount(0);
		for (RA ra:rasAsig) {
			this.fila = new Object[4];
			try {
				fila[0]=ra.getNombre();
				fila[1]=ra.getDescripcion();
				fila[2]=ra.getPonderacion();
				fila[3]=OperacionesBD.extraerNotaRaAlumno(conn, dniAlu, ra.getId());
			}
			catch(NullPointerException te) {	
			}
		model.addRow(fila);
		}
		
	}
}
