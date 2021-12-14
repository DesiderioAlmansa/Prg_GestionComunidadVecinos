import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Principal {
	
	final static Scanner TECLADO = new Scanner(System.in);
	
	public static void main(String [] args) {
		
		String ficheroVecinos = "Vecinos.txt";
		
		String ficheroSugerencias = "Sugerencias.txt";
		
		Comunidad com = new Comunidad("ESI");

		LecturaDeFicheros.leerVecinos(ficheroVecinos, com);
		LecturaDeFicheros.leerSugerencias(ficheroSugerencias, com);
		
		Vecino vecinos[]=com.getVecinos();
		boolean seguir=true;
		System.out.println("Indique que operación desea realizar:");
		Principal.menu(com, seguir, vecinos);
		
	}
	
	
	
	private static void menu(Comunidad com, boolean seguir, Vecino[] vecinos) {
		int opcion;
		do {
			opcion=seleccionarOpcionMenu();
			switch(opcion) {
				case 1: 
					
					System.out.println(" ");
					System.out.println("Los vecinos de la comunidad " + com.get_nombreCom() + " son:");
					System.out.println(" ");
					imprimeVectorVecinos(com, vecinos);
					System.out.println(" ");
	
					break;
					
				case 2:
					
					System.out.println(" ");
					realizarSugerencia(com);
					
					break;
					
				case 3:
					
					numSugRealizadas(com);
					
					break;
					
				case 4:
					
					sugURGENTESRealizadas(com);
					
					break;
				case 5:
					System.out.println("");
					System.out.println("Estos son los ingresos de la comunidad " + com.get_nombreCom() + ":");
					mostrarIngresosTotalesCom(com);
					
					break;
					
				case 6:
					
					mostrarImporteVecino(com);
					
					break;
				
				case 7:
					
					System.out.println(" ");
					System.out.println("Este es el orden alfabético (por nombres) de los vecinos de la comunidad " + com.get_nombreCom() + " :");
					System.out.println(" ");
					ordenAlfabeticoVecinos(com);
					System.out.println(" ");
					
					break;
					
				case 8:
				
					System.out.println("Este es el orden de los Inquilinos Anuales, de la comunidad "+ com.get_nombreCom()  
									 + ", de los que más pagan a los que menos:");
					System.out.println(" ");
					ordenInqAnualesCuotas(com);
					System.out.println(" ");
					
					break;	
					
				case 9:
					
					System.out.println("Este es el orden de los Propietarios, de la comunidad "+ com.get_nombreCom()  
					 + ", según la fecha en la que adquirieron sus inmuebles (de la más antigua a la más reciente):");
					System.out.println(" ");
					ordenPropietariosFecha(com);
					System.out.println(" ");
					
					break;
					
				case 10:
					
					System.out.println(" ");
					System.out.println("Estos son los cinco vecinos que proporcionan un mayor ingreso a la comunidad:");
					System.out.println(" ");
					top5VecinosMayorIngreso(com);
					System.out.println(" ");	
					
					break;
					
				case 11:
					
					System.out.println(" ");
					newVecino(com);
					System.out.println(" ");
					
					break;
					
				case 12:
					
					System.out.println("El programa se ha cerrado.");
					seguir=false;
					
					break;
					
				default:
						System.out.println("Opcion incorrecta. Por favor escoja una de las que se indican:");
						System.out.println(" ");
					}
			}while(seguir);
			System.exit(1);
		}
	
	
	
	private static int seleccionarOpcionMenu() {
		
		int opcion;
		
		System.out.println("1. Mostrar informacion de los vecinos.");
		System.out.println("2. Realizar sugerencia.");
		System.out.println("3. Consultar número de sugerencias realizadas.");
		System.out.println("4. Mostrar toda la información de las sugerencias URGENTES que ha realizado un determinado vecino.");
		System.out.println("5. Mostrar ingresos totales de la comunidad al año (desglosado).");
		System.out.println("6. Mostrar importe que tiene que pagar un vecino concreto.");
		System.out.println("7. Listado en orden alfabetico de los vecinos.");
		System.out.println("8. Listado de inquilinos anuales, ordenado de mayor a menor importe a satisfacer a la comunidad.");
		System.out.println("9. Listado de propietarios ordenado según la fecha de adquisición del inmueble.");
		System.out.println("10. Listado de los cinco vecinos que le proporcionan unos mayores ingresos a la comunidad.");
		System.out.println("11. Añadir un nuevo vecino.");
		System.out.println("12. Salir.");
		
		opcion=TECLADO.nextInt();
		
		return opcion;
	}
	
	
	
	private static void imprimeVectorVecinos(Comunidad com, Vecino [] vecinos) {
		for(int i=0;i<com.get_numVecino();i++) {
			System.out.println(vecinos[i].toString());
		}
	}
	
	
	
	//Metodo que comprueba la sintaxis del dni (7 números y 1 letra)
	private static boolean verificaDNI(String dni) {
		boolean seguir=true;
		if(dni.length()==9) {
			if(Character.isLetter(dni.charAt(8))==false) {
				seguir=false;
			}else {
				for(int i=0;i<8;i++) {
					if(Character.isDigit(dni.charAt(i))==false) {
						seguir=false;
					}
				}
			}
		}else {
			seguir=false;
		}
		return seguir;	
	}
	
	
	
	//Metodo que para realizar una sugerencia nueva (case 2)
	private static void realizarSugerencia(Comunidad com) {
		boolean opcionIncorrecta=true;
		boolean dniCorrecto;
		
		String fechaActual = fechaActual();
		Fecha fecha = new Fecha(fechaActual);
		
		System.out.println("Introduzca los siguientes datos:");
		System.out.println(" ");
		
		System.out.print("DNI: ");
		String dni=TECLADO.next();
		dniCorrecto=verificaDNI(dni);
		while(dniCorrecto == false){
			System.out.println("El dni no es correcto. Introduzcalo de nuevo por favor: ");
			dni = TECLADO.next();
			dniCorrecto=verificaDNI(dni);	
		}
		
		Vecino vecino=com.encuentraVecinoDNI(dni);
		if(vecino!=null) {
			if(vecino.get_numSug()<10) {
				System.out.println("Fecha de hoy: " + fechaActual);
		
				System.out.print("Asunto: ");
				String asunto=TECLADO.next();
		
				cadPrioridad prioridad = null;
				while(opcionIncorrecta==true) {
					System.out.println("Prioridad : \n 1.No urgente \n 2.Urgente \n 3.Muy urgente");
					int eleccionPrioridad=TECLADO.nextInt();
		
					if(eleccionPrioridad == 1) {
						prioridad = cadPrioridad.NO_URGENTE;
						opcionIncorrecta=false;
					}
					else if(eleccionPrioridad == 2) {
						prioridad = cadPrioridad.URGENTE;
						opcionIncorrecta=false;
					}
					else if(eleccionPrioridad == 3) {
						prioridad = cadPrioridad.MUY_URGENTE;
						opcionIncorrecta=false;
					}
					else {
						System.out.println("Opcion incorrecta. Por favor escoja una de las que se indican:");
						opcionIncorrecta=true;
					}
				}
		
		
				System.out.print("Descripcion: ");
				String texto=TECLADO.next();
		
		
				Sugerencia sug=new Sugerencia(vecino, fecha, asunto, prioridad, texto);
				vecino.addSug(sug);
				System.out.println(" ");
				System.out.println("La sugerencia se ha realizado con éxito.");
				System.out.println(" ");
			}
			else {
				System.out.println(" ");
				System.out.println("No puedes realizar más de 10 sugerencias.");
				System.out.println(" ");
			}
		}
		else {
			System.out.println("ERROR. El DNI introducido no corresponde a ningún vecino de la comunidad " + com.get_nombreCom() + ".");
			System.out.println(" ");
		}
	}
	
	
	
	//Metodo que muestra el numero de sugerencias realizadas por un vecino (case 3)
	private static void numSugRealizadas(Comunidad com) {
		boolean dniErroneo;
		Vecino v3=null;
		String dni;
		System.out.println(" ");
		System.out.println("Introduzca el DNI del vecino del que quiera conocer el numero de sugerencias realizadas:");
		do {
			dniErroneo=false;
			dni=TECLADO.next();
			v3=com.encuentraVecinoDNI(dni);
			if(v3==null){
				
				dniErroneo=true;
				System.out.println("El dni introducido es erróneo o no se encuentra en la comundidad. Porfavor insertelo de nuevo "+
				"(debe contener 8 números y 1 letra obligatoriamente):");
				
			}
			else {
				
				dniErroneo=false;
				System.out.println(" ");
				System.out.println("El vecino " + v3.get_nombre() + " " + v3.get_apellidos() + " ha realizado un total de "
						+ v3.get_numSug() + " sugerencias.");
				System.out.println(" ");
			}
		}while(dniErroneo==true);
		
	}
	
	
	
	//Metodo que muestra el número de sugerencias URGENTES realizadas por un vecino (case 4)
	private static void sugURGENTESRealizadas(Comunidad com) {
		boolean dniErroneo;
		int numSugURGENTES = 0;
		Vecino v4=null;
		Sugerencia [] sug;
		String dni;
		System.out.println(" ");
		System.out.println("Introduzca el DNI del vecino del que quiera conocer toda la información sobre las sugerencias URGENTES:");
		do {
			dniErroneo=false;
			dni=TECLADO.next();
			System.out.println(" ");
			v4=com.encuentraVecinoDNI(dni);
			if(v4==null){
				
				dniErroneo=true;
				System.out.println("El dni introducido es erróneo o no se encuentra en la comundidad. Porfavor insertelo de nuevo "+
				"(debe contener 8 números y 1 letra obligatoriamente):");
				
			}
			else {
				
				dniErroneo=false;
				sug=v4.get_sugerencias();
				for(int i=0;i<v4.get_numSug();i++) {
					if(sug[i].get_prioridad() == cadPrioridad.URGENTE) {
						numSugURGENTES++;
						System.out.println("Sugerencia nº "+ (i+1) + sug[i].toString() + " ");
					}
				}
				
				System.out.println(" ");
				System.out.println("El vecino " + v4.get_nombre() + " " + v4.get_apellidos() + " ha realizado un total de "
						+ numSugURGENTES + " sugerencias URGENTES.");
				System.out.println(" ");
				
			}
		}while(dniErroneo==true);
	}
	
	
	
	//Metodo que muestra los ingresos totales de la comunidad -desglosado- (case 5) 
	private static void mostrarIngresosTotalesCom(Comunidad com) {
		
		Vecino vecinos[]=com.getVecinos();
		
		double total=0;
		double ingresosPropietarios=0;
		double ingresosInqAnuales=0;
		double ingresosInqMensuales=0;
		
		for(int i=0;i<com.get_numVecino();i++) {
			if(vecinos[i] instanceof Propietario) {
				
				Propietario p=(Propietario)vecinos[i];
				total = total + p.getImporteTotal();
				ingresosPropietarios = ingresosPropietarios + p.getImporteTotal();
			
			}
			else if(vecinos[i] instanceof InquilinoAnual) {
			
				InquilinoAnual in=(InquilinoAnual)vecinos[i];
				total = total + in.getImporteTotal();
				ingresosInqAnuales = ingresosInqAnuales + in.getImporteTotal();
			
			}
			else if (vecinos[i] instanceof InquilinoMensual) {
			
				InquilinoMensual m=(InquilinoMensual)vecinos[i];
				total = total + m.getImporteTotal();
				ingresosInqMensuales = ingresosInqMensuales + m.getImporteTotal();
			}	
		}
		System.out.println(" ");
		System.out.println("Ingresos totales de Propietarios: " + ingresosPropietarios + " euros. \n" +
						   "Ingresos totales de Inquilinos Mensuales:  " + ingresosInqMensuales + " euros. \n" +
						   "Ingresos totales de Inquilinos Anuales: " + ingresosInqAnuales + " euros. \n" +
						   "Ingresos totales de la comundidad: " + total + " euros.");
		System.out.println(" ");
		
	}
	
	
	
	//Metodo que muestra el importe de un vecino concreto (case 6) 
	private static void mostrarImporteVecino(Comunidad com) {
		boolean dniErroneo;
		double importe;
		Vecino v6=null;
		String dni;
		System.out.println(" ");
		System.out.println("Introduzca el DNI del vecino del que quiera conocer el importe:");
		do {
			dniErroneo=false;
			dni=TECLADO.next();
			v6=com.encuentraVecinoDNI(dni);
			while(v6==null) {
				System.out.println("ERROR. El DNI no se encuentra en la comunidad o no se ha escrito correctamente" 
						+ ". Por favor introduzcalo de nuevo: ");
				dni=TECLADO.next();
				v6=com.encuentraVecinoDNI(dni);
			}
			importe = v6.getImporteTotal();
			System.out.println("El vecino " + v6.get_nombre() + " " + v6.get_apellidos() + " paga un importe de " + importe + " euros al año.");
			System.out.println("");
		}while(dniErroneo==true);
	}
	
	
	
	//Metodo que ordena alfabeticamente los vecinos segun su nombre (case 7)
	private static void ordenAlfabeticoVecinos(Comunidad com) {
		Vecino [] vecinos = com.getVecinos();
		String nombresVecinos[]=new String[com.get_numVecino()];
		for(int i=0;i<com.get_numVecino();i++) {
			nombresVecinos[i]=vecinos[i].get_nombre();
		}
		
		for(int i=0;i<(com.get_numVecino());i++){// bucle anidado que intercambia los valores para ordenar alfabeticamente
			for(int j=i+1;j<com.get_numVecino();j++){
				if(nombresVecinos[i].compareToIgnoreCase(nombresVecinos[j])>0){
					//Intercambiamos valores
					String variableauxiliar=nombresVecinos[i];
					nombresVecinos[i]=nombresVecinos[j];
					nombresVecinos[j]=variableauxiliar;
				}	
			}
		}
		
		Vecino v=null;
		for(int i=0;i<com.get_numVecino();i++) {
			v=com.encuentraVecinoNombre(nombresVecinos[i]);//-- aqui se utiliza el metodo ecuentraVecinoNombre de Comunidad
			System.out.println(v.toString());
		}
	}

	
	
	//Metodo que ordena a los inquilinos anuales segun su cuota, de mayor a menor cuota (case 8)
	private static void ordenInqAnualesCuotas(Comunidad com) {
		
		Vecino v8[]=com.getVecinos();
		int numInqAnual=0;//numero de inquilinos anuales que tiene la comunidad
		
		//este for cuenta cuantos inquilinos anuales hay en la comunidad
		for(int i=0;i<com.get_numVecino();i++) {
			if(v8[i] instanceof InquilinoAnual ) {
				numInqAnual++;
			}
		}
		
		InquilinoAnual inquilinosAnuales[]=new InquilinoAnual[numInqAnual];
		int cont=0;
		
		//este for mete a todos lo inquilinos anuales de una comunidad en un vector
		for(int i=0;i<com.get_numVecino();i++) {
			if(v8[i] instanceof InquilinoAnual ) {
				inquilinosAnuales[cont]=(InquilinoAnual)v8[i];	
				cont++;
			}
		}
		
		//este for ordena los inq anuales de mayor a menor cuota 
		for(int i=0;i<numInqAnual;i++){
			for(int j=i+1;j<numInqAnual;j++){
				if(inquilinosAnuales[i].getImporteTotal()<inquilinosAnuales[j].getImporteTotal()){
					//Intercambiamos valores
					InquilinoAnual vAuxiliar=inquilinosAnuales[i];
					inquilinosAnuales[i]=inquilinosAnuales[j];
					inquilinosAnuales[j]=vAuxiliar;
				}	
			}
		} 
		
		//este for imprime el vector resultante
		for(int i=0;i<numInqAnual;i++) {
			System.out.println((i+1) + "º " + inquilinosAnuales[i].get_nombre()+" "+inquilinosAnuales[i].get_apellidos()+" : "+inquilinosAnuales[i].getImporteTotal()+" euros.");
		}
	}
	
	
	
	//Metodo que ordena a los propietarios según la fecha de adquisición del inmueble, del mas antiguo al mas reciente (case 9)
	private static void ordenPropietariosFecha(Comunidad com) {
		Vecino [] v9 = com.getVecinos();
		
		int numProp=0;//numero de propietarios que tiene la comunidad
		
		//este for cuenta cuantos propietarios hay en la comunidad
		for(int i=0;i<com.get_numVecino();i++) {
			if(v9[i] instanceof Propietario ) {
				numProp++;
			}
		}
		
		Propietario [] prop = new Propietario[numProp];
		int cont=0;
		
		//este for mete a todos los propietarios de una comunidad en un vector
		for(int i=0;i<com.get_numVecino();i++) {
			if(v9[i] instanceof Propietario ) {
				prop[cont]=(Propietario)v9[i];	
				cont++;
			}
		}
		
		for (int i=0;i<cont;i++) {
			for (int j=i+1;j<cont;j++) {
				if(prop[i].get_fechaAdq().getAño()>prop[j].get_fechaAdq().getAño()) {
					Propietario vAuxiliar=prop[i];
					prop[i]=prop[j];
					prop[j]=vAuxiliar;
				}
 /*else if 1*/	else if(prop[i].get_fechaAdq().getAño()==prop[j].get_fechaAdq().getAño()) {
					if(prop[i].get_fechaAdq().getMes()>prop[j].get_fechaAdq().getMes()) {
						Propietario vAuxiliar=prop[i];
						prop[i]=prop[j];
						prop[j]=vAuxiliar;
					}
 /*else if 2*/		else if(prop[i].get_fechaAdq().getMes()==prop[j].get_fechaAdq().getMes()) {
						if(prop[i].get_fechaAdq().getDia()>prop[j].get_fechaAdq().getDia()){
							Propietario vAuxiliar=prop[i];
							prop[i]=prop[j];
							prop[j]=vAuxiliar;
						}
					} // fin del else if 2
				} //fin del else if 1
			}
		}// fin del ultimo for
		
		//este for imprime el vector resultante
				for(int i=0;i<numProp;i++) {
					System.out.println((i+1) + "º " + prop[i].get_nombre()+" "+prop[i].get_apellidos()+" : "+prop[i].get_fechaAdq());
				}
	
	}
	
	
	
	//Metodo que muestra los 5 vecinos que mas ingresos generan, de mayor a menor (case 10)
	private static void top5VecinosMayorIngreso(Comunidad com) {
		Vecino [] v10 = com.getVecinos();
		Vecino vAux;
		
		//Ordenamiento de burbuja
		for (int i = 0; i < com.get_numVecino(); i++) {
	        for (int j = 1; j < (com.get_numVecino() - i); j++) {
	            if (v10[j - 1].getImporteTotal() < v10[j].getImporteTotal()) {
	                vAux = v10[j - 1];
	                v10[j - 1] = v10[j];
	                v10[j] = vAux;
	            }
	        }
	    }
		
		for(int i=0;i<5;i++) {
			System.out.println((i+1) + "º " + v10[i].get_nombre() + " " + v10[i].get_apellidos() + " : " + v10[i].getImporteTotal() + " euros.");
		}	
	}

	
	
	//Metodo que crea un nuevo vecino en la comunidad (case 11)
	private static void newVecino(Comunidad com) {
		boolean seguir, piscina, fechaErronea;
		Vecino vAux=null;
		int tipo, planta, meses, dia, mes, año, cuota, vip;
		long telefono;
		String dni, nombre, apellidos;
		char puerta;
		System.out.println("Indique que tipo de vecino será:  \n 1. Propietario \n 2. Inquilino Mensual" 
				+ "\n 3. Inquilino Anual (normal) \n 4. Inqulino Anual VIP");
		tipo = TECLADO.nextInt();
		while(tipo<1||tipo>4) {
			System.out.println("ERROR. Escoja una de las opciones que se indican:");
			System.out.println("Indique que tipo de vecino será:  \n 1. Propietario \n 2. Inquilino Mensual" 
					+ "\n 3. Inquilino Anual (normal) \n 4. Inqulino Anual VIP");
			tipo = TECLADO.nextInt();
		}
		
	
		System.out.println("DNI: ");
		dni = TECLADO.next();
		seguir=verificaDNI(dni);
		
		while(seguir == false){
			System.out.println("El dni no es correcto. Introduzcalo de nuevo por favor: ");
			dni = TECLADO.next();
			seguir=verificaDNI(dni);	
		}
		
		vAux=com.encuentraVecinoDNI(dni);
		if(vAux == null) {
			System.out.println("Nombre: ");
			nombre=TECLADO.next();
			
			System.out.println("Apellidos (utiliza  _  en vez de espacios[Juan Perez_Perez]: ");
			apellidos=TECLADO.next();
			
			System.out.println("Inmueble:");
			System.out.print("· Planta: ");
			planta = TECLADO.nextInt();
			System.out.print("· Puerta: ");
			puerta = TECLADO.next().charAt(0);
			while(Character.isLetter(puerta)==false) {
				System.out.println("ERROR. La puerta tiene que ser obligatoriamente una letra. Introducela de nuevo:");
				puerta = TECLADO.next().charAt(0);
			}
			Inmueble inmueble = new Inmueble(planta, puerta);
			
			System.out.println("Telefono: ");
			telefono=TECLADO.nextLong();
			
			switch (tipo) {
			case 1:
				System.out.println("Fecha de adquisición del inmueble: ");
				System.out.print("· Dia: ");
				dia=TECLADO.nextInt();
				System.out.print("· Mes: ");
				mes=TECLADO.nextInt();
				System.out.print("· Año: ");
				año=TECLADO.nextInt();
				fechaErronea=compruebaFechas(dia,mes,año);
				while(fechaErronea==true){
					System.out.println("ERROR al introducir la fecha. Por favor introduzcala de nuevo:");
					System.out.print("· Dia: ");
					dia=TECLADO.nextInt();
					System.out.print("· Mes: ");
					mes=TECLADO.nextInt();
					System.out.print("· Año: ");
					año=TECLADO.nextInt();
					fechaErronea=compruebaFechas(dia,mes,año);
				}
				Fecha fechaAdquisicion = new Fecha(dia, mes, año);
				
				System.out.println("Cuota: ");
				cuota=TECLADO.nextInt();
				
				Propietario p = new Propietario(dni, nombre, apellidos, inmueble, telefono, fechaAdquisicion, cuota);
				com.addVecino(p);
				break;
				
			case 2: 
				System.out.println("Fecha de ocupación del inmueble: ");
				System.out.print("· Dia: ");
				dia=TECLADO.nextInt();
				System.out.print("· Mes: ");
				mes=TECLADO.nextInt();
				System.out.print("· Año: ");
				año=TECLADO.nextInt();
				fechaErronea=compruebaFechas(dia,mes,año);
				while(fechaErronea==true){
					System.out.println("ERROR al introducir la fecha. Por favor introduzcala de nuevo:");
					System.out.print("· Dia: ");
					dia=TECLADO.nextInt();
					System.out.print("· Mes: ");
					mes=TECLADO.nextInt();
					System.out.print("· Año: ");
					año=TECLADO.nextInt();
					fechaErronea=compruebaFechas(dia,mes,año);
				}
				Fecha fechaOcupacionM = new Fecha(dia, mes, año);
				
				System.out.println("Cuota mensual: ");
				cuota=TECLADO.nextInt();
				
				System.out.println("Meses: ");
				meses=TECLADO.nextInt();
				
				System.out.println("Piscina (escriba true[SI] o false[NO]): ");
				piscina=TECLADO.nextBoolean();
				
				InquilinoMensual m = new InquilinoMensual(dni, nombre, apellidos, inmueble, telefono, fechaOcupacionM, cuota, meses, piscina);
				com.addVecino(m);
				break;
				
			case 3:
				System.out.println("Fecha de ocupación del inmueble: ");
				System.out.print("· Dia: ");
				dia=TECLADO.nextInt();
				System.out.print("· Mes: ");
				mes=TECLADO.nextInt();
				System.out.print("· Año: ");
				año=TECLADO.nextInt();
				fechaErronea=compruebaFechas(dia,mes,año);
				while(fechaErronea==true){
					System.out.println("ERROR al introducir la fecha. Por favor introduzcala de nuevo:");
					System.out.print("· Dia: ");
					dia=TECLADO.nextInt();
					System.out.print("· Mes: ");
					mes=TECLADO.nextInt();
					System.out.print("· Año: ");
					año=TECLADO.nextInt();
					fechaErronea=compruebaFechas(dia,mes,año);
				}
				Fecha fechaOcupacionA = new Fecha(dia, mes, año);
				
				System.out.println("Cuota anual: ");
				cuota=TECLADO.nextInt();
				
				System.out.println("Piscina (escriba true[SI] o false[NO]): ");
				piscina=TECLADO.nextBoolean();
				
				InquilinoAnual i = new InquilinoAnual(dni, nombre, apellidos, inmueble, telefono, fechaOcupacionA, cuota, piscina);
				com.addVecino(i);
				break;
			case 4:
				System.out.println("Fecha de ocupación del inmueble: ");
				System.out.print("· Dia: ");
				dia=TECLADO.nextInt();
				System.out.print("· Mes: ");
				mes=TECLADO.nextInt();
				System.out.print("· Año: ");
				año=TECLADO.nextInt();
				fechaErronea=compruebaFechas(dia,mes,año);
				while(fechaErronea==true){
					System.out.println("ERROR al introducir la fecha. Por favor introduzcala de nuevo:");
					System.out.print("· Dia: ");
					dia=TECLADO.nextInt();
					System.out.print("· Mes: ");
					mes=TECLADO.nextInt();
					System.out.print("· Año: ");
					año=TECLADO.nextInt();
					fechaErronea=compruebaFechas(dia,mes,año);
				}
				Fecha fechaOcupacionAv = new Fecha(dia, mes, año);
				
				System.out.println("Cuota anual: ");
				cuota=TECLADO.nextInt();
				
				System.out.println("Piscina (escriba true[SI] o false[NO]): ");
				piscina=TECLADO.nextBoolean();
				
				System.out.println("Numero VIP: ");
				vip=TECLADO.nextInt();
				
				InquilinoAnualVip iVip = new InquilinoAnualVip(dni, nombre, apellidos, inmueble, telefono, fechaOcupacionAv, cuota, piscina, vip);
				com.addVecino(iVip);
				break;
			}
			
		}
		else {
			System.out.println("No es posile crear un vecino con este DNI porque es el de uno de los vecinos.");
		}
	}
	
	
	
	//Metodo que devuelve un String con la fecha actualizada
	private static String fechaActual() {
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd'/'MM'/'yyyy");
		Date fechaActual = new Date();
		String fechaString = formateador.format(fechaActual);
				   
		return fechaString;
	}
	
	
	
	//Metodo que comprueba la sintaxis de una fecha
	private static boolean compruebaFechas(int dia, int mes, int año) {
		boolean fechaErronea = false;
		if((dia<1 || dia>31) && (mes==1||mes==3||mes==5||mes==7||mes==8||mes==10||mes==12)) {
			fechaErronea=true;
		}
		else if((dia<1 || dia>30) && (mes==4||mes==6||mes==9||mes==11)) {
			fechaErronea=true;
		}
		else if((dia<1 || dia>28) && (mes==2)) {
			fechaErronea=true;
		}
		else if(mes<1 || mes>12) {
			fechaErronea=true;
		}
		else if(año<1900|| año>2019) {
			fechaErronea=true;
		}
		return fechaErronea;
	}
}


