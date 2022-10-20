package Menus;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ProcessHandle.Info;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Funciones.Alumno;
import Funciones.Asignatura;
import Funciones.OperacionesBD;
import Funciones.RA;
import Funciones.insertarImagenes;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


public class AlumnoInfo extends JFrame {

	private JPanel contentPane;
	private JTable tablaAlum;
	private DefaultTableModel modeloAlum = new DefaultTableModel();
	private static DefaultTableModel modeloAsig;
	private String dni;
	private String nombre;
	private String apellidos;
	private String fechaNac;
	private String tlf;
	private JTextField txtTusDatos;
	private String relativa;
	private static ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
	private JTable table;
	private Integer filaSeleccionada;
	private ArrayList<RA> rasAsig;

	public AlumnoInfo(Connection conn, String dniAlumno) {
		
		asignaturas=OperacionesBD.extraccionAsignaturasAlumno(conn, dniAlumno);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel1 = new JPanel();

		try {
			modeloAlum= new DefaultTableModel() {
				
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int x,int y) {
					return false;	
				}
			};
			String[] columnas = { "DNI", "Nombre", "Apellidos", "Fecha de nacimiento", "Telefono"};
			modeloAlum.setColumnIdentifiers(columnas);
			
			String sql="select dni,nombre,apellidos,fecha_nacimiento,telefono from alumno where dni=?;";
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1, dniAlumno);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				dni = rs.getString("dni");
				nombre = rs.getString("nombre");
				apellidos = String.valueOf(rs.getString("apellidos"));
				fechaNac = String.valueOf(rs.getString("fecha_nacimiento"));
				tlf = String.valueOf(rs.getString("telefono"));
			}
			Object[] datos = {dni, nombre, apellidos, fechaNac, tlf};
			modeloAlum.addRow(datos);
			
		} catch (Exception ex) {
		}
		setTitle("Info. de "+nombre);
		contentPane.add(panel1, BorderLayout.CENTER);
		
		JButton btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new InicioSesion();
			}
		});
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCerrarSesion.setBounds(143, 497, 250, 63);
		contentPane.add(btnCerrarSesion);
		tablaAlum = new JTable();
		tablaAlum.setEnabled(true);
		tablaAlum.setModel(modeloAlum);
		JScrollPane scrollPane = new JScrollPane(tablaAlum);
		scrollPane.setBounds(36, 74, 452, 39);
		contentPane.add(scrollPane);
		
		JLabel lblImg = new JLabel("Insertar Imagen");
		lblImg.setBounds(371, 156, 127, 143);
		contentPane.add(lblImg);
		
		JButton btnImg = new JButton("Añadir Imagen");
		btnImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relativa=insertarImagenes.generarRutaImg(relativa,lblImg);
			}
		});
		btnImg.setBounds(371, 310, 127, 35);
		contentPane.add(btnImg);
		
		txtTusDatos = new JTextField();
		txtTusDatos.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtTusDatos.setText("Tus datos");
		txtTusDatos.setBounds(192, 38, 117, 29);
		contentPane.add(txtTusDatos);
		txtTusDatos.setColumns(10); 
		txtTusDatos.setEditable(false);
		
		
		panel1.add(new JScrollPane());
		contentPane.add(panel1, BorderLayout.CENTER);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(36, 235, 308, 143);
		contentPane.add(scrollPane_1);
		String[] columna=new String[] {"Asignatura"};
		modeloAsig=new DefaultTableModel(columna,0) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int x,int y) {
				return false;
			}	
		};
		table = new JTable(modeloAsig);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				filaSeleccionada = table.rowAtPoint(evt.getPoint());
				rasAsig=OperacionesBD.extraerRAsAsig(conn, asignaturas.get(filaSeleccionada).getId());
			}
		});
		
		scrollPane_1.setViewportView(table);
		actualizarTablaAsig();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JButton btnConsultarNotas = new JButton("Consultar notas");
		btnConsultarNotas.setBounds(128, 389, 127, 23);
		contentPane.add(btnConsultarNotas);
		btnConsultarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filaSeleccionada==null) {
					JOptionPane.showMessageDialog(null, "Selecciona una asignatura");
				}else {
				NotasAlumno n=new NotasAlumno(asignaturas.get(filaSeleccionada).getId(),
						asignaturas.get(filaSeleccionada).getNombre(),dniAlumno,nombre, rasAsig, conn);
				//dispose();
				}
			}
		});
	}
	public static void actualizarTablaAsig() {
		modeloAsig.setRowCount(0);
		try {
			for (int i = 0; i < asignaturas.size(); i++) {
				String nombre = asignaturas.get(i).getNombre();

				Object[] data = { nombre };
				modeloAsig.addRow(data);
			}
		} catch (java.lang.NullPointerException e) {
		}

	}
}
