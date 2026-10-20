package negocio;
import modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorViajes {
    private List<Viaje> viajes = new ArrayList<>();

    public void programarViaje(Viaje nuevoViaje) {
        if (!nuevoViaje.getBus().estaActivo()) {
            throw new ReglaNegocioException("El bus seleccionado no está activo.");
        }
        if (!nuevoViaje.getConductor().estaApto()) {
            throw new ReglaNegocioException("El conductor no cuenta con licencia apta / vigente.");
        }

        for (Viaje v : viajes) {
            if (v.seSolapaCon(nuevoViaje)) {
                throw new ReglaNegocioException("Existe un solapamiento de horario para el bus o el conductor.");
            }
        }
        viajes.add(nuevoViaje);
    }

    public void cambiarEstadoViaje(String idViaje, EstadoViaje nuevoEstado) {
        Viaje v = buscarViaje(idViaje);
        if (v == null) throw new ReglaNegocioException("Viaje no encontrado.");
        v.cambiarEstado(nuevoEstado);
    }

    public List<Viaje> listarViajesPorFecha(LocalDate fecha) {
        List<Viaje> resultado = new ArrayList<>();
        for (Viaje v : viajes) {
            if (v.getFecha().equals(fecha)) resultado.add(v);
        }
        return resultado;
    }

    private Viaje buscarViaje(String id) {
        return viajes.stream().filter(v -> v.getId().equals(id)).findFirst().orElse(null);
    }
}