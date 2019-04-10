package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class ButtonFlatDesing extends JFrame {
	
	private static ParkingLot instanciaControlador; 
	
	private JPanel contentPane;
	private JTextField nrosDisp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					instanciaControlador = new ParkingLotImpl();
					ButtonFlatDesing frame = new ButtonFlatDesing();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ButtonFlatDesing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIngreso = new JButton("Ingreso");
		btnIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Ingreso detectado");				
				//Llamando al controlador
				instanciaControlador.ingresoDetectado();
				nrosDisp.setText(String.valueOf(instanciaControlador.EspaciosDisponibles()));
				
			}
		});
		btnIngreso.setBounds(32, 56, 89, 23);
		contentPane.add(btnIngreso);
		
		JButton btnEgreso = new JButton("Egreso");
		btnEgreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(null, "Egreso detectado");
				int codigoEgreso=instanciaControlador.getRandomCode(); //Obtengo el codigo de una posicion de los autos aparcados
				if(codigoEgreso>0){ //Si el codigo existe
					instanciaControlador.egresoDetectado(codigoEgreso); //Marco el egreso
				}
				else{ //Se detecto un egreso cuando no habia autos estacionados
					JOptionPane.showMessageDialog(null, "Se a detectado un egreso falso");
				}
				
				nrosDisp.setText(String.valueOf(instanciaControlador.EspaciosDisponibles()));
			}
		});
		btnEgreso.setBounds(312, 56, 89, 23);
		contentPane.add(btnEgreso);
		
		JButton btnNewButton = new JButton("Marcar las 00");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Forzando estado 00hs");
				instanciaControlador.recorrerAutoPorEstadia();
				nrosDisp.setText(String.valueOf(instanciaControlador.EspaciosDisponibles()));
			}
		});
		btnNewButton.setBounds(170, 136, 107, 23);
		contentPane.add(btnNewButton);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 424, 21);
		contentPane.add(menuBar);
		
		JMenu mnSalir = new JMenu("Salir");
		menuBar.add(mnSalir);
		
		JMenuItem mntmSalirDelSistema = new JMenuItem("Salir del sistema");
		mntmSalirDelSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnSalir.add(mntmSalirDelSistema);
		
		nrosDisp = new JTextField();
		nrosDisp.setText(String.valueOf(instanciaControlador.EspaciosDisponibles()));
		nrosDisp.setEditable(false);
		nrosDisp.setBounds(185, 57, 53, 20);
		contentPane.add(nrosDisp);
		nrosDisp.setColumns(10);
	}
}
