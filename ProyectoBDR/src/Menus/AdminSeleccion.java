package Menus;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Funciones.JFrameDiseño;
import Funciones.insertarImagenes;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminSeleccion extends JFrame {

	private JPanel contentPane;

	public AdminSeleccion(Connection conn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 300);
		setTitle("Panel de administrador");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setIconImage(JFrameDiseño.logo.getImage());

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		/*JButton btnNewButton = new JButton("Administrar Alumnos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VistaAlumnos va= new VistaAlumnos();
				va.setVisible(true);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(143, 10, 250, 140);
		contentPane.add(btnNewButton);
		
		JButton btnProfesor = new JButton("Administrar Profesores");
		btnProfesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminProfesor prof=new AdminProfesor(conn);
				dispose();
			}
		});
		btnProfesor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnProfesor.setBounds(143, 173, 250, 140);
		contentPane.add(btnProfesor);
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new InicioSesion();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(143, 497, 250, 63);
		contentPane.add(btnNewButton_1);
		
		JButton btnAdministrarAsignaturas = new JButton("Administrar Asignaturas");
		btnAdministrarAsignaturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAsignatura AA=new AdminAsignatura(conn);
				AA.setVisible(true);
				dispose();
			}
		});
		btnAdministrarAsignaturas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdministrarAsignaturas.setBounds(143, 335, 250, 140);
		contentPane.add(btnAdministrarAsignaturas);*/
		
		JLabel lblAdminAlum = new JLabel("");
		lblAdminAlum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VistaAlumnos va= new VistaAlumnos();
				va.setVisible(true);
			}
		});
		lblAdminAlum.setBounds(10, 45, 100, 100);
		lblAdminAlum.setIcon(insertarImagenes.ResizableImage("Imagenes/Iconos/alumno.png", lblAdminAlum));
		lblAdminAlum.setToolTipText("Administrar Alumnos");
		contentPane.add(lblAdminAlum);
		
		JLabel lblAdminProf = new JLabel("");
		lblAdminProf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminProfesor prof=new AdminProfesor(conn);
				dispose();
			}
		});
		lblAdminProf.setBounds(220, 45, 100, 100);
		lblAdminProf.setIcon(insertarImagenes.ResizableImage("Imagenes/Iconos/profesor.png", lblAdminProf));
		lblAdminProf.setToolTipText("Administrar Profesores");
		contentPane.add(lblAdminProf);
		
		JLabel lblAdminAsig = new JLabel("");
		lblAdminAsig.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminAsignatura AA=new AdminAsignatura(conn);
				AA.setVisible(true);
				dispose();
			}
		});
		lblAdminAsig.setBounds(426, 45, 100, 100);
		lblAdminAsig.setIcon(insertarImagenes.ResizableImage("Imagenes/Iconos/libros.png", lblAdminAsig));
		lblAdminAsig.setToolTipText("Administrar Asignaturas");
		contentPane.add(lblAdminAsig);
		
		JLabel lblVolver = new JLabel("");
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new InicioSesion();
			}
		});
		lblVolver.setBounds(220, 203, 100, 50);
		lblVolver.setIcon(insertarImagenes.ResizableImage("Imagenes/Iconos/logout.png", lblVolver));
		lblVolver.setToolTipText("Volver");
		contentPane.add(lblVolver);
		setVisible(true);
	}
}
