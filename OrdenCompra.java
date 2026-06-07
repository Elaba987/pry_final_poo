import java.util.ArrayList;
import java.util.List;
import java.io.*;

// OrdenCompra implementa Descargable y Promocionable
// Patron: Contrato.java -> class CConcreta extends CAbstracta implements Interface
// Folio autoincremental con static - como conteo en MiConteo.java
// IO con PrintWriter - como BufferStream.java -> PrintWriter pw = new PrintWriter(new FileWriter(args[1]))
// try/catch/finally - como DivisionException.java
public class OrdenCompra implements Descargable, Promocionable {

	// Variable estatica - patron MiConteo.java -> public static int conteo = 0;
	private static int totalVentas = 0;

	// Variables de instancia - patron Automovil.java -> private String marca;
	private int folio;
	private Cliente cliente;
	private List<Producto> carrito;
	private double subtotal;
	private double descuento;
	private double total;
	private boolean cuponAplicado;
	private boolean ganoNuevoCupon;
	private Cita cita;

	public OrdenCompra(Cliente cliente) {
		// Incrementa el estatico - patron Refresco en MiConteo.java -> conteo++;
		totalVentas++;
		this.folio = totalVentas;
		this.cliente = cliente;
		// ArrayList - patron Colecciones.java -> List<Object> lista = new ArrayList<>();
		this.carrito = new ArrayList<>();
		this.subtotal = 0;
		this.descuento = 0;
		this.total = 0;
		this.cuponAplicado = false;
		this.ganoNuevoCupon = false;
	}

	// Agrega producto al carrito - lista.add() como Colecciones.java
	public void agregarProducto(Producto p) {
		carrito.add(p);
		recalcular();
	}

	// Recalcula totales con for-each - patron Main.java -> for (Animal a : lista)
	private void recalcular() {
		subtotal = 0;
		for(Producto p : carrito) {
			subtotal += p.getPrecio();
		}
		descuento = calcularDescuento(subtotal);
		total = subtotal - descuento;
	}

	// Implementacion de Promocionable
	// @Override - patron SobreEscritura1.java -> @Override public void metodo1()
	@Override
	public double calcularDescuento(double monto) {
		if(cliente.tieneCupon()) {
			cuponAplicado = true;
			return 400;
		}
		cuponAplicado = false;
		return 0;
	}

	// Cierra la compra y asigna cupon si aplica
	public void cerrarCompra() {
		if(subtotal >= 4000) {
			ganoNuevoCupon = true;
			cliente.setTieneCupon(true);
		} else {
			ganoNuevoCupon = false;
		}
		if(cuponAplicado) {
			cliente.setTieneCupon(false);
		}
		if(subtotal >= 4000) {
			cliente.setTieneCupon(true);
		}
		// agregarHistorial - lista.add() como en Colecciones.java
		cliente.agregarHistorial("Orden #" + folio + " | $" + total
			+ (cuponAplicado ? " | Cupon usado" : "")
			+ (ganoNuevoCupon ? " | Cupon ganado" : ""));
	}

	// Metodo estatico - patron MiConteo.java -> public static int getConteo()
	// Regla de negocio global - como indica el proyecto
	public static boolean calificaParaCupon(double monto) {
		return monto >= 4000;
	}

	// Implementacion de Descargable
	// @Override - patron SobreEscritura1.java
	// I/O con PrintWriter - patron BufferStream.java -> PrintWriter salida = new PrintWriter(new FileWriter(args[1]))
	// try/catch - patron DivisionException.java -> try { } catch(MateException | ArithmeticException ma) { }
	@Override
	public void exportarAArchivo(String ruta) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(ruta));
			pw.println("========================================");
			pw.println("   VisionMaster - COMPROBANTE DE VENTA  ");
			pw.println("========================================");
			pw.println("Folio: #" + folio);
			pw.println("----------------------------------------");
			pw.println("CLIENTE:");
			pw.println("  Nombre : " + cliente.getNombre());
			pw.println("  Tel    : " + cliente.getTelefono());
			pw.println("  Email  : " + cliente.getCorreo());
			pw.println("----------------------------------------");
			pw.println("PRODUCTOS:");
			int num = 1;
			// for-each - patron Main.java -> for (Animal a : lista)
			for(Producto p : carrito) {
				pw.println("  " + num + ". " + p.toString());
				num++;
			}
			pw.println("----------------------------------------");
			if(cita != null) {
				pw.println("CITA:");
				pw.println("  " + cita.toString());
				pw.println("----------------------------------------");
			}
			pw.println("Subtotal : $" + subtotal);
			if(cuponAplicado) {
				pw.println("Descuento: -$" + descuento);
			}
			pw.println("TOTAL    : $" + total);
			if(ganoNuevoCupon) {
				pw.println("** Ganaste un cupon de $400 para tu proxima compra! **");
			}
			pw.println("========================================");
			pw.println("Ventas en sesion: " + totalVentas);
			pw.println("Gracias por su compra!");
			pw.println("========================================");
			System.out.println("Ticket guardado en: " + ruta);
		} catch(IOException ioe) {
			// catch patron DivisionException.java y BufferStream.java
			System.out.println("Error al guardar: " + ioe.getMessage());
		} finally {
			// finally - patron RTException.java -> finally { System.out.println("Finally"); }
			if(pw != null) {
				pw.close();
			}
		}
	}

	// Getters - patron Automovil.java
	public int getFolio() {
		return folio;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public List<Producto> getCarrito() {
		return carrito;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public double getDescuento() {
		return descuento;
	}
	public double getTotal() {
		return total;
	}
	public boolean isCuponAplicado() {
		return cuponAplicado;
	}
	public boolean isGanoNuevoCupon() {
		return ganoNuevoCupon;
	}
	public Cita getCita() {
		return cita;
	}
	public void setCita(Cita cita) {
		this.cita = cita;
	}

	// Metodo estatico - patron MiConteo.java -> public static int getConteo()
	public static int getTotalVentas() {
		return totalVentas;
	}

	public String toString() {
		return "Orden #" + folio + " | " + cliente.getNombre() + " | Total: $" + total;
	}
}
