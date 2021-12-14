
public class InquilinoMensual extends Vecino {
  
	private Fecha fechaOcupacion;
	private int cuotaMensual;
	private int meses;
	private boolean piscina;
	
	
	//Metodo constructor
	public InquilinoMensual(String dni, String nombre, String apellidos, Inmueble inmueble, long telefono, Fecha fechaOcupacion,int cuotaMensual, int meses, boolean piscina) {
		super(dni,nombre, apellidos, inmueble, telefono);
		this.fechaOcupacion=fechaOcupacion;
		this.cuotaMensual=cuotaMensual;
		this.meses=meses;
		this.piscina=piscina;
		
	}
	
	//Getters
	public int get_cuotaMensual() {
		return cuotaMensual;
	}
	public int get_meses() {
		return meses;
	}
	public boolean get_piscina() {
		return piscina;
	}
	public Fecha get_fechaOcupacion() {
		return fechaOcupacion;
	}
	
	//Setters
	public void set_cuotaMensual(int cuotaMensual) {
		this.cuotaMensual=cuotaMensual;
	}
	
	public void set_meses(int m) {
		meses=m;
	}
	public void set_piscina(boolean p) {
		piscina=p;;
	}
	
	public void set_fechaOcupacion(Fecha fechaOcupacion) {
		this.fechaOcupacion=fechaOcupacion;
	}

	public double getImporteTotal() {
		double importeTotal;
		if (piscina) {
			importeTotal = (cuotaMensual*12)+(MENSUAL_PISCINA*12);
		}
		else {
			importeTotal = (cuotaMensual*12);
		}
		return importeTotal;
	}

	public String toString() {
		return "InquilinoMensual [ DNI: " + dni + ", Nombre: " + nombre + ", Apellidos: " + apellidos + ", Inmueble: "
				+ inmueble.get_planta()+inmueble.get_puerta() + ", Telefono: " + telefono +  ", FechaOcupacion: " + fechaOcupacion + ", CuotaMensual: " + cuotaMensual 
				+ " Meses: "+ meses + ", Piscina: "+ piscina + " ]";
	}
	
}
