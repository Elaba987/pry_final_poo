// Enums del sistema - mismo patron que DiaFavorito2.java
// enum con constructor privado y getNombre() -> identico a enum Dia en DiaFavorito2.java

enum TipoGraduacion {
	MIOPIA("Miopia"),
	ASTIGMATISMO("Astigmatismo"),
	PRESBICIA("Presbicia"),
	NEUTRO("Sin graduacion");

	private final String nombre;

	private TipoGraduacion(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}

enum MaterialLente {
	POLICARBONATO("Policarbonato", 800.0),
	RESINA("Resina", 500.0),
	CRISTAL("Cristal", 1200.0);

	private final String nombre;
	private final double precio;

	private MaterialLente(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}
	public double getPrecio() {
		return precio;
	}
}

enum EstadoCita {
	PENDIENTE("Pendiente"),
	CONFIRMADA("Confirmada"),
	CANCELADA("Cancelada");

	private final String nombre;

	private EstadoCita(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
}
