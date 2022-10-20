package Menus;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Conexiones.Conexion;
import Funciones.Alumno;
import Funciones.OperacionesBD;
import Funciones.RA;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlumnosMatriculados extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JTable tablaAlumnos;
	protected int filaSeleccionada;
	private JLabel lblFotoAlumno;
	private ArrayList<Alumno> alumnos= new ArrayList<Alumno>();
	private Image img;
	private DefaultTableModel model;
	private Object[] fila;

	
	public AlumnosMatriculados(Connection conn, int idAsig,String nombreAsig, String dniProf, ArrayList<RA> rasAsig) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAlumnosMat = new JLabel("Alumnos matriculados en "+nombreAsig);
		lblAlumnosMat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAlumnosMat.setBounds(32, 27, 446, 30);
		contentPane.add(lblAlumnosMat);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 468, 150);
		
		contentPane.add(scrollPane);
		
		tablaAlumnos = new JTable();
		tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				filaSeleccionada = tablaAlumnos.rowAtPoint(evt.getPoint());
			}
		});
		model= new DefaultTableModel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int x,int y) {
				return false;	
			}
		};
		
		Object[]columna={"Dni","Nombre","Apellidos","Fecha de nacimiento","Telefono"};
		model.setColumnIdentifiers(columna);
		tablaAlumnos.setModel(model);
		scrollPane.setViewportView(tablaAlumnos);
		tablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton btnEvaluar = new JButton("Evaluar");
		btnEvaluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EvaluacionAlumno ea = new EvaluacionAlumno(conn,dniProf,alumnos.get(filaSeleccionada).getDNI(),
						alumnos.get(filaSeleccionada).getNombre(),alumnos.get(filaSeleccionada).getApellidos(),nombreAsig, rasAsig);
				ea.setVisible(true);
				dispose();
			}
		});
		btnEvaluar.setBounds(72, 254, 89, 23);
		contentPane.add(btnEvaluar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfesorInfo p=new ProfesorInfo(conn, dniProf);
				p.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(294, 254, 89, 23);
		contentPane.add(btnVolver);
		llenarTabla(idAsig, conn);
	}
	void llenarTabla(int idAsig, Connection conn) {
		tablaAlumnos.getColumnModel().getColumn(4).setMaxWidth(0);
		tablaAlumnos.getColumnModel().getColumn(4).setMinWidth(0);
		tablaAlumnos.getColumnModel().getColumn(4).setPreferredWidth(0);
		this.alumnos=OperacionesBD.ExtraccionTablaAlumnoAsig(conn, idAsig);
		model.setRowCount(0);
		for (Alumno alum:this.alumnos) {
			this.fila = new Object[5];
			try {
				fila[0]=alum.getDNI();
				fila[1]=alum.getNombre();
				fila[2]=alum.getApellidos();
				fila[3]=alum.getFechaNac();
				fila[4]=alum.getTlf();			
			}
			catch(NullPointerException te) {	
			}
		model.addRow(fila);
		}
		
	}
}
