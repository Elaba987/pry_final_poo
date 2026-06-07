import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.io.*;

// Clase principal - flujo de menu basado en Main.java
// Patron Main.java -> Scanner sc = new Scanner(System.in); while(opcion != 7) { switch(opcion) { } }
// Patron Mascotas.java -> instanceof y cast (Tipo)objeto
// Patron DiaFavorito2.java -> TipoGraduacion[] tipos = TipoGraduacion.values(); for(Dia d: Dia.values())
// Patron MiConteo.java -> static variables compartidas
// Patron BufferStream.java / EntradaTeclado.java -> lectura de Scanner / IO
public class VisionMaster {

	// Scanner estatico - patron Main.java -> Scanner sc = new Scanner(System.in);
	static Scanner sc = new Scanner(System.in);
	// Tienda estatica - patron de variable compartida como MiConteo.java
	static Tienda tienda = new Tienda();

	public static void main(String[] args) {
		System.out.println("========================================");
		System.out.println("  OPTICA " + Tienda.getNombre());
		System.out.println("  Sistema de Gestion");
		System.out.println("========================================");

		int opcion = 0;

		// while - patron Main.java -> while(opcion != 7)
		while(opcion != 5) {
			System.out.println("\n--- MENU PRINCIPAL ---");
			System.out.println("1. Nueva compra");
			System.out.println("2. Administrar clientes");
			System.out.println("3. Almacen");
			System.out.println("4. Ver citas");
			System.out.println("5. Salir");
			System.out.print("Opcion: ");
			opcion = leerEntero();

			// switch - patron Switch.java -> switch(i) { case 3: ... break; }
			switch(opcion) {
				case 1: nuevaCompra();      break;
				case 2: menuClientes();     break;
				case 3: menuAlmacen();      break;
				case 4: mostrarCitas();     break;
				case 5: System.out.println("Hasta luego. Ventas: " + OrdenCompra.getTotalVentas()); break;
				default: System.out.println("Opcion no valida."); break;
			}
		}
	}

	// =====================================================================
	// FLUJO NUEVA COMPRA
	// =====================================================================

	static void nuevaCompra() {
		Cliente cliente = seleccionarOCrearCliente();
		if(cliente == null) {
			System.out.println("Compra cancelada.");
			return;
		}

		OrdenCompra orden = new OrdenCompra(cliente);
		configurarProducto(orden);

		// lista.isEmpty() - patron Colecciones.java
		if(orden.getCarrito().isEmpty()) {
			System.out.println("Sin productos. Compra cancelada.");
			return;
		}

		agendarCita(orden, cliente);
		pago(orden);
		resumenYDescarga(orden, cliente);
	}

	static Cliente seleccionarOCrearCliente() {
		System.out.println("\n--- Seleccionar Cliente ---");
		System.out.println("1. Usar cliente existente");
		System.out.println("2. Registrar nuevo cliente");
		System.out.println("3. Cancelar");
		System.out.print("Opcion: ");
		int opc = leerEntero();

		// if/else if - patron If.java
		if(opc == 1) {
			return elegirClienteDeLista();
		} else if(opc == 2) {
			return crearCliente();
		}
		return null;
	}

	static Cliente elegirClienteDeLista() {
		List<Cliente> clientes = tienda.getClientes();
		// lista.isEmpty() - patron Colecciones.java
		if(clientes.isEmpty()) {
			System.out.println("No hay clientes registrados.");
			return null;
		}
		System.out.println("\n-- Clientes --");
		// for con indice - patron Main.java -> for (int i = 0; i < lista.size(); i++)
		for(int i = 0; i < clientes.size(); i++) {
			System.out.println((i + 1) + ". " + clientes.get(i));
		}
		System.out.print("Elige numero (0=cancelar): ");
		int idx = leerEntero() - 1;
		if(idx >= 0 && idx < clientes.size()) {
			return clientes.get(idx);
		}
		return null;
	}

	static Cliente crearCliente() {
		System.out.println("\n-- Nuevo Cliente --");
		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Telefono: ");
		String telefono = sc.nextLine();
		System.out.print("Correo: ");
		String correo = sc.nextLine();
		// new Cliente - patron Agencia.java -> Automovil auto1 = new Automovil("Audi R8");
		Cliente c = new Cliente(nombre, telefono, correo);
		tienda.registrarCliente(c);
		System.out.println("Cliente registrado: " + c);
		return c;
	}

	// =====================================================================
	// PASO 2 - Configuracion del producto
	// =====================================================================

	static void configurarProducto(OrdenCompra orden) {
		System.out.println("\n--- PASO 2: Seleccion de Productos ---");

		int opc = 0;
		// while con condicion - patron Main.java
		while(opc != 3) {
			System.out.println("\nCarrito: " + orden.getCarrito().size() + " producto(s) | Subtotal: $" + orden.getSubtotal());
			System.out.println("1. Ver armazones");
			System.out.println("2. Ver accesorios");
			System.out.println("3. Terminar seleccion");
			System.out.print("Opcion: ");
			opc = leerEntero();

			if(opc == 1) {
				elegirArmazon(orden);
			} else if(opc == 2) {
				elegirAccesorio(orden);
			}
		}

		System.out.println("\nCarrito final:");
		int num = 1;
		// for-each - patron Main.java -> for (Animal a : lista)
		for(Producto p : orden.getCarrito()) {
			System.out.println(num + ". " + p);
			num++;
		}
		System.out.println("Subtotal: $" + orden.getSubtotal());
	}

	static void elegirArmazon(OrdenCompra orden) {
		// Filtrar armazones - instanceof como en Mascotas.java -> if(animal instanceof Perro)
		List<Producto> armazones = new ArrayList<>();
		for(Producto p : tienda.getInventario()) {
			if(p instanceof Armazon) {
				armazones.add(p);
			}
		}

		System.out.println("\n-- Armazones --");
		for(int i = 0; i < armazones.size(); i++) {
			// Cast como en Mascotas.java -> Perro p = (Perro)animal;
			System.out.println((i + 1) + ". " + ((Armazon) armazones.get(i)).toStringCompleto());
		}
		System.out.println("0. Volver al menu anterior");
		System.out.print("Elige numero: ");
		int idx = leerEntero() - 1;
		if(idx < 0 || idx >= armazones.size()) {
			return;
		}

		// Cast - patron Mascotas.java -> Gato g = (Gato) Mascotas.comprar();
		Armazon base = (Armazon) armazones.get(idx);

		// Elegir graduacion con enum - patron DiaFavorito2.java -> for(Dia d: Dia.values())
		System.out.println("\n-- Tipo de lente para este armazon --");
		TipoGraduacion[] tipos = TipoGraduacion.values();
		// for con indice - patron For.java -> for(int i=0; i<amigos.length; i++)
		for(int i = 0; i < tipos.length; i++) {
			System.out.println((i + 1) + ". " + tipos[i].getNombre());
		}
		System.out.println("0. Volver al menu anterior");
		System.out.print("Opcion: ");
		int tIdx = leerEntero() - 1;
		if(tIdx < 0 || tIdx >= tipos.length) {
			return;
		}

		double precioFinal = base.getPrecio() + base.getMaterial().getPrecio();
		String nombreConGrad = base.getNombre() + " (" + tipos[tIdx].getNombre() + ")";
		Armazon nuevo = new Armazon(nombreConGrad, precioFinal, base.getMaterial(), base.getEstilo());
		orden.agregarProducto(nuevo);
		System.out.println("Agregado: " + base.getNombre() + " con lente " + tipos[tIdx].getNombre());
	}

	static void elegirAccesorio(OrdenCompra orden) {
		List<Producto> accesorios = new ArrayList<>();
		// instanceof - patron Mascotas.java
		for(Producto p : tienda.getInventario()) {
			if(p instanceof Accesorio) {
				accesorios.add(p);
			}
		}

		System.out.println("\n-- Accesorios --");
		for(int i = 0; i < accesorios.size(); i++) {
			System.out.println((i + 1) + ". " + accesorios.get(i));
		}
		System.out.println("0. Volver al menu anterior");
		System.out.print("Elige numero: ");
		int idx = leerEntero() - 1;
		if(idx < 0 || idx >= accesorios.size()) {
			return;
		}

		orden.agregarProducto(accesorios.get(idx));
		System.out.println("Agregado: " + accesorios.get(idx).getNombre());
	}

	// =====================================================================
	// PASO 3 - Agendar cita
	// =====================================================================

	static void agendarCita(OrdenCompra orden, Cliente cliente) {
		System.out.println("\n--- PASO 3: Agendar Cita ---");
		System.out.println("1. Cita de examen");
		System.out.println("2. Cita de entrega");
		System.out.println("3. Sin cita por ahora");
		System.out.print("Opcion: ");
		int opc = leerEntero();

		if(opc == 3) {
			return;
		}

		System.out.print("Fecha (ej. 15/07/2025): ");
		String fecha = sc.nextLine();
		System.out.print("Hora  (ej. 10:30): ");
		String hora = sc.nextLine();

		// Polimorfismo - patron Mascotas.java -> Animal[] a = { new Animal(), new Perro(), new Gato() }
		Cita cita;
		if(opc == 1) {
			System.out.print("Es gratuita? (s/n): ");
			boolean gratuita = sc.nextLine().equalsIgnoreCase("s");
			// new CitaExamen - herencia como Constructores1.java
			cita = new CitaExamen(fecha, hora, cliente.getNombre(), gratuita);
		} else {
			cita = new CitaEntrega(fecha, hora, cliente.getNombre(), orden.getFolio());
		}

		tienda.agendarCita(cita);
		orden.setCita(cita);
		System.out.println("Cita agendada: " + cita);
	}

	// =====================================================================
	// PASO 4 - Pago
	// =====================================================================

	static void pago(OrdenCompra orden) {
		System.out.println("\n--- PASO 4: Pago ---");
		System.out.println("Subtotal: $" + orden.getSubtotal());
		if(orden.isCuponAplicado()) {
			System.out.println("CUPON APLICADO! Descuento: -$" + orden.getDescuento());
		}
		System.out.println("TOTAL: $" + orden.getTotal());
	}

	// =====================================================================
	// PASO 5 - Resumen y descarga
	// =====================================================================

	static void resumenYDescarga(OrdenCompra orden, Cliente cliente) {
		orden.cerrarCompra();

		System.out.println("\n--- PASO 5: Resumen ---");
		System.out.println("Folio   : #" + orden.getFolio());
		System.out.println("Cliente : " + cliente.getNombre());
		System.out.println("Subtotal: $" + orden.getSubtotal());
		if(orden.isCuponAplicado()) {
			System.out.println("Descuento (cupon previo): -$" + orden.getDescuento());
		}
		System.out.println("Total   : $" + orden.getTotal());

		if(orden.isGanoNuevoCupon()) {
			System.out.println("** Ganaste un cupon de $400 para tu proxima compra! **");
		} else {
			System.out.println("(Compra minima $4000 para ganar cupon de $400)");
		}

		System.out.print("Descargar ticket .txt? (s/n): ");
		if(sc.nextLine().equalsIgnoreCase("s")) {
			String ruta = "ticket_" + orden.getFolio() + ".txt";
			// exportarAArchivo - implementacion de Descargable
			// Patron Contrato.java -> cc.metodo3(); // la implementacion de la interfaz
			orden.exportarAArchivo(ruta);
		}

		System.out.println("Compra finalizada. Ventas totales: " + OrdenCompra.getTotalVentas());
	}

	// =====================================================================
	// MENU ADMINISTRAR CLIENTES
	// =====================================================================

	static void menuClientes() {
		int opc = 0;
		while(opc != 6) {
			System.out.println("\n--- ADMINISTRAR CLIENTES ---");
			System.out.println("1. Registrar nuevo cliente");
			System.out.println("2. Ver lista de clientes");
			System.out.println("3. Editar cliente");
			System.out.println("4. Ver citas de un cliente");
			System.out.println("5. Eliminar cliente");
			System.out.println("6. Volver");
			System.out.print("Opcion: ");
			opc = leerEntero();

			switch(opc) {
				case 1: crearCliente();         break;
				case 2: verListaClientes();     break;
				case 3: editarCliente();        break;
				case 4: verCitasDeCliente();    break;
				case 5: eliminarCliente();      break;
				case 6:                         break;
				default: System.out.println("Opcion no valida."); break;
			}
		}
	}

	static void verListaClientes() {
		List<Cliente> clientes = tienda.getClientes();
		System.out.println("\n-- Lista de Clientes --");
		if(clientes.isEmpty()) {
			System.out.println("No hay clientes registrados.");
			return;
		}

		System.out.println("Ordenar por:");
		System.out.println("1. ID menor a mayor");
		System.out.println("2. ID mayor a menor");
		System.out.println("3. Nombre A-Z");
		System.out.println("4. Nombre Z-A");
		System.out.print("Opcion: ");
		int ordenOpc = leerEntero();

		// Comparador - patron OrdenarXComparator.java
		Comparator<Cliente> comparador;
		if(ordenOpc == 2) {
			comparador = new ComparadorClienteIdDesc();
		} else if(ordenOpc == 3) {
			comparador = new ComparadorClienteNombreAZ();
		} else if(ordenOpc == 4) {
			comparador = new ComparadorClienteNombreZA();
		} else {
			comparador = new ComparadorClienteIdAsc();
		}

		List<Cliente> ordenados = tienda.getClientesOrdenados(comparador);
		for(int i = 0; i < ordenados.size(); i++) {
			System.out.println((i + 1) + ". " + ordenados.get(i));
		}
	}

	static void editarCliente() {
		Cliente c = elegirClienteDeLista();
		if(c == null) {
			return;
		}

		System.out.println("\nCliente: " + c);
		System.out.println("Que deseas modificar?");
		System.out.println("1. Nombre");
		System.out.println("2. Telefono");
		System.out.println("3. Correo");
		System.out.println("4. Todo");
		System.out.println("0. Volver");
		System.out.print("Opcion: ");
		int opc = leerEntero();

		if(opc == 0) {
			return;
		}
		if(opc == 1 || opc == 4) {
			System.out.print("Nuevo nombre (" + c.getNombre() + "): ");
			c.setNombre(sc.nextLine());
		}
		if(opc == 2 || opc == 4) {
			System.out.print("Nuevo telefono (" + c.getTelefono() + "): ");
			c.setTelefono(sc.nextLine());
		}
		if(opc == 3 || opc == 4) {
			System.out.print("Nuevo correo (" + c.getCorreo() + "): ");
			c.setCorreo(sc.nextLine());
		}
		System.out.println("Cliente actualizado: " + c);
	}

	static void verCitasDeCliente() {
		Cliente c = elegirClienteDeLista();
		if(c == null) {
			return;
		}

		List<Cita> citas = tienda.getCitasDeCliente(c.getNombre());
		System.out.println("\nCitas de " + c.getNombre() + ":");
		if(citas.isEmpty()) {
			System.out.println("  Sin citas registradas.");
		} else {
			for(int i = 0; i < citas.size(); i++) {
				System.out.println((i + 1) + ". " + citas.get(i));
			}
		}
		System.out.println("Historial de compras:");
		// lista.isEmpty() - patron Colecciones.java
		if(c.getHistorial().isEmpty()) {
			System.out.println("  Sin compras registradas.");
		} else {
			// for-each - patron Main.java
			for(String h : c.getHistorial()) {
				System.out.println("  - " + h);
			}
		}
	}

	static void eliminarCliente() {
		List<Cliente> clientes = tienda.getClientes();
		if(clientes.isEmpty()) {
			System.out.println("No hay clientes registrados.");
			return;
		}
		System.out.println("\n-- Eliminar Cliente --");
		for(int i = 0; i < clientes.size(); i++) {
			System.out.println((i + 1) + ". " + clientes.get(i));
		}
		System.out.println("0. Volver");
		System.out.print("Elige numero: ");
		int idx = leerEntero() - 1;
		if(idx >= 0 && idx < clientes.size()) {
			System.out.println("Eliminado: " + clientes.get(idx).getNombre());
			tienda.eliminarCliente(idx);
		}
	}

	// =====================================================================
	// MENU ALMACEN
	// =====================================================================

	static void menuAlmacen() {
		int opc = 0;
		while(opc != 5) {
			System.out.println("\n--- ALMACEN ---");
			System.out.println("1. Ver inventario");
			System.out.println("2. Agregar producto");
			System.out.println("3. Editar producto");
			System.out.println("4. Eliminar producto");
			System.out.println("5. Volver");
			System.out.print("Opcion: ");
			opc = leerEntero();

			switch(opc) {
				case 1: verInventario();    break;
				case 2: agregarProducto();  break;
				case 3: editarProducto();   break;
				case 4: eliminarProducto(); break;
				case 5:                     break;
				default: System.out.println("Opcion no valida."); break;
			}
		}
	}

	static void verInventario() {
		System.out.println("\n-- Ver Inventario --");
		System.out.println("1. Solo armazones");
		System.out.println("2. Solo accesorios");
		System.out.println("3. Todos");
		System.out.println("0. Volver");
		System.out.print("Opcion: ");
		int filtroOpc = leerEntero();
		if(filtroOpc == 0) {
			return;
		}

		String filtro;
		if(filtroOpc == 1) {
			filtro = "armazones";
		} else if(filtroOpc == 2) {
			filtro = "accesorios";
		} else {
			filtro = "todos";
		}

		System.out.println("Ordenar por:");
		System.out.println("1. Nombre A-Z");
		System.out.println("2. Nombre Z-A");
		System.out.println("3. Precio menor a mayor");
		System.out.println("4. Precio mayor a menor");
		System.out.print("Opcion: ");
		int ordenOpc = leerEntero();

		// Comparadores - patron OrdenarXComparator.java
		Comparator<Producto> comparador;
		if(ordenOpc == 1) {
			comparador = new ComparadorNombreAZ();
		} else if(ordenOpc == 2) {
			comparador = new ComparadorNombreZA();
		} else if(ordenOpc == 4) {
			comparador = new ComparadorPrecioDesc();
		} else {
			comparador = new ComparadorPrecio();
		}

		List<Producto> lista = tienda.getListaOrdenada(filtro, comparador);
		if(lista.isEmpty()) {
			System.out.println("Sin productos en esta seccion.");
			return;
		}

		System.out.println("\n-- Inventario --");
		for(int i = 0; i < lista.size(); i++) {
			// instanceof - patron Mascotas.java -> if(animal instanceof Perro)
			// cast - patron Mascotas.java -> Perro p = (Perro)animal;
			if(lista.get(i) instanceof Armazon) {
				System.out.println((i + 1) + ". " + ((Armazon) lista.get(i)).toStringCompleto());
			} else {
				System.out.println((i + 1) + ". " + lista.get(i));
			}
		}
	}

	static void agregarProducto() {
		System.out.println("\n-- Agregar Producto --");
		System.out.println("1. Armazon");
		System.out.println("2. Accesorio");
		System.out.println("0. Volver");
		System.out.print("Opcion: ");
		int tipo = leerEntero();
		if(tipo == 0) {
			return;
		}

		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Precio: ");
		double precio = Double.parseDouble(sc.nextLine());

		if(tipo == 1) {
			// enum values() - patron DiaFavorito2.java -> for(Dia d: Dia.values())
			MaterialLente[] materiales = MaterialLente.values();
			for(int i = 0; i < materiales.length; i++) {
				System.out.println((i + 1) + ". " + materiales[i].getNombre());
			}
			System.out.print("Opcion material: ");
			int mIdx = leerEntero() - 1;
			if(mIdx < 0 || mIdx >= materiales.length) {
				return;
			}

			System.out.print("Estilo (ej. Cuadrado, Redondo, Aviador): ");
			String estilo = sc.nextLine();
			Armazon nuevo = new Armazon(nombre, precio, materiales[mIdx], estilo);
			tienda.agregarProducto(nuevo);
			System.out.println("Armazon agregado: " + nuevo.toStringCompleto());

		} else if(tipo == 2) {
			System.out.print("Tipo (ej. Estuche, Cadena): ");
			String tipoAcc = sc.nextLine();
			Accesorio nuevo = new Accesorio(nombre, precio, tipoAcc);
			tienda.agregarProducto(nuevo);
			System.out.println("Accesorio agregado: " + nuevo);
		}
	}

	static void editarProducto() {
		List<Producto> inv = tienda.getInventario();
		if(inv.isEmpty()) {
			System.out.println("Inventario vacio.");
			return;
		}

		System.out.println("\n-- Editar Producto --");
		System.out.println("Que tipo deseas editar?");
		System.out.println("1. Armazones");
		System.out.println("2. Accesorios");
		System.out.println("3. Todos");
		System.out.println("0. Volver");
		System.out.print("Opcion: ");
		int filtroOpc = leerEntero();
		if(filtroOpc == 0) {
			return;
		}

		List<Producto> lista = new ArrayList<>();
		for(Producto p : inv) {
			if(filtroOpc == 1 && p instanceof Armazon) {
				lista.add(p);
			} else if(filtroOpc == 2 && p instanceof Accesorio) {
				lista.add(p);
			} else if(filtroOpc == 3) {
				lista.add(p);
			}
		}

		if(lista.isEmpty()) {
			System.out.println("Sin productos en esta seccion.");
			return;
		}

		for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i) instanceof Armazon) {
				System.out.println((i + 1) + ". " + ((Armazon) lista.get(i)).toStringCompleto());
			} else {
				System.out.println((i + 1) + ". " + lista.get(i));
			}
		}
		System.out.println("0. Volver");
		System.out.print("Elige numero: ");
		int idx = leerEntero() - 1;
		if(idx < 0 || idx >= lista.size()) {
			return;
		}

		Producto p = lista.get(idx);
		// instanceof + cast - patron Mascotas.java
		if(p instanceof Armazon) {
			editarArmazon((Armazon) p);
		} else if(p instanceof Accesorio) {
			editarAccesorio((Accesorio) p);
		}
	}

	static void editarArmazon(Armazon a) {
		System.out.println("\nArmazon: " + a.toStringCompleto());
		System.out.println("1. Nombre  2. Precio  3. Material  4. Estilo  5. Todo  0. Volver");
		System.out.print("Opcion: ");
		int opc = leerEntero();
		if(opc == 0) {
			return;
		}

		if(opc == 1 || opc == 5) {
			System.out.print("Nuevo nombre (" + a.getNombre() + "): ");
			a.setNombre(sc.nextLine());
		}
		if(opc == 2 || opc == 5) {
			System.out.print("Nuevo precio (" + a.getPrecio() + "): ");
			a.setPrecio(Double.parseDouble(sc.nextLine()));
		}
		if(opc == 3 || opc == 5) {
			MaterialLente[] materiales = MaterialLente.values();
			for(int i = 0; i < materiales.length; i++) {
				System.out.println((i + 1) + ". " + materiales[i].getNombre());
			}
			System.out.print("Nuevo material: ");
			int mIdx = leerEntero() - 1;
			if(mIdx >= 0 && mIdx < materiales.length) {
				a.setMaterial(materiales[mIdx]);
			}
		}
		if(opc == 4 || opc == 5) {
			System.out.print("Nuevo estilo (" + a.getEstilo() + "): ");
			a.setEstilo(sc.nextLine());
		}
		System.out.println("Armazon actualizado: " + a.toStringCompleto());
	}

	static void editarAccesorio(Accesorio a) {
		System.out.println("\nAccesorio: " + a);
		System.out.println("1. Nombre  2. Precio  3. Tipo  4. Todo  0. Volver");
		System.out.print("Opcion: ");
		int opc = leerEntero();
		if(opc == 0) {
			return;
		}

		if(opc == 1 || opc == 4) {
			System.out.print("Nuevo nombre (" + a.getNombre() + "): ");
			a.setNombre(sc.nextLine());
		}
		if(opc == 2 || opc == 4) {
			System.out.print("Nuevo precio (" + a.getPrecio() + "): ");
			a.setPrecio(Double.parseDouble(sc.nextLine()));
		}
		if(opc == 3 || opc == 4) {
			System.out.print("Nuevo tipo (" + a.getTipo() + "): ");
			a.setTipo(sc.nextLine());
		}
		System.out.println("Accesorio actualizado: " + a);
	}

	static void eliminarProducto() {
		List<Producto> inv = tienda.getInventario();
		if(inv.isEmpty()) {
			System.out.println("Inventario vacio.");
			return;
		}
		System.out.println("\n-- Eliminar Producto --");
		for(int i = 0; i < inv.size(); i++) {
			if(inv.get(i) instanceof Armazon) {
				System.out.println((i + 1) + ". " + ((Armazon) inv.get(i)).toStringCompleto());
			} else {
				System.out.println((i + 1) + ". " + inv.get(i));
			}
		}
		System.out.println("0. Volver");
		System.out.print("Elige numero: ");
		int idx = leerEntero() - 1;
		if(idx >= 0 && idx < inv.size()) {
			System.out.println("Eliminado: " + inv.get(idx).getNombre());
			tienda.eliminarProducto(idx);
		}
	}

	// =====================================================================
	// CITAS
	// =====================================================================

	static void mostrarCitas() {
		System.out.println("\n--- CITAS AGENDADAS ---");
		List<Cita> citas = tienda.getCitas();
		if(citas.isEmpty()) {
			System.out.println("Sin citas registradas.");
			return;
		}
		for(int i = 0; i < citas.size(); i++) {
			System.out.println((i + 1) + ". " + citas.get(i));
		}

		System.out.print("Cambiar estado de una cita? (s/n): ");
		if(sc.nextLine().equalsIgnoreCase("s")) {
			System.out.print("Numero de cita: ");
			int idx = leerEntero() - 1;
			if(idx >= 0 && idx < citas.size()) {
				System.out.println("1.PENDIENTE  2.CONFIRMADA  3.CANCELADA");
				System.out.print("Nuevo estado: ");
				int est = leerEntero() - 1;
				// enum values() - patron DiaFavorito2.java -> Dia.values()
				EstadoCita[] estados = EstadoCita.values();
				if(est >= 0 && est < estados.length) {
					citas.get(idx).setEstado(estados[est]);
					System.out.println("Estado actualizado: " + citas.get(idx).getEstado().getNombre());
				}
			}
		}
	}

	// Leer entero con try/catch - patron DivisionException.java
	// -> try { } catch(MateException | ArithmeticException ma) { }
	static int leerEntero() {
		try {
			return Integer.parseInt(sc.nextLine());
		} catch(NumberFormatException e) {
			return -1;
		}
	}
}
