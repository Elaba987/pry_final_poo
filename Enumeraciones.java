// Esta clase define los catalogos que usa todo el sistema: tipos de graduacion, materiales de lente y estados de cita 
// tambien se hace un enum con nombre

// Para el diagrama: los tres enums van como clasificadores separados
//      TipoGraduacion tiene los valores MIOPIA, ASTIGMATISMO, PRESBICIA, NEUTRO
//       y el atributo: nombre (String). Metodo: getNombre()
//       MaterialLente tiene POLICARBONATO, RESINA, CRISTAL
//        y el atributo: nombre (String), precio (double). Metodos: getNombre(), getPrecio()
//        EstadoCita tiene PENDIENTE, CONFIRMADA, CANCELADA
//        y el atributo: nombre (String). Metodo: getNombre()

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
