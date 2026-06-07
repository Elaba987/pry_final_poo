// Esta clase es la que administra todo: el inventario, los clientes y las citas

// instanceof para distinguir entre Armazon y Accesorio

// Los atributos son:
//   nombre (static String), inventario (List<Producto>),
//   citas (List<Cita>), clientes (List<Cliente>)
//
// Los métodos que tiene son:
//   getNombre() (static), validarCupon() (static),
//   registrarCliente(), eliminarCliente(), getClientes(),
//   agendarCita(), getCitas(), getCitasDeCliente(),
//   agregarProducto(), eliminarProducto(), getInventario(),
//   getListaOrdenada(), getClientesOrdenados()

// Recuerden que Tienda usa (tiene referencia a) Cliente, Cita, Producto,Armazon y Accesorio
// En el diagrama eso se dibuja con líneas de asociación/agregación.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Tienda {

	private static String nombre = "VisionMaster";

	private List<Producto> inventario;
	private List<Cita> citas;
	private List<Cliente> clientes;

	public Tienda() {
		inventario = new ArrayList<>();
		citas = new ArrayList<>();
		clientes = new ArrayList<>();
		cargarInventario();
	}

	private void cargarInventario() {
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

	public static boolean validarCupon(double monto) {
		return OrdenCompra.calificaParaCupon(monto);
	}

	public static String getNombre() {
		return nombre;
	}

	public void registrarCliente(Cliente c) {
		clientes.add(c);
	}
	public void eliminarCliente(int indice) {
		clientes.remove(indice);
	}
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void agendarCita(Cita c) {
		citas.add(c);
	}
	public List<Cita> getCitas() {
		return citas;
	}

	public List<Cita> getCitasDeCliente(String nombreCliente) {
		List<Cita> resultado = new ArrayList<>();
		for(Cita c : citas) {
			if(c.getNombreCliente().equals(nombreCliente)) {
				resultado.add(c);
			}
		}
		return resultado;
	}

	public void agregarProducto(Producto p) {
		inventario.add(p);
	}
	public void eliminarProducto(int indice) {
		inventario.remove(indice);
	}
	public List<Producto> getInventario() {
		return inventario;
	}

	public List<Producto> getListaOrdenada(String filtro, Comparator<Producto> comparador) {
		List<Producto> resultado = new ArrayList<>();
		for(Producto p : inventario) {
			if(filtro.equals("armazones") && p instanceof Armazon) {
				resultado.add(p);
			} else if(filtro.equals("accesorios") && p instanceof Accesorio) {
				resultado.add(p);
			} else if(filtro.equals("todos")) {
				resultado.add(p);
			}
		}
		Collections.sort(resultado, comparador);
		return resultado;
	}

	public List<Cliente> getClientesOrdenados(Comparator<Cliente> comparador) {
		List<Cliente> resultado = new ArrayList<>(clientes);
		Collections.sort(resultado, comparador);
		return resultado;
	}
}
