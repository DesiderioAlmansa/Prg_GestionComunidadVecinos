
public  class Propietario  extends Vecino{
	
	private Fecha fechaAdquisicion;
	private int cuota;
	
	//Metodo constructor
	public Propietario(String dni, String nombre, String apellidos,Inmueble inmueble , long telefono, Fecha fechaAdquisicion, int cuota) {
		super(dni,nombre, apellidos, inmueble, telefono);
		this.fechaAdquisicion=fechaAdquisicion;
		this.cuota=cuota;
	}
	
	//Getter y setter
	public Fecha get_fechaAdq() {
		return fechaAdquisicion;
	}
	
	public void set_fechaAdq(Fecha fecha) {
		fechaAdquisicion=fecha;
	}
	public int get_cuota() {
		return cuota;
	}
	
	public void set_cuota(int c) {
		cuota=c;
	}
	
	public double getImporteTotal() {
		double cuotaTotal = cuota*12;
		return cuotaTotal;
	}


	public String toString() {
		return "Propietario [ DNI: " + dni + ", Nombre: " + nombre + ", Apellidos: " + apellidos + ", Inmueble: "
				+ inmueble.get_planta()+inmueble.get_puerta() + ", Telefono: " + telefono +  ", FechaOcupacion: " + fechaAdquisicion + ", Cuota: " + cuota + " ]";
	}
	
	//ToString
	
}
