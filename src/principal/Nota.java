package principal;

import javax.swing.*;

import Clases.Utilidades;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.BorderLayout;
import java.awt.Cursor;
public class Nota extends JFrame{
	public Nota() {
		setBounds(300,300,350,200);
		setUndecorated(true);
		setLayout(null);
		
		//--------------- Panel Menu GUI--------------------------
		panelBarra = new JPanel();
		panelBarra.setLayout(new BorderLayout());
		panelBarra.setBounds(0, 0, this.getWidth(), 40);
		
		JLabel labelFijar = new JLabel();
		labelFijar.setIcon(Utilidades.agregaIcono("alfiler"));
		JLabel labelColor = new JLabel();
		labelColor.setIcon(Utilidades.agregaIcono("gota"));
		JLabel labelTitulo = new JLabel();
		labelTitulo.setIcon(Utilidades.agregaIcono("lapiz"));
		JLabel labelGuardar = new JLabel();
		labelGuardar.setIcon(Utilidades.agregaIcono("guardar"));
		JLabel labelTipoTexto = new JLabel();
		labelTipoTexto.setIcon(Utilidades.agregaIcono("a"));
		JLabel labelMas = new JLabel();
		labelMas.setIcon(Utilidades.agregaIcono("mas"));
		JLabel labelCerrar = new JLabel();
		labelCerrar.setIcon(Utilidades.agregaIcono("eliminar"));
		
		//Texto Emergente
		labelFijar.setToolTipText("Fijar nota");
		labelColor.setToolTipText("Color de nota");
		labelTitulo.setToolTipText("Renombrar");
		labelGuardar.setToolTipText("Guardar");
		labelMas.setToolTipText("Nueva Nota");
		labelCerrar.setToolTipText("Borrar la nota");
		
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.add(labelFijar);
		panelIzquierdo.add(labelColor);
		panelIzquierdo.add(labelTitulo);
		
		
		JPanel panelDerecho=new JPanel();
		panelDerecho.add(labelGuardar);
		panelDerecho.add(labelTipoTexto);
		panelDerecho.add(labelMas);
		panelDerecho.add(labelCerrar);
		
		panelBarra.add(panelIzquierdo, BorderLayout.WEST);
		panelBarra.add(panelDerecho, BorderLayout.EAST);
		//-----------------------------------------------------
		
		
		
		//-------------- Panel de texto GUI------------------------
		panelTexto = new JPanel();
		panelTexto.setLayout(new BorderLayout());
		panelTexto.setBounds(0,40, this.getWidth(), this.getHeight()-40);
		textoArea = new JTextArea();
		scrollPane = new JScrollPane(textoArea);
		
		panelTexto.add(scrollPane, BorderLayout.CENTER);
		//------------------------------------------------------
		
		//--------------- Panel Menu EVENTOS--------------------------
		panelBarra.addMouseMotionListener(new MouseMotionAdapter() {
			//cada vez que precionemos y arrastremos en el panel menu entonces todo el marco o JFrame se mueve
			public void mouseDragged(MouseEvent e) {
				setCursor(new Cursor(Cursor.MOVE_CURSOR)); // mientras se arrastra el cursor cambia
				Point ubicacion = MouseInfo.getPointerInfo().getLocation(); // Obtenemos la pusicion del puntero
				setLocation(ubicacion.x - x, ubicacion.y - y); // posiciona el Marco(JFrame) hacia donde coloquemos el puntero
			}
		});
		
		panelBarra.addMouseListener(new MouseAdapter() {
			//al dar un click en el panel menu, obtenemos la coordenadas del click
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				// cuando se suelte si es que se esta arrastrando se regresa al cursor normal
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		//-------------------------------------------------------------
		add(panelBarra);
		add(panelTexto);
		
	}
	
	private JPanel panelTexto, panelBarra;
	
	private JTextArea textoArea;
    private JScrollPane scrollPane;

	
	private int x;
	private int y;
	public static boolean estadoAlfiler = false;
}
