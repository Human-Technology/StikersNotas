package principal;

import javax.swing.*;

import Clases.Utilidades;
import data.Recolector;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;

public class Nota extends JFrame{
	public Nota(int n, int n2, boolean inic) {
		recolector = new Recolector();

		if(n2==0) {
			contador=1;
			numeroNota=contador;

		}
		else {
			contador = n2;
			numeroNota = n;
		}
		panelMenu = new JPanel();
		panelText = new JPanel();
		textTitulo = new JTextField();
		labelFijar = new JLabel();
		labelColor = new JLabel();
		labelTitulo = new JLabel();
		labelGuardar = new JLabel();
		labelTipoTexto = new JLabel();
		labelMas = new JLabel();
		labelCerrar = new JLabel();
		textoArea =  new JTextArea();
		scrollPane = new JScrollPane(textoArea);
		popupFont = new JPopupMenu();
		popupColores = new JPopupMenu();
		estado = false;
		estadoTextTitulo = false;
		estadoBOLD = false;
		estadoITALIC = false;
		
		labelFijar.setIcon(Utilidades.agregaIcono("alfiler"));
		labelColor.setIcon(Utilidades.agregaIcono("gota"));
		labelTitulo.setIcon(Utilidades.agregaIcono("lapiz"));
		labelGuardar.setIcon(Utilidades.agregaIcono("guardar"));
		labelTipoTexto.setIcon(Utilidades.agregaIcono("a"));
		labelMas.setIcon(Utilidades.agregaIcono("mas"));
		labelCerrar.setIcon(Utilidades.agregaIcono("eliminar"));
		setLayout(null);
		setUndecorated(true);
		setResizable(false);
		setBounds(300, 300, 350, 200);
		
		panelMenu.setLayout(null);
		panelText.setLayout(new BorderLayout());
		
		panelMenu.setBounds(0, 0, this.getWidth(), 40);
		panelMenu.setBackground(Color.CYAN);
		panelText.setBounds(0, 40, this.getWidth(), this.getHeight()-40);
		add(panelMenu);
		add(panelText);
						
		textTitulo.setBounds(70, 6, 120, 20);
		labelFijar.setBounds(10, 6, 20, 20);
		labelColor.setBounds(40, 6, 20, 20);
		labelGuardar.setBounds(this.getWidth()-120, 6, 20, 20);
		labelTipoTexto.setBounds(this.getWidth()-90, 6, 20, 20);
		labelMas.setBounds(this.getWidth()-60, 6, 20, 20);
		labelCerrar.setBounds(this.getWidth()-30, 6, 20, 20);
		labelTitulo.setBounds(70, 6, 20, 20);
		textoArea.setLineWrap(true);
		
		textTitulo.setVisible(estadoTextTitulo);
		
		
		panelMenu.setBackground(new Color(227, 255, 0));
		panelText.setBackground(new Color(255, 244, 161));
		textoArea.setBackground(new Color(255, 244, 161));
		
		panelMenu.add(textTitulo);
		panelMenu.add(labelFijar);
		panelMenu.add(labelColor);
		panelMenu.add(labelTitulo);
		panelMenu.add(labelGuardar);
		panelMenu.add(labelTipoTexto);
		panelMenu.add(labelMas);
		panelMenu.add(labelCerrar);
		panelText.add(scrollPane);
		
		//Texto Emergente
		labelFijar.setToolTipText("Fijar nota");
		labelColor.setToolTipText("Color de nota");
		labelTitulo.setToolTipText("Renombrar");
		labelGuardar.setText("Guardar");
		labelMas.setToolTipText("Nueva Nota");
		labelCerrar.setToolTipText("Borrar la nota");
		
		
		//Agregamos texto si existen
		if(inic == false) {
			if(recolector.existeArchivo(numeroNota)) {
				textTitulo.setText(recolector.getTitle(numeroNota));
				textoArea.setText(recolector.getText(numeroNota));
				guardadoUna = recolector.getGuardadoUna(numeroNota);
			}

		}
		
		
		labelTipoTexto.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				labelTipoTexto.setIcon(Utilidades.agregaIcono("aseleccion"));
			}
			public void mouseExited(MouseEvent e) {
				labelTipoTexto.setIcon(Utilidades.agregaIcono("a"));
			}
			
			public void mousePressed(MouseEvent e) {
				popupFont.show(labelTipoTexto, labelTipoTexto.getBounds().x - 275, labelTipoTexto.getBounds().y + labelTipoTexto.getBounds().height);
			}
		});
		
		labelGuardar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				labelGuardar.setIcon(Utilidades.agregaIcono("guardarSeleccion"));
			}
			public void mouseExited(MouseEvent e) {
				labelGuardar.setIcon(Utilidades.agregaIcono("guardar"));
			}
			
			public void mousePressed(MouseEvent e) {
				if(estado) {
					setAlwaysOnTop(!estado);
				}
				String texto = textoArea.getText();
				try {
					recolector.crearArchivo(numeroNota); //generamos un archivo si ya existe se deja
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(numNotas) {
					numerosNotas +=numeroNota;
					recolector.setConfig(contador, numerosNotas);
					numNotas=false;
				}
				else {
					recolector.setConfig(contador, numerosNotas);
				}
				
				int respuesta= JOptionPane.showConfirmDialog(null, "Nota Guardada, ¿Desea Salir?", " la nota", JOptionPane.YES_NO_OPTION);
				
				if(respuesta == JOptionPane.YES_OPTION) {
					guardadoUna = "true";
					recolector.setText("text", texto, "contador", numeroNota, "guardado", guardadoUna,"title", textTitulo.getText());
					setVisible(false);
					dispose();
				}
				else {
					if(estado) {
						setAlwaysOnTop(true);
					}
					guardadoUna = "true";
					//System.out.println(numeroNota +texto);
					recolector.setText("text", texto, "contador", numeroNota, "guardado", guardadoUna, "title", textTitulo.getText());

				}

				

			}
		});
		
		labelMas.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				labelMas.setIcon(Utilidades.agregaIcono("masseleccion"));
			}
			public void mouseExited(MouseEvent e) {
				labelMas.setIcon(Utilidades.agregaIcono("mas"));
			}
			
			public void mousePressed(MouseEvent e) {		
				contador++;
				Nota newNota = new Nota(contador, contador, inic);
				newNota.setVisible(true);
				//System.out.println("Ventana " + contador);
			}
			
		});
		
		labelCerrar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				labelCerrar.setIcon(Utilidades.agregaIcono("eliminarseleccion"));
			}
			public void mouseExited(MouseEvent e) {
				labelCerrar.setIcon(Utilidades.agregaIcono("eliminar"));
			}
			
			public void mousePressed(MouseEvent e) {
				if(estado) {
					setAlwaysOnTop(!estado);
				}
				int respuesta= JOptionPane.showConfirmDialog(null, "Seguro que quiere eliminar esta nota", "Borrar la nota", JOptionPane.YES_NO_OPTION);
				
				if(respuesta == JOptionPane.YES_OPTION) {
	
					if(guardadoUna.equals("true")) {
						String numerosN = recolector.getNumerosNotas();
						int mayor = Character.getNumericValue(numerosN.charAt(0));
						
						for(int i=1; i<numerosN.length();i++) {
							if(Character.getNumericValue(numerosN.charAt(i))>mayor) {
								mayor =Character.getNumericValue(numerosN.charAt(i));
							}
						}
						if(numeroNota == mayor) {
							if(numerosN.length()==1) {
								contador = 0;
							}
							else {
								contador--;
							}
							
							try {
								recolector.eliminarArchivo(numeroNota);
								recolector.eliminaNumeroNotas(numeroNota, contador);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						else {
							try {
								//System.out.println(numeroNota);
								recolector.eliminarArchivo(numeroNota);
								recolector.eliminaNumeroNotas(numeroNota, contador);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					else {
						contador--;
						recolector.setConfig(contador, numerosNotas);
					}
		
					
					setVisible(false);
					dispose(); 
					
					
				}
				else {
					if(estado) {
						setAlwaysOnTop(true);
					}
				}
			}
		});
		
		labelFijar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				labelFijar.setIcon(Utilidades.agregaIcono("alfilerseleccion"));
			}
			public void mouseExited(MouseEvent e) {
				if(estado) {
					labelFijar.setIcon(Utilidades.agregaIcono("alfilerseleccion"));
				}
				else {
					labelFijar.setIcon(Utilidades.agregaIcono("alfiler"));

				}
			}
			
			public void mousePressed(MouseEvent e) {
				estado = !estado;
				setAlwaysOnTop(estado); //Mantiene arriba la ventana
			}
		});
		
		labelColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				labelColor.setIcon(Utilidades.agregaIcono("gotaseleccion"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				labelColor.setIcon(Utilidades.agregaIcono("gota"));
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				popupColores.show(labelColor, labelColor.getBounds().x-60, labelColor.getBounds().y + labelColor.getBounds().height);
			}
		});
		
		labelTitulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				labelTitulo.setIcon(Utilidades.agregaIcono("lapizseleccion"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				labelTitulo.setIcon(Utilidades.agregaIcono("lapiz"));
			}
			
			public void mousePressed(MouseEvent e) {
				estadoTextTitulo = !estadoTextTitulo;
				if(estadoTextTitulo) {
					//si es verdadero no hay texto
					labelTitulo.setLocation(200, 6); //se reposiciona el btn
					textTitulo.setVisible(estadoTextTitulo); //como estado es verdadero entonces muestra cuadro de texto
					textTitulo.requestFocus(); // se coloca el foco en el cuadro de texto
				}
				else {
					//si es false quiere decir que hay texto
					textTitulo.setEditable(true); // al volver precionar el boton y como si hay texto se habilita para editarse 
					textTitulo.requestFocus();// se coloca el foco en el cuadro de texto
				}

			}
		});
		
		textTitulo.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				//cuando pierde el foco
				if(textTitulo.getText().equals("")){
					//si no hay texto en el cuadro entonces se oculta y se reposiciona el bton de Titulo
						estadoTextTitulo = false; // se restablece en false
						textTitulo.setVisible(estadoTextTitulo);
						labelTitulo.setLocation(70, 6);
				}
				else {
					//si si hay texto entonces se bloquea el cuadro de texto
					textTitulo.setEditable(false);
					estadoTextTitulo = true; // se pone en true
				}
			}

		});
		
		panelMenu.addMouseMotionListener(new MouseMotionAdapter() {
			//cada vez que precionemos y arrastremos en el panel menu entonces todo el marco o JFrame se mueve
			public void mouseDragged(MouseEvent e) {
				setCursor(new Cursor(Cursor.MOVE_CURSOR)); // mientras se arrastra el cursor cambia
				Point ubicacion = MouseInfo.getPointerInfo().getLocation(); // Obtenemos la pusicion del puntero
				setLocation(ubicacion.x - x, ubicacion.y - y); // posiciona el Marco(JFrame) hacia donde coloquemos el puntero
			}
		});
		
		panelMenu.addMouseListener(new MouseAdapter() {
			//al dar un click en el panel menu, obtenemos la coordenadas del click
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
			}
			
			public void mouseReleased(MouseEvent e) {
				// cuando se suelte si es que se esta arrastrando se regresa al cursor normal
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
// **************** Multiples Oyentes Teclas ***********************************************************************
		InputMap mapaEntrada = panelText.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW); 
		
		mapaEntrada.put(KeyStroke.getKeyStroke("ctrl N"), "BOND");
		
		mapaEntrada.put(KeyStroke.getKeyStroke("ctrl K"), "ITALIC");
		
		ActionMap mapaAccion = panelText.getActionMap();
		
		
		mapaAccion.put("BOND", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				estadoBOLD = !estadoBOLD;
				
				if(estadoBOLD && estadoITALIC) {
					textoArea.setFont(new Font("Arial", Font.ITALIC+Font.BOLD, 12));
				}
				else if(estadoBOLD) {
					textoArea.setFont(new Font("Arial", Font.BOLD, 12));
				}
				else if(estadoBOLD == false && estadoITALIC) {
					textoArea.setFont(new Font("Arial", Font.ITALIC, 12));
				}
				else if(estadoBOLD == false && estadoITALIC == false) {
					textoArea.setFont(new Font("Arial", Font.PLAIN, 12));
				}
			}
			
		});
		
		mapaAccion.put("ITALIC", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				estadoITALIC = !estadoITALIC;
				if(estadoITALIC && estadoBOLD) {
					textoArea.setFont(new Font("Arial", Font.ITALIC+Font.BOLD, 12));
				}
				else if(estadoITALIC) {
					textoArea.setFont(new Font("Arial", Font.ITALIC, 12));
				}
				else if(estadoITALIC == false && estadoBOLD) {
					textoArea.setFont(new Font("Arial", Font.BOLD, 12));
				}
				else if(estadoITALIC == false && estadoBOLD == false) {
					textoArea.setFont(new Font("Arial", Font.PLAIN, 12));
				}
			}
			
		});
		
		
		
//***************** Fin Multiples Oyentes *************************************************************************
		
//***************** Opciones del menu Font**************************************************************************
		popupFont.add(new JMenuItem(new AbstractAction("Negrita") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				estadoBOLD = !estadoBOLD;
				
				if(estadoBOLD && estadoITALIC) {
					textoArea.setFont(new Font("Arial", Font.ITALIC+Font.BOLD, 12));
				}
				else if(estadoBOLD) {
					textoArea.setFont(new Font("Arial", Font.BOLD, 12));
				}
				else if(estadoBOLD == false && estadoITALIC) {
					textoArea.setFont(new Font("Arial", Font.ITALIC, 12));
				}
				else if(estadoBOLD == false && estadoITALIC == false) {
					textoArea.setFont(new Font("Arial", Font.PLAIN, 12));
				}
				//System.out.println(textoArea.getFont().getStyle());
			}
			
		}));
		
		popupFont.add(new JMenuItem(new AbstractAction("Cursiva") {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				estadoITALIC = !estadoITALIC;
				if(estadoITALIC && estadoBOLD) {
					textoArea.setFont(new Font("Arial", Font.ITALIC+Font.BOLD, 12));
				}
				else if(estadoITALIC) {
					textoArea.setFont(new Font("Arial", Font.ITALIC, 12));
				}
				else if(estadoITALIC == false && estadoBOLD) {
					textoArea.setFont(new Font("Arial", Font.BOLD, 12));
				}
				else if(estadoITALIC == false && estadoBOLD == false) {
					textoArea.setFont(new Font("Arial", Font.PLAIN, 12));
				}
			}
			
		}));
//************************* Fin Menu Font ************************************************************************
		
//**************** Opciones del menu colores ************************************************************************
		popupColores.add(new JMenuItem(new AbstractAction("Amarillo") {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setBackground(new Color(227, 255, 0));
				panelText.setBackground(new Color(255, 244, 161));
				textoArea.setBackground(new Color(255, 244, 161));
			}
		}));
		popupColores.add(new JMenuItem(new AbstractAction("Azul") {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setBackground(new Color(26, 134, 226));
				panelText.setBackground(new Color(12, 183, 242));
				textoArea.setBackground(new Color(12, 183, 242));
			}
		}));
		popupColores.add(new JMenuItem(new AbstractAction("Magenta") {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setBackground(new Color(172, 92, 181));
				panelText.setBackground(new Color(212, 115, 212));
				textoArea.setBackground(new Color(212, 115, 212));
				
			}
		}));
		popupColores.add(new JMenuItem(new AbstractAction("Naranja") {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setBackground(new Color(250, 153, 108));
				panelText.setBackground(new Color(255, 187, 156));
				textoArea.setBackground(new Color(255, 187, 156));
			}
		}));
		popupColores.add(new JMenuItem(new AbstractAction("Púrpura") {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setBackground(new Color(175, 105, 205));
				panelText.setBackground(new Color(203, 155, 222));
				textoArea.setBackground(new Color(203, 155, 222));
			}
		}));
		popupColores.add(new JMenuItem(new AbstractAction("Rojo") {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setBackground(new Color(253, 91, 91));
				panelText.setBackground(new Color(255, 132, 131));
				textoArea.setBackground(new Color(255, 132, 131));
			}
		}));
		popupColores.add(new JMenuItem(new AbstractAction("Verde") {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setBackground(new Color(119, 221, 119));
				panelText.setBackground(new Color(217, 248, 216));
				textoArea.setBackground(new Color(217, 248, 216));
			}
		}));
//************************* Fin Menu COlor ************************************************************************

	}
	
	private JPanel panelMenu;
	private JPanel panelText;
	private JLabel labelFijar;
	private JLabel labelColor;
	private JLabel labelTitulo;
	private JLabel labelGuardar;
	private JLabel labelTipoTexto;
	private JLabel labelMas;
	private JLabel labelCerrar;
	private JTextArea textoArea;
	private JTextField textTitulo;
	private JPopupMenu popupColores;
	private JPopupMenu popupFont;
	private boolean estado;
	private boolean estadoTextTitulo;
	private boolean estadoBOLD;
	private boolean estadoITALIC;
	private static int contador = 0;
	private final int numeroNota;
	private static String numerosNotas ="";
	private boolean numNotas = true;
	private String guardadoUna = "false";
	private int x;
	private int y;
    private JScrollPane scrollPane;
    
    private Recolector recolector;

}
