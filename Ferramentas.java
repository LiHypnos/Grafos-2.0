import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Ferramentas {
    Grafo grafo;
    public Ferramentas(Grafo grafo){
        this.grafo = grafo;
    }
    public boolean isConexo(){
        List<String> visitadasA = new ArrayList<>();
        visitadasA = grafo.BFS("0");
        List<String> verticesV = new ArrayList<>();
        String[] verticesT = grafo.Vertices().split(" ");
        for(int i=0;i < visitadasA.size();i++){
            verticesV.add(grafo.getGrafo().get(visitadasA.get(i)).get(0).getOrigin()); 
            verticesV.add(grafo.getGrafo().get(visitadasA.get(i)).get(0).getDestiny());
        }
        for(String a : verticesT){
            if(!verticesV.contains(a)){
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
        String[] valores = grafo.Vertices().split(" ");
        for (String vertice : valores) {
            if (grafo.getGrau(vertice) % 2 != 0) {
                System.out.println(vertice + " Possui grau: " + grafo.getGrau(vertice));
                oddDegreeCount++;
                return false;
            }
        }
        
        if (oddDegreeCount > 2) {
            return false;
        }
        
        return true;
    }
    public boolean possuiCiclo(){
        List<String> arestasV = new ArrayList<>(grafo.DFS("0"));
        
        return false;
    }

    public String topSort() {
        TopSort topSort = new TopSort(grafo);
        return topSort.executar();
    }
}
