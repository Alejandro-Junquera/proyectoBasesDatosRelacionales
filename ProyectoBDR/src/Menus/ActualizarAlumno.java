package Menus;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import Conexiones.Conexion;
import Funciones.Asignatura;
import Funciones.JFrameDiseño;
import Funciones.Matricula;
import Funciones.OperacionesBD;
import Funciones.insertarImagenes;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
 

public class ActualizarAlumno extends JFrame  {

	private JPanel contentPane;
	private JTextField textNombre,textContrasenia,textDNI,textApellidos,textTelefono,textFecha;
	private JLabel lblFoto,lblFechaNacimiento,lblTelefono,lblApellidos,lblDNI,lblContrasenia,lblNombre;
	private Image img;
	private JButton btnActualizar,btnAadirImagen;
	private String relativaFoto;
	private JCalendar calendar;
	private ArrayList<JCheckBox> checkboxes;
	private ArrayList<Asignatura> asignaturas=new ArrayList<Asignatura>();
	private ArrayList<Matricula> matriculas=new ArrayList<Matricula>();

	public ActualizarAlumno() {
		setTitle("Actualizar alumno");
		setBounds(100, 100, 1080, 561);
		contentPane = new JPanel();
		Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((pantallaTamano.width/2)-(this.getWidth()/2), (pantallaTamano.height/2)-(this.getHeight()/2));
		componentes();
	}
	
	public ActualizarAlumno(String dni, String nombre, String apellidos, String fecha,int tel ,String clave,String imagen, Connection conn) {
		Conexion conn1 = new Conexion();
		this.asignaturas=OperacionesBD.ExtraccionTodasAsignaturas(conn1.conectarMySQL());
		this.matriculas=OperacionesBD.ExtraerMatriculasAlumnoAsignatura(conn1.conectarMySQL());
		setTitle("Actualizar alumno");
		setBounds(100, 100, 1080, 561);
		setResizable(false);
		contentPane = new JPanel();
		Dimension pantallaTamano = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((pantallaTamano.width/2)-(this.getWidth()/2), (pantallaTamano.height/2)-(this.getHeight()/2));
		componentes();
		textDNI.setText(dni);
		textNombre.setText(nombre);
		textApellidos.setText(apellidos);
		textFecha.setText(fecha);
		textTelefono.setText(String.valueOf(tel));
		textContrasenia.setText(clave);
		relativaFoto=imagen;
		lblFoto.setText(imagen);
		this.img=getToolkit().getImage(imagen);
		this.img=this.img.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(),Image.SCALE_DEFAULT);
		lblFoto.setIcon(new ImageIcon(img));
		for(JCheckBox check : checkboxes) {
		    for(Matricula mat : matriculas) {
		    	if((mat.getDni_alu().equals(textDNI.getText()))&&(mat.getId_asi()==Integer.parseInt(check.getText().substring(check.getText().length()-1)))) {
		    		check.setSelected(true);
		    	}
		    }
		}
	}

	private void componentes() {
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(JFrameDiseño.fondoAdmin);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(26, 84, 111, 25);
		contentPane.add(lblNombre);
		
		lblContrasenia = new JLabel("Contraseña");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasenia.setBounds(26, 406, 111, 25);
		contentPane.add(lblContrasenia);
		
		textNombre = new JTextField();
		textNombre.setBounds(152, 69, 253, 40);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textContrasenia = new JTextField();
		textContrasenia.setBounds(152, 391, 253, 40);
		contentPane.add(textContrasenia);
		textContrasenia.setColumns(10);
		
		lblDNI = new JLabel("DNI");
		lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDNI.setBounds(32, 25, 34, 25);
		contentPane.add(lblDNI);
		
		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(152, 10, 253, 40);
		textDNI.setEditable(false);
		contentPane.add(textDNI);
		
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combrobarCamposVacios(textNombre, textApellidos, textTelefono,textContrasenia)) {
					try {
						Conexion conn = new Conexion();
						for(JCheckBox check : checkboxes) {
						    for(Matricula mat : matriculas) {
						    	if((mat.getDni_alu().equals(textDNI.getText()))&&(mat.getId_asi()==Integer.parseInt(check.getText().substring(check.getText().length()-1)))) {
						    		if(check.isSelected()){
						    			OperacionesBD.MatricularAlumnoAsignatura(conn.conectarMySQL(),textDNI.getText(),Integer.valueOf(check.getText().substring(check.getText().length()-1, check.getText().length())));
						    		}
						    		else {
						    			OperacionesBD.BorrarMatriculaAlumno(textDNI.getText(),Integer.valueOf(check.getText().substring(check.getText().length()-1, check.getText().length())),conn.conectarMySQL());
						    		}
						    	}
						    	else {
						    		if(check.isSelected()){
						    			OperacionesBD.MatricularAlumnoAsignatura(conn.conectarMySQL(),textDNI.getText(),Integer.valueOf(check.getText().substring(check.getText().length()-1, check.getText().length())));
						    		}
						    	}
						    }
						    if(check.isSelected()){
				    			OperacionesBD.MatricularAlumnoAsignatura(conn.conectarMySQL(),textDNI.getText(),Integer.valueOf(check.getText().substring(check.getText().length()-1, check.getText().length())));
				    		}
						}
						
						
						
						Funciones.OperacionesBD.actualizarAlumno(textDNI.getText(), textNombre.getText(),textApellidos.getText(),textFecha.getText(),Integer.parseInt(textTelefono.getText()),textContrasenia.getText(),relativaFoto,conn.conectarMySQL());
						JOptionPane.showMessageDialog(null, "Alumno actualizado");
						dispose();
						VistaAlumnos va= new VistaAlumnos();
						va.setVisible(true);
					}catch(NumberFormatException nfe1) {
						JOptionPane.showMessageDialog(null, "El campo teléfono solo puede contener números");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, "No puede haber campos vacios");
				}
				
			}
				
			
		});
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnActualizar.setBounds(206, 456, 180, 40);
		contentPane.add(btnActualizar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				VistaAlumnos va = new VistaAlumnos();
				va.setVisible(true);
			}
		});
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(679, 456, 180, 40);
		contentPane.add(btnVolver);
		
		lblFoto = new JLabel("");
		lblFoto.setBounds(891, 21, 152, 190);
		contentPane.add(lblFoto);
		
		btnAadirImagen = new JButton("Añadir Imagen");
		btnAadirImagen.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				insertarImagenes ii = new insertarImagenes();
				relativaFoto=ii.generarRutaImg(relativaFoto, lblFoto);				
			}
		});
		btnAadirImagen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAadirImagen.setBounds(891, 221, 152, 40);
		contentPane.add(btnAadirImagen);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(26, 145, 111, 25);
		contentPane.add(lblApellidos);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(152, 130, 253, 40);
		contentPane.add(textApellidos);
		
		lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTelefono.setBounds(26, 209, 62, 25);
		contentPane.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(152, 194, 253, 40);
		contentPane.add(textTelefono);
		
		lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFechaNacimiento.setBounds(26, 315, 111, 25);
		contentPane.add(lblFechaNacimiento);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(360, 300, 96, 40);
		textFecha.setEditable(false);
		contentPane.add(textFecha);
		
		
		calendar=new JCalendar();
		this.calendar.getCalendar().getTime();
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getOldValue()!=null) {
					SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
					textFecha.setText(ff.format(calendar.getCalendar().getTime()));
				}
			}
		});
		calendar.setWeekOfYearVisible(false);
		calendar.setBounds(152, 244, 199, 137);
		contentPane.add(calendar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(566, 105, 269, 249);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		checkboxes = new ArrayList<>();

		for(Asignatura asig : asignaturas) {
		    JCheckBox box = new JCheckBox(asig.getNombre()+"_ID:"+asig.getId());
		    checkboxes.add(box);
		    panel.add(box);
		}
		
		JLabel lblActuAsignaturas = new JLabel("Actualizar asignaturas");
		lblActuAsignaturas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblActuAsignaturas.setBounds(624, 69, 149, 25);
		contentPane.add(lblActuAsignaturas);
		
		
		

		
	}
	public boolean combrobarCamposVacios(JTextField nombre,JTextField apellidos,JTextField telefono,JTextField contrasenia) {
		if(!nombre.getText().isEmpty() && !apellidos.getText().isEmpty() && !telefono.getText().isEmpty()&& !contrasenia.getText().isEmpty()) {
			return true;
		}else {
			return false;
		}
	}

}