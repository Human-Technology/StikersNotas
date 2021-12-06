package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Recolector {
	
	public boolean existeArchivo(int numeroNota) {
		File f = new File("src/data/"+"contenidoNota"+numeroNota+".properties");
		return f.exists();
	}
	
	public void crearArchivo(int numeroNota) throws IOException {
		File f = new File("src/data/"+"contenidoNota"+numeroNota+".properties");
		f.createNewFile();
	}
	public void eliminarArchivo(int numeroNota) throws IOException {
		File f = new File("src/data/"+"contenidoNota"+numeroNota+".properties");
		f.delete();
	}
	
	public void eliminaNumeroNotas(int numeroNota, int contador) {
		String numNota = ""+numeroNota;
		String texto="";
    	Properties config = new Properties();

        try{
        	InputStream archivoData = new FileInputStream("src/data/config.properties");
        	config.load(archivoData);
             texto = config.getProperty("numerosNotas");
             
             StringBuffer sb = new StringBuffer(texto);
             
             for(int i =0; i<texto.length(); i++) {
            	 
            	 if(numNota.equals(""+texto.charAt(i))) {
            		 sb.delete(i, i+1);
            	 }
             }
    		 if(sb.length() <1) {
    			 sb.append("0");
    		 }
    		 texto = sb.toString();
             
             setConfig(contador, texto);
        	
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error guardando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	public String getNumerosNotas() {
		String texto="";
    	Properties config = new Properties();

        try{
        	InputStream archivoData = new FileInputStream("src/data/config.properties");
        	config.load(archivoData);
             texto = config.getProperty("numerosNotas");
        	
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error guardando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return texto;
	}
	
	public String getContador() {
		String texto="";
    	Properties config = new Properties();

        try{
        	InputStream archivoData = new FileInputStream("src/data/config.properties");
        	config.load(archivoData);
             texto = config.getProperty("contador");
        	
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error guardando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return texto;
	}
	
	public String getText(int numeroNota) {
		String texto="";
    	Properties config = new Properties();

        try{
        	InputStream archivoData = new FileInputStream("src/data/contenidoNota" + numeroNota + ".properties");
        	config.load(archivoData);
             texto = config.getProperty("text");
        	
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error guardando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return texto;
	}
	public String getGuardadoUna(int numeroNota) {
		String texto="";
    	Properties config = new Properties();

        try{
        	InputStream archivoData = new FileInputStream("src/data/contenidoNota" + numeroNota + ".properties");
        	config.load(archivoData);
             texto = config.getProperty("guardado");
        	
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error guardando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return texto;
	}
	
	public String getTitle(int numeroNota) {
		String texto="";
    	Properties config = new Properties();

        try{
        	InputStream archivoData = new FileInputStream("src/data/contenidoNota" + numeroNota + ".properties");
        	config.load(archivoData);
             texto = config.getProperty("title");
        	
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error guardando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return texto;
	}
	
	public static void setText(String property, String value, String propertyC,int contador, String propertyG, String tf, String propertyT, String Title){
        try{
        	Properties config = new Properties();
        	FileOutputStream archivoData = new FileOutputStream("src/data/contenidoNota"+contador+".properties");
        	config.setProperty(property, value);
        	config.setProperty(propertyC, ""+contador);
        	config.setProperty(propertyG, tf);
        	config.setProperty(propertyT, Title);
        	
            config.store(archivoData, "Datos de la nota");
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error guardando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	
	
	public static void setConfig(int contador, String numeros){
        try{
        	Properties config = new Properties();

        	FileOutputStream archivoData = new FileOutputStream("src/data/config.properties");
        	config.setProperty("contador", ""+contador);
        	config.setProperty("numerosNotas", numeros);
            config.store(archivoData, "Configuracion");
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error guardando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	


}

