package Clases;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import principal.Nota;


public class Utilidades {
	public static ImageIcon agregaIcono(String nombreIcono) {
		URL url = Utilidades.class.getResource("/iconos/"+nombreIcono+".png");
		ImageIcon i = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		return i;
	}
	/**
	public static JLabel agregaIcono(String nombreIcono, String accion, JFrame jframe) {
		URL url = Utilidades.class.getResource("/iconos/"+nombreIcono+".png");
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
		
		if(accion.equals("anclar")) {
			label.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					URL url = Utilidades.class.getResource("/iconos/"+nombreIcono+"seleccion"+".png");
					label.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
				}
				public void mouseExited(MouseEvent e) {
					if(Nota.estadoAlfiler) {
						URL url = Utilidades.class.getResource("/iconos/"+nombreIcono+"seleccion"+".png");
						label.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
					}
					else {
						URL url = Utilidades.class.getResource("/iconos/"+nombreIcono+".png");
						label.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));

					}
				}
				
				public void mousePressed(MouseEvent e) {
					Nota.estadoAlfiler = !Nota.estadoAlfiler;
					setAlwaysOnTop(Nota.estadoAlfiler); //Mantiene arriba la ventana
				}
			});
		}
		
		return label;
	}**/
}
