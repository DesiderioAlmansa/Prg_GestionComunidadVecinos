import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class LecturaDeFicheros {
	
	//Metodo que lee el fichero sugerencias
	public static void leerSugerencias(String nombreFichero, Comunidad com) {
		
		Vecino vecino;
		String dni; 
		Fecha fecha;
		String asunto;
		String texto;
		String prioridadLeer; //Lo que se lee de los ficheros
		cadPrioridad prioridad=null;
		
		try {
			
			FileReader fr = new FileReader(nombreFichero);
			BufferedReader br = new BufferedReader(fr);
			String linea;
		
			while((linea = br.readLine()) != null) { 
				
				try {
					
					StringTokenizer st = new StringTokenizer(linea);
					dni=st.nextToken();
					vecino=com.encuentraVecinoDNI(dni);
					fecha=new Fecha(st.nextToken());
					asunto=st.nextToken();
					prioridadLeer=st.nextToken();
					if(prioridadLeer.equals("No_Urgente")) {
						prioridad=cadPrioridad.NO_URGENTE;
					}
					else if(prioridadLeer.equals("Urgente")) {
						prioridad=cadPrioridad.URGENTE;
					}
					else if(prioridadLeer.equals("Muy_Urgente")) {
						prioridad=cadPrioridad.MUY_URGENTE;
					}
					else {
						prioridad=null;
					}
					texto=st.nextToken();
		
					Sugerencia sug=new Sugerencia(vecino, fecha, asunto, prioridad, texto);
					vecino.addSug(sug);
					
				}
				catch(Exception e) {
					System.out.println("Excepcion leyendo fichero "+ nombreFichero + ": " + e);
		    	}	
				
			}//fin del while
			
			fr.close();
			
		}
		catch(Exception e) {
			System.out.println("Excepcion leyendo fichero "+ nombreFichero + ": " + e);
    	}	
		
	}//fin del metodo leerSugerencias()
	
	/*---------------------------------------------------------------------------------------------------------------*/
  
    //Metodo que lee el fichero de los vecinos
    public static void leerVecinos(String nombreFichero, Comunidad com) {
    	
    	char tipo;
    	String dni, nombre, apellidos;
    	Inmueble inmueble;
    	long telefono;
    	Fecha fecha;
    	int cuota;
    	boolean piscina;
	  
	    try {
	      FileReader fr = new FileReader(nombreFichero);
	      BufferedReader br = new BufferedReader(fr);
	      String linea;
	      
	      while ((linea = br.readLine()) != null) {
	    	  
	    	try { 
	    		
	    		StringTokenizer st = new StringTokenizer(linea);
	    		tipo=(st.nextToken()).charAt(0);
	    		dni=(st.nextToken());
	    		nombre=(st.nextToken());
				apellidos=(st.nextToken());
				inmueble=new Inmueble(st.nextToken());
				telefono=Long.parseLong(st.nextToken());
				fecha=new Fecha(st.nextToken());
				cuota=Integer.parseInt(st.nextToken());
				
				
				switch(tipo){
				
					case 'p': //p = propietario
						
						Fecha fechaAdquisicion=fecha;
						Propietario propietario=new Propietario(dni, nombre, apellidos, inmueble, telefono, fechaAdquisicion, cuota);// ordenar como en el fichero el constructor
						com.addVecino(propietario);
					
						
						break;
						
					case 'm': //m = inquilino mensual

						Fecha fechaOcupacionM=fecha;
						int meses=Integer.parseInt(st.nextToken());
						piscina=Boolean.parseBoolean(st.nextToken());
						InquilinoMensual mensual=new InquilinoMensual(dni, nombre, apellidos, inmueble, telefono, fechaOcupacionM, cuota, meses, piscina);// ordenar como en el fichero el constructor
						com.addVecino(mensual);
						//System.out.println(mensual.toString());//
						
						break;	
						
					case 'i':// i = inquilino anual
						
						Fecha fechaOcupacionA=fecha;
						piscina=Boolean.parseBoolean(st.nextToken());
						
						if(st.hasMoreTokens()) {//añadimos un inquilino anual vip
							int vip=Integer.parseInt(st.nextToken());
							InquilinoAnualVip anualVip=new InquilinoAnualVip(dni, nombre, apellidos, inmueble, telefono, fechaOcupacionA, cuota, piscina, vip);//ordenar como en el fichero el constructor
							com.addVecino(anualVip);
							//System.out.println(anualVip.toString());//
						}
						else {//añadimos un inquilino anual normal (NO vip)
							InquilinoAnual anual=new  InquilinoAnual(dni, nombre, apellidos, inmueble, telefono, fechaOcupacionA, cuota, piscina);//oredenar como en el fichero el constructor
							com.addVecino(anual);
						//	System.out.println(anual.toString());//
						}
						
						break;
						
					
						
				}//fin del switch
				
	    	}
	    	catch(Exception e){
	    		System.out.println("Excepcion leyendo fichero "+ nombreFichero + ": " + e);
	    	} // try
	    	
	      }//fin del bucle while
	      
	      fr.close();
	      
	    }
	    catch(Exception e) {
	    	System.out.println("Excepcion leyendo fichero "+ nombreFichero + ": " + e);
	    }
	    
    }//fin del metodo leerVecinos()
    
    
}
	
		