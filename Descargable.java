// Promocionable obliga a implementar calcularDescuento
// Recuerden ponerlas en el diagrama como <<interface>> hacia las clases que las implementan (OrdenCompra usa a las dos)

public interface Descargable {
	void exportarAArchivo(String ruta);
}

interface Promocionable {
	double calcularDescuento(double total);
}
