// Jerarquia de productos consolidada en un archivo
// Herencia: Producto -> Armazon, Accesorio - como SobreCarga2.java / Mascotas.java

// Clase base - encapsulamiento como Automovil.java
class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String toString() {
        return nombre + " - $" + precio;
    }
}

// Armazon: TipoGraduacion es caracteristica del lente elegida en venta, no del armazon en almacen
class Armazon extends Producto {
    private MaterialLente material;
    private String estilo;

    public Armazon(String nombre, double precio, MaterialLente material, String estilo) {
        super(nombre, precio);
        this.material = material;
        this.estilo = estilo;
    }

    public MaterialLente getMaterial() { return material; }
    public String getEstilo() { return estilo; }

    public void setMaterial(MaterialLente material) { this.material = material; }
    public void setEstilo(String estilo) { this.estilo = estilo; }

    // toString para venta: nombre, material y precio
    public String toString() {
        return getNombre() + " | " + material.getNombre() + " | $" + getPrecio();
    }

    // toString completo para almacen
    public String toStringCompleto() {
        return getNombre() + " [" + estilo + "] | " + material.getNombre() + " | $" + getPrecio();
    }
}

// Segunda subclase - como Gato/Perro en Mascotas.java
class Accesorio extends Producto {
    private String tipo;

    public Accesorio(String nombre, double precio, String tipo) {
        super(nombre, precio);
        this.tipo = tipo;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String toString() {
        return "[Accesorio] " + getNombre() + " (" + tipo + ") | $" + getPrecio();
    }
}