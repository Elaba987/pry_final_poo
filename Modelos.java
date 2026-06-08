// Aqui Producto es el padre, y Armazon y Accesorio son los hijos

// Para el diagrama de clases, los atributos y metodos son:

// Producto:
//   Atributos: nombre (String), precio (double)
//   Metodos: getNombre(), getPrecio(), setNombre(), setPrecio(), toString()

// Armazon extiende de Producto:
//   Atributos: material (MaterialLente), estilo (String)
//   Metodos: getMaterial(), getEstilo(), setMaterial(), setEstilo(), toString(), toStringCompleto()

// Accesorio extiende de Producto:
//   Atributos: tipo (String)
//   Metodos: getTipo(), setTipo(), toString()
//
// No olviden dibujar la flecha de herencia de Armazon -> Producto y de Accesorio -> Producto

class Producto {
	private String nombre;
	private double precio;

	public Producto(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}
	public double getPrecio() {
		return precio;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String toString() {
		return nombre + " - $" + precio;
	}
}

class Armazon extends Producto {
	private MaterialLente material;
	private String estilo;

	public Armazon(String nombre, double precio, MaterialLente material, String estilo) {
		super(nombre, precio);
		this.material = material;
		this.estilo = estilo;
	}

	public MaterialLente getMaterial() {
		return material;
	}
	public String getEstilo() {
		return estilo;
	}

	public void setMaterial(MaterialLente material) {
		this.material = material;
	}
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String toString() {
		return getNombre() + " | " + material.getNombre() + " | $" + getPrecio();
	}

	public String toStringCompleto() {
		return getNombre() + " [" + estilo + "] | " + material.getNombre() + " | $" + getPrecio();
	}
}

class Accesorio extends Producto {
	private String tipo;

	public Accesorio(String nombre, double precio, String tipo) {
		super(nombre, precio);
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		return "[Accesorio] " + getNombre() + " (" + tipo + ") | $" + getPrecio();
	}
}
