package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Conexiones.Conexion;
import Funciones.OperacionesBD;
import Funciones.Profesor;
import Funciones.RA;
import javax.swing.JScrollPane;
import java.awt.Font;


public class EditarRAs extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private Object[] fila;
	private ArrayList<RA> rasAsig;
	private Integer filaSeleccionada;

	public EditarRAs(int idAsig,String nombreAsig, Connection conn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		rasAsig=OperacionesBD.extraerRAsAsig(conn, idAsig);
		
		Object[]columna={"ID","Nombre","Descripcion","Ponderacion"};
		model=new DefaultTableModel(columna,0) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int x,int y) {
				return false;
			}	
		};
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 50, 405, 155);
		contentPane.add(scrollPane);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		table.setModel(model);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				filaSeleccionada = table.rowAtPoint(evt.getPoint());
			}
		});
		
		JButton btnNewButton = new JButton("Cerrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.setLayout(null);
		btnNewButton.setBounds(371, 227, 76, 23);
		contentPane.add(btnNewButton);
		
		scrollPane.setViewportView(table);
		
		JLabel lblAsig = new JLabel("RAs de "+nombreAsig);
		lblAsig.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblAsig.setBounds(42, 11, 351, 28);
		contentPane.add(lblAsig);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActualizarRA AR = new ActualizarRA(conn,rasAsig.get(filaSeleccionada).getId(),
						rasAsig.get(filaSeleccionada).getNombre(),rasAsig.get(filaSeleccionada).getDescripcion(),
						Integer.valueOf(rasAsig.get(filaSeleccionada).getPonderacion()));
				AR.setVisible(true);
			}
		});
		btnActualizar.setBounds(246, 227, 106, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(filaSeleccionada==null) {
					JOptionPane.showMessageDialog(null, "Selecciona un RA");
				}else {
					Conexion con = new Conexion();
					OperacionesBD.borrarRA(rasAsig.get(filaSeleccionada).getId(), conn);
					rasAsig=OperacionesBD.extraerRAsAsig(conn, idAsig);
					llenarTabla(conn, rasAsig);
				}
				
			}
		});
		btnEliminar.setBounds(136, 227, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarRA IA = new InsertarRA(idAsig,nombreAsig,conn);
				IA.setVisible(true);
				dispose();
			}
		});
		btnInsertar.setBounds(37, 227, 89, 23);
		contentPane.add(btnInsertar);
		Profesor p=OperacionesBD.extraerProfesorAsignatura(conn, idAsig);
		llenarTabla(conn, rasAsig);
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
	private void llenarTabla(Connection conn, ArrayList<RA> rasAsig) {
		model.setRowCount(0);
		for (RA ra:rasAsig) {
			this.fila = new Object[4];
			try {
				fila[0]=ra.getId();
				fila[1]=ra.getNombre();
				fila[2]=ra.getDescripcion();
				fila[3]=ra.getPonderacion();
			}
			catch(NullPointerException te) {	
			}
		model.addRow(fila);
		}
		
	}
}

