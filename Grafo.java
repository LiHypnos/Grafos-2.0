import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Grafo {
    Boolean direcionado;
    // Id aresta, array de <origem,aresta,destino>
    Map<String, ArrayList<Pair<String,Integer,String>>> grafo;
    Grafo(Map<String, ArrayList<Pair<String,Integer,String>>> input){
        grafo = new HashMap<>();
        grafo = input;
        if(grafo.get("0").get(1).getOrigin() == grafo.get("0").get(0).getDestiny()){
            direcionado = false;
        } else {
            direcionado = true;
        }
    }
    public boolean isDirecionado(){
        return direcionado;
    }
    public Map<String, ArrayList<Pair<String,Integer,String>>> getGrafo(){
        return grafo;
    }
    
    public ArrayList<String> getVertices() {
        ArrayList<String> vertices = new ArrayList<>();
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if (entry.getValue() != null) {
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    if (!vertices.contains(par.getOrigin())) {
                        vertices.add(par.getOrigin());
                    }
                    if (!vertices.contains(par.getDestiny())) {
                        vertices.add(par.getDestiny());
                    }
                }
            }
        }
        Collections.sort(vertices, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // Converte as strings para números e compara
                return Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2));
            }
        });
        return vertices;
    }

    public HashMap<String, ArrayList<String>> getListaAdj() {
        HashMap<String, ArrayList<String>> listaAdj = new HashMap<>();
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if (entry.getValue() != null) {
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    if (listaAdj.containsKey(par.getOrigin())) {
                        listaAdj.get(par.getOrigin()).add(par.getDestiny());
                    } else {
                        ArrayList<String> adj = new ArrayList<>();
                        adj.add(par.getDestiny());
                        listaAdj.put(par.getOrigin(), adj);
                    }
                }
            }
        }
        return listaAdj;
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
    public Set<String> Arestas(){
        Set<String> arestas = new HashSet<>();
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if(entry.getValue() != null){
                arestas.add(entry.getKey());
            }
        }
        return arestas;
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
    public Pair<String,Integer,String> getLigacoes(String vertice){
        Pair<String,Integer,String> ligacoes = null;
        for (Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()) {
            if(entry.getValue() != null){
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    if(par.getDestiny().equals(vertice)){
                        ligacoes = par;
                    }
                }
            }
        }
        return ligacoes;
    }
    public int getGrau(String vertice){
        int cont = 0;
        for(Map.Entry<String, ArrayList<Pair<String, Integer, String>>> entry : grafo.entrySet()){
            if(entry.getValue() != null){
                for (Pair<String, Integer, String> par : entry.getValue()) {
                    if(par.getDestiny().equals(vertice)){
                        cont++;
                    }
                }
            }
        }
        return cont;
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
        arestas.sort(Comparator.naturalOrder());
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
        List<String> sortedList = new ArrayList<>(arestasVisitadas);
        Collections.sort(sortedList);
        return new HashSet<>(sortedList);
    }
}
