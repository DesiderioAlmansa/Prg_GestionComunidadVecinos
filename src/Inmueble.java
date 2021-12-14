
public class Inmueble {
	private int planta;
	private char puerta;
	
	//Metodo constructor
	public Inmueble(int planta, char puerta) {
		this.planta=planta;
		this.puerta=puerta;
	}
	
	//Constructor lectura ficheros
	public Inmueble(String cadInmueble) {
		String cadPlanta = new String (cadInmueble.substring(0,cadInmueble.length()-1));
		planta = Integer.parseInt(cadPlanta);
		puerta = cadInmueble.charAt(cadInmueble.length()-1);
	}
	
	//Getters
	public int get_planta() {
		return planta;
	}
	
	public char get_puerta() {
		return puerta;
	}
	
	//Setters
	public void set_planta(int numero) {
		planta=numero;
	}
	
	public void set_puerta(char letra) {
		puerta=letra;
	}

	
	public String toString() {
		return "[planta=" +planta + ", puerta=" + puerta + "]";
	}
	

}
