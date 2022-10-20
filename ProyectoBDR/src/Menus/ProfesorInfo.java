package Menus;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Funciones.Asignatura;
import Funciones.OperacionesBD;
import Funciones.RA;
import Funciones.insertarImagenes;

public class ProfesorInfo extends JFrame {

	private JPanel contentPane;
	static ArrayList<Asignatura> asignaturas;
	private JTable tablaProf;
	private DefaultTableModel modeloProf;
	private String dni;
	private String nombre;
	private String apellidos;
	private String email;
	private String relativa;
	private JTextField txtTusDatos;
	private static DefaultTableModel modeloAsig;
	private JTable table;
	private Integer filaSeleccionada;
	private ArrayList<RA> rasAsig;
	

	
	public ProfesorInfo(Connection conn, String dniProf) {
		asignaturas=OperacionesBD.ExtraccionAsignaturasProf(dniProf, conn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel1 = new JPanel();
		
		try {
			modeloProf= new DefaultTableModel() {
				
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int x,int y) {
					return false;	
				}
			};
			String[] columnas = { "DNI", "Nombre", "Apellidos", "Email"};
			modeloProf.setColumnIdentifiers(columnas);
			
			String sql="select dni,nombre,apellidos,email from profesor where dni=?;";
			PreparedStatement statement=conn.prepareStatement(sql);
			statement.setString(1, dniProf);
			ResultSet rs=statement.executeQuery();
			
			while(rs.next()) {
				dni = rs.getString("dni");
				nombre = rs.getString("nombre");
				apellidos = String.valueOf(rs.getString("apellidos"));
				email = String.valueOf(rs.getString("email"));
			}
			Object[] datos = {dni, nombre, apellidos, email};
			modeloProf.addRow(datos);
			
		} catch (Exception ex) {
		}
		
		setTitle("Info. de "+nombre);
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
		
		tablaProf = new JTable();
		tablaProf.setEnabled(true);
		tablaProf.setModel(modeloProf);
		JScrollPane scrollPane = new JScrollPane(tablaProf);
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
		JButton btnConsultarAlumnos = new JButton("Alumnos");
		btnConsultarAlumnos.setBounds(82, 389, 100, 23);
		contentPane.add(btnConsultarAlumnos);
		
		JButton btnEditarRA = new JButton("Editar RAs");
		btnEditarRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarRAs ER = new EditarRAs(asignaturas.get(filaSeleccionada).getId(),asignaturas.get(filaSeleccionada).getNombre(), conn);
				
				ER.setVisible(true);
			}
		});
		btnEditarRA.setBounds(212, 389, 107, 23);
		contentPane.add(btnEditarRA);
		btnConsultarAlumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filaSeleccionada==null) {
					JOptionPane.showMessageDialog(null, "Selecciona una asignatura");
				}else {
				AlumnosMatriculados am=new AlumnosMatriculados(conn,asignaturas.get(filaSeleccionada)
						.getId() ,asignaturas.get(filaSeleccionada).getNombre(), dniProf, rasAsig);
				am.setVisible(true);
				dispose();
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
