

public class Sugerencia {
	private Vecino vecino;
	private Fecha fechaSug;
	private String asunto;
	private cadPrioridad prioridad;
	private String texto;
	
	
	//Metodo constructor
	public Sugerencia(Vecino vecino, Fecha fechaSug, String asunto, cadPrioridad prioridad, String texto) {
		this.vecino=vecino;
		this.fechaSug=fechaSug;
		this.asunto=asunto;
		this.prioridad=prioridad;
		this.texto=texto;
	}
	
	//Getters
	public Vecino get_vecino() {
		return vecino;
	}
	
	public String get_dniSug() {
		return vecino.get_dni();
	}
	
	public Fecha get_fechaSug() {
		return fechaSug;
	}
	
	public String get_asunto() {
		return asunto;
	}
	public String get_texto() {
		return texto;
	}
	public cadPrioridad get_prioridad() {
		return prioridad;
	}
	
	//Setters
	public void set_vecino(Vecino vecino) {
		this.vecino=vecino;
	}
	
	public void set_fechaSug(Fecha fechaSug) {
		this.fechaSug=fechaSug;
	}
	
	public void set_asunto(String a) {
		asunto=a;
	}
	public void set_texto(String t) {
		texto=t;
	}
	public void set_prioridad(cadPrioridad p) {
		prioridad=p;
	}


	public String toString() {
		return "[DNI del vecino: " + vecino.get_dni() + ", Fecha: " + fechaSug + ", Asunto: " + asunto + ", Prioridad: "
				+ prioridad + ", Descripcion: " + texto + "]";
	}
	

}
