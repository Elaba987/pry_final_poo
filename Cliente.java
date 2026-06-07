//Toda relacion con el cliente

// El ArrayList para guarda el historial de compras del cliente

// contadorId es estático (se subraya en el diagrama o solo especifiquen que lo es)

// Los atributos de Cliente son:
//   contadorId (static int), id (int), nombre (String), telefono (String), correo (String), historial (List<String>), tieneCupon (boolean)

// Los metodos:
//   getId(), getNombre(), getTelefono(), getCorreo(), getHistorial(), tieneCupon(), setNombre(), setTelefono(), setCorreo(), setTieneCupon(), agregarHistorial(), getContadorId() (static), toString()

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private static int contadorId = 0;

	private int id;
	private String nombre;
	private String telefono;
	private String correo;
	private List<String> historial;
	private boolean tieneCupon;

	public Cliente(String nombre, String telefono, String correo) {
		contadorId++;
		this.id = contadorId;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.historial = new ArrayList<>();
		this.tieneCupon = false;
	}

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

	public void agregarHistorial(String entrada) {
		historial.add(entrada);
	}

	public static int getContadorId() {
		return contadorId;
	}

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
