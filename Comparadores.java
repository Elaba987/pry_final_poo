import java.util.Comparator;

// Todos los comparadores del sistema consolidados en un archivo
// Patron: OrdenarXComparator.java -> class Alumno implements Comparator<Alumno>
// public int compare(Alumno a1, Alumno a2) { return a1.nombre.compareTo(a2.nombre); }

// --- Comparadores de Producto ---
// Patron: OrdenarXComparator.java -> return a1.nombre.compareTo(a2.nombre); // ascendente
//                                    return a2.nombre.compareTo(a1.nombre); // descendente

class ComparadorPrecio implements Comparator<Producto> {
	public int compare(Producto p1, Producto p2) {
		if(p1.getPrecio() < p2.getPrecio()) {
			return -1;
		}
		if(p1.getPrecio() > p2.getPrecio()) {
			return 1;
		}
		return p1.getNombre().compareTo(p2.getNombre());
	}
}

class ComparadorPrecioDesc implements Comparator<Producto> {
	public int compare(Producto p1, Producto p2) {
		if(p2.getPrecio() < p1.getPrecio()) {
			return -1;
		}
		if(p2.getPrecio() > p1.getPrecio()) {
			return 1;
		}
		return p1.getNombre().compareTo(p2.getNombre());
	}
}

class ComparadorNombreAZ implements Comparator<Producto> {
	public int compare(Producto p1, Producto p2) {
		return p1.getNombre().compareTo(p2.getNombre());
	}
}

class ComparadorNombreZA implements Comparator<Producto> {
	public int compare(Producto p1, Producto p2) {
		return p2.getNombre().compareTo(p1.getNombre());
	}
}

// --- Comparadores de Cliente ---

class ComparadorClienteIdAsc implements Comparator<Cliente> {
	public int compare(Cliente c1, Cliente c2) {
		return c1.getId() - c2.getId();
	}
}

class ComparadorClienteIdDesc implements Comparator<Cliente> {
	public int compare(Cliente c1, Cliente c2) {
		return c2.getId() - c1.getId();
	}
}

class ComparadorClienteNombreAZ implements Comparator<Cliente> {
	public int compare(Cliente c1, Cliente c2) {
		return c1.getNombre().compareTo(c2.getNombre());
	}
}

class ComparadorClienteNombreZA implements Comparator<Cliente> {
	public int compare(Cliente c1, Cliente c2) {
		return c2.getNombre().compareTo(c1.getNombre());
	}
}
