package modelo;
import java.time.LocalTime;

public class HorarioParada {
    private String nombreParada;
    private LocalTime horaEstimadaPaso;

    public HorarioParada(String nombreParada, LocalTime horaEstimadaPaso) {
        this.nombreParada = nombreParada;
        this.horaEstimadaPaso = horaEstimadaPaso;
    }

    public String getNombreParada() { return nombreParada; }
    public LocalTime getHoraEstimadaPaso() { return horaEstimadaPaso; }
}