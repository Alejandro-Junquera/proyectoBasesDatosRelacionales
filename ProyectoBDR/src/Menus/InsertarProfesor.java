package Menus;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.management.modelmbean.ModelMBeanAttributeInfo;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import Funciones.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InsertarProfesor extends JFrame {

	private JPanel contentPane;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApell;
	private JTextField textEmail;
	private JTextField textContr;
	private static ArrayList<Asignatura> asignaturasLibres;
	public static ArrayList<Asignatura> asignaturaAsig;
	private String relativa = ".\\Imagenes\\Fotos\\defecto.jfif";
	private JTable tableAsig;
	private JTable tableAsigEli;
	private static DefaultTableModel tablemodel;
	private static DefaultTableModel tablemodel2;
	private int filaSeleccionada;
	private JButton Aplicar, Aplicar2;

	public static void actualizarGrafico(ArrayList<Asignatura> actualizar, DefaultTableModel tablemodel) {
		tablemodel.setRowCount(0);
		try {
			for (int i = 0; i < actualizar.size(); i++) {
				String asignatura = actualizar.get(i).getNombre();

				Object[] data = { asignatura };
				tablemodel.addRow(data);
			}
		} catch (java.lang.NullPointerException e) {
		}

	}

	public void desactivarBoton(ArrayList<Asignatura> asignaturas, JButton boton) {
		if (asignaturas.isEmpty()) {
			boton.setEnabled(false);
		} else {
			boton.setEnabled(true);
		}
	}

	public boolean combrobarCamposVacios(JTextField textDNI, JTextField textNombre, JTextField textApell,
			JTextField textEmail, JTextField textContr) {
		if (!textDNI.getText().isEmpty() && !textNombre.getText().isEmpty() && !textApell.getText().isEmpty()
				&& !textEmail.getText().isEmpty() && !textContr.getText().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public InsertarProfesor(Connection conn) {
		asignaturasLibres = OperacionesBD.ExtraccionAsignaturas(conn);
		asignaturaAsig = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(JFrameDiseño.fondoAdmin);

		JLabel lblImg = new JLabel("Insertar Imagen");
		lblImg.setBounds(659, 38, 232, 210);
		contentPane.add(lblImg);

		JButton btnImg = new JButton("Añadir Imagen");
		btnImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relativa = insertarImagenes.generarRutaImg(relativa, lblImg);
			}
		});
		btnImg.setBounds(659, 262, 222, 35);
		contentPane.add(btnImg);

		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setForeground(new Color(64, 0, 64));
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDNI.setBounds(31, 129, 109, 25);
		contentPane.add(lblDNI);

		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(138, 126, 253, 40);
		contentPane.add(textDNI);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(64, 0, 64));
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(31, 229, 109, 25);
		contentPane.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(138, 229, 253, 40);
		contentPane.add(textNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(new Color(64, 0, 64));
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(31, 331, 109, 25);
		contentPane.add(lblApellidos);

		textApell = new JTextField();
		textApell.setColumns(10);
		textApell.setBounds(138, 331, 253, 40);
		contentPane.add(textApell);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(64, 0, 64));
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(31, 433, 109, 25);
		contentPane.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(138, 433, 253, 40);
		contentPane.add(textEmail);

		JLabel lblContr = new JLabel("Contraseña");
		lblContr.setForeground(new Color(64, 0, 64));
		lblContr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContr.setBounds(31, 536, 109, 25);
		contentPane.add(lblContr);

		textContr = new JTextField();
		textContr.setColumns(10);
		textContr.setBounds(138, 524, 253, 40);
		contentPane.add(textContr);

		JButton btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (combrobarCamposVacios(textDNI, textNombre, textApell, textEmail, textContr)) {
					if (asignaturaAsig.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se puede añadir profesor sin asignaturas");
					} else {
						if (OperacionesBD.existeProf(conn, textDNI.getText())) {
							JOptionPane.showMessageDialog(null, "El campo DNI ya existe en la base de datos");
						} else {
							OperacionesBD.insertarProfesor(textDNI.getText(), textNombre.getText(), textApell.getText(),
									textEmail.getText(), textContr.getText(), relativa, asignaturaAsig, conn);
							new AdminProfesor(conn);
							dispose();
							JOptionPane.showMessageDialog(null, "Profesor añadido correctamente");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "No puede haber campos vacios");
				}

			}
		});
		btnAadir.setBounds(258, 663, 121, 40);
		btnAadir.setBackground(JFrameDiseño.boton);
		contentPane.add(btnAadir);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminProfesor(conn);
				dispose();
			}
		});
		btnVolver.setBounds(600, 663, 121, 40);
		btnVolver.setBackground(JFrameDiseño.boton);
		contentPane.add(btnVolver);

		JScrollPane scrollPane_AsigDisp = new JScrollPane();
		scrollPane_AsigDisp.setBounds(424, 351, 277, 195);
		contentPane.add(scrollPane_AsigDisp);

		String[] columnas = new String[] { "Asignaturas disponibles" };
		tablemodel = new DefaultTableModel(columnas, 0);
		tableAsig = new JTable(tablemodel);

		tableAsig.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				filaSeleccionada = tableAsig.rowAtPoint(evt.getPoint());
			}
		});
		scrollPane_AsigDisp.setViewportView(tableAsig);
		actualizarGrafico(asignaturasLibres, tablemodel);

		JScrollPane scrollPane_AsigElim = new JScrollPane();
		scrollPane_AsigElim.setBounds(711, 351, 290, 195);
		contentPane.add(scrollPane_AsigElim);

		String[] columnas2 = new String[] { "Asignaturas elegidas" };
		tablemodel2 = new DefaultTableModel(columnas2, 0);
		tableAsigEli = new JTable(tablemodel2);
		tableAsigEli.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				filaSeleccionada = tableAsigEli.rowAtPoint(evt.getPoint());
			}
		});
		scrollPane_AsigElim.setViewportView(tableAsigEli);

		Aplicar = new JButton("Aplicar");
		Aplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asignaturaAsig.add(asignaturasLibres.get(filaSeleccionada));
				asignaturasLibres.remove(filaSeleccionada);
				actualizarGrafico(asignaturasLibres, tablemodel);
				actualizarGrafico(asignaturaAsig, tablemodel2);
				desactivarBoton(asignaturasLibres, Aplicar);
				desactivarBoton(asignaturaAsig, Aplicar2);
			}
		});
		Aplicar.setToolTipText("");
		Aplicar.setBounds(520, 562, 85, 21);
		Aplicar.setBackground(JFrameDiseño.boton);
		contentPane.add(Aplicar);

		Aplicar2 = new JButton("Aplicar");
		Aplicar2.setToolTipText("");
		Aplicar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asignaturasLibres.add(asignaturaAsig.get(filaSeleccionada));
				asignaturaAsig.remove(filaSeleccionada);
				actualizarGrafico(asignaturaAsig, tablemodel2);
				actualizarGrafico(asignaturasLibres, tablemodel);
				desactivarBoton(asignaturaAsig, Aplicar2);
				desactivarBoton(asignaturasLibres, Aplicar);
			}
		});
		Aplicar2.setBounds(825, 562, 85, 21);
		contentPane.add(Aplicar2);
		desactivarBoton(asignaturasLibres, Aplicar);
		desactivarBoton(asignaturaAsig, Aplicar2);
		Aplicar2.setBackground(JFrameDiseño.boton);
		setVisible(true);
	}
}
