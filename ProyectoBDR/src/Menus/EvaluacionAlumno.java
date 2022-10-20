package Menus;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Funciones.OperacionesBD;
import Funciones.RA;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EvaluacionAlumno extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Object[] fila;
	private int filaSeleccionada;

	
	public EvaluacionAlumno(Connection conn, String dniProf, String dniAlu,
			String nombreAlu, String apellidosAlu, String nombreAsig, ArrayList<RA> rasAsig) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Notas de "+nombreAsig+" de "+nombreAlu+" "+apellidosAlu);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(46, 25, 336, 26);
		contentPane.add(lblNewLabel);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(293, 212, 89, 23);
		contentPane.add(btnCerrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(46, 77, 336, 124);
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
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				filaSeleccionada = table.rowAtPoint(evt.getPoint());
			}
		});
		JButton btnEvaluar = new JButton("Calificar");
		btnEvaluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InsertarNota in = new InsertarNota(conn, dniAlu, rasAsig.get(filaSeleccionada).getId());
				in.setVisible(true);
			}
		});
		btnEvaluar.setBounds(56, 212, 89, 23);
		contentPane.add(btnEvaluar);
		llenarTabla(conn, rasAsig, dniAlu);
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
