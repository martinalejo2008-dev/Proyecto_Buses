package modelo;
import java.time.LocalDate;
import java.time.LocalTime;

public class Viaje {
    private String id;
    private LocalDate fecha;
    private LocalTime horaSalida;
    private EstadoViaje estado;
    private Ruta ruta;
    private Bus bus;
    private Conductor conductor;

    public Viaje(String id, Ruta ruta, Bus bus, Conductor conductor, LocalDate fecha, LocalTime horaSalida) {
        this.id = id;
        this.ruta = ruta;
        this.bus = bus;
        this.conductor = conductor;
        this.fecha = fecha;
        this.horaSalida = horaSalida;
        this.estado = EstadoViaje.PROGRAMADO;
    }

    public LocalTime calcularHoraFinEstimada() {
        return ruta.calcularHoraLlegada(horaSalida);
    }

    public boolean seSolapaCon(Viaje otro) {
        if (!this.fecha.equals(otro.fecha)) return false;
        if (!this.bus.getCodigo().equals(otro.bus.getCodigo()) &&
                !this.conductor.getCedula().equals(otro.conductor.getCedula())) {
            return false;
        }
        LocalTime inicio1 = this.horaSalida;
        LocalTime fin1 = this.calcularHoraFinEstimada();
        LocalTime inicio2 = otro.horaSalida;
        LocalTime fin2 = otro.calcularHoraFinEstimada();

        return inicio1.isBefore(fin2) && inicio2.isBefore(fin1);
    }

    public void cambiarEstado(EstadoViaje nuevoEstado) {
        if (this.estado == EstadoViaje.PROGRAMADO) {
            if (nuevoEstado == EstadoViaje.EN_RECORRIDO || nuevoEstado == EstadoViaje.CANCELADO) {
                this.estado = nuevoEstado;
                return;
            }
        } else if (this.estado == EstadoViaje.EN_RECORRIDO) {
            if (nuevoEstado == EstadoViaje.FINALIZADO || nuevoEstado == EstadoViaje.CANCELADO) {
                this.estado = nuevoEstado;
                return;
            }
        }
        throw new ReglaNegocioException("Transición de estado no válida desde " + this.estado + " a " + nuevoEstado);
    }

    public String getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraSalida() { return horaSalida; }
    public EstadoViaje getEstado() { return estado; }
    public Ruta getRuta() { return ruta; }
    public Bus getBus() { return bus; }
    public Conductor getConductor() { return conductor; }
}