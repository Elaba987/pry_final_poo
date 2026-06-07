import java.util.*;

// Clase Tienda - gestora del sistema
// Colecciones como en Colecciones.java: ArrayList
// Variables y metodos estaticos como en MiConteo.java
public class Tienda {
    private static String nombre = "VisionMaster";

    private List<Producto> inventario;
    private List<Cita> citas;
    private List<Cliente> clientes;

    public Tienda() {
        inventario = new ArrayList<>();
        citas = new ArrayList<>();
        clientes = new ArrayList<>();
        cargarInventario();
    }

    private void cargarInventario() {
        inventario.add(new Armazon("Ray-Ban Clasico",  2500, MaterialLente.POLICARBONATO, "Aviador"));
        inventario.add(new Armazon("Oakley Sport",     3200, MaterialLente.POLICARBONATO, "Cuadrado"));
        inventario.add(new Armazon("Vogue Elegance",   1800, MaterialLente.RESINA,        "Redondo"));
        inventario.add(new Armazon("Gucci Premium",    5500, MaterialLente.CRISTAL,       "Cuadrado"));
        inventario.add(new Armazon("BabyVision Kids",   900, MaterialLente.RESINA,        "Redondo"));
        inventario.add(new Armazon("BlueBlock Office", 1500, MaterialLente.POLICARBONATO, "Cuadrado"));
        inventario.add(new Accesorio("Estuche Premium", 150, "Estuche"));
        inventario.add(new Accesorio("Kit Limpieza",     80, "Limpiador"));
        inventario.add(new Accesorio("Cadena Dorada",   120, "Cadena"));
    }

    public static boolean validarCupon(double monto) {
        return OrdenCompra.calificaParaCupon(monto);
    }

    public static String getNombre() { return nombre; }

    // --- Clientes ---
    public void registrarCliente(Cliente c) { clientes.add(c); }
    public void eliminarCliente(int indice) { clientes.remove(indice); }
    public List<Cliente> getClientes() { return clientes; }

    // --- Citas ---
    public void agendarCita(Cita c) { citas.add(c); }
    public List<Cita> getCitas() { return citas; }

    public List<Cita> getCitasDeCliente(String nombreCliente) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getNombreCliente().equals(nombreCliente)) resultado.add(c);
        }
        return resultado;
    }

    // --- Inventario ---
    public void agregarProducto(Producto p) { inventario.add(p); }
    public void eliminarProducto(int indice) { inventario.remove(indice); }
    public List<Producto> getInventario() { return inventario; }

    // Lista filtrada y ordenada - patron Collections.sort + Comparator como Main.java
    public List<Producto> getListaOrdenada(String filtro, Comparator<Producto> comparador) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : inventario) {
            if (filtro.equals("armazones") && p instanceof Armazon) resultado.add(p);
            else if (filtro.equals("accesorios") && p instanceof Accesorio) resultado.add(p);
            else if (filtro.equals("todos")) resultado.add(p);
        }
        Collections.sort(resultado, comparador);
        return resultado;
    }

    public List<Cliente> getClientesOrdenados(Comparator<Cliente> comparador) {
        List<Cliente> resultado = new ArrayList<>(clientes);
        Collections.sort(resultado, comparador);
        return resultado;
    }
}