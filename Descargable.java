// Interfaces del sistema consolidadas en un solo archivo
// Patron: Contrato.java / Comic.java del repo

public interface Descargable {
    void exportarAArchivo(String ruta);
}

interface Promocionable {
    double calcularDescuento(double total);
}