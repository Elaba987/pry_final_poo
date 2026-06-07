// Interfaces del sistema consolidadas en un solo archivo
// Patron: Contrato.java -> interface Interface { void metodo3(); }
// Patron: Comic.java -> interface Complemento, interface Historieta extends Complemento

public interface Descargable {
	void exportarAArchivo(String ruta);
}

interface Promocionable {
	double calcularDescuento(double total);
}
