
public abstract class Vecino implements Constantes {
	
	protected String dni;
	protected String nombre;
	protected String apellidos;
	protected long telefono;
	protected Inmueble inmueble;
	protected Sugerencia[] vectorSugerencia = new Sugerencia[MAX_SUG];
	protected  int numSug;


	//Metodo constructor
	public Vecino(String dni, String nombre, String apellidos, Inmueble inmueble, long telefono) {
		this.dni=dni;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.telefono=telefono;
		this.inmueble=inmueble;
		numSug=0;
	}
	
	//Getters
	
	public String get_dni() {
		return dni;
	}
	public String get_nombre() {
		return nombre;
	}
	public String get_apellidos() {
		return apellidos;
	}
	public long get_telefono() {
		return telefono;
	}
	
	public Inmueble get_inmueble() {
		return inmueble;
	}
	
	public int get_numSug() {
		return numSug;
	}
	
	public Sugerencia [] get_sugerencias() {
		return vectorSugerencia;
	}
	
	//Setters
	public void set_dni(String d) {
		dni=d;
	}
	public void set_nombre(String n) {
		nombre=n;
	}
	public void set_apellidos(String a) {
		apellidos=a;
	}
	public void set_telefono(long t) {
		telefono=t;
	}
	public void set_inmueble(Inmueble i) {
		inmueble=i;
	}
	
	public void addSug(Sugerencia sug) {
		if(numSug<MAX_SUG) {
			vectorSugerencia[numSug]=sug;
			numSug++;
		}	
	}
	
	public abstract double getImporteTotal();
	


}