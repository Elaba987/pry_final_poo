// Este archivo maneja la jerarquía de herencias, no se olviden de como se haceen en el diagrama

// Cita es padre y las hijas son CitaExamen y CitaEntrega

//Atributos:
//   Cita: fecha (String), hora (String), nombreCliente (String), estado (EstadoCita)
//Metodos: getFecha(), getHora(), getNombreCliente(), getEstado(), setEstado(), toString()

//   CitaExamen extiende de Cita:
//   Atributos: gratuita (boolean)
//   Metodos: isGratuita()

//   CitaEntrega extiende de Cita:
//   Atributos: folio (int)
//   Metodos: getFolio()


class Cita {
	private String fecha;
	private String hora;
	private String nombreCliente;
	private EstadoCita estado;

	public Cita(String fecha, String hora, String nombreCliente) {
		this.fecha = fecha;
		this.hora = hora;
		this.nombreCliente = nombreCliente;
		this.estado = EstadoCita.PENDIENTE;
	}

	public String getFecha() {
		return fecha;
	}
	public String getHora() {
		return hora;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public EstadoCita getEstado() {
		return estado;
	}

	public void setEstado(EstadoCita estado) {
		this.estado = estado;
	}

	public String toString() {
		return fecha + " " + hora + " | " + nombreCliente + " | " + estado.getNombre();
	}
}

class CitaExamen extends Cita {
	private boolean gratuita;

	public CitaExamen(String fecha, String hora, String nombreCliente, boolean gratuita) {
		super(fecha, hora, nombreCliente);
		this.gratuita = gratuita;
	}

	public boolean isGratuita() {
		return gratuita;
	}

	public String toString() {
		String costo;
		if(gratuita) {
			costo = "Gratuita";
		} else {
			costo = "Con costo $350";
		}
		return "[Examen - " + costo + "] " + super.toString();
	}
}

class CitaEntrega extends Cita {
	private int folio;

	public CitaEntrega(String fecha, String hora, String nombreCliente, int folio) {
		super(fecha, hora, nombreCliente);
		this.folio = folio;
	}

	public int getFolio() {
		return folio;
	}

	public String toString() {
		return "[Entrega folio #" + folio + "] " + super.toString();
	}
}
