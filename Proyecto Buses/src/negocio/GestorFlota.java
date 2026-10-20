package negocio;
import modelo.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorFlota {
    private List<Bus> buses = new ArrayList<>();
    private List<Conductor> conductores = new ArrayList<>();

    public void registrarBus(Bus bus) {
        if (bus.getCapacidad() <= 0) {
            throw new ReglaNegocioException("La capacidad del bus debe ser estrictamente mayor a cero.");
        }
        for (Bus b : buses) {
            if (b.getCodigo().equals(bus.getCodigo()) || b.getPlaca().equals(bus.getPlaca())) {
                throw new ReglaNegocioException("El código o la placa del bus ya están registrados.");
            }
        }
        buses.add(bus);
    }

    public void actualizarEstadoBus(String codigo, EstadoBus nuevoEstado, List<Viaje> viajesActivos) {
        Bus bus = buscarBus(codigo);
        if (bus == null) throw new ReglaNegocioException("Bus no encontrado.");

        if (nuevoEstado == EstadoBus.INACTIVO) {
            for (Viaje v : viajesActivos) {
                if (v.getBus().getCodigo().equals(codigo) &&
                        (v.getEstado() == EstadoViaje.PROGRAMADO || v.getEstado() == EstadoViaje.EN_RECORRIDO)) {
                    throw new ReglaNegocioException("No se puede inactivar un bus con viajes programados o en recorrido.");
                }
            }
        }
        bus.setEstado(nuevoEstado);
    }

    public void registrarConductor(Conductor conductor) {
        conductor.verificarVigenciaLicencia(LocalDate.now());
        conductores.add(conductor);
    }

    public Bus buscarBus(String codigo) {
        return buses.stream().filter(b -> b.getCodigo().equals(codigo)).findFirst().orElse(null);
    }
}