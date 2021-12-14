
public class InquilinoAnual extends Vecino {
	protected int cuotaAnual;
	protected Fecha fechaOcupacion;
	protected boolean piscina;
	
	//Metodo constructor
	public InquilinoAnual(String dni, String nombre, String apellidos,  Inmueble inmueble, long telefono,  Fecha fechaOcupacion, int cuotaAnual, boolean piscina) {
		super(dni,nombre, apellidos, inmueble, telefono);
		this.cuotaAnual=cuotaAnual;
		this.piscina=piscina;
		this.fechaOcupacion=fechaOcupacion;
	}
	//Getters
	
	public int get_cuotaAnual() {
		return cuotaAnual;
	}
	
	public boolean get_piscina() {
		return piscina;
	}
	
	public Fecha get_fechaOcupacion() {
		return fechaOcupacion;
	}
	//Setters
	public void set_cuotaAnual(int cuotaAnual) {
		this.cuotaAnual=cuotaAnual;
	}
	public void set_piscina(boolean piscina) {
		this.piscina = piscina;
	}
	
	public void set_fechaOcupacion(Fecha fechaOcupacion) {
		this.fechaOcupacion=fechaOcupacion;
	}
	
	public double getImporteTotal() {
		double importeTotal;
		if(piscina) {
				importeTotal=ANUAL_PISCINA - 0.05*cuotaAnual;
				if (importeTotal < PAGO_MINIMO) importeTotal = cuotaAnual + PAGO_MINIMO;	else importeTotal += cuotaAnual;
		}
		else {
			importeTotal=cuotaAnual;
		}
		return importeTotal;
	}
	
	
	
	public String toString() {
		return "InquilinoAnual [ DNI: " + dni + ", Nombre: " + nombre + ", Apellidos: " + apellidos + ", Inmueble: "
				+ inmueble.get_planta()+inmueble.get_puerta() + ", Telefono: " + telefono +  ", FechaOcupacion: " + fechaOcupacion + ", CuotaAnual: " + cuotaAnual  
				+ ", Piscina: "+ piscina + " ]";
	}
	
}
