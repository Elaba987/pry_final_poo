import java.util.ArrayList;
import java.util.List;

// Clase Cliente - ID autoincremental con static como en MiConteo.java (clase Refresco)
// MiConteo.java -> public static int conteo = 0; conteo++; en constructor
// Colecciones.java -> List<Object> lista = new ArrayList<>();
public class Cliente {
	// Variable estatica compartida - patron MiConteo.java -> public static int conteo = 0;
	private static int contadorId = 0;

	// Variables de instancia - patron Refresco en MiConteo.java -> private int cuenta = 0;
	private int id;
	private String nombre;
	private String telefono;
	private String correo;
	private List<String> historial;
	private boolean tieneCupon;

	public Cliente(String nombre, String telefono, String correo) {
		// Incrementa el estatico - patron Refresco -> conteo++;
		contadorId++;
		this.id = contadorId;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		// ArrayList como en Colecciones.java -> List<Object> lista = new ArrayList<>();
		this.historial = new ArrayList<>();
		this.tieneCupon = false;
	}

	// Getters - patron Automovil.java -> public String getMarca() { return marca; }
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public List<String> getHistorial() {
		return historial;
	}
	public boolean tieneCupon() {
		return tieneCupon;
	}

	// Setters - patron Automovil.java -> public void setMarca(String marca) { this.marca = marca; }
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setTieneCupon(boolean tieneCupon) {
		this.tieneCupon = tieneCupon;
	}

	// Agrega al historial - lista.add() como en Colecciones.java
	public void agregarHistorial(String entrada) {
		historial.add(entrada);
	}

	// Metodo estatico - patron MiConteo.java -> public static int getConteo() { return conteo; }
	public static int getContadorId() {
		return contadorId;
	}

	// toString - patron OrdenarXComparable.java -> public String toString() { return this.edad + ""; }
	public String toString() {
		String cupon;
		if(tieneCupon) {
			cupon = " [CUPON DISPONIBLE]";
		} else {
			cupon = "";
		}
		return "Cliente #" + id + " | " + nombre + " | " + telefono + " | " + correo + cupon;
	}
}
