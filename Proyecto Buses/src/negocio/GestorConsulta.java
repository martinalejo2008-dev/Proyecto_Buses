package negocio;
import modelo.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GestorConsulta {
    private GestorViajes gestorViajes;

    public GestorConsulta(GestorViajes gestorViajes) {
        this.gestorViajes = gestorViajes;
    }

    public List<Viaje> buscarViajes(String idOrigen, String idDestino, LocalDate fecha) {
        List<Viaje> viajesDia = gestorViajes.listarViajesPorFecha(fecha);
        List<Viaje> viajesValidos = new ArrayList<>();

        for (Viaje v : viajesDia) {
            if ((v.getEstado() == EstadoViaje.PROGRAMADO || v.getEstado() == EstadoViaje.EN_RECORRIDO) &&
                    v.getRuta().tieneSentidoValido(idOrigen, idDestino)) {
                viajesValidos.add(v);
            }
        }
        return viajesValidos;
    }

    public List<HorarioParada> obtenerHorarios(Viaje viaje) {
        List<HorarioParada> itinerario = new ArrayList<>();
        LocalTime horaAcumulada = viaje.getHoraSalida();
        List<ParadaRuta> paradasRuta = viaje.getRuta().getParadas();

        for (int i = 0; i < paradasRuta.size(); i++) {
            ParadaRuta pr = paradasRuta.get(i);
            if (i > 0) {
                horaAcumulada = horaAcumulada.plusMinutes(pr.getMinutosDesdeAnterior());
            }
            itinerario.add(new HorarioParada(pr.getParada().getNombre(), horaAcumulada));
        }
        return itinerario;
    }
}