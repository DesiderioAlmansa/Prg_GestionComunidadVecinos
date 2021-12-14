
public class Comunidad implements Constantes {
	private String nombreCom;
	private Vecino[] vecinos = new Vecino[MAX_VECINOS];
	private int numVecino;
	
	//Metodo constructor
	public Comunidad(String nombreCom) {
		this.nombreCom=nombreCom;
		numVecino=0;
	}
	
	//Getters y setters
	public String get_nombreCom() {
		return nombreCom;
	}
	
	public void set_nombreCom(String nombreCom) {
		this.nombreCom=nombreCom;
	}

	public Vecino[] getVecinos() {
		return vecinos;
	}

	public void setVecinos(Vecino[] vecinos) {
		this.vecinos = vecinos;
	}
	
	public int get_numVecino() {
		return numVecino;
	}
	
	//METODO QUE ENCUENTRA UN VECINO SABIENDO SU DNI
	public Vecino encuentraVecinoDNI(String dni) {
		boolean encontrado=false;
		Vecino v=null;
		for(int i=0;i<numVecino && !encontrado; i++) {
			encontrado=(vecinos[i].get_dni()).equals(dni);
			if(encontrado) {
				v=vecinos[i];
			}
		}
		return v;
	}
	
	
	//METODO QUE AÑADE VECINOS AL VECTOR DE VECINOS DE LA COMUNIDAD
	public void addVecino(Vecino newVecino) {
		if (numVecino<MAX_VECINOS) {
			vecinos[numVecino]=newVecino;
			numVecino++;
		}
		else {
			System.out.println("No queda espacio para un nuevo vecino");
		}
	}
	
	//METODO QUE ENCUENTRA UN VECINO SABIENDO SU NOMBRE
	public Vecino encuentraVecinoNombre(String nombre) {
		boolean encontrado=false;
		Vecino vecino=null;
		for(int i=0;i<numVecino && !encontrado; i++) {
			encontrado=(vecinos[i].get_nombre()) == nombre;
			if(encontrado) {
				vecino=vecinos[i];
			}
		}
		return vecino;
	}
	
}
