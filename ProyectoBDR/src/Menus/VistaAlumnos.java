package Menus;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Conexiones.Conexion;
import Funciones.Alumno;
import Funciones.JFrameDiseño;
import Funciones.OperacionesBD;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class VistaAlumnos extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panel;
	private JTable tablaAlumnos;
	private DefaultTableModel model;
	private JButton btnVolverAdmin,btnInsertarAlumno,btnActualizarAlumno,btnEliminarAlumno,btnMostrarNotas;
	private JLabel lblTitulo,lblFotoAlumno;
	private Object[] fila;
	private ArrayList<Alumno> alumnos= new ArrayList<Alumno>();
	private JScrollPane scrollPane;
	private int filaSeleccionada;
	private Image img;
	
	
	public VistaAlumnos() {
		setTitle("Administrador de alumnos");
		setBounds(100, 100, 769, 536);
		setResizable(false);
		Conexion conn = new Conexion();
		this.alumnos=OperacionesBD.ExtraccionTablaAlumno(conn.conectarMySQL());
		Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((pantallaTamano.width/2)-(this.getWidth()/2), (pantallaTamano.height/2)-(this.getHeight()/2));
		componentes();
	}
	
	void llenarTabla() {
		tablaAlumnos.getColumnModel().getColumn(6).setMaxWidth(0);
		tablaAlumnos.getColumnModel().getColumn(6).setMinWidth(0);
		tablaAlumnos.getColumnModel().getColumn(6).setPreferredWidth(0);
		Conexion conn = new Conexion();
		this.alumnos=OperacionesBD.ExtraccionTablaAlumno(conn.conectarMySQL());
		model.setRowCount(0);
		for (Alumno alum:this.alumnos) {
			this.fila = new Object[7];
			try {
				fila[0]=alum.getDNI();
				fila[1]=alum.getNombre();
				fila[2]=alum.getApellidos();
				fila[3]=alum.getFechaNac();
				fila[4]=alum.getTlf();
				fila[5]=alum.getClave();
				fila[6]=alum.getImg();			
			}
			catch(NullPointerException te) {	
			}
		model.addRow(fila);
		}
		
	}

	void componentes() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel = new JPanel();
		panel.setBackground(JFrameDiseño.fondoAdmin);
		panel.setBounds(0, 0, 755, 499);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnVolverAdmin = new JButton("Volver");
		btnVolverAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Conexion conn = new Conexion();
				AdminSeleccion as = new AdminSeleccion(conn.conectarMySQL());
				as.setVisible(true);
			}
		});
		btnVolverAdmin.setBounds(48, 30, 85, 21);
		panel.add(btnVolverAdmin);
		
		lblTitulo = new JLabel("Administrar alumnos");
		lblTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTitulo.setBounds(289, 58, 178, 48);
		panel.add(lblTitulo);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 735, 150);
		
		panel.add(scrollPane);
		
		tablaAlumnos = new JTable();
		tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				filaSeleccionada = tablaAlumnos.rowAtPoint(evt.getPoint());
				lblFotoAlumno.setText(String.valueOf(alumnos.get(filaSeleccionada).getImg()));
				img=getToolkit().getImage(String.valueOf(lblFotoAlumno.getText()));
				img=img.getScaledInstance(lblFotoAlumno.getWidth(), lblFotoAlumno.getHeight(),Image.SCALE_DEFAULT);
				lblFotoAlumno.setText(alumnos.get(filaSeleccionada).getImg());
				lblFotoAlumno.setIcon(new ImageIcon(img));
			}
		});
		model= new DefaultTableModel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int x,int y) {
				return false;	
			}
		};
		
		Object[]columna={"Dni","Nombre","Apellidos","Fecha de nacimiento","Telefono","Contraseña","Imagen"};
		model.setColumnIdentifiers(columna);
		tablaAlumnos.setModel(model);
		scrollPane.setViewportView(tablaAlumnos);
		tablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		llenarTabla();
		
		btnInsertarAlumno = new JButton("Insertar");
		btnInsertarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InsertarAlumno ia = new InsertarAlumno();
				ia.setVisible(true);
			}
		});
		btnInsertarAlumno.setBounds(67, 422, 121, 40);
		panel.add(btnInsertarAlumno);
		
		btnActualizarAlumno = new JButton("Actualizar");
		btnActualizarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Conexion conn = new Conexion();
				ActualizarAlumno aa=new ActualizarAlumno(alumnos.get(filaSeleccionada).getDNI(),alumnos.get(filaSeleccionada).getNombre(),alumnos.get(filaSeleccionada).getApellidos(),alumnos.get(filaSeleccionada).getFechaNac(),alumnos.get(filaSeleccionada).getTlf(),alumnos.get(filaSeleccionada).getClave(),alumnos.get(filaSeleccionada).getImg(),conn.conectarMySQL());
				aa.setVisible(true);
			}
		});
		btnActualizarAlumno.setBounds(224, 422, 121, 40);
		panel.add(btnActualizarAlumno);
		
		btnEliminarAlumno = new JButton("Eliminar");
		btnEliminarAlumno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion conn = new Conexion();
				int op=JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar el alumno seleccionado?");
				if(op==0) {
				
				File eliminar=new File(alumnos.get(filaSeleccionada).getImg());
				
				try {
					if(!alumnos.get(filaSeleccionada).getImg().contains("defecto")) {
						Files.delete(Paths.get(eliminar.getPath()));
					}
					OperacionesBD.BorrarAlumno(alumnos.get(filaSeleccionada).getDNI(), conn.conectarMySQL());
					alumnos=OperacionesBD.ExtraccionTablaAlumno(conn.conectarMySQL());
					
				} catch (IOException e1) {
					
				}catch(java.lang.IndexOutOfBoundsException e2) {
					
				}
				limpiarFoto();
				llenarTabla();
				}
			}
		});
		btnEliminarAlumno.setBounds(404, 422, 121, 40);
		panel.add(btnEliminarAlumno);
		
		btnMostrarNotas = new JButton("Mostrar notas");
		btnMostrarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMostrarNotas.setBounds(574, 422, 121, 40);
		panel.add(btnMostrarNotas);
		
		lblFotoAlumno = new JLabel("");
		lblFotoAlumno.setBounds(289, 278, 128, 119);
		panel.add(lblFotoAlumno);
	}
	void limpiarFoto() {
		lblFotoAlumno.setText("");
		lblFotoAlumno.setIcon(null);
		tablaAlumnos.clearSelection();
	}
}
