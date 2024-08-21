import java.util.ArrayList;
import java.util.HashMap;

public class FechoTransitivo {
    private ArrayList<String> vertices;
    private HashMap<String, ArrayList<String>> listaAdj;

    public FechoTransitivo(Grafo grafo) {
        this.vertices = grafo.getVertices();
        this.listaAdj = grafo.getListaAdj();
    }

    public String executar() {
        int n = vertices.size();
        boolean[][] dist = new boolean[n][n];

        // Inicializa a matriz de alcance com as arestas diretas do grafo
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = listaAdj.get(vertices.get(i)).contains(vertices.get(j));
            }
        }

        // Aplica o algoritmo de Floyd-Warshall para calcular o fecho transitivo
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = dist[i][j] || (dist[i][k] && dist[k][j]);
                }
            }
        }

        // Gera a saída conforme o formato especificado
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j]) {
                    resultado.append(j).append(" ");
                }
            }
            if (resultado.length() == 0 || resultado.charAt(resultado.length() - 1) != ' ') {
                resultado.append("-1 ");
            }
        }

        return resultado.toString().trim();
    }
}
