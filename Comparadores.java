// Hay comparadores para Producto por nombre y por precio y para Cliente por id y por nombre

// Dios sabe como esto funciona pero lo hace.

// Las clases y sus métodos son: 
//   todas tienen solo el método compare:
//   ComparadorPrecio          - ordena Producto de menor a mayor precio
//   ComparadorPrecioDesc      - ordena Producto de mayor a menor precio
//   ComparadorNombreAZ        - ordena Producto de A a Z por nombre
//   ComparadorNombreZA        - ordena Producto de Z a A por nombre
//   ComparadorClienteIdAsc    - ordena Cliente de menor a mayor id
//   ComparadorClienteIdDesc   - ordena Cliente de mayor a menor id
//   ComparadorClienteNombreAZ - ordena Cliente de A a Z por nombre
//   ComparadorClienteNombreZA - ordena Cliente de Z a A por nombre

import java.util.Comparator;

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
