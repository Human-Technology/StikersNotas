package principal;

import java.net.URL;

import data.Recolector;

public class Main {
	public static void main(String [] args) {
		Recolector recolector = new Recolector();

		String numeros = recolector.getNumerosNotas();
		int contador = Integer.parseInt(recolector.getContador());
		if(contador == 0) {
			Nota nota = new Nota(contador, contador, true);
			nota.setVisible(true);
		}else {
			for(int i = 0; i<numeros.length(); i++) {
				int num = Character.getNumericValue(numeros.charAt(i));
				Nota nota = new Nota(num, contador, false);
				nota.setVisible(true);
			}
		}

		
	}
}
