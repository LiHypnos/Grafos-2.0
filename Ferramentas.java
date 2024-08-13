import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Ferramentas {
    Grafo grafo;
    public Ferramentas(Grafo grafo){
        this.grafo = grafo;
    }
    public boolean isConexo(){
        return grafo.DFS().split("->").length == grafo.Vertices().split(" ").length;
    }
    public boolean isBipartido(){
        Map<String, Integer> cores = new HashMap<>();
        
        for (String vertice : grafo.getGrafo().keySet()) {
            if (!cores.containsKey(vertice)) {
                // Inicia o BFS para o componente conectado
                if (!bfsCheck(vertice, cores)) {
                    return false; // Se qualquer componente não for bipartido, retorna false
                }
            }
        }
        
        return true;
    }

    private boolean bfsCheck(String start, Map<String, Integer> cores) {
        Queue<String> fila = new LinkedList<>();
        fila.add(start);
        cores.put(start, 0); // Começa com a cor 0
        
        while (!fila.isEmpty()) {
            String vertice = fila.poll();
            int corAtual = cores.get(vertice);
            int corVizinho = 1 - corAtual; // Alterna entre 0 e 1
            
            ArrayList<Pair<String, Integer>> vizinhos = grafo.getGrafo().get(vertice);
            if (vizinhos != null) {
                for (Pair<String, Integer> par : vizinhos) {
                    String vizinho = par.getKey();
                    
                    if (!cores.containsKey(vizinho)) {
                        // Atribui a cor oposta ao vizinho
                        cores.put(vizinho, corVizinho);
                        fila.add(vizinho);
                    } else if (cores.get(vizinho) == corAtual) {
                        // Se o vizinho já tem a mesma cor, o grafo não é bipartido
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    public boolean isEuleriano(){

        if (!isConexo()) {
            return false;
        }
        
        
        int oddDegreeCount = 0;
        for (String vertice : grafo.Vertices().split(" ")) {
            if (grafo.getGrau(vertice) % 2 != 0) {
                System.out.println(vertice);
                oddDegreeCount++;
            }
        }
        
        if (oddDegreeCount > 2) {
            return false;
        }
        
        return true;
    }
    public boolean possuiCiclo(){
        
        
        return false;
    }
}
