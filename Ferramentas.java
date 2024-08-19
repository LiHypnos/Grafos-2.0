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
    
    public void isConexo(){
        System.out.println("Implementar");
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
