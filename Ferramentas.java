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
        String initial = grafo.Vertices().charAt(0) + "";
        Map<String, Boolean> visited = new HashMap<>();
        for (String vertice : grafo.Vertices().split(" ")) {
            visited.put(vertice, false);
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(initial);
        visited.put(grafo.getGrafo().get(initial).get(0).getOrigin(), true);
        visited.put(grafo.getGrafo().get(initial).get(0).getDestiny(), true);
        while (!queue.isEmpty()) {
            String aresta = queue.poll();
            for (String adjacente : grafo.getGrafo().get(aresta).get(0).getDestiny().split(" ")) {
                if (!visited.get(adjacente)) {
                    visited.put(adjacente, true);
                    queue.add(adjacente);
                }
            }
        }
        for (String vertice : grafo.Vertices().split(" ")) {
            if (!visited.get(vertice)) {
                return false;
            }
        }
        return true;
    }
    public boolean isBipartido(){
        Map<String, Integer> cores = new HashMap<>();
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
