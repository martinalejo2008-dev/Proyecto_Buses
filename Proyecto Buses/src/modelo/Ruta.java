package modelo;
import java.time.LocalTime;
import java.util.List;

public class Ruta {
    private String codigo;
    private String nombre;
    private List<ParadaRuta> paradas;

    public Ruta(String codigo, String nombre, List<ParadaRuta> paradas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.paradas = paradas;
    }

    public int calcularDuracionTotal() {
        int total = 0;
        for (ParadaRuta pr : paradas) {
            total += pr.getMinutosDesdeAnterior();
        }
        return total;
    }

    public LocalTime calcularHoraLlegada(LocalTime horaSalida) {
        return horaSalida.plusMinutes(calcularDuracionTotal());
    }

    public int obtenerPosicionParada(String idParada) {
        for (int i = 0; i < paradas.size(); i++) {
            if (paradas.get(i).getParada().getId().equals(idParada)) {
                return i;
            }
        }
        return -1;
    }

    public boolean tieneSentidoValido(String idOrigen, String idDestino) {
        int posOrigen = obtenerPosicionParada(idOrigen);
        int posDestino = obtenerPosicionParada(idDestino);
        return posOrigen != -1 && posDestino != -1 && posOrigen < posDestino;
    }

    public String getCodigo() { return codigo; }
    public List<ParadaRuta> getParadas() { return paradas; }
}