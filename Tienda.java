import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Clase Tienda - gestora del sistema
// Colecciones como en Colecciones.java -> List<Object> lista = new ArrayList<>();
// Variables y metodos estaticos como en MiConteo.java -> public static int conteo = 0;
// Collections.sort + Comparator como en Main.java -> lista.sort(Comparator.comparing(...))
// instanceof para filtrar - patron Mascotas.java -> if(animal instanceof Perro)
public class Tienda {

	// Variable estatica - patron MiConteo.java -> public static int conteo = 0;
	private static String nombre = "VisionMaster";

	// Colecciones - patron Colecciones.java -> List<Object> lista = new ArrayList<>();
	private List<Producto> inventario;
	private List<Cita> citas;
	private List<Cliente> clientes;

	public Tienda() {
		// ArrayList - patron Colecciones.java
		inventario = new ArrayList<>();
		citas = new ArrayList<>();
		clientes = new ArrayList<>();
		cargarInventario();
	}

	// Carga el inventario inicial con objetos instanciados
	private void cargarInventario() {
		// new Armazon(...) - patron Agencia.java -> Automovil auto1 = new Automovil("Audi R8");
		inventario.add(new Armazon("Ray-Ban Clasico",  2500, MaterialLente.POLICARBONATO, "Aviador"));
		inventario.add(new Armazon("Oakley Sport",     3200, MaterialLente.POLICARBONATO, "Cuadrado"));
		inventario.add(new Armazon("Vogue Elegance",   1800, MaterialLente.RESINA,        "Redondo"));
		inventario.add(new Armazon("Gucci Premium",    5500, MaterialLente.CRISTAL,       "Cuadrado"));
		inventario.add(new Armazon("BabyVision Kids",   900, MaterialLente.RESINA,        "Redondo"));
		inventario.add(new Armazon("BlueBlock Office", 1500, MaterialLente.POLICARBONATO, "Cuadrado"));
		inventario.add(new Accesorio("Estuche Premium", 150, "Estuche"));
		inventario.add(new Accesorio("Kit Limpieza",     80, "Limpiador"));
		inventario.add(new Accesorio("Cadena Dorada",   120, "Cadena"));
	}

	// Metodo estatico - patron MiConteo.java -> public static int getConteo()
	public static boolean validarCupon(double monto) {
		return OrdenCompra.calificaParaCupon(monto);
	}

	public static String getNombre() {
		return nombre;
	}

	// --- Metodos de Clientes ---
	// lista.add() - patron Colecciones.java
	public void registrarCliente(Cliente c) {
		clientes.add(c);
	}
	// lista.remove() - patron Colecciones.java -> lista.remove("tres")
	public void eliminarCliente(int indice) {
		clientes.remove(indice);
	}
	public List<Cliente> getClientes() {
		return clientes;
	}

	// --- Metodos de Citas ---
	public void agendarCita(Cita c) {
		citas.add(c);
	}
	public List<Cita> getCitas() {
		return citas;
	}

	// Filtrar citas por nombre de cliente - for-each + equals como en Main.java
	public List<Cita> getCitasDeCliente(String nombreCliente) {
		List<Cita> resultado = new ArrayList<>();
		for(Cita c : citas) {
			if(c.getNombreCliente().equals(nombreCliente)) {
				resultado.add(c);
			}
		}
		return resultado;
	}

	// --- Metodos de Inventario ---
	public void agregarProducto(Producto p) {
		inventario.add(p);
	}
	public void eliminarProducto(int indice) {
		inventario.remove(indice);
	}
	public List<Producto> getInventario() {
		return inventario;
	}

	// Lista filtrada y ordenada
	// instanceof - patron Mascotas.java -> if(animal instanceof Perro)
	// Collections.sort - patron Main.java -> lista.sort(Comparator.comparing(...))
	public List<Producto> getListaOrdenada(String filtro, Comparator<Producto> comparador) {
		List<Producto> resultado = new ArrayList<>();
		// for-each e instanceof - patron Mascotas.java
		for(Producto p : inventario) {
			if(filtro.equals("armazones") && p instanceof Armazon) {
				resultado.add(p);
			} else if(filtro.equals("accesorios") && p instanceof Accesorio) {
				resultado.add(p);
			} else if(filtro.equals("todos")) {
				resultado.add(p);
			}
		}
		// Collections.sort con comparador - patron OrdenarXComparator.java
		// -> Collections.sort(lista); usando Comparator
		Collections.sort(resultado, comparador);
		return resultado;
	}

	// Clientes ordenados - patron Main.java -> lista.sort(Comparator.comparing(...))
	public List<Cliente> getClientesOrdenados(Comparator<Cliente> comparador) {
		List<Cliente> resultado = new ArrayList<>(clientes);
		Collections.sort(resultado, comparador);
		return resultado;
	}
}
