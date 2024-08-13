import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
//import java.util.TreeSet;

public class Grafo {
    Map<String, ArrayList<Pair<String,Integer>>> grafo;
    Grafo(Map<String, ArrayList<Pair<String,Integer>>> input){
        grafo = new HashMap<>();
        grafo = input;
        //arrumando possiveis problemas de não detecção de vertices pontes
        for (Map.Entry<String, ArrayList<Pair<String, Integer>>> entry : grafo.entrySet()) {
            for (Pair<String, Integer> par : entry.getValue()) {
                if (!grafo.containsKey(par.getKey())) {
                    grafo.put(par.getKey(), null);
                }
            }
        }
    }
    public Map<String, ArrayList<Pair<String,Integer>>> getGrafo(){
        return grafo;
    }
    public String Vertices(){
        String vertices = "";
        for (Map.Entry<String, ArrayList<Pair<String, Integer>>> entry : grafo.entrySet()) {
            vertices += entry.getKey() + " ";
        }
        return vertices;
    }
    public String DFS() {
        Set<String> visitados = new HashSet<>();
        Stack<String> pilha = new Stack<>();
        StringBuilder resultado = new StringBuilder();

        pilha.push("0");
        visitados.add("0");

        while (!pilha.isEmpty()) {
            String vertice = pilha.pop();
            resultado.append(vertice).append("->");

            ArrayList<Pair<String, Integer>> vizinhos = grafo.get(vertice);
            if (vizinhos != null) {
                for (Pair<String, Integer> par : vizinhos) {
                    if (!visitados.contains(par.getKey())) {
                        pilha.push(par.getKey());
                        visitados.add(par.getKey());
                    }
                }
            }
        }

        return resultado.toString().trim();
    }
     public String BFS() {
        Set<String> visitados = new HashSet<>();
        Queue<String> fila = new LinkedList<>();
        StringBuilder resultado = new StringBuilder();

        fila.add("0");
        visitados.add("0");

        while (!fila.isEmpty()) {
            String vertice = fila.poll();
            resultado.append(vertice).append("->");

            ArrayList<Pair<String, Integer>> vizinhos = grafo.get(vertice);
            if (vizinhos != null) {
                for (Pair<String, Integer> par : vizinhos) {
                    if (!visitados.contains(par.getKey())) {
                        fila.add(par.getKey());
                        visitados.add(par.getKey());
                    }
                }
            }
        }

        return resultado.toString().trim();
    }
    public int getGrau(String vertice){
        return grafo.get(vertice).size();
    }
}
