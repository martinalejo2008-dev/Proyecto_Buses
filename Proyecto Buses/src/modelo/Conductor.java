package modelo;
import java.time.LocalDate;

public class Conductor {
    private String cedula;
    private String nombreCompleto;
    private String numeroLicencia;
    private LocalDate fechaVencimientoLicencia;
    private String telefono;
    private EstadoAptitud estadoAptitud;

    public Conductor(String cedula, String nombreCompleto, String numeroLicencia, LocalDate fechaVencimientoLicencia, String telefono) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.numeroLicencia = numeroLicencia;
        this.fechaVencimientoLicencia = fechaVencimientoLicencia;
        this.telefono = telefono;
        verificarVigenciaLicencia(LocalDate.now());
    }

    public void verificarVigenciaLicencia(LocalDate fechaActual) {
        if (fechaVencimientoLicencia.isBefore(fechaActual)) {
            this.estadoAptitud = EstadoAptitud.NO_APTO;
        } else {
            this.estadoAptitud = EstadoAptitud.APTO;
        }
    }

    public boolean estaApto() { return this.estadoAptitud == EstadoAptitud.APTO; }
    public String getCedula() { return cedula; }
    public EstadoAptitud getEstadoAptitud() { return estadoAptitud; }
}