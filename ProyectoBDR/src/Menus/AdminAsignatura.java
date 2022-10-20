package Menus;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Conexiones.Conexion;
import Funciones.Asignatura;
import Funciones.OperacionesBD;
import Funciones.Profesor;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

public class AdminAsignatura extends JFrame {

	private JPanel contentPane;
	private static DefaultTableModel modelo;
	private static  ArrayList<Profesor> profesores=new ArrayList<>();
	private Integer filaSeleccionada;
	private JButton btnInsertar;
	private JTable tablaAsignaturas;
	private static ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
	

	public AdminAsignatura(Connection conn) {
		setTitle("Administracion de asignaturas");
		asignaturas=OperacionesBD.ExtraccionTodasAsignaturas(conn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnVolverAdmin = new JButton("Volver");
		btnVolverAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminSeleccion(conn);
				dispose();
			}
		});
		btnVolverAdmin.setBounds(45, 42, 89, 23);
		contentPane.add(btnVolverAdmin);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarAsignatura ia = new InsertarAsignatura(conn);
				ia.setVisible(true);
				dispose();
			}
		});
		btnInsertar.setBounds(76, 427, 120, 23);
		contentPane.add(btnInsertar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filaSeleccionada==null) {
					JOptionPane.showMessageDialog(null, "Selecciona una asignatura");
				}else {
					ActualizarAsignatura AA= new ActualizarAsignatura(conn,asignaturas.get(filaSeleccionada).getId(),
							asignaturas.get(filaSeleccionada).getNombre(),asignaturas.get(filaSeleccionada).getHorasSemanales());
					dispose();
					AA.setVisible(true);
				}
				
			}
		});
		btnActualizar.setBounds(220, 427, 120, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filaSeleccionada==null) {
					JOptionPane.showMessageDialog(null, "Selecciona una asignatura");
				}else {
					Conexion con = new Conexion();
					OperacionesBD.borrarDNIProfAsignatura(filaSeleccionada,con.conectarMySQL());
					OperacionesBD.borrarAsignatura(asignaturas.get(filaSeleccionada).getId(), con.conectarMySQL());
					asignaturas=OperacionesBD.ExtraccionTodasAsignaturas(conn);
					actualizarTablaAsig();
				}
			}
		});
	
		btnEliminar.setBounds(366, 427, 120, 23);
		contentPane.add(btnEliminar);
		
		JButton btnEditarRAs = new JButton("Editar RAs");
		btnEditarRAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarRAs ER = new EditarRAs(asignaturas.get(filaSeleccionada).getId(),asignaturas.get(filaSeleccionada).getNombre(), conn);
				
				ER.setVisible(true);
			}
		});
		btnEditarRAs.setBounds(532, 427, 120, 23);
		contentPane.add(btnEditarRAs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 160, 653, 176);
		contentPane.add(scrollPane);
		String[] columna=new String[] {"ID","Nombre","Horas Semanales", "Dni del profesor"};
		modelo=new DefaultTableModel(columna,0) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int x,int y) {
				return false;
			}	
		};
		tablaAsignaturas = new JTable(modelo);
		actualizarTablaAsig();
		tablaAsignaturas.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				filaSeleccionada = tablaAsignaturas.rowAtPoint(evt.getPoint());
			}
		});
		
		scrollPane.setViewportView(tablaAsignaturas);
		
		JLabel encabezado = new JLabel("Administracion de asignaturas");
		encabezado.setFont(new Font("Tahoma", Font.PLAIN, 25));
		encabezado.setBounds(169, 83, 349, 41);
		contentPane.add(encabezado);
		
		
		
		
	}
	
	public static void actualizarTablaAsig() {
		modelo.setRowCount(0);
		try {
			for (int i = 0; i < asignaturas.size(); i++) {
				int id = asignaturas.get(i).getId();
				String nombre = asignaturas.get(i).getNombre();
				int horasSemanales = asignaturas.get(i).getHorasSemanales();
				String dni_pro = asignaturas.get(i).getDni_prof();

				Object[] data = { id,nombre,horasSemanales,dni_pro };
				modelo.addRow(data);
			}
		} catch (java.lang.NullPointerException e) {
		}

	}
}
