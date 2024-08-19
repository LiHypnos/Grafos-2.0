import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
//import java.util.TreeSet;

public class Grafo {
    Map<String, ArrayList<Pair<String,Integer,String>>> grafo;
    Grafo(Map<String, ArrayList<Pair<String,Integer,String>>> input){
        grafo = new HashMap<>();
        grafo = input;
    }
    public Map<String, ArrayList<Pair<String,Integer,String>>> getGrafo(){
        return grafo;
    }
    public String Vertices(){
        String vertices = "";
        HashMap<String, Integer> verticesMap = new HashMap<>();
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if(entry.getValue() != null){
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    verticesMap.put(par.getDestiny(), 0);
                }
            }
        }
        for (String vertice : verticesMap.keySet()) {
            vertices += vertice + " ";
        }
        return vertices;
    }
    public int getGrau(String vertice){
        return grafo.get(vertice).size();
    }
    public List<String> BFS(String initial){
        List<String> visitados = new ArrayList<>();
        Queue<String> fila = new LinkedList<>();
        fila.add(initial);
        while(!fila.isEmpty()){
            String vertice = fila.poll();
            if(!visitados.contains(vertice)){
                visitados.add(vertice);
                if(grafo.get(vertice) != null){
                    for (Pair<String, Integer, String> par : grafo.get(vertice)) {
                        fila.add(par.getDestiny());
                    }
                }
            }
        }
        return visitados;
    }
    public List<String> DFS(String initial){
        List<String> visitados = new ArrayList<>();
        Stack<String> pilha = new Stack<>();
        pilha.add(initial);
        while(!pilha.isEmpty()){
            String vertice = pilha.pop();
            if(!visitados.contains(vertice)){
                visitados.add(vertice);
                if(grafo.get(vertice) != null){
                    for (Pair<String, Integer, String> par : grafo.get(vertice)) {
                        pilha.add(par.getDestiny());
                    }
                }
            }
        }
        return visitados;
    }
    
}
