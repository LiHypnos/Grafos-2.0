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
    public String getIdValue(Pair<String,Integer,String> valor){
        String id = "";
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if(entry.getValue() != null){
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    if(entry.getValue().contains(par) && par.equals(valor)){
                        id = entry.getKey();
                    }
                }
            }
        }
        return id;
    }
    public int getGrau(String vertice){
        return grafo.get(vertice).size();
    }
    public List<String> BFS(String initial){
        List<String> visitados = new ArrayList<>();
        List<String> arestas = new ArrayList<>();
        Queue<String> fila = new LinkedList<>();
        fila.add(initial);
        while(!fila.isEmpty()){
            String aresta = fila.poll();
            if(!arestas.contains(aresta)){
                arestas.add(aresta);
                if(grafo.get(aresta) != null){
                    for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
                        if(entry.getValue() != null){
                            for (Pair<String, Integer, String> par : entry.getValue()) {
                                if(par.getDestiny().equals(grafo.get(getIdValue(par)).get(0).getOrigin()) && !visitados.contains(par.getOrigin())){
                                    visitados.add(par.getOrigin());
                                    visitados.add(par.getDestiny());
                                    fila.add(entry.getKey());
                                }
                            }
                        }
                    }
                }
            }
        }
        return arestas;
    }
    public Set<String> DFS(String initial){
        Stack<String> pilha = new Stack<>();
        Set<String> verticesVisitados = new HashSet<>();
        Set<String> arestasVisitadas = new HashSet<>();

        pilha.add(initial);
        verticesVisitados.add(grafo.get(initial).get(0).getDestiny());
        verticesVisitados.add(grafo.get(initial).get(0).getOrigin());
        arestasVisitadas.add(initial);
        while(!pilha.isEmpty()){
            String aresta = pilha.pop();

            if (grafo.get(aresta) != null) {
                for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()){
                    if(entry.getValue() != null){
                        for (Pair<String, Integer, String> par : entry.getValue()) {
                            if(par.getOrigin().equals(grafo.get(aresta).get(0).getDestiny()) && !verticesVisitados.contains(par.getDestiny())){
                                pilha.add(entry.getKey());
                                arestasVisitadas.add(entry.getKey());
                                verticesVisitados.add(par.getOrigin());
                                verticesVisitados.add(par.getDestiny());
                                aresta = pilha.pop();
                            }
                        }
                    }
                }
            }
        }
        return arestasVisitadas;
    }
}
