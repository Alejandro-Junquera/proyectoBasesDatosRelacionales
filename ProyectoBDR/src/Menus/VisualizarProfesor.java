package Menus;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Funciones.Asignatura;
import Funciones.JFrameDiseño;
import Funciones.OperacionesBD;
import Funciones.insertarImagenes;

public class VisualizarProfesor extends JFrame {

	private JPanel contentPane;
	private JTextField textDNI;
	private JTextField textNombre;
	private JTextField textApell;
	private JTextField textEmail;
	private JTextField textContr;
	private static ArrayList<Asignatura> asignaturasPropias;
	private static DefaultTableModel tablemodel;
	private JTable tableAsig;
	private String relativa;
	private int filaSeleccionada;
	
	public static void actualizarGrafico(ArrayList<Asignatura> actualizar,DefaultTableModel tablemodel) {
		tablemodel.setRowCount(0);
		try {
			for (int i = 0; i < actualizar.size(); i++) {
				String asignatura=actualizar.get(i).getNombre();

				Object[] data = { asignatura };
				tablemodel.addRow(data);
			}
		} catch (java.lang.NullPointerException e) {
		}

	}

	public VisualizarProfesor(String dni, String nombre, String apellidos, String email, String clave, String img,
			String asignatura, Connection conn) {
		asignaturasPropias = OperacionesBD.ExtraccionAsignaturasProf(dni, conn);
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

		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setForeground(new Color(64, 0, 64));
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDNI.setBounds(31, 129, 109, 25);
		contentPane.add(lblDNI);

		textDNI = new JTextField();
		textDNI.setEditable(false);
		textDNI.setColumns(10);
		textDNI.setBounds(138, 126, 253, 40);
		contentPane.add(textDNI);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(new Color(64, 0, 64));
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(31, 229, 109, 25);
		contentPane.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setEditable(false);
		textNombre.setColumns(10);
		textNombre.setBounds(138, 229, 253, 40);
		contentPane.add(textNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setForeground(new Color(64, 0, 64));
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(31, 331, 109, 25);
		contentPane.add(lblApellidos);

		textApell = new JTextField();
		textApell.setEditable(false);
		textApell.setColumns(10);
		textApell.setBounds(138, 331, 253, 40);
		contentPane.add(textApell);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(64, 0, 64));
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(31, 433, 109, 25);
		contentPane.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setEditable(false);
		textEmail.setColumns(10);
		textEmail.setBounds(138, 433, 253, 40);
		contentPane.add(textEmail);

		JLabel lblContr = new JLabel("Contraseña");
		lblContr.setForeground(new Color(64, 0, 64));
		lblContr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContr.setBounds(31, 536, 109, 25);
		contentPane.add(lblContr);

		textContr = new JTextField();
		textContr.setEditable(false);
		textContr.setColumns(10);
		textContr.setBounds(138, 524, 253, 40);
		contentPane.add(textContr);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminProfesor(conn);
				dispose();
			}
		});
		btnVolver.setBounds(436, 663, 121, 40);
		btnVolver.setBackground(JFrameDiseño.boton);
		contentPane.add(btnVolver);
		textDNI.setText(dni);
		textNombre.setText(nombre);
		textApell.setText(apellidos);
		textEmail.setText(email);
		textContr.setText(clave);
		relativa = img;
		lblImg.setIcon(insertarImagenes.ResizableImage(img, lblImg));
		JScrollPane scrollPane_AsigDisp = new JScrollPane();
		scrollPane_AsigDisp.setBounds(642, 366, 277, 195);
		contentPane.add(scrollPane_AsigDisp);

		String[] columnas = new String[] { "Asignaturas Propias" };
		tablemodel = new DefaultTableModel(columnas, 0);
		tableAsig = new JTable(tablemodel);

		tableAsig.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				filaSeleccionada = tableAsig.rowAtPoint(evt.getPoint());
			}
		});
		scrollPane_AsigDisp.setViewportView(tableAsig);
		actualizarGrafico(asignaturasPropias, tablemodel);

		setVisible(true);
	}

}
