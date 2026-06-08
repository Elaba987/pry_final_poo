// Esta clase representa una venta completa: tiene cliente y productos
// se aplican descuentos con cupon y puede guardar el ticket en un archivo .txt

// Usamos BufferStream porque escribe archivos con PrintWriter
// El folio se genera solo con una variable estatica

// Para el Diagrama no olviden que OrdenCompra implementa dos interfaces: Descargable y Promocionable

//Necesito saber si leen esto .-. capaz y no

// Los atributos son:
//   totalVentas (static int), folio (int), cliente (Cliente), carrito (List<Producto>), 
// subtotal (double), descuento (double), total (double), cuponAplicado (boolean), 
// ganoNuevoCupon (boolean), cita (Cita)



// Los metodos son:
//   agregarProducto(), calcularDescuento(), cerrarCompra(), calificaParaCupon() (static),
//   exportarAArchivo(),getFolio(), getCliente(), getCarrito(), getSubtotal(), getDescuento(),
//   getTotal(), isCuponAplicado(), isGanoNuevoCupon(), getCita(), setCita(),
//   getTotalVentas() (static), toString()

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class OrdenCompra implements Descargable, Promocionable {

	private static int totalVentas = 0;

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
		totalVentas++;
		this.folio = totalVentas;
		this.cliente = cliente;
		this.carrito = new ArrayList<>();
		this.subtotal = 0;
		this.descuento = 0;
		this.total = 0;
		this.cuponAplicado = false;
		this.ganoNuevoCupon = false;
	}

	public void agregarProducto(Producto p) {
		carrito.add(p);
		recalcular();
	}

	private void recalcular() {
		subtotal = 0;
		for(Producto p : carrito) {
			subtotal += p.getPrecio();
		}
		descuento = calcularDescuento(subtotal);
		total = subtotal - descuento;
	}

	@Override
	public double calcularDescuento(double monto) {
		if(cliente.tieneCupon()) {
			cuponAplicado = true;
			return 400;
		}
		cuponAplicado = false;
		return 0;
	}

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
		cliente.agregarHistorial("Orden #" + folio + " | $" + total
			+ (cuponAplicado ? " | Cupon usado" : "")
			+ (ganoNuevoCupon ? " | Cupon ganado" : ""));
	}

	public static boolean calificaParaCupon(double monto) {
		return monto >= 4000;
	}

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
			System.out.println("Error al guardar: " + ioe.getMessage());
		} finally {
			if(pw != null) {
				pw.close();
			}
		}
	}

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

	public static int getTotalVentas() {
		return totalVentas;
	}

	public String toString() {
		return "Orden #" + folio + " | " + cliente.getNombre() + " | Total: $" + total;
	}
}
