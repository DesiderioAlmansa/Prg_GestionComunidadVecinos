
public class InquilinoAnualVip extends InquilinoAnual{
	private int numVip;
	
	//Metodo constructor
	public InquilinoAnualVip(String dni, String nombre, String apellidos,  Inmueble inmueble, long telefono,  Fecha fechaOcupacion, int cuotaAnual, boolean piscina, int numVip) {
		super(dni,nombre, apellidos, inmueble, telefono, fechaOcupacion, cuotaAnual, piscina);
		this.numVip=numVip;
	}
	
	//Getter y setter
	
	public int get_numVip() {
		return numVip;
	}
	
	public void set_numVip(int numVip) {
		this.numVip=numVip;
	}

	public double getImporteTotal() {
		double importeTotal;
		if(piscina) {
			 importeTotal=cuotaAnual + VIP_PISCINA;
		}
		else {
			importeTotal=cuotaAnual;	
		}
		return importeTotal;
	}
	
	public String toString() {
		return "InquilinoAnualVip [ DNI: " + dni + ", Nombre: " + nombre + ", Apellidos: " + apellidos + ", Inmueble: "
				+ inmueble.get_planta()+inmueble.get_puerta() + ", Telefono: " + telefono +  ", FechaOcupacion: " + fechaOcupacion + ", CuotaAnual: " + cuotaAnual  
				+ ", Piscina: "+ piscina + ", NúmeroVip:" + numVip +  " ]";
	}
	
	
	
	

}
