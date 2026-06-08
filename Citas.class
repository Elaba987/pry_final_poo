// Jerarquia de citas consolidada en un archivo
// Herencia: Cita -> CitaExamen, CitaEntrega - como Constructores1.java

// Clase base - encapsulamiento como Automovil.java
class Cita {
    private String fecha;
    private String hora;
    private String nombreCliente;
    private EstadoCita estado;

    public Cita(String fecha, String hora, String nombreCliente) {
        this.fecha = fecha;
        this.hora = hora;
        this.nombreCliente = nombreCliente;
        this.estado = EstadoCita.PENDIENTE;
    }

    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getNombreCliente() { return nombreCliente; }
    public EstadoCita getEstado() { return estado; }

    public void setEstado(EstadoCita estado) { this.estado = estado; }

    public String toString() {
        return fecha + " " + hora + " | " + nombreCliente + " | " + estado.getNombre();
    }
}

// Subclase examen - herencia como Constructores1.java
class CitaExamen extends Cita {
    private boolean gratuita;

    public CitaExamen(String fecha, String hora, String nombreCliente, boolean gratuita) {
        super(fecha, hora, nombreCliente);
        this.gratuita = gratuita;
    }

    public boolean isGratuita() { return gratuita; }

    public String toString() {
        String costo = gratuita ? "Gratuita" : "Con costo $350";
        return "[Examen - " + costo + "] " + super.toString();
    }
}

// Subclase entrega - segunda subclase de la jerarquia Cita
class CitaEntrega extends Cita {
    private int folio;

    public CitaEntrega(String fecha, String hora, String nombreCliente, int folio) {
        super(fecha, hora, nombreCliente);
        this.folio = folio;
    }

    public int getFolio() { return folio; }

    public String toString() {
        return "[Entrega folio #" + folio + "] " + super.toString();
    }
}
